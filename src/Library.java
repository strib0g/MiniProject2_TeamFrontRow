import com.sun.org.apache.regexp.internal.RE;

import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.util.Calendar;

import java.util.GregorianCalendar;

public class Library {

    private String url = "jdbc:mysql://leia.skip.chalmers.se:3306/team1?autoReconnect=true&useSSL=false";
    private String username = "teamone";
    private String password = "HSaaD5vtp3K6QERq";
    private Calendar calendar = new GregorianCalendar();

    public Connection connect() throws SQLException {
        Connection conn;
        try {
            conn = DriverManager.getConnection(url, username, password);
        } catch (Exception E) {
            return null;
        }
        return conn;
        //throw new SQLException("ERROR");
    } // This need to be improved.

    private void createSQLCommand(String command) throws SQLException{
        Connection con = connect();
        Statement state = con.createStatement();
        state.execute(command);
        con.close();
    }

    private ResultSet querySQLcommand(String query) throws SQLException{
        Connection con = connect();
        Statement state = con.createStatement();
        con.close();
        return state.executeQuery(query);
    }

    private String currentDate(){
        String date = calendar.YEAR+"-"+calendar.MONTH+"-" + calendar.DAY_OF_MONTH;

        return date;
    } // prototype. DATE - format YYYY-MM-DD


    public void addBook(String book_author_firstname, String  book_author_surname, String book_genre, String book_publisher, String book_shelf_number, String book_title)throws SQLException{
        String insert = "INSERT INTO book (book_author_firstname, book_author_surname, book_availability," +
                " book_created, book_genre, book_publisher, book_shelf_number, book_title) VALUES (?, ?, ?, ?, ?, ?, ?, ?) ";
        Connection con = connect();

        PreparedStatement state = con.prepareStatement(insert);

        state.setString(1, book_author_firstname);
        state.setString(2, book_author_surname);
        state.setInt(3, 1);
        state.setString(4, currentDate() ); // Not done. Has to figure out DATETIME. Can be used if it is changed to DATE.
        state.setString(5, book_genre);
        state.setString(6,book_publisher);
        state.setString(7,book_shelf_number);
        state.setString(8, book_title);

        state.execute();

        con.close();
    }

    public boolean bookAvailable(String id) throws SQLException{
        String query = "SELECT * FROM book WHERE book_id = " + id;
        ResultSet set = querySQLcommand(query);
        if(set.getInt(4) == 1){
            return true;
        }
        else {
            return false;
        }
    }

    public void returnBook(String id) throws  SQLException{
        String update = "ALTER book SET book_availability = 1 WHERE book_id = " + id ;
       createSQLCommand(update);
    } // Check for bugs.

    public String checkReturnDate(String id) throws SQLException{
        String query = "SELECT * FROM book WHERE book_id = " + id;
        ResultSet set = querySQLcommand(query);
        String returndate = set.getString(420); // I'm gonna have to check how we are going to use borrowed books.
        return returndate;
    }

    public String checkReturnDateAlter(String id) throws SQLException{
        String query = "SELECT * FROM borrowedbooks WHERE book_id = " + id;
        ResultSet set = querySQLcommand(query);

        return set.getString(9);
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
        Connection con = connect();
        String newBorrow = "INSERT INTO borrowedbooks(member_id, book_id, librarian_id, borrow_date_time, return_date_time, delay_days," +
                " due_date, is_returned, borrow_created, ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?) ";
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

        String update = "ALTER book SET book_updated" + "" +"WHERE book_id = " + bookId; // FIGURE OUT DATETIME!
        createSQLCommand(update);

        update = "ALTER book SET book_availability = 0 WHERE book_id = " + bookId ;
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

}
