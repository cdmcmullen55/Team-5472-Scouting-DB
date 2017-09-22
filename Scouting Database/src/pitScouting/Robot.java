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
}
