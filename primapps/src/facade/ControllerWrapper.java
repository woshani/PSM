package facade;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;






import bean.AlertPackage;
import bean.Guardian;
import bean.GuardianContact;
import bean.User;
import connection.DBConnection;
import controller.AlertPackageController;
import controller.GuardianController;
import controller.UserController;


public class ControllerWrapper {
	/*
	 * Declaration of variables
	 */
	private DBConnection dbConn;
	private Connection conn;
	private UserController userCtrl = new UserController();
	
	private GuardianController grdnCtrl = new GuardianController();
	private AlertPackageController packCtrl = new AlertPackageController();
	
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

	
	public int checkGuardianId(String guardian_id) throws Exception {
		conn = dbConn.getConnection();
		int result = grdnCtrl.checkGuardianId(guardian_id, conn);
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

	public int checkGuardianContactId(String guardian_contact_id)
			throws Exception {
		conn = dbConn.getConnection();
		int result = grdnCtrl.checkGuardianContactId(guardian_contact_id, conn);
		dbConn.closeConnection(conn);
		return result;
	}

	public List<GuardianContact> getAllGuardianContacts(String guardian_id)
			throws Exception {
		conn = dbConn.getConnection();
		List<GuardianContact> result = grdnCtrl.getAllGuardianContacts(
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

	public Guardian getGuardianByStudentId(String student_id) throws Exception {
		conn = dbConn.getConnection();
		Guardian result = grdnCtrl.getGuardianByStudentId(student_id, conn);
		dbConn.closeConnection(conn);
		return result;
	}

	
	
	
}
