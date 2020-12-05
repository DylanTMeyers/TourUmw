package com.company;

public class TeleportCommand implements UserInputCommand {

    private String location;


    /**
     * Non-default constructor for objects of class MovementCommand.
     *
     * @param dir String referring to dir
     */
    public TeleportCommand(String location) {
        this.location = location;
    }


    /**
     * Method that describes the result of the inputted direction,
     * including the Location's name, description, doors and items.
     *
     * @return String listing the Location's name, description, doors and items
     */
    public String carryOut() {
    	String msg;
       TourStatus tour = TourStatus.getInstance();
       msg = tour.teleport(location);
       if(msg == null) {
    	   return "You do not have the Linux laptop";
       }
       else {
    	   return msg;
       }

    }
}
