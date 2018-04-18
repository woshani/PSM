package facade;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import service.BulkSMS2U;
import service.NotificationSystem;
import bean.AlertPackage;
import bean.AnalysisYearAttendance;
import bean.Announcement;
import bean.AttendanceByRace;
import bean.AttendanceStatistics;
import bean.AuditDailyAttendance;
import bean.Classroom;
import bean.DailyAttendance;
import bean.Discussion;
import bean.Dun;
import bean.FinishTime;
import bean.Guardian;
import bean.GuardianContact;
import bean.Instituition;
import bean.InstituitionKoku;
import bean.Koku;
import bean.KokuAttendance;
import bean.KokuType;
import bean.Meeting;
import bean.Message;
import bean.Monitor;
import bean.OutgoingSMS;
import bean.Parlimen;
import bean.Police;
import bean.Politician;
import bean.Position;
import bean.PpdAttendance;
import bean.Race;
import bean.Religion;
import bean.Report;
import bean.SchoolAttendance;
import bean.SkPosition;
import bean.Station;
import bean.Student;
import bean.StudentKoku;
import bean.Teacher;
import bean.TeacherClass;
import bean.TeacherKoku;
import bean.Topic;
import bean.TransferStudent;
import bean.User;
import bean.WarningHistory;
import connection.DBConnection;
import controller.AlertPackageController;
import controller.AnnouncementController;
import controller.AttendanceController;
import controller.ClassroomController;
import controller.DateController;
import controller.DiscussionController;
import controller.DunController;
import controller.GuardianController;
import controller.ImageController;
import controller.InstituitionController;
import controller.KokuAttendanceController;
import controller.KokuController;
import controller.MeetingController;
import controller.OutgoingSMSController;
import controller.ParlimenController;
import controller.PoliceController;
import controller.PoliticianController;
import controller.StationController;
import controller.StudentController;
import controller.TeacherController;
import controller.TopicController;
import controller.UserController;
import controller.WarningHistoryController;

public class ControllerWrapper {
	/*
	 * Declaration of variables
	 */
	private DBConnection dbConn;
	private Connection conn;
	private UserController userCtrl = new UserController();
	private InstituitionController instCtrl = new InstituitionController();
	private ClassroomController classCtrl = new ClassroomController();
	private TeacherController teacherCtrl = new TeacherController();
	private StudentController stuCtrl = new StudentController();
	private GuardianController grdnCtrl = new GuardianController();
	private AlertPackageController packCtrl = new AlertPackageController();
	private AnnouncementController annCtrl = new AnnouncementController();
	private OutgoingSMSController osmsCtrl = new OutgoingSMSController();
	private AttendanceController attdCtrl = new AttendanceController();
	private MeetingController meetCtrl = new MeetingController();
	private KokuAttendanceController kokuAttdCtrl = new KokuAttendanceController();
	private DateController dateCtrl = new DateController();
	private KokuController kokuCtrl = new KokuController();
	private ImageController imgCtrl = new ImageController();
	private TopicController topicCtrl = new TopicController();
	private DiscussionController discussionCtrl = new DiscussionController();
	private WarningHistoryController wHCtrl = new WarningHistoryController();
	private PoliceController policeCtrl = new PoliceController();
	private PoliticianController politicianCtrl = new PoliticianController();
	private StationController stationCtrl = new StationController();
	private ParlimenController parlimenCtrl = new ParlimenController();
	private DunController dunCtrl = new DunController();

	private BulkSMS2U bulkSMS = new BulkSMS2U();
	private NotificationSystem ns = new NotificationSystem();

	/**
	 * Constructor of ControllerWrapper
	 */
	public ControllerWrapper() {
		dbConn = new DBConnection();
	}

	public int checkUserId(String user_id) throws Exception {
		conn = dbConn.getConnection();
		int result = userCtrl.checkUserId(user_id, conn);
		dbConn.closeConnection(conn);
		return result;
	}

	public int userLogin(String username, String password) throws Exception {
		conn = dbConn.getConnection();
		int result = userCtrl.userLogin(username, password, conn);
		dbConn.closeConnection(conn);
		return result;
	}

	public List<User> getAllUsers() throws Exception {
		conn = dbConn.getConnection();
		List<User> result = userCtrl.getAllUsers(conn);
		dbConn.closeConnection(conn);
		return result;
	}

	public User getUserById(String user_id) throws Exception {
		conn = dbConn.getConnection();
		User result = userCtrl.getUserById(user_id, conn);
		dbConn.closeConnection(conn);
		return result;
	}

	public User getUserByUsername(String username) throws Exception {
		conn = dbConn.getConnection();
		User result = userCtrl.getUserByUsername(username, conn);
		dbConn.closeConnection(conn);
		return result;
	}

	public int checkInstituitionId(String academice_instituition_id)
			throws Exception {
		conn = dbConn.getConnection();
		int result = instCtrl.checkInstituitionId(academice_instituition_id,
				conn);
		dbConn.closeConnection(conn);
		return result;
	}

	public List<Instituition> getAllInstituitions() throws Exception {
		conn = dbConn.getConnection();
		List<Instituition> result = instCtrl.getAllInstituitions(conn);
		dbConn.closeConnection(conn);
		return result;
	}

	public Instituition getInstituitionById(String academic_instituition_id)
			throws Exception {
		conn = dbConn.getConnection();
		Instituition result = instCtrl.getInstituitionById(
				academic_instituition_id, conn);
		dbConn.closeConnection(conn);
		return result;
	}

	public int checkClassroomId(String class_id) throws Exception {
		conn = dbConn.getConnection();
		int result = classCtrl.checkClassroomId(class_id, conn);
		dbConn.closeConnection(conn);
		return result;
	}

	public List<Classroom> getAllClassrooms() throws Exception {
		conn = dbConn.getConnection();
		List<Classroom> result = classCtrl.getAllClassrooms(conn);
		dbConn.closeConnection(conn);
		return result;
	}

	public Classroom getClassroomById(String class_id) throws Exception {
		conn = dbConn.getConnection();
		Classroom result = classCtrl.getClassroomById(class_id, conn);
		dbConn.closeConnection(conn);
		return result;
	}

	public List<Classroom> getClassroomsByInstituitionId(
			String academic_instituition_id) throws Exception {
		conn = dbConn.getConnection();
		List<Classroom> result = classCtrl.getClassroomsByInstituitionId(
				academic_instituition_id, conn);
		dbConn.closeConnection(conn);
		return result;
	}

	public List<Classroom> getClassroomsByTeacherId(String teacher_id)
			throws Exception {
		conn = dbConn.getConnection();
		List<Classroom> result = classCtrl.getClassroomsByTeacherId(teacher_id,
				conn);
		dbConn.closeConnection(conn);
		return result;
	}

	public Classroom getClassroomByStudentId(String student_id)
			throws Exception {
		conn = dbConn.getConnection();
		Classroom result = classCtrl.getClassroomByStudentId(student_id, conn);
		dbConn.closeConnection(conn);
		return result;
	}

	public int checkTeacherId(String teacher_id) throws Exception {
		conn = dbConn.getConnection();
		int result = teacherCtrl.checkTeacherId(teacher_id, conn);
		dbConn.closeConnection(conn);
		return result;
	}

	public List<Teacher> getAllTeachers() throws Exception {
		conn = dbConn.getConnection();
		List<Teacher> result = teacherCtrl.getAllTeachers(conn);
		dbConn.closeConnection(conn);
		return result;
	}

	public Teacher getTeacherById(String teacher_id) throws Exception {
		conn = dbConn.getConnection();
		Teacher result = teacherCtrl.getTeacherById(teacher_id, conn);
		dbConn.closeConnection(conn);
		return result;
	}

	public Teacher getTeacherByUserId(String user_id) throws Exception {
		conn = dbConn.getConnection();
		Teacher result = teacherCtrl.getTeacherByUserId(user_id, conn);
		dbConn.closeConnection(conn);
		return result;
	}

