CREATE TABLE Team_Matches_2017
(
	t_m_key		CHAR(20)	NOT NULL PRIMARY KEY,
	comp_key	CHAR(10)	REFERENCES Competitions(comp_key),
	team_key	CHAR(10)	REFERENCES Teams(team_key),
	match_num	INTEGER		NOT NULL,
	/*AUTO*/
	baseline	BIT			DEFAULT 0,
	auto_none	BIT			DEFAULT 0,
	auto_break	BIT			DEFAULT	0,
	/* gears scored */
	auto_gear_cent	INTEGER		DEFAULT 0,
	auto_gear_boil	INTEGER		DEFAULT 0,
	auto_gear_load	INTEGER		DEFAULT 0,
	/* gears picked up */
	auto_grnd_gear	INTEGER		DEFAULT 0,
	auto_load_gear	INTEGER		DEFAULT 0,
	/*gears attempted*/
	auto_g_c_att	INTEGER		DEFAULT 0,
	auto_g_b_att	INTEGER		DEFAULT 0,
	auto_g_l_att	INTEGER		DEFAULT 0,
	auto_gear_drop	INTEGER		DEFAULT 0,
	/*Balls Attempted*/
	auto_fuel_hopp	BIT			DEFAULT 0,
	auto_fuel_load	BIT			DEFAULT 0,
	auto_fuel_grnd	BIT			DEFAULT 0,
	/*0 low, 1 high*/
	auto_boil_high	BIT			DEFAULT NULL,
	/*0 is out, 1 is in*/
	auto_boil_key	BIT			DEFAULT NULL,
	/*1 is full, 0 is half, null is N/A*/
	auto_boil_cyc	BIT			DEFAULT NULL,
	/*TELE-OP*/
	tele_none	BIT			DEFAULT 0,
	tele_break	BIT			DEFAULT 0,
	/*gears*/
	tele_gear_drop	INTEGER		DEFAULT 0,
	tele_gear_cent	INTEGER		DEFAULT 0,
	tele_gear_boil	INTEGER		DEFAULT 0,
	tele_gear_load	INTEGER		DEFAULT 0,
	tele_grnd_pckp	INTEGER		DEFAULT 0,
	tele_load_pckp	INTEGER		DEFAULT 0,
	/*fuel*/
	tele_full_high	INTEGER		DEFAULT 0,
	tele_full_low	INTEGER		DEFAULT 0,
	tele_half_high	INTEGER		DEFAULT 0,
	tele_half_low	INTEGER		DEFAULT 0,
	/*MATCH END*/
	/*Takeoff*/
	takeoff_att		BIT			DEFAULT 0,
	tkoff_succ		BIT			DEFAULT 0,
	tkoff_speed		INTEGER		,
	/*Other*/
	dfnse_rate		INTEGER		,
	win			BIT			DEFAULT 0,
	rotors		BIT			DEFAULT 0,
	pascals		BIT			DEFAULT 0,
	comments	CHAR(1000)	,
)
