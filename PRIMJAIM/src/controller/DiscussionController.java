package controller;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;

import bean.Discussion;
import bean.Topic;
import oracle.jdbc.OracleTypes;


public class DiscussionController {
	
	public Vector<Discussion> getAllDiscussionsMain(Topic topic, Connection conn) throws Exception {

		Vector<Discussion> discussions = new Vector<Discussion>();
		
		// prepare call store procedure
		String getAllDiscussionMain = "{ CALL ? := GETALLDISCUSSIONMAINFUNC(?) }";
		CallableStatement cs = conn.prepareCall(getAllDiscussionMain);

		cs.registerOutParameter(1, OracleTypes.CURSOR);
		cs.setInt(2, topic.getTopicId());

		// execute and retrieve the results
		cs.execute();
		ResultSet rs = (ResultSet) cs.getObject(1);
		
		if (rs != null){			
			while (rs.next()) {
				
				Discussion discussion = new Discussion();
				
				discussion.setDiscussionId(rs.getInt(1));
				discussion.setTeacherId(rs.getString(2));
				discussion.setTeacherName(rs.getString(3));
				discussion.setText(rs.getString(4));
				discussion.setPostDate(rs.getString(5));
				
				discussions.add(discussion);	
			}	
		}		
		return discussions;
	}

}
