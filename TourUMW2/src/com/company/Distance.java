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
    
    public String getStatus() {
    	if (this.distance < 5) {
    		return "Your Status: Eaglet Status     Points Needed to Level Up: " + (5 - this.distance);
    	}
    	else if (this.distance == 5) {
    		return "You have now reached Eagle Status!";
    	}
    	else if (this.distance < 10) {
    		return "Your Status: Eagle Status     Points Needed to Level Up: " + (10 - this.distance);
    	}
    	else if (this.distance == 10) {
    		return "You have now reached Golden Eagle Status!";
    	}
    	else if (this.distance < 15) {
    		return "Your Status: Golden Eagle Status     Points Needed to Level Up: " + (15 - this.distance);
    	}
    	else if (this.distance == 15) {
    		return "You have now reached Platinum Eagle Status, the highest level!";
    	}
    	else {
    		return "Your Status: Platinum Eagle Status";
    	}
    }
}
