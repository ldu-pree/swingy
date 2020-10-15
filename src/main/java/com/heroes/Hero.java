package com.heroes;

import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.*;

/**
 * hero
 */
public class Hero {
	
	@NotBlank(message = "Class cannot be blank.")
	public String Name;
	
	@NotNull( message = "Class Has To Exists.")
	public Object Class;
	
	@Min(value = 0, message = "Level Can Not Be Below 0.")
	public int Level;
	
	@Min(value = 0, message = "Experience Can Not Be Below 0.")
	public int Experience;
	
	@NotNull(message = "Artifacts Have To Exist")
	public List<Artifacts> Artifacts = new ArrayList<Artifacts>();
}