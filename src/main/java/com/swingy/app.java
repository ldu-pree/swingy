package com.swingy;

import com.file.*;
import com.heroes.*;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.net.URL;
import java.io.InputStream;
import java.util.Scanner;

import javax.management.monitor.Monitor;
import javax.swing.*;
import java.awt.*;
import javax.validation.*;
import javax.validation.constraints.*;
import com.globals.*;
import java.util.Random;

public class app {
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";
	private static InputStream stream = System.in;
	private static Scanner scanner = new Scanner(stream);
	public static JFrame frame;
	public static Hero CurrentHero = new Hero();
	public static boolean isDead = false;
	public static boolean levelFinish = false;

	private static String[][] drops = {{
		"Wooden Sword",
		"Wooden Axe",
		"Wooden Staff"
	},
	{
		"Iron Sword",
		"Iron Axe",
		"Iron Staff"
	},
	{
		"Golden Sword",
		"Golden Axe",
		"Golden Staff"
	},
	{
		"Titanium Sword",
		"Titanium Axe",
		"Titanium Staff"
	}};

	public static void main(final String[] args) {
		System.out.println(prefixes.Swingy_B + "Welcome To Swingy!!!");
		if (args.length == 0){
			System.err.format(prefixes.Swingy_R + "Please Specify A Mode, Either (console) Or (gui)%n");
		} else if (args.length > 1){
			System.err.format(prefixes.Swingy_R + "Too Many Arguments Please Only Specify 1 Mode At A Time.%n");
		} else {
			if (args[0].equals("Console") || args[0].equals("console")){
				com.globals.variables.operationMode = "Console";
				System.out.println(prefixes.Swingy_B + "Swingy Is In Console Mode.");
				startConsole();
			} else if (args[0].equals("gui") || args[0].equals("Gui") || args[0].equals("GUI")){
				com.globals.variables.operationMode = "Gui";
				System.out.println(prefixes.Swingy_B + "Swingy Is In Gui Mode.");
				startGui();
			}
		}
	}
	
	private static void startConsole() {
		List<String> Heroes = com.heroes.get.getHeroes();
		promptSelectCreate(Heroes);
		System.out.print("\n");
		gameplay();
	}

	private static void startGui() {
		generateFrame();
	}

	private static void promptSelectCreate(List<String> Heroes) {
		System.out.printf(prefixes.Swingy_B + "Would You Like To Select A Existing Hero Or Create A New One? (S/C): ");
		String prompt = scanner.next();
		if (prompt.equals("S") || prompt.equals("s") || prompt.equals("Select") || prompt.equals("select")){
			selectHero(Heroes);
		}if (prompt.equals("C") || prompt.equals("c") || prompt.equals("Create") || prompt.equals("create")){
			selectHero(com.heroes.create.createHero());
		}
	}

	private static void gameplay() {
		while (!isDead){
			map.resetChar();
			System.out.printf(prefixes.Swingy_B + "Generating New Map...%n");
			map.generateMap();
			map.printMap();
			levelFinish = false;
			map.saveMap();
			while (!levelFinish && !isDead){
				promptMove();
				map.printMap();
				map.saveMap();
			}
			if (!isDead){
				System.out.printf(prefixes.Swingy_B + "Congrats You Beat The Map!!!%n");
				int lvl = CurrentHero.Level + 1;
				CurrentHero.Experience += (int)((((lvl*1000)+(((int)Math.pow((lvl - 1),2))*450))/4));
				//System.out.printf("%d|%d%n", (int)(((lvl*1000)+(((int)Math.pow((lvl - 1),2))*450))/4), (int)((lvl*1000)+((int)Math.pow((lvl - 1),2))*450));
				if (CurrentHero.Experience >= (int)((lvl*1000)+((int)Math.pow((lvl - 1),2))*450)){
					CurrentHero.Level++;
				}
				com.file.write.writeSL(CurrentHero.Name+".hero", "Level:"+CurrentHero.Level, 1);
				com.file.write.writeSL(CurrentHero.Name+".hero", "XP:"+CurrentHero.Experience, 2);
				System.out.printf(prefixes.Swingy_B + "Your Stats Are Now: LVL(%d) XP(%d)%n", CurrentHero.Level, CurrentHero.Experience);
			}
		}
		System.out.printf(prefixes.Swingy_B + "Oh Nooo You Died!!!%n");
		System.out.printf(prefixes.Swingy_B + "Your Stats Were: LVL(%d) XP(%d)%n", CurrentHero.Level, CurrentHero.Experience);
	}

