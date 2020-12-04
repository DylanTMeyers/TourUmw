package com.company;

/**
 * This class implements the UserInputCommand interface.
 * It creates Objects that handle the user's direction commands.
 *
 * @author Tessa Lanzafame
 * @version 1.0
 */
public class MovementCommand implements UserInputCommand {

    private String dir;


    /**
     * Non-default constructor for objects of class MovementCommand.
     *
     * @param dir String referring to dir
     */
    public MovementCommand(String dir) {
        this.dir = dir;
    }


    /**
     * Method that describes the result of the inputted direction,
     * including the Location's name, description, doors and items.
     *
     * @return String listing the Location's name, description, doors and items
     * @throws InterruptedException 
     */
    public String carryOut() throws InterruptedException {

        TourStatus tour = TourStatus.getInstance();

        String movementResult;

        tour.updateTourLocation(dir);

        if ((!tour.getCurrentLocation().getHaveVisited()) && (tour.getCanGo())) {

            tour.getCurrentLocation().setHaveVisited(true);

            movementResult = "Current location: " + tour.getCurrentLocation().getName() + "\n" +
                    tour.getCurrentLocation().getDescription() + "\n \n" +
                    tour.getCurrentLocation().getDoors() + "\n" +
                    tour.getCurrentLocation().getItemsInLocation() + "\n";
            
            if (tour.getCurrentLocation().getPerson() != null) {
            	movementResult = movementResult + tour.getCurrentLocation().getPerson().getName() + "\n" +
            				"     Type 'talk' to talk to " + tour.getCurrentLocation().getPerson().getName() + "\n";
            }
            		

        } else if ((tour.getCurrentLocation().getHaveVisited()) && (tour.getCanGo())) {

            movementResult = "Current location: " + tour.getCurrentLocation().getName() + "\n\n" +
                    tour.getCurrentLocation().getDoors() + "\n" +
                    tour.getCurrentLocation().getItemsInLocation() + "\n";
            if (tour.getCurrentLocation().getPerson() != null) {
            	movementResult = movementResult + tour.getCurrentLocation().getPerson().getName() + "\n" +
            				"     Type 'talk' to talk to " + tour.getCurrentLocation().getPerson().getName() + "\n";
            }

        } else {

            movementResult = "You can't go that way.\n\n" +
                    tour.getCurrentLocation().getDoors() + "\n";
        }


        return movementResult;
    }

}
