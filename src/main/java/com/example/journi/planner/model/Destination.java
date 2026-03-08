package com.example.journi.planner.model;

/**
 * Represents a travel destination with detailed information.
 *
 * Inherits from LocationInfo and implements DayTripPossible, Favourites, and Sortable.
 *
 * Each Destination has an ID, name, place type (e.g. CITY, BEACH, MOUNTAIN),
 * travel time in hours, travel mode, best season to visit, and a description.
 *
 * Also includes logic for:
 * - Checking if it is a suitable day trip (travel time * 2 ≤ 6 hours)
 * - Toggling and tracking favourite status
 * - Returning travel time in minutes for sorting
 */
public class Destination extends LocationInfo implements DayTripPossible, Favourites, Sortable {
    private final int id;
    private final String placeType;
    private final double travelTime;
    private final String travelMode;
    private final String bestSeason;
    private final String description;
    private boolean isFavourited = false;

    public Destination(int id, String name, String region, String country, String placeType,
                       double travelTime, String travelMode, String bestSeason, String description) {
        super(name, region, country);
        this.id = id;
        this.placeType = placeType;
        this.travelTime = travelTime;
        this.travelMode = travelMode;
        this.bestSeason = bestSeason;
        this.description = description;
    }

    public Destination(int id, String name, String placeType,
                       double travelTime, String travelMode, String bestSeason, String description) {
        super(name, "None", "None");
        this.id = id;
        this.placeType = placeType;
        this.travelTime = travelTime;
        this.travelMode = travelMode;
        this.bestSeason = bestSeason;
        this.description = description;
    }

    public int getId() { return id; }
    public String getPlaceType() { return placeType; }
    public double getTravelTime() { return travelTime; }
    public String getTravelMode() { return travelMode; }
    public String getBestSeason() { return bestSeason; }
    public String getDescription() { return description; }

    @Override
    public String toString() {
        return id + ": " + name + " [" + placeType + "] - " + travelTime + "h via " + travelMode + " (" + bestSeason + ") " + description;
    }

    // Consider if it is a day trip possible by calculating the return time
    // if return time, which is travel time * 2, is longer than 6 hours, it will be considered impossible
    @Override
    public boolean isDayTrip() {
        return travelTime * 2 <= 6;
    }

    @Override
    public void favourites() {
        isFavourited = !isFavourited;
    }

    @Override
    public boolean isAdd() {
        return isFavourited;
    }

    // Convert to minutes
    @Override
    public int getTravelTimeValue() {
        return (int) Math.round(travelTime * 60);
    }
}