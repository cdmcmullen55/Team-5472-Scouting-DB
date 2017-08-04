//import from the TBA-API-V3.jar 
import localconstantstorage.ConnectionConstants;
import main.*;

public class TBATest{
   
   public static void main(String[] args){
      // Set TBA auth token
      TBA.setAuthToken(ConnectionConstants.AUTH_KEY);
      // Create TBA object
      TBA tba = new TBA();
      // Each model has a handy .toString method
      System.out.println(tba.getTeamSEvents(1777));
      }
  }