package com.company;

public class TalkCommand implements UserInputCommand{
	
	private Person person;

	public TalkCommand(Person person) {
		this.person = person;
	}
	
	public String carryOut() {
		return person.talk();
	}

}
