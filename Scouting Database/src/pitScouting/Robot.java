package pitScouting;

import java.io.*;
import java.util.Scanner;

public class Robot {

	private String robot_key;
	private String team_key;
	private String drive_train;
	private int cims_used;
	private int speed_fps;
	private int speed_scaled;
	private boolean shift_gears;
	private int robot_wt;
	private int ball_cap;
	private boolean vision;
	private boolean active_gear;
	private boolean ground_gear;
	private int run_sec;
	private int run_scale;
	private boolean ground_ball;
	private int strategy;
	private String start_pos;
	private boolean baseline;
	private boolean auto_gear;
	private int auto_ball;
	private boolean auto_low;
	private int tele_gears;
	private int tele_bals;
	private boolean tele_low;
	private int acc_scale;
	private boolean climb;
	private int climb_time;
	private String comments;
	private Scanner scanner = new Scanner(System.in);
	
	public Robot(int team_num) {
		// TODO Auto-generated constructor stub
		team_key = "frc" + team_num;
		robot_key = team_key + "_2017";
	}
	
	public void setDriveTrain() {
		System.out.println("Enter drive train type (type \"null\" if unknown): ");
		String input = scanner.nextLine();
		if(!input.equals("null"))
			drive_train = scanner.nextLine();
		else
			drive_train = null;
	}

	public void setCimsUsed() {
		System.out.println("Enter number of CIM motors used :");
		cims_used = Integer.parseInt(scanner.nextLine());
	}
	
	public void setSpeedFPS() {
		System.out.println("Enter  :");
		cims_used = Integer.parseInt(scanner.nextLine());
	}
	
	public void setSpeedScaled() {
		System.out.println("Enter speed on a scale of 1 to 5: ");
		speed_scaled = Integer.parseInt(scanner.nextLine());
	}
	
	public void setGearShift() {
		System.out.println("Enter \"true\" for shifting gearbox, \"false\" for static: ");
		shift_gears = Boolean.parseBoolean(scanner.nextLine());
	}
	
	public void setRobotWeight() {
		System.out.println("Enter robot weight in lbs (\"null\" if unknown): ");
		String input = scanner.nextLine();
		if(!input.equals("null"))
			robot_wt = Integer.parseInt(input);
		else
			return;
	}
	
	public void setBallCapacity() {
		System.out.println("Enter robot ball capacity (0 if none): ");
		ball_cap = Integer.parseInt(scanner.nextLine());
	}
	
	public void setVision() {
		System.out.println("Enter \"true\" for vision system, \"false\" for none: ");
		vision = Boolean.parseBoolean(scanner.nextLine());
		System.out.println("Include details in your comments.");
	}
	
	public void setActiveGear() {
		System.out.println("Enter \"true\" for active pickup, \"false\" for passive: ");
		active_gear = Boolean.parseBoolean(scanner.nextLine());
	}
	
	public void setGroundGear() {
		System.out.println("Enter \"true\" for ground pickup, \"false\" for none: ");
		ground_gear = Boolean.parseBoolean(scanner.nextLine());
	}
	
	public void setRunSec() {
		System.out.println("Enter gear run time in sec (0 if unknown): ");
		run_sec = Integer.parseInt(scanner.nextLine());
	}
	
	public void setRunScale() {
		System.out.println("Enter gear run speed on a scale of 1 to 5: ");
		run_scale = Integer.parseInt(scanner.nextLine());
	}
	
	public void setGroundBall() {
		System.out.println("Enter 1 for ground ball pickup, 0 for hopper only: ");
		ground_ball = Boolean.parseBoolean(scanner.nextLine());
	}
	
	public void setStrategy() {
		System.out.println("Enter 0 for offense, 1 for defense, 2 for both: ");
		strategy = Integer.parseInt(scanner.nextLine());
	}
	
	public void setStartPosition() {
		System.out.println("Enter preferred start position (10 characters or less)");
		start_pos = scanner.nextLine();
	}
	
	public void setBaseline() {
		System.out.println("Enter \"true\" for baseline cross, \"false\" for none: ");
		baseline = Boolean.parseBoolean(scanner.nextLine());
	}
	
	public String getRobotKey(){
	
		return robot_key;
		
	}
	
	public String getTeamKey(){
	
	return team_key;
	
	}
	
	public String getDriveTrain(){
	
	return drive_train;
	
	}
	
	public int getCimsUsed(){
	
	return cims_used;
	
	}
	
	public int getSpeedFps(){
	
	return speed_fps;
	
	}
	
	public int getSpeedScaled(){
	
	return speed_scaled;
	
	}
	
	public boolean getShiftGears(){
	
	return shift_gears;
	
	}
	
	public int getRobotWt(){
	
	return robot_wt;
	
	}
	
	public int getBallCap(){
	
	return ball_cap;
	
	}
	
	public boolean getVision(){
	
	return vision;
	
	}
	
	public boolean getActiveGear(){
	
	return active_gear;
	
	}
	
	public boolean getGroundGear(){
	
	return ground_gear;
	
	}
	
	public int getRunSec(){
	
	return run_sec;
	
	}
	
	public int getRunScale(){
	
	return run_scale;
	
	}
	
	public boolean getGroundBall(){
	
	return ground_ball;
		
	}
	
	public int getStrategy(){
	
	return strategy;
	
	}
	
	public String getStartPos(){
	
	return start_pos;
	
	}
	
	public boolean getBaseline(){
	
	return baseline;
	
	}
	
	public boolean getAutoGear(){
	
	return auto_gear;
	
	}
	
	public int getAutoBall(){
	
	return auto_ball;
	
	}
	
	public boolean getAutoLow(){
	
	return auto_low;
	
	}
	
	public int getTeleGears(){
	
	return tele_gears;
	
	}
	
	public int getTeleBals(){
	
	return tele_bals;
	
	}
	
	public boolean getTeleLow(){
	
	return tele_low;
	
	}
	
	public int getAccScale(){
	
	return acc_scale;
	
	}
	
	public boolean getClimb(){
	
	return climb;
	
	}
	
	public int getClimbTime(){
	
	return climb_time;
	
	}
	
	public String comments(){
	
	return comments;
	
	}
}
