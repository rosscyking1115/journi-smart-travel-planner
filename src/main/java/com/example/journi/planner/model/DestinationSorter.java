package com.example.journi.planner.model;

import java.util.List;

/**
 * Provides sorting functionality for a list of Destination objects.
 *
 * Uses the QuickSort algorithm to sort destinations in ascending order
 * based on their travel time in minutes.
 *
 * The sorting is done in-place and uses getTravelTimeValue() as the key.
 */
public class DestinationSorter {

    public static void quickSort(List<Destination> list) {
        quickSort(list, 0, list.size() - 1);
    }

    private static void quickSort(List<Destination> list, int low, int high) {
        if (low < high) {
            int pvIndex = partition(list, low, high);
            quickSort(list, low, pvIndex - 1);
            quickSort(list, pvIndex + 1, high);
        }
    }

    private static void swap(List<Destination> list, int i, int j) {
        Destination temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }

    private static int partition(List<Destination> list, int low, int high) {
        int pivot = list.get(high).getTravelTimeValue();
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (list.get(j).getTravelTimeValue() <= pivot) {
                i++;
                swap(list, i, j);
            }
        }

        swap(list, i + 1, high);
        return i + 1;
    }
}