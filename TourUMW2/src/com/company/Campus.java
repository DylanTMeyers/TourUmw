package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

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
    private HashMap<String, Item> commandItems;


    /**
     * Non-default constructor for objects of class Campus.
     *
     * @param filename String referring to filename
     */
    public Campus(String filename) {
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
                            System.out.println("Door:");
                            Door newDoor = new Door(line6, getLocation(line5), getLocation(line7));
                            System.out.println(newDoor+ "\n");

                            addDoorsToLocation(newDoor);

                            line4 = myReader.nextLine();

                        } else {
                            break;
                        }
                    }
                    
                    
                    while(myReader.hasNextLine()){// creates and adds the doors to the arraylist
                        System.out.println("Item");
                        Item item = new Item(myReader);
                        if(item.getName() == null){
                            break;
                        }
                        if(item.getStartLocation().equals("none")){
                            commandItems.put(item.getName(),item);
                        }
                        System.out.println(item);
                        addItemsToLocation(item);
                        
                    }
                   
                    myReader.nextLine();
                    myReader.nextLine();
                    
                   while(myReader.hasNextLine()) { //reads in quizzes
                	   Quiz newQuiz = new Quiz(myReader, this);
                	   if (newQuiz.getQuizLocation() == null) {
                		   break;
                	   }
                   }
                   
                    
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

}
