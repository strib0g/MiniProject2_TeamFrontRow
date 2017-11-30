import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.util.Calendar;

import java.util.GregorianCalendar;

public class Library {

    private String url = "jdbc:mysql://leia.skip.chalmers.se:3306/team1?autoReconnect=true&useSSL=false";
    private String username = "teamone";
    private String password = "HSaaD5vtp3K6QERq";
    private Calendar calendar = new GregorianCalendar();

    public Connection login() throws SQLException {
        Connection conn;
        try {
            conn = DriverManager.getConnection(url, username, password);
        } catch (Exception E) {
            return null;
        }
        return conn;
        //throw new SQLException("ERROR");
    } // This need to be improved.

    public void enterSQLCommand(String command) throws SQLException{
        Connection con = login();
        Statement state = con.createStatement();
        state.execute(command);
        con.close();
    } // This is a method intended to be used in fringe cases. Emergencies and unexpected stuff.

    public void addBook(String book_author_firstname, String  book_author_surname, String book_genre, String book_publisher, String book_shelf_number, String book_title)throws SQLException{
        String insert = "INSERT INTO book (book_author_firstname, book_author_surname, book_availability," +
                " book_created, book_genre, book_publisher, book_shelf_number, book_title) VALUES (?, ?, ?, ?, ?, ?, ?, ?) ";
        Connection con = login();

        PreparedStatement state = con.prepareStatement(insert);

        state.setString(1, book_author_firstname);
        state.setString(2, book_author_surname);
        state.setInt(3, 1);
        state.setDate(4, ); // Not done. Has to figure out DATETIME.
        state.setString(5, book_genre);
        state.setString(6,book_publisher);
        state.setString(7,book_shelf_number);
        state.setString(8, book_title);

        state.execute();

        con.close();
    }

    public boolean bookAvailable(String id) throws SQLException{
        String query = "SELECT * FROM book WHERE book_id = " + id;
        Connection con = login();
        Statement state = con.createStatement();
        ResultSet set = state.executeQuery(query);
        if(set.getInt(4) == 1){
            return true;
        }
        else {
            return false;
        }
    }

    public void returnBook(String id) throws  SQLException{
        String update = "ALTER book SET book_availability = 1 WHERE book_id = " + id ;
        Connection con = login();
        Statement state = con.createStatement();
        state.execute(update);
        con.close();
    } // Check for bugs.

    public String checkReturnDate(String id) throws SQLException{
        String query = "SELECT * FROM book WHERE book_id = " + id;
        Connection con = login();
        Statement state = con.createStatement();
        ResultSet set =state.executeQuery(query);
        String returndate = set.getString(420); // I'm gonna have to check how we are going to use borrowed books.
        con.close();
        return returndate;
    }

    public String checkReturnDateAlter(String id) throws SQLException{
        String query = "SELECT * FROM borrowedbooks WHERE book_id = " + id;
        Connection con = login();
        Statement state = con.createStatement();
        ResultSet set = state.executeQuery(query);

        con.close();

        return set.getString(9);
    }

    public double payFine(String id, double payment)throws SQLException{
        String query = "SELECT * FROM member WHERE member_id = " + id;
        Connection con = login();
        Statement state = con.createStatement();
        ResultSet set = state.executeQuery(query);

        double excess = 0; // Used if the user pays too much.

        double remainingFine = set.getDouble(7) - payment;
        if(remainingFine < 0 ){
            excess = (remainingFine * -1);
            remainingFine = 0;
        }

        String update = "UPDATE member SET member_total_fine = "+ remainingFine + "WHERE member_id = " + id;
        state.execute(update);

        con.close();
        return excess;
    }



}