	public static void battleTime(){
		int lvl = 0;
		if (CurrentHero.Level >= map.enemies.length) {
			lvl = map.enemies.length-1;
		} else {
			lvl = CurrentHero.Level;
		}
		int level = CurrentHero.Level;
		int mapWidth = (level-1)*5+10-(level%2);
		System.out.printf(prefixes.Swingy_B_F + "You Have Encountered A %s What Do You Do? (Run/Fight): ", map.enemies[lvl][map.map[(((mapWidth+1)/2)-1)-map.cY][(((mapWidth+1)/2)-1)+map.cX]-5]);
		String prompt = scanner.next();
		String monster = map.enemies[lvl][map.map[(((mapWidth+1)/2)-1)-map.cY][(((mapWidth+1)/2)-1)+map.cX]-5];
		String monsterW = map.enemiesW[lvl][map.map[(((mapWidth+1)/2)-1)-map.cY][(((mapWidth+1)/2)-1)+map.cX]-5];
		if (prompt.equals("Run") || prompt.equals("run") || prompt.equals("r")) {
			Random r2 = new Random();
			int low2 = 0;
			int high2 = 100;
			int result2 = r2.nextInt(high2-low2) + low2;
			if (result2 >= 0 && result2 < 50){
				map.cY = map.pcY;
				map.cX = map.pcX;
				System.out.printf(prefixes.Swingy_B_F + "You Chose To Run From The %s? You're A Coward But A Fast One, So You Escape Barely!!!%n", monster);
			} else {
				System.out.printf(prefixes.Swingy_B_F + "You Chose To Run From The %s? You Tried To Run But Fell Over A Bag Of Nuts! Now You Must Face The %s!%n", monster, monster);
				fighting(monster,monsterW);
			}
		} else if (prompt.equals("Fight") || prompt.equals("fight") || prompt.equals("f")) {
			fighting(monster,monsterW);
		}
	}

	private static void fighting(String monster, String weapon) {
		System.out.printf(prefixes.Swingy_B_F + "The %s Unleashes It's %s Upon You How Do You Answer This Devious Act?:%n", monster, weapon);
		promptAction2(monster);
		int level = CurrentHero.Level;
		int mapWidth = (level-1)*5+10-(level%2);
		map.map[(((mapWidth+1)/2)-1)-map.pcY][(((mapWidth+1)/2)-1)+map.pcX] = 3;
		map.map[(((mapWidth+1)/2)-1)-map.cY][(((mapWidth+1)/2)-1)+map.cX] = 2;
	}

