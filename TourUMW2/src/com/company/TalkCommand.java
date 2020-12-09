package com.company;

/**
* This class handles what should happen when a user tries to talk to a person on campus.
* 
* @author Justin Daniels
* @version 1.0
*/
public class TalkCommand implements UserInputCommand{
	
	private Person person;

	/**
	* This constructor sets the value of the person field.
	*
	* @param persson the person to talk to
	*/
	public TalkCommand(Person person) {
		this.person = person;
	}
	
	/**
	* This method lets the user talk to a person
	* 
	* @return The goodbye message from the talk method in the Person class
	*/
	public String carryOut() {
		return person.talk();
	}

}
