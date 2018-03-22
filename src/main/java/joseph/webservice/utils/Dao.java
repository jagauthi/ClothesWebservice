package joseph.webservice.utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import joseph.webservice.pojos.*;

/* 	
 	To view database in a browser:
 		Navigate to phpmyadmin.co
 		Use below login credentials to log in
 	
	Database Connection Details:
		Server: sql3.freesqldatabase.com
		Name: sql3205145
		Username: sql3205145
		Password: rDlDd1QCsz
		Port number: 3306
 */

public class Dao {
	
	private static final Logger log = Logger.getLogger( Dao.class.getName() );
	Connection conn;
	
	public static final String SELECT_FROM_USERS = "select * from UserAccounts ";
	public static final String WHERE_USERNAME = "where username = '";
	public static final String INSERT_TO_USERS = "insert into UserAccounts ";
	public static final String DELETE_FROM_USERS = "delete from UserAccounts ";
	public static final String SELECT_FROM_ITEMS = "select * from Items ";
	
	String sqlUser = "sql3205145";
	String sqlPass = "rDlDd1QCsz";
	String sqlAddress = "jdbc:mysql://sql3.freesqldatabase.com:3306/sql3205145";
	
	public Dao() {
		conn = null;
		try{
			log.info("Connecting to " + sqlAddress);
			Class.forName("com.mysql.jdbc.Driver");
			Properties properties = new Properties();
			properties.setProperty("user", sqlUser);
			properties.setProperty("password", sqlPass);
			properties.setProperty("useSSL", "false");
			conn = DriverManager.getConnection(sqlAddress, properties);
			log.info("Connected!");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public LoginRequest login(LoginRequest loginPacket) throws SQLException {
		try {			
			log.info("Getting account for: " + loginPacket.getUsername() 
						+ " trying password " + loginPacket.getPassword());
			Statement stmt = conn.createStatement() ;
			String query = SELECT_FROM_USERS + WHERE_USERNAME + loginPacket.getUsername() + "';" ;
			log.info("Query: " + query);
			ResultSet rs = stmt.executeQuery(query) ;
			if(!rs.next()) {
				String response = "No account for this user";
				log.log(Level.SEVERE, response);
				conn.close();
				return new LoginRequest(0, response, response, response);
			}
			else {
				LoginRequest response;
				LoginRequest userAccount = new LoginRequest(rs.getInt("userId"), rs.getString("username"), 
								rs.getString("password"), rs.getString("email"));
				if(userAccount.getUsername().equals(loginPacket.getUsername())
						&& userAccount.getPassword().equals(loginPacket.getPassword())) {
					log.info("Returning: " + userAccount.toString());
					response = userAccount;
				}
				else {
					log.info("Wrong password entered for " + loginPacket.getUsername());
					log.info("\tEntered: " + loginPacket.getPassword());
					log.info("\tCorrect: " + userAccount.getPassword());
					response = new LoginRequest(-1, "Wrong password", "Wrong password", "Wrong password");
				}
				conn.close();
				return response;
			}
		} 
		catch (Exception e) {
			LoginRequest errorPacket = new LoginRequest(0, e.getMessage(), "", "");
			e.printStackTrace();
			log.log(Level.SEVERE, e.getMessage());
			conn.close();
			return errorPacket;
		}
	}

	public LoginRequest getAccount(String username) throws SQLException {
		try {			
			log.info("Getting account");
			Statement stmt = conn.createStatement() ;
			String query = SELECT_FROM_USERS + WHERE_USERNAME + username + "';" ;
			log.info("Query: " + query);
			ResultSet rs = stmt.executeQuery(query) ;
			if(!rs.next()) {
				String response = "No account for this user";
				log.log(Level.SEVERE, response);
				conn.close();
				return new LoginRequest(0, response, response, response);
			}
			else {
				LoginRequest response = new LoginRequest(rs.getInt("userId"), rs.getString("username"), 
								rs.getString("password"), rs.getString("email"));
				log.info("Returning: " + response.toString());
				conn.close();
				return response;
			}
		} 
		catch (Exception e) {
			LoginRequest errorPacket = new LoginRequest(0, e.getMessage(), "", "");
			e.printStackTrace();
			log.log(Level.SEVERE, e.getMessage());
			conn.close();
			return errorPacket;
		}
	}
	
	public List<LoginRequest> getAccounts() throws SQLException {
		try {			
			log.info("Getting all accounts");
			Statement stmt = conn.createStatement() ;
			String query = SELECT_FROM_USERS + ";" ;
			log.info("Query: " + query);
			ResultSet rs = stmt.executeQuery(query) ;
			List<LoginRequest> responseList = new ArrayList<LoginRequest>();
			log.info("List of accounts: " );
			while(rs.next()) {
				LoginRequest response = new LoginRequest(rs.getInt("userId"), rs.getString("username"), 
								rs.getString("password"), rs.getString("email"));
				log.info("\t" + response.toString() + "");
				responseList.add(response);
			}
			conn.close();
			return responseList;
		} 
		catch (Exception e) {
			log.log(Level.SEVERE, "Exception: " + e.getMessage());
			LoginRequest errorPacket = new LoginRequest(0, e.getMessage(), "", "");
			List<LoginRequest> responseList = new ArrayList<LoginRequest>();
			responseList.add(errorPacket);
			conn.close();
			return responseList;
		}
	}
	
	public int addAccount(LoginRequest user) throws SQLException {
		try {			
			log.info("Adding user: " + user.getUsername() + ", " + user.getPassword() + ", " + user.getEmail());
			Statement stmt = conn.createStatement() ;
			String query = INSERT_TO_USERS + " values"
					+ "(0, '" + user.getUsername() + "', '" + user.getPassword() + "', + '" + user.getEmail() + "');" ;
			log.info("Query: " + query);
			int numRowsAffected = stmt.executeUpdate(query) ;
			log.info("Added " + numRowsAffected + " users");
			conn.close();
			return numRowsAffected;
		} 
		catch (Exception e) {
			e.printStackTrace();
			log.log(Level.SEVERE, e.getMessage());
			conn.close();
			return 0;
		}
	}
	
	public int deleteAccount(String username) throws SQLException {
		try {			
			log.info("Deleting user: " + username);
			Statement stmt = conn.createStatement() ;
			String query = DELETE_FROM_USERS + WHERE_USERNAME + username + "';";
			log.info("Query: " + query);
			int numRowsAffected = stmt.executeUpdate(query) ;
			log.info("Deleted " + numRowsAffected + " users");
			conn.close();
			return numRowsAffected;
		} 
		catch (Exception e) {
			e.printStackTrace();
			log.log(Level.SEVERE, e.getMessage());
			conn.close();
			return 0;
		}
	}
	
	public List<ItemInfoResponse> getItems() throws SQLException {
		try {
			log.info("Getting all items");
			Statement stmt = conn.createStatement() ;
			String query = SELECT_FROM_ITEMS + ";" ;
			log.info("Query: " + query);
			ResultSet rs = stmt.executeQuery(query) ;
			List<ItemInfoResponse> responseList = new ArrayList<ItemInfoResponse>();
			log.info("List of items: " );
			while(rs.next()) {
				ItemInfoResponse response = new ItemInfoResponse(rs.getInt("itemNumber"), rs.getFloat("cost"),
						rs.getFloat("price"), rs.getString("description"), rs.getString("category"));
				log.info("\t" + response.toString() + "");
				responseList.add(response);
			}
			conn.close();
			return responseList;
		} 
		catch (Exception e) {
			log.log(Level.SEVERE, "Exception: " + e.getMessage());
			ItemInfoResponse errorPacket = new ItemInfoResponse(0, 0, 0, e.getMessage(), "");
			List<ItemInfoResponse> responseList = new ArrayList<ItemInfoResponse>();
			responseList.add(errorPacket);
			conn.close();
			return responseList;
		}
	}
}
