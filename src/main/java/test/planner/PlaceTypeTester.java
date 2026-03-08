package test.planner;

import com.example.journi.planner.model.PlaceType;

/**
 * A unit tester for the PlaceType enum.
 * Tests:
 * - Enum constants and overridden toString() method
 * - Value validation and human-readable label output
 */
public class PlaceTypeTester {

    public static void main(String[] args) {
        System.out.println("=== PlaceType Enum Tester ===");

        // Test 1: Iterate through all values
        for (PlaceType type : PlaceType.values()) {
            System.out.println("Enum name: " + type.name() + " | toString(): " + type);
        }

        // Test 2: Individual value check
        boolean testCity     = "City".equals(PlaceType.CITY.toString());
        boolean testBeach    = "Beach".equals(PlaceType.BEACH.toString());
        boolean testMountain = "Mountain".equals(PlaceType.MOUNTAIN.toString());

        System.out.println("\nTest 2 - toString override:");
        System.out.println("City: "     + (testCity     ? "PASS" : "FAIL"));
        System.out.println("Beach: "    + (testBeach    ? "PASS" : "FAIL"));
        System.out.println("Mountain: " + (testMountain ? "PASS" : "FAIL"));
    }
}