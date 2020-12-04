package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * This class represents a Campus object. Each object contains data about a campus.
 *
 * @author Tessa Lanzafame
 * @version 1.0
 */
public class Campus {

    private String campusName;
    private HashMap<String, Location> locations;    //key is location name, value is location object
    private Location startingLocation;
    private String filename;

    private Location currentLocInTour;  //for save file

    /**
     * Non-default constructor for objects of class Campus.
     *
     * @param filename String referring to filename
     */
    public Campus(String filename) {

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

                    String line8 = myReader.nextLine();

                    while (!line8.equals("*****")) {

                        String line9 = myReader.nextLine();
                        if (!line9.equals("*****")) {
                            String line10 = myReader.nextLine();
                            String line11 = myReader.nextLine();

                            Item newItem = new Item(line9, line10, line11);
                            newItem.setInSaveData(false);
                            addItemsToLocation(newItem);

                            line8 = myReader.nextLine();

                        } else {
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


   //relies on each thing to be on one line
    public void readAndUpdateCampus(Scanner myReader) {

        while (myReader.hasNextLine()) {


            String l3 = myReader.nextLine();    //*****

            if (l3.equals("*****")) {   //initially checks if file is formatted correctly; section for locations

                myReader.nextLine();    //Location states:
                String l4 = myReader.nextLine();    //the name of a location

                while (!l4.equals("*****")) {

                    Location location = getLocation(l4);

                    String l = myReader.nextLine();

                    while (!l.equals("+++")) {

                        String[] locationDetails = l.split(":");    //list of Strings ->

                        if (locationDetails[0].equals("visited")) {
                            if (locationDetails[1].equals("true")) {
                                location.setHaveVisited(true);
                            } else {
                                location.setHaveVisited(false);
                            }

                                                                    //idk if && part of statement will work (or is needed)
                        } else if ((locationDetails[0].equals("items")) && (!locationDetails[1].equals(""))) {
                            String locationItems = locationDetails[1];
                            String[] itemsInLocation = locationItems.split(",");

                            //iterates through the list of item names for each location in the txt file
                            for (int i = 0; i < itemsInLocation.length; i++) {  //add items listed in save file to location
                                for (Map.Entry<String, Location> set : locations.entrySet()) {
                                    set.getValue().rearrangeItems(itemsInLocation[i], getLocation(l4));  // l4 = name of the location

                                }
                            }
                        }

                        l = myReader.nextLine();
                    }
                    l4 = myReader.nextLine();
                }

                myReader.nextLine();    //Visitor:
                //current status of code:  relies on only two infos about visitor:  their current location and their items

                String l6 = myReader.nextLine();    //current location:

                String[] currentTourLocation = l6.split(":");
                String locationName = currentTourLocation[1];
                Location loc = getLocation(locationName);
                currentLocInTour = loc;

                setCurrentLocInTour(loc);

                //what if there's no items in the backpack; need to test that; nope, think it works fine
                String l7 = myReader.nextLine();    //backpack items

                String bpItems = l7.substring(9);   //file must be "backpack:_________(items); no spaces

                String[] bpItemList = bpItems.split(",");

                //program CASE SENSITIVE --> needs to be changed if you don't want to worry about caps matching up
                for (String s : bpItemList) {   //get item from location, remove it and add to backpack
                    for (Map.Entry<String, Location> set : locations.entrySet()) {
                        set.getValue().rearrangeItemsForBackpack(s);
                    }
                }

                //remove any items not listed in save file (in the locations)   ???do i even need this
                //for loop not needed(?)
                //for (Map.Entry<String, Location> set : locations.entrySet()) {
                    deleteItemsNotInSaveData();
                //}

                String l8 = myReader.nextLine();
                if (l8.equals("*****")) {
                    break;
                }

            } else{
                System.out.println();
                System.out.println("The file is not the right format.");
            }

        }

    }


    //if a location isn't listed, or its items aren't, those items will be deleted
    public void deleteItemsNotInSaveData() {    //locations only, not backpack

        ArrayList<Item> toRemove = new ArrayList<Item>();

        for (Map.Entry<String, Location> set : locations.entrySet()) {  //go thru locations
            for (Item x : set.getValue().getLocationsItemsArray()) {
                if (x.getInSaveData() == false) {
                    toRemove.add(x);
                }
            }

            set.getValue().getLocationsItemsArray().removeAll(toRemove);
        }

    }



    public String locationDataForSaveFile() {

        String data = "";

        int numLocations = locations.size();    //don't even need to save in a variable

        int countOfLocations = 0;

        for (Map.Entry<String, Location> set : locations.entrySet()) {

            if (countOfLocations < numLocations) {

                data = data + set.getValue().getName() + "\n" +
                        "visited:" + set.getValue().getHaveVisited() + "\n";

                        if (!set.getValue().getLocationsItemsArray().isEmpty()) {
                            data = data + "items:" + set.getValue().getListOfItems() + "\n";
                        }

                        data = data + "+++" + "\n";

                countOfLocations++;

            } else {
                data = data + set.getValue().getName() + "\n" +
                        "visited:" + set.getValue().getHaveVisited() + "\n";

                if (!set.getValue().getLocationsItemsArray().isEmpty()) {
                    data = data + "items:" + set.getValue().getListOfItems() + "\n";
                }

                data = data + "+++";
            }
        }

        return data;
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


    public void setCurrentLocInTour(Location l) {
        currentLocInTour = l;
    }

    public Location getCurrentLocInTour() {
        return currentLocInTour;
    }

}
