package controller;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.apache.tomcat.jni.Stdlib;

import facade.ControllerWrapper;
import bean.AnalysisYearAttendance;
import bean.AttendanceByRace;
import bean.AttendanceStatistics;
import bean.AuditDailyAttendance;
import bean.Classroom;
import bean.DailyAttendance;
import bean.DateAbsent;
import bean.FinishTime;
import bean.Instituition;
import bean.Monitor;
import bean.PpdAttendance;
import bean.Report;
import bean.SchoolAttendance;
import bean.Student;
import bean.StudentClass;
import bean.Warning;
import bean.WarningHistory;
import oracle.jdbc.OracleTypes;

public class AttendanceController {
	
	private StudentController stdCtrl = new StudentController();
	private ClassroomController classCtrl = new ClassroomController();
	private InstituitionController instCtrl = new InstituitionController();
	
	public int insertDailyAttendance(String classId, Connection conn)
			throws Exception {

		// prepare call store procedure
		CallableStatement cs = conn
				.prepareCall("{ call student_daily_attendance(?,?)}");

		// register the type of the out parameter - an Oracle specific type
		cs.setString(1, classId);
		cs.registerOutParameter(2, OracleTypes.INTEGER);

		// execute and retrieve the results
		cs.execute();

		int status = cs.getInt(2);

		return status;

	}

	public int insertDailyAttendanceAbsent(String studentId, String classId,
			Connection conn) throws Exception {

		// prepare call store procedure
		CallableStatement cs = conn
				.prepareCall("{ call dailyAttendanceProcess(?,?,?)}");

		// register the type of the out parameter - an Oracle specific type
		cs.setString(1, studentId);
		cs.setString(2, classId);
		cs.registerOutParameter(3, Types.INTEGER);

		// execute and retrieve the results
		cs.execute();

		int status = cs.getInt(3);

		// Close connection
		cs.close();

		conn.close();

		return status;

	}

	public int insertFinishTime(String classId, Connection conn)
			throws Exception {

		// prepare call store procedure
		CallableStatement cs = conn
				.prepareCall("{ call updateFinishTime(?,?)}");

		// register the type of the out parameter - an Oracle specific type
		cs.setString(1, classId);
		cs.registerOutParameter(2, Types.INTEGER);

		// execute and retrieve the results
		cs.execute();

		int status = cs.getInt(2);

		// Close connection
		cs.close();

		conn.close();

		return status;

	}

	public String WarningList(String studentId, Connection conn)
			throws Exception {

		String description = null;

		// prepare call store procedure
		String amaran_SP = "{ CALL ? := amaran(?) }";
		CallableStatement cs = conn.prepareCall(amaran_SP);

		cs.registerOutParameter(1, Types.VARCHAR);
		cs.setString(2, studentId);

		cs.execute();

		description = cs.getString(1);

		return description;
	}
	
	public WarningHistory WarningList2(String studentId, Connection conn)
			throws Exception {

		WarningHistory warningHistory = new WarningHistory();
		Warning warning = new Warning();

		// prepare call store procedure
		String amaran2_SP = "{ CALL ? := amaran2(?) }";
		CallableStatement cs = conn.prepareCall(amaran2_SP);

		cs.registerOutParameter(1, OracleTypes.CURSOR);
		cs.setString(2, studentId);

		cs.execute();

		ResultSet rs = (ResultSet) cs.getObject(1);

		while (rs.next()) {
			
			warning.setDescription(rs.getString(1));
			warning.setWarningType(rs.getNString(2));
			warning.setWarningLevel(rs.getInt(3));
			
			warningHistory.setWhId(rs.getInt(4));
			warningHistory.setWarningDate(rs.getDate(5));
			warningHistory.setWarning(warning);
		}

		return warningHistory;
	}

	public Vector<DailyAttendance> MonthlyAttendanceList(String month,
			String year, String classId, Connection conn) throws Exception {

		Vector<DailyAttendance> attendances = new Vector<DailyAttendance>();
		DailyAttendance attendance = null;
		Student student = null;

		// prepare call store procedure
		String monthlyAttendanceList_SP = "{ CALL ? := monthlyAttendanceList(?,?,?) }";
		CallableStatement cs = conn.prepareCall(monthlyAttendanceList_SP);

		// register the type of the out parameter - an Oracle specific type
		cs.registerOutParameter(1, OracleTypes.CURSOR);

		cs.setString(2, month);
		cs.setString(3, year);
		cs.setString(4, classId);

		// execute and retrieve the results
		cs.execute();
		ResultSet rs = (ResultSet) cs.getObject(1);

		while (rs.next()) {

			attendance = new DailyAttendance();
			student = new Student();
			student = stdCtrl.getStudentById(rs.getString(1), conn);
			attendance.setStudent(student);
			attendance.setD01(rs.getString(2));
			attendance.setD02(rs.getString(3));
			attendance.setD03(rs.getString(4));
			attendance.setD04(rs.getString(5));
			attendance.setD05(rs.getString(6));
			attendance.setD06(rs.getString(7));
			attendance.setD07(rs.getString(8));
			attendance.setD08(rs.getString(9));
			attendance.setD09(rs.getString(10));
			attendance.setD10(rs.getString(11));
			attendance.setD11(rs.getString(12));
			attendance.setD12(rs.getString(13));
			attendance.setD13(rs.getString(14));
			attendance.setD14(rs.getString(15));
			attendance.setD15(rs.getString(16));
			attendance.setD16(rs.getString(17));
			attendance.setD17(rs.getString(18));
			attendance.setD18(rs.getString(19));
			attendance.setD19(rs.getString(20));
			attendance.setD20(rs.getString(21));
			attendance.setD21(rs.getString(22));
			attendance.setD22(rs.getString(23));
			attendance.setD23(rs.getString(24));
			attendance.setD24(rs.getString(25));
			attendance.setD25(rs.getString(26));
			attendance.setD26(rs.getString(27));
			attendance.setD27(rs.getString(28));
			attendance.setD28(rs.getString(29));
			attendance.setD29(rs.getString(30));
			attendance.setD30(rs.getString(31));
			attendance.setD31(rs.getString(32));
			attendance.setThisMonth(rs.getInt(33));
			attendance.setPreviousMonth(rs.getInt(34));
			attendance.setEndMonth(rs.getInt(35));

			attendances.add(attendance);
		}

		return attendances;

	}
	
	public Vector<DailyAttendance> MonthlyTransferAttendanceList(String month,
			String year, String classId, Connection conn) throws Exception {

		Vector<DailyAttendance> attendances = new Vector<DailyAttendance>();
		DailyAttendance attendance = null;
		Student student = null;

		// prepare call store procedure
		String monthlyAttendanceList_SP = "{ CALL ? := monthlyTransferAttendanceList(?,?,?) }";
		CallableStatement cs = conn.prepareCall(monthlyAttendanceList_SP);

		// register the type of the out parameter - an Oracle specific type
		cs.registerOutParameter(1, OracleTypes.CURSOR);

		cs.setString(2, month);
		cs.setString(3, year);
		cs.setString(4, classId);

		// execute and retrieve the results
		cs.execute();
		ResultSet rs = (ResultSet) cs.getObject(1);

		while (rs.next()) {

			attendance = new DailyAttendance();
			student = new Student();
			student = stdCtrl.getStudentById(rs.getString(1), conn);
			attendance.setStudent(student);
			attendance.setD01(rs.getString(2));
			attendance.setD02(rs.getString(3));
			attendance.setD03(rs.getString(4));
			attendance.setD04(rs.getString(5));
			attendance.setD05(rs.getString(6));
			attendance.setD06(rs.getString(7));
			attendance.setD07(rs.getString(8));
			attendance.setD08(rs.getString(9));
			attendance.setD09(rs.getString(10));
			attendance.setD10(rs.getString(11));
			attendance.setD11(rs.getString(12));
			attendance.setD12(rs.getString(13));
			attendance.setD13(rs.getString(14));
			attendance.setD14(rs.getString(15));
			attendance.setD15(rs.getString(16));
			attendance.setD16(rs.getString(17));
			attendance.setD17(rs.getString(18));
			attendance.setD18(rs.getString(19));
			attendance.setD19(rs.getString(20));
			attendance.setD20(rs.getString(21));
			attendance.setD21(rs.getString(22));
			attendance.setD22(rs.getString(23));
			attendance.setD23(rs.getString(24));
			attendance.setD24(rs.getString(25));
			attendance.setD25(rs.getString(26));
			attendance.setD26(rs.getString(27));
			attendance.setD27(rs.getString(28));
			attendance.setD28(rs.getString(29));
			attendance.setD29(rs.getString(30));
			attendance.setD30(rs.getString(31));
			attendance.setD31(rs.getString(32));
			attendance.setThisMonth(rs.getInt(33));
			attendance.setPreviousMonth(rs.getInt(34));
			attendance.setEndMonth(rs.getInt(35));

			attendances.add(attendance);
		}

		return attendances;

	}

