package com.company;

/**
 * This class implements the UserInputCommand interface.
 * It creates Objects that handle picking up Items from a Location and
 * updating the Item ArrayLists for a Location and the backpack.
 *
 * @author Tessa Lanzafame
 * @version 1.0
 */
public class PickupCommand implements UserInputCommand {

    private String itemName;


    /**
     * Non-default constructor for objects of class PickupCommand.
     *
     * @param itemName String referring to itemName
     */
    public PickupCommand(String itemName) {
        this.itemName = itemName;
    }


    /**
     * Method that returns a String that says an Item has been picked up from a Location.
     *
     * @return String saying an Item has been picked up from a Location
     */
    public String carryOut() {

        TourStatus tour = TourStatus.getInstance();

        String pickedItem = "";

        Item i = tour.pickupItemFromLocation(itemName);

        if (i != null) {
            pickedItem = "You got " + itemName + ": " + i.getMessage() + "\n\n";

        } else {
            pickedItem = "That item is not in this location (or you didn't enter an item).\n\n";
        }

        return pickedItem;
    }

}
