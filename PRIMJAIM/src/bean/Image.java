package bean;

import java.sql.*;
import oracle.jdbc.*;


public class Image
{
  /*-------------------------
   *   Get the Blob image
   *------------------------*/
  public static byte[] getPhoto (OracleConnection conn, int iNumPhoto)
       throws Exception, SQLException
  {
    String req = "" ;
    Blob img ;
    byte[] imgData = null ;
    Statement stmt = conn.createStatement ();
    
    // Query
    req = "Select image From IMAGES Where ImageID = " + iNumPhoto ;
    
    ResultSet rset  = stmt.executeQuery ( req ); 
    
    while (rset.next ())
    {    
      img = rset.getBlob(1);
      imgData = img.getBytes(1,(int)img.length());
    }    
    
    rset.close();
    stmt.close();
    
    return imgData ;
  }
  
} 
