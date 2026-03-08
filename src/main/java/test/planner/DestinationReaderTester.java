package test.planner;

import com.example.journi.planner.model.AbstractLocation;
import com.example.journi.planner.model.Destination;
import com.example.journi.planner.model.DestinationReader;

import java.util.List;
import java.util.Map;


/**
 * An integration tester for the DestinationReader class.
 * Tests the integration of file reading and object creation:
 * - Loads destination and location info from resources
 * - Verifies destination list and location map are populated correctly
 * - Checks if each destination is linked with the correct LocationInfo
 * - Uses getters to verify correct data mapping and formatting
 */
public class DestinationReaderTester {

    public static void main(String[] args) {
        System.out.println("=== DestinationReader Tester ===");

        DestinationReader reader = new DestinationReader();

        // Test 1: Check if the destination list is not empty
        List<Destination> destinations = reader.getDestinations();
        System.out.println("Test 1 - Destinations loaded: " + (!destinations.isEmpty() ? "PASS" : "FAIL"));

        // Test 2: Check if the location map is not empty
        Map<Integer, AbstractLocation> locationMap = reader.getLocationMap();
        System.out.println("Test 2 - Location info loaded: " + (!locationMap.isEmpty() ? "PASS" : "FAIL"));

        // Test 3: Print size of list and map
        System.out.println("Test 3 - List size: " + destinations.size());
        System.out.println("Test 4 - Map size: " + locationMap.size());

        // Test 5: Check first destination entry (if exists)
        if (!destinations.isEmpty()) {
            Destination d = destinations.getFirst();
            System.out.println("Test 5 - First destination:");
            System.out.println("  ID: " + d.getId());
            System.out.println("  Name: " + d.getName());
            System.out.println("  Region: " + d.getRegion());
            System.out.println("  Country: " + d.getCounty());
            System.out.println("  Place Type: " + d.getPlaceType());
            System.out.println("  Travel Time: " + d.getTravelTime());
            System.out.println("  Travel Mode: " + d.getTravelMode());
            System.out.println("  Best Season: " + d.getBestSeason());
            System.out.println("  Description: " + d.getDescription());
        } else {
            System.out.println("Test 5 - First destination: SKIPPED (no data)");
        }

        // Optional: Check if all destinations are matched with the location map
        boolean allMatched = destinations.stream().allMatch(d ->
                locationMap.containsKey(d.getId())
                        || (d.getRegion().equals("Unknown") && d.getCounty().equals("Unknown"))
                        || (d.getRegion().equals("None") && d.getCounty().equals("None"))
        );
        System.out.println("Test 6 - All destinations matched with location info: " + (allMatched ? "PASS" : "FAIL"));
    }
}