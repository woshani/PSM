package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import bean.NotificationMessage;
import bean.Project;

public class NotificationController {

	// Method to send Notifications from server to client end.

	// public final static String AUTH_KEY_FCM =
	// "AAAA8nKsqc0:APA91bHAgh2_CY_dwxdh330wlI2OH78OK7pCr74SNQn_X3n_0s2oQYDYXWr6qgqzE6Y6d0qkoUcDl9ynSorAyWv6okSTBFY64gFPKI6ZIJzJjsQ-cBZIJC8gKJ58b6serrazpapzAvt9";
	public final static String API_URL_FCM = "https://fcm.googleapis.com/fcm/send";

	// userDeviceIdKey is the device id you will query from your database

	public static void pushFCMNotification(String userDeviceIdKey,
			NotificationMessage message) throws Exception {	
		try {
			// Create URL instance.
			URL url = new URL(API_URL_FCM);

			// create connection.
			HttpURLConnection conn;
			conn = (HttpURLConnection) url.openConnection();
			conn.setUseCaches(false);
			conn.setDoInput(true);
			conn.setDoOutput(true);

			// set method as POST or GET
			conn.setRequestMethod("POST");

			// pass FCM server key
			conn.setRequestProperty("Authorization", "key="
					+ message.getProject().getAuthKey());

			// Specify Message Format
			conn.setRequestProperty("Content-Type", "application/json");

			conn.setRequestProperty("CharacterEncoding", "utf-8");

			// Create JSON Object & pass value
			JSONObject infoJson = new JSONObject();

			infoJson.put("title", message.getTitle());
			infoJson.put("body", message.getMessage());

			JSONObject json = new JSONObject();
			json.put("to", userDeviceIdKey.trim());
			// json.put("notification", infoJson);
			json.put("data", infoJson);

			//System.out.println("json :" + json.toString());
			//System.out.println("infoJson :" + infoJson.toString());

			OutputStreamWriter wr = new OutputStreamWriter(
					conn.getOutputStream());
			wr.write(json.toString());
			wr.flush();
			int status = 0;

			if (null != conn) {
				status = conn.getResponseCode();
			}
			if (status != 0) {

				if (status == 200) {
					// SUCCESS message
					BufferedReader reader = new BufferedReader(
							new InputStreamReader(conn.getInputStream()));
					System.out.println(status
							+ ": Android Notification Response : "
							+ reader.readLine());
				} else if (status == 401) {
					// client side error
					System.out.println(status
							+ ": Notification Response : TokenId : "
							+ userDeviceIdKey + " Error occurred :");
				} else if (status == 501) {
					// server side error
					System.out
							.println(status
									+ ": Notification Response : [ errorCode=ServerError ] TokenId : "
									+ userDeviceIdKey);
				} else if (status == 503) {
					// server side error
					System.out
							.println(status
									+ ": Notification Response : FCM Service is Unavailable TokenId : "
									+ userDeviceIdKey);
				}
			}
		} catch (MalformedURLException mlfexception) {
			// Protocol Error
			System.out
					.println("Error occurred while sending push Notification!.."
							+ mlfexception.getMessage());
		} catch (Exception mlfexception) {
			// URL problem
			System.out
					.println("Reading URL, Error occurred while sending push Notification!.."
							+ mlfexception.getMessage());
		}

	}