	public List<Report> MonthlyReportList(String month, String year,
			String classId, Connection conn) throws Exception {

		List<Report> reports = new ArrayList<Report>();

		String sql = "{ call ? := monthlyReportList(?, ?, ?) }";

		CallableStatement cs = conn.prepareCall(sql);

		cs.registerOutParameter(1, OracleTypes.CURSOR);
		cs.setString(2, month);
		cs.setString(3, year);
		cs.setString(4, classId);

		cs.execute();
		ResultSet rs = (ResultSet) cs.getObject(1);

		while (rs.next()) {

			Report report = new Report();

			if (rs.getString(1).equalsIgnoreCase("TOTAL ATTEND")) {
				report.setItem("JUMLAH YANG HADIR");
			} else if (rs.getString(1).equalsIgnoreCase("TOTAL ABSENT")) {
				report.setItem("JUMLAH YANG TIDAK HADIR");
			} else if (rs.getString(1).equalsIgnoreCase("TOTAL STUDENT")) {
				report.setItem("JUMLAH PELAJAR");
			}

			report.setD01(rs.getInt(2));
			report.setD02(rs.getInt(3));
			report.setD03(rs.getInt(4));
			report.setD04(rs.getInt(5));
			report.setD05(rs.getInt(6));
			report.setD06(rs.getInt(7));
			report.setD07(rs.getInt(8));
			report.setD08(rs.getInt(9));
			report.setD09(rs.getInt(10));
			report.setD10(rs.getInt(11));
			report.setD11(rs.getInt(12));
			report.setD12(rs.getInt(13));
			report.setD13(rs.getInt(14));
			report.setD14(rs.getInt(15));
			report.setD15(rs.getInt(16));
			report.setD16(rs.getInt(17));
			report.setD17(rs.getInt(18));
			report.setD18(rs.getInt(19));
			report.setD19(rs.getInt(20));
			report.setD20(rs.getInt(21));
			report.setD21(rs.getInt(22));
			report.setD22(rs.getInt(23));
			report.setD23(rs.getInt(24));
			report.setD24(rs.getInt(25));
			report.setD25(rs.getInt(26));
			report.setD26(rs.getInt(27));
			report.setD27(rs.getInt(28));
			report.setD28(rs.getInt(29));
			report.setD29(rs.getInt(30));
			report.setD30(rs.getInt(31));
			report.setD31(rs.getInt(32));
			report.setThisMonth(rs.getInt(33));
			report.setPreviousMonth(rs.getInt(34));
			report.setEndMonth(rs.getInt(35));

			reports.add(report);
		}

		return reports;
	}

	public Monitor studentWarning(String studentId, String whId, Connection conn)
			throws Exception {
		Classroom classroom = null;
		DateAbsent dateAbsent = null;
		Monitor monitor = null;
		Student student = null;
		Warning warning = null;
		ClassroomController classCtrl = new ClassroomController();

		// prepare call store procedure
		CallableStatement cs = conn.prepareCall("{ call studentWarning(?,?,?)}");

		cs.registerOutParameter(3, OracleTypes.CURSOR);
		cs.setString(1, studentId);
		cs.setString(2, whId);
		
		// execute and retrieve the results
		cs.execute();
		ResultSet rs = (ResultSet) cs.getObject(3);

		while (rs.next()) {
			monitor = new Monitor();

			classroom = new Classroom();
			classroom = classCtrl.getClassroomById(rs.getString(5), conn);

			student = new Student();
			student.setName(rs.getString(1));
			student.setClassroom(classroom);

			monitor.setStudent(student);

			warning = new Warning();
			warning.setDescription(rs.getString(2));
			warning.setWarningType(rs.getString(3));
			warning.setAbsentDay(rs.getInt(4));

			monitor.setWarning(warning);

			dateAbsent = new DateAbsent();
			dateAbsent.setFirstWarning(rs.getString(6));

			monitor.setDateAbsent(dateAbsent);

		}

		conn.close();

		return monitor;
	}	

	public Vector<FinishTime> StatusKedatangan(int status, String instituitionId, Connection conn)
			throws Exception {

		Vector<FinishTime> finishTimes = new Vector<FinishTime>();
		FinishTime finishTime = null;
		String sql = null;
		if (status == 1) {
			// prepare call store procedure
			sql = "{ call ? := AttendanceStatusDoneFunc(?)}";
		}
		if (status == 2) {
			sql = "{ call ? := AttendanceStatusNotDoneFunc(?)}";
		}
		CallableStatement cs = conn.prepareCall(sql);

		// register the type of the out parameter - an Oracle specific type
		cs.registerOutParameter(1, OracleTypes.CURSOR);
		cs.setString(2, instituitionId);

		// execute and retrieve the results
		cs.execute();
		ResultSet rs = (ResultSet) cs.getObject(1);

		while (rs.next()) {
			finishTime = new FinishTime();
			finishTime.setClassroom(classCtrl.getClassroomById(rs.getString(1), conn));
			if (status == 1) {
				finishTime.setStatus(rs.getString(2));
			}
			
			finishTimes.add(finishTime);
		}

		return finishTimes;
	}
	
	public Vector<FinishTime> StatusKedatanganRecord(int status, Date date, String instituitionId, Connection conn) throws Exception {

		Vector<FinishTime> finishTimes = new Vector<FinishTime>();
		FinishTime finishTime = null;
		String sql = null;
		if (status == 1) {
			// prepare call store procedure
			sql = "{ call ? := AttendanceStatusDoneRecFunc(?,?)}";
		}
		if (status == 2) {
			sql = "{ call ? := AttendanceStatusNotDoneRecFunc(?,?)}";
		}
		CallableStatement cs = conn.prepareCall(sql);

		// register the type of the out parameter - an Oracle specific type
		cs.registerOutParameter(1, OracleTypes.CURSOR);
		cs.setDate(2,date);
		cs.setString(3, instituitionId);

		// execute and retrieve the results
		cs.execute();
		ResultSet rs = (ResultSet) cs.getObject(1);

		while (rs.next()) {
			finishTime = new FinishTime();
			finishTime.setClassroom(classCtrl.getClassroomById(rs.getString(1), conn));
			if (status == 1) {
				finishTime.setStatus(rs.getString(2));
			}

			finishTimes.add(finishTime);
		}

		return finishTimes;
	}

	public int totalAttendMale(String classId, Connection conn) throws Exception {


		String sql = "{ call ? := jumlahKedatanganHadirLaki(?) }";
		CallableStatement cs = conn.prepareCall(sql);

		// register the type of the out parameter - an Oracle specific type
		cs.registerOutParameter(1, OracleTypes.NUMBER);
		cs.setString(2, classId);

		// execute and retrieve the results
		cs.execute();

		int jumlah = cs.getInt(1);

		return jumlah;
	}

