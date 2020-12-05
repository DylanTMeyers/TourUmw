package com.company;

/**
 * This class implements Item Command using the strategy pattern.
 * Has all data of the teleport commands.
 *
 * @author Tessa Lanzafame
 * @version 1.0
 */
public class Teleport implements ItemCommand {
 	private String startCommand;
    private String halfCommand;
    private String trans;
    private String msg;
    private String itemName;
    /**
     * Non-default constructor for objects of class PickupCommand.
     * Sets half command, trans, msg, start command, item name.
     * @param halfCommand sets the half command
     * @param trans sets the trans
     * @param startCommand sets the start command
     * @param itemName sets the item name
     */
    public Teleport(String halfCommand, String trans, String msg,String startCommand, String itemName) {
    this.halfCommand = halfCommand;
    this.trans = trans;
    this.msg = msg;
    this.startCommand = startCommand;
    this.itemName = itemName;
    }
    /**
     * gets the question
     * @return String the question of whether the command should be used
     */

    public String question() {

        return "Would you like to " + halfCommand + " with the " + itemName + "? (If you do, type [" + halfCommand + "]" + " [Location Name])";
    }
    /**
     * Gets the start command
     * @return String of the start commmand
     */
    public String getStartCommand(){
        return startCommand;
    }
    /**
     * Gets the trans
     * @return String of the trans
     */
    public String getTrans(){
        return trans;
    }
    /**
     * Gets the msg
     * @return String of the msg
     */
    public String getMsg(){
        return msg;
    }
    /**
     * Gets the half command
     * @return String of the half command
     */
    public String getHalfCommand(){
        return halfCommand;
    }
    /**
     * Gets the item name
     * @return String of the item name
     */
    public String getItemName(){
        return itemName;
    }    
    /**
     * Sets the start command
     * @param startCommand the action
     */
    public void setStartCommand(String startCommand){
        this.startCommand = startCommand;
    }
    /**
     * Sets the trans
     * @param trans the item name after command
     */
    public void setTrans(String trans){
        this.trans = trans;
    }
    /**
     * Sets the msg
     * @param msg the msg the command outputs
     */
    public void setMsg(String msg){
        this.msg = msg;
    }
    /**
     * Sets the half command
     * @param halfCommand the command itself
     */
    public void setHalfCommand(String halfCommand){
        this.halfCommand = halfCommand;
    }
    /**
     * Sets the item name
     * @param itemName the item the command goes to
     */
    public void setItemName(String itemName){
       this.itemName = itemName;
    }
}
