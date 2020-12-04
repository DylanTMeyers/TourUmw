package com.company;

/**
 * This is the interface from which the command classes implement.
 * It provides the basic method for various user commands.
 *
 * @author Tessa Lanzafame
 * @version 1.0
 */
public interface UserInputCommand {

    /**
     * Method that returns a String in response to the user's command
     * and updates the tour status of the tour if needed.
     *
     * @return String providing a message in response to a user command
     * @throws InterruptedException 
     */
    public String carryOut() throws InterruptedException;

}
