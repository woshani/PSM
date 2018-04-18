package controller;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import oracle.jdbc.OracleTypes;
import bean.Station;

public class StationController {

	public Station getStationByPoliceId(String police_id, Connection conn)
			throws Exception {
		// Object to keep results retrieved from database
		Station station = new Station();

		// define sql statement
		String sql = "{call ? := getStationByPoliceId(?)}";

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
			station.setId(rs.getInt(1));
			station.setName(rs.getString(2));
			station.setAddress(rs.getString(3));
			station.setTelNo(rs.getString(4));
			station.setTypes(rs.getString(5));
			station.setDistrict(rs.getString(6));
			station.setState(rs.getString(7));
		}

		return station;
	}

	public ArrayList<Station> getAllPoliceStation(Connection conn)
			throws Exception {
		// Object to keep results retrieved from database
		
		ArrayList<Station> stations = new ArrayList<Station>();

		// define sql statement
		String sql = "{call ? := getAllPoliceStation()}";

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
			
			Station station = new Station();
			
			station.setId(rs.getInt(1));
			station.setName(rs.getString(2));
			station.setAddress(rs.getString(3));
			station.setTelNo(rs.getString(4));
			station.setTypes(rs.getString(5));
			station.setDistrict(rs.getString(6));
			station.setState(rs.getString(7));
			
			stations.add(station);
		}

		return stations;
	}
	
	public Station getStationByStationId(String station_id, Connection conn)
			throws Exception {
		// Object to keep results retrieved from database
		Station station = new Station();

		// define sql statement
		String sql = "{call ? := getStationByStationId(?)}";

		// create prepared statement
		CallableStatement cs = conn.prepareCall(sql);

		// set prepared statement's parameters
		cs.registerOutParameter(1, OracleTypes.CURSOR);
		cs.setString(2, station_id);

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
			station.setId(rs.getInt(1));
			station.setName(rs.getString(2));
			station.setAddress(rs.getString(3));
			station.setTelNo(rs.getString(4));
			station.setTypes(rs.getString(5));
			station.setDistrict(rs.getString(6));
			station.setState(rs.getString(7));
		}

		return station;
	}
	
	public Station getStationByInstitutionId(String institution_id, Connection conn)
			throws Exception {
		// Object to keep results retrieved from database
		Station station = new Station();

		// define sql statement
		String sql = "{call ? := getStationByInstitutionId(?)}";

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
			station.setId(rs.getInt(1));
			station.setName(rs.getString(2));
			station.setAddress(rs.getString(3));
			station.setTelNo(rs.getString(4));
			station.setTypes(rs.getString(5));
			station.setDistrict(rs.getString(6));
			station.setState(rs.getString(7));
		}

		return station;
	}
	
	public Station getStationByClassId(String class_id, Connection conn)
			throws Exception {
		// Object to keep results retrieved from database
		Station station = new Station();

		// define sql statement
		String sql = "{call ? := getStationByClassId(?)}";

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
			station.setId(rs.getInt(1));
			station.setName(rs.getString(2));
			station.setAddress(rs.getString(3));
			station.setTelNo(rs.getString(4));
			station.setTypes(rs.getString(5));
			station.setDistrict(rs.getString(6));
			station.setState(rs.getString(7));
		}

		return station;
	}
	
	public Station getStationByStudentId(String student_id, Connection conn)
			throws Exception {
		// Object to keep results retrieved from database
		Station station = new Station();

		// define sql statement
		String sql = "{call ? := getStationByStudentId(?)}";

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
			station.setId(rs.getInt(1));
			station.setName(rs.getString(2));
			station.setAddress(rs.getString(3));
			station.setTelNo(rs.getString(4));
			station.setTypes(rs.getString(5));
			station.setDistrict(rs.getString(6));
			station.setState(rs.getString(7));
		}

		return station;
	}

}
