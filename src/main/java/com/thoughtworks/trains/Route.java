package com.thoughtworks.trains;

public class Route {

    private Town<String> town;
    private int distance;
    
    public Route(Town<String> town, int distance) {
        this.town = town;
        this.distance = distance;
    }

    public Town<String> getTown() {
        return town;
    }

    public void setTown(Town<String> town) {
        this.town = town;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }
    
    
}
