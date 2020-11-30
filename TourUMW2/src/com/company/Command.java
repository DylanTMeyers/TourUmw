package com.company;

public class Command {
    public ItemCommand itemcommand;


    public Command(ItemCommand itemcommand){
        this.itemcommand = itemcommand;
    }
    public String questions(){
        return itemcommand.question();
    }
    public String getStartCommand(){
        return itemcommand.getStartCommand();
    }
    public String getTrans() {
        return itemcommand.getTrans();
    }
    public String getMsg(){
        return itemcommand.getMsg();
    }


}