	public int totalAttendFemale(String classId, Connection conn) throws Exception {


		String sql = "{ call ? := jumlahKedatanganHadirPerempuan(?) }";
		CallableStatement cs = conn.prepareCall(sql);

		// register the type of the out parameter - an Oracle specific type
		cs.registerOutParameter(1, OracleTypes.NUMBER);
		cs.setString(2, classId);

		// execute and retrieve the results
		cs.execute();

		int jumlah = cs.getInt(1);

		return jumlah;
	}
	
	public int totalAbsentMale(String classId, Connection conn) throws Exception {

		String sql = "{ call ? := jumlahKedatanganTidakHadirLaki(?) }";
		CallableStatement cs = conn.prepareCall(sql);

		// register the type of the out parameter - an Oracle specific type
		cs.registerOutParameter(1, OracleTypes.NUMBER);
		cs.setString(2, classId);

		// execute and retrieve the results
		cs.execute();

		int jumlah = cs.getInt(1);
		
		return jumlah;
	}
	
	public int totalAbsentFemale(String classId, Connection conn) throws Exception {

		String sql = "{ call ? := jumlahKedatanganTidakHadirP(?) }";
		CallableStatement cs = conn.prepareCall(sql);

		// register the type of the out parameter - an Oracle specific type
		cs.registerOutParameter(1, OracleTypes.NUMBER);
		cs.setString(2, classId);

		// execute and retrieve the results
		cs.execute();
		
		int jumlah = cs.getInt(1);

		return jumlah;
	}

	public int CheckFinishTime(String classId, Connection conn)
			throws Exception {

		String sql = "{ call ? := verify_time(?) }";
		CallableStatement cs = conn.prepareCall(sql);

		cs.registerOutParameter(1, Types.INTEGER);
		cs.setString(2, classId);

		cs.execute();

		int status = cs.getInt(1);

		return status;

	}

	public AnalysisYearAttendance getAnalysisYearAttendance(String month, String year,
			String classId, Connection conn) throws SQLException {

		AnalysisYearAttendance analysis = new AnalysisYearAttendance();
		Classroom classroom = new Classroom();
		
		String sql = "{call ? := getAnalysisYearAttendance(?,?,?)}";

		CallableStatement cs = conn.prepareCall(sql);

		cs.registerOutParameter(1, OracleTypes.CURSOR);
		cs.setString(2, classId);
		cs.setString(3, month);
		cs.setString(4, year);
		
		cs.executeQuery();
		
		ResultSet rs = (ResultSet) cs.getObject(1);
		
		while(rs.next()){
			
			analysis.setAnalysis_year_attendance_id(rs.getInt(1));
			classroom.setClass_id(rs.getString(2));
			analysis.setClassroom(classroom);
			analysis.setMonth_year(rs.getString(3));
			analysis.setNo_of_student(rs.getInt(4));
			analysis.setAttendance(rs.getString(5));
			analysis.setAttendance_percentage(rs.getString(6));
			analysis.setTotal_day(rs.getInt(7));
			
		}

		return analysis;
	}

	public List<DailyAttendance> getAttendanceByDay(String month, String classId, String day, Connection conn) throws Exception{
		
		DailyAttendance attendance = null;
		List<DailyAttendance> attendances = new ArrayList<DailyAttendance>();
		Student student = null;
		
		String sql = "{ call ? := getAttendanceByDay(?,?,?) }";
		
		CallableStatement cs = conn.prepareCall(sql);
		
		cs.registerOutParameter(1, OracleTypes.CURSOR);
		cs.setString(2, day);
		cs.setString(3, month);
		cs.setString(4, classId);
		
		cs.execute();
		
		ResultSet rs = (ResultSet) cs.getObject(1);
		
		while(rs.next()){
			
			student = new Student();
			attendance = new DailyAttendance();
			student =  stdCtrl.getStudentById(rs.getString(1), conn);
			attendance.setStudent(student);
			
			if(rs.getString(2) == null){
				attendance.setD01("-");
			}else{
				attendance.setD01(rs.getString(2));
			}
			
			attendances.add(attendance);
			
		}
		
		return attendances;
	}
	
	public String convertSymbol(String symbol){
		
		String meaning = null;
		
		if(symbol.equalsIgnoreCase("/")){
			meaning = "Hadir";
		}
		
		else if(symbol.equalsIgnoreCase("O")){
			meaning = "Tidak Hadir";
		}
		
		else if(symbol.equalsIgnoreCase("AK")){
			meaning = "Ancaman Keselamatan";
		}
		
		else if(symbol.equalsIgnoreCase("BA")){
			meaning = "Bencana Alam";
		}
		
		else if(symbol.equalsIgnoreCase("S")){
			meaning = "Cuti Sakit";
		}
		
		else if(symbol.equalsIgnoreCase("G")){
			meaning = "Digantung Sekolah";
		}
		
		else if(symbol.equalsIgnoreCase("LL")){
			meaning = "Lain-lain sebab";
		}
		
		else if(symbol.equalsIgnoreCase("L")){
			meaning = "Lewat";
		}
		
		else if(symbol.equalsIgnoreCase("MP")){
			meaning = "Masalah Peribadi";
		}
		
		else if(symbol.equalsIgnoreCase("UK")){
			meaning = "Urusan Keluarga";
		}
		
		else{
			meaning = "Tiada Kedatangan";
		}
		

		return meaning;
	}
	
	public int updateAttendance(String studentId, String classId, String day, String month, String year, String statusBefore, String statusAfter, Connection conn) throws SQLException{
		
		int status = 0;
		
		String sql = "{ call ? := update_attendance(?, ?, ?, ?, ?, ?, ?)}";
		
		CallableStatement cs = conn.prepareCall(sql);
		
		cs.registerOutParameter(1, OracleTypes.INTEGER);
		cs.setString(2, studentId);
		cs.setString(3, classId);
		cs.setString(4, day);
		cs.setString(5, month);
		cs.setString(6, year);
		cs.setString(7, statusBefore);
		cs.setString(8, statusAfter);
		
		cs.execute();
		
		status = cs.getInt(1);
		
		return status;
	}
	
	public DailyAttendance getAttendanceMonthlyByStudent(String studentId, String classId, String month, String year, Connection conn) throws Exception{
		
		DailyAttendance attendance = null;
		Student student = null;
		
		String sql = "{call ? := attendanceByStudent(?,?,?,?)}";
		
		CallableStatement cs = conn.prepareCall(sql);
		
		cs.registerOutParameter(1, OracleTypes.CURSOR);

		cs.setString(2, studentId);
		cs.setString(3, classId);
		cs.setString(4, month);
		cs.setString(5, year);
		
		cs.execute();
		
		ResultSet rs = (ResultSet) cs.getObject(1);
		
		while(rs.next()){
			
			attendance = new DailyAttendance();
			student = new Student();
			student = stdCtrl.getStudentById(rs.getString(1), conn);
			attendance.setStudent(student);
			attendance.setD01(rs.getString(2));
			attendance.setD02(rs.getString(3));
			attendance.setD03(rs.getString(4));
			attendance.setD04(rs.getString(5));
			attendance.setD05(rs.getString(6));
			attendance.setD06(rs.getString(7));
			attendance.setD07(rs.getString(8));
			attendance.setD08(rs.getString(9));
			attendance.setD09(rs.getString(10));
			attendance.setD10(rs.getString(11));
			attendance.setD11(rs.getString(12));
			attendance.setD12(rs.getString(13));
			attendance.setD13(rs.getString(14));
			attendance.setD14(rs.getString(15));
			attendance.setD15(rs.getString(16));
			attendance.setD16(rs.getString(17));
			attendance.setD17(rs.getString(18));
			attendance.setD18(rs.getString(19));
			attendance.setD19(rs.getString(20));
			attendance.setD20(rs.getString(21));
			attendance.setD21(rs.getString(22));
			attendance.setD22(rs.getString(23));
			attendance.setD23(rs.getString(24));
			attendance.setD24(rs.getString(25));
			attendance.setD25(rs.getString(26));
			attendance.setD26(rs.getString(27));
			attendance.setD27(rs.getString(28));
			attendance.setD28(rs.getString(29));
			attendance.setD29(rs.getString(30));
			attendance.setD30(rs.getString(31));
			attendance.setD31(rs.getString(32));
		}
		
		return attendance;
	}
	
