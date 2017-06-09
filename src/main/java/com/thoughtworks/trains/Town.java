package com.thoughtworks.trains;

public class Town<T> {
    
    private T town;
    
    public Town(T town) {
        this.town = town;
    }

    
    public T getTown() {
        return town;
    }

    public void setTown(T town) {
        this.town = town;
    }

    
}
