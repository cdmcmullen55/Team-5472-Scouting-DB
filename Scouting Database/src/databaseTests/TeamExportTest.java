package databaseTests;
import blueAllianceDB.TBATeam;

public class TeamExportTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TBATeam team = new TBATeam(5472);
		System.out.println(team.getTeamKey());
		System.out.println(team.getTeamName());
		System.out.println("Wins: " + team.getTeamWins());
		System.out.println("Losses: " + team.getTeamLosses());
		System.out.println("Ties: " + team.getTeamTies());
		System.out.println("OPR: " + team.getOPR());
		System.out.println("Rookie Year: " + team.getRookieYear());
	}

}
