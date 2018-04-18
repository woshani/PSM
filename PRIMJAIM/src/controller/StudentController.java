package controller;

import java.io.InputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import facade.ControllerWrapper;
import oracle.jdbc.OracleTypes;
import oracle.jdbc.internal.OracleCallableStatement;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;
import oracle.sql.STRUCT;
import oracle.sql.StructDescriptor;
import bean.Classroom;
import bean.Guardian;
import bean.GuardianContact;
import bean.Race;
import bean.Religion;
import bean.Student;
import bean.TransferStudent;

public class StudentController {
	/*
	 * Declaration of variables
	 */
	private ClassroomController classCtrl = new ClassroomController();

	public int checkStudentId(String student_id, Connection conn)
			throws Exception {
		// define sql statement
		String sql = "{call ? := checkStudentId(?)}";

		// create prepared statement
		CallableStatement cs = conn.prepareCall(sql);

		// set prepared statement's parameters
		cs.registerOutParameter(1, OracleTypes.NUMBER);
		cs.setString(2, student_id);

		cs.execute();

		int status = -1;

		status = cs.getInt(1);

		return status;
	}

	public List<Student> getAllStudents(Connection conn) throws Exception {
		// List to keep results retrieved from database
		List<Student> students = new ArrayList<Student>();

		// define sql statement
		String sql = "{call ? := getAllStudents()}";

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
			Student student = new Student();
			Race race = new Race();
			Religion religion = new Religion();

			student.setStudent_id(rs.getString(1));
			student.setName(rs.getString(2));
			student.setIc_number(rs.getString(3));
			student.setBirth_cert(rs.getString(4));
			student.setGender(rs.getString(5));
			race.setRace(rs.getString(6));
			student.setRace(race);
			religion.setReligion(rs.getString(7));
			student.setReligion(religion);
			student.setDate_of_birth(rs.getString(8));
			student.setPlace_of_birth(rs.getString(9));
			student.setClassroom(classCtrl.getClassroomByStudentId(
					student.getStudent_id(), conn));

			// add object to List
			students.add(student);
		}

