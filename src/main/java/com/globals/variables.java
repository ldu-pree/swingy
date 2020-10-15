package com.globals;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.net.URL;
import java.io.InputStream;
import java.util.Scanner;
import javax.swing.*;
import java.awt.*;
import javax.validation.*;
import javax.validation.constraints.*;
/**
 * variables
 */
public class variables {
	public static String operationMode;
	public static boolean ShowEnemies = true;
	public static boolean useWASD = false;

	//GUI
	public static JTextArea guiTextArea;
	public static JTextField guiTextField;

	//enemy spawnrate
	// 30 == 30% chance of spawning an enemy
	public static int VSpawn = 25;

	// individual spawnrate, AKA if a enemy spawns what type of enemy is it
	public static int easySpawn = 70;
	public static int mediumSpawn = 85;
	public static int hardSpawn = 95;
	public static int extremeSpawn = 100;
}