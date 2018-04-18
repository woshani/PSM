package controller;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;

import bean.Teacher;
import bean.Topic;
import oracle.jdbc.OracleTypes;

public class TopicController {

	public Vector<Topic> getAllTopicsMain(Connection conn) throws Exception {

		Vector<Topic> topics = new Vector<Topic>();

		// prepare call store procedure
		String getAllTopicMain = "{ CALL ? := GETALLTOPICMAINFUNC() }";
		CallableStatement cs = conn.prepareCall(getAllTopicMain);

		cs.registerOutParameter(1, OracleTypes.CURSOR);

		// execute and retrieve the results
		cs.execute();
		ResultSet rs = (ResultSet) cs.getObject(1);

		if (rs != null) {
			while (rs.next()) {

				Topic topic = new Topic();

				topic.setTopicId(rs.getInt(1));
				topic.setTeacherId(rs.getString(2));
				topic.setTeacherName(rs.getString(3));
				topic.setTitle(rs.getString(4));
				topic.setText(rs.getString(5));
				topic.setPostDate(rs.getString(6));
				topic.setTotalDiscussion(rs.getInt(7));

				topics.add(topic);
			}
		}
		return topics;
	}

	public Topic getTopicByIdFunc(Topic topic, Connection conn) throws Exception {

		
		// prepare call store procedure
		String getTopicById = "{ CALL ? := GETTOPICBYIDFUNC(?) }";
		CallableStatement cs = conn.prepareCall(getTopicById);

		cs.registerOutParameter(1, OracleTypes.CURSOR);
		cs.setInt(2, topic.getTopicId());

		// execute and retrieve the results
		cs.execute();
		ResultSet rs = (ResultSet) cs.getObject(1);

		if (rs.next()) {
				topic.setTopicId(rs.getInt(1));
				topic.setTeacherId(rs.getString(2));
				topic.setTeacherName(rs.getString(3));
				topic.setTitle(rs.getString(4));
				topic.setText(rs.getString(5));
				topic.setPostDate(rs.getString(6));
				topic.setTotalDiscussion(rs.getInt(7));
		}		
		return topic;
	}

	
	public Vector<Topic> getAllTopicByUser(Teacher teacher, Connection conn)
			throws Exception {

		Vector<Topic> userTopics = new Vector<Topic>();

		// prepare call store procedure
		String getAllTopicByUser = "{ CALL ? := GETALLTOPICUSERFUNC(?) }";
		CallableStatement cs = conn.prepareCall(getAllTopicByUser);

		cs.registerOutParameter(1, OracleTypes.CURSOR);
		cs.setString(2, teacher.getTeacher_id());

		// execute and retrieve the results
		cs.execute();
		ResultSet rs = (ResultSet) cs.getObject(1);

		if (rs != null) {
			while (rs.next()) {

				Topic userTopic = new Topic();

				userTopic.setTopicId(rs.getInt(1));
				userTopic.setTeacherId(rs.getString(2));
				userTopic.setTeacherName(rs.getString(3));
				userTopic.setTitle(rs.getString(4));
				userTopic.setText(rs.getString(5));
				userTopic.setPostDate(rs.getString(6));
				userTopic.setTotalDiscussion(rs.getInt(7));

				userTopics.add(userTopic);
			}
		}
		return userTopics;
	}

}
