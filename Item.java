package com.company;

import java.util.Scanner;

/**
 * This class represents an Item object. Each object contains data about an Item
 *
 * @author Tessa Lanzafame
 * @version 1.0
 */
public class Item {

    private String name;
    private String startLocation;
    private String message;

    private boolean inSaveData;


    /**
     * Default constructor for objects of class Item.
     */
    public Item() {
    }

    /**
     * Non-default constructor for objects of class Item.
     *
     * @param s Scanner that reads in a file and assigns values to fields
     */
    public Item(Scanner s) {

        String line4 = s.nextLine();

        while (((s.hasNextLine())) && (!line4.equals("+++"))) {

            String line = s.nextLine();
            name = line;

            String line2 = s.nextLine();
            startLocation = line2;

            String line3 = s.nextLine();
            message = line3;
        }
    }

    /**
     * Non-default constructor for objects of class Item.
     *
     * @param n  String referring to name
     * @param sL String referring to the name of startLocation
     * @param m  String referring to message
     */
    public Item(String n, String sL, String m) {
        name = n;
        startLocation = sL;
        message = m;
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
     * @param name String with the new value for name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Accessor method for the message field.
     *
     * @return the value of the message field
     */
    public String getMessage() {
        return message;
    }

    /**
     * Mutator method for the message field.
     *
     * @param msg String with the new value for message
     */
    public void setMessage(String msg) {
        message = msg;
    }

    /**
     * Accessor method for the startLocation field.
     *
     * @return the value of the startLocation field
     */
    public String getStartLocation() {
        return startLocation;
    }

    /**
     * Mutator method for the startLocation field.
     *
     * @param sL Location object with the new value for startLocation
     */
    public void setStartLocation(String sL) {
        startLocation = sL;
    }


    public boolean getInSaveData() {
        return inSaveData;
    }

    public void setInSaveData(boolean b) {
        inSaveData = b;
    }
}
