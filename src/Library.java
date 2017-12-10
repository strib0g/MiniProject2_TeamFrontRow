import java.sql.*;

public class Library {

    private String url = "jdbc:mysql://leia.skip.chalmers.se:3306/team1?autoReconnect=true&useSSL=false";
    private String username = "teamone";
    private String password = "HSaaD5vtp3K6QERq";
    private static int currentUser;
    private static int currentLib;

    public Connection connect() throws SQLException {
        Connection conn;
        try {
            conn = DriverManager.getConnection(url, username, password);
        }catch (Exception E){
            return null;
        }
        return conn;
        //throw new SQLException("ERROR");
    } // This need to be improved.
    
    public void loginUser(String email, int pin) throws SQLException, Exception
    {
    	if(currentUser == 0 && currentLib == 0)
    	{
    		Connection conn = this.connect();
        	String sql = "SELECT `member_id` FROM `member` WHERE `member_email` = ? AND `pin_code` = ?;";
       		PreparedStatement pStatement = conn.prepareStatement(sql);
       		pStatement.setString(1, email);
       		pStatement.setInt(2, pin);
       		ResultSet result = pStatement.executeQuery(sql);
       		if(result.first())
       		{
       			currentUser = result.getInt(1);
       		}
       		else
       		{
       			throw new Exception("User not found.");
       		}
    	}
    	else
    	{
    		throw new Exception("Someone is already logged in.");
    	}
    }
    
    public void loginLibrarian(String username, int password) throws SQLException, Exception
    {
    	if(currentUser == 0 && currentLib == 0)
    	{
    		Connection conn = this.connect();
        	String sql = "SELECT `librarian_id` FROM `librarian` WHERE `librarian_user_name` = ?"
        			+ " AND `librarian_password` = ?;";
       		PreparedStatement pStatement = conn.prepareStatement(sql);
       		pStatement.setString(1, username);
       		pStatement.setInt(2, password);
       		ResultSet result = pStatement.executeQuery(sql);
       		if(result.first())
       		{
       			currentLib = result.getInt(1);
       		}
       		else
       		{
       			throw new Exception("Librarian not found.");
       		}
    	}
    	else
    	{
    		throw new Exception("Someone is already logged in.");
    	}
    }
    
    public void logout()
    {
    	currentUser = 0;
    	currentLib = 0;
    }
    
    public boolean userLoggedIn()
    {
    	if(currentUser != 0)
    	{
    		return true;
    	}
    	else //this should not ever happen
    	{
    		return false;
    	}
    }
    
    public boolean libLoggedIn()
    {
    	if(currentLib != 0)
    	{
    		return true;
    	}
    	else //this should not ever happen
    	{
    		return false;
    	}
    }
    public ResultSet getBook(String id) throws SQLException
    {
    	String sql = "SELECT * FROM book WHERE id = " + id;
    	Statement statement = this.connect().createStatement();
    	ResultSet result = statement.executeQuery(sql);
    	return result;
    	
    }
    
    public void addUser(String pin,
						String name,
						String phone,
						String email,
						String adress) throws SQLException, Exception
    {
    	if(this.libLoggedIn())
    	{
    		Librarian.addUser(this.connect(), pin, name, phone, email, adress);
    	}
    	else
    	{
    		throw new Exception("Unauthorized access");
    	}
    }
    
    public void addBook(String title,
						String publisher, 
						String author,
						String shelf,
						String genre) throws SQLException, Exception
    {
    	if(this.libLoggedIn()) 
    	{
    		Librarian.addBook(this.connect(), title, publisher, author, shelf, genre);
    	}
    	else
    	{
    		throw new Exception("Unauthorized access");
    	}
    }
    
    public void deleteUser(int id) throws SQLException, Exception
    {
    	if(this.libLoggedIn()) 
    	{
    		Librarian.deleteBook(this.connect(), id);
    	}
    	else
    	{
    		throw new Exception("Unauthorized access");
    	}
    }
    
    public void deleteBook(int id) throws SQLException, Exception
    {
    	if(this.libLoggedIn()) 
    	{
    		Librarian.deleteUser(this.connect(), id);
    	}
    	else
    	{
    		throw new Exception("Unauthorized access");
    	}
    }
}
