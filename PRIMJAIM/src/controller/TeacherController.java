package controller;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import oracle.jdbc.OracleTypes;
import bean.Teacher;
import bean.TeacherClass;

public class TeacherController {
	/*
	 * Declaration of variables
	 */
	private UserController userCtrl = new UserController();

	public int checkTeacherId(String teacher_id, Connection conn) 
			throws Exception {
		// define sql statement
		String sql = "{call ? := checkTeacherId(?)}";
		
		// create prepared statement
		CallableStatement cs = conn.prepareCall(sql);
		
		// set prepared statement's parameters
		cs.registerOutParameter(1, OracleTypes.NUMBER);
		cs.setString(2, teacher_id);
		
		cs.executeQuery();

		int status = -1;

		status = cs.getInt(1);

		
		return status;
	}
	
	public List<Teacher> getAllTeachers(Connection conn) 
			throws Exception {
		// List to keep results retrieved from database
		List<Teacher> teachers = new ArrayList<Teacher>();
		
		// define sql statement
		String sql = "{call ? := getAllTeachers()}";
		
		// create prepared statement
		CallableStatement cs = conn.prepareCall(sql);
		
		cs.registerOutParameter(1, OracleTypes.CURSOR);
		
		cs.executeQuery();
		
		/* execute the query on prepared statement and 
		 * create result set to keep result
		 */
		ResultSet rs = (ResultSet) cs.getObject(1);

		/* while reading result from result set,
		 * set result information to object
		 */
		while (rs.next()) {
			// Object to keep results retrieved from database
			Teacher teacher = new Teacher();
			
			teacher.setTeacher_id(rs.getString(1));
			teacher.setUser(userCtrl.getUserById(rs.getString(2), conn));
			teacher.setName(rs.getString(3));
			teacher.setGender(rs.getString(4));
			teacher.setIc_number(rs.getString(5));
			teacher.setMarital_status(rs.getString(6));
			teacher.setAddress(rs.getString(7));
			teacher.setTelno_ext(rs.getString(8));
			teacher.setTelno_house(rs.getString(9));
			teacher.setTelno_hp(rs.getString(10));
			teacher.setPosition(rs.getString(11));
			
			// add object to List
			teachers.add(teacher);
		}

		return teachers;
	}

	public Teacher getTeacherById(String teacher_id, Connection conn) 
			throws Exception {
		// Object to keep results retrieved from database
		Teacher teacher = new Teacher();
		
		// define sql statement
		String sql = "{call ? := getTeacherById(?)}";
		
		// create prepared statement
		CallableStatement cs = conn.prepareCall(sql);
		
		// set prepared statement's parameters
		cs.registerOutParameter(1, OracleTypes.CURSOR);
		cs.setString(2, teacher_id);
		
		cs.executeQuery();

		/* execute the query on prepared statement and 
		 * create result set to keep result
		 */
		ResultSet rs = (ResultSet) cs.getObject(1);

		/* while reading result from result set,
		 * set result information to object
		 */
		while (rs.next()) {
			teacher.setTeacher_id(rs.getString(1));
			teacher.setUser(userCtrl.getUserById(rs.getString(2), conn));
			teacher.setName(rs.getString(3));
			teacher.setGender(rs.getString(4));
			teacher.setIc_number(rs.getString(5));
			teacher.setMarital_status(rs.getString(6));
			teacher.setAddress(rs.getString(7));
			teacher.setTelno_ext(rs.getString(8));
			teacher.setTelno_house(rs.getString(9));
			teacher.setTelno_hp(rs.getString(10));
			teacher.setPosition(rs.getString(11));
		}

		return teacher;
	}

	public Teacher getTeacherByUserId(String user_id, Connection conn) 
			throws Exception {
		// Object to keep results retrieved from database
		Teacher teacher = new Teacher();
		
		// define sql statement
		String sql = "{call ? := getTeacherByUserId(?)}";
		
		// create prepared statement
		CallableStatement cs = conn.prepareCall(sql);
		
		// set prepared statement's parameters
		cs.registerOutParameter(1, OracleTypes.CURSOR);
		cs.setString(2, user_id);
		
		cs.executeQuery();

		/* execute the query on prepared statement and 
		 * create result set to keep result
		 */
		ResultSet rs = (ResultSet) cs.getObject(1);

		/* while reading result from result set,
		 * set result information to object
		 */
		while (rs.next()) {
			teacher.setTeacher_id(rs.getString(1));
			teacher.setUser(userCtrl.getUserById(rs.getString(2), conn));
			teacher.setName(rs.getString(3));
			teacher.setGender(rs.getString(4));
			teacher.setIc_number(rs.getString(5));
			teacher.setMarital_status(rs.getString(6));
			teacher.setAddress(rs.getString(7));
			teacher.setTelno_ext(rs.getString(8));
			teacher.setTelno_house(rs.getString(9));
			teacher.setTelno_hp(rs.getString(10));
			teacher.setPosition(rs.getString(11));
		}

		return teacher;
	}
	
