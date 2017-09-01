package blueAllianceDB;

import java.sql.*;
import localconstantstorage.ConnectionConstants;

public class TBASingleImport {

	private TBATeam team;
	private Connection conn;
	
	public TBASingleImport(int number) {
		team = new TBATeam(number);
	}
	
	public boolean openConnection(){
		try
		{
			Class.forName("net.sourceforge.jtds.jdbc.Driver");
			String url = ConnectionConstants.TBA_DB_CONNECTION_STRING;
			conn = DriverManager.getConnection(url, ConnectionConstants.USER_ID, ConnectionConstants.PASS);
			return true;
		}
		catch (Exception e)
		{
			System.err.println("Exception thrown:"); 
      			System.err.println(e.getMessage());
			return false;
		}
	}
	
	private boolean insertTeam(){
		try
		{
			Statement statement = conn.createStatement();
			statement.execute("INSERT INTO Blue_Alliance_Data " + "VALUES (\'" + team.getTeamKey() + "\', \'" + 
					  team.getTeamName() + "\', " + team.getTeamNumber() + ", " + team.getTeamWins() + ", " + 
					  team.getTeamLosses() + ", " + team.getTeamTies() + ", " + team.getOPR() + ", " + 
					  team.getRookieYear() + ");");
			return true;
		}
		catch (Exception e)
		{
			System.err.println("Exception thrown:");
			System.err.println(e.getMessage());
			return false;
		}
	}
	
	public boolean runImport(){
		if(!openConnection()){
			System.err.println("There was a connection error");
			return false;
		}
		else if(!insertTeam()){
			System.err.println("There was a SQL query error");
			return false;
		}
		else
			return true;
	}
}
