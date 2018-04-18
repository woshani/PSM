package controller;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import oracle.jdbc.OracleTypes;
import bean.Announcement;
import bean.Guardian;
import bean.GuardianContact;
import bean.Message;
import bean.OutgoingSMS;

public class OutgoingSMSController {
	/*
	 * Declaration of variables
	 */
	private StudentController stuCtrl = new StudentController();
	private GuardianController grdnCtrl = new GuardianController();
	private AlertPackageController packCtrl = new AlertPackageController();

	public int checkOutgoingSMSId(String osms_id, Connection conn) throws Exception {
		// define sql statement
		String sql = "{call ? := checkOutgoingSMSId(?)}";

		// create prepared statement
		CallableStatement cs = conn.prepareCall(sql);

		// set prepared statement's parameters
		cs.registerOutParameter(1, OracleTypes.NUMBER);
		cs.setString(2, osms_id);

		cs.executeQuery();

		int status = -1;

		status = cs.getInt(1);

		return status;
	}

	public List<OutgoingSMS> getAllOutgoingSMSsByTeacher(String teacherId, Connection conn) throws Exception {
		// List to keep results retrieved from database
		List<OutgoingSMS> outgoingSMSs = new ArrayList<OutgoingSMS>();

		// define sql statement
		String sql = "{ call ? := getAllOutgoingSMSsByTeacher(?)}";

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
			OutgoingSMS outgoingSMS = new OutgoingSMS();

			outgoingSMS.setOsms_id(rs.getString(1));
			outgoingSMS.setStudent(stuCtrl.getStudentById(rs.getString(2), conn));
			outgoingSMS.setAlertPackage(packCtrl.getAlertPackageById(rs.getString(3), conn));
			outgoingSMS.setDatetime(rs.getTimestamp(4));
			outgoingSMS.setStatus(rs.getString(5));
			outgoingSMS.setEventDate(rs.getString(6));

			// add object to List
			outgoingSMSs.add(outgoingSMS);
		}