		return students;
	}

	public Student getStudentById(String student_id, Connection conn)
			throws Exception {
		// Object to keep results retrieved from database
		Student student = new Student();
		Race race = new Race();
		Religion religion = new Religion();

		// define sql statement
		String sql = "{call ? := getStudentById(?)}";

		// create prepared statement
		CallableStatement cs = conn.prepareCall(sql);

		// set prepared statement's parameters
		cs.registerOutParameter(1, OracleTypes.CURSOR);
		cs.setString(2, student_id);

		cs.executeQuery();

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
			student.setStudent_id(rs.getString(1));
			student.setName(rs.getString(2));
			student.setIc_number(rs.getString(3));
			student.setBirth_cert(rs.getString(4));
			if (rs.getString(5).equalsIgnoreCase("L")) {
				student.setGender("LELAKI");
			} else if (rs.getString(5).equalsIgnoreCase("P")) {
				student.setGender("PEREMPUAN");
			} else if (rs.getString(5) == null) {
				student.setGender("TIADA MAKLUMAT");
			}
			
			race.setId(rs.getInt(6));
			race.setRace(rs.getString(7));
			student.setRace(race);
			
			religion.setId(rs.getInt(8));
			religion.setReligion(rs.getString(9));
			student.setReligion(religion);
			
			student.setDate_of_birth(rs.getString(10));
			student.setPlace_of_birth(rs.getString(11));
			student.setClassroom(classCtrl.getClassroomByStudentId(
					student.getStudent_id(), conn));
		}

		return student;
	}

	public Student getStudentByIc(String student_ic, Connection conn)
			throws Exception {
		// Object to keep results retrieved from database
		Student student = new Student();
		Race race = new Race();
		Religion religion = new Religion();

		// define sql statement
		String sql = "{call ? := getStudentByIc(?)}";

		// create prepared statement
		CallableStatement cs = conn.prepareCall(sql);

		// set prepared statement's parameters
		cs.registerOutParameter(1, OracleTypes.CURSOR);
		cs.setString(2, student_ic);

		cs.executeQuery();

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
			student.setStudent_id(rs.getString(1));
			student.setName(rs.getString(2));
			student.setIc_number(rs.getString(3));
			student.setBirth_cert(rs.getString(4));
			if (rs.getString(5).equalsIgnoreCase("L")) {
				student.setGender("LELAKI");
			} else if (rs.getString(5).equalsIgnoreCase("P")) {
				student.setGender("PEREMPUAN");
			} else if (rs.getString(5) == null) {
				student.setGender("TIADA MAKLUMAT");
			}
			race.setId(rs.getInt(6));
			race.setRace(rs.getString(7));
			student.setRace(race);
			religion.setId(rs.getInt(8));
			religion.setReligion(rs.getString(9));
			student.setReligion(religion);
			student.setDate_of_birth(rs.getString(10));
			student.setPlace_of_birth(rs.getString(11));
			student.setClassroom(classCtrl.getClassroomByStudentId(
					student.getStudent_id(), conn));
			student.setPicture(rs.getBytes(12));
		}

		return student;
	}

	public Student getStudentByIdGuarView(String student_id, String class_id,
			Connection conn) throws Exception {
		// Object to keep results retrieved from database
		Student student = new Student();

		Race race = new Race();
		Religion religion = new Religion();

		// define sql statement?
		String sql = "{call ? := getStudentByIdGuarView(?,?)}";

		// create prepared statement
		CallableStatement cs = conn.prepareCall(sql);

		// set prepared statement's parameters
		cs.registerOutParameter(1, OracleTypes.CURSOR);
		cs.setString(2, student_id);
		cs.setString(3, class_id);

		cs.executeQuery();

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
			student.setStudent_id(rs.getString(1));
			student.setName(rs.getString(2));
			student.setIc_number(rs.getString(3));
			student.setBirth_cert(rs.getString(4));
			if (rs.getString(5).equalsIgnoreCase("L")) {
				student.setGender("LELAKI");
			} else if (rs.getString(5).equalsIgnoreCase("P")) {
				student.setGender("PEREMPUAN");
			} else if (rs.getString(5) == null) {
				student.setGender("TIADA MAKLUMAT");
			}
			race.setRace(rs.getString(6));
			student.setRace(race);
			religion.setReligion(rs.getString(7));
			student.setReligion(religion);
			student.setDate_of_birth(rs.getString(8));
			student.setPlace_of_birth(rs.getString(9));
			student.setClassroom(classCtrl.getClassroomById(class_id, conn));

		}

		return student;
	}

	public List<Student> getStudentsByClassId(String class_id, Connection conn)
			throws Exception {
		// List to keep results retrieved from database
		List<Student> students = new ArrayList<Student>();

		// define sql statement
		String sql = "{call ? := getStudentsByClassId(?)}";

		// create prepared statement
		CallableStatement cs = conn.prepareCall(sql);

		// set prepared statement's parameters
		cs.registerOutParameter(1, OracleTypes.CURSOR);
		cs.setString(2, class_id);

		cs.executeQuery();
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
			Student student = getStudentById(rs.getString(1), conn);

			// add object to List
			students.add(student);
		}

		return students;
	}

	public List<Student> getStudentsByGuardianId(String guardian_id,
			Connection conn) throws Exception {
		// List to keep results retrieved from database
		List<Student> students = new ArrayList<Student>();

		// define sql statement
		String sql = "{call ? := getStudentsByGuardianId(?)}";

		// create prepared statement
		CallableStatement cs = conn.prepareCall(sql);

		// set prepared statement's parameters
		cs.registerOutParameter(1, OracleTypes.CURSOR);
		cs.setString(2, guardian_id);

		cs.executeQuery();

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
			Student student = new Student();
			// Object to keep results retrieved from database
			student = getStudentByIdGuarView(rs.getString(1), rs.getString(2),
					conn);

			students.add(student);
		}

		return students;
	}

	public Vector<Student> getStudentByTeacher(String teacher_id,
			Connection conn) throws Exception {
		Vector<Student> students = new Vector<Student>();

		// prepare call store procedure
		String studentListSP = "{ CALL ? := studentList(?) }";
		CallableStatement cs = conn.prepareCall(studentListSP);

		// register the type of the out parameter - an Oracle specific type
		cs.registerOutParameter(1, OracleTypes.CURSOR);
		cs.setString(2, teacher_id);

		// execute and retrieve the results
		cs.execute();
		ResultSet rs = (ResultSet) cs.getObject(1);

		while (rs.next()) {
			Student student = new Student();
			student.setName(rs.getString(1));
			student.setStudent_id(rs.getString(2));
			student.setClassname(rs.getString(3));
			student.setClassType(rs.getString(4));

			students.add(student);
		}

		return students;
	}

	public int getStudentCount(String class_id, Connection conn)
			throws Exception {
		// define sql statement
		String sql = "{call ? := getStudentCount(?)}";

		// create prepared statement
		CallableStatement cs = conn.prepareCall(sql);

		// set prepared statement's parameters
		cs.registerOutParameter(1, OracleTypes.NUMBER);
		cs.setString(2, class_id);

		cs.execute();
		/*
		 * execute the query on prepared statement and create result set to keep
		 * result
		 */
		int status = cs.getInt(1);

		return status;
	}

	public List<Religion> getReligion(Connection conn) throws SQLException {

		List<Religion> religions = new ArrayList<Religion>();

		String sql = "{call ? := getReligion()}";

		CallableStatement cs = conn.prepareCall(sql);

		cs.registerOutParameter(1, OracleTypes.CURSOR);

		cs.executeQuery();

		ResultSet rs = (ResultSet) cs.getObject(1);

		while (rs.next()) {

			Religion religion = new Religion();

			religion.setId(rs.getInt(1));
			religion.setReligion(rs.getString(2));

			religions.add(religion);

		}

		return religions;

	}

	public List<Race> getRace(Connection conn) throws SQLException {

		List<Race> races = new ArrayList<Race>();

		String sql = "{call ? := getRace()}";

		CallableStatement cs = conn.prepareCall(sql);

		cs.registerOutParameter(1, OracleTypes.CURSOR);

		cs.executeQuery();

		ResultSet rs = (ResultSet) cs.getObject(1);

		while (rs.next()) {

			Race race = new Race();

			race.setId(rs.getInt(1));
			race.setRace(rs.getString(2));

			races.add(race);

		}

		return races;

	}

	public String insertStudent(Student student, String classId, Connection conn)
			throws SQLException {

		String sql = "{ call ? := addStudent(?, ?, ?, ?, ?, ?, ?, ?)}";

		CallableStatement cs = conn.prepareCall(sql);

		cs.registerOutParameter(1, OracleTypes.VARCHAR);
		cs.setString(2, student.getName());
		cs.setString(3, student.getIc_number());
		cs.setString(4, student.getBirth_cert());
		cs.setString(5, student.getPlace_of_birth());
		cs.setString(6, student.getGender());
		cs.setInt(7, student.getRace().getId());
		cs.setInt(8, student.getReligion().getId());
		cs.setString(9, classId);

		cs.execute();

		String studentId = cs.getString(1);

		return studentId;

	}

	public List<Student> getStudentsByInstituitionId(String instituitionId,
			Connection conn) throws Exception {

		List<Student> students = new ArrayList<Student>();

		Student student = new Student();

		String sql = "{ call ? := getStudentsByInstituitionId(?)}";

		CallableStatement cs = conn.prepareCall(sql);

		cs.registerOutParameter(1, OracleTypes.CURSOR);
		cs.setString(2, instituitionId);

		cs.execute();

		ResultSet rs = (ResultSet) cs.getObject(1);

		while (rs.next()) {

			student = getStudentById(rs.getString(1), conn);

			students.add(student);
		}

		rs.close();
		cs.close();

		return students;

	}

	public List<Student> getStudentsByInstituitionId2(String instituitionId,
			Connection conn) throws Exception {

		List<Student> students = new ArrayList<Student>();

		String sql = "{ call ? := getStudentsByInstituitionId2(?)}";

		CallableStatement cs = conn.prepareCall(sql);

		cs.registerOutParameter(1, OracleTypes.CURSOR);
		cs.setString(2, instituitionId);

		cs.execute();

		ResultSet rs = (ResultSet) cs.getObject(1);

		while (rs.next()) {

			Student student = new Student();

			student.setStudent_id(rs.getString(1));
			student.setName(rs.getString(2));
			student.setClassType(rs.getString(3));
			student.setClassname(rs.getString(4));

			students.add(student);
		}

		rs.close();
		cs.close();

		return students;

	}

	// Delete student in current class and add into transfer student process.
	public int studentTransfer(String studentId, Date transferDate,
			Connection conn) throws SQLException {

		String sql = "{ call ? := studentTransfer(?,?)}";

		CallableStatement cs = conn.prepareCall(sql);

		cs.registerOutParameter(1, OracleTypes.INTEGER);
		cs.setString(2, studentId);
		cs.setDate(3, (java.sql.Date) transferDate);

		cs.execute();

		int result = cs.getInt(1);

		cs.close();

		return result;

	}

	// List all student in transfer process
	public List<TransferStudent> allTransferStudent(Connection conn)
			throws Exception {

		List<TransferStudent> transferStudents = new ArrayList<TransferStudent>();

		TransferStudent transferStudent = null;

		String sql = "{ call ? := allStudentTransfer()}";

		CallableStatement cs = conn.prepareCall(sql);

		cs.registerOutParameter(1, OracleTypes.CURSOR);

		cs.execute();

		ResultSet rs = (ResultSet) cs.getObject(1);

		while (rs.next()) {

			transferStudent = new TransferStudent();

			transferStudent.setStudent(getStudentById(rs.getString(1), conn));
			transferStudent.setTransferDate(rs.getDate(2));

			transferStudents.add(transferStudent);
		}

		rs.close();
		cs.close();

		return transferStudents;

	}

	// Register student in new class
	public int studentTransferProcess(String studentId, Date transferDate,
			String classId, Connection conn) throws SQLException {

		// Delete student_id in transferstudent table
		// Add into student_class table assign new class.
		String sql = "{call studentRegisterClass(?,?,?)}";

		CallableStatement cs = conn.prepareCall(sql);

		cs.setString(1, studentId);
		cs.setDate(2, (java.sql.Date) transferDate);
		cs.setString(3, classId);

		cs.execute();

		// After student register/assign into new class,
		//
		sql = "{call ? := studentTransferProcess(?,?,?)}";

		cs = conn.prepareCall(sql);

		cs.registerOutParameter(1, OracleTypes.INTEGER);
		cs.setString(2, studentId);
		cs.setDate(3, (java.sql.Date) transferDate);
		cs.setString(4, classId);

		cs.execute();

		int result = cs.getInt(1);

		cs.close();

		return result;
	}

	public int updatestudent(Student student, Connection conn)
			throws SQLException, Exception {
		// define sql statement
		String sql = "{call ? := updatestudent(?,?,?,?,?,?,?,?,?)}";

		// create prepared statement
		CallableStatement cs = conn.prepareCall(sql);

		// set prepared statement's parameters
		cs.registerOutParameter(1, OracleTypes.INTEGER);
		cs.setString(2, student.getStudent_id());
		cs.setString(3, student.getName());
		cs.setString(4, student.getIc_number());
		cs.setString(5, student.getBirth_cert());
		cs.setString(6, student.getGender());
		cs.setInt(7, student.getRace().getId());
		cs.setInt(8, student.getReligion().getId());
		cs.setDate(9, student.getDob());
		cs.setString(10, student.getPlace_of_birth());

		cs.execute();

		int status = (Integer) cs.getObject(1);

		return status;
	}

	public int checkStudentIc(String student_ic, Connection conn)
			throws Exception {
		// define sql statement
		String sql = "{call ? := checkStuRegStatByIc(?)}";

		// create prepared statement
		CallableStatement cs = conn.prepareCall(sql);

		// set prepared statement's parameters
		cs.registerOutParameter(1, OracleTypes.NUMBER);
		cs.setString(2, student_ic);

		cs.execute();

		int status = -1;

		status = cs.getInt(1);

		return status;
	}

	public String insertRecordedStudent(Student student, String classId, InputStream picture, int pictureStatus, Connection conn)
			throws SQLException {

		String sql = "{ call ? := addRecordedStudent(?, ?, ?, ?, ?, ?, ?, ?,?,?,?)}";

		CallableStatement cs = conn.prepareCall(sql);

		cs.registerOutParameter(1, OracleTypes.VARCHAR);
		cs.setString(2, student.getStudent_id());
		cs.setString(3, student.getName());
		cs.setString(4, student.getIc_number());
		cs.setString(5, student.getBirth_cert());
		cs.setString(6, student.getPlace_of_birth());
		cs.setString(7, student.getGender());
		cs.setInt(8, student.getRace().getId());
		cs.setInt(9, student.getReligion().getId());
		cs.setString(10, classId);
		cs.setBlob(11, picture);
		cs.setInt(12, pictureStatus);

		cs.execute();

		String studentId = cs.getString(1);

		return studentId;

	}
	
	public String insertNewStudent(Student student, String classId, InputStream picture, int pictureStatus, Connection conn)
			throws SQLException {

		String sql = "{ call ? := addNewStudent(?, ?, ?, ?, ?, ?, ?, ?, ?,?,?)}";

		CallableStatement cs = conn.prepareCall(sql);

		cs.registerOutParameter(1, OracleTypes.VARCHAR);
		cs.setString(2, student.getName());
		cs.setString(3, student.getIc_number());
		cs.setDate(4, student.getDob());
		cs.setString(5, student.getBirth_cert());
		cs.setString(6, student.getPlace_of_birth());
		cs.setString(7, student.getGender());
		cs.setInt(8, student.getRace().getId());
		cs.setInt(9, student.getReligion().getId());
		cs.setString(10, classId);
		cs.setBlob(11, picture);
		cs.setInt(12, pictureStatus);

		cs.execute();

		String studentId = cs.getString(1);

		return studentId;

	}
	
	public String addNewStudentRegistration(Student student, String classId, InputStream picture, int pictureStatus, Connection conn)
			throws SQLException {

		String sql = "{ call ? := ADD_STUDENT_REGISTRATION(?, ?, ?, ?, ?, ?, ?, ?, ?,?,?)}";

		CallableStatement cs = conn.prepareCall(sql);

		cs.registerOutParameter(1, OracleTypes.VARCHAR);
		cs.setString(2, student.getName());
		cs.setString(3, student.getIc_number());
		cs.setDate(4, student.getDob());
		cs.setString(5, student.getBirth_cert());
		cs.setString(6, student.getPlace_of_birth());
		cs.setString(7, student.getGender());
		cs.setInt(8, student.getRace().getId());
		cs.setInt(9, student.getReligion().getId());
		cs.setString(10, classId);
		cs.setBlob(11, picture);
		cs.setInt(12, pictureStatus);

		cs.execute();

		String studentId = cs.getString(1);

		return studentId;

	}
	
	public String updateStudentRegistration(Student student, String classId, InputStream picture, int pictureStatus, Connection conn)
			throws SQLException {

		String sql = "{ call ? := UPDATE_STUDENT_REGISTRATION(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";

		CallableStatement cs = conn.prepareCall(sql);

		cs.registerOutParameter(1, OracleTypes.VARCHAR);
		cs.setString(2, student.getStudent_id());
		cs.setString(3, student.getName());
		cs.setString(4, student.getIc_number());
		cs.setDate(5, student.getDob());
		cs.setString(6, student.getBirth_cert());
		cs.setString(7, student.getPlace_of_birth());
		cs.setString(8, student.getGender());
		cs.setInt(9, student.getRace().getId());
		cs.setInt(10, student.getReligion().getId());
		cs.setString(11, classId);
		cs.setBlob(12, picture);
		cs.setInt(13, pictureStatus);

		cs.execute();

		String studentId = cs.getString(1);

		return studentId;

	}
	
	public String addNewStudentWithGuardianRegistration(Student student, Guardian guardian, String classId, InputStream picture, int pictureStatus, Connection conn)
			throws SQLException {

		// 23 parameters (1+11+11)
		String sql = "{ call ? := ADDSTUWITHGUAREGISTRATION(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, "
				+ " ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";

		CallableStatement cs = conn.prepareCall(sql);

		cs.registerOutParameter(1, OracleTypes.VARCHAR);
		cs.setString(2, student.getName());
		cs.setString(3, student.getIc_number());
		cs.setDate(4, student.getDob());
		cs.setString(5, student.getBirth_cert());
		cs.setString(6, student.getPlace_of_birth());
		cs.setString(7, student.getGender());
		cs.setInt(8, student.getRace().getId());
		cs.setInt(9, student.getReligion().getId());
		cs.setString(10, classId);
		cs.setBlob(11, picture);
		cs.setInt(12, pictureStatus);
		
		cs.setString(13, guardian.getGuardian_id());
		cs.setString(14, guardian.getName());
		cs.setString(15, guardian.getIc());
		cs.setString(16, guardian.getGender());
		cs.setString(17, guardian.getOccupation());
		cs.setString(18, guardian.getDependent2());
		cs.setString(19, guardian.getAddress());
		cs.setString(20, guardian.getCity());
		cs.setString(21, guardian.getPostcode());
		cs.setString(22, guardian.getState());
		
		cs.setString(23, guardian.getRelationship());

		cs.execute();

		String studentId = cs.getString(1);

		return studentId;

	}
	
	public String updateStudentWithGuardianRegistration(Student student, Guardian guardian, String classId, InputStream picture, int pictureStatus, Connection conn)
			throws SQLException {

		// 24 parameters (1+12+11)
		String sql = "{ call ? := UPDATESTUWITHGUAREGISTRATION(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, "
				+ "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";

		CallableStatement cs = conn.prepareCall(sql);

		cs.registerOutParameter(1, OracleTypes.VARCHAR);
		cs.setString(2, student.getStudent_id());
		cs.setString(3, student.getName());
		cs.setString(4, student.getIc_number());
		cs.setDate(5, student.getDob());
		cs.setString(6, student.getBirth_cert());
		cs.setString(7, student.getPlace_of_birth());
		cs.setString(8, student.getGender());
		cs.setInt(9, student.getRace().getId());
		cs.setInt(10, student.getReligion().getId());
		cs.setString(11, classId);
		cs.setBlob(12, picture);
		cs.setInt(13, pictureStatus);
		
		cs.setString(14, guardian.getGuardian_id());
		cs.setString(15, guardian.getName());
		cs.setString(16, guardian.getIc());
		cs.setString(17, guardian.getGender());
		cs.setString(18, guardian.getOccupation());
		cs.setString(19, guardian.getDependent2());
		cs.setString(20, guardian.getAddress());
		cs.setString(21, guardian.getCity());
		cs.setString(22, guardian.getPostcode());
		cs.setString(23, guardian.getState());
		
		cs.setString(24, guardian.getRelationship());

		cs.execute();

		String studentId = cs.getString(1);

		return studentId;

	}
	
	public String addStudentForRegistration(Student student, Guardian guardian, String classId, InputStream picture, int pictureStatus, ArrayList<GuardianContact> contacts, Connection conn)
			throws SQLException {

		// initialize object for student
		Object[] studentObj = {student.getStudent_id(), student.getName(), student.getIc_number(), student.getDob(), student.getBirth_cert(), 
				student.getPlace_of_birth(), student.getGender(), student.getRace().getId(), student.getReligion().getId()};
		
		// describe and link JAVA Object and Oracle type of Object for guardian
		StructDescriptor studentStruct = StructDescriptor.createDescriptor("REGSTUDENTTYPE",conn);
		STRUCT inputStudent = new STRUCT(studentStruct, conn, studentObj);
		
		//initialize object for guardian
		Object[] guardianObj = {guardian.getGuardian_id(), guardian.getName(),guardian.getIc(), guardian.getGender(),guardian.getOccupation(),
				guardian.getDependent(), guardian.getAddress(), guardian.getCity(), guardian.getPostcode(), guardian.getState()};
		
		// describe and link JAVA Object and Oracle type of Object for guardian
		StructDescriptor guardianStruct = StructDescriptor.createDescriptor("REGGUARDIANTYPE",conn);
		STRUCT inputGuardian = new STRUCT(guardianStruct, conn, guardianObj);
		
		//
		//initialize object for guardian contacts
		Object[] contactsObj = new Object[contacts.size()];
		
		for (int i=0;i<contacts.size();i++){
			Object[] contact = {contacts.get(i).getGcontact_id(), contacts.get(i).getGuardian().getGuardian_id(),
					contacts.get(i).getPhone_number(), contacts.get(i).getProvider(), contacts.get(i).getSubscription()};
			contactsObj[i] = contact;
		}
		
		// describe and link JAVA Array and Oracle type of Array for guardian contact
		ArrayDescriptor contactArray = ArrayDescriptor.createDescriptor("REGGUARDIANCONTACTTYPES", conn);
		ARRAY inputGuardianContacts = new ARRAY(contactArray, conn, contactsObj);
		
		
		//test print
		/*for(int i=0;i<contactsObj.length;i++){
			Object[] a = (Object[])contactsObj[i];
			
			System.out.println(a[0]+" : "+a[1]+" : "+a[2]+" : "+a[3]+" : "+a[4]);
		}*/
		
		// 7 parameters 
		String sql = "{ call ? := ADDSTUDENTFORREGISTRATION(?,?,?,?,?,?,?)}";
		
		
		OracleCallableStatement cs = (OracleCallableStatement)conn.prepareCall(sql);
		
		cs.registerOutParameter(1, OracleTypes.VARCHAR);
		cs.setObject(2, inputStudent);
		cs.setString(3, classId);
		cs.setBlob(4, picture);
		cs.setInt(5, pictureStatus);
		cs.setObject(6, inputGuardian);
		cs.setString(7, guardian.getRelationship());
		cs.setARRAY(8, inputGuardianContacts);
		
		cs.execute();

		String studentId = cs.getString(1);

		return studentId;
		
	}

}
