package controller;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;

import oracle.jdbc.OracleTypes;
import bean.Dun;
import bean.Parlimen;
import facade.ControllerWrapper;

public class ParlimenController {
	
	public Parlimen getParlimenByDunId(int dun_id, Connection conn)
			throws Exception {
		// Object to keep results retrieved from database
		Parlimen parlimen = new Parlimen();

		// define sql statement
		String sql = "{call ? := getParlimenByDunId(?)}";

		// create prepared statement
		CallableStatement cs = conn.prepareCall(sql);

		// set prepared statement's parameters
		cs.registerOutParameter(1, OracleTypes.CURSOR);
		cs.setInt(2, dun_id);

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
			parlimen.setId(rs.getInt(1));
			parlimen.setName(rs.getString(2));
			parlimen.setCode(rs.getString(3));
			parlimen.setDistrict(rs.getString(4));
			parlimen.setState(rs.getString(5));
		}
		return parlimen;
	}

}
