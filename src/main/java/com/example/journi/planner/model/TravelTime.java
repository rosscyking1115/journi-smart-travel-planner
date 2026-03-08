package com.example.journi.planner.model;

/**
 * An enum represents a range of travel durations used for filtering destinations.
 *
 * Categorised into SHORT, MEDIUM, and LONG based on total travel time in minutes.
 * Each value includes a minimum and maximum time range and a display label.
 *
 * Provides a matches method to check if a given travel time in hours
 * fits within the range.
 */
public enum TravelTime {
    SHORT (0, 90, "30 min – 1 h 30"),
    MEDIUM (90, 240, "1 h 30 – 4 h"),
    LONG (240, Integer.MAX_VALUE, "4 h –");

    private final int minMinutes;
    private final int maxMinutes;
    private final String label;

    TravelTime(int min, int max, String label) {
        this.minMinutes = min;
        this.maxMinutes = max;
        this.label      = label;
    }

    public boolean matches(double hours) {
        int m = (int)(hours * 60);
        return m >= minMinutes && m < maxMinutes;
    }

    @Override
    public String toString() {
        return label;
    }
}