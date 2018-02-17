package joseph.webservice.utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import joseph.webservice.pojos.LoginPacket;

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
	
	String user = "sql3205145";
	String pass = "rDlDd1QCsz";
	String address = "jdbc:mysql://sql3.freesqldatabase.com:3306/sql3205145";
	
	public Dao()
	{
		conn = null;
		try{
			log.info("Connecting to " + address);
			Class.forName("com.mysql.jdbc.Driver");
			Properties properties = new Properties();
			properties.setProperty("user", user);
			properties.setProperty("password", pass);
			properties.setProperty("useSSL", "false");
			conn = DriverManager.getConnection(address, properties);
			log.info("Connected!");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public LoginPacket getAccount(String username)
	{
		try {			
			log.info("Getting accounts");
			Statement stmt = conn.createStatement() ;
			String query = "select * from UserAccounts where user = '" + username + "';" ;
			log.info("Query: " + query);
			ResultSet rs = stmt.executeQuery(query) ;
			conn.close();
			if(rs.getFetchSize() > 1) {
				String response = "More than 1 account for this user";
				log.log(Level.SEVERE, response);
				return new LoginPacket(response, "lol");
			}
			else if (rs.getFetchSize() == 0) {
				String response = "No account for this user";
				log.log(Level.SEVERE, response);
				return new LoginPacket(response, "lol");
			}
			else {
				LoginPacket response = new LoginPacket(rs.getString("user"), rs.getString("pass"));
				log.info("Returning: " + response.toString());
				return response;
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
			LoginPacket errorPacket = new LoginPacket(e.getMessage(), e.getStackTrace().toString());
			e.printStackTrace();
			log.log(Level.SEVERE, e.getMessage());
			return errorPacket;
		}
	}
	
	public List<LoginPacket> getAccounts()
	{
		try {			
			log.info("Getting all accounts");
			Statement stmt = conn.createStatement() ;
			String query = "select * from UserAccounts;" ;
			ResultSet rs = stmt.executeQuery(query) ;
			List<LoginPacket> responseList = new ArrayList<LoginPacket>();
			conn.close();
			
			if(rs.getFetchSize() > 1) {
				String response = "More than 1 account for this user";
				log.log(Level.SEVERE, response);
				responseList.add(new LoginPacket(response, "lol"));
				return responseList;
			}
			else if (rs.getFetchSize() == 0) {
				String response = "No account for this user";
				log.log(Level.SEVERE, response);
				responseList.add(new LoginPacket(response, "lol"));
				return responseList;
			}
			else {
				LoginPacket response = new LoginPacket(rs.getString("user"), rs.getString("pass"));
				log.info("Returning: " + response.toString());
				responseList.add(response);
				return responseList;
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
			LoginPacket errorPacket = new LoginPacket(e.getMessage(), e.getStackTrace().toString());
			e.printStackTrace();
			log.log(Level.SEVERE, e.getMessage());
			List<LoginPacket> responseList = new ArrayList<LoginPacket>();
			responseList.add(errorPacket);
			return responseList;
		}
	}
	
	public int addAccount(String username, String password)
	{
		try {			
			log.info("Adding user: " + username + ", " + password);
			Statement stmt = conn.createStatement() ;
			String query = "insert into users values('" + username + "', '" + password + "');" ;
			log.info("Query: " + query);
			int numRowsAffected = stmt.executeUpdate(query) ;
			log.info("Added " + numRowsAffected + " users");
			return numRowsAffected;
		} 
		catch (Exception e) {
			e.printStackTrace();
			log.log(Level.SEVERE, e.getMessage());
			return 0;
		}
	}
}
