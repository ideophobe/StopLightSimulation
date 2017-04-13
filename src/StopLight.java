/*
  Created by Drake Nelson on 4/12/2017.
  The stoplight simulation stoplight object is the object being simulated
 */

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

class StopLight {
    //Stoplight has a clock to keep track of runtime in seconds
    private static int clock;

    static int getClock() {
        return clock;
    }

    static void setClock(int c) {
        clock = c;
    }

    //end time for the simulation
    private int endTimeClock;
    //variables used for statistics
    static double totalNSCars;
    static double totalEWCars;
    static double totalNSWaitTime;
    static double totalEWWaitTime;
    private double avgWaitTimePerCarEW;
    private double avgWaitTimePerCarNS;

    static Queue<Car> getEastCars() {
        return eastCars;
    }

    static Queue<Car> getNorthCars() {
        return northCars;
    }

    static Queue<Car> getWestCars() {
        return westCars;
    }

    //Queue for the cars on the eastwest road
    private static Queue<Car> eastCars, northCars, westCars;

    static void addCar(Car car) {
        if (car.getRoad() == Road.EAST_WEST) {
            eastCars.add(car);
        } else if (car.getPath() == Path.NORTH_NORTH) {
            northCars.add(car);
        } else {
            westCars.add(car);
        }
    }

    //PriorityQueue:stopLightEvents is a queue that holds the events in the order they will happen based on the clock time
    private static PriorityQueue<StopLightEvent> stopLightEvents = new PriorityQueue<>(10, new StopLightEventComparator());

    static void addEvent(StopLightEvent e) {
        stopLightEvents.add(e);
    }

    //this variable holds the current value of the stoplight which controls who has green
    static StopLightStatus stopLightStatus;

    //constructor for the stoplight
    StopLight() {
        //1000 hours * 60 minutes * 60 seconds runs the simulation for total time in seconds
        endTimeClock = 1000 * 60 * 60;
        eastCars = new LinkedList<>();
        northCars = new LinkedList<>();
        westCars = new LinkedList<>();
        clock = 0;
        stopLightStatus = StopLightStatus.EAST_GREEN;
        totalNSCars = 0;
        totalEWCars = 0;
        totalNSWaitTime = 0;
        totalEWWaitTime = 0;
        avgWaitTimePerCarEW = 0;
        avgWaitTimePerCarNS = 0;
    }

    //boolean variables to check if queues can deque
    static boolean canMakeLeftTurn = false;

    void simulation() {
        stopLightEvents.add(new ArrivalEvent(0, Road.EAST_WEST));
        stopLightEvents.add(new ArrivalEvent(0, Road.NORTH_SOUTH));
        stopLightEvents.add(new StopLightChangeEvent(0, StopLightStatus.EAST_GREEN));
        while (clock < endTimeClock) {
            StopLightEvent event = stopLightEvents.poll();
            event.execute();
        }
        adjust();
    }

    private void adjust() {
        avgWaitTimePerCarEW = totalEWWaitTime / totalEWCars;
        avgWaitTimePerCarNS = totalNSWaitTime / totalNSCars;
    }

    void printStats() {
        System.out.println("The average wait time of a car in the North/South Road: " + (int) avgWaitTimePerCarNS + " seconds");
        System.out.println("The average wait time of a car in the East/West Road: " + (int) avgWaitTimePerCarEW + " seconds");
    }

}
