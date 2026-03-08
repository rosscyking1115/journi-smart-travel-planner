package com.example.journi.planner.model;

/**
 * An enum represents different types of travel destinations.
 *
 * Includes CITY, BEACH, and MOUNTAIN as possible values.
 * Overrides toString to return a user-readable label for each type.
 */
public enum PlaceType {
    CITY,
    BEACH,
    MOUNTAIN;

    @Override
    public String toString() {
        return switch (this) {
            case CITY     -> "City";
            case BEACH    -> "Beach";
            case MOUNTAIN -> "Mountain";
        };
    }
}