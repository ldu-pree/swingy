package com.swingy;

import com.file.*;
import com.heroes.*;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.net.URL;
import java.io.InputStream;
import java.util.Scanner;
import javax.swing.*;
import java.awt.*;

public class app {
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";
	private static InputStream stream = System.in;
	private static Scanner scanner = new Scanner(stream);
	public static JFrame frame;

	public static void main(final String[] args) {
		System.out.println(prefixes.Swingy_B + "Welcome To Swingy!!!");
		if (args.length == 0){
			System.err.format(prefixes.Swingy_R + "Please Specify A Mode, Either (console) Or (gui)%n");
		} else if (args.length > 1){
			System.err.format(prefixes.Swingy_R + "Too Many Arguments Please Only Specify 1 Mode At A Time.%n");
		} else {
			if (args[0].equals("Console") || args[0].equals("console")){
				System.out.println(prefixes.Swingy_B + "Swingy Is In Console Mode.");
				startConsole();
			} else if (args[0].equals("gui") || args[0].equals("Gui") || args[0].equals("GUI")){
				System.out.println(prefixes.Swingy_B + "Swingy Is In Gui Mode.");
				startGui();
			}
		}
	}
	
	private static void startConsole() {
		List<String> Heroes = com.heroes.get.getHeroes();
		promptSelectCreate(Heroes);
	}

	private static void startGui() {
		generateFrame();
	}

	private static void promptSelectCreate(List<String> Heroes) {
		System.out.printf(prefixes.Swingy_B + "Would You Like To Select A Existing Hero Or Create A New One? (S/C): ");
		String prompt = scanner.next();
		if (prompt.equals("S") || prompt.equals("s") || prompt.equals("Select") || prompt.equals("select")){
			selectHero(Heroes);
		}if (prompt.equals("C") || prompt.equals("c") || prompt.equals("Create") || prompt.equals("create")){
			com.heroes.create.createHero();
		}
	}

	private static void selectHero(List<String> Heroes) {
		System.out.printf(prefixes.Swingy_B_S + "Please Select A Hero:%n");
		for (String hero : Heroes){
			System.out.printf(prefixes.Swingy_B_S_H + "%s%n", hero);
		}
		System.out.printf(prefixes.Swingy_B_S + "Please Type The Name Of The Hero: ");
		String Name = scanner.next();
	}

	public File getFileFromResources(String fileName) {

        ClassLoader classLoader = getClass().getClassLoader();

        URL resource = classLoader.getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("file is not found!");
        } else {
            return new File(resource.getFile());
        }

	}
	
	private static void generateFrame(){
		//Creating the Frame
        JFrame frame = new JFrame("Chat Frame");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);

        //Creating the MenuBar and adding components
        JMenuBar mb = new JMenuBar();
        JMenu m1 = new JMenu("Hero");
        mb.add(m1);
        JMenuItem m11 = new JMenuItem("Save");
        m1.add(m11);

        //Creating the panel at bottom and adding components
        JPanel panel = new JPanel(); // the panel is not visible in output
        JLabel label = new JLabel("What's Your Action?");
        final JTextField tf = new JTextField(30); // accepts upto 10 characters
        JButton submit = new JButton("Submit");
        panel.add(label); // Components Added using Flow Layout
        panel.add(tf);
        panel.add(submit);

        // Text Area at the Center
        final JTextArea ta = new JTextArea();

		submit.addActionListener(new java.awt.event.ActionListener() {

			@Override
			public void actionPerformed(java.awt.event.ActionEvent e) {
				ta.append(tf.getText()+"\n");
			}
		});

        //Adding Components to the frame.
        frame.getContentPane().add(BorderLayout.SOUTH, panel);
        frame.getContentPane().add(BorderLayout.NORTH, mb);
        frame.getContentPane().add(BorderLayout.CENTER, ta);
        frame.setVisible(true);
	}
}