	public void pushFCMNotificationMulti(ArrayList<String> putIds,
			NotificationMessage message) {
		try {

			// Create URL instance.
			URL url = new URL(API_URL_FCM);

			// create connection.
			HttpURLConnection conn;
			conn = (HttpURLConnection) url.openConnection();
			conn.setUseCaches(false);
			conn.setDoInput(true);
			conn.setDoOutput(true);

			// set method as POST or GET
			conn.setRequestMethod("POST");

			// pass FCM server key
			conn.setRequestProperty("Authorization", "key="
					+ message.getProject().getAuthKey());

			// Specify Message Format
			conn.setRequestProperty("Content-Type", "application/json");

			JSONArray regIds = new JSONArray();
			for (int i = 0; i < putIds.size(); i++) {
				regIds.put(putIds.get(i));
			}

			// Create JSON Object & pass value
			JSONObject jsonData = new JSONObject();
			
			jsonData.put("title", message.getTitle());
			jsonData.put("body", message.getMessage());
			
			JSONObject jsonNotification= new JSONObject();
			
			jsonNotification.put("click_action", ".MainActivity");
			jsonNotification.put("body" , message.getMessage());
			jsonNotification.put("title", message.getTitle());
			jsonNotification.put("icon" , "logoutem");
			//jsonNotification.put("time_to_live", 604800);

			JSONObject json = new JSONObject();
			
			
			json.put("registration_ids", regIds);
			//json.put("notification", infoJson);
			json.put("data", jsonData);
			//json.put("notification", jsonNotification);

			System.out.println("json :" + json.toString());
			System.out.println("infoJson :" + jsonData.toString());

			OutputStreamWriter wr = new OutputStreamWriter(
					conn.getOutputStream());

			wr.write(json.toString());
			wr.flush();

			int status = 0;
			
			if (null != conn) {
				status = conn.getResponseCode();
			}
			if (status != 0) {

				if (status == 200) {
					// SUCCESS message
					BufferedReader reader = new BufferedReader(
							new InputStreamReader(conn.getInputStream()));

					StringBuilder sb = new StringBuilder();

				    String line;
				    while ((line = reader.readLine()) != null) {
				        sb.append(line);
				    }
				    
				    JSONObject jsonObjectResult = new JSONObject(sb.toString());
				    
				    //String result = (String)jsonObjectResult.get("results");

					System.out.println("Android Notification Response : "+ sb.toString());
					
					System.out.println("Multicast_id : " + jsonObjectResult.get("multicast_id"));
					System.out.println("Success : " + jsonObjectResult.get("success"));
					System.out.println("Failure : " + jsonObjectResult.get("failure"));
					System.out.println("Canonical_ids : " + jsonObjectResult.get("canonical_ids"));
					System.out.println("Result : " + jsonObjectResult.get("results"));
					

					JSONArray jsonArray = new JSONArray(jsonObjectResult.get("results").toString());
					
					for (int i = 0; i < jsonArray.length(); i++) {
						JSONObject json2 = jsonArray.getJSONObject(i);
						
						System.out.println(json2.toString());
						
						if(json2.toString().contains("message_id")){
							System.out.print("Message Berjaya!");
							
							//check for canonical_id
							if(json2.toString().contains("registration_id")){
								System.out.println(": Canonical_ids : "+json2.get("registration_id").toString());
							}else{
								System.out.println("");
							}
						}else if(json2.toString().contains("error")){
							System.out.println(json2.get("error").toString());
						}
					}
					
					
					
				} else if (status == 401) {
					// client side error
					System.out
							.println("Notification Response : TokenId : Error occurred :");
				} else if (status == 501) {
					// server side error
					System.out
							.println("Notification Response : [ errorCode=ServerError TokenId : ");
				} else if (status == 503) {
					// server side error
					System.out
							.println("Notification Response : FCM Service is Unavailable TokenId : ");
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

	public static void main(String[] args) {
		/*
		 * String userDeviceIdKey=
		 * "c5HXuA5ZOyE:APA91bF1ladPBfE2SaySz14p9ixfaDX0OQia9mdCk6ySLI9MQ8mSShm6hW1cXYMETvcxZjOQ1mYJ7kJ6lHLTFRyxU-9T1tPjz2apwuUJLVeehQi1VkPtHcYL4QBf7gM3qdba7ml_JE-H"
		 * ; NotificationMessage message = new
		 * NotificationMessage("Alpha Test");
		 * 
		 * FirebaseController fc = new FirebaseController(); try {
		 * fc.pushFCMNotification(userDeviceIdKey, message); } catch (Exception
		 * e) { // TODO Auto-generated catch block e.printStackTrace(); }
		 */

		ArrayList<String> usersToken = new ArrayList<String>();

		String userDeviceIdKey1 = "eU5R2ok8Kd8:APA91bFpNAEQB387PpDsZEdGGi2FNN9iJz0FKhd3l2k72U9DvZXlIb0jutezS9zMHswrw9MiEMKSsGeMGVCepK6A0HnpZ6Rf1ukpxNBWwRSJQPO5cKstYkUZzuccSiTguyslhV3unoTr";
		String userDeviceIdKey2 = "eCtSm3159G8:APA91bFUK4c6-EN1m7aZ1wLv3m_Lfy7J-pmfJ9ivmdBm3n-DzIfhj-c8Ok77oDzfSkIKXPq491jr3aJ4ZqP1A29Hh6hyLUORrZzxGnlW1Bqrfm8Fa0VkqWpE61meXe-dJ6pmQTQr7wmx";
		String userDeviceIdKey3 = "chpnKUc2BaE:APA91bHpD18szMrBdb5WCyre2X1UcL57a1X5Nu74CjW31YLNlrFBch8XaXw8ypI-bFUOWRSBIqGTWpZ0SGppfbtjy-cYymXYIOqzt1OJ9xmqykyVA9Ei70rf_LChMKII7s6HEL0G410L";
		
		usersToken.add(userDeviceIdKey1);
		//usersToken.add(userDeviceIdKey2);
		usersToken.add(userDeviceIdKey3);

		String projectName = "GPANOTI";
		String authKey = "AAAAohCpuoo:APA91bG3rgqdkn8XdgWT_P7Sp-g8kBt0CMjDGu58VcRrfU5-3_mEyFbI3onCEVjrMNGGYmXD7DPITPqwKZHeHJgshnYAWXCdZL3SOShUqqrJ5RBRjvpgbhWyVHOjYJY6L4mvm-kCvVM9";

		Project project = new Project(projectName, authKey);

		NotificationMessage message = new NotificationMessage(project.getName()
				+ " System: ", "Alpha Beta Test", project);

		NotificationController nc = new NotificationController();
		try {
			//nc.pushFCMNotificationMulti(usersToken, message);
			nc.pushFCMNotification(userDeviceIdKey3, message);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