	private static void promptAction2(String monster) {
		System.out.printf(prefixes.Swingy_B_F + "Would you like to defend yourself from the %s? or fight back and attack the %s?(a/d): ", monster, monster);
		String prompt = scanner.next();
		if (prompt.toLowerCase().equals("d") || prompt.toLowerCase().equals("defense") || prompt.toLowerCase().equals("def")){
			promptDefense();
		} else if (prompt.toLowerCase().equals("a") || prompt.toLowerCase().equals("attack") || prompt.toLowerCase().equals("att")){
			promptAttack(monster);
		} else {
			System.out.printf(prefixes.Swingy_R_F + "Invalid option, please make use of (defense/d/def) or (attack/a/att)%n");
			promptAction2(monster);
		}
	}
	private static void promptAttack(String monster) {
		System.out.printf(prefixes.Swingy_B_F + "What Would You Like To Attack With?%n");
		int i = 0;
		for (Artifacts artw : CurrentHero.Artifacts){
			if (artw.Type.equals("Attack")){
				System.out.printf(prefixes.Swingy_B_F_A + "%d: A %s with %d Attack Buff?%n",i,artw.Name,artw.Stat);
				i++;
			}
		}
		String prompt = scanner.next();
		int selected = -1;
		try {
			selected = Integer.parseInt(prompt);
		} catch (NumberFormatException nfe) {
			System.out.printf(prefixes.Swingy_R_F + "Invalid Option, Please Select From The List%n");
			promptAttack(monster);
			return;
		}
		if (selected >= 0 && selected < i){
			System.out.printf(prefixes.Swingy_B_F + "Selected %d%n", selected);
			Random r2 = new Random();
			int low2 = 0;
			int high2 = 100;
			int result2 = r2.nextInt(high2-low2) + low2;
			int chance = 0;
			int i2 = 0;
			int j = 0;
			for (int k = 0; k < map.enemies.length; k++) { 
				i2 = 0;
				for (String m2 : map.enemies[k]) {
					if (m2.equals(monster)){
						if (i2 == 0) {
							chance = 60;
						} else if (i2 == 1) {
							chance = 50;
						} else if (i2 == 2) {
							chance = 35;
						} else if (i2 == 3) {
							chance = 20;
						}
					}
					i2++;
				}
				j++;
        	}
			if (result2 >= 0 && result2 < chance){
				map.cY = map.pcY;
				map.cX = map.pcX;
				System.out.printf(prefixes.Swingy_B_F + "You Tried To Attack The %s. But It's Defense Is Too High!%n", monster);
				promptAttack(monster);
			} else {
				System.out.printf(prefixes.Swingy_B_F + "You Defeated The %s. Congrats!%n", monster);
				runDropSystem(monster);
			}
		} else {
			System.out.printf(prefixes.Swingy_R_F + "Invalid Option, Please Select From The List%n");
			promptAttack(monster);
		}
	}
	private static void runDropSystem(String monster) {
		int i = 0;
		int j = 0;
		for (int k = 0; k < map.enemies.length; k++) { 
			i = 0;
			for (String m2 : map.enemies[k]) {
				if (m2.equals(monster)){
					System.out.printf(prefixes.Swingy_B_D + "The %s Dropped A %s, Would You Like To Keep It (Y/N):",monster,drops[i][0]);
					String prompt = scanner.next();
					if (prompt.equals("Y") || prompt.equals("y") || prompt.equals("Yes") || prompt.equals("yes")){
						System.out.printf(prefixes.Swingy_B_D + "%s Picked Up%n", drops[i][0]);
						final List<String> HData = com.file.read.readF(CurrentHero.Name+".hero");
						for (String line : HData){
							String[] data = line.split(":");
							if (data[0].equals("Artifacts")){
								com.file.write.writeSL(CurrentHero.Name+".hero", data[1]+"#"+drops[i][0]+"=Attack=2="+drops[i][0]+" Helps Attack A Bit", 3);
							}
						}
					}if (prompt.equals("N") || prompt.equals("n") || prompt.equals("No") || prompt.equals("no")){
					}
				}
				i++;
			}
			j++;
        }
	}
	private static void promptDefense() {
		System.out.printf(prefixes.Swingy_B_F + "What Would You Like To Defend With?%n");
		int i = 0;
		for (Artifacts artw : CurrentHero.Artifacts){
			if (artw.Type.equals("Defense")){
				System.out.printf(prefixes.Swingy_B_F_D + "%d: A %s with %d Defense Buff?%n",i,artw.Name,artw.Stat);
				i++;
			}
		}
		String prompt = scanner.next();
		int selected = -1;
		try {
			selected = Integer.parseInt(prompt);
		} catch (NumberFormatException nfe) {
			System.out.printf(prefixes.Swingy_R_F + "Invalid Option, Please Select From The List%n");
			promptDefense();
		}
		if (selected >= 0 && selected < i){
			System.out.printf(prefixes.Swingy_B_F + "Selected %d%n", selected);
		} else {
			System.out.printf(prefixes.Swingy_R_F + "Invalid Option, Please Select From The List%n");
			promptDefense();
		}
	}

	private static void promptMove() {
		File tmpDir = new File(System.getProperty("user.dir")+"/target/classes/data/swingy.config");
		if(tmpDir.exists() && !com.swingy.app.levelFinish){
			List<String> lines = com.file.read.readF("swingy.config");
			if (lines.get(1).split(":")[1].strip().equals("1")){
				com.globals.variables.useWASD = false;
			}	else {
				com.globals.variables.useWASD = true;
			}
		}
		if (com.globals.variables.useWASD){
			System.out.printf(prefixes.Swingy_B + "(%d:%d)(%d:%d)Where Would You Like To Go? (W/A/S/D): ",map.cY,map.cX,map.pcY,map.pcX);
			String prompt = scanner.next();
			if (prompt.equals("W") || prompt.equals("w")){
				map.pcY = map.cY;
				map.pcX = map.cX;
				map.cY += 1;
			} else if (prompt.equals("D") || prompt.equals("d")){
				map.pcY = map.cY;
				map.pcX = map.cX;
				map.cX += 1;
			} else if (prompt.equals("S") || prompt.equals("s")){
				map.pcY = map.cY;
				map.pcX = map.cX;
				map.cY -= 1;
			} else if (prompt.equals("A") || prompt.equals("a")){
				map.pcY = map.cY;
				map.pcX = map.cX;
				map.cX -= 1;
			}
		} else {
			System.out.printf(prefixes.Swingy_B + "(%d:%d)(%d:%d)Where Would You Like To Go? (N/E/S/W): ",map.cY,map.cX,map.pcY,map.pcX);
			String prompt = scanner.next();
			if (prompt.equals("N") || prompt.equals("n")){
				map.pcY = map.cY;
				map.pcX = map.cX;
				map.cY += 1;
			} else if (prompt.equals("E") || prompt.equals("e")){
				map.pcY = map.cY;
				map.pcX = map.cX;
				map.cX += 1;
			} else if (prompt.equals("S") || prompt.equals("s")){
				map.pcY = map.cY;
				map.pcX = map.cX;
				map.cY -= 1;
			} else if (prompt.equals("W") || prompt.equals("w")){
				map.pcY = map.cY;
				map.pcX = map.cX;
				map.cX -= 1;
			}
		}
		map.moveChar();
	}

