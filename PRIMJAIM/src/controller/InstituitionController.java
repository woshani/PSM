package controller;

import java.io.InputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import oracle.jdbc.OracleTypes;
import bean.Instituition;

public class InstituitionController {

	public int checkInstituitionId(String academic_instituition_id,
			Connection conn) throws Exception {
		// define sql statement
		String sql = "{call ? := checkInstituitionId(?)}";

		// create prepared statement
		CallableStatement cs = conn.prepareCall(sql);

		// set prepared statement's parameters
		cs.registerOutParameter(1, OracleTypes.NUMBER);
		cs.setString(2, academic_instituition_id);

		cs.execute();

		int status = -1;

		status = cs.getInt(1);

		return status;
	}

	public List<Instituition> getAllInstituitions(Connection conn)
			throws Exception {
		// List to keep results retrieved from database
		List<Instituition> instituitions = new ArrayList<Instituition>();

		// define sql statement
		String sql = "{call ? := getAllInstituitions()}";

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
			Instituition instituition = new Instituition();

			instituition.setAcademic_instituition_id(rs.getString(1));
			instituition.setAcademic_name(rs.getString(2));
			instituition.setAddress(rs.getString(3));
			instituition.setPostcode(rs.getString(4));
			instituition.setCity(rs.getString(5));
			instituition.setTelephone_number(rs.getString(6));
			instituition.setFax_number(rs.getString(7));
			instituition.setType(rs.getString(8));
			instituition.setDistrict(rs.getString(9));
			instituition.setPpd(rs.getString(10));

			// add object to List
			instituitions.add(instituition);
		}

