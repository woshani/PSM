package controller;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import oracle.jdbc.OracleTypes;

import bean.AlertPackage;
import bean.Guardian;
import bean.GuardianContact;

public class GuardianController {
	/*
	 * Declaration of variables
	 */
	private UserController userCtrl = new UserController();

	public int checkGuardianId(String guardian_id, Connection conn)
			throws Exception {
		// define sql statement
		String sql = "{call ? := checkGuardianId(?)}";

		// create prepared statement
		CallableStatement cs = conn.prepareCall(sql);

		// set prepared statement's parameters
		cs.registerOutParameter(1, OracleTypes.NUMBER);
		cs.setString(2, guardian_id);

		cs.execute();

		int status = cs.getInt(1);

		return status;
	}

	public List<Guardian> getAllGuardians(Connection conn) throws Exception {
		// List to keep results retrieved from database
		List<Guardian> guardians = new ArrayList<Guardian>();

		// define sql statement
		String sql = "{call ? := getAllGuardians()}";

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
			Guardian guardian = new Guardian();

			guardian.setGuardian_id(rs.getString(1));
			guardian.setUser(userCtrl.getUserById(rs.getString(2), conn));
			guardian.setName(rs.getString(3));
			guardian.setGender(rs.getString(4));
			guardian.setOccupation(rs.getString(5));
			guardian.setAddress(rs.getString(6));
			guardian.setCity(rs.getString(7));
			guardian.setState(rs.getString(8));
			guardian.setPostcode(rs.getString(9));

			// add object to List
			guardians.add(guardian);
		}

