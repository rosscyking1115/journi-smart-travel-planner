package com.example.journi.planner.model;

/**
 * An interface representing whether a destination is suitable for a day trip.
 * Determined if it can be visited and returned from within a single day, typically based on travel time.
 */
public interface DayTripPossible {
    boolean isDayTrip();
}