package src;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.sql.Statement;

public class Library extends Librarian {

	
	public ResultSet sortTitle() throws SQLException {
		// SORT BY TITLE CODE TO TEST
		try {
			Connection conn = login();

			String sqlSortTitle = "SELECT* FROM book ORDER BY book_title;";
			Statement st = conn.createStatement();
			ResultSet result = st.executeQuery(sqlSortTitle);

			conn.close();
			return result;
		} catch (SQLException e) {
			System.out.print(e);
			return null;
		}

	}

	public ResultSet sortTitleDesc() throws SQLException {
		// SORT BY TITLE DESC CODE TO TEST
		try {
			Connection conn = login();

			String sqlSortTitleDesc = "SELECT * FROM book ORDER BY book_title DESC;";
			Statement st = conn.createStatement();
			ResultSet result = st.executeQuery(sqlSortTitleDesc);
			conn.close();
			return result;
		} catch (SQLException e) {
			System.out.println(e);
			return null;
		}

	}

	public ResultSet sortSurname() throws SQLException {
		// SORT BY SURNAME CODE TO TEST
		try {
			Connection conn = login();

			String sqlSortSurname = "SELECT * FROM book ORDER BY book_surname;";
			Statement st = conn.createStatement();
			ResultSet result = st.executeQuery(sqlSortSurname);

			conn.close();
			return result;
		} catch (SQLException e) {
			System.out.println(e);
			return null;
		}
	}

	public ResultSet sortSurnameDes() throws SQLException {
		// SORT BY SURNAME DESC CODE TO TEST
		try {
			Connection conn = login();

			String sqlSortSurnameDesc = "SELECT * FROM book ORDER BY book_surname DESC;";
			Statement st = conn.createStatement();
			ResultSet result = st.executeQuery(sqlSortSurnameDesc);

			conn.close();
			return result;
		} catch (SQLException e) {
			System.out.println(e);
			return null;
		}
	}

	public ResultSet sortFirstName() throws SQLException {
		// SORT BY FIRSTNAME CODE TO TEST
		try {
			Connection conn = login();

			String sqlSortFirstname = "SELECT * FROM book ORDER BY book_firstname;";
			Statement st = conn.createStatement();
			ResultSet result = st.executeQuery(sqlSortFirstname);

			conn.close();
			return result;
		} catch (SQLException e) {
			System.out.println(e);
			return null;
		}
	}

	public ResultSet sortFirstNameDesc() throws SQLException {
		// SORT BY FIRSTNAME DESC CODE TO TEST
		try {
			Connection conn = login();

			String sqlSortFirstnameDesc = "SELECT * FROM book ORDER BY book_firstname DESC;";
			Statement st = conn.createStatement();
			ResultSet result = st.executeQuery(sqlSortFirstnameDesc);

			conn.close();
			return result;
		} catch (SQLException e) {
			System.out.println(e);
			return null;
		}
	}

	public ResultSet sortGenre() throws SQLException {
		// SORT BY GENRE CODE TO TEST

		try {
			Connection conn = login();

			String sqlSortGenre = "SELECT * FROM book ORDER BY book_genre;";
			Statement st = conn.createStatement();
			ResultSet result = st.executeQuery(sqlSortGenre);

			conn.close();
			return result;
		} catch (SQLException e) {
			System.out.println(e);
			return null;
		}
	}

	public ResultSet sortGenreDesc() throws SQLException {
		// SORT BY GENRE DESC CODE TO TEST;
		try {
			Connection conn = login();

			String sqlSortGenreDesc = "SELECT * FROM book ORDER BY book_genre DESC;";
			Statement st = conn.createStatement();
			ResultSet result = st.executeQuery(sqlSortGenreDesc);

			conn.close();
			return result;
		} catch (SQLException e) {
			System.out.println(e);
			return null;
		}
	}

	public ResultSet sortPublisher() throws SQLException {
		// SORT BY PUBLISHER CODE TO TEST
		try {
			Connection conn = login();

			String sqlSortPublisher = "SELECT * FROM book ORDER BY book_publisher;";
			Statement st = conn.createStatement();
			ResultSet result = st.executeQuery(sqlSortPublisher);

			conn.close();
			return result;
		} catch (SQLException e) {
			System.out.println(e);
			return null;
		}
	}

	public ResultSet sortPublisherDesc() throws SQLException {
		// SORT BY PUBLISHER DESC CODE TO TEST
		try {
			Connection conn = login();

			String sqlSortPublisherDesc = "SELECT * FROM book ORDER BY bok_publisher DESC;";
			Statement st = conn.createStatement();
			ResultSet result = st.executeQuery(sqlSortPublisherDesc);
			conn.close();
			return result;
		} catch (SQLException e) {
			System.out.println(e);
			return null;
		}
	}

	public ResultSet sortShelf() throws SQLException {
		// SORT BY SHELF CODE TO TEST
		try {
			Connection conn = login();

			String sqlSortShelf = "SELECT * FROM book ORDER BY book_shelf_number;";
			Statement st = conn.createStatement();
			ResultSet result = st.executeQuery(sqlSortShelf);
			conn.close();
			return result;
		} catch (SQLException e) {
			System.out.println(e);
			return null;
		}
	}

	public ResultSet sortShelfDesc() throws SQLException {
		// SORT BY SHELF DESC CODE TO TEST
		try {
			Connection conn = login();

			String sqlSortShelfDesc = "SELECT * FROM book ORDER BY book_shelf_number DESC;";
			Statement st = conn.createStatement();
			ResultSet result = st.executeQuery(sqlSortShelfDesc);
			conn.close();
			return result;
		} catch (SQLException e) {
			System.out.println(e);
			return null;
		}
	}

	public ResultSet searchBookPublisher() throws SQLException {
		// SEARCH BOOK BY PUBLISHER
		try {
			Connection conn = login();

			String searchPublisher = "SELECT * FROM book WHERE book_publisher = ?;";
			Statement st = conn.createStatement();
			ResultSet result = st.executeQuery(searchPublisher);
			conn.close();
			return result;
		} catch (SQLException e) {
			System.out.println(e);
			return null;
		}
	}

	public ResultSet searchBookGenre() throws SQLException {
		// SEARCH BOOK BY GENRE
		try {
			Connection conn = login();

			String searchGenre = "SELECT * FROM book WHERE book_genre = ?;";
			Statement st = conn.createStatement();
			ResultSet result = st.executeQuery(searchGenre);
			conn.close();
			return result;
		} catch (SQLException e) {
			System.out.println(e);
			return null;
		}
	}

	public ResultSet searchAuthorAvailbilty() throws SQLException {
		// SEARCH AVAILABLE BOOKS BY AUTHOR
		try {
			Connection conn = login();

			String searchAvailableAuthor = "SELECT * FROM book WHERE book_availabilty = 1 AND WHERE book_author = ?;";
			Statement st = conn.createStatement();
			ResultSet result = st.executeQuery(searchAvailableAuthor);
			conn.close();
			return result;
		} catch (SQLException e) {
			System.out.println(e);
			return null;
		}
	}

	public ResultSet seachGenreAvailablity() throws SQLException {
		// SEARCH AVILABLE GENRES
		try {
			Connection conn = login();

			String searchAvailableGenre = "SELECT * FROM book WHERE book_availablity = 1 AND WHERE book_author = ?;";
			Statement st = conn.createStatement();
			ResultSet result = st.executeQuery(searchAvailableGenre);
			conn.close();
			return result;
		} catch (SQLException e) {
			System.out.println(e);
			return null;
		}
	}

}

