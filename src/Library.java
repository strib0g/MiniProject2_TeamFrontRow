import com.sun.org.apache.regexp.internal.RE;

import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;

import java.util.GregorianCalendar;

public class Library {

    private String url = "jdbc:mysql://leia.skip.chalmers.se:3306/team1?autoReconnect=true&useSSL=false";
    private String username = "teamone";
    private String password = "HSaaD5vtp3K6QERq";

    private Calendar calendar = new GregorianCalendar();
    private Connection con;

    public void connect() throws SQLException {
        try {
            con = DriverManager.getConnection(url, username, password);
        } catch (Exception E) {
        }
        //throw new SQLException("ERROR");
    } // Use this before anything else

    public void close()throws SQLException{
        con.close();
    } // Always use at the end.

    private void createSQLCommand(String command) throws SQLException{
        Statement state = con.createStatement();
        state.execute(command);
    }

    private ResultSet querySQLcommand(String query) throws SQLException{
        Statement state = con.createStatement();
        return state.executeQuery(query);
    }

    public String currentDate(){
        String date = calendar.get(Calendar.YEAR)+"-"+ (calendar.get(Calendar.MONTH) + 1)+ "-" + calendar.get(Calendar.DAY_OF_MONTH);

        return date;
    } // prototype. DATE - format YYYY-MM-DD


    public void addBook(String book_author_firstname, String  book_author_surname, String book_genre, String book_publisher, String book_shelf_number, String book_title)throws SQLException{
        String insert = "INSERT INTO book (book_author_firstname, book_author_surname, book_availability," +
                " book_created, book_genre, book_publisher, book_shelf_number, book_title, book_updated) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement state = con.prepareStatement(insert);

        state.setString(1, book_author_firstname);
        state.setString(2, book_author_surname);
        state.setString(3, "YES");
        state.setString(4, currentDate() ); // Not done. Has to figure out DATETIME. Can be used if it is changed to DATE.
        state.setString(5, book_genre);
        state.setString(6,book_publisher);
        state.setString(7,book_shelf_number);
        state.setString(8, book_title);
        state.setString(9, currentDate());
        state.execute();
    }

    public boolean bookAvailable(String id) throws SQLException{
        String query = "SELECT * FROM book WHERE book_id = " + id;
        System.out.println("at");
        ResultSet set = querySQLcommand(query);
        System.out.println("aty");
        set.next();
        if(set.getString(4).equals("YES")){
            return true;
        }
        else {
            return false;
        }
    }

    public void returnBook(String id) throws  SQLException{
        String update = "UPDATE book SET book_availability='YES' WHERE book_id = " + id;
       createSQLCommand(update);
       update = "UPDATE borrowedbooks SET is_returned = 'YES', borrow_updated = "+currentDate()+ " WHERE book_id = " + id;
       createSQLCommand(update);

    }

    public String checkReturnDate(String id) throws SQLException{
        String query = "SELECT * FROM borrowedbooks WHERE book_id = " + id + "AND is_returned = NO";
        ResultSet set = querySQLcommand(query);

        return set.getString(6);
    }

    public double payFine(String id, double payment)throws SQLException{
        String query = "SELECT * FROM member WHERE member_id = " + id;
        ResultSet set = querySQLcommand(query);

        double excess = 0; // Used if the user pays too much.

        double remainingFine = set.getDouble(7) - payment;
        if(remainingFine < 0 ){
            excess = (remainingFine * -1);
            remainingFine = 0;
        }

        String update = "UPDATE member SET member_total_fine = "+ remainingFine + "WHERE member_id = " + id;
        createSQLCommand(update);
        return excess;
    }



    public void loanBook(String bookId, String userId, String librarianID)throws SQLException{
        String newBorrow = "INSERT INTO borrowedbooks(member_id, book_id, librarian_id, borrow_date_time, return_date_time, delay_days," +
                " due_date, is_returned, borrow_created, ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement state = con.prepareStatement(newBorrow);
        state.setString(1, userId );
        state.setString(2, bookId );
        state.setString(3, librarianID );
        state.setString(4, currentDate()); // DATE
        state.setString(5, "0"); // DATE
        state.setString(6, ""); // DATE
        state.setString(7, ""); // DATE
        state.setString(8, "0");
        state.setString(9, currentDate()); // DATE

        String update = "UPDATE book SET book_updated" + currentDate() +"WHERE book_id = " + bookId; // FIGURE OUT DATETIME!
        createSQLCommand(update);

        update = "UPDATE book SET book_availability = 'NO' WHERE book_id = " + bookId ;
        createSQLCommand(update);


    }

    public void setNewReturnDate(String borrow_transaction_id, String newReturn)throws SQLException{
        String command = "UPDATE borrowedbooks SET return_date_time = " + newReturn + "WHERE borrow_transaction_id = " + borrow_transaction_id;
        createSQLCommand(command);

    } // CHECK FOR BUGS.

    public String unreturnedBooksUser(String uId)throws SQLException{
        String query = "SELECT * borrowedbooks WHERE is_returned = 0 AND member_id = " + uId;
        ResultSet set = querySQLcommand(query);

        return set.getString(4);
    }

    public ResultSet borrowedBookCount()throws SQLException{ // Prototype. Checks how many books where borrowed every day.
        String query = "SELECT borrow_date_time,COUNT(*) FROM borrowedbooks GROUP BY borrow_date_time WHERE is_returned = 0";
        ResultSet set = querySQLcommand(query);

        return set; // set is a set of count of books borrowed and the day they were borrowed grouped by the day they were borrowed.
    }

    public ArrayList<ResultSet> borrowedBookMonths()throws SQLException{
        ArrayList<ResultSet> list = new ArrayList<ResultSet>();

        int year = 2017;
        int month = 11;
        String query = "SELECT COUNT(*) WHERE CONTAINS(borrow_date_time, ' " + year + "-" + (month + 1) +"'" ;
        do{
           ResultSet set = querySQLcommand(query);
           list.add(set);
            if(month == 12){
                month = 1;
                year += 1;
            }
            else {
                month += 1;
            }

        }while (year != calendar.YEAR && month != calendar.MONTH);


        return list;
    }

}


