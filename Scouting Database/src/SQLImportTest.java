import java.sql.*;
public class SQLImportTest {
	public static void main (String[] args) { 
	try
	{
	   // Step 1: Load jTDS driver
	   Class.forName("net.sourceforge.jtds.jdbc.Driver");
	   // Step 2: Establish the connection to the database
	   String url = "jdbc:jtds:sqlserver://localhost:1433/TestDB"; 
	   Connection conn = DriverManager.getConnection(url, "", "");  
	      
	   // Step 3: Create Statement object (Type set to scrollable, read only)
	   Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
	      
	   //Step 4: Select data
       ResultSet rs = statement.executeQuery("SELECT * " + "FROM Orders ");
       
       //Step 5: Set the ResultSet to the first row
       rs.first();
       
       //Step 5: Parse data
       int ordernum = rs.getInt("order_num");
       System.out.println(ordernum);
	 }
	 catch (Exception e)
	 {
	   System.err.println("Exception thrown:"); 
	   System.err.println(e.getMessage()); 
	 } 
   } 
}
