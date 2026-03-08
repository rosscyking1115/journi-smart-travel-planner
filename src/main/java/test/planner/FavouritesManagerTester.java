package test.planner;

import com.example.journi.planner.model.Destination;
import com.example.journi.planner.model.FavouritesManager;

import java.util.List;

/**
 * A unit tester for the FavouritesManager class.
 * Tests the logic of managing a static favourites list:
 * - Toggles destinations as favourites using toggleFavourite()
 * - Verifies correct addition and removal of destinations
 * - Ensures no duplicates are added
 * - Confirms the state of the favourite list reflects toggles accurately
 */
public class FavouritesManagerTester {

    public static void main(String[] args) {
        System.out.println("=== FavouritesManager Tester ===");

        Destination d1 = new Destination(1, "York", "CITY", 2.0, "Train", "Spring", "Historic city.");
        Destination d2 = new Destination(2, "Brighton", "BEACH", 3.5, "Car", "Summer", "Coastal town.");
        Destination d3 = new Destination(3, "Lake District", "MOUNTAIN", 4.0, "Bus", "Autumn", "Beautiful nature.");

        // Test 1: Initially empty
        List<Destination> favs = FavouritesManager.getFavourites();
        System.out.println("Test 1 - Initially empty: " + (favs.isEmpty() ? "PASS" : "FAIL"));

        // Test 2: Toggle d1 as favourite
        FavouritesManager.toggleFavourite(d1);
        favs = FavouritesManager.getFavourites();
        System.out.println("Test 2 - Add d1: " + (favs.contains(d1) ? "PASS" : "FAIL"));

        // Test 3: Add d2, now 2 in the list
        FavouritesManager.toggleFavourite(d2);
        favs = FavouritesManager.getFavourites();
        boolean bothExist = favs.contains(d1) && favs.contains(d2);
        System.out.println("Test 3 - Add d2: " + (bothExist && favs.size() == 2 ? "PASS" : "FAIL"));

        // Test 4: Toggle d1 again → should remove
        FavouritesManager.toggleFavourite(d1);
        favs = FavouritesManager.getFavourites();
        System.out.println("Test 4 - Remove d1: " + (!favs.contains(d1) && favs.contains(d2) ? "PASS" : "FAIL"));

        // Test 5: Toggle d3 ON then OFF quickly
        FavouritesManager.toggleFavourite(d3);
        FavouritesManager.toggleFavourite(d3);
        favs = FavouritesManager.getFavourites();
        System.out.println("Test 5 - Toggle d3 on/off: " + (!favs.contains(d3) ? "PASS" : "FAIL"));
    }
}