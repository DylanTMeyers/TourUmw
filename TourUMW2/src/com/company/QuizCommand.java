package com.company;

/**
* This class handles what should happen when a user tries to take a quiz.
* 
* @author Justin Daniels
* @version 1.0
*/
public class QuizCommand implements UserInputCommand{
	private String itemName;
	
	/**
	* This constructor sets the value of the itemName field.
	*/
	public QuizCommand(String itemName) {
		this.itemName = itemName;
	}
	
	/**
	* This method handles what should happen when a user tries to take a quiz.
	*
	* @return a String message
	*/
	@Override
	public String carryOut() {
		boolean hasItem = false;
		boolean answerCorrect = false;
		int itemLocation = -1;
		
		for (int i = 0; i < TourStatus.getInstance().getBackpack().size(); i++) {
			if (TourStatus.getInstance().getBackpack().get(i).getName().equals(itemName)) {
				hasItem = true;
				itemLocation = i;
			}
		}
		
		if (hasItem && !(itemLocation < 0) && TourStatus.getInstance().getBackpack().get(itemLocation).getItemQuiz().getSolved()) {
			return "You have already passed this quiz!";
		}
		
		if (hasItem && !(itemLocation < 0)) {
			answerCorrect = TourStatus.getInstance().getBackpack().get(itemLocation).getItemQuiz().quizStudent();
		}
		
		if (answerCorrect) {
			Distance.getInstance().setDistance(Distance.getInstance().getDistance() + 1);
			return "Yay! You passed the quiz! +1 point";
		}
		
		return "Sorry, type '" + itemName + " quiz' to try again.";
	}

}
