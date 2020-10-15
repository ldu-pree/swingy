package com.heroes.Classes;

import javax.validation.constraints.*;
/**
 * Mage
 */
public class Mage {
	@Min(value = 0, message = "Class stat cannot be below 0.")
	public int Attack = 15;
	@Min(value = 0, message = "Class stat cannot be below 0.")
	public int Defense = 15;
	@Min(value = 0, message = "Class stat cannot be below 0.")
	public int HitPoints = 10;
	@NotBlank(message = "Class Description cannot be blank.")
	public String Description = "I Am An Mage A"+Attack+" D"+Defense+" HP"+HitPoints;
	
	public String GetDescription() {
		return(this.Description);
	}
}