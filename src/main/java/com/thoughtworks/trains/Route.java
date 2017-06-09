package com.thoughtworks.trains;

public class Route<T,E> {

    private T town;
    private E distance;
    
    public Route(T town, E distance) {
        this.town = town;
        this.distance = distance;
    }

    public T getTown() {
        return town;
    }

    public void setTown(T town) {
        this.town = town;
    }

    public E getDistance() {
        return distance;
    }

    public void setDistance(E distance) {
        this.distance = distance;
    }
    
    
}
