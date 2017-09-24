package pitScouting;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
//import java.io.*;
import java.util.Scanner;

import localconstantstorage.ConnectionConstants;

public class Robot {

	private String robot_key;
	private String team_key;
	private boolean fuel;
	private boolean gears;
	private String drive_train;
	private int cims_used;
	private int speed_fps;
	private int speed_scaled;
	private boolean shift_gears;
	private int shift_gears_int;
	private int robot_wt;
	private int ball_cap;
	private boolean vision;
	private int vision_int;
	private boolean active_gear;
	private int active_gear_int;
	private boolean ground_gear;
	private int ground_gear_int;
	private int run_sec;
	private int run_scale;
	private boolean ground_ball;
	private int ground_ball_int;
	private int strategy;
	private String start_pos;
	private boolean baseline;
	private int baseline_int;
	private boolean auto_gear;
	private int auto_gear_int;
	private int auto_ball;
	private boolean auto_low;
	private int auto_low_int;
	private int tele_gears;
	private int tele_balls;
	private boolean tele_low;
	private int tele_low_int;
	private int acc_scale;
	private boolean climb;
	private int climb_int;
	private int climb_time;
	private String comments;
	private Scanner scanner = new Scanner(System.in);
	private Connection conn;
	
	public Robot(int team_num, boolean fuel, boolean gears) {
		// TODO Auto-generated constructor stub
		team_key = "frc" + team_num;
		robot_key = team_key + "_2017";
		this.fuel = fuel;
		this.gears = gears;
	}
	
	public void setAll() {
		System.out.println("General Information:");
		setDriveTrain();
		setCimsUsed();
		setSpeedFPS();
		setSpeedScaled();
		setGearShift();
		setRobotWeight();
		setBallCapacity();
		setVision();
		setActiveGear();
		setGroundGear();
		setRunSec();
		setRunScale();
		setGroundBall();
		setStrategy();
		System.out.println("Auto Round:");
		setStartPosition();
		setBaseline();
		setAutoGear();
		setAutoBall();
		setAutoLow();
		System.out.println("Tele Round:");
		setTeleGears();
		setTeleBalls();
		setTeleLow();
		setAccScale();
		setClimb();
		setClimbTime();
		setComments();
	}
	
	private String writeQuery() {
		//tested
		String query = "INSERT INTO Pit_Scouting_2017 (robot_key, team_key, drive_train, cims_used, ";
		if(speed_fps==0)
			//tested
			query = query+"speed_scaled, ";
		else
			//tested
			query = query+"speed_fps, ";
		//tested
		query = query+"shift_gears, robot_wt, ball_cap, vision, ";
		if(gears&&run_sec==0)
			query = query+"active_gear, ground_gear, run_scale, auto_gear, tele_gears, ";
		if(gears&&run_sec!=0)
			//tested
			query = query+"active_gear, ground_gear, run_sec, auto_gear, tele_gears, ";
		if(fuel)
			//tested
			query = query+"ground_ball, auto_ball, auto_low, tele_balls, tele_low, acc_scale, ";
		//tested
		query = query+"strategy, start_pos, baseline, climb, comments";
		if(climb)
			//tested
			query = query+", climb_time";
		//tested
		query = query+")" + "VALUES (\'"+robot_key+"\', \'"+team_key+"\', \'"+drive_train+"\', "
				+cims_used+", ";
		if(speed_fps==0)
			//tested
			query = query+speed_scaled+", ";
		else
			//tested
			query = query+speed_fps+", ";
		//tested
		query = query+shift_gears_int+", "+robot_wt+", "+ball_cap+", "+vision_int+", ";
		if(gears)
			//tested
			query = query+active_gear_int+", "+ground_gear_int+", ";
		if(gears&&run_sec==0)
			query = query+run_scale+", "+auto_gear_int+", "+tele_gears+", ";
		if(gears&&run_sec!=0)
			//tested
			query = query+run_sec+", "+auto_gear_int+", "+tele_gears+", ";
		if(fuel)
			//tested
			query = query+ground_ball_int+", "+auto_ball+", "+auto_low_int+", "+
					tele_balls+", "+tele_low_int+", "+acc_scale+", ";
		//tested
		query = query+strategy+", \'"+start_pos+"\', "+baseline_int+", "+climb_int+", \'"
				+comments+"\'";
		if(climb)
			//tested
			query = query+", "+climb_time;
		//tested
		query = query+")";
		return query;
	}
	
