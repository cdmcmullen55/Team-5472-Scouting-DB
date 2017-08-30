package databaseTests;
//import from the TBA-API-V3.jar 
import main.*;
import models.simple.SEvent;
import localconstantstorage.ConnectionConstants;

public class TBATest{
   
   public static void main(String[] args){
      // Set TBA auth token
      TBA.setAuthToken(ConnectionConstants.AUTH_KEY);
      // Create TBA object
      TBA tba = new TBA();
      SEvent[] events = tba.getTeamSEvents(5472);
      System.out.println(events[0]);
      }
  }