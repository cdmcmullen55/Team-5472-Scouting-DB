import java.sql.*;

import localconstantstorage.ConnectionConstants;

public class SQLExportTest{
    public static void main (String[] args) { 
    try
    {
      // Step 1: Load jTDS driver
      Class.forName("net.sourceforge.jtds.jdbc.Driver");
      // Step 2: Establish the connection to the database
      String url = ConnectionConstants.CONNECTION_STRING; 
      Connection conn = DriverManager.getConnection(url, ConnectionConstants.USER_ID, ConnectionConstants.PASS);  
      
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