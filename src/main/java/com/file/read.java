package com.file;

import com.swingy.prefixes;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;


/**
 * read
 */
public class read {
	public static List<String> readF(String file) {
		final List<String> records = new ArrayList<String>();
	  	try
	  	{
	  	  	final BufferedReader reader = new BufferedReader(new FileReader(System.getProperty("user.dir")+"/target/classes/data/"+file));
	  	  	String line;
	  	  	while ((line = reader.readLine()) != null)
	  	  	{
	  	  	  	records.add(line);
	  	  	}
	  	  	reader.close();
	  	  	return records;
	  	}
	  	catch (final Exception e)
	  	{
	  	  	System.err.format(prefixes.Swingy_R + "Error occurred trying to read '%s'.%n", file);
	  	  	e.printStackTrace();
	  	  	return null;
	  	}
	}	
}