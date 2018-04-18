package controller;

import java.sql.CallableStatement;
import java.sql.Connection;

import bean.KokuAttendance;

public class KokuAttendanceController {

	
	public KokuAttendance insertAttendance(KokuAttendance kokuAttendance, 
			Connection conn) throws Exception {
		//prepare call store procedure
		String sql = "{ call insert_student_attend(?,?) }";
		CallableStatement cs = conn.prepareCall(sql);
		
		// register the type of the out parameter - an Oracle specific type
		cs.setString(1, kokuAttendance.getStudentId().getStudent_id());
		cs.setString(2, kokuAttendance.getMeetingId().getMeetingId());
		
		cs.execute();
		cs.close();
		
		return kokuAttendance;
	}
	
	
}
