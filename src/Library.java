/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miniproject2_teamfrontrow;

import static java.sql.JDBCType.DATE;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author majeddalain
 */
public class Library {
    private final static int DELAYFEES =3;
    
    /*
    A method that gives all the book for a specific author by the Surname. 
    */
    
    public static ArrayList getBooksByAuthor(String authorSurName){
        String st = "select book_id, book_title from book where book_author_surname ='"+authorSurName+"';";
        ArrayList<String> bookList = new ArrayList<String>();
         
        ResultSet rs = Tools.runQuery(st);
        try {
           while(rs.next()){
           String result = rs.getString(2);
           bookList.add( result);
              }
           rs.close();
           Tools.closeConnectin();
            return bookList;
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
            Tools.closeConnectin();
            return null;
            
        }
               
    }
    
    /*
    This methods sets fine for the book the user had borrowed, takes the member ID and the 
    borrowed book ID, if the number of delay days is over than 14 then the user will pay 3 Kr for 
    every day of delay. 
    */
    
    public static void setBookDelayFine (int member_id, int book_id){

            String st1 = "update borrowedbooks set delay_days = DATEDIFF(return_date_time,borrow_date_time)-14;";
            String st2 = "select delay_days from borrowedbooks where member_id ="+member_id+" AND book_id ="+book_id+";";
            int delayDays=0;
            
        try {
            Tools.runNonQuery(st1);
            ResultSet rs= Tools.runQuery(st2);
            while(rs.next()){
                delayDays = rs.getInt(1);         
            }
            rs.close();
            if(delayDays>0){
                String st = "insert into fine values(NULL,1,"+member_id+",(select borrow_transaction_id from "
                        + "borrowedbooks where member_id="+member_id+" and book_id ="+book_id+"),"
                        +delayDays*3.0+",default,current_date,current_date,current_date);"; 
                int fines = delayDays * DELAYFEES;
                
               boolean finadded = Tools.runNonQuery(st);
               if(finadded){
                   System.out.println(fines+"/Kr is added to user wiht ID "+member_id+"as fines");
               }  
            }
            
            else{
                System.out.println("No fines to add for this user");
            }
            Tools.closeConnectin();
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Tools.closeConnectin();
        }
                
    }
    /* 
    A method that set the total fine for one user, checks all fines the user has for 
    all books that are borrowed. Takes the member id as parameter. 
    */
    public static void setUserTotalFine(int member_id){
        String st = " update member set member_total_fine = (select sum(delay_fines) "
                + "from fine where member_id ="+ member_id+") where member_id="+member_id+";";
        
        Tools.runNonQuery(st);
        
    }
    /*
    this method takes amount of payment and the member ID and subtract the amount 
    from the total fine the user has. Moreover set to zero all the fines for every book in the 
    fine table. 
    */
    public static void payTotalFines(int member_id, int payment){
        String st = "update member set member_total_fine =(member_total_fine -"+payment+")"
                +"where member_id="+member_id+";";
        String st1 = " update fine set delay_fines = 0 where member_id = "+member_id+";";
        Tools.runNonQuery(st);     
        Tools.runNonQuery(st1);
    }
    
    
}
