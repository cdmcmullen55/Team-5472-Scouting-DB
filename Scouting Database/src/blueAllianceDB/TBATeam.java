package blueAllianceDB;
import main.*;
import models.simple.*;
import models.other.matches.MatchAlliance;
import models.other.events.EventOPR;
import requests.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import localconstantstorage.ConnectionConstants;

public class TBATeam {
	
	//requested information about each team
	private int team_number;
	private TeamRequest request;
	private STeam team;
	private String team_key;
	private String team_name;
	private int wins;
	private int losses;
	private int ties;
	private double OPR;
	private int rookie_year;
	private PreparedStatement statement;

	public TBATeam(int teamnumber) {
		// TODO Auto-generated constructor stub
		this(teamnumber, false);
	}
	
	public TBATeam(int teamnumber, boolean fromDB) {
		team_number = teamnumber;
		if(fromDB)
			setFromDB();
		else {
			// Set Authorization Key
			TBA.setAuthToken(ConnectionConstants.AUTH_KEY);
			// Create TBA object (from main.*)
			TBA tba = new TBA();
			// Retrieve Team object from Blue Alliance
			team = tba.getSTeam(team_number);
			// Create TeamRequest object
			request = new TeamRequest();
			// Retrieve team_key (primary key in DB)
			team_key = team.getKey();
			// Retrieve team_name
			team_name = team.getNickname();
			scrubApostrophe();
			// Retrieve win/loss record
			setWinLoss();
			// Retrieve OPR
			setOPR();
			// Retrieve rookie year
			setRookieYear();
		}
	}
	
	// Returns event key for most recent event
	
	private void scrubApostrophe() {
		int index = team_name.indexOf("\'");
		while(index >= 0) {
			team_name = team_name.substring(0, index) + team_name.substring(index+1);
			index = team_name.indexOf("\'", index);
		}
	}
	
	private String getMostRecentEvent(int team) {
		Date now = new Date();
		// Retrieve array of events team has participated in
		SEvent[] events = request.getSEvents(team, Calendar.getInstance().get(Calendar.YEAR));
		if(events.length < 2) {
			events = request.getSEvents(team, Calendar.getInstance().get(Calendar.YEAR)-1);
		}
		if(team == 5058)
			return "2017alhu";
		if(events.length == 0 || team == 5573)
			return null;
		// Set format for date parsing
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		// Declare default date object
		Date event_date = null;
		try {
			event_date = sdf.parse("0000-01-01");
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		int index = events.length-1;
		// Loop parses the date for five most recent events and compares to most recent date found
		for(int i=events.length-1; i>0; i--) {
			if(request.getTeamEventSMatches(team_number, events[i].getKey()).length < 6 && i == index)
				index--;
		}
		for(int i=events.length-1; i > 0; i--) {
			if(events[i] != null && !events[i].getStartDate().equals(null)) {
				Date date = new Date();
				try {
					date = sdf.parse(events[i].getStartDate());
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
					if(date.after(event_date) && date.before(now) &&
							request.getTeamEventSMatches(team_number, events[i].getKey()).length > 10
							&& !(events[index].getKey().equals("2017micmp")
							|| events[index].getKey().equals("2017nhfoc"))) {
						event_date = date;
						index = i;
					}
				}
			}
		return events[index].getKey();
	}
	
	/* Returns event key for the first event participated in
	(See getMostRecentEvent comments)*/
	private String getFirstEvent(int team) {
		SEvent[] events = request.getTeamSEvents(team);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date event_date = new Date();
		try {
			event_date = sdf.parse(events[0].getStartDate());
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		int index = 0;
		for(int i=0; i < events.length; i++) {
			Date date = new Date();
			if(events[i].getStartDate() != null) {
				try {
					date = sdf.parse(events[i].getStartDate());
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(date.before(event_date)) {
					event_date = date;
					index = i;
				}
			}
		}
		return events[index].getKey();
	}
	
	private void setWinLoss() {
		int event_wins = 0;
		int event_losses = 0;
		int event_ties = 0;
		if(getMostRecentEvent(team_number) == null) {
			return;
		}
		String event_key = getMostRecentEvent(team_number);
		SMatch[] matches = request.getTeamEventSMatches(team_number, event_key);
		if(matches.length==0) {
			return;
		}
		for(SMatch match:matches) {
			String alliance = getTeamAlliance(match, team_key);
			if(match.getWinningAlliance().isEmpty()) {
				event_ties++;
			}
			else if(alliance.equals(match.getWinningAlliance())) {
				event_wins++;
			}
			else event_losses++;
		}
		wins = event_wins;
		losses = event_losses;
		ties = event_ties;
	}
	
	private String getTeamAlliance(SMatch match, String team_key) {
		MatchAlliance blue = match.getBlue();
		for(String team:blue.getTeamKeys()) {
			if(team.equals(team_key)) {
				return "blue";
			}
		}
		return "red";
	}
	
	private void setOPR() {
		double opr = 0;
		if(getMostRecentEvent(team_number) == null)
			return;
		String event_key = getMostRecentEvent(team_number);
		EventOPR[] oprs = new TBA().getOprs(event_key);
		for(EventOPR result:oprs) {
			if(team_key.equals(result.getTeamKey())) {
				opr = result.getOpr();
			}
		}
		OPR = opr;
	}
	
	private void setRookieYear() {
		String event_key = getFirstEvent(team_number);
		SEvent event = new TBA().getSEvent(event_key);
		rookie_year = (int) event.getYear();
	}
	
	private boolean openConnection(){
		try {
			Class.forName("net.sourceforge.jtds.jdbc.Driver");
		    String url = ConnectionConstants.TBA_DB_CONNECTION_STRING; 
		    Connection conn = DriverManager.getConnection(url, ConnectionConstants.USER_ID, ConnectionConstants.PASS);
		    statement = conn.prepareStatement("SELECT * " + "FROM Blue_Alliance_Data " + "WHERE team_number = " + team_number, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	private void setFromDB() {
		openConnection();
		try {
			ResultSet rs = statement.executeQuery();
			if(rs.next());{
				team_key = rs.getString("team_key");
				team_name = rs.getString("team_name");
				wins = rs.getInt("team_wins");
				losses = rs.getInt("team_losses");
				ties = rs.getInt("team_ties");
				OPR = rs.getFloat("team_OPR");
				rookie_year = rs.getInt("rookie_year");
			}
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			System.err.println("There was a SQL exception:");
			e.printStackTrace();
		}
	}
	
	public String getTeamKey() {
		return team_key;
	}
	
	public int getTeamNumber() {
		return team_number;
	}
	public String getTeamName() {
		return team_name;
	}
	
	public int getTeamWins() {
		return wins;
	}
	
	public int getTeamLosses() {
		return losses;
	}
	
	public int getTeamTies() {
		return ties;
	}
	
	public double getOPR() {
		return OPR;
	}
	
	public int getRookieYear() {
		return rookie_year;
	}
}
