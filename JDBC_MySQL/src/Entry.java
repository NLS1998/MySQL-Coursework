import java.sql.*;
import java.util.Scanner;

public class Entry {
    private static final Scanner S = new Scanner(System.in);

    private static Connection c = null;
    private static ResultSet rs = null;

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();

            c = DriverManager.getConnection("jdbc:mysql://mysql.cms.livjm.ac.uk:3306/cmpnscar? user=CMPNSCAR & password=a7amphi"); 
            Statement s = c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

            rs = s.executeQuery("SELECT S.`no`, S.`Name`, L.Due FROM Student S INNER JOIN Loan L ON S.`no` = L.`no` WHERE YEAR (CURRENT_DATE()) AND Back IS NULL ORDER BY Due ASC;"); 

            String choice = "";

            do {
                System.out.println("-- MAIN MENU --");
                System.out.println("1 - Browse ResultSet");
                System.out.println("2 - Invoke Procedure");
                System.out.println("Q - Quit");
                System.out.print("Pick : ");

                choice = S.next().toUpperCase();

                switch (choice) {
                    case "1" : {
                        browseResultSet();
                        break;
                    }
                    case "2" : {
//                        invokeProcedure();
                        break;
                    }
                }
            } while (!choice.equals("Q"));

            c.close();

            System.out.println("Bye Bye :)");
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

   private static void browseResultSet() throws Exception {
        if (rs.first()) {
            System.out.println("-- View the Result Set --");
        
           do {
            System.out.println(String.format(rs.getString("code"), rs.getString("no"), rs.getString("Taken"), rs.getString("Due"), rs.getString("Back")));
        }while (rs.next());
        }
    
        else {
            System.out.println("-- No Results --");
        
        }
   }
}
    

//    private static void invokeProcedure() throws Exception {
//    
//            String query = "SELECT Student FROM Loan";
//
//            Scanner scan = new Scanner(System.in); //Creates a new scanner
//            System.out.println("What is the Book ISBN Number ?"); //Asks question
//     String input = scan.nextLine(); //Waits for input
//     if (input.equalsIgnoreCase(isbn)) { 
//          System.out.println("Correct!");
//     }
//     else { //If the input is anything else
//          System.out.println("Incorrect!");
//     }
//}
//      // create the java statement
//      Statement st = conn.createStatement();
//      
//      // execute the query, and get a java resultset
//      ResultSet rs = st.executeQuery(query);
//      
//      // iterate through the java resultset
//      while (rs.next())
//      {
//        int no = rs.getInt("Student`no`");
//        String StudentName = rs.getString("Student.`Name`");
//        String LoanDue = rs.getString("Loan.Due");
//        Date LoanBack = rs.getDate("Loan.Back");
//      
//        
//        // print the results
//        System.out.format("%s, %s, %s, %s", no, StudentName, LoanDue, LoanBack);
//      }
//      st.close();
//    }
//    catch (Exception e)
//    }
//}
