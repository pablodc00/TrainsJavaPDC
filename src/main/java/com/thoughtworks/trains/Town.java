package com.thoughtworks.trains;

import java.util.ArrayList;
import java.util.List;

public class Town<T> {
    
    private T town;
    private List<Route> routes;
    
    public Town(T town) {
        this.town = town;
        this.routes = new ArrayList<>();
    }

    public T getTown() {
        return town;
    }

    public void setTown(T town) {
        this.town = town;
    }

    public List<Route> getRoutes() {
        return routes;
    }

    public void setRoutes(List<Route> routes) {
        this.routes = routes;
    }
    

    
}
