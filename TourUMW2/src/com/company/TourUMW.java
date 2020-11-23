package com.company;
//gee hope this works
//BLAH BLOOP
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * This class is the main class for the Virtual UMW project. It tours a virtual UMW.
 *
 * @author Tessa Lanzafame
 * @version 1.0
 */
public class TourUMW {

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

        System.out.println("Enter a direction, pickup or drop an item, check your backpack or quit:");
        String userInput = input.next();

        String isItem = input.nextLine();

        System.out.println();

        if ((userInput.equals("n")) || (userInput.equals("s")) || (userInput.equals("e")) || (userInput.equals("w"))) {
            return new MovementCommand(userInput);

        } else if ((((userInput.equals("pickup")) || (userInput.equals("p")))) && (isItem != null)) {

            userInput = isItem.trim();

            return new PickupCommand(userInput);

        } else if ((((userInput.equals("drop")) || (userInput.equals("d")))) && (isItem != null)) {

            userInput = isItem.trim();

            return new DropCommand(userInput);

        } else if ((userInput.equals("backpack")) || (userInput.equals("b"))) {
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
