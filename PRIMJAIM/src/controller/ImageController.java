package controller;

import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import oracle.jdbc.OracleTypes;

public class ImageController {

	/*
	 * public byte[] getPhoto (int iNumPhoto, Connection conn) throws Exception,
	 * SQLException { String req = null ; Blob img ; byte[] imgData = null ;
	 * Statement stmt = conn.createStatement ();
	 * 
	 * // Query req = "Select image From photos Where ImageID = " + iNumPhoto ;
	 * 
	 * ResultSet rset = stmt.executeQuery ( req );
	 * 
	 * while (rset.next ()) { img = rset.getBlob(1); imgData =
	 * img.getBytes(1,(int)img.length()); }
	 * 
	 * rset.close(); stmt.close();
	 * 
	 * return imgData ; }
	 */

	public byte[] getPhoto(String academicInstituitionId, Connection conn)
			throws Exception, SQLException {
		Blob img;
		byte[] imgData = null;

		// Query
		String sql = "{call ? := getPhoto(?)}";
		CallableStatement cs = conn.prepareCall(sql);

		cs.registerOutParameter(1, OracleTypes.BLOB);
		cs.setString(2, academicInstituitionId);

		cs.execute();

		img = cs.getBlob(1);
		imgData = img.getBytes(1, (int) img.length());

		cs.close();

		return imgData;
	}

	public byte[] getPhotoPelajar(String studentId, Connection conn)
			throws Exception, SQLException {
		Blob img;
		byte[] imgData = null;

		// Query
		String sql = "{call ? := getPhotoPelajar(?)}";
		CallableStatement cs = conn.prepareCall(sql);

		cs.registerOutParameter(1, OracleTypes.BLOB);
		cs.setString(2, studentId);

		cs.execute();

		img = cs.getBlob(1);
		imgData = img.getBytes(1, (int) img.length());

		cs.close();

		return imgData;
	}
}
