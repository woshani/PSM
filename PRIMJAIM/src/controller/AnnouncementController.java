package controller;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import oracle.jdbc.OracleTypes;
import bean.Announcement;
import bean.Classroom;
import bean.Dun;
import bean.Guardian;
import bean.GuardianContact;
import bean.Instituition;
import bean.Message;
import bean.Police;
import bean.Politician;
import bean.Station;
import bean.Student;

public class AnnouncementController {
	/*
	 * Declaration of variables
	 */
	private UserController userCtrl = new UserController();
	private StudentController stuCtrl = new StudentController();
	private ClassroomController classCtrl = new ClassroomController();
	private InstituitionController instCtrl = new InstituitionController();
	private GuardianController grdnCtrl = new GuardianController();
	private StationController stationCtrl = new StationController();
	private PoliceController policeCtrl = new PoliceController();
	private DunController dunCtrl = new DunController();
	private PoliticianController politicianCtrl = new PoliticianController();

	public int checkAnnouncementId(String announcement_id, Connection conn)
			throws Exception {
		// define sql statement
		String sql = "{ call ? := checkAnnouncementId(?)}";

		// create prepared statement
		CallableStatement cs = conn.prepareCall(sql);

		// set prepared statement's parameters
		cs.registerOutParameter(1, OracleTypes.NUMBER);
		cs.setString(2, announcement_id);

		cs.execute();
		/*
		 * execute the query on prepared statement and create result set to keep
		 * result
		 */

		/*
		 * while reading result from result set, set result information to
		 * object
		 */
		int status = cs.getInt(1);

		return status;
	}

	public List<Announcement> getAllAnnouncements(Connection conn)
			throws Exception {
		// List to keep results retrieved from database
		List<Announcement> announcements = new ArrayList<Announcement>();

		// define sql statement
		String sql = "{ call ? := getAllAnnouncement() }";

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
			Announcement announcement = new Announcement();

			announcement.setAnnouncement_id(rs.getString(1));
			announcement.setAnnouncer(userCtrl.getUserById(rs.getString(2),
					conn));
			announcement.setSubject(rs.getString(3));
			announcement.setText(rs.getString(4));
			announcement.setType(rs.getString(6));
			if (announcement.getType().equals("S")) {
				announcement.setTarget(stuCtrl.getStudentById(rs.getString(5),
						conn));
			} else if (announcement.getType().equals("C")) {
				announcement.setTarget(classCtrl.getClassroomById(
						rs.getString(5), conn));
			} else if (announcement.getType().equals("I")) {
				announcement.setTarget(instCtrl.getInstituitionById(
						rs.getString(5), conn));
			}
			announcement.setDatetime(rs.getTimestamp(7));
			announcement.setStatus(rs.getString(8));

			// add object to List
			announcements.add(announcement);
		}

