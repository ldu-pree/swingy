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
		List<String> Heroes = com.file.read.readF("Heroes.txt");
		Hero Hero = new Hero();
		String Name = "";
		while (Name.equals("")){
			Name = promptName(Heroes);
		}
		Hero.Name = Name;
		if (nameExists(Heroes, Name)){

		} else {
			Heroes.add(Hero.Name);
			com.file.write.writeL("Heroes.txt", Hero.Name);
		}
		com.file.create.createFile(Hero.Name);
		printClasses();
		String inputClass = scanner.next();
		if (verInp(inputClass)){
			Hero.Class = inputClass;
			com.file.write.writeL("Heroes.txt", Hero.Name);
		}
		return Heroes;
	}
	private static void printClasses() {
		System.out.printf(prefixes.Swingy_B_C + "Select A Class From The Below List:%n");
			System.out.printf(prefixes.Swingy_B_C_Classes + "Elf: %s%n",Elf.Description);
		System.out.printf(prefixes.Swingy_B_C + "Type The Name Of The Class: ");
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
		return Name;
	}
	private static boolean nameExists(List<String> Heroes, String Name) {
		for (String Hero : Heroes){
			if (Hero.equals(Name)){
				return true;
			}
		}
		return false;
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
			if (CName.equals(class1)){
				return true;
			}
		}
		return false;
	}
}