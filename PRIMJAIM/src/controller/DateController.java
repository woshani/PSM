package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DateController {
	
	public String convertMonthToMalay(String date){
		
		String convertedDate = null;
		
		
		if(date.equals("01")){
			convertedDate = "JANUARI";
		}
		
		else if(date.equals("02")){
			convertedDate = "FEBRUARI";
		}
		
		else if(date.equals("03")){
			convertedDate = "MAC";
		}
		
		else if(date.equals("04")){
			convertedDate = "APRIL";
		}
		
		else if(date.equals("05")){
			convertedDate = "MEI";
		}
		
		else if(date.equals("06")){
			convertedDate = "JUN";
		}
		
		else if(date.equals("07")){
			convertedDate = "JULAI";
		}
		
		else if(date.equals("08")){
			convertedDate = "OGOS";
		}
		
		else if(date.equals("09")){
			convertedDate = "SEPTEMBER";
		}
		
		else if(date.equals("10")){
			convertedDate = "OKTOBER";
		}
		
		else if(date.equals("11")){
			convertedDate = "NOVEMBER";
		}
		
		else if(date.equals("12")){
			convertedDate = "DISEMBER";
		}
		
		return convertedDate;
	}
	
	public String getTodayDate(Connection conn) throws SQLException{
		
			// define sql statement
			String sql = "select to_char(sysdate,'DD-MM-YYYY') from dual";
			
			// create prepared statement
			PreparedStatement ps = conn.prepareStatement(sql);

			/* execute the query on prepared statement and 
			 * create result set to keep result
			 */
			ResultSet rs = ps.executeQuery();

			/* while reading result from result set,
			 * set result information to object
			 */
			String date = null;
			
			while (rs.next()) {
				date = rs.getString(1);
			}
			
			return date;
	}

	
	public String getTodayDate2(Connection conn) throws SQLException{
		
		// define sql statement
		String sql = "SELECT to_char(sysdate, 'DD/MM/YYYY (HH12:MI:SS AM)') FROM DUAL";
		
		// create prepared statement
		PreparedStatement ps = conn.prepareStatement(sql);

		/* execute the query on prepared statement and 
		 * create result set to keep result
		 */
		ResultSet rs = ps.executeQuery();

		/* while reading result from result set,
		 * set result information to object
		 */
		String date = null;
		
		while (rs.next()) {
			date = rs.getString(1);
		}
		
		return date;
}


}
