package com.company;
//gee hope this works
//BLAH BLOOP
//Please tell me this works
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * This class is the main class for the Virtual UMW project. It tours a virtual UMW.
 *
 * @author Tessa Lanzafame
 * @version 1.0
 */
public class TourUMW {
	private static boolean backpackOpen;
    public static void main(String[] args) throws FileNotFoundException {

        Scanner stdin = new Scanner(System.in);

        Campus c = setUpCampus(stdin);
        TourStatus tour = TourStatus.getInstance();

        tour.setCampus(c);
        tour.setCurrentLocation(c.getStartLocation());
        tour.getCurrentLocation().setHaveVisited(true);

        System.out.println();
        System.out.println("Current location: " + c.getStartLocation().getName());
        System.out.println(c.getStartLocation().getDescription());
        System.out.println();
        System.out.println(tour.getCurrentLocation().getDoors());
        System.out.println(tour.getCurrentLocation().getItemsInLocation());
        System.out.println();

        UserInputCommand command = promptUser(stdin);


        while (command != null) {

            System.out.println(command.carryOut());

            command = promptUser(stdin);
        }

        stdin.close();
    }


    /**
     * This method prompts the user for input.
     *
     * @param input Scanner that reads in user input
     * @return Object created in response to the user's input
     */
    public static UserInputCommand promptUser(Scanner input) throws FileNotFoundException {
        TourStatus tour = TourStatus.getInstance();

        System.out.println(Distance.getInstance().getStatus());
        System.out.println("Enter a direction, pickup or drop an item, check your backpack or quit:");
        String userInput = input.nextLine();

        //String isItem = input.nextLine();
        
        String[] inputArray = userInput.split(" ");
        
        String isItem = "";
        String itemFirst = "";
        
        if (inputArray.length > 1) {
        	for (int i = 1; i < inputArray.length; i++) {
        		if (i == inputArray.length - 1) {
        			isItem = isItem + inputArray[i];
        		}
        		else {
        			isItem = isItem + inputArray[i] + " ";
        		}
        		
        	}
        	for (int i = inputArray.length - 2; i > -1; i--) {
        		if (i == 0) {
        			itemFirst = inputArray[i] + itemFirst;
        		}
        		else {
        			itemFirst = " " + inputArray[i] + itemFirst;
        		}
        		 
        	}
        }

        System.out.println();
       

        if ((inputArray[0].equals("n")) || (inputArray[0].equals("s")) || (inputArray[0].equals("e")) || (inputArray[0].equals("w"))) {
        	backpackOpen = false;
            return new MovementCommand(userInput);

        } else if ((((inputArray[0].equals("pickup")) || (inputArray[0].equals("p")))) && (isItem != null)) {
        	backpackOpen = false;

            return new PickupCommand(isItem);

        } else if (backpackOpen == true && inputArray[inputArray.length - 1].equals("quiz") && tour.getBackpackItem(itemFirst) != null && tour.getBackpackItem(itemFirst).getItemQuiz() != null) {
        	return new QuizCommand(itemFirst);
        	
        } else if(tour.backpackContains(userInput)){
        	backpackOpen = false;
            return new ItemCommands(userInput);

        } else if ((((inputArray[0].equals("drop")) || (inputArray[0].equals("d")))) && (isItem != null)) {
        	backpackOpen = false;

            return new DropCommand(isItem);

        } else if ((userInput.equals("backpack")) || (userInput.equals("b"))) {
        	backpackOpen = true;
            return new BackpackCommand();

        } else if (userInput.equals("q")) {
            return null;

        } else {
            return new InvalidCommand(userInput);
        }
    }

    /**
     * This method asks for a file and sets up a campus object.
     *
     * @param s Scanner that reads in user input
     * @return created Campus object
     */
    public static Campus setUpCampus(Scanner s) {
        System.out.println("Enter a file:");
        String fileName = s.nextLine();

        Campus campus = new Campus(fileName);

        return campus;
    }

}
