package com.example.journi.planner.model;


import java.util.ArrayList;
import java.util.List;

/**
 * Manages a static list of favourite Destination objects.
 *
 * Provides methods to toggle the favourite status of a destination
 * and retrieve the current list of favourites.
 *
 * Ensures no duplicates are added and handles removal when toggled off.
 */
public class FavouritesManager {
    private static final List<Destination> favourites = new ArrayList<>();

    public static void toggleFavourite(Destination d) {
        d.favourites();
        if (d.isAdd()) {
            if (!favourites.contains(d)) {
                favourites.add(d);
            }
        } else {
            favourites.remove(d);
        }
    }

    public static List<Destination> getFavourites() {
        return new ArrayList<>(favourites);
    }
}
