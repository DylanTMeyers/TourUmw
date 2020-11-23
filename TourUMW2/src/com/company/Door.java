package com.company;

import java.util.Scanner;

/**
 * This class represents a Door object. Each object contains data about a Door.
 *
 * @author Tessa Lanzafame
 * @version 1.0
 */
public class Door {

    private String direction;
    private Location leavingLocation;
    private Location enteringLocation;


    /**
     * Non-default constructor for objects of class Door.
     *
     * @param dir   String referring to direction
     * @param leave Location object referring to leavingLocation
     * @param enter Location object referring to enteringLocation
     */
    public Door(String dir, Location leave, Location enter) {
        direction = dir;
        leavingLocation = leave;
        enteringLocation = enter;
    }

    /**
     * Non-default constructor for objects of class Door.
     *
     * @param s Scanner that reads in a file and assigns values to fields
     * @param c Campus object that is referred to
     */
    public Door(Scanner s, Campus c) {

        String line4 = s.nextLine();

        while (((s.hasNextLine())) && (!line4.equals("+++"))) {

            String line = s.nextLine();
            leavingLocation = c.getLocation(line);

            String line2 = s.nextLine();
            direction = line2;

            String line3 = s.nextLine();
            enteringLocation = c.getLocation(line3);
        }
    }

    /**
     * Non-default constructor for objects of class Door.
     *
     * @param s       Scanner that reads in a file and assigns values to fields
     * @param c       Campus object that is referred to
     * @param leaving String referring to name of leavingLocation
     */
    public Door(Scanner s, Campus c, String leaving) {

        leavingLocation = c.getLocation(leaving);

        while ((s.hasNextLine())) {

            String line = s.nextLine();

            if (!line.equals("+++")) {

                direction = line;

                String line2 = s.nextLine();
                enteringLocation = c.getLocation(line2);

            } else {
                break;
            }
        }
    }


    /**
     * Method that describes what direction will lead to what Location.
     *
     * @return String containing what direction will lead to what Location
     */
    public String describe() {
        return "You can go " + direction.toUpperCase() + " to get to " + enteringLocation.getName() + ".";
    }


    /**
     * Accessor method for the leavingLocation field.
     *
     * @return the value of the leavingLocation field
     */
    public Location getLeaving() {
        return leavingLocation;
    }

    /**
     * Mutator method for the leavingLocation field.
     *
     * @param leave Location object with the new value for leavingLocation
     */
    public void setLeaving(Location leave) {
        leavingLocation = leave;
    }

    /**
     * Accessor method for the enteringLocation field.
     *
     * @return the value of the enteringLocation field
     */
    public Location getEntering() {
        return enteringLocation;
    }

    /**
     * Mutator method for the enteringLocation field.
     *
     * @param enter Location object with the new value for enteringLocation
     */
    public void setEntering(Location enter) {
        enteringLocation = enter;
    }

    /**
     * Accessor method for the direction field.
     *
     * @return the value of the direction field
     */
    public String getDirection() {
        return direction;
    }

    /**
     * Mutator method for the direction field.
     *
     * @param dir String with the new value for direction
     */
    public void setDirection(String dir) {
        direction = dir;
    }

}
