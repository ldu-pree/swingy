package com.heroes.Classes;

import javax.validation.constraints.*;
/**
 * Troll
 */
public class Troll {
	@Min(value = 0, message = "Class stat cannot be below 0.")
	public int Attack = 10;
	@Min(value = 0, message = "Class stat cannot be below 0.")
	public int Defense = 20;
	@Min(value = 0, message = "Class stat cannot be below 0.")
	public int HitPoints = 10;
	@NotBlank(message = "Class Description cannot be blank.")
	public String Description = "I Am An Troll A"+Attack+" D"+Defense+" HP"+HitPoints;
	
	public String GetDescription() {
		return(this.Description);
	}
}