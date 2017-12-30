/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import DB.Go;
import java.sql.Connection;
import java.sql.Date;
import static java.sql.JDBCType.DATE;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import static java.util.Calendar.DATE;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import librarysystem_dit092.Tools;
import librarysystem_dit092.Tools.Table;

/**
 *
 * @author majeddalain
 */
public class Library {
    private final static int DELAYFEES =3;
    public static Connection conn = null;
    PreparedStatement pst=null;
    ResultSet rs=null;
    
    
    /*
    A method that gives all the book for a specific author by the Surname. 
    */
    
    public static void getBooksByAuthor(String authorSurName, String orderBy, String orderType,JTable table){
        String st = "select book_id,book_title,book_publisher,book_author_surname,book_author_firstname"
                + ",book_availability,book_shelf_number"
                + ",book_genre from book where book_author_surname ='"+authorSurName+"'ORDER BY "+orderBy +orderType+ ";";
        if(Go.runQuery(st)){
        Go.fillToJTable(st, table);
        }
        else{
            Tools.msgBox("No Books found");
        }
      
        
               
    }
    
    public static void getBooksByTitle(String title,String orderBy, String orderType, JTable table){
        String st ="select book_id,book_title,book_publisher,book_author_surname,book_author_firstname"
                + ",book_availability,book_shelf_number"
                + ",book_genre from book where book_title ='"+title+"'ORDER BY "+orderBy +orderType+ ";";
        if(Go.runQuery(st)){
            Go.fillToJTable(st, table);
        }
        else{
            Tools.msgBox("No books found");
        }
    }
    public static void addBook(Connection conn,
						String title,
						String publisher, 
						String authorSurname,
                                                String authorFirstname,
						String shelf,
						String genre) throws SQLException
	{
		String sql = "INSERT INTO book(book_title, book_publisher, "
				+ "book_author_surname, book_author_firstname, book_shelf_number, book_genre) VALUES(?, ?, ?, ?, ?, ?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, title);
		ps.setString(2, publisher);
		ps.setString(3, authorSurname);
                ps.setString(4, authorFirstname);
		ps.setString(5, shelf);
		ps.setString(6, genre);
		ps.executeUpdate();
		//TODO creation date
	}
    
    public static void deleteBook(Connection conn,String bookId) throws SQLException {
    	String sqlDelete = "DELETE FROM book WHERE book_id = ?";
   		PreparedStatement pStatement = conn.prepareStatement(sqlDelete);
    	pStatement.setString(1, bookId);
    	pStatement.executeUpdate();
    }
    
    
    
    /*
    This methods sets fine for the book the user had borrowed, takes the member ID and the 
    borrowed book ID, if the number of delay days is over than 14 then the user will pay 3 Kr for 
    every day of delay. 
    */
    
    public static void returnBook(String memberId, String bookId, String returnDate ){
        String st = " update borrowedbooks set return_date_time = '"+ returnDate+"', is_returned ='YES' where is_returned = 'NO' and member_id="+memberId+ " and book_id = "+bookId+";";
        String st1 = " update book set book_availability = 'YES' where book_id ="+bookId+";";
        
       
     
        if(Go.runNonQuery(st)&& Go.runNonQuery(st1)){
        Tools.msgBox(" Books is successfully returned");
    }
        else{
            Tools.msgBox(" can not return a Book");
        }
        
                
    }
    
