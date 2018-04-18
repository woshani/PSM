package controller;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import bean.Student;
import connection.OracleConnection;
import oracle.jdbc.OracleTypes;

public class MobileController {
	
	//declare important part here!
			private OracleConnection dbconn;
			private Connection conn;
			
		public MobileController() {
			dbconn = new OracleConnection();
		}
		
		public Student loginMobile(String matricNo,String pass,String token) {
			Student stud = new Student();
			String sql = "{call loginmobile(?,?,?,?,?,?)}";
			CallableStatement cstm = null;
			
	        try {
	            conn = dbconn.getConnection();
	            cstm = conn.prepareCall(sql);
	            cstm.setString(1, matricNo);
	            cstm.setString(2, pass);
	            cstm.registerOutParameter(3, OracleTypes.VARCHAR);
	            cstm.registerOutParameter(4, OracleTypes.VARCHAR);
	            cstm.registerOutParameter(5, OracleTypes.VARCHAR);
	            cstm.setString(6, token);

	            cstm.executeQuery();
	            
	            stud.setMatric(matricNo);
	            stud.setName(cstm.getString(4));
	            stud.setPhone(cstm.getString(5));
	            
	            cstm.close();
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        return stud;
		}
}
