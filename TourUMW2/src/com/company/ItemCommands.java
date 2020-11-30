package com.company;

public class ItemCommands implements UserInputCommand{
    private String itemCommand;
    private String msg;
    public ItemCommands(String itemCommand){
        this.itemCommand = itemCommand;
    }
    public String carryOut() {
        TourStatus tour = TourStatus.getInstance();

            msg = tour.itemsReplace(itemCommand)+tour.itemRemove(itemCommand);

        return msg.toUpperCase();
    }
}
