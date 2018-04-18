package controller;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Types;

import bean.Meeting;

public class MeetingController {
	
	public Meeting insertMeeting(Meeting meeting, String instId, Connection conn) 
			throws Exception {
		//prepare call store procedure
		String sql = "{ call insert_meeting(?,?,?,?,?) }";
		CallableStatement cs = conn.prepareCall(sql);
	
		// register the type of the out parameter - an Oracle specific type
		cs.setString(1, meeting.getMeetingDate());
		cs.setString(2, meeting.getMeetingTime());
		cs.setString(3, meeting.getMeetingDetails());
		cs.setString(4, instId);
		cs.registerOutParameter(5, Types.VARCHAR);
		//System.out.println(user.getId());
		// set the in parameter
		
		cs.execute();
		
		//execute and retrieve the results
		meeting.setMeetingId(cs.getString(5));
		cs.close();
		
		return meeting;
	}
	
}