	public List<Teacher> getTeachersByClassroomId(String classroom_id)
			throws Exception {
		conn = dbConn.getConnection();
		List<Teacher> result = teacherCtrl.getTeachersByClassroomId(
				classroom_id, conn);
		dbConn.closeConnection(conn);
		return result;
	}

	public boolean isClassroomTeacher(String teacher_id, String classroom_id,
			Connection conn) throws Exception {
		conn = dbConn.getConnection();
		List<Teacher> teachers = teacherCtrl.getTeachersByClassroomId(
				classroom_id, conn);
		dbConn.closeConnection(conn);
		if (teachers.contains(teacher_id)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isStudentTeacher(String student_id, String teacher_id,
			Connection conn) throws Exception {
		conn = dbConn.getConnection();
		List<Classroom> classrooms = classCtrl.getClassroomsByTeacherId(
				teacher_id, conn);
		Classroom classroom = classCtrl.getClassroomByStudentId(student_id,
				conn);
		dbConn.closeConnection(conn);
		if (classrooms.contains(classroom)) {
			return true;
		} else {
			return false;
		}
	}

	public int checkStudentId(String student_id) throws Exception {
		conn = dbConn.getConnection();
		int result = stuCtrl.checkStudentId(student_id, conn);
		dbConn.closeConnection(conn);
		return result;
	}
	
	public int checkStudentIc(String student_ic) throws Exception {
		conn = dbConn.getConnection();
		int result = stuCtrl.checkStudentIc(student_ic, conn);
		dbConn.closeConnection(conn);
		return result;
	}

	public List<Student> getAllStudents() throws Exception {
		conn = dbConn.getConnection();
		List<Student> result = stuCtrl.getAllStudents(conn);
		dbConn.closeConnection(conn);
		return result;
	}

	public Student getStudentById(String student_id) throws Exception {
		conn = dbConn.getConnection();
		Student result = stuCtrl.getStudentById(student_id, conn);
		dbConn.closeConnection(conn);
		return result;
	}
	
	public Student getStudentByIc(String student_ic) throws Exception {
		conn = dbConn.getConnection();
		Student result = stuCtrl.getStudentByIc(student_ic, conn);
		dbConn.closeConnection(conn);
		return result;
	}
	
	public Student getStudentByIdGuarView(String student_id, String class_id) throws Exception {
		conn = dbConn.getConnection();
		Student result = stuCtrl.getStudentByIdGuarView(student_id, class_id, conn);
		dbConn.closeConnection(conn);
		return result;
	}

	public List<Student> getStudentsByClassId(String class_id) throws Exception {
		conn = dbConn.getConnection();
		List<Student> result = stuCtrl.getStudentsByClassId(class_id, conn);
		dbConn.closeConnection(conn);
		return result;
	}

	public List<Student> getStudentsByGuardianId(String guardian_id)
			throws Exception {
		conn = dbConn.getConnection();
		List<Student> result = stuCtrl.getStudentsByGuardianId(guardian_id,
				conn);
		dbConn.closeConnection(conn);
		return result;
	}

	public List<Student> getStudentByTeacher(String teacherId) throws Exception {
		conn = dbConn.getConnection();
		Vector<Student> result = stuCtrl.getStudentByTeacher(teacherId, conn);
		dbConn.closeConnection(conn);
		return result;
	}

	public List<StudentKoku> getAllStudentByInstKokuId(String instKokuId)
			throws Exception {
		conn = dbConn.getConnection();
		List<StudentKoku> result = kokuCtrl.getAllStudentByInstKokuId(
				instKokuId, conn);
		dbConn.closeConnection(conn);
		return result;
	}

	public int getStudentCount(String class_id) throws Exception {
		conn = dbConn.getConnection();
		int result = stuCtrl.getStudentCount(class_id, conn);
		dbConn.closeConnection(conn);
		return result;
	}

	public int checkGuardianId(String guardian_id) throws Exception {
		conn = dbConn.getConnection();
		int result = grdnCtrl.checkGuardianId(guardian_id, conn);
		dbConn.closeConnection(conn);
		return result;
	}
	
	public int checkGuardianIc(String guardian_ic) throws Exception {
		conn = dbConn.getConnection();
		int result = grdnCtrl.checkGuardianIc(guardian_ic, conn);
		dbConn.closeConnection(conn);
		return result;
	}

	public List<Guardian> getAllGuardians() throws Exception {
		conn = dbConn.getConnection();
		List<Guardian> result = grdnCtrl.getAllGuardians(conn);
		dbConn.closeConnection(conn);
		return result;
	}

	public Guardian getGuardianById(String guardian_id) throws Exception {
		conn = dbConn.getConnection();
		Guardian result = grdnCtrl.getGuardianById(guardian_id, conn);
		dbConn.closeConnection(conn);
		return result;
	}
	
	public Guardian getGuardianById2(String guardian_id) throws Exception {
		conn = dbConn.getConnection();
		Guardian result = grdnCtrl.getGuardianById2(guardian_id, conn);
		dbConn.closeConnection(conn);
		return result;
	}
	
	public Guardian getGuardianByIc(String guardian_ic) throws Exception {
		conn = dbConn.getConnection();
		Guardian result = grdnCtrl.getGuardianByIc(guardian_ic, conn);
		dbConn.closeConnection(conn);
		return result;
	}

	public Guardian getGuardianByUserId(String user_id) throws Exception {
		conn = dbConn.getConnection();
		Guardian result = grdnCtrl.getGuardianByUserId(user_id, conn);
		dbConn.closeConnection(conn);
		return result;
	}

	public List<Guardian> getGuardiansByStudentId(String student_id)
			throws Exception {
		conn = dbConn.getConnection();
		List<Guardian> result = grdnCtrl.getGuardiansByStudentId(student_id,
				conn);
		dbConn.closeConnection(conn);
		return result;
	}
	
	public List<Guardian> getGuardiansByStudentId2(String student_id)
			throws Exception {
		conn = dbConn.getConnection();
		List<Guardian> result = grdnCtrl.getGuardiansByStudentId2(student_id,
				conn);
		dbConn.closeConnection(conn);
		return result;
	}
	
	public List<Guardian> getGuardiansByClassId(String class_id)
			throws Exception {
		conn = dbConn.getConnection();
		List<Guardian> result = grdnCtrl.getGuardiansByClassId(class_id,
				conn);
		dbConn.closeConnection(conn);
		return result;
	}
	
	public List<Guardian> getGuardiansByInstitutionId(String institution_id)
			throws Exception {
		conn = dbConn.getConnection();
		List<Guardian> result = grdnCtrl.getGuardiansByInstitutionId(institution_id,
				conn);
		dbConn.closeConnection(conn);
		return result;
	}

	public int checkGuardianContactId(String guardian_contact_id)
			throws Exception {
		conn = dbConn.getConnection();
		int result = grdnCtrl.checkGuardianContactId(guardian_contact_id, conn);
		dbConn.closeConnection(conn);
		return result;
	}

	public ArrayList<GuardianContact> getAllGuardianContacts(String guardian_id)
			throws Exception {
		conn = dbConn.getConnection();
		ArrayList<GuardianContact> result = grdnCtrl.getAllGuardianContacts(
				guardian_id, conn);
		dbConn.closeConnection(conn);
		return result;
	}

	public GuardianContact getGuardianSubscribedContact(String guardian_id)
			throws Exception {
		conn = dbConn.getConnection();
		GuardianContact result = grdnCtrl.getGuardianSubscribedContact(
				guardian_id, conn);
		dbConn.closeConnection(conn);
		return result;
	}

	public int addNewGuardianContact(GuardianContact contact) throws Exception {
		conn = dbConn.getConnection();
		int result = grdnCtrl.addNewGuardianContact(contact, conn);
		dbConn.closeConnection(conn);
		return result;
	}

	public int updateGuardianContact(GuardianContact contact) throws Exception {
		conn = dbConn.getConnection();
		int result = grdnCtrl.updateGuardianContact(contact, conn);
		dbConn.closeConnection(conn);
		return result;
	}

	public int deleteGuardianContact(GuardianContact contact) throws Exception {
		conn = dbConn.getConnection();
		int result = grdnCtrl.deleteGuardianContact(contact, conn);
		dbConn.closeConnection(conn);
		return result;
	}

	public int sendAlertToGuardian(Guardian guardian) throws Exception {
		conn = dbConn.getConnection();
		int result = grdnCtrl.sendAlertToGuardian(guardian, conn);
		dbConn.closeConnection(conn);
		return result;
	}

	public int getGuardianOutgoingSMSCount(Guardian guardian) throws Exception {
		conn = dbConn.getConnection();
		int result = grdnCtrl.getGuardianOutgoingSMSCount(guardian, conn);
		dbConn.closeConnection(conn);
		return result;
	}

	public int getGuardianIncomingSMSCount(Guardian guardian) throws Exception {
		conn = dbConn.getConnection();
		int result = grdnCtrl.getGuardianIncomingSMSCount(guardian, conn);
		dbConn.closeConnection(conn);
		return result;
	}

	public int getGuardianMonthlyOutgoingSMSCount(Guardian guardian)
			throws Exception {
		conn = dbConn.getConnection();
		int result = grdnCtrl
				.getGuardianMonthlyOutgoingSMSCount(guardian, conn);
		dbConn.closeConnection(conn);
		return result;
	}

	public int getGuardianMonthlyIncomingSMSCount(Guardian guardian)
			throws Exception {
		conn = dbConn.getConnection();
		int result = grdnCtrl
				.getGuardianMonthlyIncomingSMSCount(guardian, conn);
		dbConn.closeConnection(conn);
		return result;
	}

	public int subscribe(Guardian guardian, AlertPackage alertPackage)
			throws Exception {
		conn = dbConn.getConnection();
		int result = grdnCtrl.subscribe(guardian, alertPackage, conn);
		dbConn.closeConnection(conn);
		return result;
	}

	public int unsubscribe(Guardian guardian, AlertPackage alertPackage)
			throws Exception {
		conn = dbConn.getConnection();
		int result = grdnCtrl.unsubscribe(guardian, alertPackage, conn);
		dbConn.closeConnection(conn);
		return result;
	}

	public int checkAlertPackageId(String package_id) throws Exception {
		conn = dbConn.getConnection();
		int result = packCtrl.checkAlerPackageId(package_id, conn);
		dbConn.closeConnection(conn);
		return result;
	}

	public List<AlertPackage> getAllAlertPackages() throws Exception {
		conn = dbConn.getConnection();
		List<AlertPackage> result = packCtrl.getAllAlertPackages(conn);
		dbConn.closeConnection(conn);
		return result;
	}

	public AlertPackage getAlertPackageById(String package_id) throws Exception {
		conn = dbConn.getConnection();
		AlertPackage result = packCtrl.getAlertPackageById(package_id, conn);
		dbConn.closeConnection(conn);
		return result;
	}

	public List<AlertPackage> getAlertPackagesByGuardian(Guardian guardian)
			throws Exception {
		conn = dbConn.getConnection();
		List<AlertPackage> result = packCtrl.getAlertPackagesByGuardian(
				guardian, conn);
		dbConn.closeConnection(conn);
		return result;
	}

	public int addNewAlertPackage(AlertPackage alertPackage) throws Exception {
		conn = dbConn.getConnection();
		int result = packCtrl.addNewAlertPackage(alertPackage, conn);
		dbConn.closeConnection(conn);
		return result;
	}

	public int updateAlertPackage(AlertPackage alertPackage) throws Exception {
		conn = dbConn.getConnection();
		int result = packCtrl.updateAlertPackage(alertPackage, conn);
		dbConn.closeConnection(conn);
		return result;
	}

	public int checkAnnouncementId(String announcement_id) throws Exception {
		conn = dbConn.getConnection();
		int result = annCtrl.checkAnnouncementId(announcement_id, conn);
		dbConn.closeConnection(conn);
		return result;
	}

	public List<Announcement> getAllAnnouncements() throws Exception {
		conn = dbConn.getConnection();
		List<Announcement> result = annCtrl.getAllAnnouncements(conn);
		dbConn.closeConnection(conn);
		return result;
	}

	public List<Announcement> getAllSentAnnouncementsById(String teacherId) throws Exception {
		conn = dbConn.getConnection();
		List<Announcement> result = annCtrl.getAllSentAnnouncementsById(teacherId, conn);
		dbConn.closeConnection(conn);
		return result;
	}
	
	public List<Announcement> getAllDelayAnnouncementsById(String teacherId) throws Exception {
		conn = dbConn.getConnection();
		List<Announcement> result = annCtrl.getAllDelayAnnouncementsById(teacherId, conn);
		dbConn.closeConnection(conn);
		return result;
	}
	
	public List<Announcement> getAllSentAnnouncements() throws Exception {
		conn = dbConn.getConnection();
		List<Announcement> result = annCtrl.getAllSentAnnouncements(conn);
		dbConn.closeConnection(conn);
		return result;
	}

	public Announcement getAnnouncementById(String announcement_id)
			throws Exception {
		conn = dbConn.getConnection();
		Announcement result = annCtrl
				.getAnnouncementById(announcement_id, conn);
		dbConn.closeConnection(conn);
		return result;
	}

	public int addNewAnnouncement(Announcement announcement) throws Exception {
		conn = dbConn.getConnection();
		int result = annCtrl.addNewAnnouncement(announcement, conn);
		dbConn.closeConnection(conn);
		return result;
	}
	
	public int deleteAnnouncement(Announcement announcement) throws Exception {
		conn = dbConn.getConnection();
		int result = annCtrl.deleteAnnouncement(announcement, conn);
		dbConn.closeConnection(conn);
		return result;
	}
	
	public int updateAnnouncement(Announcement announcement) throws Exception {
		conn = dbConn.getConnection();
		int result = annCtrl.updateAnnouncement(announcement, conn);
		dbConn.closeConnection(conn);
		return result;
	}

	// public int deleteAnnouncement(Announcement announcement) throws Exception
	// {
	// conn = dbConn.getConnection();
	// int result = annCtrl.deleteAnnouncement(announcement, conn);
	// dbConn.closeConnection(conn);
	// return result;
	// }

	public int updateAnnouncementStatus(String announcement_id, Connection conn)
			throws Exception {
		conn = dbConn.getConnection();
		int result = annCtrl.updateAnnouncementStatus(announcement_id, conn);
		dbConn.closeConnection(conn);
		return result;
	}

	public int checkOutgoingSMSId(String osms_id) throws Exception {
		conn = dbConn.getConnection();
		int result = osmsCtrl.checkOutgoingSMSId(osms_id, conn);
		dbConn.closeConnection(conn);
		return result;
	}

	public List<OutgoingSMS> getAllOutgoingSMSs() throws Exception {
		conn = dbConn.getConnection();
		List<OutgoingSMS> result = osmsCtrl.getAllOutgoingSMSs(conn);
		dbConn.closeConnection(conn);
		return result;
	}

	public List<OutgoingSMS> getAllSentSMSs() throws Exception {
		conn = dbConn.getConnection();
		List<OutgoingSMS> result = osmsCtrl.getAllSentSMSs(conn);
		dbConn.closeConnection(conn);
		return result;
	}

	public OutgoingSMS getOutgoingSMSById(String osms_ids) throws Exception {
		conn = dbConn.getConnection();
		OutgoingSMS result = osmsCtrl.getOutgoingSMSById(osms_ids, conn);
		dbConn.closeConnection(conn);
		return result;
	}

	public int updateOutgoingSMSStatus(String osms_id, Connection conn)
			throws Exception {
		conn = dbConn.getConnection();
		int result = osmsCtrl.updateOutgoingSMSStatus(osms_id, conn);
		dbConn.closeConnection(conn);
		return result;
	}
	
	public int deleteOutGoingSMS(OutgoingSMS ogsms) throws Exception {
		conn = dbConn.getConnection();
		int result = osmsCtrl.deleteOutGoingSMS(ogsms, conn);
		dbConn.closeConnection(conn);
		return result;
	}

	public List<Message> getAllMessagesByOutgoingSMSs(
			List<OutgoingSMS> outgoingSMSs) throws Exception {
		conn = dbConn.getConnection();
		List<Message> result = osmsCtrl.getMessagesByOutgoingSMSs(outgoingSMSs,
				conn);
		dbConn.closeConnection(conn);
		return result;
	}

	public List<Message> getAllMessagesByAnnouncements(
			List<Announcement> announcements) throws Exception {
		conn = dbConn.getConnection();
		// get message by annoucement_id and change annoucement.status from 'N' (not send) to 'Y'(sent)
		List<Message> result = annCtrl.getMessagesByAnnouncements(
				announcements, conn);
		dbConn.closeConnection(conn);
		return result;
	}

	public String sendSMSs(List<Message> messages, String sender) throws Exception {
		conn = dbConn.getConnection();
		String result = bulkSMS.sendSMSs(messages, sender, conn);
		dbConn.closeConnection(conn);
		return result;
	}

	public String checkBalance() throws Exception {
		return bulkSMS.checkBalance();
	}

	public int insertDailyAttendance(String classId) throws Exception {
		conn = dbConn.getConnection();
		int result = attdCtrl.insertDailyAttendance(classId, conn);
		dbConn.closeConnection(conn);
		return result;
	}

	public int insertDailyAttendanceAbsent(String studentId, String classId) throws Exception {
		conn = dbConn.getConnection();
		int result = attdCtrl.insertDailyAttendanceAbsent(studentId, classId, conn);
		dbConn.closeConnection(conn);
		return result;
	}

	public int insertFinishTime(String classId) throws Exception {
		conn = dbConn.getConnection();
		int result = attdCtrl.insertFinishTime(classId, conn);
		dbConn.closeConnection(conn);
		return result;
	}

	public String WarningList(String studentId) throws Exception {
		conn = dbConn.getConnection();
		String result = attdCtrl.WarningList(studentId, conn);
		dbConn.closeConnection(conn);
		return result;
	}
	
	public WarningHistory WarningList2(String studentId) throws Exception {
		conn = dbConn.getConnection();
		WarningHistory result = attdCtrl.WarningList2(studentId, conn);
		dbConn.closeConnection(conn);
		return result;
	}

	public List<DailyAttendance> MonthlyAttendanceList(String month,
			String year, String classId) throws Exception {
		conn = dbConn.getConnection();
		Vector<DailyAttendance> result = attdCtrl.MonthlyAttendanceList(month,
				year, classId, conn);
		dbConn.closeConnection(conn);
		return result;
	}
	
	public List<DailyAttendance> MonthlyTransferAttendanceList(String month,
			String year, String classId) throws Exception {
		conn = dbConn.getConnection();
		Vector<DailyAttendance> result = attdCtrl.MonthlyTransferAttendanceList(month,
				year, classId, conn);
		dbConn.closeConnection(conn);
		return result;
	}

	public Monitor studentWarning(String studentId, String whId) throws Exception {
		conn = dbConn.getConnection();
		Monitor result = attdCtrl.studentWarning(studentId, whId, conn);
		dbConn.closeConnection(conn);
		return result;
	}
	
	public Meeting insertMeeting(Meeting meeting, String instId)
			throws Exception {
		conn = dbConn.getConnection();
		Meeting result = meetCtrl.insertMeeting(meeting, instId, conn);
		dbConn.closeConnection(conn);
		return result;
	}

	public KokuAttendance insertAttendance(KokuAttendance kokuAttendance)
			throws Exception {
		conn = dbConn.getConnection();
		KokuAttendance result = kokuAttdCtrl.insertAttendance(kokuAttendance,
				conn);
		dbConn.closeConnection(conn);
		return result;
	}

	public Vector<FinishTime> StatusKedatangan(int status, String instituitionId)
			throws Exception {
		conn = dbConn.getConnection();
		Vector<FinishTime> result = attdCtrl.StatusKedatangan(status,
				instituitionId, conn);
		dbConn.closeConnection(conn);
		return result;
	}
	
	public Vector<FinishTime> StatusKedatanganRecord(int status, Date date, String instituitionId) throws Exception {
		conn = dbConn.getConnection();
		Vector<FinishTime> result = attdCtrl.StatusKedatanganRecord(status, date, instituitionId, conn);
		dbConn.closeConnection(conn);
		return result;
	}

	public int totalAttendMale(String classId) throws Exception {
		conn = dbConn.getConnection();
		int result = attdCtrl.totalAttendMale(classId, conn);
		dbConn.closeConnection(conn);
		return result;
	}

	public int totalAttendFemale(String classId) throws Exception {
		conn = dbConn.getConnection();
		int result = attdCtrl.totalAttendFemale(classId, conn);
		dbConn.closeConnection(conn);
		return result;
	}

	public int totalAbsentMale(String classId) throws Exception {
		conn = dbConn.getConnection();
		int result = attdCtrl.totalAbsentMale(classId, conn);
		dbConn.closeConnection(conn);
		return result;
	}

	public int totalAbsentFemale(String classId) throws Exception {
		conn = dbConn.getConnection();
		int result = attdCtrl.totalAbsentFemale(classId, conn);
		dbConn.closeConnection(conn);
		return result;
	}
	
	public int jumlahPelajar(String classId) throws Exception {
		conn = dbConn.getConnection();
		int result = attdCtrl.jumlahPelajar(classId, conn);
		dbConn.closeConnection(conn);
		return result;
	}
	
	public int jumlahKedatanganHadir(String classId) throws Exception {
		conn = dbConn.getConnection();
		int result = attdCtrl.jumlahKedatanganHadir(classId, conn);
		dbConn.closeConnection(conn);
		return result;
	}
	
	public int jumlahKedatanganTidakHadir(String classId) throws Exception {
		conn = dbConn.getConnection();
		int result = attdCtrl.jumlahKedatanganTidakHadir(classId, conn);
		dbConn.closeConnection(conn);
		return result;
	}

	public Vector<TeacherClass> TeacherList() throws Exception {
		conn = dbConn.getConnection();
		Vector<TeacherClass> result = teacherCtrl.TeacherList(conn);
		dbConn.closeConnection(conn);
		return result;
	}

	public Teacher getTeacherByClassroomId(String class_id) throws Exception {
		conn = dbConn.getConnection();
		Teacher result = teacherCtrl.getTeacherByClassroomId(class_id, conn);
		dbConn.closeConnection(conn);
		return result;
	}

	public Guardian getGuardianByStudentId(String student_id) throws Exception {
		conn = dbConn.getConnection();
		Guardian result = grdnCtrl.getGuardianByStudentId(student_id, conn);
		dbConn.closeConnection(conn);
		return result;
	}

	public Vector<Classroom> getClassBySchool(String teacher_id)
			throws Exception {
		conn = dbConn.getConnection();
		Vector<Classroom> result = classCtrl.getClassBySchool(teacher_id, conn);
		dbConn.closeConnection(conn);
		return result;
	}

	public int CheckFinishTime(String classId) throws Exception {
		conn = dbConn.getConnection();
		int result = attdCtrl.CheckFinishTime(classId, conn);
		dbConn.closeConnection(conn);
		return result;

	}

	public List<Religion> getReligion() throws Exception {
		conn = dbConn.getConnection();
		List<Religion> result = stuCtrl.getReligion(conn);
		dbConn.closeConnection(conn);
		return result;
	}

	public List<Race> getRace() throws Exception {
		conn = dbConn.getConnection();
		List<Race> result = stuCtrl.getRace(conn);
		dbConn.closeConnection(conn);
		return result;
	}

	public String insertStudent(Student student, String classId)
			throws Exception {
		conn = dbConn.getConnection();
		String result = stuCtrl.insertStudent(student, classId, conn);
		dbConn.closeConnection(conn);
		return result;
	}
	
	public String insertRecordedStudent(Student student, String classId, InputStream picture, int pictureStatus)
			throws Exception {
		conn = dbConn.getConnection();
		String result = stuCtrl.insertRecordedStudent(student, classId, picture, pictureStatus, conn);
		dbConn.closeConnection(conn);
		return result;
	}
	
	public String insertNewStudent(Student student, String classId, InputStream picture, int pictureStatus)
			throws Exception {
		conn = dbConn.getConnection();
		String result = stuCtrl.insertNewStudent(student, classId, picture, pictureStatus, conn);
		dbConn.closeConnection(conn);
		return result;
	}
	
	public String addNewStudentRegistration(Student student, String classId, InputStream picture, int pictureStatus)
			throws Exception {
		conn = dbConn.getConnection();
		String result = stuCtrl.addNewStudentRegistration(student, classId, picture, pictureStatus, conn);
		dbConn.closeConnection(conn);
		return result;
	}
	
	public String updateStudentRegistration(Student student, String classId, InputStream picture, int pictureStatus)
			throws Exception {
		conn = dbConn.getConnection();
		String result = stuCtrl.updateStudentRegistration(student, classId, picture, pictureStatus, conn);
		dbConn.closeConnection(conn);
		return result;
	}
	
	public String addStudentWithGuardianRegistration(Student student, Guardian guardian, String classId, InputStream picture, int pictureStatus)
			throws Exception {
		conn = dbConn.getConnection();
		String result = stuCtrl.addNewStudentWithGuardianRegistration(student, guardian, classId, picture, pictureStatus, conn);
		dbConn.closeConnection(conn);
		return result;
	}
	
	
	public String updateStudentWithGuardianRegistration(Student student, Guardian guardian, String classId, InputStream picture, int pictureStatus)
			throws Exception {
		conn = dbConn.getConnection();
		String result = stuCtrl.updateStudentWithGuardianRegistration(student, guardian, classId, picture, pictureStatus, conn);
		dbConn.closeConnection(conn);
		return result;
	}
	
	public String addStudentForRegistration(Student student, Guardian guardian, String classId, InputStream picture, int pictureStatus, ArrayList<GuardianContact> contacts)
			throws Exception {
		conn = dbConn.getConnection();
		String result = stuCtrl.addStudentForRegistration(student, guardian, classId, picture, pictureStatus, contacts, conn);
		dbConn.closeConnection(conn);
		return result;
	}
	

	public List<Classroom> getClassroomList(String classId) throws Exception {
		conn = dbConn.getConnection();
		List<Classroom> result = classCtrl.getClassroomList(classId, conn);
		dbConn.closeConnection(conn);
		return result;
	}

	public int insertStudentClass(String studentId, String classId)
			throws Exception {
		conn = dbConn.getConnection();
		int result = classCtrl.insertStudentClass(studentId, classId, conn);
		dbConn.closeConnection(conn);
		return result;
	}

	public int insertOriginalStudentClass(String classId) throws Exception {
		conn = dbConn.getConnection();
		int result = classCtrl.insertOriginalStudentClass(classId, conn);
		dbConn.closeConnection(conn);
		return result;
	}

	public String insertGuardian(Guardian guardian, String studentId,
			String relationship) throws Exception {
		conn = dbConn.getConnection();
		String result = grdnCtrl.insertGuardian(guardian, studentId,
				relationship, conn);
		dbConn.closeConnection(conn);
		return result;
	}
	
	public String addNewGuardianRegistration(Guardian guardian, String studentId,
			String relationship) throws Exception {
		conn = dbConn.getConnection();
		String result = grdnCtrl.addNewGuardianRegistration(guardian, studentId, relationship, conn);
		dbConn.closeConnection(conn);
		return result;
	}
	
	public String updateGuardianRegistration(Guardian guardian, String studentId,
			String relationship) throws Exception {
		conn = dbConn.getConnection();
		String result = grdnCtrl.updateGuardianRegistration(guardian, studentId, relationship, conn);
		dbConn.closeConnection(conn);
		return result;
	}
	
	public void deleteAllGuardianContactsRegistration(Guardian guardian) throws Exception{
		conn = dbConn.getConnection();
		grdnCtrl.deleteAllGuardianContactsRegistration(guardian, conn);
		dbConn.closeConnection(conn);
	}
	
	public void addNewGuardianContactRegistration(String guardian_id, String phoneNo, String provider, String subscription) throws Exception{
		conn = dbConn.getConnection();
		grdnCtrl.addNewGuardianContactRegistration(guardian_id, phoneNo, provider, subscription, conn);
		dbConn.closeConnection(conn);
	}
	
	public String addParentForRegistration(String studentId, Guardian guardian, String relationship, ArrayList<GuardianContact> contacts)
			throws Exception {
		conn = dbConn.getConnection();
		String result = grdnCtrl.addGuardianForRegistration(studentId, guardian, relationship, contacts, conn);
		dbConn.closeConnection(conn);
		return result;
	}

	public String getInstituitionByTeacherId(String teacherId) throws Exception {
		conn = dbConn.getConnection();
		String result = instCtrl.getInstituitionByTeacherId(teacherId, conn);
		dbConn.closeConnection(conn);
		return result;
	}

	public List<Report> MonthlyReportList(String month, String year,
			String classId) throws Exception {
		conn = dbConn.getConnection();
		List<Report> result = attdCtrl.MonthlyReportList(month, year, classId,
				conn);
		dbConn.closeConnection(conn);
		return result;
	}

	public AnalysisYearAttendance getAnalysisYearAttendance(String month,
			String year, String classId) throws Exception {
		conn = dbConn.getConnection();
		AnalysisYearAttendance result = attdCtrl.getAnalysisYearAttendance(
				month, year, classId, conn);
		dbConn.closeConnection(conn);
		return result;
	}

	public List<DailyAttendance> getAttendanceByDay(String month,
			String classId, String day) throws Exception {
		conn = dbConn.getConnection();
		List<DailyAttendance> result = attdCtrl.getAttendanceByDay(month,
				classId, day, conn);
		dbConn.closeConnection(conn);
		return result;
	}

	public String convertSymbol(String symbol) {
		String result = attdCtrl.convertSymbol(symbol);
		return result;
	}

	public int updateAttendance(String studentId, String classId, String day, String month,
			String year, String statusBefore, String statusAfter)
			throws Exception {
		conn = dbConn.getConnection();
		int result = attdCtrl.updateAttendance(studentId, classId, day, month, year,
				statusBefore, statusAfter, conn);
		dbConn.closeConnection(conn);
		return result;
	}

	public DailyAttendance getAttendanceMonthlyByStudent(String studentId, String classId,
			String month, String year) throws Exception {
		conn = dbConn.getConnection();
		DailyAttendance result = attdCtrl.getAttendanceMonthlyByStudent(
				studentId, classId, month, year, conn);
		dbConn.closeConnection(conn);
		return result;
	}

	public DailyAttendance getAttendanceMonthlyByDay(String studentId,
			String day, String month, String year) throws Exception {
		conn = dbConn.getConnection();
		DailyAttendance result = attdCtrl.getAttendanceMonthlyByDay(studentId,
				day, month, year, conn);
		dbConn.closeConnection(conn);
		return result;
	}

	public String convertMonthToMalay(String date) {
		String result = dateCtrl.convertMonthToMalay(date);
		return result;
	}

	public KokuType getKokuTypesByTypeId(String typeId) throws Exception {
		conn = dbConn.getConnection();
		KokuType result = kokuCtrl.getKokuTypesByTypeId(typeId, conn);
		dbConn.closeConnection(conn);
		return result;
	}

	public Koku getKokuByKokuId(String kokuId) throws Exception {
		conn = dbConn.getConnection();
		Koku result = kokuCtrl.getKokuByKokuId(kokuId, conn);
		dbConn.closeConnection(conn);
		return result;
	}

	public InstituitionKoku getInstKokuByInstKokuId(String instituitionKokuId)
			throws Exception {
		conn = dbConn.getConnection();
		InstituitionKoku result = kokuCtrl.getInstKokuByInstKokuId(
				instituitionKokuId, conn);
		dbConn.closeConnection(conn);
		return result;
	}

	public List<TeacherKoku> getTeacherKokuByTeacherId(String teacherId)
			throws Exception {
		conn = dbConn.getConnection();
		List<TeacherKoku> result = kokuCtrl.getTeacherKokuByTeacherId(
				teacherId, conn);
		dbConn.closeConnection(conn);
		return result;
	}

	public SkPosition getSkPositionByStudentKokuId(String studentKokuId)
			throws Exception {
		conn = dbConn.getConnection();
		SkPosition result = kokuCtrl.getSkPositionByStudentKokuId(
				studentKokuId, conn);
		dbConn.closeConnection(conn);
		return result;
	}

	public Position getPositionByPositionId(String positionId) throws Exception {
		conn = dbConn.getConnection();
		Position result = kokuCtrl.getPositionByPositionId(positionId, conn);
		dbConn.closeConnection(conn);
		return result;
	}

	public StudentKoku getStudentKokuByStudentKokuId(String studentKokuId)
			throws Exception {
		conn = dbConn.getConnection();
		StudentKoku result = kokuCtrl.getStudentKokuByStudentKokuId(
				studentKokuId, conn);
		dbConn.closeConnection(conn);
		return result;
	}

	public byte[] getPhoto(String iNumPhoto) throws Exception, SQLException {

		conn = dbConn.getConnection();
		byte[] result = imgCtrl.getPhoto(iNumPhoto, conn);
		dbConn.closeConnection(conn);
		return result;

	}
	
	public byte[] getPhotoPelajar(String iNumPhoto) throws Exception, SQLException {

		conn = dbConn.getConnection();
		byte[] result = imgCtrl.getPhotoPelajar(iNumPhoto, conn);
		dbConn.closeConnection(conn);
		return result;

	}

	public List<OutgoingSMS> getAllOutgoingSMSsByTeacher(String teacherId)
			throws Exception {
		conn = dbConn.getConnection();
		List<OutgoingSMS> result = osmsCtrl.getAllOutgoingSMSsByTeacher(
				teacherId, conn);
		dbConn.closeConnection(conn);
		return result;
	}
	
	// ADDED ON 08/05/2015 BY MOHAMAD IDZHAR BIN YA`AKUB
	// CREATE NEW FUNCTION RETRIEVE SMS SENT AND DELAY BY ACADEMIC INSTITUTION
	// ID AND TEACHER ID
	// CODE START
	public List<OutgoingSMS> getAllSentSMSsByTeacherId(String teacherId)
			throws Exception {
		conn = dbConn.getConnection();
		List<OutgoingSMS> result = osmsCtrl.getAllSentSMSsById(teacherId, conn);
		dbConn.closeConnection(conn);
		return result;
	}
	
	public List<OutgoingSMS> getAllDelaySMSsByTeacherId(String teacherId)
			throws Exception {
		conn = dbConn.getConnection();
		List<OutgoingSMS> result = osmsCtrl.getAllDelaySMSsById(teacherId, conn);
		dbConn.closeConnection(conn);
		return result;
	}
	// CODE END

	/*public List<AuditDailyAttendance> getAuditAttendance(String classId,
			String month) throws Exception {
		conn = dbConn.getConnection();
		List<AuditDailyAttendance> result = attdCtrl.getAuditAttendance(
				classId, month, conn);
		dbConn.closeConnection(conn);
		return result;
	}*/
	
	public List<AuditDailyAttendance> getAuditAttendance(String classId,
			String month, String year) throws Exception {
		conn = dbConn.getConnection();
		List<AuditDailyAttendance> result = attdCtrl.getAuditAttendance(
				classId, month, year, conn);
		dbConn.closeConnection(conn);
		return result;
}


	public List<DailyAttendance> getAbsentStudentList(String classId)
			throws Exception {
		conn = dbConn.getConnection();
		List<DailyAttendance> result = attdCtrl.getAbsentStudentList(classId,
				conn);
		dbConn.closeConnection(conn);
		return result;
	}

	public String verifyAttendanceTime(String classId ,String day ,String month ,String year) throws Exception{
		conn = dbConn.getConnection();
		String result = attdCtrl.verifyAttendanceTime(classId , day , month , year, conn);
		dbConn.closeConnection(conn);
		return result;
		
	}
	
	public List<SchoolAttendance> jumlahKedatanganBulananSekolah(String classId, String month, String year) throws Exception{
		conn = dbConn.getConnection();
		List<SchoolAttendance> result = attdCtrl.jumlahKedatanganBulananSekolah(classId , month , year, conn);
		dbConn.closeConnection(conn);
		return result;
	}
	
	public int jumlahHadirPerempuan2(String teacherId, String day, String month, String year) throws Exception{
		conn = dbConn.getConnection();
		int result = attdCtrl.jumlahHadirPerempuan2(teacherId , day , month , year, conn);
		dbConn.closeConnection(conn);
		return result;
	}
	
	public int jumlahHadirSekolah(String teacherId, String day, String month, String year) throws Exception{
		conn = dbConn.getConnection();
		int result = attdCtrl.jumlahHadirSekolah(teacherId , day , month , year, conn);
		dbConn.closeConnection(conn);
		return result;
	}
	
	public int getHadirKelas(String status, String teacherId) throws Exception{
		conn = dbConn.getConnection();
		int result = attdCtrl.getHadirKelas(status, teacherId , conn);
		dbConn.closeConnection(conn);
		return result;
	}
	
	public int getHadirKelasJantina(String status, String teacherId, String gender) throws Exception{
		conn = dbConn.getConnection();
		int result = attdCtrl.getHadirKelasJantina(status, teacherId , gender, conn);
		dbConn.closeConnection(conn);
		return result;
	}
	
	public String getTodayDate() throws Exception{
		conn = dbConn.getConnection();
		String result = dateCtrl.getTodayDate(conn);
		dbConn.closeConnection(conn);
		return result;
	}
	
	public String getTodayDate2() throws Exception{
		conn = dbConn.getConnection();
		String result = dateCtrl.getTodayDate2(conn);
		dbConn.closeConnection(conn);
		return result;
	}
	
	public List<AttendanceByRace> getAttendanceByRace(String academicInstituitionId, String year) throws Exception{
		conn = dbConn.getConnection();
		List<AttendanceByRace> result = attdCtrl.getAttendanceByRace(academicInstituitionId,year,conn);
		dbConn.closeConnection(conn);
		return result;
	}
	
	public int getHitungPanjangKedatangan(String academicInstituitionId, String monthYear) throws Exception{
		conn = dbConn.getConnection();
		int result = attdCtrl.getHitungPanjangKedatangan(academicInstituitionId, monthYear, conn);
		dbConn.closeConnection(conn);
		return result;
	}
	
	public int attendanceAccess(String classId, String day, String monthYear) throws Exception{
		conn = dbConn.getConnection();
		int result = attdCtrl.attendanceAccess(classId, day, monthYear, conn);
		dbConn.closeConnection(conn);
		return result;
	}
	
	public int attendanceAccessStudent(String studentId, String classId, String day , String monthYear) throws Exception{
		conn = dbConn.getConnection();
		int result = attdCtrl.attendanceAccessStudent(studentId, classId, day, monthYear, conn);
		dbConn.closeConnection(conn);
		return result;
	}
	
	public int compareDate(String day, String monthYear) throws Exception{
		conn = dbConn.getConnection();
		int result = attdCtrl.compareDate(day, monthYear, conn);
		dbConn.closeConnection(conn);
		return result;
	}
	
	public AttendanceStatistics getAttendanceStatisticsByClassId(String classId, String attendanceDate) throws Exception{
		conn = dbConn.getConnection();
		AttendanceStatistics result = attdCtrl.getAttendanceStatisticsByClassId(classId, attendanceDate, conn);
		dbConn.closeConnection(conn);
		return result;
	}
	
	public List<AttendanceStatistics> getAttendanceStatistics(String instituitionId, String attendanceDate) throws Exception{
		conn = dbConn.getConnection();
		List<AttendanceStatistics> result = attdCtrl.getAttendanceStatistics(instituitionId, attendanceDate, conn);
		dbConn.closeConnection(conn);
		return result;
	}
	
	public List<AttendanceStatistics> getAttendanceStatisticsByTahap(String instituitionId, String attendanceDate, int tahap) throws Exception{
		conn = dbConn.getConnection();
		List<AttendanceStatistics> result = attdCtrl.getAttendanceStatisticsByTahap(instituitionId, attendanceDate, tahap, conn);
		dbConn.closeConnection(conn);
		return result;
	}
	
	public AttendanceStatistics getTotalAttendanceStatistics(String instituitionId, String attendanceDate) throws Exception{
		conn = dbConn.getConnection();
		AttendanceStatistics result = attdCtrl.getTotalAttendanceStatistics(instituitionId, attendanceDate, conn);
		dbConn.closeConnection(conn);
		return result;
	}
	
	public AttendanceStatistics getTotalAttendanceStatisticsByTahap(String instituitionId, String attendanceDate, int tahap) throws Exception{
		conn = dbConn.getConnection();
		AttendanceStatistics result = attdCtrl.getTotalAttendanceStatisticsByTahap(instituitionId, attendanceDate, tahap, conn);
		dbConn.closeConnection(conn);
		return result;
	}
	
	public void updateTotalDay(String classId) throws Exception{
		conn = dbConn.getConnection();
		attdCtrl.updateTotalDay(classId,conn);
		dbConn.closeConnection(conn);
	}
	
	public List<Student> getStudentsByInstituitionId(String instituitionId) throws Exception{
		conn = dbConn.getConnection();
		List<Student> result = stuCtrl.getStudentsByInstituitionId(instituitionId, conn);
		dbConn.closeConnection(conn);
		return result;
	}
	
	//for select list performance purposes
	public List<Student> getStudentsByInstituitionId2(String instituitionId) throws Exception{
		conn = dbConn.getConnection();
		List<Student> result = stuCtrl.getStudentsByInstituitionId2(instituitionId, conn);
		dbConn.closeConnection(conn);
		return result;
	}
	
	public List<Instituition> getAllInstituitionByPPD(String ppd) throws Exception{
		conn = dbConn.getConnection();
		List<Instituition> result = instCtrl.getAllInstituitionByPPD(ppd, conn);
		dbConn.closeConnection(conn);
		return result;
	}
	
	public List<Instituition> getAllInstitutionsByStationId(int station_id, int jenis) throws Exception{
		conn = dbConn.getConnection();
		List<Instituition> result = instCtrl.getAllInstitutionsByStationId(station_id, jenis, conn);
		dbConn.closeConnection(conn);
		return result;
	}
	
	public List<Instituition> getAllInstitutionsByDunId(int dun_id, int jenis) throws Exception{
		conn = dbConn.getConnection();
		List<Instituition> result = instCtrl.getAllInstitutionsByDunId(dun_id, jenis, conn);
		dbConn.closeConnection(conn);
		return result;
	}
	
	public int getSchoolAttendancePercentage(String instituitionId) throws Exception{
		conn = dbConn.getConnection();
		int result = attdCtrl.getSchoolAttendancePercentage(instituitionId, conn);
		dbConn.closeConnection(conn);
		return result;
	}
	
	public void updateGuardianContact(String contactId, String phoneNo) throws Exception{
		conn = dbConn.getConnection();
		grdnCtrl.updateGuardianContact(contactId,phoneNo, conn);
		dbConn.closeConnection(conn);
	}
	
	public List<Instituition> getPPDSekolahByJenis(String ppd, int jenis) throws Exception{
		conn = dbConn.getConnection();
		List<Instituition> result = instCtrl.getPPDSekolahByJenis(ppd, jenis, conn);
		dbConn.closeConnection(conn);
		return result;
	}
	

	public PpdAttendance ppdReport(String instituitionId) throws Exception {
		conn = dbConn.getConnection();
		PpdAttendance result = attdCtrl.ppdReport(instituitionId, conn);
		dbConn.closeConnection(conn);
		return result;
	}
	
	public PpdAttendance ppdReportRecord(String instituitionId, Date date) throws Exception {
		conn = dbConn.getConnection();
		PpdAttendance result = attdCtrl.ppdReportRecord(instituitionId, date, conn);
		dbConn.closeConnection(conn);
		return result;
	}

	public PpdAttendance jpnReport(String city) throws Exception {
		conn = dbConn.getConnection();
		PpdAttendance result = attdCtrl.jpnReport(city, conn);
		dbConn.closeConnection(conn);
		return result;

	}
	
	public PpdAttendance jpnReportRecord(String city, Date date) throws Exception {
		conn = dbConn.getConnection();
		PpdAttendance result = attdCtrl.jpnReportRecord(city, date, conn);
		dbConn.closeConnection(conn);
		return result;

	}

	public PpdAttendance jpnPpdReport(String vppd) throws Exception {
		conn = dbConn.getConnection();
		PpdAttendance result = attdCtrl.jpnPpdReport(vppd, conn);
		dbConn.closeConnection(conn);
		return result;
	}
	
	public PpdAttendance jpnPpdReportRecord(String vppd, Date date) throws Exception {
		conn = dbConn.getConnection();
		PpdAttendance result = attdCtrl.jpnPpdReportRecord(vppd, date, conn);
		dbConn.closeConnection(conn);
		return result;
	}
	
	public PpdAttendance policeReport(int stationId) throws Exception {
		conn = dbConn.getConnection();
		PpdAttendance result = attdCtrl.policeReport(stationId, conn);
		dbConn.closeConnection(conn);
		return result;
	}
	
	public PpdAttendance policeReportRecord(int stationId, Date date) throws Exception {
		conn = dbConn.getConnection();
		PpdAttendance result = attdCtrl.policeReportRecord(stationId, date, conn);
		dbConn.closeConnection(conn);
		return result;
	}
	
	public PpdAttendance politicianReport(int dunId) throws Exception {
		conn = dbConn.getConnection();
		PpdAttendance result = attdCtrl.politicianReport(dunId, conn);
		dbConn.closeConnection(conn);
		return result;
	}
	
	public PpdAttendance politicianReportRecord(int dunId, Date date) throws Exception {
		conn = dbConn.getConnection();
		PpdAttendance result = attdCtrl.politicianReportRecord(dunId, date, conn);
		dbConn.closeConnection(conn);
		return result;
	}

	public List<Instituition> getPpdByCity(String city) throws Exception {
		conn = dbConn.getConnection();
		List<Instituition> result = instCtrl.getPpdByCity(city, conn);
		dbConn.closeConnection(conn);
		return result;
	}
	
	public int studentTransfer(String studentId, Date transferDate) throws Exception{
		conn = dbConn.getConnection();
		int result = stuCtrl.studentTransfer(studentId, transferDate, conn);
		dbConn.closeConnection(conn);
		return result;
	}
	
	public List<TransferStudent> allTransferStudent() throws Exception{
		conn = dbConn.getConnection();
		List<TransferStudent> result = stuCtrl.allTransferStudent(conn);
		dbConn.closeConnection(conn);
		return result;
	}
	
	public int studentTransferProcess(String studentId, Date transferDate, String classId) throws Exception{
		conn = dbConn.getConnection();
		int result = stuCtrl.studentTransferProcess(studentId,transferDate,classId,conn);
		dbConn.closeConnection(conn);
		return result;
	}
	
	public int dailyattendancecarryforwstat(String studentId , String classId) throws Exception{
		conn = dbConn.getConnection();
		int result = attdCtrl.dailyattendancecarryforwstat(studentId, classId, conn);
		dbConn.closeConnection(conn);
		return result;
	}
	
	public int dailyattendancecarryforwstat2(String classId) throws Exception{
		conn = dbConn.getConnection();
		int result = attdCtrl.dailyattendancecarryforwstat2(classId, conn);
		dbConn.closeConnection(conn);
		return result;
	}
	
	public int updateInstitution(Instituition institution, InputStream logo) throws SQLException, Exception {
		conn = dbConn.getConnection();
		int result = instCtrl.updateInstitution(institution, logo, conn);
		dbConn.closeConnection(conn);
		return result;
	}
	
	public int updateTeacher(Teacher teacher) throws SQLException, Exception {
		conn = dbConn.getConnection();
		int result = teacherCtrl.updateTeacher(teacher, conn);
		dbConn.closeConnection(conn);
		return result;
	}
	
	public int updateStudent(Student student) throws SQLException, Exception {
		conn = dbConn.getConnection();
		int result = stuCtrl.updatestudent(student, conn);
		dbConn.closeConnection(conn);
		return result;
	}
	
	public Vector<Topic> getAllTopicMain() throws Exception{
		conn = dbConn.getConnection();
		Vector<Topic> result = topicCtrl.getAllTopicsMain(conn);
		dbConn.closeConnection(conn);
		return result;
	}
	
	public Vector<Topic> getAllTopicByUser(Teacher teacher) throws Exception{
		conn = dbConn.getConnection();
		Vector<Topic> result2 = topicCtrl.getAllTopicByUser(teacher, conn);
		dbConn.closeConnection(conn);
		return result2;
	}
	
	public Topic getTopicById(Topic topic) throws Exception{
		conn = dbConn.getConnection();
		Topic result = topicCtrl.getTopicByIdFunc(topic, conn);
		dbConn.closeConnection(conn);
		return result;
	}
	
	public Vector<Discussion> getAllDiscussionMain(Topic topic) throws Exception{
		conn = dbConn.getConnection();
		Vector<Discussion> result = discussionCtrl.getAllDiscussionsMain(topic, conn);
		dbConn.closeConnection(conn);
		return result;
	}
	
	public List<WarningHistory> getAllWarningHistory(String studentId) throws Exception {
		conn = dbConn.getConnection();
		List<WarningHistory> result = wHCtrl.getAllWarningHistory(studentId, conn);
		dbConn.closeConnection(conn);
		return result;
	}
	//police
		public Police getPoliceById(String police_id) throws Exception {
			conn = dbConn.getConnection();
			Police result = policeCtrl.getPoliceById(police_id, conn);
			dbConn.closeConnection(conn);
			return result;
		}
		
		public int checkPoliceId(String police_id) throws Exception {
			
			conn = dbConn.getConnection();
			int result = policeCtrl.checkPoliceId(police_id, conn);
			dbConn.closeConnection(conn);
			
			return result;
		}
		
		public Police getPoliceByUserId(String user_id) throws Exception {
			
			conn = dbConn.getConnection();
			Police result = policeCtrl.getPoliceByUserId(user_id, conn);
			dbConn.closeConnection(conn);
			
			return result;
		}
		
		public ArrayList<Police> getPolicesByStationId(int station_id) throws Exception {
			
			conn = dbConn.getConnection();
			ArrayList<Police> result = policeCtrl.getPolicesByStationId(station_id, conn);
			dbConn.closeConnection(conn);
			
			return result;
		}
		
		public int updatePoliceContact(Police police) throws Exception {
			
			conn = dbConn.getConnection();
			int result = policeCtrl.updatePoliceContact(police, conn);
			
			return result;
		}
		
		//station
		
		public Station getStationByPoliceId(String police_id) throws Exception {
			
			conn = dbConn.getConnection();
			Station result = stationCtrl.getStationByPoliceId(police_id, conn);
			dbConn.closeConnection(conn);
			
			return result;
		}
		
		public Station getStationByStationId(String station_id) throws Exception {
			
			conn = dbConn.getConnection();
			Station result = stationCtrl.getStationByStationId(station_id, conn);
			dbConn.closeConnection(conn);
			
			return result;
		}
		public Station getStationByInstitutionId(String institution_id) throws Exception {
					
					conn = dbConn.getConnection();
					Station result = stationCtrl.getStationByInstitutionId(institution_id, conn);
					dbConn.closeConnection(conn);
					
					return result;
				}
		public Station getStationByClassId(String class_id) throws Exception {
			
			conn = dbConn.getConnection();
			Station result = stationCtrl.getStationByClassId(class_id, conn);
			dbConn.closeConnection(conn);
			
			return result;
		}
		public Station getStationByStudentId(String student_id) throws Exception {
			
			conn = dbConn.getConnection();
			Station result = stationCtrl.getStationByStudentId(student_id, conn);
			dbConn.closeConnection(conn);
			
			return result;
		}
		
		public ArrayList<Station> getAllPoliceStation() throws Exception {
			
			conn = dbConn.getConnection();
			ArrayList<Station> result = stationCtrl.getAllPoliceStation(conn);
			dbConn.closeConnection(conn);
			
			return result;
		}
		
		//politician
		public Politician getPoliticianById(String politician_id) throws Exception {
			
			conn = dbConn.getConnection();
			Politician result = politicianCtrl.getPoliticianById(politician_id, conn);
			dbConn.closeConnection(conn);
		
			return result;
		
		}
		
		public int checkPoliticianId(String politician_id) throws Exception {
				
			conn = dbConn.getConnection();
			int result = politicianCtrl.checkPoliticianId(politician_id, conn);
			dbConn.closeConnection(conn);
				
			return result;
		}
			
		public Politician getPoliticianByUserId(String user_id) throws Exception {
				
			conn = dbConn.getConnection();
			Politician result = politicianCtrl.getPoliticianByUserId(user_id, conn);
			dbConn.closeConnection(conn);
				
			return result;
		}
		
		public int updatePoliticianContact(Politician politician) throws Exception {
			
			conn = dbConn.getConnection();
			int result = politicianCtrl.updatePoliticianContact(politician, conn);
			
			return result;
		}
		
		// dun
		public Dun getDunByPoliticianId(String politician_id) throws Exception {
			
			conn = dbConn.getConnection();
			Dun result = dunCtrl.getDunByPoliticianId(politician_id, conn);
			dbConn.closeConnection(conn);
			
			return result;
		}
		
		public Dun getDunByDunId(String dun_id) throws Exception {
			
			conn = dbConn.getConnection();
			Dun result = dunCtrl.getDunByDunId(dun_id, conn);
			dbConn.closeConnection(conn);
			
			return result;
		}
		
		public Dun getDunByInstitutionId(String institution_id) throws Exception {
			
			conn = dbConn.getConnection();
			Dun result = dunCtrl.getDunByInstitutionId(institution_id, conn);
			dbConn.closeConnection(conn);
			
			return result;
		}
		public Dun getDunByClassId(String class_id) throws Exception {
					
					conn = dbConn.getConnection();
					Dun result = dunCtrl.getDunByClassId(class_id, conn);
					dbConn.closeConnection(conn);
					
					return result;
				}
		public Dun getDunByStudentId(String student_id) throws Exception {
			
			conn = dbConn.getConnection();
			Dun result = dunCtrl.getDunByStudentId(student_id, conn);
			dbConn.closeConnection(conn);
			
			return result;
		}


		public ArrayList<Dun> getAllDun() throws Exception {
			
			conn = dbConn.getConnection();
			ArrayList<Dun> result = dunCtrl.getAllDun(conn);
			dbConn.closeConnection(conn);
			
			return result;
		}
		
		
		
		// parlimen
		public Parlimen getParlimenByDunId(int dun_id) throws Exception {
			
			conn = dbConn.getConnection();
			Parlimen parlimen = parlimenCtrl.getParlimenByDunId(dun_id, conn);
			dbConn.closeConnection(conn);
			
			return parlimen;
		}
		
		public String sendNotificationMulti(List<Message> messages, String sender) throws Exception {
			conn = dbConn.getConnection();
			String result = ns.sendNotificationMulti(messages, sender);
			dbConn.closeConnection(conn);
			return result;
		}
}
