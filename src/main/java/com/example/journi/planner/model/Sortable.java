package com.example.journi.planner.model;

/**
 * An interface for objects that can be sorted by travel time.
 *
 * Implementing classes should provide a method to return
 * travel time in minutes for comparison and sorting purposes.
 */
public interface Sortable {
    int getTravelTimeValue(); // returns total travel time in minutes
}