		return outgoingSMSs;
	}

	public List<OutgoingSMS> getAllOutgoingSMSs(Connection conn) throws Exception {
		// List to keep results retrieved from database
		List<OutgoingSMS> outgoingSMSs = new ArrayList<OutgoingSMS>();

		// define sql statement
		String sql = "{ call ? := getAllOutgoingSMSs()}";

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
			OutgoingSMS outgoingSMS = new OutgoingSMS();

			outgoingSMS.setOsms_id(rs.getString(1));
			outgoingSMS.setStudent(stuCtrl.getStudentById(rs.getString(2), conn));
			outgoingSMS.setAlertPackage(packCtrl.getAlertPackageById(rs.getString(3), conn));
			outgoingSMS.setDatetime(rs.getTimestamp(4));
			outgoingSMS.setStatus(rs.getString(5));
			outgoingSMS.setEventDate(rs.getString(6));

			// add object to List
			outgoingSMSs.add(outgoingSMS);
		}

		return outgoingSMSs;
	}

	public List<OutgoingSMS> getAllSentSMSs(Connection conn) throws Exception {
		// List to keep results retrieved from database
		List<OutgoingSMS> outgoingSMSs = new ArrayList<OutgoingSMS>();

		// define sql statement
		String sql = "{call ? := getAllSentSMSs()}";

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
			OutgoingSMS outgoingSMS = new OutgoingSMS();

			outgoingSMS.setOsms_id(rs.getString(1));
			outgoingSMS.setStudent(stuCtrl.getStudentById(rs.getString(2), conn));
			outgoingSMS.setAlertPackage(packCtrl.getAlertPackageById(rs.getString(3), conn));
			outgoingSMS.setDatetime(rs.getTimestamp(4));
			outgoingSMS.setStatus(rs.getString(5));

			// add object to List
			outgoingSMSs.add(outgoingSMS);
		}

		return outgoingSMSs;
	}

	public OutgoingSMS getOutgoingSMSById(String osms_ids, Connection conn) throws Exception {
		// Object to keep results retrieved from database
		OutgoingSMS outgoingSMS = new OutgoingSMS();

		// define sql statement
		String sql = "{call ? := getOutgoingSMSById(?)}";

		// create prepared statement
		CallableStatement cs = conn.prepareCall(sql);

		// set prepared statement's parameters
		cs.registerOutParameter(1, OracleTypes.CURSOR);
		cs.setString(2, osms_ids);

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
			outgoingSMS.setOsms_id(rs.getString(1));
			outgoingSMS.setStudent(stuCtrl.getStudentById(rs.getString(2), conn));
			outgoingSMS.setAlertPackage(packCtrl.getAlertPackageById(rs.getString(3), conn));
			outgoingSMS.setDatetime(rs.getTimestamp(4));
			outgoingSMS.setStatus(rs.getString(5));
		}

		return outgoingSMS;
	}

	public List<Message> getMessagesByOutgoingSMSs(List<OutgoingSMS> outgoingSMSs, Connection conn) throws Exception {
		List<Message> messages = new ArrayList<Message>();
		for (OutgoingSMS outgoingSMS : outgoingSMSs) {
			List<Guardian> guardians = grdnCtrl.getGuardiansByStudentId(outgoingSMS.getStudent().getStudent_id(), conn);

			for (Guardian guardian : guardians) {
				GuardianContact guardianContact = grdnCtrl
						.getGuardianSubscribedContact(guardian.getGuardian_id(), conn);

				if (guardianContact.getGcontact_id() != null) {
					String msgText = "";
					if (outgoingSMS.getAlertPackage().getPackage_id().equals("1")) {
						msgText += outgoingSMS.getStudent().getName() + " tidak hadir ke sekolah pada hari ini.";
					} else if (outgoingSMS.getAlertPackage().getPackage_id().equals("3")) {
						msgText += outgoingSMS.getStudent().getName()
								+ " tidak hadir aktiviti kokurikulum pada hari ini.";
					} else if (outgoingSMS.getAlertPackage().getPackage_id().equals("5")) {
						msgText += outgoingSMS.getStudent().getName()
								+ " telah diberi surat amaran tidak hadir ke sekolah.";
					}

					Message message = new Message();
					message.setMessage_id(outgoingSMS.getOsms_id());
					message.setReceiver("6" + guardianContact.getPhone_number());
					message.setMsgText(msgText);
					message.setType("O");
					message.setGuardian(guardian);
					messages.add(message);
				}
			}

			updateOutgoingSMSStatus(outgoingSMS.getOsms_id(), conn);
		}
		return messages;
	}

	public int updateOutgoingSMSStatus(String osms_id, Connection conn) throws Exception {

		// define sql statement
		String sql = "{ ? = call update_outgoing_sms_status (?) }";

		// create callable statement
		CallableStatement cs = conn.prepareCall(sql);

		// set callable statement's parameters
		cs.registerOutParameter(1, java.sql.Types.INTEGER);
		cs.setString(2, osms_id);

		// execute the query on prepared statement
		cs.execute();

		// get returned id from function
		int status = cs.getInt(1);

		// if sql statement successfully committed
		if (status > 0) {
			System.out.println(osms_id + " updated");
			return 1;
		} else {
			// sql statement failed
			return -1;
		}
	}

	// ADDED ON 08/05/2015 BY MOHAMAD IDZHAR BIN YA`AKUB
	// CREATE NEW FUNCTION RETRIEVE SMS SENT AND DELAY BY ACADEMIC INSTITUTION
	// ID AND TEACHER ID
	// CODE START

	// GET ALL SENT SMS DATA BY TEACHER ID
	// ACADEMIC INSTITUTION ID WILL BE CAPTURED IN STORED PROCEDURE
	public List<OutgoingSMS> getAllSentSMSsById(String teacherId, Connection conn) throws Exception {
		// List to keep results retrieved from database
		List<OutgoingSMS> outgoingSMSs = new ArrayList<OutgoingSMS>();

		// define sql statement
		String sql = "{ call ? := GETALLSENTSMSSBYTEACHERID(?)}";

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
			OutgoingSMS outgoingSMS = new OutgoingSMS();

			outgoingSMS.setOsms_id(rs.getString(1));
			outgoingSMS.setStudent(stuCtrl.getStudentById(rs.getString(2), conn));
			outgoingSMS.setAlertPackage(packCtrl.getAlertPackageById(rs.getString(3), conn));
			outgoingSMS.setDatetime(rs.getTimestamp(4));
			outgoingSMS.setStatus(rs.getString(5));
			outgoingSMS.setEventDate(rs.getString(6));

			// add object to List
			outgoingSMSs.add(outgoingSMS);
		}

		return outgoingSMSs;
	}

	// GET ALL DELAY SMS DATA BY TEACHER ID
	// ACADEMIC INSTITUTION ID WILL BE CAPTURED IN STORED PROCEDURE
	public List<OutgoingSMS> getAllDelaySMSsById(String teacherId, Connection conn) throws Exception {
		// List to keep results retrieved from database
		List<OutgoingSMS> outgoingSMSs = new ArrayList<OutgoingSMS>();

		// define sql statement
		String sql = "{ call ? := GETALLDELAYSMSSBYTEACHERID(?)}";

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
			OutgoingSMS outgoingSMS = new OutgoingSMS();

			outgoingSMS.setOsms_id(rs.getString(1));
			outgoingSMS.setStudent(stuCtrl.getStudentById(rs.getString(2), conn));
			outgoingSMS.setAlertPackage(packCtrl.getAlertPackageById(rs.getString(3), conn));
			outgoingSMS.setDatetime(rs.getTimestamp(4));
			outgoingSMS.setStatus(rs.getString(5));
			outgoingSMS.setEventDate(rs.getString(6));

			// add object to List
			outgoingSMSs.add(outgoingSMS);
		}

		return outgoingSMSs;
	}
	
	// CODE END
	
	
	public int deleteOutGoingSMS(OutgoingSMS ogsms, Connection conn) 
			throws Exception {
		
		// define sql statement
		String sql = "{ ? = call delete_outgoingsms(?) }";
		
		// create callable statement
		CallableStatement cs = conn.prepareCall(sql);
		
		// set callable statement's parameters
		cs.registerOutParameter(1,  java.sql.Types.INTEGER);
		cs.setInt(2, Integer.parseInt(ogsms.getOsms_id()));

		// execute the query on prepared statement
		cs.execute();
		
		// get returned id from function
		int status = cs.getInt(1);
		
		// if sql statement successfully committed
		if(status > 0) {
			return 1;
		} else {
			// sql statement failed
			return -1;
		}
	}

}
