//import from the TBA-API-V3.jar 
import localconstantstorage.ConnectionConstants;
import main.*;
import requests.TeamRequest;


public class TBATest{
   
   public static void main(String[] args){
      // Set TBA auth token
      TBA.setAuthToken(ConnectionConstants.AUTH_KEY);
      // Create TBA object
      TBA tba = new TBA();
      // Pull the team object (make sure to do this asynchronously if it updates an UI 
      TeamRequest request = new TeamRequest();
      // Each model has a handy .toString method
      System.out.println(tba.getTeamSEvents(1777));
      }
  }