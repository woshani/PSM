package controller;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

import bean.DataModel;
import bean.DataResult;
import bean.ExcelData;
import bean.NotificationMessage;
import bean.PushNoti;
import bean.Staff;
import bean.Student;
import connection.OracleConnection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;
import oracle.sql.STRUCT;
import oracle.sql.StructDescriptor;

public class MobileController {

	// declare important part here!
	private OracleConnection dbconn;
	private Connection conn;

	public MobileController() {
		dbconn = new OracleConnection();
	}

	public Student loginMobile(String matricNo, String pass, String token) {
		Student stud = new Student();
		String sql = "{call loginmobile(?,?,?,?,?,?)}";
		CallableStatement cstm = null;

		try {
			conn = dbconn.getConnection();
			cstm = conn.prepareCall(sql);
			cstm.setString(1, matricNo);
			cstm.setString(2, pass);
			cstm.registerOutParameter(3, OracleTypes.VARCHAR);
			cstm.registerOutParameter(4, OracleTypes.VARCHAR);
			cstm.registerOutParameter(5, OracleTypes.VARCHAR);
			cstm.setString(6, token);

			cstm.executeQuery();

			stud.setMatric(matricNo);
			stud.setName(cstm.getString(4));
			stud.setPhone(cstm.getString(5));

			cstm.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return stud;
	}

	public String logoutMobile(String matricNo) {
		String res = null;
		String sql = "{call logoutmobile(?,?)}";
		CallableStatement cstm = null;

		try {
			conn = dbconn.getConnection();
			cstm = conn.prepareCall(sql);
			cstm.setString(1, matricNo);
			cstm.registerOutParameter(2, OracleTypes.VARCHAR);

			cstm.executeQuery();
			res = cstm.getString(2);
			cstm.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}

	public ArrayList<PushNoti> getPushNoti(String sesisons, String sender) {
		ArrayList<PushNoti> ar = new ArrayList<PushNoti>();

		String sql = "{call sentNotiMulti(?,?)}";
		CallableStatement cstm = null;

		try {
			conn = dbconn.getConnection();
			cstm = conn.prepareCall(sql);
			cstm.setString(1, sesisons);
			cstm.registerOutParameter(2, OracleTypes.CURSOR);

			cstm.executeQuery();
			ResultSet rs = (ResultSet) cstm.getObject(2);

			while (rs.next()) {
				PushNoti pn = new PushNoti();

				String title = "Result for: " + sesisons;
				String message = "Your GPA is " + String.valueOf(rs.getInt(3));
				NotificationMessage nm = new NotificationMessage(title, message);

				pn.setToken(rs.getString(2));
				pn.setMatric(rs.getString(1));
				pn.setMsj(nm);
				pn.setSender(sender);
				ar.add(pn);
			}
			cstm.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ar;
	}

	public int insertMessage(ArrayList<PushNoti> pn) {

		String sql = "{call insnulkmessage(?)}";
		CallableStatement cstm = null;
		int statusquery = 0;
		try {
			conn = dbconn.getConnection();
			cstm = conn.prepareCall(sql);

			// create struct descriptor, 'MESSAGEDATA' nama kene sama mcm oracle object
			StructDescriptor structDescriptor = StructDescriptor.createDescriptor("MESSAGEDATA", conn);

			STRUCT[] structs = new STRUCT[pn.size()];
			for (int index = 0; index < pn.size(); index++) {
				PushNoti ex = pn.get(index);
				Object[] params = new Object[4];
				params[0] = ex.getMsj().getTitle();
				params[1] = ex.getMsj().getMessage();
				params[2] = ex.getMatric();
				params[3] = ex.getSender();
				STRUCT struct = new STRUCT(structDescriptor, conn, params);
				structs[index] = struct;
			}
			// create array descriptor ,'MESSAGEDATAARRAY' kene sama mcm dalam oracle
			ArrayDescriptor arrDesc = ArrayDescriptor.createDescriptor("MESSAGEDATAARRAY", conn);

			ARRAY arrayExcelData = new ARRAY(arrDesc, conn, structs);
			cstm.setArray(1, arrayExcelData);
			statusquery = cstm.executeUpdate();
			conn.commit();
			conn.close();

			cstm.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return statusquery;
	}

	public String changePass(String matric, String oldPass, String newPass) {
		String res = null;

		String sql = "{call change_pass(?,?,?,?)}";
		CallableStatement cstm = null;
		try {
			conn = dbconn.getConnection();
			cstm = conn.prepareCall(sql);
			cstm.setString(1, oldPass);
			cstm.setString(2, newPass);
			cstm.setString(3, matric);
			cstm.registerOutParameter(4, OracleTypes.VARCHAR);
			cstm.executeQuery();

			res = cstm.getString(4);
			cstm.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}

	public ArrayList<DataModel> getMessage(String matric) {
		ArrayList<DataModel> res = new ArrayList<DataModel>();

		String sql = "{call selMessage(?,?)}";
		CallableStatement cstm = null;
		try {
			conn = dbconn.getConnection();
			cstm = conn.prepareCall(sql);
			cstm.setString(1, matric);
			cstm.registerOutParameter(2, OracleTypes.CURSOR);
			cstm.executeQuery();
			ResultSet rs = (ResultSet) cstm.getObject(2);

			while (rs.next()) {
				DataModel al = new DataModel();
				al.setMessageId(rs.getString(1));
				al.setMessageTitle(rs.getString(2));
				al.setMessageBody(rs.getString(3));
				al.setReceiver(rs.getString(4));
				al.setSender(rs.getString(5));

				java.sql.Timestamp dbSqlTimestamp = rs.getTimestamp(6);
				String s = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(dbSqlTimestamp);
				al.setDate(s);
				res.add(al);
			}
			cstm.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}

	public ArrayList<String> getCurrentGpa(String matrik) {
		ArrayList<String> res = new ArrayList<String>();

		String sql = "{call GETCURRENTGPA(?,?,?)}";
		CallableStatement cstm = null;
		try {
			conn = dbconn.getConnection();
			cstm = conn.prepareCall(sql);
			cstm.setString(1, matrik);
			cstm.registerOutParameter(2, OracleTypes.NUMBER);
			cstm.registerOutParameter(3, OracleTypes.VARCHAR);
			cstm.executeQuery();
			res.add(0,String.valueOf(cstm.getDouble(2)));
			res.add(1,cstm.getString(3));
			cstm.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}
	
	public ArrayList<DataResult> getDataResult(String matric) {
		ArrayList<DataResult> res = new ArrayList<DataResult>();

		String sql = "{call GETALLSEMRESULT(?,?)}";
		CallableStatement cstm = null;
		try {
			conn = dbconn.getConnection();
			cstm = conn.prepareCall(sql);
			cstm.setString(1, matric);
			cstm.registerOutParameter(2, OracleTypes.CURSOR);
			cstm.executeQuery();
			ResultSet rs = (ResultSet) cstm.getObject(2);

			while (rs.next()) {
				DataResult al = new DataResult();
				al.setGpa(String.valueOf(rs.getDouble(2)));
				al.setSession(rs.getString(1));
				res.add(al);
			}
			cstm.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}
}
