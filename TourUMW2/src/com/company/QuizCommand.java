package com.company;

public class QuizCommand implements UserInputCommand{
	private String itemName;
	
	public QuizCommand(String itemName) {
		this.itemName = itemName;
	}
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
