package com.file;

import com.swingy.prefixes;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * write
 */
public class write {
	public static void writeF(String file, String line) {
		try {
			final FileWriter myWriter = new FileWriter(System.getProperty("user.dir")+"/target/classes/data/"+file);
			myWriter.write(line);
			myWriter.close();
		} catch (IOException e) {
			System.err.format(prefixes.Swingy_R + "An error occurred writing to " + file);
			e.printStackTrace();
		}
	}
	public static void writeL(String file, String line) {
		try {
			List<String> lines = com.file.read.readF(file);
			lines.add(line);
			final FileWriter myWriter = new FileWriter(System.getProperty("user.dir")+"/target/classes/data/"+file);
			for (String newL : lines){
				myWriter.write(newL+"\n");
			}
			myWriter.close();
		} catch (IOException e) {
			System.err.format(prefixes.Swingy_R + "An error occurred writing to " + file);
			e.printStackTrace();
		}
	}
	public static void writeExecs() {
		try {
			final FileWriter myWriter = new FileWriter(System.getProperty("user.dir")+"/console.bat");
			myWriter.write("java -jar target/swingy-1.jar console\n");
			myWriter.close();
		} catch (IOException e) {
			System.err.format(prefixes.Swingy_R + "An error occurred writing to console.bat");
			e.printStackTrace();
		}
		try {
			final FileWriter myWriter = new FileWriter(System.getProperty("user.dir")+"/gui.bat");
			myWriter.write("java -jar target/swingy-1.jar gui\n");
			myWriter.close();
		} catch (IOException e) {
			System.err.format(prefixes.Swingy_R + "An error occurred writing to gui.bat");
			e.printStackTrace();
		}
	}
}