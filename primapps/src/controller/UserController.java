package controller;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import oracle.jdbc.OracleTypes;
import bean.User;

public class UserController {

	public int checkUserId(String user_id, Connection conn) 
			throws Exception {
		// define sql statement
		String sql = "{call ? := checkUserId(?)}";
		
		// create prepared statement
		CallableStatement cs = conn.prepareCall(sql);
		
		// set prepared statement's parameters
		cs.registerOutParameter(1, OracleTypes.NUMBER);
		cs.setString(2, user_id);
		
		cs.executeQuery();

		int status = -1;

		status = cs.getInt(1);
		
		return status;
	}
	
	private boolean isUsernameValid(String username, Connection conn) 
			throws Exception {
		
		// define sql statement
		String sql = "{call ? := isUsernameValid(?)}";
		
		// create prepared statement
		CallableStatement cs = conn.prepareCall(sql);
		
		// set prepared statement's parameters
		cs.registerOutParameter(1, OracleTypes.NUMBER);
		cs.setString(2, username);
		
		cs.executeQuery();

		
		// if valid email
		if(cs.getInt(1) == 1) {
			return true;
		} else {
			// if invalid email
			return false;
		}
	}
	
	public int userLogin(String username, String password, Connection conn) 
			throws Exception {
		if(isUsernameValid(username, conn)) {
			
			// define sql statement
			String sql = "SELECT user_id FROM users WHERE upper(username) = upper(?)"
					+ " and password = ?";
			
			// create prepared statement
			PreparedStatement ps = conn.prepareStatement(sql);
			
			// set prepared statement's parameters
			ps.setString(1, username);
			ps.setString(2, password);

			/* execute the query on prepared statement and 
			 * create result set to keep result
			 */
			ResultSet rs = ps.executeQuery();
			
			// if valid password
			if(rs.next()) {
				int userId = rs.getInt(1);
				return userId;
			} else {
				// if invalid password
				conn.close();
				return -2;
			}
		} else {
			// invalid email
			return -1;
		}
	}
	
	public List<User> getAllUsers(Connection conn) throws Exception {
		// List to keep results retrieved from database
		List<User> users = new ArrayList<User>();
		
		// define sql statement
		String sql = "{call ? := getAllUsers()}";
		
		// create prepared statement
		CallableStatement cs = conn.prepareCall(sql);

		cs.registerOutParameter(1, OracleTypes.CURSOR);
		
		cs.executeQuery();
		/* execute the query on prepared statement and 
		 * create result set to keep result
		 */
		ResultSet rs = (ResultSet) cs.getObject(1);

		/* while reading result from result set,
		 * set result information to object
		 */
		while (rs.next()) {
			// Object to keep results retrieved from database
			User user = new User();
			
			user.setUser_id(rs.getString(1));
			user.setUsername(rs.getString(2));
			user.setPassword(rs.getString(3));
			user.setLevel(rs.getInt(4));
			user.setRole(rs.getString(5));
			user.setStatus(rs.getString(6));
			
			// add object to List
			users.add(user);
		}

		return users;
	}

	public User getUserById(String user_id, Connection conn) throws Exception {
		// Object to keep results retrieved from database
		User user = new User();
		
		// define sql statement
		String sql = "{call ? := getUserById(?)}";
		
		// create prepared statement
		CallableStatement cs = conn.prepareCall(sql);
		
		// set prepared statement's parameters
		cs.registerOutParameter(1, OracleTypes.CURSOR);
		cs.setString(2, user_id);
		
		cs.executeQuery();

		/* execute the query on prepared statement and 
		 * create result set to keep result
		 */
		ResultSet rs = (ResultSet) cs.getObject(1);

		/* while reading result from result set,
		 * set result information to object
		 */
		while (rs.next()) {
			user.setUser_id(rs.getString(1));
			user.setUsername(rs.getString(2));
			user.setPassword(rs.getString(3));
			user.setLevel(rs.getInt(4));
			user.setRole(rs.getString(5));
			user.setStatus(rs.getString(6));
		}

		return user;
	}

	public User getUserByUsername(String username, Connection conn) 
			throws Exception {
		// Object to keep results retrieved from database
		User user = new User();
		
		// define sql statement
		String sql = "{call ? := getUserByUsername(?)}";
		
		// create prepared statement
		CallableStatement cs = conn.prepareCall(sql);
		
		// set prepared statement's parameters
		cs.registerOutParameter(1, OracleTypes.CURSOR);
		cs.setString(2, username);
		
		cs.executeQuery();

		/* execute the query on prepared statement and 
		 * create result set to keep result
		 */
		ResultSet rs = (ResultSet) cs.getObject(1);

		/* while reading result from result set,
		 * set result information to object
		 */
		while (rs.next()) {
			user.setUser_id(rs.getString(1));
			user.setUsername(rs.getString(2));
			user.setPassword(rs.getString(3));
			user.setLevel(rs.getInt(4));
			user.setRole(rs.getString(5));
			user.setStatus(rs.getString(6));
		}

		return user;
	}
	
}
