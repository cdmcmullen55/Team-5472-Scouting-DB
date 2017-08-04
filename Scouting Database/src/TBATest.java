//import from the TBA_API .jar 
import main.*;

public class TBATest{
   
   public static void main(String[] args){
      // Set TBA auth token
      TBA.setAuthToken("gB8DloeXdemPVOVhOLPkewJzcQjC3RVxfKilcBQpf9OIt168Pf8U5NWnNteSVLhc");
      // Create TBA object
      TBA tba = new TBA();
      // Pull the team object (make sure to do this asynchronously if it updates an UI 
      Object team = tba.customCall("team/frc5472");
      // Each model has a handy .toString method
      System.out.println(team);
      }
  }