    public static void setBookDelayFine (String member_id, String book_id){

            String st2 = "select delay_days from borrowedbooks where is_returned='NO' and member_id ="+member_id+" AND book_id ="+book_id+";";
            int delayDays=0;
            
        try {
            
            conn = Go.setConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs= stmt.executeQuery(st2);
            while(rs.next()){
                delayDays = rs.getInt(1);         
            }
            rs.close();
            if(delayDays>0){
                String st = "insert into fine values(NULL,1,"+member_id+",(select borrow_transaction_id from "
                        + "borrowedbooks where is_returned='NO' and member_id="+member_id+" and book_id ="+book_id+"),"
                        +delayDays*3.0+",default,current_date,current_date,current_date);"; 
                int fines = delayDays * DELAYFEES;
                
               boolean finadded = Go.runNonQuery(st);
               if(finadded){
                   Tools.msgBox(fines+" Kr is added to user wiht ID "+member_id);
               }  
            }
            
            else{
                Tools.msgBox("No fines to add for this user");
            }
            String st3 = " update borrowedbooks set is_returned = 'YES' where is_returned='NO' and member_id ="+member_id+" AND book_id ="+book_id+";";
            stmt.execute(st3);
            Go.closeConnectin();
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Go.closeConnectin();
        }
                
    }
    /* 
    A method that set the total fine for one user, checks all fines the user has for 
    all books that are borrowed. Takes the member id as parameter. 
    */
    public static void setUserTotalFine(String member_id){
        String st = " update member set member_total_fine = (select sum(delay_fines) "
                + "from fine where member_id ="+ member_id+") where member_id="+member_id+";";
        
        Go.runNonQuery(st);
        
    }
    /*
    this method takes amount of payment and the member ID and subtract the amount 
    from the total fine the user has. Moreover null all the fines for every book
    */
    public static void payTotalFines(String member_id, String payment){
        String st = "update member set member_total_fine =(member_total_fine -"+payment+")"
                +"where member_id="+member_id+";";
        String st1 = " update fine set delay_fines = 0 where member_id = "+member_id+";";
        Go.runNonQuery(st);     
        Go.runNonQuery(st1);
    }
    
    public static void getEveryBookFine(String id, JTable table){
        String st = "select c.book_title, b.borrow_date_time, b.return_date_time , b.delay_days , b.due_date, a.delay_fines "
                + "from book c , borrowedbooks b , fine a where a.borrow_transaction_id = b.borrow_transaction_id "
                + "AND b.book_id = c.book_id  and a.member_id ="+id+";";
             
        
        if(Go.runQuery(st)){
            Go.fillToJTable(st, table);
        }
        else{
            Tools.msgBox(" NO results found");
        }
                
    }
    
    public static void allBorrowedBooks(String id, JTable table){
        String st = "select b.book_title,a.borrow_date_time,a.return_date_time,a.delay_days,a.due_date"
                + ",a.is_returned from borrowedbooks a,book b where a.book_id = b.book_id and "
                + "member_id ="+id+";";
        
        if(Go.runQuery(st)){
        Go.fillToJTable(st, table);
        }
        else{
            Tools.msgBox("No Books found");
        }
                
    }
   
    public static void allDelayedBooksToReturn(String id, JTable table){
           String st = "select a.book_id,b.book_title,a.borrow_date_time,a.due_date"
                + " from borrowedbooks a,book b where a.book_id = b.book_id and "
                + "a.member_id ="+id+" and a.is_returned ='NO';";
           
        if(Go.runQuery(st)){
        Go.fillToJTable(st, table);
        }
        else{
            Tools.msgBox("No Books found");
        }
           
    }
    
    public static void allDelayedBooks(String id, JTable table){
        String st = "select b.book_title,a.borrow_date_time,a.due_date"
                + ",a.is_returned from borrowedbooks a,book b where a.book_id = b.book_id and "
                + "member_id ="+id+" and a.is_returned ='NO';";
        if(Go.runQuery(st)){
        Go.fillToJTable(st, table);
        }
        else{
            Tools.msgBox("No Books found");
        }
    }
    
    public static boolean isBookAvailable(String id){
        String st = "select book_id, book_title,book_availability,book_shelf_number from book where  book_availability= 'YES' and book_id="+id+";";
        
        boolean isAvailable = false;
        if(Go.runQuery(st)){
            isAvailable = true;
        }
        else{
            Tools.msgBox(" book is not available");
            isAvailable = false;
        }
        
        return isAvailable;
    }
    
    public static void issueBook(String librarianId, String memberId,String bookId, String issueDate){
      String st = "insert into borrowedbooks values(null,"+librarianId+","+memberId+","+bookId+",'"+issueDate+"',default , default,DATE_ADD('"+issueDate+"',INTERVAL 14 DAY),default,current_date,current_date);";
      
      String st1 = "update book set book_availability ='NO' where book_id ="+bookId+";";
      
    if( Go.runNonQuery(st)&& Go.runNonQuery(st1)){
        Tools.msgBox("Book Issued");
    }
    else{
        Tools.msgBox("Can not Issue the Book");
    }
                   
    }
    
    
}
