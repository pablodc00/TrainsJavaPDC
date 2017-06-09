package com.thoughtworks.trains;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TrainsRoutes {
    
    private Map<Town, List<Route>> trainsMap;
    
    public TrainsRoutes(String input) {
        trainsMap = new HashMap<>();
        buildTrainsRoutes(input);
    }
    
    
    /**
     * Build the graph
     * @param input: AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7
     */
    public void buildTrainsRoutes(String input) {
        Town town = null;
        Route route = null;
        List<String> listOfInput = Arrays.asList(input.split(","));
        for (String string : listOfInput) {
            town = new Town(string.substring(0, 1));
            route = new Route(string.substring(1, 2), Integer.parseInt(string.substring(2)));
            trainsMap.putIfAbsent(town, new ArrayList<>());
            trainsMap.get(town).add(route);
        }
    }

    
    
    
}
