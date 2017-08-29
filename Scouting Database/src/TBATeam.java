import main.*;
import models.simple.*;
import models.other.matches.MatchAlliance;
import models.other.events.EventOPR;
import requests.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import localconstantstorage.ConnectionConstants;

public class TBATeam {
	
	private int team_number;
	private TeamRequest request;
	private STeam team;
	private String team_key;
	private String team_name;
	private int wins;
	private int losses;
	private int ties;
	private double OPR;
	private int years_competed;

	public TBATeam(int teamnumber) {
		// TODO Auto-generated constructor stub
		team_number = teamnumber;
		// Set Authorization Key
		TBA.setAuthToken(ConnectionConstants.AUTH_KEY);
		// Create TBA object
		TBA tba = new TBA();
		team = tba.getSTeam(team_number);
		// Create TeamRequest object
		request = new TeamRequest();
		team_key = team.getKey();
		team_name = team.getNickname();
		years_competed = tba.getYearsParticipated(team_number).length;
		setWinLoss();
		setOPR();
		
	}
	
	public String getMostRecentEvent(int team) {
		SEvent[] events = request.getTeamSEvents(team);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date event_date = new Date();
		try {
			event_date = sdf.parse("0000-01-01");
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		int index =0;
		for(int i=0; i < events.length; i++) {
			Date date = new Date();
			try {
				date = sdf.parse(events[i].getStartDate());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(date.after(event_date)) {
				event_date = date;
				index = i;
			}
		}
		return events[index].getKey();
	}
	
	public void setWinLoss() {
		String event_key = getMostRecentEvent(team_number);
		SMatch[] matches = request.getTeamEventSMatches(team_number, event_key);
		int event_wins = 0;
		int event_losses = 0;
		int event_ties = 0;
		for(SMatch match:matches) {
			String alliance = getTeamAlliance(match, team_key);
			if(alliance.equals(match.getWinningAlliance())) {
				event_wins++;
			}
			else if(match.getWinningAlliance().isEmpty()) {
				event_ties++;
			}
			else event_losses++;
		}
		wins = event_wins;
		losses = event_losses;
		ties = event_ties;
	}
	
	public String getTeamAlliance(SMatch match, String team_key) {
		MatchAlliance blue = match.getBlue();
		for(String team:blue.getTeamKeys()) {
			if(team == team_key) {
				return "blue";
			}
		}
		return "red";
	}
	
	public void setOPR() {
		String event_key = getMostRecentEvent(team_number);
		EventOPR[] oprs = new TBA().getOprs(event_key);
		double opr = 0;
		for(EventOPR result:oprs) {
			if(team_key.equals(result.getTeamKey())) {
				opr = result.getOpr();
			}
		}
		OPR = opr;
	}
	
	public String getTeamKey() {
		return team_key;
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
	
	public int getYearsParticipated() {
		return years_competed;
	}
}
