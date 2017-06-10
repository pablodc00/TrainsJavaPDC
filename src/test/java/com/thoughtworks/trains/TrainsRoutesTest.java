package com.thoughtworks.trains;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import org.junit.Test;


public class TrainsRoutesTest {


    TrainsRoutes trainsRoutes = new TrainsRoutes("AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7");        
    
    //@Test
    public void buildTrainsRoutesTest() {
        Town<String> town = new Town<>("A");
        List<Route> routes = trainsRoutes.getTrainsMap().get(town);
        assertEquals(routes.size(), 3);
        
        town = new Town<>("C");
        routes = trainsRoutes.getTrainsMap().get(town);
        assertEquals(routes.size(), 2);        
    }
    
    //@Test
    public void distanceOfRouteTest() {
        //Test The distance of the route A-B-C.
        List<Town<String>> towns = new ArrayList<>();
        Town<String> town = new Town<>("A");
        towns.add(town);
        town = new Town<>("B");
        towns.add(town);
        town = new Town<>("C");
        towns.add(town);        
        assertEquals(trainsRoutes.distanceOfRoute(towns), "9");
        
        //Test The distance of the route A-E-D
        towns.clear();
        town = new Town<>("A");
        towns.add(town);
        town = new Town<>("E");
        towns.add(town);
        town = new Town<>("D");
        towns.add(town);        
        assertEquals(trainsRoutes.distanceOfRoute(towns), Constants.NOT_FOUND);
        
    }
    
    @Test
    public void numberOfTripsTest() {
        //Test the number of trips starting at C and ending at C with a maximum of 3 stops.
        //In the sample data below, there are two such trips: C-D-C (2 stops). and C-E-B-C (3 stops).
        Town<String> start = new Town<>("C");
        Town<String> end = new Town<>("C");
        assertEquals(trainsRoutes.numberOfTrips(start, end, 3), "2");
        
    }
    
}
