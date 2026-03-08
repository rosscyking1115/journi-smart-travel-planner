package com.example.journi.planner.model;

/**
 * An interface for managing favourite status on an object.
 *
 * Implementing classes should define how to toggle the favourite state
 * and check whether the object is currently marked as a favourite.
 */
public interface Favourites {
    void favourites();
    boolean isAdd();
}
