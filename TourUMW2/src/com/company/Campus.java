package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.*;

/**
 * This class represents a Campus object. Each object contains data about a campus.
 *
 * @author Tessa Lanzafame
 * @version 1.0
 */
public class Campus {

    private String campusName;
    private HashMap<String, Location> locations;
    private Location startingLocation;
    private String filename;
    private Location currentLocInTour;
    private HashMap<String, Item> commandItems;
    private ArrayList<Quiz> listOfQuizzes;


    /**
     * Non-default constructor for objects of class Campus.
     *
     * @param filename String referring to filename
     */
    public Campus(String filename) {
    	listOfQuizzes = new ArrayList<Quiz>();
        commandItems = new HashMap<String, Item>();
        this.filename = filename;
        locations = new HashMap<String, Location>();

        try {
            File myFile = new File(filename);
            Scanner myReader = new Scanner(myFile);

             while (myReader.hasNextLine()) {

                String line = myReader.nextLine();
                campusName = line;

                String line2 = myReader.nextLine();

                if (line2.equals("*****")) {

                    myReader.nextLine();
                    String line3 = myReader.nextLine();

                    int count = 0;

                    while (!line3.equals("*****")) {

                        Location newLocation = new Location(myReader, line3);

                        if (count == 0) {
                            startingLocation = newLocation;
                            newLocation.setHaveVisited(true);
                            count++;
                        }

                        addLocation(newLocation);
                        line3 = myReader.nextLine();
                    }

                    String line4 = myReader.nextLine();

                    while (!line4.equals("*****")) {

                        String line5 = myReader.nextLine();

                        if (!line5.equals("*****")) {
                            String line6 = myReader.nextLine();
                            String line7 = myReader.nextLine();
                            
                            Door newDoor = new Door(line6, getLocation(line5), getLocation(line7));
                          
                            addDoorsToLocation(newDoor);

                            line4 = myReader.nextLine();

                        } else {
                            break;
                        }
                    }
                    
                    
                    while(myReader.hasNextLine()){// creates and adds the doors to the arraylist
                        
                        Item item = new Item(myReader);
                        if(item.getName() == null){
                            break;
                        }
                        if(item.getStartLocation().equals("none")){
                            commandItems.put(item.getName(),item);
                        }
                        
                        addItemsToLocation(item);
                        
                    }
                   
                    myReader.nextLine();
                    myReader.nextLine();
                    
                   while(myReader.hasNextLine()) { //reads in quizzes
                	   Quiz newQuiz = new Quiz(myReader, this);
                	   
                	   addQuiz(newQuiz);
                	   
                	   if (newQuiz.getQuizLocation() == null) {
                		   removeQuiz(newQuiz);
                		   break;
                	   }
                   }
                   
                   String random = myReader.nextLine();
                  
                   
                   while(myReader.hasNextLine()) {
                	   Person person = new Person(myReader, this);
                	   if (person.getLocation() == null) {
                		   break;
                	   }
                   }
                   
                   break;
                   
                    
                } else {
                    System.out.println();
                    System.out.println("The file is not the right format.");
                    break;
                }
            }

            myReader.close();

        } catch (FileNotFoundException e) {
            System.out.println();
            System.out.println("The file wasn't found.");
            e.printStackTrace();
        }
    }

    /**
     * Non-default constructor for objects of class Campus.
     *
     * @param entry Location referring to startingLocation
     * @param name  String referring to campusName
     */
    public Campus(Location entry, String name) {
        startingLocation = entry;
        campusName = name;
    }
    
