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
     * Answer questions 1-5.
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
     * Answer question 6.
     * @param start
     * @param end
     * @param maxStops
     * @return The number of trips starting at start and ending at end with a maximum of maxStops stops.
     */
    public String numberOfTripsMaxStops(Town<String> start, Town<String> end, int maxStops) {        
        AtomicInteger counter = new AtomicInteger();
        Deque<Route> queue = new LinkedList<>();
        
        countTripsMaxStops(start, end, maxStops, queue, counter);
        
        return counter.toString();
    }
    
    private void countTripsMaxStops(Town<String> start, Town<String> end, int maxStops, 
            Deque<Route> queue, AtomicInteger counter) {
        
        if (queue.size() < maxStops) {
            Town<String> currentTown = null;
            List<Route> routes = trainsMap.get(start);
            for (Route route : routes) {                
                currentTown = route.getTown();
                if (currentTown.equals(end)) {
                    counter.incrementAndGet();
                }
                queue.addLast(route);
                countTripsMaxStops(currentTown, end, maxStops, queue, counter);
                queue.removeLast();
            } 
        }
    }
    
    
    /**
     * Answer question 7.
     * @param start
     * @param end
     * @param exacltyStops
     * @return The number of trips starting at start and ending at end with exactly exacltyStops stops.
     */
    public String numberOfTripsExacltyStops(Town<String> start, Town<String> end, int exacltyStops) {
        AtomicInteger counter = new AtomicInteger();
        Deque<Route> queue = new LinkedList<>();
        
        countTripsExacltyStops(start, end, exacltyStops, queue, counter);
        
        return counter.toString();
    }
    

    private void countTripsExacltyStops(Town<String> start, Town<String> end, int exacltyStops, 
            Deque<Route> queue, AtomicInteger counter) {
       
        if (queue.size() < exacltyStops) {
            Town<String> currentTown = null;
            List<Route> routes = trainsMap.get(start);
            for (Route route : routes) {

                currentTown = route.getTown();
                if (currentTown.equals(end) && queue.size() == exacltyStops -1) {
                    counter.incrementAndGet();
                }
                queue.addLast(route);
                countTripsExacltyStops(currentTown, end, exacltyStops, queue, counter);
                queue.removeLast();

            } 
        }      
    }
    
    
    /**
     * Answer questions 8,9.
     * @param start
     * @param end
     * @return The length of the shortest route (in terms of distance to travel) from start to end.
     */
    public String lengthShortestRoute(Town<String> start, Town<String> end) {
        //AtomicInteger minDistance = new AtomicInteger(999999);
        ThreadLocal<Integer> minDistance = new ThreadLocal<>();
        minDistance.set(999999);
        int sumDistance = 0;
        Deque<Town<String>> queue = new LinkedList<>();
        
        calculateShortestRoute(start, end, queue, minDistance, sumDistance);
        
        return minDistance.get().toString();
    }
    
    
    private void calculateShortestRoute(Town<String> start, Town<String> end, Deque<Town<String>> queue, ThreadLocal<Integer> minDistance, int sumDistance) {

            Town<String> currentTown = null;
            List<Route> routes = trainsMap.get(start);
            for (Route route : routes) {

                currentTown = route.getTown();

                if (currentTown.equals(end)) {
                    if (sumDistance + route.getDistance() < minDistance.get()) {
                        minDistance.set(sumDistance + route.getDistance());
                        continue;
                    }
                }
                
                if (!queue.contains(currentTown)) {
                    queue.addLast(currentTown);
                    sumDistance += route.getDistance();
                    calculateShortestRoute(currentTown, end, queue, minDistance, sumDistance);
                    sumDistance -= route.getDistance();
                    queue.removeLast();
                }
            }
    }
    
    /**
     * Answer question 10.
     * @param start
     * @param end
     * @return The number of different routes from start to ed with a distance of less than given number
     */
    public String numberDifferenRoutes(Town<String> start, Town<String> end, int maxDistance) {
        int sumDistance = 0;
        AtomicInteger counter = new AtomicInteger();
        
        calculateDifferenRoutes(start, end, maxDistance, counter, sumDistance);
        
        return counter.toString();
    }


    private void calculateDifferenRoutes(Town<String> start, Town<String> end, int maxDistance, 
            AtomicInteger counter, int sumDistance) {
        
        Town<String> currentTown = null;
        List<Route> routes = trainsMap.get(start);
        for (Route route : routes) {

            currentTown = route.getTown();
            if (sumDistance + route.getDistance() >= maxDistance) {
                continue;
            }
            
            if (currentTown.equals(end)) {
                counter.getAndIncrement();
            }
            
            sumDistance += route.getDistance();
            calculateDifferenRoutes(currentTown, end, maxDistance, counter, sumDistance);
            sumDistance -= route.getDistance();
        }
        
    }


    public Map<Town<String>, List<Route>> getTrainsMap() {
        return trainsMap;
    }


    public void setTrainsMap(Map<Town<String>, List<Route>> trainsMap) {
        this.trainsMap = trainsMap;
    }

}
