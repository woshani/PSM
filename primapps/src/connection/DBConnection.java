package connection;

import java.sql.Connection;

import oracle.jdbc.pool.OracleDataSource;

public class DBConnection {
	/*
	 * Declaration of variables
	 */
	private Connection connection = null;
	private String connectionURL;
	private String username;
	private String password;
	private String serverIP;
	private String port;
	private String dbName;

	
	/**
	 * This method loads driver and create connection
	 * @return connection - connection created
	 * @throws Exception
	 */
	public Connection getConnection() throws Exception {
		
		/* Define the database to be used, the driver,
		 * the connection url, and the username and
		 * password to connect the database
		 */
	
		username = "prim2017";
		password = "prim";
		serverIP = "10.1.3.170";
		port = "1522";
		dbName = "PRIM";
		
		//username = "primv3";
		//password = "prim";
		//serverIP = "10.1.3.170";
		//port = "1522";
		//dbName = "prim";
		
		
		connectionURL = "jdbc:oracle:thin:" 
				+ "@" + serverIP + ":" + port + ":" + dbName;
		
		// Create the connection
		OracleDataSource ods = new OracleDataSource();
		ods.setUser(username);
		ods.setPassword(password);
		ods.setURL(connectionURL);
		connection = ods.getConnection();
		
		return connection;
	}
	
	/**
	 * This method closes database connection
	 * @param connection
	 * @throws Exception
	 */
	public void closeConnection(Connection connection) 
	throws Exception
	{
		//Close Database connection
		if (connection != null)
			connection.close();
	}

}