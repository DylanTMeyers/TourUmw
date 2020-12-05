package com.company;

import java.util.Scanner;

public class Person {
	
	private String name;
	private String choiceOne;
	private String choiceTwo;
	private String youTextOne;
	private String youTextTwo;
	private String personTextOne;
	private String personTextTwo;
	private String welcomeText;
	private String goodbyeMessage;
	private Location location;
	
	public Person(Scanner s, Campus c) {
		this.personTextOne = "";
		this.personTextTwo = "";
		
		this.location = c.getLocation(s.nextLine());
		
		if (location != null) {
			
			this.name = s.nextLine();
			this.welcomeText = s.nextLine();
			this.choiceOne = s.nextLine();
			this.youTextOne = s.nextLine();
			
			String line = s.nextLine();
			
			while (line.charAt(0) != 'C') {
				this.personTextOne = personTextOne + line + "\n";
				line = s.nextLine();
			}
			
			this.choiceTwo = line;
			this.youTextTwo = s.nextLine();
			line = s.nextLine();
			while (!(line.equals("---"))) {
				this.personTextTwo = personTextTwo + line + "\n";
				line = s.nextLine();
			}
			
			this.goodbyeMessage = s.nextLine();
			s.nextLine();
			
			location.setPerson(this);
		}
		
	}
	
	public String talk() {
		Scanner s = new Scanner(System.in);
		String option = "";
		
		System.out.println(welcomeText);
		
		while (!(option.equals("bye"))) {
			System.out.println("Dialogue Options: \n     " + choiceOne + "\n     " + choiceTwo + "\n     Type 'bye' to end dialogue.");
			System.out.println("Which option do you choose? ");
			
			option = s.nextLine();
			System.out.println();
			
			if (option.toLowerCase().equals("choice 1") || option.equals("1")) {
				System.out.println("You: " + youTextOne + "\n" + name + ": " + personTextOne);
			}
			else if (option.toLowerCase().equals("choice 2") || option.equals("2")) {
				System.out.println("You: " + youTextTwo + "\n" + name + ": " + personTextTwo);
			}
			else if (!(option.equals("bye"))) {
				System.out.println("Not a valid dialogue option");
			}
		}
		
		return goodbyeMessage;
		
	}
	public String getName() {
		return name;
	}
	public Location getLocation() {
		return location;
	}

	public static void main(String[] args) {
		C:\Users\justi\git\TourUmw\group_umw_campus1.txt
	}
}
