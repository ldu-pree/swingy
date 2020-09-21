package com.heroes;

import java.util.ArrayList;
import java.util.List;
import java.io.InputStream;
import java.util.Scanner;
import com.file.*;
import com.swingy.prefixes;
import com.heroes.Classes.*;


/**
 * create
 */
public class create {
	private static InputStream stream = System.in;
	private static Scanner scanner = new Scanner(stream);
	public static List<String> createHero() {
		List<String> Heroes = com.file.read.readF("Swingy.data");
		Hero Hero = new Hero();
		String Name = "";
		while (Name.equals("")){
			Name = promptName(Heroes);
		}
		Hero.Name = Name;
		com.file.create.createFile(Hero.Name+".hero");
		while(!classesPrompt(Hero)){

		}
		System.out.printf(prefixes.Swingy_B_C_Classes + "Successfully Selected A Class!%n");
		com.file.write.writeL(Hero.Name+".hero", "Level:0");
		com.file.write.writeL(Hero.Name+".hero", "XP:0");
		com.file.write.writeL(Hero.Name+".hero", "Artifacts:Sword=Attack=5=Shiny Silver Attack Booster#Leather Jacket=Defence=2=Heavy Leather Jacket Helps Defence A Bit");
		Heroes.add(Name);
		return Heroes;
	}
	private static void printClasses() {
		System.out.printf(prefixes.Swingy_B_C + "Select A Class From The Below List:%n");
			System.out.printf(prefixes.Swingy_B_C_Classes + "Elf: %s%n",Elf.Description);
			System.out.printf(prefixes.Swingy_B_C_Classes + "Mage: %s%n",Mage.Description);
			System.out.printf(prefixes.Swingy_B_C_Classes + "Paladin: %s%n",Paladin.Description);
			System.out.printf(prefixes.Swingy_B_C_Classes + "Troll: %s%n",Troll.Description);
		System.out.printf(prefixes.Swingy_B_C + "Type The Name Of The Class: ");
	}
	private static boolean classesPrompt(Hero Hero) {
		printClasses();
		String inputClass = scanner.next();
		if (verInp(inputClass)){
			Hero.Class = inputClass;
			com.file.write.writeL(Hero.Name+".hero", "Class:"+inputClass.substring(0, 1).toUpperCase() + inputClass.substring(1));
			return true;
		} else {
			System.out.printf(prefixes.Swingy_R_C_Classes + "Invalid Class Name%n");
			return false;
		}
	}
	private static String promptName(List<String> Heroes) {
		System.out.printf(prefixes.Swingy_B_C + "Type A Name For Your Hero: ");
		String Name = scanner.next();
		for (String Hero : Heroes){
			if (Hero.equals(Name)){
				if (promptOverite()){
					return Name;
				} else {
					return "";
				}
			}
		}
		com.file.write.writeL("Swingy.data", Name);
		return Name;
	}
	private static boolean promptOverite() {
		System.out.printf(prefixes.Swingy_R_C + "A Hero With This name Already Exists Would You Like To Overite It? (Y/N): ");
		String opt = scanner.next();
		if (opt.equals("Y") || opt.equals("y") || opt.equals("Yes") || opt.equals("yes")){
			return true;
		} else if (opt.equals("N") || opt.equals("n") || opt.equals("No") || opt.equals("no")){
			return false;
		}
		return false;
	}
	private static boolean verInp(String CName){
		for (String class1 : com.heroes.Classes.AllClasses.Classes){
			if (CName.equals(class1) || CName.toLowerCase().equals(class1.toLowerCase())){
				return true;
			}
		}
		return false;
	}
}