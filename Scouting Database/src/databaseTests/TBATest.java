package databaseTests;
//import from the TBA-API-V3.jar 
import main.*;
import models.simple.SEvent;
import models.simple.SMatch;
import requests.TeamRequest;
import localconstantstorage.ConnectionConstants;

public class TBATest{
   
   public static void main(String[] args){
      // Set TBA auth token
      TBA.setAuthToken(ConnectionConstants.AUTH_KEY);
      // Create TBA object
      TBA tba = new TBA();
      TeamRequest request = new TeamRequest();
      SEvent[] events = tba.getTeamSEvents(2383);
      String event_key = events[7].getKey();
      SMatch[] matches = request.getTeamEventSMatches(2383, event_key);
      int i = 0;
      System.out.println(matches[i]);
      System.out.println(matches[i].getBlue());
      System.out.println(matches[i].getBlue().getTeamKeys()[0]);
      System.out.println(matches[i].getWinningAlliance());
      }
  }