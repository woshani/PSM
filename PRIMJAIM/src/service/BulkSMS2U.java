package service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import bean.Message;
import controller.GuardianController;
import facade.ControllerWrapper;

public class BulkSMS2U {
	private String webpage = "http://www.bulksms2u.com/websmsapi/ISendSMS.aspx";
	//private String username = "yahya8870";
	//private String password = "prim8870";
	//private String sender = "PRM";
	
	private String username = "weisy915";
	private String password = "87364544";
	
	private GuardianController grdnCtrl = new GuardianController();
	
	public String httpUrlAccess(String url) {
		BufferedReader buffRead;
		String response = "";
		try {
			url = url.replace(" ", "%20");
			URL endpointUrl = new URL(url);
			java.net.HttpURLConnection request;
			request = (HttpURLConnection) endpointUrl.openConnection();
			request.setRequestMethod("GET");
			request.connect();

			buffRead  = new BufferedReader(new InputStreamReader(
					request.getInputStream()));
			String line = null;
			while ((line = buffRead.readLine()) != null){
				response += line + "\n";
			}
		} catch (ProtocolException e) {
			response = "Protocol Exception";
			e.printStackTrace();
		} catch (MalformedURLException e) {
			response = "Malformed URL Exception";
			e.printStackTrace();
		} catch (IOException e) {
			response = "IO Exception";
			e.printStackTrace();
		}
		
		return response;
	}
	
	public String sendSMSs(List<Message> messages, String sender, Connection conn) 
			throws Exception {
		
	    String response = "No response";
	    
	    int success = 0, failed = 0;
		
	    int counter = 1;
	    
		for(Message message : messages) {
			
			String[] sentences = message.getMsgText().split("\n");
			
			String textLine = "";

			// process to trim end line and whitespace from text and transform into one single line text.
			for (int i = 0; i < sentences.length; i++) {
				
				// trim and transform process
				textLine = textLine+""+sentences[i].substring(0, sentences[i].length()).trim()+" ";
			
			}
			System.out.println("Mesej : "+ textLine);
			
			String url = webpage + "?username=" + username +
					"&password=" + password +
					"&message= RM0.00 ("+sender+") " + textLine +
					"&mobile=" + message.getReceiver() +
					"&sender="+sender+
					"&type=1";
			
			response = httpUrlAccess(url.trim());
			System.out.println(response.toString());
			
			if(response.contains("1701")) {
				//grdnCtrl.sendAlertToGuardian(message.getGuardian(), conn);
				success++;
				//System.out.println("Succesful sent to : "+message.getReceiver());
				
			} else{
				System.out.println("response: " + response + url.trim());
				failed++;
			}
			
			counter = counter+ 1;
		}
		
		return success + " message(s) sent, " + failed + " message(s) failed.";
	}
	
	public String checkBalance() {
	    String response = "No response";
		String url = "http://www.bulksms2u.com/websmsapi/creditsLeft.aspx" + 
				"?username=" + username +
				"&password=" + password;
		response = httpUrlAccess(url);
		
		return response;
	}
	
	public static void main(String[] args) {
		String result = new BulkSMS2U().checkBalance();
		System.out.println("REMAIN SMS : "+result);
		
		//test send message
				
		List<Message> messages = new ArrayList<Message>();
		
		Message message = new Message();
		message.setReceiver("60133008743");
		message.setMsgText("Test drive sms bulk - Rest01");
		
		messages.add(message);
			
		String sender = "MCT2001";
		
		ControllerWrapper ctrl = new ControllerWrapper();
		
		try{
			String resultSend = ctrl.sendSMSs(messages, sender);
			System.out.println(resultSend);
		}catch (Exception e){
			e.printStackTrace();
		}
		
		/*1701:[MSG ID]	Message Sent Successfully.
		1702	Invalid Username/Password
		1703	Internal Server Error
		1704	Insufficient Credits
		1705	Invalid Mobile Number
		1706	Invalid Message / Invalid SenderID
		1707	Transfer Credits Successful
		1708	Account not existing for Credits Transfer
		1709	Invalid Credits Value for Credits Transfer
		1718	Duplicate record received*/


	}
	
	
}
