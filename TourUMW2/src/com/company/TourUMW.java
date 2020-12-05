package com.company;
import java.io.File;
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

        System.out.println("Do you have tour save data you wish to load? Type 'r or 'restore' to load a file," +
                "or press any other key to start a new tour.");
        String selectData = stdin.next();
        stdin.nextLine();
        System.out.println();

            if ((selectData.equals("r")) || (selectData.equals("restore"))) {

                System.out.println("What is the name of your save file?");
                String userFile = stdin.nextLine();
                userFile = userFile + ".txt";

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

                if (tour.getCurrentLocation().getPerson() != null) {
                    System.out.println(tour.getCurrentLocation().getPerson().getName() + "\n" +
                            "     Type 'talk' to talk to " + tour.getCurrentLocation().getPerson().getName() + "\n");
                }


                UserInputCommand command = promptUser(stdin);

                while (command != null) {

                    System.out.println(command.carryOut());

                    if (tour.getCurrentLocation().getPerson() != null) {
                        System.out.println(tour.getCurrentLocation().getPerson().getName() + "\n" +
                                "     Type 'talk' to talk to " + tour.getCurrentLocation().getPerson().getName() + "\n");
                    }

                    command = promptUser(stdin);
                }

            } else {   //group_umw_campus1.txt

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

                if (tour.getCurrentLocation().getPerson() != null) {
                    System.out.println(tour.getCurrentLocation().getPerson().getName() + "\n" +
                            "     Type 'talk' to talk to " + tour.getCurrentLocation().getPerson().getName() + "\n");
                }

                UserInputCommand command = promptUser(stdin);

                while (command != null) {

                    System.out.println(command.carryOut());

                    if (tour.getCurrentLocation().getPerson() != null) {
                        System.out.println(tour.getCurrentLocation().getPerson().getName() + "\n" +
                                "     Type 'talk' to talk to " + tour.getCurrentLocation().getPerson().getName() + "\n");
                    }

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

        } else if ((inputArray[0].equals("talk") || inputArray[0].equals("t")) && tour.getCurrentLocation().getPerson() != null) {
        	return new TalkCommand(tour.getCurrentLocation().getPerson());
        }
        else if (backpackOpen == true && inputArray[inputArray.length - 1].equals("quiz") && tour.getBackpackItem(itemFirst) != null && tour.getBackpackItem(itemFirst).getItemQuiz() != null) {
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

        } else if (userInput.equals("save")) {

            System.out.println("Enter the name of the file that will be used to save your tour data:");
            String saveFileName = input.nextLine();
            saveFileName = saveFileName + ".txt";

            System.out.println();

            return new SaveDataCommand(saveFileName);

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
    
    /**
     * Method that reads in a save file and uses that data to set up a campus object.
     *
     * @param saveFile the name of that save file that will be used
     * @return created Campus object
     */
    public static Campus updateCampus(String saveFile) {

        Campus c = null;

        try {
            File file = new File(saveFile);
            Scanner myReader = new Scanner(file);

            while (myReader.hasNextLine()) {

                myReader.nextLine();
                String l2 = myReader.nextLine();

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
