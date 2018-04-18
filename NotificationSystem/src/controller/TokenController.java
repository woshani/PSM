package controller;

import java.sql.CallableStatement;
import java.sql.Connection;

import connection.DBConnection;
import oracle.jdbc.OracleTypes;

public class TokenController {

	Connection conn;

	public String TokenVerification(String userId, String token, int projectId)
			throws Exception {

		DBConnection dbConn = new DBConnection();
		String result = "Not Process";

		conn = dbConn.getConnection();

		String sql = "{call ? := googleTokenMain(?,?,?)}";

		// create prepared statement
		CallableStatement cs = conn.prepareCall(sql);

		// set prepared statement's parameters
		cs.registerOutParameter(1, OracleTypes.VARCHAR);
		cs.setString(2, userId);
		cs.setString(3, token);
		cs.setInt(4, projectId);

		cs.execute();

		result = cs.getString(1);
		
		conn.close();
		return result;

	}
	
	public String getTokenFromUserId (String vUserRealId, int pid) throws Exception {
		
		DBConnection dbConn = new DBConnection();
		String result = "Not Process";

		conn = dbConn.getConnection();

		String sql = "{call ? := GETREGISTEREDUSERTOKEN(?,?)}";

		// create prepared statement
		CallableStatement cs = conn.prepareCall(sql);

		// set prepared statement's parameters
		cs.registerOutParameter(1, OracleTypes.VARCHAR);
		cs.setString(2, vUserRealId);
		cs.setInt(3, pid);
		

		cs.execute();

		result = cs.getString(1);
		
		conn.close();
		
		return result;
		
	}
	
	public static void main(String[] args) {
		String userId = "P100093748";
		String token = "duqInQX01us:APA91bGMOF-Wxw57zGQ7uGsKuqwAQL1HhNVKsYlRqXDU35CxvWLvx15rIkYv6vThnswOrZufOV298WQttHSY529mU1FF7Zatz7mGWxhmyVe72FzFLG9xs37W1uUkT-ABMSQdCdvxcUQV";
		int projectId = 1;
		
		String result = "0";
		
		TokenController tokenCtrl = new TokenController();
		
		try {
			//result = tokenCtrl.TokenVerification(userId, token, projectId);
			result = tokenCtrl.getTokenFromUserId("P100093747", projectId);
		} catch (Exception e) {
			result = e.getMessage();
			e.printStackTrace();
		}
		System.out.println(result);
		
		try {
			//result = tokenCtrl.TokenVerification(userId, token, projectId);
			result = tokenCtrl.getTokenFromUserId("P100093752", projectId);
		} catch (Exception e) {
			result = e.getMessage();
			e.printStackTrace();
		}
		System.out.println(result);
	}

}