    /**
     * Method that continues reading in a save file and changes the campus accordingly.
     *
     * @param myReader scanner that reads the file
     */
    public void readAndUpdateCampus(Scanner myReader) {

        while (myReader.hasNextLine()) {

            String l3 = myReader.nextLine();

            if (l3.equals("*****")) {

                myReader.nextLine();
                String l4 = myReader.nextLine();

                while (!l4.equals("*****")) {

                    Location location = getLocation(l4);

                    String l = myReader.nextLine();

                    while (!l.equals("+++")) {

                        String[] locationDetails = l.split(":");

                        if (locationDetails[0].equals("visited")) {
                            if (locationDetails[1].equals("true")) {
                                location.setHaveVisited(true);
                            } else {
                                location.setHaveVisited(false);
                            }

                        } else if (locationDetails[0].equals("items")) {
                            String locationItems = locationDetails[1];
                            String[] itemsInLocation = locationItems.split(",");

                            for (int i = 0; i < itemsInLocation.length; i++) {
                                for (Map.Entry<String, Location> set : locations.entrySet()) {
                                    set.getValue().rearrangeItems(itemsInLocation[i], getLocation(l4));

                                }
                            }
                        }

                        l = myReader.nextLine();
                    }
                    l4 = myReader.nextLine();
                }

                myReader.nextLine();

                String l6 = myReader.nextLine();

                String[] currentTourLocation = l6.split(":");
                String locationName = currentTourLocation[1];
                Location loc = getLocation(locationName);
                currentLocInTour = loc;

                setCurrentLocInTour(loc);

                String l7 = myReader.nextLine();

                String bpItems = l7.substring(9);

                String[] bpItemList = bpItems.split(",");

                for (String s : bpItemList) {
                    for (Map.Entry<String, Location> set : locations.entrySet()) {
                        set.getValue().rearrangeItemsForBackpack(s);
                    }
                }

                deleteItemsNotInSaveData();


                myReader.nextLine();

                myReader.nextLine();

                String l9 = myReader.nextLine();

                while (!l9.equals("*****")) {

                    String[] quizInfo = l9.split(":");

                    String quizAnswer = quizInfo[1];

                    Quiz quiz = getQuiz(quizAnswer);

                    String l10 = myReader.nextLine();

                    while (!l10.equals("+++")) {

                        String[] quizStatus = l10.split(":");
                        String isCompleted = quizStatus[1];

                        if (isCompleted.equals("true")) {
                            quiz.setSolved(true);
                        } else {
                            quiz.setSolved(false);
                        }


                        l10 = myReader.nextLine();
                    }

                    l9 = myReader.nextLine();

                }

            } else {
                System.out.println();
                System.out.println("The file is not the right format.");
            }

        }

    }


    /**
     * Method that loops through the campus' locations and deletes any items that aren't in the save file.
     */
    public void deleteItemsNotInSaveData() {

        ArrayList<Item> toRemove = new ArrayList<Item>();

        for (Map.Entry<String, Location> set : locations.entrySet()) {
            for (Item x : set.getValue().getLocationsItemsArray()) {
                if (x.getInSaveData() == false) {
                    toRemove.add(x);
                }
            }

            set.getValue().getLocationsItemsArray().removeAll(toRemove);
        }

    }


    /**
     * Method that returns a String containing updated data about a Location.
     *
     * @return String containing data about the status of a Location
     */
    public String locationDataForSaveFile() {

        String data = "";

        int numLocations = locations.size();

        int countOfLocations = 0;

        for (Map.Entry<String, Location> set : locations.entrySet()) {

            data = data + set.getValue().getName() + "\n" +
                    "visited:" + set.getValue().getHaveVisited() + "\n";

            if (!set.getValue().getLocationsItemsArray().isEmpty()) {
                data = data + "items:" + set.getValue().getListOfItems() + "\n";
            }

            data = data + "+++";


            if (countOfLocations < numLocations) {
                data = data + "\n";

            }
            countOfLocations++;
        }

        return data;
    }


    /**
     * Method that returns a String containing updated data about a Quiz.
     *
     * @return String containing data about if a quiz has been solved
     */
    public String quizDataForSaveFile() {
        String data = "";

        int countOfQuizzes = 0;

        for (Quiz q : listOfQuizzes) {

            data = data + q.getQuizItem().getName() + " quiz answer:" + q.getAnswer() + "\n" +
                    "completed:" + q.getSolved() + "\n" +
                    "+++";

            if (countOfQuizzes < listOfQuizzes.size()) {
                data = data + "\n";
            }
            countOfQuizzes++;
        }
        return data;

    }