	public DailyAttendance getAttendanceMonthlyByDay(String studentId, String day , String month, String year, Connection conn) throws Exception{
		
		DailyAttendance attendance = null;
		Student student = null;
		
		String sql = "{ call ? := getAttendanceStudentByDay(?,?,?,?) }";
		
		CallableStatement cs = conn.prepareCall(sql);
		
		cs.registerOutParameter(1, OracleTypes.CURSOR);
		cs.setString(2, day);
		cs.setString(3, month);
		cs.setString(4, year);
		cs.setString(5, studentId);
		
		cs.execute();
		
		ResultSet rs = (ResultSet) cs.getObject(1);
		
		while(rs.next()){
			
			student = new Student();
			attendance = new DailyAttendance();
			student =  stdCtrl.getStudentById(rs.getString(1), conn);
			attendance.setStudent(student);
			
			if(rs.getString(2) == null){
				attendance.setD01("-");
			}else{
				attendance.setD01(rs.getString(2));
			}
			
		}
		
		return attendance;
	}
	
	/* public List<AuditDailyAttendance> getAuditAttendance(String classId, String month, Connection conn) throws Exception
	{
		
		List<AuditDailyAttendance> auditAttendances = new ArrayList<AuditDailyAttendance>();
		AuditDailyAttendance auditAttendance = null;
		StudentClass studentClass = null;
		Student student = null;
		
		String sql = "{ call ? := attendancelog(?,?) }";
		
		CallableStatement cs = conn.prepareCall(sql);
		
		cs.registerOutParameter(1, OracleTypes.CURSOR);
		cs.setString(2, classId);
		cs.setString(3, month);
		
		cs.execute();
		
		ResultSet rs = (ResultSet) cs.getObject(1);
		
		while(rs.next()){
			
			auditAttendance = new AuditDailyAttendance();
			studentClass = new StudentClass();
			student = new Student();
			
			student =  stdCtrl.getStudentById(rs.getString(1), conn);
			
			studentClass.setStudent(student);
			auditAttendance.setStudentClass(studentClass);
			auditAttendance.setAuditDate(rs.getString(2));
			auditAttendance.setAttendanceDate(rs.getString(3));
			auditAttendance.setStatusBefore(rs.getString(4));
			auditAttendance.setStatusAfter(rs.getString(5));
			
			auditAttendances.add(auditAttendance);
			
		}
		
		return auditAttendances;
	}*/
	
	public List<AuditDailyAttendance> getAuditAttendance(String classId, String month, String year, Connection conn) throws Exception
	{
		
		List<AuditDailyAttendance> auditAttendances = new ArrayList<AuditDailyAttendance>();
		AuditDailyAttendance auditAttendance = null;
		StudentClass studentClass = null;
		Student student = null;
		
		String sql = "{ call ? := attendancelog(?,?,?) }";
		
		CallableStatement cs = conn.prepareCall(sql);
		
		cs.registerOutParameter(1, OracleTypes.CURSOR);
		cs.setString(2, classId);
		cs.setString(3, month);
		cs.setString(4, year);
		
		cs.execute();
		
		ResultSet rs = (ResultSet) cs.getObject(1);
		
		while(rs.next()){
			
			auditAttendance = new AuditDailyAttendance();
			studentClass = new StudentClass();
			student = new Student();
			
			student =  stdCtrl.getStudentById(rs.getString(1), conn);
			
			studentClass.setStudent(student);
			auditAttendance.setStudentClass(studentClass);
			auditAttendance.setAuditDate(rs.getString(2));
			auditAttendance.setAttendanceDate(rs.getString(3));
			auditAttendance.setStatusBefore(rs.getString(4));
			auditAttendance.setStatusAfter(rs.getString(5));
			
			auditAttendances.add(auditAttendance);
			
		}
		
		return auditAttendances;
	}


	
	public List<DailyAttendance> getAbsentStudentList(String classId, Connection conn) throws Exception
	{
		
		List<DailyAttendance> attendances = new ArrayList<DailyAttendance>();
		DailyAttendance attendance = null;
		Student student = null;
		
		String sql = "{ call ? := getAbsentStudentList(?) }";
		
		CallableStatement cs = conn.prepareCall(sql);
		
		cs.registerOutParameter(1, OracleTypes.CURSOR);
		cs.setString(2, classId);
		
		cs.execute();
		
		ResultSet rs = (ResultSet) cs.getObject(1);
		
		while(rs.next()){
			
			attendance = new DailyAttendance();
			student = stdCtrl.getStudentById(rs.getString(1), conn);
			
			attendance.setStudent(student);
			attendance.setD01(rs.getString(2));
			
			attendances.add(attendance);
			
		}
		
		return attendances;
		
	}
	
	public String verifyAttendanceTime(String classId ,String day ,String month ,String year, Connection conn) throws SQLException{
		
		String result = null;
		
		String sql = "{ call ? := verifyAttendanceTime(?,?,?,?) }";
		
		CallableStatement cs = conn.prepareCall(sql);
		
		cs.registerOutParameter(1, OracleTypes.VARCHAR);
		cs.setString(2, classId);
		cs.setString(3, day);
		cs.setString(4, month);
		cs.setString(5, year);
		
		cs.execute();
		
		result = cs.getString(1);
		
		return result;
	}
	
