package com.thoughtworks.trains;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

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
        List<String> listOfInput = Arrays.asList(input.split("[\\s]*,[\\s]*"));
        for (String string : listOfInput) {
            town = new Town<>(string.substring(0, 1));
            routeTown = new Town<>(string.substring(1, 2));
            route = new Route(routeTown, Integer.parseInt(string.substring(2)));
            trainsMap.putIfAbsent(town, new ArrayList<>());
            trainsMap.get(town).add(route);
        }
    }

    /**
     * Answers questions 1-5.
     * Calculate distance of the given route.
     * Assume listOfTowns has more than one element.
     * @param listOfTowns
     * @return The distance of the route
     */
    public String distanceOfRoute(List<Town<String>> listOfTowns) {

        int distance = 0;
        int currentIndex = 0;
        List<Route> routes = null;
        Route route = null;
        
        for (Town<String> town : listOfTowns) {
            currentIndex = listOfTowns.indexOf(town);
            if (currentIndex <  listOfTowns.size() -1) {
                final Town<String> nextTown = listOfTowns.get(currentIndex +1);
                routes = trainsMap.get(town);
                route = routes.stream()
                    .filter(r -> r.getTown().equals(nextTown))
                    .findFirst()
                    .orElse(null);
                if (null == route) {
                    return Constants.NOT_FOUND;
                } else {
                    distance += route.getDistance();
                }
                
            }
        }               
        
        return String.valueOf(distance);
    }


    /**
     * Answers question 6.
     * @param start
     * @param end
     * @param maxStops
     * @return The number of trips starting at start and ending at end with a maximum of maxStops stops.
     */
    public String numberOfTrips(Town<String> start, Town<String> end, int maxStops) {        
        AtomicInteger counter = new AtomicInteger();
        Deque<Town<String>> queue = new LinkedList<>();
        
        countTrips(start, end, maxStops, queue, counter);
        
        return String.valueOf(counter.get());
    }
    
    /**
     * Recursive method to go over graph.
     * @param start
     * @param end
     * @param maxStops
     * @param queue
     * @param counter
     */
    private void countTrips(Town<String> start, Town<String> end, int maxStops, 
            Deque<Town<String>> queue, AtomicInteger counter) {
        
        if (queue.size() < maxStops) {
            Town<String> currentTown = null;
            List<Route> routes = trainsMap.get(start);
            for (Route route : routes) {
                currentTown = route.getTown();
                if (currentTown.equals(end)) {
                    counter.incrementAndGet();
                } else if (!queue.contains(currentTown)) {
                    queue.add(currentTown);
                    countTrips(currentTown, end, maxStops, queue, counter);
                    queue.removeLast();
                }
            }
        }
    }
    
    
    
    public Map<Town<String>, List<Route>> getTrainsMap() {
        return trainsMap;
    }


    public void setTrainsMap(Map<Town<String>, List<Route>> trainsMap) {
        this.trainsMap = trainsMap;
    }

}
