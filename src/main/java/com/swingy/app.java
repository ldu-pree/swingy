package com.swingy;

import com.file.*;
import com.heroes.*;
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
import com.globals.*;

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
			com.heroes.create.createHero();
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
		if (prompt.equals("Run") || prompt.equals("run") || prompt.equals("r")) {
			map.cY = map.pcY;
			map.cX = map.pcX;
		} else if (prompt.equals("Fight") || prompt.equals("fight") || prompt.equals("f")) {
		}
	}

	private static void promptMove() {
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