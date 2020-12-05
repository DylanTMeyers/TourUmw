package com.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * This class represents a Location object. Each object contains data about a Location.
 *
 * @author Tessa Lanzafame
 * @version 1.0
 */
public class Location {

    private String name;
    private String description;
    private boolean haveVisited;
    private boolean isOutside;
    private ArrayList<Door> doors;
    private ArrayList<Item> items;
    private Person person;


    /**
     * Default constructor for objects of class Location.
     */
    public Location() {
        haveVisited = false;
        doors = new ArrayList<Door>();
        items = new ArrayList<Item>();
    }

    /**
     * Non-default constructor for objects of class Location.
     *
     * @param s Scanner that reads in a file and assigns values to fields
     */
    public Location(Scanner s) {

        String line = s.nextLine();
        name = line;

        if (!line.equals("*****")) {

            description = "";

            int count = 0;

            while ((s.hasNextLine())) {

                String line2 = s.nextLine();

                if ((!line2.equals("+++")) && (count == 0)) {
                    description = description + line2;
                    count++;
                } else if ((!line2.equals("+++")) && (count != 0)) {
                    description = description + " " + line2;
                } else {
                    break;
                }
            }
            haveVisited = false;
            doors = new ArrayList<Door>();
            items = new ArrayList<Item>();
        }
    }

    /**
     * Non-default constructor for objects of class Location.
     *
     * @param s Scanner that reads in a file and assigns values to fields
     * @param n String referring to name
     */
    public Location(Scanner s, String n) {

        name = n;

        description = "";

        int count = 0;

        while ((s.hasNextLine())) {

            String line2 = s.nextLine();

            if ((!line2.equals("+++")) && (count == 0)) {
                description = description + line2;
                count++;
            } else if ((!line2.equals("+++")) && (count != 0)) {
                description = description + " " + line2;
            } else {
                break;
            }
        }
        haveVisited = false;
        doors = new ArrayList<Door>();
        items = new ArrayList<Item>();
    }
    
    /**
     * Method that moves Items to different Locations according to a save file.
     *
     * @param nameOfItemInQuestion the Item to be moved
     * @param locInFile the Location in the save file that is currently updating the status of
     */
    public void rearrangeItems(String nameOfItemInQuestion, Location locInFile) {

        ArrayList<Item> toMove = new ArrayList<Item>();

        for (Item i : items) {

            if (((nameOfItemInQuestion.equals(i.getName()))) && (!locInFile.getName().equals(name))) {
                i.setInSaveData(true);
                toMove.add(i);

            } else if (((nameOfItemInQuestion.equals(i.getName()))) && (locInFile.getName().equals(name))) {
                i.setInSaveData(true);
            }

        }

        items.removeAll(toMove);

        for (Item x : toMove) {
            locInFile.addMovedItem(x);
        }

    }


    /**
     * Method that moves Items to the user's backpack from different Locations according to a save file.
     *
     * @param nameOfItemInQuestion the Item to be moved
     */
    public void rearrangeItemsForBackpack(String nameOfItemInQuestion) {

        TourStatus tour = TourStatus.getInstance();

        ArrayList<Item> toMove = new ArrayList<Item>();

        for (Item x : items) {
            if (nameOfItemInQuestion.equals(x.getName())) {
                toMove.add(x);
            }
        }

        items.removeAll(toMove);

        for (Item y : toMove) {
            tour.addToBackpack(y);
        }

    }
    
    /**
     * Method that adds an Item to the items ArrayList for a Location.
     *
     * @param item Item that is to be added to the ArrayList
     */
    public void addMovedItem(Item item) { //for rearranging items - save file
        items.add(item);
    }
    
    /**
     * Method that lists all Items in a Location, separated by commas.
     *
     * @return a String listing all Items in a Location, separated by commas
     */
    public String getListOfItems() {
        String itemList = "";

        for (int i = 0; i < items.size(); i++) {

            if (i <= items.size()-2) {
                itemList = itemList + items.get(i).getName() + ",";

            } else {
                itemList = itemList + items.get(i).getName();
            }
        }
        return itemList;
    }


