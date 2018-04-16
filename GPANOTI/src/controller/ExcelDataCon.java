package controller;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bean.ExcelData;
import bean.Staff;
import connection.OracleConnection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;
import oracle.sql.STRUCT;
import oracle.sql.StructDescriptor;

public class ExcelDataCon {
	//declare important part here!
		private OracleConnection dbconn;
		private Connection conn;
		
	public ExcelDataCon() {
		dbconn = new OracleConnection();
	}
	
	public int insertdata(ArrayList<ExcelData> exs){
		int statusquery = 0;
		String sql = "{call insexceldata(?)}";
		CallableStatement cstm = null;
		
		try {
			conn = dbconn.getConnection();
			cstm = conn.prepareCall(sql);
			
			//create struct descriptor, 'EXCELDATAOBJECT' nama kene sama mcm oracle object
			StructDescriptor structDescriptor = StructDescriptor.createDescriptor("EXCELDATAOBJECT", conn);

	        STRUCT[] structs = new STRUCT[exs.size()];
	        for (int index = 0; index < exs.size(); index++)
	        {
	            ExcelData ex = exs.get(index);
	            Object[] params = new Object[12];
	            params[0] = ex.getBil();
	            params[1] = ex.getName();
	            params[2] = ex.getMatricNumber();
	            params[3] = ex.getCourse();
	            params[4] = ex.getCohort();
	            params[5] = ex.getMuet();
	            params[6] = ex.getYearsem();
	            params[7] = ex.getStatus();
	            params[8] = ex.getGpa();
	            params[9] = ex.getAcademicAdvisor();
	            params[10] = ex.getPhoneNumber();
	            params[11] = ex.getSesi();
	            
	            STRUCT struct = new STRUCT(structDescriptor, conn, params);
	            structs[index] = struct;
	        }
			
			
			// create array descriptor ,'EXCELDATAOBJECTARRAY' kene sama mcm dalam oracle
			ArrayDescriptor arrDesc = ArrayDescriptor.createDescriptor("EXCELDATAOBJECTARRAY", conn);
			
			ARRAY arrayExcelData = new ARRAY( arrDesc, conn, structs);
			cstm.setArray(1, arrayExcelData);
			statusquery = cstm.executeUpdate();
			conn.commit();
	        conn.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return statusquery;
	}
	
	public ArrayList<String> getSesiList(){
		ArrayList<String> sesis = new ArrayList<String>();
		
		String sql = "{call getSesi(?)}";
		CallableStatement cstm = null;
		
        try {
            conn = dbconn.getConnection();
            cstm = conn.prepareCall(sql);
            cstm.registerOutParameter(1, OracleTypes.CURSOR);
            cstm.executeQuery();
            ResultSet rs = (ResultSet) cstm.getObject(1);

            while (rs.next()) {
            	sesis.add(rs.getString(1));
            }
            cstm.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return sesis;
	}
	
	public ArrayList<String> getCourseList(String sessions){
		ArrayList<String> courses = new ArrayList<String>();
		
		String sql = "{call getCourse(?,?)}";
		CallableStatement cstm = null;
		
        try {
            conn = dbconn.getConnection();
            cstm = conn.prepareCall(sql);
            cstm.setString(1, sessions);
            cstm.registerOutParameter(2, OracleTypes.CURSOR);
            cstm.executeQuery();
            ResultSet rs = (ResultSet) cstm.getObject(2);

            while (rs.next()) {
            	courses.add(rs.getString(1));
            }
            cstm.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return courses;
	}
	
	public ArrayList<String> getYearList(String sessions,String course){
		ArrayList<String> Years = new ArrayList<String>();
		
		String sql = "{call getYears(?,?,?)}";
		CallableStatement cstm = null;
		
        try {
            conn = dbconn.getConnection();
            cstm = conn.prepareCall(sql);
            cstm.setString(1, course);
            cstm.setString(2, sessions);
            cstm.registerOutParameter(3, OracleTypes.CURSOR);
            cstm.executeQuery();
            ResultSet rs = (ResultSet) cstm.getObject(3);

            while (rs.next()) {
            	Years.add(rs.getString(1));
            }
            cstm.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return Years;
	}
	
	public ArrayList<ArrayList<String>> getBySesi(String sesi,String kos) {
		//int res = 0;
		
		String sql = "{call getBySession(?,?,?)}";
		CallableStatement cstm = null;
		ArrayList<ArrayList<String>> res = new ArrayList<ArrayList<String>>();
		
        try {
            conn = dbconn.getConnection();
            cstm = conn.prepareCall(sql);
            cstm.setString(1, sesi);
            cstm.setString(2, kos);
            cstm.registerOutParameter(3, OracleTypes.CURSOR);
            cstm.executeQuery();
            ResultSet rs = (ResultSet) cstm.getObject(3);
            while (rs.next()) {
            	ArrayList<String> x = new ArrayList<String>();
            	x.add(String.valueOf(rs.getInt(1)));
            	x.add(rs.getString(2));
            	res.add(x);
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

