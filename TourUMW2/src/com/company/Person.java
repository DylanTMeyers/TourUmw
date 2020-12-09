package com.company;

import java.util.Scanner;
/**
* This class holds information for people to interact with throughout the campus.
*
* @author Justin Daniels
* @version 1.0
*/
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
	
	/**
	* This constructor creates a persson object using a Scanner for a file and a Campus.
	*
	* @param s Scanner for the input file
	* @param c Campus object where the people are
	*/
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
	
	/**
	* This method allows the user to talk with the person.
	*
	* @return goodbyeMessage the message the person says when the user is done speaking with them
	*/
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
	
	/**
	* This getter method gets the value of the name variable.
	* 
	* @return name the name of the person
	*/
	public String getName() {
		return name;
	}
	
	/**
	* This getter method get the value of the location variable.
	* 
	* @return location the location where the person can be found
	*/
	public Location getLocation() {
		return location;
	}

}