	public List<SchoolAttendance> jumlahKedatanganBulananSekolah(String classId, String month, String year, Connection conn) throws Exception{
		
		List<SchoolAttendance> schoolAttendances = new ArrayList<SchoolAttendance>();
		SchoolAttendance schoolAttendance = null;
		Classroom classroom = null;

		String sql = "{ call ? := jumlahKedatanganBulananSekolah(?,?,?) }";
		
		CallableStatement cs = conn.prepareCall(sql);
		
		cs.registerOutParameter(1, OracleTypes.CURSOR);
		cs.setString(2, classId);
		cs.setString(3, month);
		cs.setString(4, year);
		
		cs.executeQuery();
		
		ResultSet rs = (ResultSet) cs.getObject(1);
		
		while(rs.next()){
			
			schoolAttendance = new SchoolAttendance();
			classroom = new Classroom();
			
			classroom = classCtrl.getClassroomById(classId, conn);
					
			schoolAttendance.setClassroom(classroom);
			schoolAttendance.setD01(rs.getString(1));
			schoolAttendance.setD02(rs.getString(2));
			schoolAttendance.setD03(rs.getString(3));
			schoolAttendance.setD04(rs.getString(4));
			schoolAttendance.setD05(rs.getString(5));
			schoolAttendance.setD06(rs.getString(6));
			schoolAttendance.setD07(rs.getString(7));
			schoolAttendance.setD08(rs.getString(8));
			schoolAttendance.setD09(rs.getString(9));
			schoolAttendance.setD10(rs.getString(10));
			schoolAttendance.setD11(rs.getString(11));
			schoolAttendance.setD12(rs.getString(12));
			schoolAttendance.setD13(rs.getString(13));
			schoolAttendance.setD14(rs.getString(14));
			schoolAttendance.setD15(rs.getString(15));
			schoolAttendance.setD16(rs.getString(16));
			schoolAttendance.setD17(rs.getString(17));
			schoolAttendance.setD18(rs.getString(18));
			schoolAttendance.setD19(rs.getString(19));
			schoolAttendance.setD20(rs.getString(20));
			schoolAttendance.setD21(rs.getString(21));
			schoolAttendance.setD22(rs.getString(22));
			schoolAttendance.setD23(rs.getString(23));
			schoolAttendance.setD24(rs.getString(24));
			schoolAttendance.setD25(rs.getString(25));
			schoolAttendance.setD26(rs.getString(26));
			schoolAttendance.setD27(rs.getString(27));
			schoolAttendance.setD28(rs.getString(28));
			schoolAttendance.setD29(rs.getString(29));
			schoolAttendance.setD30(rs.getString(30));
			schoolAttendance.setD31(rs.getString(31));
			schoolAttendance.setGender(rs.getString(32));
			schoolAttendance.setStatus(rs.getString(33));
			
			schoolAttendances.add(schoolAttendance);
			
		}
		
		sql = "{ call ? := jumlahHadirSekolah(?,?,?) }";
		
		cs = conn.prepareCall(sql);
		
		cs.registerOutParameter(1, OracleTypes.CURSOR);
		cs.setString(2, classId);
		cs.setString(3, month);
		cs.setString(4, year);
		
		cs.executeQuery();
		
		rs = (ResultSet) cs.getObject(1);
		
		while(rs.next()){
			schoolAttendance = new SchoolAttendance();
			
			schoolAttendance.setD01(rs.getString(1));
			schoolAttendance.setD02(rs.getString(2));
			schoolAttendance.setD03(rs.getString(3));
			schoolAttendance.setD04(rs.getString(4));
			schoolAttendance.setD05(rs.getString(5));
			schoolAttendance.setD06(rs.getString(6));
			schoolAttendance.setD07(rs.getString(7));
			schoolAttendance.setD08(rs.getString(8));
			schoolAttendance.setD09(rs.getString(9));
			schoolAttendance.setD10(rs.getString(10));
			schoolAttendance.setD11(rs.getString(11));
			schoolAttendance.setD12(rs.getString(12));
			schoolAttendance.setD13(rs.getString(13));
			schoolAttendance.setD14(rs.getString(14));
			schoolAttendance.setD15(rs.getString(15));
			schoolAttendance.setD16(rs.getString(16));
			schoolAttendance.setD17(rs.getString(17));
			schoolAttendance.setD18(rs.getString(18));
			schoolAttendance.setD19(rs.getString(19));
			schoolAttendance.setD20(rs.getString(20));
			schoolAttendance.setD21(rs.getString(21));
			schoolAttendance.setD22(rs.getString(22));
			schoolAttendance.setD23(rs.getString(23));
			schoolAttendance.setD24(rs.getString(24));
			schoolAttendance.setD25(rs.getString(25));
			schoolAttendance.setD26(rs.getString(26));
			schoolAttendance.setD27(rs.getString(27));
			schoolAttendance.setD28(rs.getString(28));
			schoolAttendance.setD29(rs.getString(29));
			schoolAttendance.setD30(rs.getString(30));
			schoolAttendance.setD31(rs.getString(31));
			schoolAttendance.setGender(rs.getString(32));
			schoolAttendance.setStatus(rs.getString(33));
			
			schoolAttendances.add(schoolAttendance);
		}
		
		return schoolAttendances;

	}
	
	public int jumlahHadirPerempuan2(String teacherId, String day, String month, String year, Connection conn) throws SQLException{
		
		int result = 0;

		String sql = "{ call ? := jumlahHadirPerempuan2(?,?,?,?) }";
		
		CallableStatement cs = conn.prepareCall(sql);
		
		cs.registerOutParameter(1, OracleTypes.VARCHAR);
		cs.setString(2, teacherId);
		cs.setString(3, day);
		cs.setString(4, month);
		cs.setString(5, year);
		
		cs.execute();
		
		result = cs.getInt(1);
		
		return result;
	}
	
	public int jumlahHadirSekolah(String teacherId, String day, String month, String year, Connection conn) throws SQLException{
		
		int result = 0;

		String sql = "{ call ? := jumlahHadirSekolah(?,?,?,?) }";
		
		CallableStatement cs = conn.prepareCall(sql);
		
		cs.registerOutParameter(1, OracleTypes.VARCHAR);
		cs.setString(2, teacherId);
		cs.setString(3, day);
		cs.setString(4, month);
		cs.setString(5, year);
		
		cs.execute();
		
		result = cs.getInt(1);
		
		return result;
	}
	
	public int jumlahPelajar(String classId, Connection conn) throws Exception {


		String sql = "{ call ? := jumlahPelajar(?) }";
		CallableStatement cs = conn.prepareCall(sql);

		// register the type of the out parameter - an Oracle specific type
		cs.registerOutParameter(1, OracleTypes.NUMBER);
		cs.setString(2, classId);

		// execute and retrieve the results
		cs.execute();

		int jumlah = cs.getInt(1);
		
		return jumlah;
	}
	
	public int jumlahKedatanganHadir(String classId, Connection conn) throws Exception {


		String sql = "{ call ? := jumlahKedatanganHadir(?) }";
		CallableStatement cs = conn.prepareCall(sql);

		// register the type of the out parameter - an Oracle specific type
		cs.registerOutParameter(1, OracleTypes.NUMBER);
		cs.setString(2, classId);

		// execute and retrieve the results
		cs.execute();

		int jumlah = cs.getInt(1);
		
		return jumlah;
	}
	
	public int jumlahKedatanganTidakHadir(String classId, Connection conn) throws Exception {


		String sql = "{ call ? := jumlahKedatanganTidakHadir(?) }";
		CallableStatement cs = conn.prepareCall(sql);

		// register the type of the out parameter - an Oracle specific type
		cs.registerOutParameter(1, OracleTypes.NUMBER);
		cs.setString(2, classId);

		// execute and retrieve the results
		cs.execute();

		int jumlah = cs.getInt(1);
		
		return jumlah;
	}
	
	public int getHadirKelas(String status, String teacherId, Connection conn) throws Exception {


		String sql = "{ call ? := getHadirKelas(?, ?) }";
		CallableStatement cs = conn.prepareCall(sql);

		// register the type of the out parameter - an Oracle specific type
		cs.registerOutParameter(1, OracleTypes.NUMBER);
		cs.setString(2, teacherId);
		cs.setString(3, status);

		// execute and retrieve the results
		cs.execute();

		int jumlah = cs.getInt(1);
		
		return jumlah;
	}
	
	public int getHadirKelasJantina(String status, String teacherId, String gender, Connection conn) throws Exception {


		String sql = "{ call ? := getHadirKelasJantina(?, ?, ?) }";
		CallableStatement cs = conn.prepareCall(sql);

		// register the type of the out parameter - an Oracle specific type
		cs.registerOutParameter(1, OracleTypes.NUMBER);
		cs.setString(2, teacherId);
		cs.setString(3, status);
		cs.setString(4, gender);

		// execute and retrieve the results
		cs.execute();

		int jumlah = cs.getInt(1);
		
		return jumlah;
	}
	
	public List<AttendanceByRace> getAttendanceByRace(String academicInstituitionId, String year, Connection conn) throws Exception{
		
		List<AttendanceByRace> attendanceByRaces = new ArrayList<AttendanceByRace>();
		AttendanceByRace attendanceByRace = null;
		Instituition instituition = null;
		
		String sql = "{ call ? := getAttendanceByRace(?,?)}";
		CallableStatement cs = conn.prepareCall(sql);
		
		cs.registerOutParameter(1, OracleTypes.CURSOR);
		cs.setString(2, academicInstituitionId);
		cs.setString(3, year);
		
		cs.execute();
		
		ResultSet rs = (ResultSet) cs.getObject(1);
		
		while(rs.next()){
			
			attendanceByRace = new AttendanceByRace();
			
			attendanceByRace.setAttendanceByRaceId(rs.getString(1));
			attendanceByRace.setMonth_year(rs.getString(2));
			
			instituition = instCtrl.getInstituitionById(rs.getString(3), conn);
			attendanceByRace.setInstituition(instituition);
			
			attendanceByRace.setMelayuL(rs.getInt(4));
			attendanceByRace.setMelayuP(rs.getInt(5));
			attendanceByRace.setCinaL(rs.getInt(6));
			attendanceByRace.setCinaP(rs.getInt(7));
			attendanceByRace.setIndiaL(rs.getInt(8));
			attendanceByRace.setIndiaP(rs.getInt(9));
			attendanceByRace.setLainL(rs.getInt(10));
			attendanceByRace.setLainP(rs.getInt(11));
			attendanceByRace.setJumlahL(rs.getInt(12));
			attendanceByRace.setJumlahP(rs.getInt(13));
			attendanceByRace.setJumlah(rs.getInt(14));
			attendanceByRace.setCatatan(rs.getString(15));
			
			attendanceByRaces.add(attendanceByRace);
			
			
		}
		
		return attendanceByRaces;
	}
	
