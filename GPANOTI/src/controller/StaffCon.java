package controller;
import connection.OracleConnection;
import oracle.jdbc.OracleTypes;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bean.Staff;

public class StaffCon {
	//declare important part here!
	private OracleConnection dbconn;
	private Connection conn;
	
	public StaffCon() {
		dbconn = new OracleConnection();
	}
	
	/*
	 * method for get staff detail during login
	 */
	public Staff getStaffDetail(String username,String password) {
		Staff staff = new Staff();
		
		String sql = "{call login(?,?,?,?,?,?,?,?)}";
		CallableStatement cstm = null;
		
		try {
			conn = dbconn.getConnection();
			cstm = conn.prepareCall(sql);
			cstm.setString(1, username);
			cstm.setString(2, password);
			cstm.registerOutParameter(3, OracleTypes.VARCHAR);
			cstm.registerOutParameter(4, OracleTypes.VARCHAR);
			cstm.registerOutParameter(5, OracleTypes.VARCHAR);
			cstm.registerOutParameter(6, OracleTypes.VARCHAR);
			cstm.registerOutParameter(7, OracleTypes.VARCHAR);
			cstm.registerOutParameter(8, OracleTypes.VARCHAR);
			cstm.executeQuery();
			
			staff.setStaffID(cstm.getString(3));
			staff.setFullName(cstm.getString(4));
			staff.setAddress(cstm.getString(5));
			staff.setEmail(cstm.getString(6));
			staff.setPhoneNum(cstm.getString(7));
			staff.setPassword(cstm.getString(8));
			
			cstm.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return staff;
	}
	
    public ArrayList<Staff> getListStaff() throws Exception {
        String sql = "{call getListStaff(?)}";
        CallableStatement cstm = null;

        ArrayList<Staff> staffs = new ArrayList<>();
        try {
            conn = dbconn.getConnection();
            cstm = conn.prepareCall(sql);
            cstm.registerOutParameter(1, OracleTypes.CURSOR);

            cstm.executeQuery();
            ResultSet rs = (ResultSet) cstm.getObject(1);

            while (rs.next()) {
                Staff staff = new Staff();
                staff.setStaffID(rs.getString(1));
                staff.setAddress(rs.getString(3));
                staff.setEmail(rs.getString(4));
                staff.setFullName(rs.getString(2));
                staff.setPhoneNum(rs.getString(5));
                staffs.add(staff);
            }
            cstm.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return staffs;
    }
    
    public String registerStaff(Staff staff) {
    	String msg = null;
    	String sql = "{call ins_staff(?,?,?,?,?,?,?) }";
    	CallableStatement cstm = null;
    	int status = 0 ;
    	
    	try {
            conn = dbconn.getConnection();
            cstm = conn.prepareCall(sql);
            cstm.setString(1, staff.getStaffID());
            cstm.setString(2, staff.getFullName());
            cstm.setString(3, staff.getAddress());
            cstm.setString(4, staff.getEmail());
            cstm.setString(5, staff.getPhoneNum());
            cstm.setString(6, staff.getPassword());
            cstm.setString(7, staff.getCreateBy());
            status = cstm.executeUpdate();
            cstm.close();
            System.out.print(staff.getCreateBy());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            status = ((SQLException) e).getErrorCode();
        }
    	if (status == 1) {
			msg = "SUCCESS";
		} else if (status == 20010) {
			msg = "ALREADY";
		}
    	return msg;
    }
	
}
