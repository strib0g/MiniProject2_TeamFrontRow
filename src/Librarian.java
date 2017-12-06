package src;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
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

		try {
			login();

			// THIS IS PROBABLY WRONG AND NEEDS TESTING
			String sqlRemoveBook = "DELETE FROM book WHERE book_id = ?;";
			Statement pStatement = conn.prepareStatement(sqlRemoveBook);
			pStatement.executeUpdate(sqlRemoveBook);
			conn.close();
		} catch (SQLException e) {
			System.out.println(e);

		}

	}

	public ResultSet totalFines(Connection conn) throws SQLException {
		// TOTAL SOME OF FINES
		try {
			login();

			String totalFines = "SELECT SUM member_total_fine FROM book;";
			Statement st = conn.createStatement();
			ResultSet result = st.executeQuery(totalFines);
			conn.close();
			return result;
		} catch (SQLException e) {
			System.out.println(e);
			return null;
		}
	}

	public ResultSet finesTable(Connection conn) throws SQLException {
		// SHOW THE FINES TABLE
		try {
			login();

			String showFinesTable = "SELECT * FROM fine;";
			Statement st = conn.createStatement();
			ResultSet result = st.executeQuery(showFinesTable);
			conn.close();
			return result;
		} catch (SQLException e) {
			System.out.println(e);
			return null;
		}

	}

	public ResultSet finesDue(Connection conn) throws SQLException{
		//SHOWS THE LATEST FINE DUE FROM FINEWS TABLE
		try {
			login();
			
			String finesDue = "SELECT * FROM fine ORDERED BY fine_created;";
			Statement st = conn.createStatement();
			ResultSet result = st.executeQuery(finesDue);
			conn.close();
			return result;
		}catch (SQLException e) {
			System.out.println(e);
			return null;
		}
	}

	public ResultSet searchMemberFine(Connection conn) throws SQLException{
		//SEARCH FOR A MEMBERS FINES
		try {
			login();
			
			String memberFine = "SELECT member_total_fine FROM member WHERE member_id = ?;";
			Statement st = conn.createStatement();
			ResultSet result = st.executeQuery(memberFine);
			conn.close();
			return result;
		}catch (SQLException e) {
			System.out.println(e);
			return null;
		}
	}

	public ResultSet searchMemberName(Connection conn) throws SQLException {

		//SEARCH FOR MEMBER CODE	
		try {
			login();
			
			String searchMemberName = "SELECT * FROM member WHERE member_name = ?;";
			Statement st = conn.createStatement();
			ResultSet result = st.executeQuery(searchMemberName);
			conn.close();
			return result;
		} catch (SQLException e) {
			System.out.println(e);
			return null;
		}
	}

	public ResultSet searchMemberId(Connection conn) throws SQLException {
		//SEARCH FOR MEMBER BY NAME
		try {
			login();
			
			String searchMemberId = "SELECT * FROM member WHERE member_id = ?;";
			Statement st = conn.createStatement();
			ResultSet result = st.executeQuery(searchMemberId);
			conn.close();
			return result;
		}catch (SQLException e) {
			System.out.println(e);
			return null;
		}
	}

	

}
