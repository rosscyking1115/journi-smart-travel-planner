package com.example.journi.planner.model;

/**
 * Represents basic geographic information about a location.
 *
 * Stores the name, region, and county of a place.
 * Provides methods to access individual fields, display the full location name,
 * and print it in a readable format.
 */
public class LocationInfo extends AbstractLocation {
    protected String region;
    protected String county;

    public LocationInfo(String name, String region, String county) {
        super(name);
        this.region = region;
        this.county = county;
    }

    @Override
    public String getRegion() {
        return region;
    }

    @Override
    public String getCounty() {
        return county;
    }

    @Override
    public String getLocationSummary() {
        return "%s (%s, %s)".formatted(name, region, county);
    }

    public void displayInfo() {
        System.out.println("📍 " + getLocationSummary());
    }
}