package com.company;



public class Transform implements ItemCommand {

    private String startCommand;
    private String halfCommand;
    private String trans;
    private String msg;
    private String itemName;
    public Transform(String halfCommand, String trans, String msg,String startCommand, String itemName) {
    this.halfCommand = halfCommand;
    this.trans = trans;
    this.msg = msg;
    this.startCommand = startCommand;
    this.itemName = itemName;
    }


    public String question() {

        return "Would you like to " + startCommand + " the " + itemName + "? (If you do, type [" + startCommand + "])";
    }
    public String getStartCommand(){
        return startCommand;
    }
    public String getTrans(){
        return trans;
    }
    public String getMsg(){
        return msg;
    }
}