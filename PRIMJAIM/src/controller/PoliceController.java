package controller;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.GuardianContact;
import bean.Police;
import oracle.jdbc.OracleTypes;

public class PoliceController {
	
	private UserController userCtrl = new UserController();
	
	public int checkPoliceId(String police_id, Connection conn)
			throws Exception {
		// define sql statement
		String sql = "{call ? := checkPoliceId(?)}";

		// create prepared statement
		CallableStatement cs = conn.prepareCall(sql);

		// set prepared statement's parameters
		cs.registerOutParameter(1, OracleTypes.NUMBER);
		cs.setString(2, police_id);

		cs.execute();

		int status = cs.getInt(1);

		return status;
	}
	
	public Police getPoliceByUserId(String user_id, Connection conn)
			throws Exception {
		// Object to keep results retrieved from database
		Police police = new Police();

		// define sql statement
		String sql = "{call ? := getPoliceByUserId(?)}";

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
			police.setId(rs.getString(1));
			police.setUser(userCtrl.getUserById(rs.getString(2), conn));
			police.setName(rs.getString(3));
			police.setPhoneNo(rs.getString(4));
		}

		return police;
	}
	
	public Police getPoliceById(String police_id, Connection conn)
			throws Exception {
		// Object to keep results retrieved from database
		Police police = new Police();

		// define sql statement
		String sql = "{call ? := getPoliceById(?)}";

		// create prepared statement
		CallableStatement cs = conn.prepareCall(sql);

		// set prepared statement's parameters
		cs.registerOutParameter(1, OracleTypes.CURSOR);
		cs.setString(2, police_id);

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
			police.setId(rs.getString(1));
			police.setUser(userCtrl.getUserById(rs.getString(2), conn));
			police.setName(rs.getString(3));
			police.setPhoneNo(rs.getString(4));
		}

		return police;
	}
	
	public ArrayList<Police> getPolicesByStationId(int station_id, Connection conn)
			throws Exception {
		// Object to keep results retrieved from database
		ArrayList<Police> polices = new ArrayList<Police>();

		// define sql statement
		String sql = "{call ? := getPolicesByStationId(?)}";

		// create prepared statement
		CallableStatement cs = conn.prepareCall(sql);

		// set prepared statement's parameters
		cs.registerOutParameter(1, OracleTypes.CURSOR);
		cs.setInt(2, station_id);

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
			Police police = new Police();
			
			police.setId(rs.getString(1));
			police.setUser(userCtrl.getUserById(rs.getString(2), conn));
			police.setName(rs.getString(3));
			police.setPhoneNo(rs.getString(4));
			
			polices.add(police);
		}

		return polices;
	}
	
	public int updatePoliceContact(Police police, Connection conn)
			throws Exception {

		// define sql statement
		String sql = "{ ? = call update_police_contact (?,?) }";

		// create callable statement
		CallableStatement cs = conn.prepareCall(sql);

		// set callable statement's parameters
		cs.registerOutParameter(1, java.sql.Types.INTEGER);
		cs.setString(2, police.getId());
		cs.setString(3, police.getPhoneNo());

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


}
