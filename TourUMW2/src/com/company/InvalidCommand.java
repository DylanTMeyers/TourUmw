package com.company;

/**
 * This class implements the UserInputCommand interface.
 * It creates Objects that handle any inputted commands that are invalid.
 *
 * @author Tessa Lanzafame
 * @version 1.0
 */
public class InvalidCommand implements UserInputCommand {

    private String invalidCommand;


    /**
     * Non-default constructor for objects of class InvalidCommand.
     *
     * @param invalidCommand String referring to invalidCommand
     */
    public InvalidCommand(String invalidCommand) {
        this.invalidCommand = invalidCommand;
    }


    /**
     * Method that says the inputted command is invalid and provides a list of valid commands.
     *
     * @return String containing an error message and a list of valid commands
     */
    public String carryOut() {

        String invalidOption = "";

        invalidOption = invalidOption + invalidCommand + " is not a valid command.\n" +
                "Commands: n, s, e, w, pickup/p, drop/d, backpack/b, save, q\n";

        return invalidOption;
    }

}
