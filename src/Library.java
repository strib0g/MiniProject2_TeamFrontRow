import java.sql.*;

public class Library {

    private String url = "jdbc:mysql://leia.skip.chalmers.se:3306/team1?autoReconnect=true&useSSL=false";
    private String username = "teamone";
    private String password = "HSaaD5vtp3K6QERq";

    public Connection login() throws SQLException {
        Connection conn;
        try {
            conn = DriverManager.getConnection(url, username, password);
        }catch (Exception E){
            return null;
        }
        return conn;
        //throw new SQLException("ERROR");
    } // This need to be improved.
    
    public ResultSet getBook(String id) throws SQLException
    {
    	String sql = "SELECT * FROM book WHERE id = " + id;
    	Statement statement = this.login().createStatement();
    	ResultSet result = statement.executeQuery(sql);
    	return result;
    	
    }
    
    public void addUser(String pin,
						String name,
						String phone,
						String email,
						String adress) throws SQLException, Exception
    {
    	if(currentUser) //TODO login system
    	{
    		Librarian.addUser(this.login(), pin, name, phone, email, adress);
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
    	if(currentUser) //TODO login system
    	{
    		Librarian.addBook(this.login(), title, publisher, author, shelf, genre);
    	}
    	else
    	{
    		throw new Exception("Unauthorized access");
    	}
    }
    
    public void deleteUser(int id) throws SQLException, Exception
    {
    	if(currentUser) //TODO login system
    	{
    		Librarian.deleteBook(this.login(), id);
    	}
    	else
    	{
    		throw new Exception("Unauthorized access");
    	}
    }
    
    public void deleteBook(int id) throws SQLException, Exception
    {
    	if(currentUser) //TODO login system
    	{
    		Librarian.deleteUser(this.login(), id);
    	}
    	else
    	{
    		throw new Exception("Unauthorized access");
    	}
    }
}
