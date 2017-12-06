import java.sql.*;
import java.util.*;

public class Librarian {
	
	public static void addUser(Connection conn,
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
	
	public static void addBook(Connection conn,
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
		//TODO creation date
	}
	public static void deleteUser(Connection conn, int id) throws SQLException
    {
    	String sqlDelete = "DELETE FROM member WHERE id = ?";
   		PreparedStatement pStatement = conn.prepareStatement(sqlDelete);
    	pStatement.setInt(1, id);
    	pStatement.executeUpdate();
    }
	public static void deleteBook(Connection conn, int id) throws SQLException
    {
    	String sqlDelete = "DELETE FROM book WHERE id = ?";
   		PreparedStatement pStatement = conn.prepareStatement(sqlDelete);
    	pStatement.setInt(1, id);
    	pStatement.executeUpdate();
    }
	
	private static boolean isValidUserColumn(String column)
	{
		for(UserColumns c : UserColumns.values())
		{
			if(c.getColumn().equals(column))
			{
				return true;
			}
		}
		return false;
	}
	
	private static boolean isValidBookColumn(String column)
	{
		for(BookColumns c : BookColumns.values())
		{
			if(c.getColumn().equals(column))
			{
				return true;
			}
		}
		return false;
	}
	
	public static void updateUser(Connection conn, int id, String column, String input) throws SQLException
	{
		if(Librarian.isValidUserColumn(column))
		{
			String sqlUpdate = "UPDATE member SET ? = ? WHERE id = ?";
			PreparedStatement pStatement = conn.prepareStatement(sqlUpdate);
			
			pStatement.setString(1, column);
			pStatement.setString(2, input);
			pStatement.setInt(3, id);
			pStatement.executeUpdate();
			//TODO untested code, be careful
		}
	}
	
	public static void updateBook(Connection conn, int id, String column, String input) throws SQLException
	{
		if(Librarian.isValidBookColumn(column))
		{
			String sqlUpdate = "UPDATE book SET ? = ? WHERE id = ?";
			PreparedStatement pStatement = conn.prepareStatement(sqlUpdate);
			
			pStatement.setString(1, column);
			pStatement.setString(2, input);
			pStatement.setInt(3, id);
			pStatement.executeUpdate();
			//TODO untested code, be careful
		}
	}

}
