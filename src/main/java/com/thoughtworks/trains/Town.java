package com.thoughtworks.trains;

public class Town<E> {
    
    private String town;
    
    public Town(String town) {
        this.town = town;
    }

    
    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    @Override
    public boolean equals(Object obj) {     
        return this.getTown().equals(((Town<?>)obj).getTown());
    }
    
    @Override
    public int hashCode() {
        return this.getTown().hashCode();
    }
}
