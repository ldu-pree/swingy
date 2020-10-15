package com.heroes.Classes;

import javax.validation.constraints.*;
/**
 * Paladin
 */
public class Paladin {
	@Min(value = 0, message = "Class stat cannot be below 0.")
	public int Attack = 10;
	@Min(value = 0, message = "Class stat cannot be below 0.")
	public int Defense = 10;
	@Min(value = 0, message = "Class stat cannot be below 0.")
	public int HitPoints = 20;
	@NotBlank(message = "Class Description cannot be blank.")
	public String Description = "I Am An Paladin A"+Attack+" D"+Defense+" HP"+HitPoints;
	
	public String GetDescription() {
		return(this.Description);
	}
}