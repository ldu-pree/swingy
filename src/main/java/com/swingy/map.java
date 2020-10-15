package com.swingy;

import java.util.ArrayList;
import java.util.List;
import com.globals.*;
import java.io.File;

import jdk.nashorn.internal.ir.ReturnNode;

import java.util.Random;
/**
 * map
 */
public class map {
	public static int level = com.swingy.app.CurrentHero.Level;
	public static Integer[][] map = new Integer[(level-1)*5+10-(level%2)][(level-1)*5+10-(level%2)];
	public static String[][] enemies = {
		{"Bat","Monkey","Crab","Coyote"},
		{"Parrot","Spider","Hydra","Cougar"},
		{"Worm","Scorpion","Panther","Lion"}
	};
	public static String[][] enemiesW = {
		{"Fangs","Bananas","Dance Moves","Claws"},
		{"Jokes","Webs","Arguements","Powerfull Pounce With Sharp Claws"},
		{"Wiggle","Sting","Misterious Pink Power","Roar"}
	};
	public static int cX = 0;
	public static int cY = 0;
	public static int pcX = 0;
	public static int pcY = 0;
	public static void generateMap(){
		int level = com.swingy.app.CurrentHero.Level;
		map = new Integer[(level-1)*5+10-(level%2)][(level-1)*5+10-(level%2)];
		int mapWidth = (level-1)*5+10-(level%2);
		File tmpDir = new File(System.getProperty("user.dir")+"/target/classes/data/"+com.swingy.app.CurrentHero.Name+".map");
		if(tmpDir.exists() && !com.swingy.app.levelFinish){
			List<String> lines = com.file.read.readF(com.swingy.app.CurrentHero.Name+".map");
			if (lines.size() > 0){
				loadMap();
				return;
			}
		}
		for (int i = 0; i < mapWidth; i++) {
			for (int j = 0; j < mapWidth; j++) {
				Random r = new Random();
				int low = 0;
				int high = 100;
				int result = r.nextInt(high-low) + low;
				if (i == (((mapWidth+1)/2)-1)-cY && j == (((mapWidth+1)/2)-1)+cX){
					map[i][j] = 2;
				} else if (result < com.globals.variables.VSpawn && result >= 0){
					Random r2 = new Random();
					int low2 = 0;
					int high2 = 100;
					int result2 = r2.nextInt(high2-low2) + low2;
					if (level >= enemies.length){
						if (result2 < com.globals.variables.easySpawn && result2 >= 0){
							map[i][j] = 5;
						} else if (result2 < com.globals.variables.mediumSpawn && result2 >= com.globals.variables.easySpawn){
							map[i][j] = 6;
						} else if (result2 < com.globals.variables.hardSpawn && result2 >= com.globals.variables.mediumSpawn){
							map[i][j] = 7;
						} else if (result2 < com.globals.variables.extremeSpawn && result2 >= com.globals.variables.hardSpawn){
							map[i][j] = 8;
						}
					} else {
						if (result2 < com.globals.variables.easySpawn && result2 >= 0){
							map[i][j] = 5;
						} else if (result2 < com.globals.variables.mediumSpawn && result2 >= com.globals.variables.easySpawn){
							map[i][j] = 6;
						} else if (result2 < com.globals.variables.hardSpawn && result2 >= com.globals.variables.mediumSpawn){
							map[i][j] = 7;
						} else if (result2 < com.globals.variables.extremeSpawn && result2 >= com.globals.variables.hardSpawn){
							map[i][j] = 8;
						}
					}
				} else {
					map[i][j] = 0;
				}
			}
		}
	}
	public static void printMap() {
		int level = com.swingy.app.CurrentHero.Level;
		int mapWidth = (level-1)*5+10-(level%2);
		File tmpDir = new File(System.getProperty("user.dir")+"/target/classes/data/swingy.config");
		if(tmpDir.exists() && !com.swingy.app.levelFinish){
			List<String> lines = com.file.read.readF("swingy.config");
			if (lines.get(0).split(":")[1].strip().equals("t")){
				com.globals.variables.ShowEnemies = true;
			}	else {
				com.globals.variables.ShowEnemies = false;
			}
		}
		System.out.printf("__");
		for (int i = 0; i < mapWidth; i++) {
			System.out.printf("__");
		}
		System.out.printf("_%n");
		for (int i = 0; i < mapWidth; i++) {
			System.out.printf("| ");
			for (int j = 0; j < mapWidth; j++) {
				if (map[i][j] == 0){
					System.out.printf("%s",prefixes.Swingy_M_G);
				} else if (map[i][j] == 3){
					System.out.printf("%s",prefixes.Swingy_M_P);
				} else if (map[i][j] >= 5 && map[i][j] <= 8){
					if (com.globals.variables.ShowEnemies){
						if (map[i][j] == 5){
							System.out.printf("%s",prefixes.Swingy_M_E_1);
						} else if (map[i][j] == 6){
							System.out.printf("%s",prefixes.Swingy_M_E_2);
						} else if (map[i][j] == 7){
							System.out.printf("%s",prefixes.Swingy_M_E_3);
						} else if (map[i][j] == 8){
							System.out.printf("%s",prefixes.Swingy_M_E_4);
						}

					} else {
						System.out.printf("%s",prefixes.Swingy_M_G);
					}
				} else if (map[i][j] == 2) {
					System.out.printf("%s",prefixes.Swingy_M_C);
				}
			}
			System.out.printf("%s%n","|");
		}
		System.out.printf("**");
		for (int i = 0; i < mapWidth; i++) {
			System.out.printf("**");
		}
		System.out.printf("*%n");
	}
	public static void moveChar() {
		int level = com.swingy.app.CurrentHero.Level;
		int mapWidth = (level-1)*5+10-(level%2);
		if ((((mapWidth+1)/2)-1)-cY < 0 || (((mapWidth+1)/2)-1)-cY > mapWidth-1 || (((mapWidth+1)/2)-1)-cX < 0 || (((mapWidth+1)/2)-1)-cX > mapWidth-1){
			com.swingy.app.levelFinish = true;
			return;
		}
		if (map[(((mapWidth+1)/2)-1)-cY][(((mapWidth+1)/2)-1)+cX] >= 5 && map[(((mapWidth+1)/2)-1)-cY][(((mapWidth+1)/2)-1)+cX] <= 8) {
			com.swingy.app.battleTime();
			return;
		}
		map[(((mapWidth+1)/2)-1)-pcY][(((mapWidth+1)/2)-1)+pcX] = 3;
		map[(((mapWidth+1)/2)-1)-cY][(((mapWidth+1)/2)-1)+cX] = 2;
	}
	public static void resetChar() {
		cX = 0;
		cY = 0;
		pcX = 0;
		pcY = 0;
	}
	public static void saveMap() {
		int level = com.swingy.app.CurrentHero.Level;
		int mapWidth = (level-1)*5+10-(level%2);
		com.file.create.createFile(com.swingy.app.CurrentHero.Name+".map");
		for (int i = 0; i < mapWidth; i++) {
			String line = "";
			for (int j = 0; j < mapWidth; j++) {
				if (j < mapWidth-1){
					line += Integer.toString(map[i][j])+"#";
				}else{
					line += Integer.toString(map[i][j]);
				}
			}
			com.file.write.writeL(com.swingy.app.CurrentHero.Name+".map", line);
		}
	}
	public static void loadMap() {
		System.out.printf(prefixes.Swingy_B+"Map Already Existed%n");
		System.out.printf(prefixes.Swingy_B+"Loading Map...%n");
		int level = com.swingy.app.CurrentHero.Level;
		map = new Integer[(level-1)*5+10-(level%2)][(level-1)*5+10-(level%2)];
		int mapWidth = (level-1)*5+10-(level%2);
		List<String> lines = com.file.read.readF(com.swingy.app.CurrentHero.Name+".map");
		int i = 0;
		int j = 0;
		for (String line : lines){
			String[] coords = line.split("#");
			j = 0;
			for (String coord : coords){
				map[i][j] = Integer.parseInt(coord);
				if (map[i][j] == 2){
					cY = (((mapWidth+1)/2)-1)-i;
					cX = -1*((((mapWidth+1)/2)-1)-j);
					System.out.print(cY+"\n");
					System.out.print(cX+"\n");
				}
				j++;
			}
			i++;
		}
	}
}