		return instituitions;
	}

	public Instituition getInstituitionById(String academic_instituition_id,
			Connection conn) throws Exception {
		// Object to keep results retrieved from database
		Instituition instituition = new Instituition();

		// define sql statement
		String sql = "{call ? := getInstituitionById(?)}";

		// create prepared statement
		CallableStatement cs = conn.prepareCall(sql);

		// set prepared statement's parameters
		cs.registerOutParameter(1, OracleTypes.CURSOR);
		cs.setString(2, academic_instituition_id);

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
			instituition.setAcademic_instituition_id(rs.getString(1));
			instituition.setAcademic_name(rs.getString(2));
			instituition.setAddress(rs.getString(3));
			instituition.setPostcode(rs.getString(4));
			instituition.setCity(rs.getString(5));
			instituition.setTelephone_number(rs.getString(6));
			instituition.setFax_number(rs.getString(7));
			instituition.setType(rs.getString(8));
			instituition.setDistrict(rs.getString(9));
			instituition.setPpd(rs.getString(10));

		}

		return instituition;
	}

	public String getInstituitionByTeacherId(String teacherId, Connection conn)
			throws SQLException {

		String sql = "{call ? := getInstituitionByTeacherId(?)}";

		CallableStatement cs = conn.prepareCall(sql);

		cs.registerOutParameter(1, OracleTypes.VARCHAR);
		cs.setString(2, teacherId);

		cs.execute();

		String instituitionId = cs.getString(1);

		return instituitionId;
	}

	public List<Instituition> getAllInstituitionByPPD(String ppd,
			Connection conn) throws Exception {

		List<Instituition> instituitions = new ArrayList<Instituition>();
		Instituition instituition = null;

		String sql = "{call ? := getAllInstituitionByPPD(?)}";

		CallableStatement cs = null;
		ResultSet rs = null;

		try {
			cs = conn.prepareCall(sql);

			cs.registerOutParameter(1, OracleTypes.CURSOR);
			cs.setString(2, ppd);

			cs.execute();

			rs = (ResultSet) cs.getObject(1);

			while (rs.next()) {

				instituition = new Instituition();

				instituition = getInstituitionById(rs.getString(1), conn);

				instituitions.add(instituition);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			rs.close();
			cs.close();
			conn.close();
		}

		return instituitions;

	}

	public List<Instituition> getPPDSekolahByJenis(String ppd, int jenis,
			Connection conn) throws Exception {
		List<Instituition> instituitions = new ArrayList<Instituition>();
		Instituition instituition = null;

		String sql = "{call ? := getPPDSekolahByJenis(?,?)}";

		CallableStatement cs = null;
		ResultSet rs = null;

		try {
			cs = conn.prepareCall(sql);

			cs.registerOutParameter(1, OracleTypes.CURSOR);
			cs.setString(2, ppd);
			cs.setInt(3, jenis);

			cs.execute();

			rs = (ResultSet) cs.getObject(1);

			while (rs.next()) {

				instituition = new Instituition();

				instituition = getInstituitionById(rs.getString(1), conn);

				instituitions.add(instituition);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			rs.close();
			cs.close();
			conn.close();
		}

		return instituitions;
	}

	public List<Instituition> getPpdByCity(String city, Connection conn)
			throws Exception {

		List<Instituition> instituitions = new ArrayList<Instituition>();
		Instituition instituition = null;

		String sql = "{call ? := getPpdByCity(?)}";

		CallableStatement cs = null;
		ResultSet rs = null;

		try {
			cs = conn.prepareCall(sql);

			cs.registerOutParameter(1, OracleTypes.CURSOR);
			cs.setString(2, city);

			cs.execute();

			rs = (ResultSet) cs.getObject(1);

			while (rs.next()) {

				instituition = new Instituition();

				instituition = getInstituitionById(rs.getString(1), conn);

				instituitions.add(instituition);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			rs.close();
			cs.close();
			conn.close();
		}
		return instituitions;

	}

	public int updateInstitution(Instituition institution, InputStream logo,
			Connection conn) throws SQLException, Exception {

		// define sql statement
		String sql = "{call ? := updateInstitution(?,?,?,?,?,?,?,?,?,?)}";

		// create prepared statement
		CallableStatement cs = conn.prepareCall(sql);

		// set prepared statement's parameters
		cs.registerOutParameter(1, OracleTypes.INTEGER);
		cs.setString(2, institution.getAcademic_instituition_id());
		cs.setString(3, institution.getAcademic_name());
		cs.setString(4, institution.getAddress());
		cs.setString(5, institution.getPostcode());
		cs.setString(6, institution.getCity());
		cs.setString(7, institution.getDistrict());
		cs.setString(8, institution.getTelephone_number());
		cs.setString(9, institution.getFax_number());
		cs.setString(10, institution.getPpd());
		cs.setBlob(11, logo);
		
		cs.execute();
		
		int status = (Integer) cs.getObject(1);

		return status;
	}
	
	public List<Instituition> getAllInstitutionsByStationId(int station_id, int jenis,Connection conn)
			throws Exception {
		// Object to keep results retrieved from database
		List<Instituition> instituitions = new ArrayList<Instituition>();
		Instituition institution = new Instituition();

		// define sql statement
		String sql = "{call ? :=  getAllInsIdByStationId(?,?)}";

		// create prepared statement
		CallableStatement cs = conn.prepareCall(sql);

		// set prepared statement's parameters
		cs.registerOutParameter(1, OracleTypes.CURSOR);
		cs.setInt(2, station_id);
		cs.setInt(3, jenis);

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

				Instituition instituition = new Instituition();

				instituition = getInstituitionById(rs.getString(1), conn);

				instituitions.add(instituition);
		}

		return instituitions;
	}
	
	public List<Instituition> getAllInstitutionsByDunId(int dun_id, int jenis, Connection conn)
			throws Exception {
		// Object to keep results retrieved from database
		List<Instituition> instituitions = new ArrayList<Instituition>();
		Instituition institution = new Instituition();

		// define sql statement
		String sql = "{call ? :=  getAllInsIdByDunId(?,?)}";

		// create prepared statement
		CallableStatement cs = conn.prepareCall(sql);

		// set prepared statement's parameters
		cs.registerOutParameter(1, OracleTypes.CURSOR);
		cs.setInt(2, dun_id);
		cs.setInt(3, jenis);

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

				Instituition instituition = new Instituition();

				instituition = getInstituitionById(rs.getString(1), conn);

				instituitions.add(instituition);
		}

		return instituitions;
	}

}
