package com.heroes;

import javax.validation.constraints.*;
/**
 * Artifacts
 */
public class Artifacts {
	@NotBlank(message = "Name cannot be blank.")
	public String Name;

	@NotBlank(message = "Type cannot be blank.")
	public String Type;

	@Min(value = 0, message = "Stat cannot be Below 0.")
	public int Stat;

	@NotBlank(message = "Description cannot be blank.")
	public String Description;

}