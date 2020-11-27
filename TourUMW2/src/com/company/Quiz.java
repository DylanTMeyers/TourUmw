package com.company;
/**
 * This class holds the information for the quizes throughout the 
 * tour.
 * 
 * @author Justin Daniels
 * @version 1.0
 */
public class Quiz {
	private String question;
	private String answerChoices;
	private String answer;
	
	public Quiz() {
		
	}
	
	public void setQuestion(String question) {
		this.question = question;
	}
	
	public String getQuestion() {
		return question;
	}
	
	public void setAnswerChoices(String answerChoices) {
		this.answerChoices = answerChoices;
	}
	
	public String getAnswerChoices() {
		return answerChoices;
	}
	
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
	public String getAnswer() {
		return answer;
	}
}