	public int getHitungPanjangKedatangan(String academicInstituitionId, String monthYear, Connection conn) throws SQLException{
		
		String sql = "{ call ? := getHitungPanjangKedatangan(?,?) }";
		
		CallableStatement cs = conn.prepareCall(sql);
		
		cs.registerOutParameter(1, OracleTypes.NUMBER);
		cs.setString(2, academicInstituitionId);
		cs.setString(3, monthYear);
		
		cs.execute();
		
		int result = cs.getInt(1);
		
		return result;
	}
	
	public int attendanceAccess(String classId, String day, String monthYear, Connection conn) throws SQLException{
		
		String sql = "{ call ? := attendanceAccess(?,?,?) }";
		
		CallableStatement cs = conn.prepareCall(sql);
		
		cs.registerOutParameter(1, OracleTypes.NUMBER);
		cs.setString(2, classId);
		cs.setString(3, day);
		cs.setString(4, monthYear);
		
		cs.execute();
		
		int result = cs.getInt(1);
		
		return result;
	}
	
	public int attendanceAccessStudent(String studentId , String classId, String day , String monthYear, Connection conn ) throws SQLException{
		
		String sql = "{ call ? := attendanceAccessStudent(?,?,?,?)}";
		
		CallableStatement cs = conn.prepareCall(sql);
		
		cs.registerOutParameter(1, OracleTypes.NUMBER);
		cs.setString(2, studentId);
		cs.setString(3, classId);
		cs.setString(4, day);
		cs.setString(5, monthYear);
		
		cs.execute();
		
		int result = cs.getInt(1);
		
		return result;
	}
	
	public int compareDate(String day, String monthYear, Connection conn) throws SQLException{
		
		String sql = "{ call ? := compareDate(?,?)}";
		
		CallableStatement cs = conn.prepareCall(sql);
		
		cs.registerOutParameter(1, OracleTypes.NUMBER);
		cs.setString(2, day);
		cs.setString(3, monthYear);
		
		cs.execute();
		
		int result = cs.getInt(1);
		
		return result;
	}
	
	public AttendanceStatistics getAttendanceStatisticsByClassId(String classId, String attendanceDate, Connection conn) throws Exception{
		
		AttendanceStatistics attendanceStatistics = new AttendanceStatistics();
		
		String sql = "{ call ? := getAttendanceStatisticsByClass(?,?)}";
		
		CallableStatement cs = conn.prepareCall(sql);
		
		cs.registerOutParameter(1, OracleTypes.CURSOR);
		cs.setString(2, classId);
		cs.setString(3, attendanceDate);
		
		cs.execute();
		
		ResultSet rs = (ResultSet) cs.getObject(1);
		
		while(rs.next()){
			
			attendanceStatistics.setClassroom(classCtrl.getClassroomById(classId, conn));
			attendanceStatistics.setJumlahHadirL(rs.getInt(1));
			attendanceStatistics.setJumlahHadirP(rs.getInt(2));
			attendanceStatistics.setJumlahTidakHadirL(rs.getInt(3));
			attendanceStatistics.setJumlahTidakHadirP(rs.getInt(4));
			attendanceStatistics.setJumlahPelajar(rs.getInt(5));
			attendanceStatistics.setPeratusanKedatangan(rs.getInt(6));
			
		}
		
		return attendanceStatistics;
	}
	
	public List<AttendanceStatistics> getAttendanceStatistics(String instituitionId, String attendanceDate, Connection conn) throws Exception{
		
		List<AttendanceStatistics> attendanceStatistics = new ArrayList<AttendanceStatistics>();
		AttendanceStatistics attendanceStatistic = null;
		
		String sql = "{ call ? := getAttendanceStatistics(?,?)}";
		
		CallableStatement cs = conn.prepareCall(sql);
		
		cs.registerOutParameter(1, OracleTypes.CURSOR);
		cs.setString(2, instituitionId);
		cs.setString(3, attendanceDate);
		
		cs.execute();
		
		ResultSet rs = (ResultSet) cs.getObject(1);
		
		while(rs.next()){
			
			attendanceStatistic = new AttendanceStatistics();
			
			attendanceStatistic.setClassroom(classCtrl.getClassroomById(rs.getString(1), conn));
			attendanceStatistic.setJumlahHadirL(rs.getInt(2));
			attendanceStatistic.setJumlahHadirP(rs.getInt(3));
			attendanceStatistic.setJumlahTidakHadirL(rs.getInt(4));
			attendanceStatistic.setJumlahTidakHadirP(rs.getInt(5));
			attendanceStatistic.setJumlahPelajar(rs.getInt(6));
			attendanceStatistic.setPeratusanKedatangan(rs.getDouble(7));
			
			attendanceStatistics.add(attendanceStatistic);
			
		}
		
		return attendanceStatistics;
		
	}
	
	public List<AttendanceStatistics> getAttendanceStatisticsByTahap(String instituitionId, String attendanceDate, int tahap, Connection conn) throws Exception{
		
		List<AttendanceStatistics> attendanceStatistics = new ArrayList<AttendanceStatistics>();
		AttendanceStatistics attendanceStatistic = null;
		
		String sql = "{ call ? := getAttendanceStatisticsByTahap(?,?,?)}";
		
		CallableStatement cs = conn.prepareCall(sql);
		
		cs.registerOutParameter(1, OracleTypes.CURSOR);
		cs.setString(2, instituitionId);
		cs.setString(3, attendanceDate);
		cs.setInt(4, tahap);
		
		cs.execute();
		
		ResultSet rs = (ResultSet) cs.getObject(1);
		
		while(rs.next()){
			
			attendanceStatistic = new AttendanceStatistics();
			
			attendanceStatistic.setClassroom(classCtrl.getClassroomById(rs.getString(1), conn));
			attendanceStatistic.setJumlahHadirL(rs.getInt(2));
			attendanceStatistic.setJumlahHadirP(rs.getInt(3));
			attendanceStatistic.setJumlahTidakHadirL(rs.getInt(4));
			attendanceStatistic.setJumlahTidakHadirP(rs.getInt(5));
			attendanceStatistic.setJumlahPelajar(rs.getInt(6));
			attendanceStatistic.setPeratusanKedatangan(rs.getDouble(7));
			
			attendanceStatistics.add(attendanceStatistic);
			
		}
		
		return attendanceStatistics;
		
	}
	
