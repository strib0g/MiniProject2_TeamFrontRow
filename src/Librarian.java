package src;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Librarian {
	
	private String url = "jdbc:mysql://leia.skip.chalmers.se:3306/team1?autoReconnect=true&useSSL=false";
	private String username = "teamone";
	private String password = "HSaaD5vtp3K6QERq";
	private Connection conn;

	public Connection login() throws SQLException {
		try {
			conn = DriverManager.getConnection(url, username, password);
		} catch (Exception E) {
			return null;
		}
		return conn;
		// throw new SQLException("ERROR");
	} // This need to be improved.

	
public void removeBook(Connection conn) throws SQLException {
		
		try{
			login();
		
		// THIS IS PROBABLY WRONG AND NEEDS TESTING
		String sqlRemoveBook = "DELETE FROM book WHERE book_id = ?;";
		Statement pStatement = conn.prepareStatement(sqlRemoveBook);
		pStatement.executeUpdate(sqlRemoveBook);
		conn.close();
		}catch (SQLException e) {
			System.out.println(e);
			
		}

	}

}
