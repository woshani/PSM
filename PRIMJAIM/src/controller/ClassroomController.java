package controller;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import oracle.jdbc.OracleTypes;

import bean.Classroom;

public class ClassroomController {
	/*
	 * Declaration of variables
	 */
	private InstituitionController instCtrl = new InstituitionController();

	public int checkClassroomId(String class_id, Connection conn)
			throws Exception {
		// define sql statement
		String sql = "{call ? := checkClassroomId(?)}";

		// create prepared statement
		CallableStatement cs = conn.prepareCall(sql);

		// set prepared statement's parameters
		cs.registerOutParameter(1, OracleTypes.NUMBER);
		cs.setString(2, class_id);

		cs.execute();

		int status = cs.getInt(1);

		return status;
	}

	public List<Classroom> getAllClassrooms(Connection conn) throws Exception {
		// List to keep results retrieved from database
		List<Classroom> classrooms = new ArrayList<Classroom>();

		// define sql statement
		String sql = "{call ? := getAllClassrooms()}";

		// create prepared statement
		CallableStatement cs = conn.prepareCall(sql);

		cs.registerOutParameter(1, OracleTypes.CURSOR);

		cs.execute();

		/*
		 * execute the query on prepared statement and create result set to keep
		 * result
		 */
		ResultSet rs = (ResultSet) cs.getObject(1);

		/*
		 * while reading result from result set, set result information to
		 * object
		 */
		while (rs.next()) {
			// Object to keep results retrieved from database
			Classroom classroom = new Classroom();

			classroom.setClass_id(rs.getString(1));
			classroom.setInstituition(instCtrl.getInstituitionById(
					rs.getString(2), conn));
			classroom.setName(rs.getString(3));

			// add object to List
			classrooms.add(classroom);
		}

		return classrooms;
	}

	public Classroom getClassroomById(String classroom_id, Connection conn)
			throws Exception {
		// Object to keep results retrieved from database
		Classroom classroom = new Classroom();

		// define sql statement
		String sql = "{ call ? := getClassroomById(?)}";

		// create prepared statement
		CallableStatement cs = conn.prepareCall(sql);

		// set prepared statement's parameters
		cs.registerOutParameter(1, OracleTypes.CURSOR);
		cs.setString(2, classroom_id);

		cs.execute();
		/*
		 * execute the query on prepared statement and create result set to keep
		 * result
		 */
		ResultSet rs = (ResultSet) cs.getObject(1);

		/*
		 * while reading result from result set, set result information to
		 * object
		 */
		while (rs.next()) {

			classroom.setClass_id(rs.getString(1));
			classroom.setInstituition(instCtrl.getInstituitionById(
					rs.getString(2), conn));
			classroom.setName(rs.getString(3));
			classroom.setType(rs.getString(4));
		}

		return classroom;
	}

	public List<Classroom> getClassroomsByInstituitionId(
			String academic_instituition_id, Connection conn) throws Exception {
		// List to keep results retrieved from database
		List<Classroom> classrooms = new ArrayList<Classroom>();

		// define sql statement
		String sql = "{ call ? := getClassroomsByInstituitionId(?)}";

		// create prepared statement
		CallableStatement cs = conn.prepareCall(sql);

		// set prepared statement's parameters
		cs.registerOutParameter(1, OracleTypes.CURSOR);
		cs.setString(2, academic_instituition_id);

		cs.execute();
		/*
		 * execute the query on prepared statement and create result set to keep
		 * result
		 */
		ResultSet rs = (ResultSet) cs.getObject(1);

		/*
		 * while reading result from result set, set result information to
		 * object
		 */
		while (rs.next()) {
			// Object to keep results retrieved from database
			Classroom classroom = new Classroom();

			classroom.setClass_id(rs.getString(1));
			classroom.setInstituition(instCtrl.getInstituitionById(
					rs.getString(2), conn));
			classroom.setName(rs.getString(3));
			classroom.setType(rs.getString(4));

			// add object to List
			classrooms.add(classroom);
		}

		return classrooms;
	}