		return guardians;
	}

	public Guardian getGuardianById(String guardian_id, Connection conn)
			throws Exception {
		// Object to keep results retrieved from database
		Guardian guardian = new Guardian();

		// define sql statement
		String sql = "{call ? := getGuardianById(?)}";

		// create prepared statement
		CallableStatement cs = conn.prepareCall(sql);

		// set prepared statement's parameters
		cs.registerOutParameter(1, OracleTypes.CURSOR);
		cs.setString(2, guardian_id);

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
			guardian.setGuardian_id(rs.getString(1));
			guardian.setUser(userCtrl.getUserById(rs.getString(2), conn));
			guardian.setName(rs.getString(3));
			guardian.setGender(rs.getString(4));
			guardian.setOccupation(rs.getString(5));
			guardian.setAddress(rs.getString(6));
			guardian.setCity(rs.getString(7));
			guardian.setState(rs.getString(8));
			guardian.setPostcode(rs.getString(9));
		}

		return guardian;
	}

	public Guardian getGuardianByUserId(String user_id, Connection conn)
			throws Exception {
		// Object to keep results retrieved from database
		Guardian guardian = new Guardian();

		// define sql statement
		String sql = "{call ? := getGuardianByUserId(?)}";

		// create prepared statement
		CallableStatement cs = conn.prepareCall(sql);

		// set prepared statement's parameters
		cs.registerOutParameter(1, OracleTypes.CURSOR);
		cs.setString(2, user_id);

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
			guardian.setGuardian_id(rs.getString(1));
			guardian.setUser(userCtrl.getUserById(rs.getString(2), conn));
			guardian.setName(rs.getString(3));
			guardian.setGender(rs.getString(4));
			guardian.setOccupation(rs.getString(5));
			guardian.setAddress(rs.getString(6));
			guardian.setCity(rs.getString(7));
			guardian.setState(rs.getString(8));
			guardian.setPostcode(rs.getString(9));
		}

		return guardian;
	}

	public List<Guardian> getGuardiansByStudentId(String student_id,
			Connection conn) throws Exception {
		// List to keep results retrieved from database
		List<Guardian> guardians = new ArrayList<Guardian>();

		// define sql statement
		String sql = "{call ? := getGuardiansByStudentId(?)}";

		// create prepared statement
		CallableStatement cs = conn.prepareCall(sql);

		// set prepared statement's parameters
		cs.registerOutParameter(1, OracleTypes.CURSOR);
		cs.setString(2, student_id);

		cs.execute();
		
		ResultSet rs = (ResultSet) cs.getObject(1);
		
		while (rs.next()) {
		
		// Object to keep results retrieved from database
		Guardian guardian = getGuardianById(rs.getString(1), conn);

		// add object to List
		guardians.add(guardian);
		
		}


		return guardians;
	}

	public int subscribe(Guardian guardian, AlertPackage alertPackage,
			Connection conn) throws Exception {

		// define sql statement
		String sql = "{ ? = call subscribe (?,?) }";

		// create callable statement
		CallableStatement cs = conn.prepareCall(sql);

		// set callable statement's parameters
		cs.registerOutParameter(1, java.sql.Types.INTEGER);
		cs.setString(2, guardian.getGuardian_id());
		cs.setString(3, alertPackage.getPackage_id());

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

	public int unsubscribe(Guardian guardian, AlertPackage alertPackage,
			Connection conn) throws Exception {

		// define sql statement
		String sql = "{ ? = call unsubscribe (?,?) }";

		// create callable statement
		CallableStatement cs = conn.prepareCall(sql);

		// set callable statement's parameters
		cs.registerOutParameter(1, java.sql.Types.INTEGER);
		cs.setString(2, guardian.getGuardian_id());
		cs.setString(3, alertPackage.getPackage_id());

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

	public int checkGuardianContactId(String guardian_contact_id,
			Connection conn) throws Exception {
		// define sql statement
		String sql = "{call ? := checkGuardianContactId(?)}";

		// create prepared statement
		CallableStatement cs = conn.prepareCall(sql);

		// set prepared statement's parameters
		cs.setString(2, guardian_contact_id);

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

	public List<GuardianContact> getAllGuardianContacts(String guardian_id,
			Connection conn) throws Exception {
		// List to keep results retrieved from database
		List<GuardianContact> contacts = new ArrayList<GuardianContact>();

		// define sql statement
		String sql = "{call ? := getAllGuardianContacts(?)}";

		// create prepared statement
		CallableStatement cs = conn.prepareCall(sql);

		// set prepared statement's parameters
		cs.registerOutParameter(1, OracleTypes.CURSOR);
		cs.setString(2, guardian_id);

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
			GuardianContact contact = new GuardianContact();

			contact.setGcontact_id(rs.getString(1));
			contact.setGuardian(getGuardianById(rs.getString(2), conn));
			contact.setPhone_number(rs.getString(3));
			contact.setProvider(rs.getString(4));
			contact.setSubscription(rs.getString(5));

			// add object to List
			contacts.add(contact);
		}

		return contacts;
	}

	public GuardianContact getGuardianSubscribedContact(String guardian_id,
			Connection conn) throws Exception {
		// Object to keep results retrieved from database
		GuardianContact contact = new GuardianContact();

		// define sql statement
		String sql = "{call ? := getGuardianSubscribedContact(?)}";

		// create prepared statement
		CallableStatement cs = conn.prepareCall(sql);

		// set prepared statement's parameters
		cs.registerOutParameter(1, OracleTypes.CURSOR);
		cs.setString(2, guardian_id);

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
			contact.setGcontact_id(rs.getString(1));
			contact.setGuardian(getGuardianById(rs.getString(2), conn));
			contact.setPhone_number(rs.getString(3));
			contact.setProvider(rs.getString(4));
			contact.setSubscription(rs.getString(5));
		}

		return contact;
	}

	public int addNewGuardianContact(GuardianContact contact, Connection conn)
			throws Exception {

		// define sql statement
		String sql = "{ ? = call insert_guardian_contact (?,?,?,?) }";

		// create callable statement
		CallableStatement cs = conn.prepareCall(sql);

		// set callable statement's parameters
		cs.registerOutParameter(1, java.sql.Types.INTEGER);
		cs.setString(2, contact.getGuardian().getGuardian_id());
		cs.setString(3, contact.getPhone_number());
		cs.setString(4, contact.getProvider());
		cs.setString(5, contact.getSubscription());

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

	public int updateGuardianContact(GuardianContact contact, Connection conn)
			throws Exception {

		// define sql statement
		String sql = "{ ? = call update_guardian_contact (?,?,?,?,?) }";

		// create callable statement
		CallableStatement cs = conn.prepareCall(sql);

		// set callable statement's parameters
		cs.registerOutParameter(1, java.sql.Types.INTEGER);
		cs.setString(2, contact.getGcontact_id());
		cs.setString(3, contact.getGuardian().getGuardian_id());
		cs.setString(4, contact.getPhone_number());
		cs.setString(5, contact.getProvider());
		cs.setString(6, contact.getSubscription());

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

	public int deleteGuardianContact(GuardianContact contact, Connection conn)
			throws Exception {

		// define sql statement
		String sql = "{ ? = call delete_guardian_contact (?) }";

		// create callable statement
		CallableStatement cs = conn.prepareCall(sql);

		// set callable statement's parameters
		cs.registerOutParameter(1, java.sql.Types.INTEGER);
		cs.setString(2, contact.getGcontact_id());

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

	public int sendAlertToGuardian(Guardian guardian, Connection conn)
			throws Exception {

		// define sql statement
		String sql = "{ ? = call send_alert (?) }";

		// create callable statement
		CallableStatement cs = conn.prepareCall(sql);

		// set callable statement's parameters
		cs.registerOutParameter(1, java.sql.Types.INTEGER);
		cs.setString(2, guardian.getGuardian_id());

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

	public int getGuardianOutgoingSMSCount(Guardian guardian, Connection conn)
			throws Exception {

		// define sql statement
		String sql = "{ ? = call get_total_outgoing_gs_count (?) }";

		// create callable statement
		CallableStatement cs = conn.prepareCall(sql);

		// set callable statement's parameters
		cs.registerOutParameter(1, java.sql.Types.INTEGER);
		cs.setString(2, guardian.getGuardian_id());

		// execute the query on prepared statement
		cs.execute();

		// get returned id from function
		int status = cs.getInt(1);

		// if sql statement successfully committed
		if (status >= 0) {
			return status;
		} else {
			// sql statement failed
			return -1;
		}
	}

	public int getGuardianIncomingSMSCount(Guardian guardian, Connection conn)
			throws Exception {

		// define sql statement
		String sql = "{ ? = call get_total_incoming_gs_count (?) }";

		// create callable statement
		CallableStatement cs = conn.prepareCall(sql);

		// set callable statement's parameters
		cs.registerOutParameter(1, java.sql.Types.INTEGER);
		cs.setString(2, guardian.getGuardian_id());

		// execute the query on prepared statement
		cs.execute();

		// get returned id from function
		int status = cs.getInt(1);

		// if sql statement successfully committed
		if (status >= 0) {
			return status;
		} else {
			// sql statement failed
			return -1;
		}
	}

	public int getGuardianMonthlyOutgoingSMSCount(Guardian guardian,
			Connection conn) throws Exception {

		// define sql statement
		String sql = "{ ? = call get_monthly_outgoing_gs_count (?) }";

		// create callable statement
		CallableStatement cs = conn.prepareCall(sql);

		// set callable statement's parameters
		cs.registerOutParameter(1, java.sql.Types.INTEGER);
		cs.setString(2, guardian.getGuardian_id());

		// execute the query on prepared statement
		cs.execute();

		// get returned id from function
		int status = cs.getInt(1);

		// if sql statement successfully committed
		if (status >= 0) {
			return status;
		} else {
			// sql statement failed
			return -1;
		}
	}

	public int getGuardianMonthlyIncomingSMSCount(Guardian guardian,
			Connection conn) throws Exception {

		// define sql statement
		String sql = "{ ? = call get_monthly_incoming_gs_count (?) }";

		// create callable statement
		CallableStatement cs = conn.prepareCall(sql);

		// set callable statement's parameters
		cs.registerOutParameter(1, java.sql.Types.INTEGER);
		cs.setString(2, guardian.getGuardian_id());

		// execute the query on prepared statement
		cs.execute();

		// get returned id from function
		int status = cs.getInt(1);

		// if sql statement successfully committed
		if (status >= 0) {
			return status;
		} else {
			// sql statement failed
			return -1;
		}
	}

	public Guardian getGuardianByStudentId(String student_id, Connection conn)
			throws Exception {
		// List to keep results retrieved from database
		Guardian guardian = new Guardian();

		// define sql statement
		String sql = "{call ? := getGuardianByStudentId(?)}";

		// create prepared statement
		CallableStatement cs = conn.prepareCall(sql);

		// set prepared statement's parameters
		cs.registerOutParameter(1, OracleTypes.VARCHAR);
		cs.setString(2, student_id);
		
		cs.execute();

		/*
		 * execute the query on prepared statement and create result set to keep
		 * result
		 */
		
			// Object to keep results retrieved from database
		guardian = getGuardianById(cs.getString(1), conn);


		return guardian;
	}

	public String insertGuardian(Guardian guardian, String studentId,
			String relationship, Connection conn) throws SQLException {

		String guardianId = null;

		String sql = "{ call ? := addParent(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";

		CallableStatement cs = conn.prepareCall(sql);

		cs.registerOutParameter(1, OracleTypes.VARCHAR);
		cs.setString(2, guardian.getName());
		cs.setString(3, guardian.getOccupation());
		cs.setString(4, guardian.getGender());
		cs.setString(5, guardian.getAddress());
		cs.setString(6, guardian.getCity());
		cs.setString(7, guardian.getState());
		cs.setString(8, guardian.getPostcode());
		cs.setString(9, studentId);
		cs.setString(10, relationship);
		cs.setString(11, guardian.getDependent());
		cs.setString(12, guardian.getIc());

		cs.execute();

		guardianId = cs.getString(1);

		return guardianId;
	}
	
	public void updateGuardianContact(String contactId, String phoneNo, Connection conn) throws SQLException{
		
		String sql = "{ call updateGuardianContact(?,?)}";
		
		CallableStatement cs = conn.prepareCall(sql);
		
		cs.setString(1, contactId);
		cs.setString(2, phoneNo);
		
		cs.execute();
		
	}

}
