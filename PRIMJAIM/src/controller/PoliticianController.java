package controller;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

import bean.Police;
import bean.Politician;
import oracle.jdbc.OracleTypes;

public class PoliticianController {
	
	private UserController userCtrl = new UserController();
	
	public int checkPoliticianId(String politician_id, Connection conn)
			throws Exception {
		// define sql statement
		String sql = "{call ? := checkPoliticianId(?)}";

		// create prepared statement
		CallableStatement cs = conn.prepareCall(sql);

		// set prepared statement's parameters
		cs.registerOutParameter(1, OracleTypes.NUMBER);
		cs.setString(2, politician_id);

		cs.execute();

		int status = cs.getInt(1);

		return status;
	}
	
	public Politician getPoliticianByUserId(String user_id, Connection conn)
			throws Exception {
		// Object to keep results retrieved from database
		Politician politician= new Politician();

		// define sql statement
		String sql = "{call ? := getPoliticianByUserId(?)}";

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
			politician.setId(rs.getString(1));
			politician.setUser(userCtrl.getUserById(rs.getString(2), conn));
			politician.setName(rs.getString(3));
			politician.setPhoneNo(rs.getString(4));
		}

		return politician;
	}
	
	public Politician getPoliticianById(String politician_id, Connection conn)
			throws Exception {
		// Object to keep results retrieved from database
		Politician politician = new Politician();

		// define sql statement
		String sql = "{call ? := getPoliticianById(?)}";

		// create prepared statement
		CallableStatement cs = conn.prepareCall(sql);

		// set prepared statement's parameters
		cs.registerOutParameter(1, OracleTypes.CURSOR);
		cs.setString(2, politician_id);

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
			politician.setId(rs.getString(1));
			politician.setUser(userCtrl.getUserById(rs.getString(2), conn));
			politician.setName(rs.getString(3));
			politician.setPhoneNo(rs.getString(4));
		}

		return politician;
	}
	
	public ArrayList<Politician> getPoliticiansByDunId(int politician_id, Connection conn)
			throws Exception {
		// Object to keep results retrieved from database
		ArrayList<Politician> politicians = new ArrayList<Politician>();

		// define sql statement
		String sql = "{call ? := getPoliticiansByDunId(?)}";

		// create prepared statement
		CallableStatement cs = conn.prepareCall(sql);

		// set prepared statement's parameters
		cs.registerOutParameter(1, OracleTypes.CURSOR);
		cs.setInt(2, politician_id);

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
			Politician politician = new Politician();
			politician.setId(rs.getString(1));
			politician.setUser(userCtrl.getUserById(rs.getString(2), conn));
			politician.setName(rs.getString(3));
			politician.setPhoneNo(rs.getString(4));
			
			politicians.add(politician);
		}

		return politicians;
	}
	
	public int updatePoliticianContact(Politician politician, Connection conn)
			throws Exception {

		// define sql statement
		String sql = "{ ? = call update_politician_contact (?,?) }";

		// create callable statement
		CallableStatement cs = conn.prepareCall(sql);

		// set callable statement's parameters
		cs.registerOutParameter(1, java.sql.Types.INTEGER);
		cs.setString(2, politician.getId());
		cs.setString(3, politician.getPhoneNo());

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
