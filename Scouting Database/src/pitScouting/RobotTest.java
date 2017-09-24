package pitScouting;

public class RobotTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Robot robot = new Robot(2383, true, true);
		robot.setAll();
		System.out.println(robot.toString());
		boolean success = robot.runImport();
		System.out.println(success);
	}

}
