package joseph.webservice.classes;

import java.sql.*;
import java.util.Properties;

/* 	
	Database Connection Details:
		Server: sql9.freemysqlhosting.net
		Name: sql9195935
		Username: sql9195935
		Password: lp5FPtqSb4
		Port number: 3306
 */

public class Dao {
	Connection conn;
	public Dao()
	{
		conn = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Properties properties = new Properties();
			properties.setProperty("user", "sql9195935");
			properties.setProperty("password", "lp5FPtqSb4");
			properties.setProperty("useSSL", "false");
			conn = DriverManager.getConnection("jdbc:mysql://sql9.freemysqlhosting.net:3306/sql9195935", properties);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public DataPacket getAccount(String username)
	{
		try {			
			Statement stmt = conn.createStatement() ;
			String query = "select * from Users where User = '" + username + "';" ;
			ResultSet rs = stmt.executeQuery(query) ;
			
			int resultSetSize = 0;
			try {
			    rs.last();
			    resultSetSize = rs.getRow();
			    rs.beforeFirst();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			
			DataPacket[] packets = new DataPacket[resultSetSize];
			int counter = 0;
			while(rs.next())
			{
				packets[counter] = new DataPacket(rs.getString("User"), rs.getString("Pass"));
				counter++;
			}
			conn.close();
			return packets[0];
		} 
		catch (Exception e) {
			e.printStackTrace();
			DataPacket errorPacket = new DataPacket("error...", "1776-07-04");
			return errorPacket;
		}
	}
	
	public DataPacket[] getAccounts()
	{
		try {			
			Statement stmt = conn.createStatement() ;
			String query = "select * from Users;" ;
			ResultSet rs = stmt.executeQuery(query) ;
			
			int resultSetSize = 0;
			try {
			    rs.last();
			    resultSetSize = rs.getRow();
			    rs.beforeFirst();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			
			DataPacket[] packets = new DataPacket[resultSetSize];
			int counter = 0;
			while(rs.next())
			{
				packets[counter] = new DataPacket(rs.getString("User"), rs.getString("Pass"));
				counter++;
			}
			conn.close();
			return packets;
		} 
		catch (Exception e) {
			e.printStackTrace();
			DataPacket errorPackets[] = {new DataPacket("error...", "1776-07-04")};
			return errorPackets;
		}
	}
	
	public int addAccount(String username, String password)
	{
		try {			
			Statement stmt = conn.createStatement() ;
			String query = "insert into Users values('" + username + "', '" + password + "');" ;
			int numRowsAffected = stmt.executeUpdate(query) ;
			return numRowsAffected;
		} 
		catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
}
