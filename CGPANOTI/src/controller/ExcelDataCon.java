package controller;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.util.ArrayList;

import bean.ExcelData;
import connection.OracleConnection;
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
	            Object[] params = new Object[11];
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
	
}
