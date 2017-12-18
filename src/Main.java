import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Main {
    static Library  lib = new Library();
    static String url = "jdbc:mysql://leia.skip.chalmers.se:3306/team1?autoReconnect=true&useSSL=false";
    static String username = "teamone";
    static String password = "HSaaD5vtp3K6QERq";

    public static void main(String[] args) {
      /*  Library lib = new Library();
        try {
            lib.connect();
           // lib.addBook("David", "Lindgren", "Sci-Fi", "Punguin", "420ö", "Spark Wars: The old despair"); // Tested works.
           // lib.returnBook("1"); Tested once, works
            System.out.println(lib.bookAvailable("3"));
            System.out.println(lib.bookAvailable("2"));
            lib.close();

        }catch (Exception e){
            System.out.println(e);
            try {
                lib.close();
            }catch (Exception e1){

            }
        }*/
        System.out.println(lib.currentDate());
    }


   /* public static  void addBook(Connection conn, String book_author_firstname, String  book_author_surname, String book_genre, String book_publisher, String book_shelf_number, String book_title)throws SQLException {
        String insert = "INSERT INTO book (book_author_firstname, book_author_surname, book_availability," +
                " book_created, book_genre, book_publisher, book_shelf_number, book_title) VALUES (?, ?, ?, ?, ?, ?, ?, ?) ";
        System.out.println("Before connect");

        PreparedStatement state = conn.prepareStatement(insert);
        System.out.println("before parameters");

        state.setString(1, book_author_firstname);
        state.setString(2, book_author_surname);
        state.setInt(3, 1);
        System.out.println("before parameter 4");
        state.setString(4, lib.currentDate() ); // Not done. Has to figure out DATETIME. Can be used if it is changed to DATE.
        state.setString(5, book_genre);
        state.setString(6,book_publisher);
        state.setString(7,book_shelf_number);
        state.setString(8, book_title);
        System.out.println("Before exe");
        state.execute();

        conn.close();
    } */
}
