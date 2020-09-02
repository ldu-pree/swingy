package com.heroes;

import java.util.ArrayList;
import java.util.List;
import java.io.InputStream;
import java.util.Scanner;
import com.file.*;
import com.swingy.prefixes;


/**
 * get
 */
public class get {
	private static InputStream stream = System.in;
	private static Scanner scanner = new Scanner(stream);
	public static List<String> getHeroes() {
		final List<String> Heroes = com.file.read.readF("Swingy.data");
		if (Heroes.size() == 0){
			System.out.printf(prefixes.Swingy_B + "You Don't Have Any Heroes Yet, Lets Create One!%n");
			return create.createHero();
		} else {
			return Heroes;
		}
	}
}