	public List<Teacher> getTeachersByClassroomId(String class_id, 
			Connection conn) throws Exception {
		// List to keep results retrieved from database
		List<Teacher> teachers = new ArrayList<Teacher>();
		
		// define sql statement
		String sql = "{call ? := getTeachersByClassroomId(?)}";
		
		// create prepared statement
		CallableStatement cs = conn.prepareCall(sql);
		
		// set prepared statement's parameters
		cs.registerOutParameter(1, OracleTypes.CURSOR);
		cs.setString(2, class_id);
		
		cs.executeQuery();

		/* execute the query on prepared statement and 
		 * create result set to keep result
		 */
		ResultSet rs = (ResultSet) cs.getObject(1);

		/* while reading result from result set,
		 * set result information to object
		 */
		while (rs.next()) {
			// Object to keep results retrieved from database
			Teacher teacher = new Teacher();
			
			teacher = getTeacherById(rs.getString(1), conn);
			
			teachers.add(teacher);
		}

		return teachers;
	}
	
	public Teacher getTeacherByClassroomId(String class_id, 
			Connection conn) throws Exception {
		// List to keep results retrieved from database
		Teacher teacher = new Teacher();
		
		// define sql statement
		String sql = "{call ? := getTeacherByClassroomId(?)}";
		
		// create prepared statement
		CallableStatement cs = conn.prepareCall(sql);
		
		// set prepared statement's parameters
		cs.registerOutParameter(1, OracleTypes.CURSOR);
		cs.setString(2, class_id);
		
		cs.executeQuery();

		/* execute the query on prepared statement and 
		 * create result set to keep result
		 */
		ResultSet rs = (ResultSet) cs.getObject(1);

		/* while reading result from result set,
		 * set result information to object
		 */
		while (rs.next()) {
			// Object to keep results retrieved from database
			
			teacher = getTeacherById(rs.getString(1), conn);
			
		}

		return teacher;
	}
	
	public Vector<TeacherClass> TeacherList(Connection conn) throws Exception {

		Vector<TeacherClass> teacherClasss = new Vector<TeacherClass>();
		TeacherClass teacherClass = null;
		String sql = null;
		
		sql = "{ call ? := teacherList}";
		CallableStatement cs = conn.prepareCall(sql);

		// register the type of the out parameter - an Oracle specific type
		cs.registerOutParameter(1, OracleTypes.CURSOR);

		// execute and retrieve the results
		cs.execute();
		ResultSet rs = (ResultSet) cs.getObject(1);

		while (rs.next()) {
			teacherClass = new TeacherClass();
			teacherClass.setTeacherId(rs.getString(1));
			teacherClass.setTeacherName(rs.getString(2));
			teacherClass.setClassName(rs.getString(3));
			

			teacherClasss.add(teacherClass);
		}

		return teacherClasss;
	}
	
	public int updateTeacher(Teacher teacher, Connection conn) throws SQLException, Exception {
		// define sql statement
		String sql = "{call ? := updateTeacher(?,?,?,?,?,?,?,?,?)}";

		// create prepared statement
		CallableStatement cs = conn.prepareCall(sql);

		// set prepared statement's parameters
		cs.registerOutParameter(1, OracleTypes.INTEGER);
		cs.setString(2, teacher.getTeacher_id());
		cs.setString(3, teacher.getName());
		cs.setString(4, teacher.getGender());
		cs.setString(5, teacher.getIc_number());
		cs.setString(6, teacher.getMarital_status());
		cs.setString(7, teacher.getAddress());
		cs.setString(8, teacher.getTelno_hp());
		cs.setString(9, teacher.getTelno_house());
		cs.setString(10, teacher.getTelno_ext());
		
		cs.execute();
		
		int status = (Integer) cs.getObject(1);

		return status;
	}
}
