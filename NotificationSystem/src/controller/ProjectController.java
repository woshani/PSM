package controller;

import java.sql.CallableStatement;
import java.sql.Connection;

import bean.Project;
import connection.DBConnection;
import oracle.jdbc.OracleTypes;

/**
 * @author Mohamad Idzhar
 * 
 */
public class ProjectController {

	/* Object and variable declaration */
	Connection conn;

	public String getProjectAuthKey(String projectId) {

		String result = "0";
		DBConnection DBConn = new DBConnection();

		try {
			conn = DBConn.getConnection();

			
			// prepare call store pro
			String getProjectAuthKey = "{ CALL ? := GETPROJECTAUTHKEY(?) }";
			CallableStatement cs = conn.prepareCall(getProjectAuthKey);

			// register the type of the out param - an Oracle specific type for
			// Total
			cs.registerOutParameter(1, OracleTypes.VARCHAR);

			// set in parameter
			cs.setString(2, projectId);

			// register the type of the out param - an Oracle specific type for
			// Base and Count

			// execute and retrieve the results
			cs.executeUpdate();

			if (cs.getObject(1) != null) {
				result = (String) cs.getObject(1);
			} else {
				result = "0";
			}

			conn.close();
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public Project getAuthToken(int projectId) {

		DBConnection DBConn = new DBConnection();
		Project resultProject = new Project();

		try {
			conn = DBConn.getConnection();

			
			// prepare call store pro
			String getProjectAuthKey = "{ CALL ? := GETAUTHTOKEN(?,?) }";
			CallableStatement cs = conn.prepareCall(getProjectAuthKey);

			// register the type of the out param - an Oracle specific type for
			// Total
			cs.registerOutParameter(1, OracleTypes.VARCHAR);
			cs.registerOutParameter(3, OracleTypes.VARCHAR);
			// set in parameter
			cs.setInt(2, projectId);

			// register the type of the out param - an Oracle specific type for
			// Base and Count

			// execute and retrieve the results
			cs.executeUpdate();

			if (cs.getObject(1) != null) {
				//result = (String) cs.getObject(1);
				resultProject.setAuthKey((String) cs.getObject(1));
				resultProject.setName((String) cs.getObject(3));
			} else {
				resultProject.setAuthKey("0");;
			}

			conn.close();
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultProject;
	}

}
