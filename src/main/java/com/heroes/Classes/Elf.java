package com.heroes.Classes;

import javax.validation.constraints.*;
/**
 * elf
 */
public class Elf {
	@Min(value = 0, message = "Class stat cannot be below 0.")
	public int Attack = 20;
	@Min(value = 0, message = "Class stat cannot be below 0.")
	public int Defense = 10;
	@Min(value = 0, message = "Class stat cannot be below 0.")
	public int HitPoints = 10;
	@NotBlank(message = "Class Description cannot be blank.")
	public String Description = "I Am An Elf A"+Attack+" D"+Defense+" HP"+HitPoints;

	
	public String GetDescription() {
		return this.Description;
	}
}