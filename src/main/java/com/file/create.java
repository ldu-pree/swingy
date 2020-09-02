package com.file;

import com.swingy.prefixes;
import java.io.File;
import java.io.IOException;

/**
 * create
 */
public class create {
	public static void createFile(String name) {
		try {
			final File myObj = new File(System.getProperty("user.dir")+"/target/classes/data/"+name);
			if (myObj.createNewFile()) {
			} else {
				if (myObj.delete()) { 
					createFile(name);
				  } else {
					System.err.format(prefixes.Swingy_R + name + ".txt already existed and we failed to delete it.%n");
				  }
			}
		} catch (final IOException e) {
			System.err.format(prefixes.Swingy_R + "An error occurred creating " + name + "%n");
			e.printStackTrace();
		}
	}
	public static void buildExecs() {
		try {
			final File myObj = new File(System.getProperty("user.dir")+"/console.bat");
			if (myObj.createNewFile()) {
			} else {
				if (myObj.delete()) { 
					buildExecs();
				  } else {
					System.err.format(prefixes.Swingy_R + "console.bat already existed and we failed to delete it.%n");
				  }
			}
		} catch (final IOException e) {
			System.err.format(prefixes.Swingy_R + "An error occurred creating console.bat%n");
			e.printStackTrace();
		}
		try {
			final File myObj = new File(System.getProperty("user.dir")+"/gui.bat");
			if (myObj.createNewFile()) {
			} else {
				if (myObj.delete()) { 
					buildExecs();
				  } else {
					System.err.format(prefixes.Swingy_R + "gui.bat already existed and we failed to delete it.%n");
				  }
			}
		} catch (final IOException e) {
			System.err.format(prefixes.Swingy_R + "An error occurred creating gui.bat%n");
			e.printStackTrace();
		}
		write.writeExecs();
	}
}