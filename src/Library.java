import java.sql.Connection;
import java.sql.DriverManager;

public class Library {

    private String url = "jdbc:mysql://leia.skip.chalmers.se:3306/team1?autoReconnect=true&useSSL=false";
    private String username = "teamone";
    private String password = "HSaaD5vtp3K6QERq";

    public void login() throws Exception{
            Connection conn = DriverManager.getConnection(url, username, password);
            throw new Exception("ERROR");
    }

    



}
