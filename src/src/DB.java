package src;

import java.util.*;
import java.sql.*;

public class DB {
	static String url = "jdbc:mysql://leia.skip.chalmers.se:3306/team1?autoReconnect=true&useSSL=false";
    static String username = "teamone";
    static String password = "HSaaD5vtp3K6QERq";

   public static Connection login() throws SQLException {
        Connection conn;
        try {
            conn = DriverManager.getConnection(url, username, password);
        }catch (Exception E){
            return null;
        }
        return conn;
   }
}