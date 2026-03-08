package test.planner;

import com.example.journi.planner.model.Destination;

/**
 * A unit tester for the Destination class.
 * Tests the construction, getter methods, and logic of:
 * - ID, place type, travel time, travel mode, best season, and description fields
 * - toString formatting
 * - isDayTrip() based on travel time
 * - Favourites toggle using favourites() and isAdd()
 * - Travel time conversion via getTravelTimeValue()
 */
public class DestinationTester {

    public static void main(String[] args) {
        System.out.println("=== Destination Class Tester ===");

        // Create sample destination
        Destination d = new Destination(
                1,
                "York",
                "North Yorkshire",
                "England",
                "CITY",
                2.5,
                "Train",
                "Spring",
                "A historic city with Roman roots."
        );

        // Test 1: Check ID
        System.out.println("Test 1 - ID: " + (d.getId() == 1 ? "PASS" : "FAIL"));

        // Test 2: Check Place Type
        System.out.println("Test 2 - PlaceType: " + ("CITY".equals(d.getPlaceType()) ? "PASS" : "FAIL"));

        // Test 3: Check Travel Time (double)
        System.out.println("Test 3 - TravelTime: " + (d.getTravelTime() == 2.5 ? "PASS" : "FAIL"));

        // Test 4: Check Travel Mode
        System.out.println("Test 4 - TravelMode: " + ("Train".equals(d.getTravelMode()) ? "PASS" : "FAIL"));

        // Test 5: Check Best Season
        System.out.println("Test 5 - BestSeason: " + ("Spring".equals(d.getBestSeason()) ? "PASS" : "FAIL"));

        // Test 6: Check Description
        System.out.println("Test 6 - Description: " + ("A historic city with Roman roots.".equals(d.getDescription()) ? "PASS" : "FAIL"));

        // Test 7: Day Trip Possibility (2.5 * 2 = 5.0 hours → should be true)
        System.out.println("Test 7 - isDayTrip: " + (d.isDayTrip() ? "PASS" : "FAIL"));

        // Test 8: Travel time in minutes (2.5 * 60 = 150)
        System.out.println("Test 8 - getTravelTimeValue: " + (d.getTravelTimeValue() == 150 ? "PASS" : "FAIL"));

        // Test 9: Favourite toggle
        d.favourites(); // should be true now
        System.out.println("Test 9 - Favourited ON: " + (d.isAdd() ? "PASS" : "FAIL"));

        d.favourites(); // toggle back to false
        System.out.println("Test 10 - Favourited OFF: " + (!d.isAdd() ? "PASS" : "FAIL"));

        // Test 11: toString contains expected keywords
        String result = d.toString();
        boolean valid = result.contains("York") && result.contains("CITY") && result.contains("Train") && result.contains("Spring");
        System.out.println("Test 11 - toString check: " + (valid ? "PASS" : "FAIL"));
    }
}