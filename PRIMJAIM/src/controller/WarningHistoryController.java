package controller;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import oracle.jdbc.OracleTypes;
import bean.Warning;
import bean.WarningHistory;


public class WarningHistoryController {
	
	public List<WarningHistory> getAllWarningHistory(String studentId, Connection conn) throws Exception{
		
		List<WarningHistory> warningHistories = new ArrayList<WarningHistory>();
		
		//define sql statement
		String sql = "{ call getAllWarningHistory(?,?) }";
		
		// create prepared statement
		CallableStatement cs = conn.prepareCall(sql);
		
		cs.setString(1, studentId);
		cs.registerOutParameter(2, OracleTypes.CURSOR);
		
				
		cs.execute();
		
		/* execute the query on prepared statement and 
		 * create result set to keep result
		 */
		ResultSet rs = (ResultSet) cs.getObject(2);

		/* while reading result from result set,
		 * set result information to object
		 */
		while (rs.next()) {
			// Store results retrieved from database
			WarningHistory warningHistory = new WarningHistory();
			Warning warning = new Warning();
			
			warningHistory.setWhId(rs.getInt(1));
			
			warning.setDescription(rs.getString(2));
			warning.setWarningType(rs.getString(3));
			warningHistory.setWarning(warning);
			
			warningHistory.setWarningDate(rs.getDate(4));
			
			warningHistories.add(warningHistory);
			
		}
		
		return warningHistories;
	}

}