	private static void selectHero(List<String> Heroes) {
		System.out.printf(prefixes.Swingy_B_S + "Please Select A Hero:%n");
		for (String hero : Heroes){
			System.out.printf(prefixes.Swingy_B_S_H + "%s%n", hero);
		}
		System.out.printf(prefixes.Swingy_B_S + "Please Type The Name Of The Hero: ");
		String Name = scanner.next();
		loadHero(Name);
	}

	private static void loadHero(String Hero) {
		final List<String> HData = com.file.read.readF(Hero+".hero");
		CurrentHero.Name = Hero;
		String ClassName = "";
		for (String line : HData){
			String[] data = line.split(":");
			if (data[0].equals("Class")){
				ClassName = data[1];
				switch(data[1]) {
					case "Elf":
					  // code block
					  break;
					case "Mage":
					  // code block
					  break;
					case "Paladin":
					  // code block
					  break;
					case "Troll":
					  // code block
					  break;
				  }
			} else if (data[0].equals("Level")){
				CurrentHero.Level = Integer.parseInt(data[1]);
			} else if (data[0].equals("XP")){
				CurrentHero.Experience = Integer.parseInt(data[1]);
			} else if (data[0].equals("Artifacts")){
				String[] Arts = data[1].split("#");
				System.out.printf("%s%n%sName: %s%n%sClass: %s%n%sLevel: %d%n%sExp: %d%n%sArtifacts:%n",
				prefixes.Swingy_B_L_H,
				prefixes.Swingy_B_L,
				CurrentHero.Name,
				prefixes.Swingy_B_L,
				ClassName,
				prefixes.Swingy_B_L,
				CurrentHero.Level,
				prefixes.Swingy_B_L,
				CurrentHero.Experience,
				prefixes.Swingy_B_L
				);
				for (String Art : Arts){
					String[] ArtStats = Art.split("=");
					Artifacts NewArt = new Artifacts();
					NewArt.Name = ArtStats[0];
					NewArt.Type = ArtStats[1];
					NewArt.Stat = Integer.parseInt(ArtStats[2]);
					NewArt.Description = ArtStats[3];
					System.out.printf("%sName: %s%n%sType: %s%n%sStat: %d%n%sDescription: %s%n",
					prefixes.Swingy_B_L_Art,
					NewArt.Name,
					prefixes.Swingy_B_L_Art,
					NewArt.Type,
					prefixes.Swingy_B_L_Art,
					NewArt.Stat,
					prefixes.Swingy_B_L_Art,
					NewArt.Description
					);
					CurrentHero.Artifacts.add(NewArt);
				}
			}
		}
	}

	public File getFileFromResources(String fileName) {

        ClassLoader classLoader = getClass().getClassLoader();

        URL resource = classLoader.getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("file is not found!");
        } else {
            return new File(resource.getFile());
        }

	}
	
	private static void generateFrame(){
		//Creating the Frame
        JFrame frame = new JFrame("Swingy");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);

        //Creating the MenuBar and adding components
        JMenuBar mb = new JMenuBar();
        JMenu m1 = new JMenu("Hero");
        mb.add(m1);
        JMenuItem m11 = new JMenuItem("Save");
        m1.add(m11);

        //Creating the panel at bottom and adding components
        JPanel panel = new JPanel(); // the panel is not visible in output
        JLabel label = new JLabel("What's Your Action?");
        com.globals.variables.guiTextField = new JTextField(30); // accepts upto 10 characters
        JButton submit = new JButton("Submit");
        panel.add(label); // Components Added using Flow Layout
        panel.add(com.globals.variables.guiTextField);
        panel.add(submit);

        // Text Area at the Center
        com.globals.variables.guiTextArea = new JTextArea();

		submit.addActionListener(new java.awt.event.ActionListener() {

			@Override
			public void actionPerformed(java.awt.event.ActionEvent e) {
				com.globals.variables.guiTextArea.append(com.globals.variables.guiTextField.getText()+"\n");
			}
		});

        //Adding Components to the frame.
        frame.getContentPane().add(BorderLayout.SOUTH, panel);
        frame.getContentPane().add(BorderLayout.NORTH, mb);
        frame.getContentPane().add(BorderLayout.CENTER, com.globals.variables.guiTextArea);
        frame.setVisible(true);
	}
}