import java.sql.*;
import java.util.ArrayList;

public class Main {
    static Library lib = new Library();
    static String url = "jdbc:mysql://leia.skip.chalmers.se:3306/team1?autoReconnect=true&useSSL=false";
    static String username = "teamone";
    static String password = "HSaaD5vtp3K6QERq";

    public static void main(String[] args) {
        Library lib = new Library();
        try {
            lib.connect();

            /*
           // lib.addBook("David", "Lindgren", "Sci-Fi", "Punguin", "420ö", "Spark Wars: The old despair"); // Tested works.
           // lib.returnBook("1"); Tested once, works
            System.out.println(lib.bookAvailable("3"));
            System.out.println(lib.bookAvailable("2"));
            */
            //lib.returnBook("4"); Tested once. Seemed to work, Should try another day to check the dates.
            //System.out.println(lib.checkReturnDate("3")); Seems to work.
            // lib.setNewDueDate("1", lib.dueDate()); Tested once. Works with dueDate()
           /* ArrayList<String> list = lib.unreturnedBooksUser("2");
            for(int i = 0; i < list.size(); i++){
                System.out.println(list.get(i));
            }  It seems to work. */
            // lib.loanBook("4","1","1"); IT WORKS!
           // System.out.println(lib.borrowedBooksMonths(2017, 10).getString(1)); // IT WORKS!!



            lib.close();

        } catch (Exception e) {
            System.out.println(e);
            try {
                lib.close();
            } catch (Exception e1) {

            }
        }
    }

    }