	public List<Classroom> getClassroomsByTeacherId(String teacher_id,
			Connection conn) throws Exception {
		// List to keep results retrieved from database
		List<Classroom> classrooms = new ArrayList<Classroom>();

		// define sql statement
		// String sql =
		// "SELECT class_id FROM teacher_class WHERE teacher_id = ? AND start_date is not null AND end_date is null";
		String sql = "{call ? := getClassroomsByTeacherId(?)}";

		// create prepared statement
		CallableStatement cs = conn.prepareCall(sql);

		// set prepared statement's parameters
		cs.registerOutParameter(1, OracleTypes.CURSOR);
		cs.setString(2, teacher_id);

		cs.execute();
		/*
		 * execute the query on prepared statement and create result set to keep
		 * result
		 */
		ResultSet rs = (ResultSet) cs.getObject(1);

		/*
		 * while reading result from result set, set result information to
		 * object
		 */
		while (rs.next()) {
			// Object to keep results retrieved from database
			Classroom classroom = getClassroomById(rs.getString(1), conn);

			// add object to List
			classrooms.add(classroom);
		}

		return classrooms;
	}

	public Classroom getClassroomByStudentId(String student_id, Connection conn)
			throws Exception {
		// Object to keep results retrieved from database
		Classroom classroom = new Classroom();

		// define sql statement
		String sql = "{call ? := getClassroomByStudentId(?)}";

		// create prepared statement
		CallableStatement cs = conn.prepareCall(sql);

		// set prepared statement's parameters
		cs.registerOutParameter(1, OracleTypes.VARCHAR);
		cs.setString(2, student_id);

		cs.execute();

		classroom = getClassroomById(cs.getString(1), conn);

		return classroom;
	}

	public Vector<Classroom> getClassBySchool(String teacher_id, Connection conn)
			throws Exception {
		Vector<Classroom> classes = new Vector<Classroom>();

		// prepare call store procedure
		String studentListSP = "{ CALL ? := classListBySchool(?) }";
		CallableStatement cs = conn.prepareCall(studentListSP);

		// register the type of the out parameter - an Oracle specific type
		cs.registerOutParameter(1, OracleTypes.CURSOR);
		cs.setString(2, teacher_id);

		// execute and retrieve the results
		cs.execute();
		ResultSet rs = (ResultSet) cs.getObject(1);

		while (rs.next()) {
			Classroom classroom = new Classroom();

			classroom.setClass_id(rs.getString(1));
			classroom.setName(rs.getString(2));
			classroom.setType(rs.getString(4));

			classes.add(classroom);
		}

		return classes;
	}

	public List<Classroom> getClassroomList(String classId, Connection conn)
			throws SQLException {

		List<Classroom> classes = new ArrayList<Classroom>();

		String sql = "{ call ? := getClass(?)}";

		CallableStatement cs = conn.prepareCall(sql);

		// register the type of the out parameter - an Oracle specific type
		cs.registerOutParameter(1, OracleTypes.CURSOR);
		cs.setString(2, classId);

		// execute and retrieve the results
		cs.execute();

		ResultSet rs = (ResultSet) cs.getObject(1);

		while (rs.next()) {

			Classroom classroom = new Classroom();

			classroom.setClass_id(rs.getString(1));
			classroom.setName(rs.getString(2));

			classes.add(classroom);
		}

		return classes;

	}

	public int insertStudentClass(String studentId, String classId,
			Connection conn) throws SQLException {

		String sql = "{ call ? := insertStudentClassProcess(?, ?)}";
		CallableStatement cs = conn.prepareCall(sql);

		cs.registerOutParameter(1, OracleTypes.INTEGER);
		cs.setString(2, studentId);
		cs.setString(3, classId);

		cs.execute();

		int status = cs.getInt(1);

		return status;

	}

	public int insertOriginalStudentClass(String classId, Connection conn)
			throws SQLException {

		String sql = "{ call ? := insertOriginalStudentClass(?)}";
		CallableStatement cs = conn.prepareCall(sql);

		cs.registerOutParameter(1, OracleTypes.INTEGER);
		cs.setString(2, classId);

		cs.execute();

		int status = cs.getInt(1);

		return status;

	}

}
