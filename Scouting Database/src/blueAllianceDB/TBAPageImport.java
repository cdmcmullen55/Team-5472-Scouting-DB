package blueAllianceDB;

import java.util.Calendar;
import requests.TeamRequest;
import models.simple.*;
import localconstantstorage.ConnectionConstants;
import main.TBA;

public class TBAPageImport{

	private STeam[] page;
	private TeamRequest request;

	
	public TBAPageImport(int number) {
		request = new TeamRequest();
		TBA.setAuthToken(ConnectionConstants.AUTH_KEY);
		int year = Calendar.getInstance().get(Calendar.YEAR);
		page = request.getSTeams(year, number);
	}

	public boolean runImport() {
		for (STeam team:page) {
			TBASingleImport s_import = new TBASingleImport((int)team.getTeamNumber());
			if(!s_import.runImport())
				return false;
		}
		return true;
	}
	
	public boolean isEmpty() {
		return (page.length == 0);
	}
}
