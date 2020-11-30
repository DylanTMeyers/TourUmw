package com.company;

import java.util.ArrayList;
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
    private String itemCommand;
    private ArrayList<Command> transform;
    private ArrayList<Command> disappear;


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
        transform = new ArrayList<Command>();
        disappear = new ArrayList<Command>();
        boolean alreadyHap = false;
        boolean hitEnd = false;
        String command = "null";
        if (((s.hasNextLine())) && ((s.hasNext("\\+\\+\\+")) || (s.hasNext("Items:")))) {
            s.nextLine();
            if (s.hasNext("\\*\\*\\*\\*\\*")) {
                name = null;
                hitEnd = true;
    
            } else {
                name = s.nextLine();


                String line2 = s.nextLine();
                if (!line2.contains("[") && !line2.contains(":")) {
                    startLocation = line2;
                } else {
                    command = line2;
                    startLocation = "none";
                    alreadyHap = true;
                    message = "none";

                }


                String line3 = s.nextLine();
                if (!line3.contains("[") && !line3.contains(":")) {
                    message = line3;
                } else {
                    command = line3;
                    alreadyHap = true;
                    message = "none";

                }

            }
        }
        String[] StringArray;

        while (s.hasNextLine() && !s.hasNext("\\+\\+\\+") && name != null&& !hitEnd) {
            StringBuilder trans = new StringBuilder();
            StringBuilder halfCommand = new StringBuilder();
            if (!alreadyHap) {
                command = s.nextLine();
                if (command.equals("+++")) {
                    break;
                }
                itemCommand = command;
            }
            StringArray = command.split(":");
            if (StringArray[0].contains("[")) {

                boolean notYet = false;
                for (int i = 0; i < StringArray[0].length(); i++) {
                    if(notYet) {
                        if(StringArray[0].charAt(i) == ']' || StringArray[0].charAt(i) == '('){
                            break;
                        }
                        halfCommand.append(StringArray[0].charAt(i));
                    }
                    if (StringArray[0].charAt(i) == '[') {
                        notYet = true;

                    }
                }
            }
            if (StringArray[0].contains("(")) {

                boolean yet = false;
                for (int i = 0; i < StringArray[0].length(); i++) {
                    if(yet){
                        if(StringArray[0].charAt(i) == ')') {
                            break;
                        }
                        trans.append(StringArray[0].charAt(i));
                        } if (StringArray[0].charAt(i) == '(') {
                        yet = true;

                    }
                }
            }
            String action;
            String[] Array;
            if(!StringArray[0].contains("(") && !StringArray[0].contains("[")){
                action = StringArray[0];
            }
            else {
                Array = StringArray[0].split("\\[");
                action = Array[0];
            }
            switch (halfCommand.toString()) {
                case "Transform":
                    Command transformCommand = new Command(new Transform(halfCommand.toString(), trans.toString(), StringArray[1], action,getName()));
                    transform.add(transformCommand);
                    break;
                case "Disappear":
                    Command disappearCommand = new Command(new Disappear(halfCommand.toString(), trans.toString(), StringArray[1],action,getName()));
                    disappear.add(disappearCommand);
                    break;
                case "Teleport":

                    break;
            }
            alreadyHap = false;

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
    public String toString(){
        return name + "\n" + startLocation+ "\n"+ message + "\n" +itemCommand;
    }
    public String commandsActivation(){
        StringBuilder list = new StringBuilder();
        for(Command command:transform){
            list.append(command.questions()).append("\n");
        }
        for(Command command: disappear){
            list.append(command.questions()).append("\n");
        }
return(list.toString());
    }
    public Command transItem(String command){
        Command n = null;
        for(Command l: transform){
            if(l.getStartCommand().equals(command)){
                n = l;
            }
        }
        return n;
    }
    public Command disItem(String command){
        Command n = null;
        for(Command l: disappear){
            if(l.getStartCommand().equals(command)){
                n = l;
            }
        }
        return n;

    }


}