	private boolean openConnection(){
		try
		{
			Class.forName("net.sourceforge.jtds.jdbc.Driver");
			String url = ConnectionConstants.TBA_DB_CONNECTION_STRING;
			conn = DriverManager.getConnection(url, ConnectionConstants.USER_ID, ConnectionConstants.PASS);
			return true;
		}
		catch (Exception e)
		{
			System.err.println("Exception thrown:"); 
      			System.err.println(e.getMessage());
			return false;
		}
	}
	
	private boolean importRobot() {
		try {
			Statement statement = conn.createStatement();
			statement.execute(writeQuery());
			return true;
		}
		catch(Exception e){
			System.err.println(e.getMessage());
			return false;
		}
	}
	
	public boolean runImport() {
		if(!openConnection()) {
			System.err.println("There was a connection error");
			return false;
		}
		if(!importRobot()) {
			System.err.println("There was a SQL Query error");
			return false;
		}
		return true;
	}
	
	//General Information
	public void setDriveTrain() {
		System.out.println("Enter drive train type (type \"null\" if unknown): ");
		String input = scanner.nextLine();
		if(!input.equals("null"))
			drive_train = input;
		else
			drive_train = null;
	}

	public void setCimsUsed() {
		System.out.println("Enter number of CIM motors used : ");
		cims_used = Integer.parseInt(scanner.nextLine());
	}
	
	public void setSpeedFPS() {
		System.out.println("Enter speed in feet per second (enter 0 if unknown): ");
		speed_fps = Integer.parseInt(scanner.nextLine());
	}
	
	public void setSpeedScaled() {
		if(speed_fps==0) {
			System.out.println("Enter speed on a scale of 1 to 5: ");
			speed_scaled = Integer.parseInt(scanner.nextLine());
		}
	}
	
	public void setGearShift() {
		System.out.println("Enter \"true\" for shifting gearbox, \"false\" for static: ");
		shift_gears = Boolean.parseBoolean(scanner.nextLine());
		if(shift_gears)
			shift_gears_int = 1;
		else
			shift_gears_int = 0;
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
		if(fuel) {
			System.out.println("Enter robot ball capacity: ");
			ball_cap = Integer.parseInt(scanner.nextLine());
		}
		else
			ball_cap = 0;
	}
	
	public void setVision() {
		System.out.println("Enter \"true\" for vision system, \"false\" for none: ");
		vision = Boolean.parseBoolean(scanner.nextLine());
		if(vision) {
			System.out.println("Include details in your comments.");
			vision_int = 1;
		}
		else
			vision_int = 0;
	}
	
	public void setActiveGear() {
		if(gears) {
			System.out.println("Enter \"true\" for active dropoff, \"false\" for passive: ");
			active_gear = Boolean.parseBoolean(scanner.nextLine());
			if(active_gear)
				active_gear_int = 1;
			else
				active_gear_int = 0;
		}
	}
	
	public void setGroundGear() {
		if(gears) {
			System.out.println("Enter \"true\" for ground pickup, \"false\" for none: ");
			ground_gear = Boolean.parseBoolean(scanner.nextLine());
			if(ground_gear)
				ground_gear_int = 1;
			else
				ground_gear_int = 0;
		}
	}
	
	public void setRunSec() {
		if(gears) {
			System.out.println("Enter gear run time in sec (0 if unknown): ");
			run_sec = Integer.parseInt(scanner.nextLine());
		}
	}
	
	public void setRunScale() {
		if(gears&&run_sec==0) {
			System.out.println("Enter gear run speed on a scale of 1 to 5: ");
			run_scale = Integer.parseInt(scanner.nextLine());
		}
	}
	
	public void setGroundBall() {
		if(fuel) {
			System.out.println("Enter 1 for ground ball pickup, 0 for hopper only: ");
			ground_ball = Boolean.parseBoolean(scanner.nextLine());
		}
	}
	
	public void setStrategy() {
		System.out.println("Enter 0 for offense, 1 for defense, 2 for both: ");
		strategy = Integer.parseInt(scanner.nextLine());
	}
	
	
	//Auto round
	public void setStartPosition() {
		System.out.println("Enter preferred start position (10 characters or less)");
		start_pos = scanner.nextLine();
	}
	
	public void setBaseline() {
		System.out.println("Enter \"true\" for baseline cross, \"false\" for none: ");
		baseline = Boolean.parseBoolean(scanner.nextLine());
		if(baseline)
			baseline_int = 1;
		else
			baseline_int = 0;
	}
	
