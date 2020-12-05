package com.company;

import java.util.Scanner;

/**
 * This class holds the information for the quizzes throughout the 
 * tour.
 * 
 * @author Justin Daniels
 * @version 1.0
 */
public class Quiz {
	private String question;
	private String answerChoices;
	private String answer;
	private Location quizLocation;
	private Item quizItem;
	private boolean solved;
	
	/**
	 * This is the constructor for the Quiz class that takes in a file
	 * to iterate through.
	 * 
	 * @param s Scanner that scans through file
	 * @param c Campus
	 */
	public Quiz(Scanner s, Campus c) {
		this.answerChoices = "";
		this.question = "";
		this.quizLocation = c.getLocation(s.nextLine());
		if (this.quizLocation != null) {
			this.quizItem = quizLocation.getItemNamed(s.nextLine());;
			String line = s.nextLine();
			while (line.charAt(0) != 'a') {
				this.question = question + line + "\n";
				line = s.nextLine();			
			}
			while (line.length() != 1) {
				this.answerChoices = answerChoices + line + "\n";
				line = s.nextLine();
			}
			s.nextLine();
			this.answer = line;
			this.quizItem.setItemQuiz(this);
		}
	}
	
	/**
	 * This setter method sets the value of the question field.
	 * 
	 * @param question the question for the quiz
	 */
	public void setQuestion(String question) {
		this.question = question;
	}
	
	/**
	 * This getter method gets the value of question.
	 * 
	 * @return question the question for the quiz
	 */
	public String getQuestion() {
		return question;
	}
	
	/**
	 * This setter method sets the value of the answerChoices field.
	 * 
	 * @param answerChoices the answer choices for the quiz
	 */
	public void setAnswerChoices(String answerChoices) {
		this.answerChoices = answerChoices;
	}
	
	/**
	 * This getter method gets the value of answerChoices.
	 * 
	 * @return answerChoices the answer choices for the quiz
	 */
	public String getAnswerChoices() {
		return answerChoices;
	}
	
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
	public String getAnswer() {
		return answer;
	}
	
	public void setSolved(boolean solved) {
		this.solved = solved;
	}
	
	public boolean getSolved() {
		return solved;
	}
	
	public void setQuizLocation(Location l) {
		this.quizLocation = l;
	}
	
	public Location getQuizLocation() {
		return this.quizLocation;
	}
	
	public boolean quizStudent() {
		if (this.solved == false) {
			Scanner s = new Scanner(System.in);
			System.out.println(this.question + "\n" + this.answerChoices);
			System.out.print("Type the letter of your answer: ");
			String answer = s.next();
			
			
			if (answer.toLowerCase().equals(this.answer)) {
				this.solved = true;
				return true;
			}
			return false;
		}
		else {
			return true;
		}
		
	}
	
	/**
     * Accessor method for the quizItem field.
     *
     * @return the value of the quizItem field
     */
    public Item getQuizItem() {
        return quizItem;
    }

}
