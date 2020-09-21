package com.IO;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.net.URL;
import java.io.InputStream;
import java.util.Scanner;
import javax.swing.*;
import java.awt.*;
import javax.validation.*;
import javax.validation.constraints.*;
import com.globals.*;
/**
 * input
 */
public class input {
	private static InputStream stream = System.in;
	private static Scanner scanner = new Scanner(stream);
	
	public static String scanInput() {
		if(com.globals.variables.operationMode == "Console"){
			return scanner.next();
		}else if(com.globals.variables.operationMode == "Gui"){
			return com.globals.variables.guiTextField.getText();
		}else{
			return "Error";
		}
	}
}