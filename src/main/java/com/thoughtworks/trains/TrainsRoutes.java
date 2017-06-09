package com.thoughtworks.trains;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TrainsRoutes {
    
    private Map<Town<String>, List<Route>> trainsMap;
    
    public TrainsRoutes(String input) {
        trainsMap = new HashMap<>();
        buildTrainsRoutes(input);
    }
    
    
    /**
     * Build the graph
     * @param input: AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7
     */
    public void buildTrainsRoutes(String input) {
        Town<String> town = null;
        Town<String> routeTown = null;
        Route route = null;
        List<String> listOfInput = Arrays.asList(input.split(","));
        for (String string : listOfInput) {
            town = new Town<>(string.substring(0, 1));
            routeTown = new Town<>(string.substring(1, 2));
            route = new Route(routeTown, Integer.parseInt(string.substring(2)));
            trainsMap.putIfAbsent(town, new ArrayList<>());
            trainsMap.get(town).add(route);
        }
    }

    /**
     * Calculate distance of the given route.
     * Assume listOfTowns has more than one element.
     * @param listOfTowns
     * @return The distance of the route
     */
    public String distanceOfRoute(List<Town<String>> listOfTowns) {
        final String NOT_FOUND = "NO SUCH ROUTE";
        int distance = 0;
        int currentIndex = 0;
        List<Route> routes = null;
        Route route = null;
        
        for (Town<String> town : listOfTowns) {
            currentIndex = listOfTowns.indexOf(town);
            final Town<String> nextTown = listOfTowns.get(currentIndex +1);
            if (currentIndex > 0 && currentIndex <  listOfTowns.size() -1) {
                routes = trainsMap.get(town);
                route = routes.stream()
                    .filter(r -> r.getTown().equals(nextTown))
                    .findFirst()
                    .orElse(null);
                if (null == route) {
                    return NOT_FOUND;
                } else {
                    distance += route.getDistance();
                }
                
            }
        }               
        
        return String.valueOf(distance);
    }
    
}
