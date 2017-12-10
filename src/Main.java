public class Main {
    public static void main(String[] args) {
    	     // TODO code application logic here
//        User user1 = new User();
//        User user2 = new User();
//        User user3 = new User("700","Gustaf","gustaf","1232","332",0);
//        //User.addUser(user3);
//        System.out.println(user1.getTotalFine(6,"Gustaf","700"));
//        System.out.println(user2.getTotalFine(9,"ahmad","0000"));
//        System.out.println(Library.getBooksByAuthor("TREVOR NOAH"));
//        Library.setBookDelayFine(1,1);
//        Library.setUserTotalFine(1);
        System.out.println(User.getTotalFine(1, "Majed", "0000"));
        Library.payTotalFines(1, 30);
         System.out.println(User.getTotalFine(1, "Majed", "0000"));
//        
    }

    }

