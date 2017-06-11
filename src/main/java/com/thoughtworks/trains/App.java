package com.thoughtworks.trains;

import java.util.ArrayList;
import java.util.List;

/**
 * TrainsJavaPDC
 * ThoughtWorks Problem one: Trains
 */
public class App 
{
    public static void main( String[] args )
    {

        TrainsRoutes trainsRoutes = new TrainsRoutes(Constants.SAMPLE_DATE);
     
        
        List<Town<String>> towns = new ArrayList<>();
        towns.add(new Town<>("A"));
        towns.add(new Town<>("B"));
        towns.add(new Town<>("C"));                
        System.out.println("Output #1: " + trainsRoutes.distanceOfRoute(towns));
        
        towns.clear();
        towns.add(new Town<>("A"));
        towns.add(new Town<>("D"));
        System.out.println("Output #2: " + trainsRoutes.distanceOfRoute(towns));

        towns.add(new Town<>("C"));                
        System.out.println("Output #3: " + trainsRoutes.distanceOfRoute(towns));
        
        towns.clear();
        towns.add(new Town<>("A"));
        towns.add(new Town<>("E"));
        towns.add(new Town<>("B"));
        towns.add(new Town<>("C"));
        towns.add(new Town<>("D"));
        System.out.println("Output #4: " + trainsRoutes.distanceOfRoute(towns));
        
        towns.clear();
        towns.add(new Town<>("A"));
        towns.add(new Town<>("E"));
        towns.add(new Town<>("D"));
        System.out.println("Output #5: " + trainsRoutes.distanceOfRoute(towns));

        System.out.println("Output #6: " + trainsRoutes.numberOfTripsMaxStops(new Town<>("C"), new Town<>("C"), 3));

        System.out.println("Output #7: " + trainsRoutes.numberOfTripsExacltyStops(new Town<>("A"), new Town<>("C"), 4));

        System.out.println("Output #8: " + trainsRoutes.lengthShortestRoute(new Town<>("A"), new Town<>("C")));
        System.out.println("Output #9: " + trainsRoutes.lengthShortestRoute(new Town<>("B"), new Town<>("B")));
        
        System.out.println("Output #10: " + trainsRoutes.numberDifferenRoutes(new Town<>("C"), new Town<>("C"), 30));

    }
}
