package com.company;

import java.util.ArrayList;

public class Teleport implements ItemCommands {
    private String locationName;
    private ArrayList<Item> itemsThatCanComplete;

    public Teleport(String locationName) {
        this.locationName = locationName;
        itemsThatCanComplete = new ArrayList<>();
    }
    public String completeAction() {

        TourStatus.getInstance().setCurrentLocation(TourStatus.getInstance().getCampus().getLocation(locationName));
        return TourStatus.getInstance().getCurrentLocation().getName();
    }
}
