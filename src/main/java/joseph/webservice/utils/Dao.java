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
	public static final String INSERT_TO_USERS = "insert into UserAccounts ";
	public static final String DELETE_FROM_USERS = "delete from UserAccounts ";
	public static final String SELECT_FROM_ITEMS = "select * from Items ";
	public static final String SELECT_FROM_USER_CART = "select * from UserCart ";
	public static final String INSERT_TO_USER_CART = "insert into UserCart ";
	public static final String DELETE_FROM_USER_CART = "delete from UserCart ";
	public static final String SELECT_CART = "select i.itemNumber, i.cost, i.price, i.description, i.category ";
	public static final String WHERE_USERID = "where userId = '";
	public static final String WHERE_USERNAME = "where username = '";
	public static final String AND_ITEMNUMBER = "and itemNumber = ";
	
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
	
	public List<ItemInfo> getItems() throws SQLException {
		try {
			log.info("Getting all items");
			Statement stmt = conn.createStatement() ;
			String query = SELECT_FROM_ITEMS + ";" ;
			log.info("Query: " + query);
			ResultSet rs = stmt.executeQuery(query) ;
			List<ItemInfo> responseList = new ArrayList<ItemInfo>();
			log.info("Returning catalog: " );
			while(rs.next()) {
				ItemInfo response = new ItemInfo(rs.getInt("itemNumber"), rs.getFloat("cost"),
						rs.getFloat("price"), rs.getString("description"), rs.getString("category"));
				log.info("\t" + response.toString() + "");
				responseList.add(response);
			}
			conn.close();
			return responseList;
		} 
		catch (Exception e) {
			log.log(Level.SEVERE, "Exception: " + e.getMessage());
			ItemInfo errorPacket = new ItemInfo(0, 0, 0, e.getMessage(), "");
			List<ItemInfo> responseList = new ArrayList<ItemInfo>();
			responseList.add(errorPacket);
			conn.close();
			return responseList;
		}
	}
	
	public int addToCart(UserItemsRequest addToCartRequest) throws SQLException {
		try {
			Statement stmt = conn.createStatement() ;
			int numRowsAffected = 0 ;
			for(ItemInfo item : addToCartRequest.getItems()) {
				log.info("Adding item to cart: " + item.getDescription());
				String query = INSERT_TO_USER_CART + "values"
						+ "('" + addToCartRequest.getUser() + "', " + item.getItemNumber() + ");";
				log.info("Query: " + query);
				numRowsAffected += stmt.executeUpdate(query) ;
				log.info("Added " + numRowsAffected + " items to cart");
			}
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
	
	public List<ItemInfo> getCartForUser(String username) throws SQLException {
		List<ItemInfo> itemList;
		try {
			log.info("Getting cart for " + username);
			Statement stmt = conn.createStatement() ;
			String query = SELECT_CART + "from Items i, UserCart c "
					 + "where c.username = '" + username 
					 + "' and c.itemNumber = i.itemNumber";
			 
			log.info("Query: " + query);
			ResultSet rs = stmt.executeQuery(query) ;
			itemList = new ArrayList<ItemInfo>();
			while(rs.next()) {
				itemList.add( new ItemInfo(rs.getInt("itemNumber"), rs.getFloat("cost"),
						rs.getFloat("price"), rs.getString("description"), rs.getString("category")) );
			}
			log.info("Returning: " + itemList);
			conn.close();
			return itemList;
		} 
		catch (Exception e) {
			log.log(Level.SEVERE, "Exception: " + e.getMessage());
			ItemInfo errorPacket = new ItemInfo(0, 0, 0, e.getMessage(), "");
			itemList = new ArrayList<ItemInfo>();
			itemList.add(errorPacket);
			log.info("Returning: " + itemList);
			conn.close();
			return itemList;
		}
	}
	
	public List<ItemInfo> removeFromCart(UserItemsRequest removeFromCartRequest) throws SQLException {
		List<ItemInfo> itemList = new ArrayList<ItemInfo>();
		try {			
			String query;
			int numRowsAffected = 0;
			Statement stmt = conn.createStatement();
			for(ItemInfo item : removeFromCartRequest.getItems()) {
				log.info("\tDeleting from cart: " + item.getDescription());
				query = DELETE_FROM_USER_CART + WHERE_USERNAME + removeFromCartRequest.getUser() + "' "
						+ AND_ITEMNUMBER + item.getItemNumber() + ";";
				log.info("\tQuery: " + query);
				numRowsAffected += stmt.executeUpdate(query) ;
			}
			log.info("Deleted " + numRowsAffected + " items");
			
			log.info("Getting cart for " + removeFromCartRequest.getUser());
			query = SELECT_CART + "from Items i, UserCart c "
			 + "where c.username = '" + removeFromCartRequest.getUser() 
			 + "' and c.itemNumber = i.itemNumber";
			log.info("Query: " + query);
			ResultSet rs = stmt.executeQuery(query) ;
			while(rs.next()) {
				itemList.add( new ItemInfo(rs.getInt("itemNumber"), rs.getFloat("cost"),
						rs.getFloat("price"), rs.getString("description"), rs.getString("category")) );
			}
			log.info("Returning: " + itemList);
			
			conn.close();
			return itemList;
		} 
		catch (Exception e) {
			log.log(Level.SEVERE, "Exception: " + e.getMessage());
			ItemInfo errorPacket = new ItemInfo(0, 0, 0, e.getMessage(), "");
			itemList = new ArrayList<ItemInfo>();
			itemList.add(errorPacket);
			log.info("Returning: " + itemList);
			conn.close();
			return itemList;
		}
	}
}
