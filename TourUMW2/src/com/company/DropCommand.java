package com.company;

/**
 * This class implements the UserInputCommand interface.
 * It creates Objects that handle dropping Items from the backpack and putting them in a Location.
 *
 * @author Tessa Lanzafame
 * @version 1.0
 */
public class DropCommand implements UserInputCommand {

    private String itemName;


    /**
     * Non-default constructor for objects of class DropCommand.
     *
     * @param itemName String referring to itemName
     */
    public DropCommand(String itemName) {
        this.itemName = itemName;
    }


    /**
     * Method that returns a String that says an Item has been dropped in a Location.
     *
     * @return String saying an Item has been dropped in a Location
     */
    public String carryOut() {

        TourStatus tour = TourStatus.getInstance();

        String droppedItem = "";

        Item i = tour.dropItemFromBackpack(itemName);

        if (i != null) {
            droppedItem = "The " + itemName + " has been dropped in " + tour.getCurrentLocation().getName() + ".\n\n";

        } else {
            droppedItem = "That item is not in your backpack (or you didn't enter an item).\n\n";
        }

        return droppedItem;
    }

}
