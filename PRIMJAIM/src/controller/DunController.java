package controller;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

import facade.ControllerWrapper;
import oracle.jdbc.OracleTypes;
import bean.Dun;
import bean.Station;

public class DunController {
	
	public Dun getDunByPoliticianId(String politician_id, Connection conn)
			throws Exception {
		// Object to keep results retrieved from database
		Dun dun = new Dun();
		ControllerWrapper ctrl = new ControllerWrapper();

		// define sql statement
		String sql = "{call ? := getDunByPoliticianId(?)}";

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
			dun.setId(rs.getInt(1));
			dun.setParlimen(ctrl.getParlimenByDunId(dun.getId()));
			dun.setName(rs.getString(2));
			dun.setCode(rs.getString(3));
		}
		
		return dun;
	}

	public ArrayList<Dun> getAllDun(Connection conn)
			throws Exception {
		// Object to keep results retrieved from database
		
		ArrayList<Dun> duns = new ArrayList<Dun>();
		ControllerWrapper ctrl = new ControllerWrapper();

		// define sql statement
		String sql = "{call ? := getAllDun()}";

		// create prepared statement
		CallableStatement cs = conn.prepareCall(sql);

		// set prepared statement's parameters
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
			
			Dun dun = new Dun();
			
			dun.setId(rs.getInt(1));
			dun.setName(rs.getString(2));
			dun.setCode(rs.getString(3));
			dun.setParlimen(ctrl.getParlimenByDunId(dun.getId()));
			
			duns.add(dun);
		}

		return duns;
	}
	
	public Dun getDunByDunId(String dun_id, Connection conn)
			throws Exception {
		// Object to keep results retrieved from database
		Dun dun = new Dun();
		ControllerWrapper ctrl = new ControllerWrapper();

		// define sql statement
		String sql = "{call ? := getDunByDunId(?)}";

		// create prepared statement
		CallableStatement cs = conn.prepareCall(sql);

		// set prepared statement's parameters
		cs.registerOutParameter(1, OracleTypes.CURSOR);
		cs.setString(2, dun_id);

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
			dun.setId(rs.getInt(1));
			dun.setName(rs.getString(2));
			dun.setCode(rs.getString(3));
			dun.setParlimen(ctrl.getParlimenByDunId(dun.getId()));
		}
		return dun;
	}
	
	public Dun getDunByInstitutionId(String institution_id, Connection conn)
			throws Exception {
		// Object to keep results retrieved from database
		Dun dun = new Dun();
		ControllerWrapper ctrl = new ControllerWrapper();

		// define sql statement
		String sql = "{call ? := getDunByInstitutionId(?)}";

		// create prepared statement
		CallableStatement cs = conn.prepareCall(sql);

		// set prepared statement's parameters
		cs.registerOutParameter(1, OracleTypes.CURSOR);
		cs.setString(2, institution_id);

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
			dun.setId(rs.getInt(1));
			dun.setName(rs.getString(2));
			dun.setCode(rs.getString(3));
			dun.setParlimen(ctrl.getParlimenByDunId(dun.getId()));
		}
		return dun;
	}
	
	public Dun getDunByClassId(String class_id, Connection conn)
			throws Exception {
		// Object to keep results retrieved from database
		Dun dun = new Dun();
		ControllerWrapper ctrl = new ControllerWrapper();

		// define sql statement
		String sql = "{call ? := getDunByClassId(?)}";

		// create prepared statement
		CallableStatement cs = conn.prepareCall(sql);

		// set prepared statement's parameters
		cs.registerOutParameter(1, OracleTypes.CURSOR);
		cs.setString(2, class_id);

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
			dun.setId(rs.getInt(1));
			dun.setName(rs.getString(2));
			dun.setCode(rs.getString(3));
			dun.setParlimen(ctrl.getParlimenByDunId(dun.getId()));
		}
		return dun;
	}
	
	public Dun getDunByStudentId(String student_id, Connection conn)
			throws Exception {
		// Object to keep results retrieved from database
		Dun dun = new Dun();
		ControllerWrapper ctrl = new ControllerWrapper();

		// define sql statement
		String sql = "{call ? := getDunByStudentId(?)}";

		// create prepared statement
		CallableStatement cs = conn.prepareCall(sql);

		// set prepared statement's parameters
		cs.registerOutParameter(1, OracleTypes.CURSOR);
		cs.setString(2, student_id);

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
			dun.setId(rs.getInt(1));
			dun.setName(rs.getString(2));
			dun.setCode(rs.getString(3));
			dun.setParlimen(ctrl.getParlimenByDunId(dun.getId()));
		}
		return dun;
	}

}
