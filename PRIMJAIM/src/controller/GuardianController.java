package controller;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import oracle.jdbc.OracleTypes;
import oracle.jdbc.internal.OracleCallableStatement;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;
import oracle.sql.STRUCT;
import oracle.sql.StructDescriptor;
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
	
	public int checkGuardianIc(String guardian_ic, Connection conn)
			throws Exception {
		// define sql statement
		String sql = "{call ? := checkGuaRegStatByIc(?)}";

		// create prepared statement
		CallableStatement cs = conn.prepareCall(sql);

		// set prepared statement's parameters
		cs.registerOutParameter(1, OracleTypes.NUMBER);
		cs.setString(2, guardian_ic);

		cs.execute();

		int status = -1;

		status = cs.getInt(1);

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
			guardian.setIc(rs.getString(10));
		}

		return guardian;
	}

	public Guardian getGuardianById2(String guardian_id, Connection conn)
			throws Exception {
		// Object to keep results retrieved from database
		Guardian guardian = new Guardian();

		// define sql statement
		String sql = "{call ? := getGuardianById2(?)}";

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
			guardian.setDependent(rs.getInt(10));
			guardian.setIc(rs.getString(11));
			guardian.setCitizen_status(rs.getString(12));
			guardian.setRelationship(rs.getString(13));
			
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
		guardian.setRelationship(rs.getString(2));

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

	public ArrayList<GuardianContact> getAllGuardianContacts(String guardian_id,
			Connection conn) throws Exception {
		// List to keep results retrieved from database
		ArrayList<GuardianContact> contacts = new ArrayList<GuardianContact>();

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
	
	public Guardian getGuardianByIc(String guardian_ic, Connection conn)
			throws Exception {
		// Object to keep results retrieved from database
		Guardian guardian = new Guardian();

		// define sql statement
		String sql = "{call ? := getGuardianByIc(?)}";

		// create prepared statement
		CallableStatement cs = conn.prepareCall(sql);

		// set prepared statement's parameters
		cs.registerOutParameter(1, OracleTypes.CURSOR);
		cs.setString(2, guardian_ic);

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
			guardian.setDependent(rs.getInt(10));
			guardian.setIc(rs.getString(11));
			guardian.setCitizen_status(rs.getString(12));
			guardian.setRelationship(rs.getString(13));
		}
		
		//System.out.println(guardian.getRelationship());

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
		cs.setInt(11, guardian.getDependent());
		cs.setString(12, guardian.getIc());

		cs.execute();

		guardianId = cs.getString(1);

		return guardianId;
	}
	
	public String addNewGuardianRegistration(Guardian guardian, String studentId,
			String relationship, Connection conn) throws SQLException {

		String guardianId = null;

		String sql = "{ call ? := ADD_GUARDIAN_REGISTRATION(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";

		CallableStatement cs = conn.prepareCall(sql);

		cs.registerOutParameter(1, OracleTypes.VARCHAR);
		cs.setString(2, guardian.getName());
		cs.setString(3, guardian.getIc());
		cs.setString(4, guardian.getGender());
		cs.setString(5, guardian.getOccupation());
		cs.setInt(6, guardian.getDependent());
		cs.setString(7, guardian.getAddress());
		cs.setString(8, guardian.getCity());
		cs.setString(9, guardian.getPostcode());
		cs.setString(10, guardian.getState());
		cs.setString(11, studentId);
		cs.setString(12, relationship);
		
		cs.execute();

		guardianId = cs.getString(1);

		return guardianId;
	}
	
	public String updateGuardianRegistration(Guardian guardian, String studentId,
			String relationship, Connection conn) throws SQLException {

		String guardianId = null;

		String sql = "{ call ? := UPDATE_GUARDIAN_REGISTRATION(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";

		CallableStatement cs = conn.prepareCall(sql);

		cs.registerOutParameter(1, OracleTypes.VARCHAR);
		cs.setString(2, guardian.getGuardian_id());
		cs.setString(3, guardian.getName());
		cs.setString(4, guardian.getIc());
		cs.setString(5, guardian.getGender());
		cs.setString(6, guardian.getOccupation());
		cs.setInt(7, guardian.getDependent());
		cs.setString(8, guardian.getAddress());
		cs.setString(9, guardian.getCity());
		cs.setString(10, guardian.getPostcode());
		cs.setString(11, guardian.getState());
		cs.setString(12, studentId);
		cs.setString(13, relationship);
		
		cs.execute();

		guardianId = cs.getString(1);

		return guardianId;
	}
	
	public void deleteAllGuardianContactsRegistration(Guardian guardian, Connection conn) throws SQLException{
		
		String sql = "{ call DELETE_ALL_GC_REGISTRATION(?)}";
		
		CallableStatement cs = conn.prepareCall(sql);
		
		cs.setString(1, guardian.getGuardian_id());
		
		cs.execute();
		
	}
	
	public void addNewGuardianContactRegistration(String guardian_id, String phoneNo, String provider, String subscription, Connection conn) throws SQLException{
		
		String sql = "{ call ADD_GC_REGISTRATION(?, ?, ?, ?)}";
		
		CallableStatement cs = conn.prepareCall(sql);
		
		cs.setString(1, guardian_id);
		cs.setString(2, phoneNo);
		cs.setString(3, provider);
		cs.setString(4, subscription);
		
		cs.execute();
		
	}
	
	public void updateGuardianContact(String contactId, String phoneNo, Connection conn) throws SQLException{
		
		String sql = "{ call updateGuardianContact(?,?)}";
		
		CallableStatement cs = conn.prepareCall(sql);
		
		cs.setString(1, contactId);
		cs.setString(2, phoneNo);
		
		cs.execute();
		
	}
	
	public List<Guardian> getGuardiansByClassId(String class_id,
			Connection conn) throws Exception {
		// List to keep results retrieved from database
		List<Guardian> guardians = new ArrayList<Guardian>();

		// define sql statement
		String sql = "{call ? := getGuardiansByClassId(?)}";

		// create prepared statement
		CallableStatement cs = conn.prepareCall(sql);

		// set prepared statement's parameters
		cs.registerOutParameter(1, OracleTypes.CURSOR);
		cs.setString(2, class_id);

		cs.execute();
		
		ResultSet rs = (ResultSet) cs.getObject(1);
		
		while (rs.next()) {		
			// Object to keep results retrieved from database
			GuardianContact gc = new GuardianContact(rs.getString(2), rs.getString(3), rs.getString(4));
			
			Guardian guardian = new Guardian(rs.getString(1), gc);
	
			// add object to List
			guardians.add(guardian);
		}
		return guardians;
	}
	
	public List<Guardian> getGuardiansByInstitutionId(String institution_id,
			Connection conn) throws Exception {
		// List to keep results retrieved from database
		List<Guardian> guardians = new ArrayList<Guardian>();

		// define sql statement
		String sql = "{call ? := getGuardiansByInstitutionId(?)}";

		// create prepared statement
		CallableStatement cs = conn.prepareCall(sql);

		// set prepared statement's parameters
		cs.registerOutParameter(1, OracleTypes.CURSOR);
		cs.setString(2, institution_id);

		cs.execute();
		
		ResultSet rs = (ResultSet) cs.getObject(1);
		
		while (rs.next()) {
			// Object to keep results retrieved from database
			GuardianContact gc = new GuardianContact(rs.getString(2), rs.getString(3), rs.getString(4));
	
			Guardian guardian = new Guardian(rs.getString(1), gc);
			
			guardians.add(guardian);
		}
		return guardians;
	}

	public List<Guardian> getGuardiansByStudentId2(String student_id,
			Connection conn) throws Exception {
		// List to keep results retrieved from database
		List<Guardian> guardians = new ArrayList<Guardian>();

		// define sql statement
		String sql = "{call ? := getGuardiansByStudentId2(?)}";

		// create prepared statement
		CallableStatement cs = conn.prepareCall(sql);

		// set prepared statement's parameters
		cs.registerOutParameter(1, OracleTypes.CURSOR);
		cs.setString(2, student_id);

		cs.execute();
		
		ResultSet rs = (ResultSet) cs.getObject(1);
		
		while (rs.next()) {		
			// Object to keep results retrieved from database
			GuardianContact gc = new GuardianContact(rs.getString(2), rs.getString(3), rs.getString(4));
			
			Guardian guardian = new Guardian(rs.getString(1), gc);
	
			// add object to List
			guardians.add(guardian);
		}
		return guardians;
	}
	
	public String addGuardianForRegistration(String studentId, Guardian guardian, String relationship, ArrayList<GuardianContact> contacts, Connection conn)
			throws SQLException {
		
		//initialize object for guardian
		Object[] guardianObj = {guardian.getGuardian_id(), guardian.getName(),guardian.getIc(), guardian.getGender(),guardian.getOccupation(),
		guardian.getDependent(), guardian.getAddress(), guardian.getCity(), guardian.getPostcode(), guardian.getState()};
				
		// describe and link JAVA Object and Oracle type of Object for guardian
		StructDescriptor guardianStruct = StructDescriptor.createDescriptor("REGGUARDIANTYPE",conn);
		STRUCT inputGuardian = new STRUCT(guardianStruct, conn, guardianObj);
		
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
		
		// 4 parameters 
		String sql = "{ call ? := ADDPARENTFORREGISTRATION(?,?,?,?)}";
				
				
		OracleCallableStatement cs = (OracleCallableStatement)conn.prepareCall(sql);
				
		cs.registerOutParameter(1, OracleTypes.VARCHAR);
		cs.setString(2, studentId);
		cs.setObject(3, inputGuardian);
		cs.setString(4, relationship);
		cs.setARRAY(5, inputGuardianContacts);
				
		cs.execute();
		
		String guardianId = cs.getString(1);

		return guardianId;
	}


}
