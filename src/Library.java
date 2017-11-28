import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;

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
}
