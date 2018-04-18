package controller;

import org.json.JSONArray;
import org.json.JSONObject;

public class TestController {

	public static void main(String[] args) {
		JSONObject jsonObjectResult = new JSONObject("{ \"multicast_id\": 216,\"success\": 3,\"failure\": 3,\"canonical_ids\": 1,\"results\": [{ \"message_id\": \"1:0408\" },{ \"error\": \"Unavailable\" },{ \"error\": \"InvalidRegistration\" },{ \"message_id\": \"1:1516\" },{ \"message_id\": \"1:2342\", \"registration_id\": \"32\" },{ \"error\": \"NotRegistered\"}]}");
	    
	    //String result = (String)jsonObjectResult.get("results");

		System.out.println("Android Notification Response : "+ jsonObjectResult.toString());
		
		System.out.println("Multicast_id : " + jsonObjectResult.get("multicast_id"));
		System.out.println("Success : " + jsonObjectResult.get("success"));
		System.out.println("Failure : " + jsonObjectResult.get("failure"));
		System.out.println("Canonical_ids : " + jsonObjectResult.get("canonical_ids"));
		
		System.out.println("Result : " + jsonObjectResult.get("results").toString());
		
		//JSONObject jsonObjectResult2 = new JSONObject(jsonObjectResult.get("results").toString().replaceAll("[\\[\\]]", ""));
		
		
		JSONArray jsonArray = new JSONArray(jsonObjectResult.get("results").toString());
		
		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject json = jsonArray.getJSONObject(i);
			
			//System.out.println(json.toString());
			
			if(json.toString().contains("message_id")){
				System.out.print("Message Berjaya!");
				
				//check for canonical_id
				if(json.toString().contains("registration_id")){
					System.out.println(": Canonical_ids : "+json.get("registration_id").toString());
				}else{
					System.out.println("");
				}
			}else if(json.toString().contains("error")){
				System.out.println(json.get("error").toString());
			}
		}
		
	}
}
