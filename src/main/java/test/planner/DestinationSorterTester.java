package test.planner;

import com.example.journi.planner.model.Destination;
import com.example.journi.planner.model.DestinationSorter;

import java.util.ArrayList;
import java.util.List;

/**
 * A unit tester for the DestinationSorter class.
 * Tests the custom QuickSort implementation:
 * - Sorts a list of Destination objects by travel time (in minutes)
 * - Verifies that sorting occurs in ascending order
 * - Includes before and after state printing for validation
 */
public class DestinationSorterTester {

    public static void main(String[] args) {
        System.out.println("=== DestinationSorter Tester ===");

        List<Destination> testList = new ArrayList<>();

        testList.add(new Destination(3, "City C", "CITY", 3.0, "Train", "Winter", "Description C"));
        testList.add(new Destination(1, "City A", "BEACH", 1.0, "Car", "Summer", "Description A"));
        testList.add(new Destination(4, "City D", "MOUNTAIN", 4.5, "Bus", "Spring", "Description D"));
        testList.add(new Destination(2, "City B", "CITY", 2.0, "Bike", "Autumn", "Description B"));

        System.out.println("\nBefore sorting:");
        for (Destination d : testList) {
            System.out.println(d.getName() + " - " + d.getTravelTimeValue() + " min");
        }

        DestinationSorter.quickSort(testList);

        System.out.println("\nAfter sorting:");
        for (Destination d : testList) {
            System.out.println(d.getName() + " - " + d.getTravelTimeValue() + " min");
        }

        // Test: Ensure sorted
        boolean sorted = true;
        for (int i = 0; i < testList.size() - 1; i++) {
            if (testList.get(i).getTravelTimeValue() > testList.get(i + 1).getTravelTimeValue()) {
                sorted = false;
                break;
            }
        }

        System.out.println("\nTest - Sorted in Ascending Order: " + (sorted ? "PASS" : "FAIL"));
    }
}