package com.example.journi.planner.model;

/**
 * An abstract class that get the information
 */
public abstract class AbstractLocation {
    protected final String name;

    public AbstractLocation(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract String getRegion();
    public abstract String getCounty();
    public abstract String getLocationSummary();
}