package com.swingy;

/**
 * prefixes
 */
public class prefixes {
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";

	public static final String Swingy_B = ANSI_WHITE + "[" + ANSI_BLUE + "Swingy" + ANSI_WHITE + "] ";
	public static final String Swingy_R = ANSI_WHITE + "[" + ANSI_RED + "Swingy" + ANSI_WHITE + "] ";
	
	public static final String Swingy_B_C = ANSI_WHITE + "[" + ANSI_BLUE + "Swingy->"+ANSI_YELLOW+"Create" + ANSI_WHITE + "] ";
	public static final String Swingy_R_C = ANSI_WHITE + "[" + ANSI_RED + "Swingy->"+ANSI_YELLOW+"Create" + ANSI_WHITE + "] ";
	
	public static final String Swingy_B_C_Classes = ANSI_WHITE + "[" + ANSI_BLUE + "Swingy->"+ANSI_YELLOW+"Create->"+ANSI_GREEN+"Classes" + ANSI_WHITE + "] ";
	public static final String Swingy_R_C_Classes = ANSI_WHITE + "[" + ANSI_RED + "Swingy->"+ANSI_YELLOW+"Create->"+ANSI_GREEN+"Classes" + ANSI_WHITE + "] ";

	public static final String Swingy_B_S = ANSI_WHITE + "[" + ANSI_BLUE + "Swingy->"+ANSI_YELLOW+"Select" + ANSI_WHITE + "] ";
	public static final String Swingy_R_S = ANSI_WHITE + "[" + ANSI_RED + "Swingy->"+ANSI_YELLOW+"Select" + ANSI_WHITE + "] ";

	public static final String Swingy_B_S_H = ANSI_WHITE + "[" + ANSI_BLUE + "Swingy->"+ANSI_YELLOW+"Select->"+ANSI_GREEN+"Hero" + ANSI_WHITE + "] ";
	public static final String Swingy_R_S_H = ANSI_WHITE + "[" + ANSI_RED + "Swingy->"+ANSI_YELLOW+"Select->"+ANSI_GREEN+"Hero" + ANSI_WHITE + "] ";
}