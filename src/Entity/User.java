/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import DB.Go;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JTable;
import librarysystem_dit092.Tools;

/**
 *
 * @author majeddalain
 */
public class User {
    private String pin_code;
    private String name;
    private String phone_number;
    private String email;
    private String address;
    private double total_fine;
    
    
    public User(){
        
    }
    
    public User(String pin_code, String name,String phone,String email,
            String address, double total_fine){
        this.pin_code = pin_code;
        this.name = name;
        this.email = email;
        this.phone_number = phone;
        this.address = address;
        this.total_fine = 0;
    }
    
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
    
    
//    public static void addUser(User user){
//        String st = "insert into member values( null,'"+user.pin_code+"','"+user.name+"','"+user.phone_number+
//                "','"+user.email+"','"+user.address+"',default,current_date,current_date);";
//        
//        if(Go.runNonQuery(st)){
//            System.out.println("user added");
//        }
//        else{
//            System.out.println("failed to add user");
//        }
//        
//    }
    /* 
    A method that returns the total fine a user has accured for all the books 
    he was late for returning them. Takes the member id, name and pin_code. 
    */
    public static String getTotalFine(String id ){
        String st = "select member_total_fine from member where member_id ="+id;
        
        try {
            Connection  conn = Go.setConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(st); // what does this return when the data does not 
            String totalfine = null;        //exist in the database
           if(rs.next()){
            totalfine = rs.getString(1);
            }
          return totalfine;
        
        
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        
    }
    
//    public static String getUserName(String id, String pincode){
//        String stGetName = "select member_name from member where member_id =" +id+ "and pin_code = '"+pincode+"';";
//        
//        try {
//            ResultSet rs = Go.runQuery(stGetName);
//            while(rs.next()){
//                String userName = rs.getString("member_name");
//                
//            }
//            
//            
//            
//        } catch (SQLException ex) {
//            Tools.msgBox(ex.getMessage());
//        }
//        
//    }
    
    public static void getUserInformation(String id, JTable table){
        String st = "select member_name, member_email,member_address from member where member_id ="+id+";";
        
        if(Go.runQuery(st)){
            Go.fillToJTable(st, table);
        }
        else{
            Tools.msgBox(" User is not found ");
                    
        }
    }
            
    
    
    
    
  
    
}
