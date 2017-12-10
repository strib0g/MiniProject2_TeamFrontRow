/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miniproject2_teamfrontrow;

import java.sql.Date;
import java.sql.ResultSet;

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
    
    public static void addUser(User user){
        String st = "insert into member values( null,'"+user.pin_code+"','"+user.name+"','"+user.phone_number+
                "','"+user.email+"','"+user.address+"',default,current_date,current_date);";
        
        if(Tools.runNonQuery(st)){
            System.out.println("user added");
        }
        else{
            System.out.println("failed to add user");
        }
        
    }
    /* 
    A method that returns the total fine a user has accured for all the books 
    he was late for returning them. Takes the member id, name and pin_code. 
    */
    public static double getTotalFine(int id, String name, String pin_code ){
        String st = "select member_total_fine from member where member_name ='"
                + name +"' AND pin_code='"+ pin_code +"'AND member_id ="+id;
        
        try {
           
        ResultSet rs = Tools.runQuery(st); // what does this return when the data does not 
        double totalfine = 0;        //exist in the database
        if(rs.next()){
            totalfine = rs.getDouble(1);
        }
        return totalfine;
        
        
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 0;
        }
        
    }
    
    
    
    
  
    
}
