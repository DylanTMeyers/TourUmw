package com.company;

/**
 * This class implements the UserInputCommand interface.
 * It creates Objects that handle users using the "backpack" command and lists the Items in the backpack.
 *
 * @author Tessa Lanzafame
 * @version 1.0
 */
public class BackpackCommand implements UserInputCommand {

    /**
     * Default constructor for objects of class BackpackCommand.
     */
    public BackpackCommand() {
    }


    /**
     * Method that lists all names of the Items in the backpack.
     *
     * @return String listing all names of the Items in the backpack
     */
    public String carryOut() {

        TourStatus tour = TourStatus.getInstance();

        String checkBP;

        String s = tour.listBackpackItems();

        if (s != null) {
            checkBP = s;

        } else {
            checkBP = "Your backpack is empty.\n\n";
        }

        return checkBP;
    }

}