	public AttendanceStatistics getTotalAttendanceStatistics(String instituitionId, String attendanceDate, Connection conn) throws Exception{
		
		AttendanceStatistics attendanceStatistics = new AttendanceStatistics();
		
		String sql = "{ call ? := getTotalAttendanceStatistics(?,?)}";
		
		CallableStatement cs = conn.prepareCall(sql);
		
		cs.registerOutParameter(1, OracleTypes.CURSOR);
		cs.setString(2, instituitionId);
		cs.setString(3, attendanceDate);
		
		cs.execute();
		
		ResultSet rs = (ResultSet) cs.getObject(1);
		
		while(rs.next()){
			
			attendanceStatistics.setJumlahHadirL(rs.getInt(1));
			attendanceStatistics.setJumlahHadirP(rs.getInt(2));
			attendanceStatistics.setJumlahTidakHadirL(rs.getInt(3));
			attendanceStatistics.setJumlahTidakHadirP(rs.getInt(4));
			attendanceStatistics.setJumlahPelajar(rs.getInt(7));
			attendanceStatistics.setPeratusanKedatangan(rs.getInt(8));
			
		}
		
		return attendanceStatistics;
		
	}
	
public AttendanceStatistics getTotalAttendanceStatisticsByTahap(String instituitionId, String attendanceDate, int tahap, Connection conn) throws Exception{
		
		AttendanceStatistics attendanceStatistics = new AttendanceStatistics();
		
		String sql = "{ call ? := getTotalAttStatByTahap(?,?,?)}";
		
		CallableStatement cs = conn.prepareCall(sql);
		
		cs.registerOutParameter(1, OracleTypes.CURSOR);
		cs.setString(2, instituitionId);
		cs.setString(3, attendanceDate);
		cs.setInt(4, tahap);
		
		cs.execute();
		
		ResultSet rs = (ResultSet) cs.getObject(1);
		
		while(rs.next()){
			
			attendanceStatistics.setJumlahHadirL(rs.getInt(1));
			attendanceStatistics.setJumlahHadirP(rs.getInt(2));
			attendanceStatistics.setJumlahTidakHadirL(rs.getInt(3));
			attendanceStatistics.setJumlahTidakHadirP(rs.getInt(4));
			attendanceStatistics.setJumlahPelajar(rs.getInt(7));
			attendanceStatistics.setPeratusanKedatangan(rs.getInt(8));
			
		}
		
		return attendanceStatistics;
		
	}

	public void updateTotalDay(String classId, Connection conn) throws SQLException{
		
		
		CallableStatement cs = conn.prepareCall("{call updateTotalDay(?) }");
		
		cs.setString(1, classId);
		
		cs.execute();
		
	}
	
	public int getSchoolAttendancePercentage(String instituitionId, Connection conn) throws SQLException{
		
		String sql = "{call ? := getSchoolAttendancePercentage(?) }";

		CallableStatement cs = null;
		int percentage = 0;
		
		try {
			cs = conn.prepareCall(sql);
			
			cs.registerOutParameter(1, OracleTypes.NUMBER);
			cs.setString(2, instituitionId);
			
			cs.execute();
			
			percentage = cs.getInt(1);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			cs.close();
		}
		
		return percentage;
		
	}
	
	public PpdAttendance ppdReport(String instituitionId, Connection conn) throws Exception {

		CallableStatement cs = null;
		PpdAttendance ppdAttendance = new PpdAttendance();

		try {
			cs = conn.prepareCall("{call ppdreport(?,?,?,?,?,?,?,?) }");

			cs.setString(1, instituitionId);
			cs.registerOutParameter(2, OracleTypes.NUMBER);
			cs.registerOutParameter(3, OracleTypes.NUMBER);
			cs.registerOutParameter(4, OracleTypes.NUMBER);
			cs.registerOutParameter(5, OracleTypes.NUMBER);
			cs.registerOutParameter(6, OracleTypes.NUMBER);
			cs.registerOutParameter(7, OracleTypes.NUMBER);
			cs.registerOutParameter(8, OracleTypes.NUMBER);

			cs.execute();
			
			ControllerWrapper ctrl = new ControllerWrapper();
			
			ppdAttendance.setInstituition(ctrl.getInstituitionById(instituitionId));
			ppdAttendance.setTotalFinish(cs.getInt(2));
			ppdAttendance.setTotalClass(cs.getInt(3));
			ppdAttendance.setFinishPercentage(cs.getInt(4));
			ppdAttendance.setTotalAttend(cs.getInt(5));
			ppdAttendance.setTotalAbsent(cs.getInt(6));
			ppdAttendance.setTotalStudent(cs.getInt(7));
			ppdAttendance.setAttendancePercentage(cs.getInt(8));
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			cs.close();
		}

		return ppdAttendance;

	}
	
	public PpdAttendance ppdReportRecord(String instituitionId, Date date, Connection conn) throws SQLException {

		CallableStatement cs = null;
		PpdAttendance ppdAttendance = new PpdAttendance();

		try {
			cs = conn.prepareCall("{call ppdreportrecord(?,?,?,?,?,?,?,?,?) }");

			cs.setString(1, instituitionId);
			cs.registerOutParameter(2, OracleTypes.NUMBER);
			cs.registerOutParameter(3, OracleTypes.NUMBER);
			cs.registerOutParameter(4, OracleTypes.NUMBER);
			cs.registerOutParameter(5, OracleTypes.NUMBER);
			cs.registerOutParameter(6, OracleTypes.NUMBER);
			cs.registerOutParameter(7, OracleTypes.NUMBER);
			cs.registerOutParameter(8, OracleTypes.NUMBER);
			cs.setDate(9, date);

			cs.execute();

			ppdAttendance.setTotalFinish(cs.getInt(2));
			ppdAttendance.setTotalClass(cs.getInt(3));
			ppdAttendance.setFinishPercentage(cs.getInt(4));
			ppdAttendance.setTotalAttend(cs.getInt(5));
			ppdAttendance.setTotalAbsent(cs.getInt(6));
			ppdAttendance.setTotalStudent(cs.getInt(7));
			ppdAttendance.setAttendancePercentage(cs.getInt(8));

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			cs.close();
		}

		return ppdAttendance;

	}

	public PpdAttendance jpnReport(String city, Connection conn) throws SQLException {

		CallableStatement cs = null;
		PpdAttendance ppdAttendance = new PpdAttendance();

		try {
			cs = conn.prepareCall("{call jpnreport(?,?,?) }");

			cs.setString(1, city);
			cs.registerOutParameter(2, OracleTypes.NUMBER);
			cs.registerOutParameter(3, OracleTypes.NUMBER);

			cs.execute();

			ppdAttendance.setFinishPercentage(cs.getInt(2));
			ppdAttendance.setAttendancePercentage(cs.getInt(3));

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			cs.close();
		}

		return ppdAttendance;

	}

	public PpdAttendance jpnReportRecord(String city, Date date, Connection conn) throws SQLException {

		CallableStatement cs = null;
		PpdAttendance ppdAttendance = new PpdAttendance();

		try {
			cs = conn.prepareCall("{call jpnreportrecord(?,?,?,?) }");

			cs.setString(1, city);
			cs.registerOutParameter(2, OracleTypes.NUMBER);
			cs.registerOutParameter(3, OracleTypes.NUMBER);
			cs.setDate(4, date);

			cs.execute();

			ppdAttendance.setFinishPercentage(cs.getInt(2));
			ppdAttendance.setAttendancePercentage(cs.getInt(3));

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			cs.close();
		}

		return ppdAttendance;

	}
	
	public PpdAttendance jpnPpdReport(String vppd, Connection conn) throws SQLException {

		CallableStatement cs = null;
		PpdAttendance ppdAttendance = new PpdAttendance();

		try {
			cs = conn.prepareCall("{call jpnPpdReport(?,?,?,?,?,?,?,?) }");

			cs.setString(1, vppd);
			cs.registerOutParameter(2, OracleTypes.NUMBER);
			cs.registerOutParameter(3, OracleTypes.NUMBER);
			cs.registerOutParameter(4, OracleTypes.NUMBER);
			cs.registerOutParameter(5, OracleTypes.NUMBER);
			cs.registerOutParameter(6, OracleTypes.NUMBER);
			cs.registerOutParameter(7, OracleTypes.NUMBER);
			cs.registerOutParameter(8, OracleTypes.NUMBER);

			cs.execute();

			ppdAttendance.setTotalFinish(cs.getInt(2));
			ppdAttendance.setTotalClass(cs.getInt(3));
			ppdAttendance.setFinishPercentage(cs.getInt(4));
			ppdAttendance.setTotalAttend(cs.getInt(5));
			ppdAttendance.setTotalAbsent(cs.getInt(6));
			ppdAttendance.setTotalStudent(cs.getInt(7));
			ppdAttendance.setAttendancePercentage(cs.getInt(8));

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			cs.close();
		}

		return ppdAttendance;

	}

