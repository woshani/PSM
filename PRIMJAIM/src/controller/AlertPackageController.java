package controller;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import oracle.jdbc.OracleTypes;

import bean.AlertPackage;
import bean.Guardian;

public class AlertPackageController {

	public int checkAlerPackageId(String package_id, Connection conn) 
			throws Exception {
		// define sql statement
		String sql = "{ call ? := checkAlertPackageId(?) }";
		
		// create prepared statement
		CallableStatement cs = conn.prepareCall(sql);
		
		// set prepared statement's parameters
		cs.registerOutParameter(1, OracleTypes.NUMBER);
		cs.setString(2, package_id);

		/* execute the query on prepared statement and 
		 * create result set to keep result
		 */
		cs.execute();

		/* while reading result from result set,
		 * set result information to object
		 */
		int status = -1;
		status = cs.getInt(1);
		
		return status;
	}
	
	public List<AlertPackage> getAllAlertPackages(Connection conn) 
			throws Exception {
		// List to keep results retrieved from database
		List<AlertPackage> alertPackages = new ArrayList<AlertPackage>();
		
		// define sql statement
		String sql = " { call ? := getAllAlertPackages() } ";
		
		// create prepared statement
		CallableStatement cs = conn.prepareCall(sql);
		
		cs.registerOutParameter(1, OracleTypes.CURSOR);

		cs.execute();
		
		/* execute the query on prepared statement and 
		 * create result set to keep result
		 */
		ResultSet rs = (ResultSet) cs.getObject(1);

		/* while reading result from result set,
		 * set result information to object
		 */
		while (rs.next()) {
			// Object to keep results retrieved from database
			AlertPackage alertPackage = new AlertPackage();
			
			alertPackage.setPackage_id(rs.getString(1));
			alertPackage.setCode(rs.getString(2));
			alertPackage.setName(rs.getString(3));
			
			// add object to List
			alertPackages.add(alertPackage);
		}

		return alertPackages;
	}

	public AlertPackage getAlertPackageById(String package_id, Connection conn) 
			throws Exception {
		// Object to keep results retrieved from database
		AlertPackage alertPackage = new AlertPackage();
		
		// define sql statement
		String sql = "{ call ? := getAlertPackageById(?)}";
		
		// create prepared statement
		CallableStatement cs = conn.prepareCall(sql);
		
		// set prepared statement's parameters
		cs.registerOutParameter(1, OracleTypes.CURSOR);
		cs.setString(2, package_id);

		cs.execute();
		
		/* execute the query on prepared statement and 
		 * create result set to keep result
		 */
		
		ResultSet rs = (ResultSet) cs.getObject(1);

		/* while reading result from result set,
		 * set result information to object
		 */
		while (rs.next()) {
			alertPackage.setPackage_id(rs.getString(1));
			alertPackage.setCode(rs.getString(2));
			alertPackage.setName(rs.getString(3));
		}

		return alertPackage;
	}
	
	public List<AlertPackage> getAlertPackagesByGuardian(Guardian guardian, 
			Connection conn) throws Exception {
		// List to keep results retrieved from database
		List<AlertPackage> alertPackages = new ArrayList<AlertPackage>();
		
		// define sql statement
		String sql = "{ call ? := getAlertPackagesByGuardian(?) }";
		
		// create prepared statement
		CallableStatement cs = conn.prepareCall(sql);
		
		// set prepared statement's parameters
		cs.registerOutParameter(1, OracleTypes.CURSOR);
		cs.setString(2, guardian.getGuardian_id());

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
			AlertPackage alertPackage = 
					getAlertPackageById(rs.getString(1), conn);
			
			// add object to List
			alertPackages.add(alertPackage);
		}

		return alertPackages;
	}
	
	public int addNewAlertPackage(AlertPackage alertPackage, Connection conn) 
			throws Exception {
		
		// define sql statement
		String sql = "{ ? = call insert_package (?,?) }";
		
		// create callable statement
		CallableStatement cs = conn.prepareCall(sql);
		
		// set callable statement's parameters
		cs.registerOutParameter(1,  java.sql.Types.INTEGER);
		cs.setString(2, alertPackage.getCode());
		cs.setString(3, alertPackage.getName());

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
	
	public int updateAlertPackage(AlertPackage alertPackage, Connection conn) 
			throws Exception {
		
		// define sql statement
		String sql = "{ ? = call update_package (?,?,?) }";
		
		// create callable statement
		CallableStatement cs = conn.prepareCall(sql);
		
		// set callable statement's parameters
		cs.registerOutParameter(1,  java.sql.Types.INTEGER);
		cs.setString(2, alertPackage.getPackage_id());
		cs.setString(3, alertPackage.getCode());
		cs.setString(4, alertPackage.getName());

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
