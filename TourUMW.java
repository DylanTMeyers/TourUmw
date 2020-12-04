package com.company;

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;

/**
 * This class is the main class for the Virtual UMW project. It tours a virtual UMW.
 *
 * @author Tessa Lanzafame
 * @version 1.0
 */
public class TourUMW {

    public static void main(String[] args) throws FileNotFoundException {

        Scanner stdin = new Scanner(System.in);

        System.out.println("Do you have tour save data you wish to load? Type 'r or 'restore' to load a file," +
                "or press any other key to start a new tour.");
        String selectData = stdin.next();
        stdin.nextLine();
        System.out.println();

            if ((selectData.equals("r")) || (selectData.equals("restore"))) {

                System.out.println("What is your save file?");  //sample_save.txt
                String userFile = stdin.nextLine();

                Campus camp = updateCampus(userFile);
                TourStatus tour = TourStatus.getInstance();
                tour.setCampus(camp);
                tour.setCurrentLocation(tour.getCampus().getCurrentLocInTour());

                System.out.println();
                System.out.println("Current location: " + tour.getCurrentLocation().getName());
                System.out.println(tour.getCurrentLocation().getDescription());
                System.out.println();
                System.out.println(tour.getCurrentLocation().getDoors());
                System.out.println(tour.getCurrentLocation().getItemsInLocation());
                System.out.println();

                UserInputCommand command = promptUser(stdin);

                while (command != null) {

                    System.out.println(command.carryOut());
                    command = promptUser(stdin);
                }

//simplify to else?
            } else {   //umw_campus_2.txt

                Campus c = setUpCampus(stdin);  //umw_campus_2.txt

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
            }

        stdin.close();
    }


    /**
     * This method prompts the user for input.
     *
     * @param input Scanner that reads in user input
     * @return Object created in response to the user's input
     */
    public static UserInputCommand promptUser(Scanner input) {

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

        } else if (userInput.equals("save")) {

            System.out.println("Enter the name of the file that will be created to save your tour data:");   //or change to say 'to save'
            String saveFileName = input.nextLine();   //maybe only use next() ???

            System.out.println();

            return new SaveDataCommand(saveFileName);

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
        campus.setFilename(fileName);

        return campus;
    }


    public static Campus updateCampus(String saveFile) {    //for line 'Data file:' --> change to file needed

        Campus c = null;

        try {
            File file = new File(saveFile);
            Scanner myReader = new Scanner(file);

            while (myReader.hasNextLine()) {

                String l = myReader.nextLine(); //SAVE FILE
                String l2 = myReader.nextLine();    //data file name

                String[] dataFileName = l2.split(":");
                String fileName = dataFileName[dataFileName.length - 1];

                c = new Campus(fileName);
                c.setFilename(fileName);

                c.readAndUpdateCampus(myReader);

            }
            myReader.close();

        } catch (FileNotFoundException e) {
            System.out.println();
            System.out.println("The file wasn't found.");
            e.printStackTrace();
        }

        return c;
    }


}