	public PpdAttendance jpnPpdReportRecord(String vppd, Date date, Connection conn) throws SQLException {

		CallableStatement cs = null;
		PpdAttendance ppdAttendance = new PpdAttendance();

		try {
			cs = conn.prepareCall("{call jpnPpdReportRecord(?,?,?,?,?,?,?,?,?) }");

			cs.setString(1, vppd);
			cs.registerOutParameter(2, OracleTypes.NUMBER);
			cs.registerOutParameter(3, OracleTypes.NUMBER);
			cs.registerOutParameter(4, OracleTypes.NUMBER);
			cs.registerOutParameter(5, OracleTypes.NUMBER);
			cs.registerOutParameter(6, OracleTypes.NUMBER);
			cs.registerOutParameter(7, OracleTypes.NUMBER);
			cs.registerOutParameter(8, OracleTypes.NUMBER);
			cs.setDate(9, date);

			cs.execute();

			ppdAttendance.setTotalFinish(cs.getInt(2));
			ppdAttendance.setTotalClass(cs.getInt(3));
			ppdAttendance.setFinishPercentage(cs.getInt(4));
			ppdAttendance.setTotalAttend(cs.getInt(5));
			ppdAttendance.setTotalAbsent(cs.getInt(6));
			ppdAttendance.setTotalStudent(cs.getInt(7));
			ppdAttendance.setAttendancePercentage(cs.getInt(8));

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			cs.close();
		}

		return ppdAttendance;

	}
	
	public PpdAttendance policeReport(int stationId, Connection conn) throws SQLException {

		CallableStatement cs = null;
		PpdAttendance ppdAttendance = new PpdAttendance();

		try {
			cs = conn.prepareCall("{call policeReport(?,?,?,?,?,?,?,?) }");

			cs.setInt(1, stationId);
			cs.registerOutParameter(2, OracleTypes.NUMBER);
			cs.registerOutParameter(3, OracleTypes.NUMBER);
			cs.registerOutParameter(4, OracleTypes.NUMBER);
			cs.registerOutParameter(5, OracleTypes.NUMBER);
			cs.registerOutParameter(6, OracleTypes.NUMBER);
			cs.registerOutParameter(7, OracleTypes.NUMBER);
			cs.registerOutParameter(8, OracleTypes.NUMBER);

			cs.execute();

			ppdAttendance.setTotalFinish(cs.getInt(2));
			ppdAttendance.setTotalClass(cs.getInt(3));
			ppdAttendance.setFinishPercentage(cs.getInt(4));
			ppdAttendance.setTotalAttend(cs.getInt(5));
			ppdAttendance.setTotalAbsent(cs.getInt(6));
			ppdAttendance.setTotalStudent(cs.getInt(7));
			ppdAttendance.setAttendancePercentage(cs.getInt(8));

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			cs.close();
		}

		return ppdAttendance;

	}
	
	
	public PpdAttendance policeReportRecord(int stationId, Date date, Connection conn) throws SQLException {

		CallableStatement cs = null;
		PpdAttendance ppdAttendance = new PpdAttendance();

		try {
			cs = conn.prepareCall("{call policeReportRecord(?,?,?,?,?,?,?,?,?) }");

			cs.setInt(1, stationId);
			cs.registerOutParameter(2, OracleTypes.NUMBER);
			cs.registerOutParameter(3, OracleTypes.NUMBER);
			cs.registerOutParameter(4, OracleTypes.NUMBER);
			cs.registerOutParameter(5, OracleTypes.NUMBER);
			cs.registerOutParameter(6, OracleTypes.NUMBER);
			cs.registerOutParameter(7, OracleTypes.NUMBER);
			cs.registerOutParameter(8, OracleTypes.NUMBER);
			cs.setDate(9, date);

			cs.execute();

			ppdAttendance.setTotalFinish(cs.getInt(2));
			ppdAttendance.setTotalClass(cs.getInt(3));
			ppdAttendance.setFinishPercentage(cs.getInt(4));
			ppdAttendance.setTotalAttend(cs.getInt(5));
			ppdAttendance.setTotalAbsent(cs.getInt(6));
			ppdAttendance.setTotalStudent(cs.getInt(7));
			ppdAttendance.setAttendancePercentage(cs.getInt(8));

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			cs.close();
		}

		return ppdAttendance;

	}
	
	public PpdAttendance politicianReport(int dunId, Connection conn) throws SQLException {

		CallableStatement cs = null;
		PpdAttendance ppdAttendance = new PpdAttendance();

		try {
			cs = conn.prepareCall("{call politicianReport(?,?,?,?,?,?,?,?) }");

			cs.setInt(1, dunId);
			cs.registerOutParameter(2, OracleTypes.NUMBER);
			cs.registerOutParameter(3, OracleTypes.NUMBER);
			cs.registerOutParameter(4, OracleTypes.NUMBER);
			cs.registerOutParameter(5, OracleTypes.NUMBER);
			cs.registerOutParameter(6, OracleTypes.NUMBER);
			cs.registerOutParameter(7, OracleTypes.NUMBER);
			cs.registerOutParameter(8, OracleTypes.NUMBER);

			cs.execute();

			ppdAttendance.setTotalFinish(cs.getInt(2));
			ppdAttendance.setTotalClass(cs.getInt(3));
			ppdAttendance.setFinishPercentage(cs.getInt(4));
			ppdAttendance.setTotalAttend(cs.getInt(5));
			ppdAttendance.setTotalAbsent(cs.getInt(6));
			ppdAttendance.setTotalStudent(cs.getInt(7));
			ppdAttendance.setAttendancePercentage(cs.getInt(8));

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			cs.close();
		}

		return ppdAttendance;

	}
	
	
	public PpdAttendance politicianReportRecord(int dunId, Date date, Connection conn) throws SQLException {

		CallableStatement cs = null;
		PpdAttendance ppdAttendance = new PpdAttendance();

		try {
			cs = conn.prepareCall("{call politicianReportRecord(?,?,?,?,?,?,?,?,?) }");

			cs.setInt(1, dunId);
			cs.registerOutParameter(2, OracleTypes.NUMBER);
			cs.registerOutParameter(3, OracleTypes.NUMBER);
			cs.registerOutParameter(4, OracleTypes.NUMBER);
			cs.registerOutParameter(5, OracleTypes.NUMBER);
			cs.registerOutParameter(6, OracleTypes.NUMBER);
			cs.registerOutParameter(7, OracleTypes.NUMBER);
			cs.registerOutParameter(8, OracleTypes.NUMBER);
			cs.setDate(9, date);

			cs.execute();

			ppdAttendance.setTotalFinish(cs.getInt(2));
			ppdAttendance.setTotalClass(cs.getInt(3));
			ppdAttendance.setFinishPercentage(cs.getInt(4));
			ppdAttendance.setTotalAttend(cs.getInt(5));
			ppdAttendance.setTotalAbsent(cs.getInt(6));
			ppdAttendance.setTotalStudent(cs.getInt(7));
			ppdAttendance.setAttendancePercentage(cs.getInt(8));

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			cs.close();
		}

		return ppdAttendance;

	}
	
	public int dailyattendancecarryforwstat(String studentId , String classId, Connection conn ) throws SQLException{
		
		String sql = "{ call ? := dailyattendancecarryforwstat(?,?)}";
		
		CallableStatement cs = conn.prepareCall(sql);
		
		cs.registerOutParameter(1, OracleTypes.NUMBER);
		cs.setString(2, studentId);
		cs.setString(3, classId);
		
		cs.execute();
		
		int result = cs.getInt(1);
		
		return result;
	}
	
	// for all student attend
	public int dailyattendancecarryforwstat2(String classId, Connection conn) throws SQLException{
		
		String sql = "{ call ? := dailyattendancecarryforwstat2(?)}";
		
		CallableStatement cs = conn.prepareCall(sql);
		
		cs.registerOutParameter(1, OracleTypes.NUMBER);
		cs.setString(2, classId);
		
		cs.execute();
		
		int result = cs.getInt(1);
		
		return result;
	}
	
}
