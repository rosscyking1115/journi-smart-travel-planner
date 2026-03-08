package test.planner;

import com.example.journi.planner.model.LocationInfo;

/**
 * A unit tester for the LocationInfo class.
 * Tests:
 * - Constructor and field access through getters
 * - Full name generation using getFullName()
 * - Display output using displayInfo()
 */
public class LocationInfoTester {

    public static void main(String[] args) {
        System.out.println("=== LocationInfo Tester ===");

        LocationInfo loc = new LocationInfo("York", "North Yorkshire", "England");

        // Test 1: getName
        System.out.println("Test 1 - getName: " + ("York".equals(loc.getName()) ? "PASS" : "FAIL"));

        // Test 2: getRegion
        System.out.println("Test 2 - getRegion: " + ("North Yorkshire".equals(loc.getRegion()) ? "PASS" : "FAIL"));

        // Test 3: getCounty
        System.out.println("Test 3 - getCounty: " + ("England".equals(loc.getCounty()) ? "PASS" : "FAIL"));

        // Test 4: getFullName
        String expected = "York, North Yorkshire, England";
        System.out.println("Test 4 - getFullName: " + (expected.equals(loc.getLocationSummary()) ? "PASS" : "FAIL"));

        // Test 5: displayInfo() output
        System.out.print("Test 5 - displayInfo(): ");
        loc.displayInfo();  // manual inspection expected
    }
}