    /**
     * Accessor method for the name field.
     *
     * @return the value of the name field
     */
    public String getName() {
        return name;
    }

    /**
     * Mutator method for the name field.
     *
     * @param n String with the new value for name
     */
    public void setName(String n) {
        name = n;
    }

    /**
     * Accessor method for the description field.
     *
     * @return the value of the description field
     */
    public String getDescription() {
        return description;
    }

    /**
     * Mutator method for the description field.
     *
     * @param d String with the new value for description
     */
    public void setDescription(String d) {
        description = d;
    }

    /**
     * Accessor method for the haveVisited field.
     *
     * @return the value of the haveVisited field
     */
    public boolean getHaveVisited() {
        return haveVisited;
    }

    /**
     * Mutator method for the haveVisited field.
     *
     * @param v boolean with the new value for haveVisited
     */
    public void setHaveVisited(boolean v) {
        haveVisited = v;
    }

    /**
     * Accessor method for the doors field.
     *
     * @return the value of the doors field
     */
    public ArrayList<Door> getDoorsArray() {
        return doors;
    }


    /**
     * Method that lists descriptions of all doors that lead out of a Location.
     *
     * @return String that lists descriptions of all doors that lead out of a Location
     */
    public String getDoors() {

        String allDoors = "";

        allDoors = allDoors + "Doors for " + name + ": \n";


        for (Door x : doors) {
            if (!x.describe().contains(name)) {
                allDoors = allDoors + x.describe() + "\n";
            }
        }
        return allDoors;
    }

    /**
     * Method that tests if there is a Location that can be reached by going in a given direction.
     *
     * @param dir String that refers to what direction is being taken
     * @return Location that can be exited to
     */
    public Location leaveLocation(String dir) {

        Location possibleExit = new Location();

        for (Door d : doors) {
            if ((d.getDirection().equals(dir)) && (name.equals(d.getLeaving().getName()))) {
                possibleExit = d.getEntering();
            }
        }
        return possibleExit;
    }

    /**
     * Method that adds Doors to the doors ArrayList for a Location.
     *
     * @param door Door that is to be added to the ArrayList
     */
    public void addDoor(Door door) {
        if ((door.getEntering().getName().equals(name)) || (door.getLeaving().getName().equals(name))) {
            doors.add(door);
        }
    }

    /**
     * Method that adds Items to the items ArrayList for a Location.
     *
     * @param item Item that is to be added to the ArrayList
     */
    public void addItem(Item item) {
        if (item.getStartLocation().equals(name)) {
            items.add(item);
        }
    }

    /**
     * Method that removes Items from the items ArrayList for a Location.
     *
     * @param item Item that is to be removed from the ArrayList
     */
    public void removeItem(Item item) {
        items.remove(item);
    }

    /**
     * Method that returns an Item that's name matches the given String.
     *
     * @param name String used to search for an Item in a Location
     * @return Item that's name matches the given String
     */
    public Item getItemNamed(String name) {

        for (Item i : items) {

            if (name.equals(i.getName())) {
                return i;
            }
        }

        return null;
    }

    /**
     * Method that lists all Items in a Location.
     *
     * @return String listing all Items in a Location
     */
    public String getItemsInLocation() {

        String allItems = "";

        allItems = allItems + "Items in " + name + " (none if nothing listed): \n";

        for (Item x : items) {
            allItems = allItems + x.getName() + "\n";
        }

        return allItems;
    }

    /**
     * Accessor method for the items field.
     *
     * @return the value of the items field
     */
    public ArrayList<Item> getLocationsItemsArray() {
        return items;
    }
    public String toString(){
        return name + "\n" + description;
    }
    public void setPerson(Person person) {
    	this.person = person;
    }
    public Person getPerson() {
    	return person;
    }
}


