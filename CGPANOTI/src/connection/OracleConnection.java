package connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class OracleConnection {
	
	//declare variable for connection details
	private String driver;
    private String dbName;
    private String connectionURL;
    private String username;
    private String password;
    
    public OracleConnection() {
    	//initialised connection details
    	driver          ="oracle.jdbc.driver.OracleDriver";
        dbName          ="CGPADB";        
        connectionURL   ="jdbc:oracle:thin:@localhost:1521:";
        username        ="shay";
        password        ="shay";
    }
    
    //start the connection
    public Connection getConnection() throws Exception {

        // Load JDBC driver
        Class.forName(driver);

        // Establish connection
        Connection connection = DriverManager.getConnection(
                        connectionURL + dbName,
                        username, password);
        //System.out.println("database: " + dbName);

        return connection;
    }
    
    //Testing connection success or not
    public static void main(String[] args) {

    	OracleConnection conn = new OracleConnection();

		try {
			Connection connMain = conn.getConnection();

			if (connMain != null) {
				System.out.println("Berjaya");
			} else {
				System.out.println("abesla..");
			}

			connMain.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


    }
}
