import java.sql.*;

public class Librarian {
	
	public void addUser(Connection conn,
						String pin,
						String name,
						String phone,
						String email,
						String adress) throws SQLException
	{
		String sql = "INSERT INTO member(pin_code, member_name, member_phone_number, member_email, "
				+ "member_address) VALUES(?, ?, ?, ?, ?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, pin);
		ps.setString(2, name);
		ps.setString(3, phone);
		ps.setString(4, email);
		ps.setString(5, adress);
		ps.executeUpdate();
		//TODO add a way to include creation date here
	}
	
	public void addBook(Connection conn,
						String title,
						String publisher, 
						String author,
						String shelf,
						String genre) throws SQLException
	{
		String sql = "INSERT INTO book(book_title, book_publisher, "
				+ "book_author, book_shelf, book_genre) VALUES(?, ?, ?, ?, ?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, title);
		ps.setString(2, publisher);
		ps.setString(3, author);
		ps.setString(4, shelf);
		ps.setString(5, genre);
		ps.executeUpdate();
	}
	public void deleteUser(Connection conn, int id) throws SQLException
    {
    	String sqlDelete = "DELETE FROM member WHERE id = ?";
   		PreparedStatement pStatement = conn.prepareStatement(sqlDelete);
    	pStatement.setInt(1, id);
    	pStatement.executeUpdate();
    }
	public void deleteBook(Connection conn, int id) throws SQLException
    {
    	String sqlDelete = "DELETE FROM book WHERE id = ?";
   		PreparedStatement pStatement = conn.prepareStatement(sqlDelete);
    	pStatement.setInt(1, id);
    	pStatement.executeUpdate();
    }

}
