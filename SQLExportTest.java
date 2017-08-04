import java.sql.*;

public class SQLExportTest{
    public static void main (String[] args) { 
    try
    {
      // Step 1: Load jTDS driver
      Class.forName("net.sourceforge.jtds.jdbc.Driver");
      // Step 2: Establish the connection to the database
      String url = "jdbc:jtds:sqlserver://localhost:1433/TestDB"; 
      Connection conn = DriverManager.getConnection(url, "", "");  
      
      // Step 3: Create Statement object
      Statement statement = conn.createStatement();
      
      //Step 4: Insert data
      statement.executeUpdate("INSERT INTO Customers " + "VALUES ('1000000006', 'Toy Land', '123 Any Street', 'New York', 'NY', '11111', 'USA', NULL, NULL)");
    }
    catch (Exception e)
    {
      System.err.println("Exception thrown:"); 
      System.err.println(e.getMessage()); 
    } 
  } 
} 