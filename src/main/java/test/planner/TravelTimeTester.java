package test.planner;

import com.example.journi.planner.model.TravelTime;

/**
 * A unit tester for the TravelTime enum.
 * Tests:
 * - Label formatting via toString()
 * - matches() method for correct categorisation based on travel time
 * - Boundary checks at enum threshold limits (e.g. 90 min, 240 min)
 */
public class TravelTimeTester {

    public static void main(String[] args) {
        System.out.println("=== TravelTime Enum Tester ===");

        // Test 1: toString() labels
        System.out.println("Test 1 - Labels:");
        System.out.println("SHORT:   " + ("30 min – 1 h 30".equals(TravelTime.SHORT.toString()) ? "PASS" : "FAIL"));
        System.out.println("MEDIUM:  " + ("1 h 30 – 4 h".equals(TravelTime.MEDIUM.toString()) ? "PASS" : "FAIL"));
        System.out.println("LONG:    " + ("4 h –".equals(TravelTime.LONG.toString()) ? "PASS" : "FAIL"));

        // Test 2: matches() method
        System.out.println("\nTest 2 - matches() hours:");
        System.out.println("1.0h (60min) → SHORT:    " + (TravelTime.SHORT.matches(1.0)     ? "PASS" : "FAIL"));
        System.out.println("1.5h (90min) → MEDIUM:   " + (TravelTime.MEDIUM.matches(1.5)    ? "PASS" : "FAIL"));
        System.out.println("3.0h (180min) → MEDIUM:  " + (TravelTime.MEDIUM.matches(3.0)    ? "PASS" : "FAIL"));
        System.out.println("4.0h (240min) → LONG:    " + (TravelTime.LONG.matches(4.0)      ? "PASS" : "FAIL"));
        System.out.println("0.2h (12min) → SHORT:    " + (TravelTime.SHORT.matches(0.2)     ? "PASS" : "FAIL"));

        // Test 3: boundary exclusions
        System.out.println("\nTest 3 - Boundary edge case:");
        System.out.println("90min → Not SHORT:       " + (!TravelTime.SHORT.matches(1.5)    ? "PASS" : "FAIL"));
        System.out.println("240min → Not MEDIUM:     " + (!TravelTime.MEDIUM.matches(4.0)   ? "PASS" : "FAIL"));
    }
}