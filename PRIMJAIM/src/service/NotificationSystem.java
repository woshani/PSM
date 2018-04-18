package service;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bean.CustomPostParam;
import bean.Message;

public class NotificationSystem {
	
	private String webpage = "http://primapps.utem.edu.my/NotificationSystem/Send";
	//private String webpage = "http://localhost:8080/NotificationSystem/Send";
	
	public void notificationServerAccess(ArrayList<String> putIds,
			Message message) {
		try {

			StringBuilder sb=  new StringBuilder();
			sb.append("Hello there");

			URL url = new URL(webpage);
			HttpURLConnection connection = (HttpURLConnection)url.openConnection();                
			connection.setDoOutput(true);
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Length", "" + sb.length());

			OutputStreamWriter outputWriter = new OutputStreamWriter(connection.getOutputStream());
			outputWriter.write(sb.toString());
			outputWriter.flush();
			outputWriter.close();

			int status = 0;
			
			if (null != connection) {
				status = connection.getResponseCode();
			}
			
			if (status != 0) {

				if (status == 200) {
					// SUCCESS message
					System.out.println("Notification Status : Berjaya "+status);
				}else{
					System.out.println("Notification Status : "+status);
				}
			}
			
				    

		} catch (MalformedURLException mlfexception) {
			// Protocol Error
			System.out
					.println("Error occurred while sending push Notification!.."
							+ mlfexception.getMessage());
		} catch (IOException mlfexception) {
			// URL problem
			System.out
					.println("Reading URL, Error occurred while sending push Notification!.."
							+ mlfexception.getMessage());
		} catch (Exception exception) {
			// General Error or exception.
			System.out
					.println("Error occurred while sending push Notification!.."
							+ exception.getMessage());
		}
	}
	
	public String sendNotificationMulti(List<Message> messages, String sender) 
			throws Exception {
		
	    String response = "No response";
	    
	    int success = 0, failed = 0;
		
	    int counter = 1;
	    
	    
	    ArrayList<String> receivers = new ArrayList<>();
	    
	    URL url = new URL(webpage);
	    
        ArrayList<CustomPostParam> postParams = new ArrayList<CustomPostParam>();
        
        String resultUserIds ="";
        
        ArrayList<String> list = new ArrayList<String>();
        
        
	    
		for(Message message : messages) {
				
			String[] sentences = message.getMsgText().split("\n");
			
			String textLine = "";
			String resultReceiver="";

			// process to trim end line and whitespace from text and transform into one single line text.
			for (int i = 0; i < sentences.length; i++) {
				
				// trim and transform process
				textLine = textLine+""+sentences[i].substring(0, sentences[i].length()).trim()+" ";
			
			}
			//System.out.println("Mesej : "+ textLine);
			
			//System.out.println("Receiver : "+message.getReceiverId());
			receivers.add(message.getReceiverId());
			
			list.add(message.getReceiverId());
			
			CustomPostParam postParam = new CustomPostParam("userId", message.getReceiverId());
			
			postParams.add(postParam);
		
			counter = counter+ 1;
		}
		
		   
	    postParams.add(new CustomPostParam("t", sender));
	    postParams.add(new CustomPostParam("m", messages.get(0).getMsgText()));
	    postParams.add(new CustomPostParam("p","1"));
		
        StringBuilder postData = new StringBuilder();
              
        for (CustomPostParam param : postParams) {
            if (postData.length() != 0){
            	postData.append('&');
            }
            postData.append(URLEncoder.encode(param.getName(), "UTF-8"));
            postData.append('=');
            postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
        }
        byte[] postDataBytes = postData.toString().getBytes("UTF-8");
        
        //System.out.println("Parameter : "+postData.toString());

        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
        
        
        // Send post request
     	conn.setDoOutput(true);
     	DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
     	
     	//System.out.println("Parameter : "+postData.toString());
     	
     	wr.writeBytes(postData.toString());
     	wr.flush();
     	wr.close();

     	BufferedReader in = new BufferedReader(
		        new InputStreamReader(conn.getInputStream()));
		String inputLine;
		StringBuffer responseBuffer = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			responseBuffer.append(inputLine);
		}
		in.close();

		//print result
		System.out.println(responseBuffer.toString());
        
        System.out.println("Result send NS : "+conn.getResponseCode());
            
    	return success + " message(s) sent, " + failed + " message(s) failed.";
	}

}