	public void setAutoGear() {
		if(gears) {
			System.out.println("Enter \"true\" for gear placed in auto, \"false\" for none: ");
			auto_gear = Boolean.parseBoolean(scanner.nextLine());
			if(auto_gear)
				auto_gear_int = 1;
			else
				auto_gear_int = 0;
		}
	}
	
	public void setAutoBall() {
		if(fuel) {
			System.out.println("Enter number of balls scored in auto: ");
			auto_ball = Integer.parseInt(scanner.nextLine());
		}
	}
	
	public void setAutoLow() {
		if(fuel&&auto_ball!=0) {
			System.out.println("Enter \"true\" for low goal, \"false\" for high: ");
			auto_low = Boolean.parseBoolean(scanner.nextLine());
			if(auto_low)
				auto_low_int = 1;
			else
				auto_low_int = 0;
		}
	}
	
	
	//Tele round
	public void setTeleGears() {
		if(gears) {
			System.out.println("Enter projected number of gears scored in match: ");
			tele_gears = Integer.parseInt(scanner.nextLine());
		}
	}
	
	public void setTeleBalls() {
		if(fuel) {
			System.out.println("Enter projected number of balls scored in match: ");
			tele_balls = Integer.parseInt(scanner.nextLine());
		}
	}
	
	public void setTeleLow() {
		if(fuel&&tele_balls!=0) {
			System.out.println("Enter \"true\" for low goal, \"false\" for high: ");
			tele_low = Boolean.parseBoolean(scanner.nextLine());
			if(tele_low)
				tele_low_int = 1;
			else
				tele_low_int = 0;
		}
	}
	
	public void setAccScale() {
		if(fuel&&tele_balls!=0) {
			System.out.println("Enter fuel accuracy on a scale of 1 to 5: ");
			acc_scale = Integer.parseInt(scanner.nextLine());
		}
	}
	
	public void setClimb() {
		System.out.println("Enter \"true\" for climb, \"false\" for none: ");
		climb = Boolean.parseBoolean(scanner.nextLine());
		if(climb)
			climb_int = 1;
		else
			climb_int = 0;
	}
	
	public void setClimbTime() {
		if(climb) {
			System.out.println("Enter climb time in seconds: ");
			climb_time = Integer.parseInt(scanner.nextLine());
		}
	}
	
	public void setComments() {
		System.out.println("Enter additional comments: ");
		comments = scanner.nextLine();
	}
	
	public String toString() {
		String general = "Robot key: "+robot_key+"\r\nTeam key: "+team_key+"\r\nDrive train: "+
				drive_train+"\r\nCIM motors: "+cims_used;
		if(speed_fps!=0)
			general = general+"\r\nSpeed(fps): "+speed_fps;
		else
			general = general+"\r\nSpeed(1-5): "+speed_scaled;
		general = general+"\r\nShifting gearbox? "+shift_gears+"\r\nWeight: "+robot_wt+" lbs";
		if(fuel)
			general = general+"\r\nFuel capacity: "+ball_cap;
		general = general+"\r\nVision? "+vision;
		if(gears)
			general = general+"\r\nActive gear pickup? "+active_gear+"\r\nGround gear pickup? "
				+ground_gear;
		if(strategy==0)
			general = general+"\r\nOffensive";
		else if(strategy==1)
			general = general+"\r\nDefensive";
		else
			general = general+"\r\nBoth";
		String auto = "\r\nStarting position:"+start_pos+"\r\nBaseline? "+baseline;
		if(gears)
			auto = auto+"\r\nAuto Gear? "+auto_gear;
		if(fuel)
			auto = auto+"\r\nAuto Fuel? "+auto_ball+"\r\nLow goal? "+auto_low;
		String tele = "";
		if(gears)
			tele = tele+"\r\nTele Gears: "+tele_gears;
		if(fuel)
			tele = tele+"\r\nTele Fuel: "+tele_balls+"\r\nLow goal? "+tele_low+"\r\nAccuracy(1-5): "
			+acc_scale;
		tele = tele+"\r\nClimb? "+climb;
		if(climb)
			tele = tele+"\r\nClimb time: "+climb_time;
		return general+auto+tele+"\r\n"+comments;
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
	
	public int getTeleBalls(){
	
	return tele_balls;
	
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