		return announcements;
	}

	public List<Announcement> getAllSentAnnouncements(Connection conn)
			throws Exception {
		// List to keep results retrieved from database
		List<Announcement> announcements = new ArrayList<Announcement>();

		// define sql statement
		String sql = "{ call ? := getAllSentAnnouncements()}";

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
			Announcement announcement = new Announcement();

			announcement.setAnnouncement_id(rs.getString(1));
			announcement.setAnnouncer(userCtrl.getUserById(rs.getString(2),
					conn));
			announcement.setSubject(rs.getString(3));
			announcement.setText(rs.getString(4));
			announcement.setType(rs.getString(6));
			if (announcement.getType().equals("S")) {
				announcement.setTarget(stuCtrl.getStudentById(rs.getString(5),
						conn));
			} else if (announcement.getType().equals("C")) {
				announcement.setTarget(classCtrl.getClassroomById(
						rs.getString(5), conn));
			} else if (announcement.getType().equals("I")) {
				announcement.setTarget(instCtrl.getInstituitionById(
						rs.getString(5), conn));
			}
			announcement.setDatetime(rs.getTimestamp(7));
			announcement.setStatus(rs.getString(8));

			// add object to List
			announcements.add(announcement);
		}

		return announcements;
	}

	// Modified on 07/05/2015 Enhance Announcement Modul
	public List<Announcement> getAllSentAnnouncementsById(String teacherId,
			Connection conn) throws Exception {
		// List to keep results retrieved from database
		List<Announcement> announcements = new ArrayList<Announcement>();

		// define sql statement
		String sql = "{ call ? := GETALLSENTANNOUNCEMENT(?)}";

		// create prepared statement
		CallableStatement cs = conn.prepareCall(sql);

		cs.registerOutParameter(1, OracleTypes.CURSOR);
		cs.setString(2, teacherId);

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
			Announcement announcement = new Announcement();

			announcement.setAnnouncement_id(rs.getString(1));
			announcement.setAnnouncer(userCtrl.getUserById(rs.getString(2),
					conn));
			announcement.setSubject(rs.getString(3));
			announcement.setText(rs.getString(4));
			announcement.setType(rs.getString(6));

			if (announcement.getType().equals("S")) {
				announcement.setTarget(stuCtrl.getStudentById(rs.getString(5),
						conn));
			} else if (announcement.getType().equals("C")) {
				announcement.setTarget(classCtrl.getClassroomById(
						rs.getString(5), conn));
			} else if (announcement.getType().equals("I")) {
				announcement.setTarget(instCtrl.getInstituitionById(
						rs.getString(5), conn));
			}
			announcement.setDatetime(rs.getTimestamp(7));
			announcement.setStatus(rs.getString(8));
			announcement.setName(rs.getString(9));

			// add object to List
			announcements.add(announcement);
		}

		return announcements;
	}

	// Modified on 07/05/2015 Enhance Announcement Modul
	public List<Announcement> getAllDelayAnnouncementsById(String teacherId,
			Connection conn) throws Exception {
		// List to keep results retrieved from database
		List<Announcement> announcements = new ArrayList<Announcement>();

		// define sql statement
		String sql = "{ call ? := GETALLDELAYANNOUNCEMENT(?)}";

		// create prepared statement
		CallableStatement cs = conn.prepareCall(sql);

		cs.registerOutParameter(1, OracleTypes.CURSOR);
		cs.setString(2, teacherId);

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
			Announcement announcement = new Announcement();

			announcement.setAnnouncement_id(rs.getString(1));
			announcement.setAnnouncer(userCtrl.getUserById(rs.getString(2),
					conn));
			announcement.setSubject(rs.getString(3));
			announcement.setText(rs.getString(4));
			announcement.setType(rs.getString(6));
			if (announcement.getType().equals("S")) {
				announcement.setTarget(stuCtrl.getStudentById(rs.getString(5),
						conn));
			} else if (announcement.getType().equals("C")) {
				announcement.setTarget(classCtrl.getClassroomById(
						rs.getString(5), conn));
			} else if (announcement.getType().equals("I")) {
				announcement.setTarget(instCtrl.getInstituitionById(
						rs.getString(5), conn));
			}
			announcement.setDatetime(rs.getTimestamp(7));
			announcement.setStatus(rs.getString(8));
			announcement.setName(rs.getString(9));

			// add object to List
			announcements.add(announcement);
		}

		return announcements;
	}

	public Announcement getAnnouncementById(String announcement_id,
			Connection conn) throws Exception {
		// Object to keep results retrieved from database
		Announcement announcement = new Announcement();

		// define sql statement
		String sql = "{ call ? := getAnnouncementById(?)}";

		// create prepared statement
		CallableStatement cs = conn.prepareCall(sql);

		// set prepared statement's parameters
		cs.registerOutParameter(1, OracleTypes.CURSOR);
		cs.setString(2, announcement_id);

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
			announcement.setAnnouncement_id(rs.getString(1));
			announcement.setAnnouncer(userCtrl.getUserById(rs.getString(2),
					conn));
			announcement.setSubject(rs.getString(3));
			announcement.setText(rs.getString(4));
			announcement.setType(rs.getString(6));
			if (announcement.getType().equals("S")) {
				announcement.setTarget(stuCtrl.getStudentById(rs.getString(5),
						conn));
			} else if (announcement.getType().equals("C")) {
				announcement.setTarget(classCtrl.getClassroomById(
						rs.getString(5), conn));
			} else if (announcement.getType().equals("I")) {
				announcement.setTarget(instCtrl.getInstituitionById(
						rs.getString(5), conn));
			} else if (announcement.getType().equals("P")) {
				announcement.setTarget(stationCtrl.getStationByStationId(
						rs.getString(5), conn));
			} else if (announcement.getType().equals("D")) {
				announcement.setTarget(dunCtrl.getDunByDunId(rs.getString(5),
						conn));
			}
			announcement.setDatetime(rs.getTimestamp(7));
			announcement.setStatus(rs.getString(8));
		}

		return announcement;
	}

	public int addNewAnnouncement(Announcement announcement, Connection conn)
			throws Exception {

		// define sql statement
		String sql = "{ ? = call insert_announcement (?,?,?,?,?) }";

		// create callable statement
		CallableStatement cs = conn.prepareCall(sql);

		// set callable statement's parameters
		cs.registerOutParameter(1, java.sql.Types.INTEGER);
		cs.setString(2, announcement.getAnnouncer().getUser_id());
		cs.setString(3, announcement.getSubject());
		cs.setString(4, announcement.getText());

		String type = announcement.getType();

		Student student = new Student();
		Classroom classroom = new Classroom();
		Instituition instituition = new Instituition();
		Station station = new Station();
		Dun dun = new Dun();

		if (type.equals("I")) {
			instituition = (Instituition) announcement.getTarget();
			cs.setString(5, instituition.getAcademic_instituition_id());
		} else if (type.equals("C")) {
			classroom = (Classroom) announcement.getTarget();
			cs.setString(5, classroom.getClass_id());
		} else if (type.equals("S")) {
			student = (Student) announcement.getTarget();
			cs.setString(5, student.getStudent_id());
		} else if (type.equals("P")) {
			station = (Station) announcement.getTarget();
			cs.setInt(5, station.getId());
		} else if (type.equals("D")) {
			dun = (Dun) announcement.getTarget();
			cs.setInt(5, dun.getId());
		}
		cs.setString(6, announcement.getType());

		// execute the query on prepared statement
		cs.execute();

		// get returned id from function
		int status = cs.getInt(1);

		// if sql statement successfully committed
		if (status > 0) {
			return 1;
		} else {
			// sql statement failed
			return -1;
		}
	}

	public int updateAnnouncement(Announcement announcement, Connection conn)
			throws Exception {

		// define sql statement
		String sql = "{ ? = call update_announcement (?,?,?,?,?,?) }";

		// create callable statement
		CallableStatement cs = conn.prepareCall(sql);

		// set callable statement's parameters
		cs.registerOutParameter(1, java.sql.Types.INTEGER);
		cs.setString(2, announcement.getAnnouncement_id());
		cs.setString(3, announcement.getAnnouncer().getUser_id());
		cs.setString(4, announcement.getSubject());
		cs.setString(5, announcement.getText());

		String type = announcement.getType();
		Student student = new Student();
		Classroom classroom = new Classroom();
		Instituition instituition = new Instituition();
		if (type.equals("S")) {
			student = (Student) announcement.getTarget();
			cs.setString(6, student.getStudent_id());
		} else if (type.equals("C")) {
			classroom = (Classroom) announcement.getTarget();
			cs.setString(6, classroom.getClass_id());
		} else if (type.equals("I")) {
			instituition = (Instituition) announcement.getTarget();
			cs.setString(6, instituition.getAcademic_instituition_id());
		}
		cs.setString(7, announcement.getType());

		// execute the query on prepared statement
		cs.execute();

		// get returned id from function
		int status = cs.getInt(1);

		// if sql statement successfully committed
		if (status > 0) {
			return 1;
		} else {
			// sql statement failed
			return -1;
		}
	}

	public int deleteAnnouncement(Announcement announcement, Connection conn)
			throws Exception {

		// define sql statement
		String sql = "{ ? = call delete_announcement(?) }";

		// create callable statement
		CallableStatement cs = conn.prepareCall(sql);

		// set callable statement's parameters
		cs.registerOutParameter(1, java.sql.Types.INTEGER);
		cs.setInt(2, Integer.parseInt(announcement.getAnnouncement_id()));

		// execute the query on prepared statement
		cs.execute();

		// get returned id from function
		int status = cs.getInt(1);

		// if sql statement successfully committed
		if (status > 0) {
			return 1;
		} else {
			// sql statement failed
			return -1;
		}
	}

	public List<Message> getMessagesByAnnouncements(
			List<Announcement> announcements, Connection conn) throws Exception {
		List<Message> messages = new ArrayList<Message>();
		for (Announcement announcement : announcements) {
			List<Guardian> guardians = new ArrayList<Guardian>();
			ArrayList<Police> polices = new ArrayList<Police>();
			ArrayList<Politician> politicians = new ArrayList<Politician>();

			if (announcement.getType().equals("A")) {
				guardians = grdnCtrl.getAllGuardians(conn);
			} else if (announcement.getType().equals("S")) {
				Student student = (Student) announcement.getTarget();
				guardians = grdnCtrl.getGuardiansByStudentId2(student.getStudent_id(), conn);
			} else if (announcement.getType().equals("C")) {

				/*
				 * Classroom classroom = (Classroom) announcement.getTarget();
				 * List<Student> students = stuCtrl.getStudentsByClassId(
				 * classroom.getClass_id(), conn);
				 * 
				 * for(Student student : students) {
				 * guardians.addAll(grdnCtrl.getGuardiansByStudentId(
				 * student.getStudent_id(), conn)); }
				 */

				/*
				 * Tuning up slow process : Mohamad Idzhar (24/10/2017)
				 * add process get guardiansbyclassid
				 */
				Classroom classroom = (Classroom) announcement.getTarget();

				guardians.addAll(grdnCtrl.getGuardiansByClassId(
						classroom.getClass_id(), conn));
			}

			/*
			 * Problem found on 24/10/2017 : Mohamad Idzhar ORACLE error :
			 * maximum open cursor exceed error suspect too many connection to
			 * db (institution get >> class >> student >> get guardian) default
			 * open cursor on PRACLE DB set is 10 000 db manual update status is
			 * working. table messages get the data. solution aim get guardians
			 */
			else if (announcement.getType().equals("I")) {

				/*
				 * Instituition instituition = (Instituition)
				 * announcement.getTarget(); List<Classroom> classrooms =
				 * classCtrl.getClassroomsByInstituitionId(
				 * instituition.getAcademic_instituition_id(), conn);
				 * 
				 * for(Classroom classroom : classrooms) {
				 * 
				 * List<Student> students = stuCtrl.getStudentsByClassId(
				 * classroom.getClass_id(), conn);
				 * 
				 * for(Student student : students) {
				 * guardians.addAll(grdnCtrl.getGuardiansByStudentId(
				 * student.getStudent_id(), conn)); } }
				 */

				// solution 1 = get guardians by institution id
				Instituition instituition = (Instituition) announcement
						.getTarget();

				guardians.addAll(grdnCtrl.getGuardiansByInstitutionId(
						instituition.getAcademic_instituition_id(), conn));

			} else if (announcement.getType().equals("P")) {

				Station station = (Station) announcement.getTarget();
				polices = policeCtrl.getPolicesByStationId(station.getId(),
						conn);

			} else if (announcement.getType().equals("D")) {
				Dun dun = (Dun) announcement.getTarget();
				politicians = politicianCtrl.getPoliticiansByDunId(dun.getId(),
						conn);

			}

			// merge message with tel no
			if (announcement.getType().equals("P")) {
				for (Police police : polices) {

					if (police.getPhoneNo() != null) {
						Message message = new Message();
						message.setMessage_id(announcement.getAnnouncement_id());
						message.setReceiver("6" + police.getPhoneNo());
						message.setMsgText("POLIS : " + announcement.getText());
						message.setType("P");
						message.setReceiverId(police.getId());
						messages.add(message);
					}
				}

			} else if (announcement.getType().equals("D")) {
				for (Politician politician : politicians) {

					if (politician.getPhoneNo() != null) {
						Message message = new Message();
						message.setMessage_id(announcement.getAnnouncement_id());
						message.setReceiver("6" + politician.getPhoneNo());
						message.setMsgText("ADUN : " + announcement.getText());
						message.setType("D");
						message.setReceiverId(politician.getId());
						messages.add(message);
					}
				}

			} else {
				//alter process get receiver and receiver id in one connection
				for (Guardian guardian : guardians) {

					/*//System.out.println(guardian.getGuardian_id());
					GuardianContact guardianContact = grdnCtrl
							.getGuardianSubscribedContact(
									guardian.getGuardian_id(), conn);*/
					System.out.println("Data : "+guardian.getGuardianContact().getGcontact_id());
					
					if (guardian.getGuardianContact().getGcontact_id() != null) {
						Message message = new Message();
						message.setMessage_id(announcement.getAnnouncement_id());
						message.setMsgText(announcement.getText());
						message.setType("A");
						
						
						message.setReceiver("6"
								+ guardian.getGuardianContact().getPhone_number());
						message.setGuardian(guardian);
						message.setReceiverId(guardian.getGuardian_id());
						
						
						messages.add(message);
					}
				}
			}

			updateAnnouncementStatus(announcement.getAnnouncement_id(), conn);
		}
		return messages;
	}

	public int updateAnnouncementStatus(String announcement_id, Connection conn)
			throws Exception {
		
		System.out.println(announcement_id);

		// define sql statement
		String sql = "{ ? = call update_announcement_status (?) }";

		// create callable statement
		CallableStatement cs = conn.prepareCall(sql);

		// set callable statement's parameters
		cs.registerOutParameter(1, java.sql.Types.INTEGER);
		cs.setString(2, announcement_id);

		// execute the query on prepared statement
		cs.execute();

		// get returned id from function
		int status = cs.getInt(1);

		// if sql statement successfully committed
		if (status > 0) {
			System.out.println("AnnouncementId (PK) : " + announcement_id
					+ " is updated");
			return 1;
		} else {
			// sql statement failed
			System.out.println("update fail");
			return -1;
		}
	}

}