    /**
     * Method that adds Locations to the locations HashMap.
     *
     * @param location Location to be added to the HashMap
     */
    public void addLocation(Location location) {
        locations.put(location.getName(), location);
    }

    /**
     * Accessor method for the locations field-accesses a Location from the HashMap.
     *
     * @param name String referring to the name of the Location to be accessed
     * @return the value of the Location
     */
    public Location getLocation(String name) {
        return locations.get(name);
    }
    public void addCommand(Item item){
        commandItems.put(item.getName(),item);
    }

    public Item getCommand(String name) {
        return commandItems.get(name);
    }
    public void removeCommand(String key){
        commandItems.remove(key);
    }

    /**
     * Method that adds Doors to a Location.
     *
     * @param door Door to be added to a Location
     */
    public void addDoorsToLocation(Door door) {
        locations.forEach((k, v) -> v.addDoor(door));
    }

    /**
     * Method that adds Items to a Location.
     *
     * @param i Item to be added to a Location
     */
    public void addItemsToLocation(Item i) {
        locations.forEach((k, v) -> v.addItem(i));
    }

    /**
     * Method that removes a Location from the locations HashMap.
     *
     * @param name String referring to name of the Location to be removed
     */
    public void removeLocation(String name) {
        locations.remove(name);
    }

    /**
     * Mutator method for the campusName field.
     *
     * @param n String with the new value for campusName
     */
    public void setCampusName(String n) {
        campusName = n;
    }

    /**
     * Accessor method for the campusName field.
     *
     * @return the value of the campusName field
     */
    public String getCampusName() {
        return campusName;
    }

    /**
     * Mutator method for the startingLocation field.
     *
     * @param start Location with the new value for startingLocation
     */
    public void setStartLocation(Location start) {
        startingLocation = start;
    }

    /**
     * Accessor method for the startingLocation field.
     *
     * @return the value of the startingLocation field
     */
    public Location getStartLocation() {
        return startingLocation;
    }

    /**
     * Mutator method for the filename field.
     *
     * @param f String with the new value for filename
     */
    public void setFilename(String f) {
        filename = f;
    }

    /**
     * Accessor method for the filename field.
     *
     * @return the value of the filename field
     */
    public String getFilename() {
        return filename;
    }
    

    /**
     * Mutator method for the currentLocInTour field.
     *
     * @param l Location with the new value for currentLocInTour
     */
    public void setCurrentLocInTour(Location l) {
        currentLocInTour = l;
    }

    /**
     * Accessor method for the currentLocInTour field.
     *
     * @return the value of the currentLocInTour field
     */
    public Location getCurrentLocInTour() {
        return currentLocInTour;
    }


    /**
     * Accessor method for the listOfQuizzes field.
     *
     * @return the value of the listOfQuizzes field
     */
    public ArrayList<Quiz> getListOfQuizzes() {
        return listOfQuizzes;
    }


    /**
     * Method that adds a quiz to the ArrayList listOfQuizzes.
     *
     * @param quiz the quiz to added to the ArrayList
     */
    public void addQuiz(Quiz quiz) {
        listOfQuizzes.add(quiz);
    }


    /**
     * Method that returns a Quiz with a matching answer to the one provided.
     *
     * @param quizAnswer the answer to the quiz
     * @return Quiz with a matching answer to the one provided
     */
    public Quiz getQuiz(String quizAnswer) {
        for (Quiz q : listOfQuizzes) {
            if (quizAnswer.equals(q.getAnswer())) {
                return q;
            }
        }
        return null;
    }


    /**
     * Method that removes a Quiz from the ArrayList listOfQuizzes.
     *
     * @param quiz the Quiz to be removed
     */
    public void removeQuiz(Quiz quiz) {

        ArrayList<Quiz> toRemove = new ArrayList<Quiz>();

        for (Quiz q : listOfQuizzes) {
            if (q.getQuizItem() == null) {
                toRemove.add(q);

            }
        }
        listOfQuizzes.removeAll(toRemove);
    }

}
