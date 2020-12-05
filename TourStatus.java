package com.company;

import java.util.ArrayList;

/**
 * This class represents a TourStatus object. The object contains data about the TourStatus.
 *
 * @author Tessa Lanzafame
 * @version 1.0
 */
public class TourStatus {

    private Campus campus;
    private Location currentLocation;
    private boolean canGo;
    private static TourStatus tourInstance;
    private ArrayList<Item> backpack;
    private Distance tourDistance;


    /**
     * Default constructor for objects of class TourStatus.
     */
    private TourStatus() {
        backpack = new ArrayList<Item>();
        tourDistance = Distance.getInstance();
    }


    /**
     * Accessor method for the instance field.
     *
     * @return the value of the instance field
     */
    public static synchronized TourStatus getInstance() {

        if (tourInstance == null) {
            tourInstance = new TourStatus();
        }
        return tourInstance;
    }

    /**
     * Mutator method for the campus field.
     *
     * @param campus Campus with the new value for campus
     */
    public void setCampus(Campus campus) {
        this.campus = campus;
    }

    /**
     * Accessor method for the campus field.
     *
     * @return the value of the campus field
     */
    public Campus getCampus() {
        return campus;
    }

    /**
     * Mutator method for the currentLocation field.
     *
     * @param location Location with the new value for currentLocation
     */
    public void setCurrentLocation(Location location) {
        currentLocation = location;
    }

    /**
     * Accessor method for the currentLocation field.
     *
     * @return the value of the currentLocation field
     */
    public Location getCurrentLocation() {
        return currentLocation;
    }

    /**
     * Mutator method for the canGo field.
     *
     * @param g boolean with the new value for canGo
     */
    public void setCanGo(boolean g) {
        canGo = g;
    }

    /**
     * Accessor method for the canGo field.
     *
     * @return the value of the canGo field
     */
    public boolean getCanGo() {
        return canGo;
    }


    /**
     * Method that updates which Location in the Campus the user is at.
     *
     * @param dir String referring to the direction the user wants to go
     */
    public void updateTourLocation(String dir) {

        for (Door d : currentLocation.getDoorsArray()) {
            if ((dir.equals(d.getDirection())) && (currentLocation.equals(d.getLeaving()))) {

                currentLocation = d.getEntering();
                canGo = true;
                tourDistance.setDistance(tourDistance.getDistance() + 1);
                break;

            } else {
                canGo = false;
            }
        }
    }


    /**
     * Method that adds an Item to the backpack ArrayList.
     *
     * @param item Item to be added to backpack
     */
    public void addToBackpack(Item item) {
        backpack.add(item);
    }

    /**
     * Method that removes an Item from the backpack ArrayList.
     *
     * @param item Item to be removed from backpack
     */
    public void removeFromBackpack(Item item) {
        backpack.remove(item);
    }

    /**
     * Method that removes an Item from the backpack ArrayList and adds it to a Location.
     *
     * @param name String used to search for an Item to remove
     * @return Item that's name matches the given String
     */
    public Item dropItemFromBackpack(String name) {

        if (backpack.size() >= 1) {

            for (Item i : backpack) {

                if (i.getName().equals(name)) {

                    removeFromBackpack(i);
                    currentLocation.getLocationsItemsArray().add(i);

                    return i;
                }
            }
        } else {
            return null;
        }

        return null;
    }

    /**
     * Method that adds an Item to the backpack ArrayList and removes it from a Location.
     *
     * @param name String used to search for an Item to add
     * @return Item that's name matches the given String
     */
    public Item pickupItemFromLocation(String name) {

        if (currentLocation.getLocationsItemsArray().size() >= 1) {

            for (Item x : currentLocation.getLocationsItemsArray()) {

                if (name.equals(x.getName())) {

                    currentLocation.removeItem(x);
                    addToBackpack(x);

                    return x;
                }
            }
        } else {
            return null;
        }

        return null;
    }

    /**
     * Method that lists all Items in backpack.
     *
     * @return String listing all Items in backpack
     */
    public String listBackpackItems() {

        if (backpack.size() >= 1) {

            String bpInventory = "Items in backpack:\n";

            for (Item i : backpack) {
                bpInventory = bpInventory + i.getName() + "\n";
            }

            return bpInventory;

        } else {
            return null;
        }
    }


    public boolean backpackContains(String item){
        boolean contains = false;
        for (Item i : backpack) {
            if(i.commandsActivation().contains(item)){
                contains = true;
            }
        }
        return contains;
    }

    public String itemsReplace(String command){
        String msg ="";
        for(int i= 0; i<backpack.size(); i++) {
            if (backpack.get(i).transItem(command) != null){
                if (backpack.get(i).transItem(command).getStartCommand().equals(command)) {
                    msg = backpack.get(i).transItem(command).getMsg();
                    Item l = backpack.get(i);
                    backpack.set(i, campus.getCommand(backpack.get(i).transItem(command).getTrans()));
                    campus.removeCommand(backpack.get(i).getName());
                    campus.addCommand(l);
                }
            }
        }
        return msg;
    }

    public String itemRemove(String command){
        String msg ="";
        for(int i= 0; i<backpack.size(); i++) {
            if (backpack.get(i).disItem(command) != null)
                if (backpack.get(i).disItem(command).getStartCommand().equals(command)) {
                    msg = backpack.get(i).disItem(command).getMsg();
                    backpack.remove(backpack.get(i));
                }
        }
        return msg;
    }


    public String teleport(String location){
        String msg = null;
        for(int i= 0; i<backpack.size(); i++) {
            if(backpack.get(i).getName().equals("Linux laptop") ) {
                msg = backpack.get(i).teleItem().getMsg();
                msg = msg + "\nYou are now at "+location;
                TourStatus.getInstance().setCurrentLocation(TourStatus.getInstance().getCampus().getLocation(location));
            }
        }
        if(campus.getLocation(location) == null) {
            msg = "This Location does not exist on campus";
        }
        return msg;
    }


    public String commandMsg(String command){
        for (Item item : backpack) {
            if (item.disItem(command) != null) {
                if (item.disItem(command).getStartCommand().equals(command)) {
                    return item.disItem(command).getMsg();
                }
            }
        }
        for (Item item : backpack) {
            if (item.transItem(command) != null){
                if (item.transItem(command).getStartCommand().equals(command)) {
                    return item.transItem(command).getMsg();
                }
            }
        }
        return"no message";
    }


    /**
     * Accessor method for the backpack field.
     *
     * @return the value of the instance field
     */
    public ArrayList<Item> getBackpack() {
        return backpack;
    }

    public Item getBackpackItem(String itemName) {
        for (Item i: backpack) {
            if (i.getName().equals(itemName)) {
                return i;
            }
        }
        return null;
    }


    /**
     * Method that lists all Items in the backpack, separated by commas.
     *
     * @return a String listing all Items in a Location, separated by commas
     */
    public String bpDataForSaveFile() {

        String bpList = "";

        for (int i = 0; i < backpack.size(); i++) {

            if (i <= backpack.size()-2) {
                bpList = bpList + backpack.get(i).getName() + ",";

            } else {
                bpList = bpList + backpack.get(i).getName();
            }
        }
        return bpList;
    }

}
