package com.company;

public class Distance {
    private static Distance distanceInstance;
    private int distance;

    private Distance() {
        distance = 0;
    }
    public static synchronized Distance getInstance() {

        if (distanceInstance == null) {
            distanceInstance = new Distance();
        }
        return distanceInstance;
    }
    public void setDistance(int num) {
        distance = num;
    }
    public int getDistance() {
        return distance;
    }
}
