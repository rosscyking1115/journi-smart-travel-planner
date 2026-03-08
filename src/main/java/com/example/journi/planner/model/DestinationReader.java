package com.example.journi.planner.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * Reads destination and location information from external text files.
 *
 * Loads and parses data from destinations.txt and locationinfo.txt files
 * located in the /resources/data/ directory.
 *
 * Creates Destination objects using data from both files,
 * linking each destination with its corresponding location info.
 *
 * Provides access to the full list of Destination objects and
 * a map of LocationInfo objects indexed by their ID.
 *
 * destinations.txt format:
 * id, name, placeType, travelTime (hrs), travelMode, bestSeason, description
 *
 * locationinfo.txt format:
 * id, locationName, region, country
 */
public class DestinationReader {
    // Collection, List is used to store destination detail
    private final List<Destination> desList = new ArrayList<>();
    // Collection, Map is used so that can easily find the corresponding destination location info
    // by searching the id
    private final Map<Integer, AbstractLocation> locationMap = new HashMap<>();

    public DestinationReader() {
        loadLocationInfo();

        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(
                        Objects.requireNonNull(getClass().getResourceAsStream("/data/destinations.txt")),
                        StandardCharsets.UTF_8))) {

            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",", 7);
                if (parts.length == 7) {
                    int id = Integer.parseInt(parts[0].trim());
                    String name = parts[1].trim();
                    String placeType = parts[2].trim();
                    double travelTime = Double.parseDouble(parts[3].trim());
                    String travelMode = parts[4].trim();
                    String bestSeason = parts[5].trim();
                    String description = parts[6].trim();

                    AbstractLocation loc = locationMap.get(id);
                    if (loc == null) continue;
                    Destination d = new Destination(id, name, loc.getRegion(), loc.getCounty(),
                            placeType, travelTime, travelMode, bestSeason, description);

                    desList.add(d);
                }
            }
        } catch (IOException | NullPointerException e) {
            System.out.println("Error reading destinations.txt: " + e.getMessage());
        }
    }

    private void loadLocationInfo() {
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(
                        Objects.requireNonNull(getClass().getResourceAsStream("/data/locationinfo.txt")),
                        StandardCharsets.UTF_8))) {

            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",", 4);
                if (parts.length == 4) {
                    int id = Integer.parseInt(parts[0].trim());
                    String name = parts[1].trim();
                    String region = parts[2].trim();
                    String country = parts[3].trim();

                    locationMap.put(id, new LocationInfo(name, region, country));
                }
            }
        } catch (IOException | NullPointerException e) {
            System.out.println("Error reading locationinfo.txt: " + e.getMessage());
        }
    }

    public List<Destination> getDestinations() {
        return desList;
    }

    public Map<Integer, AbstractLocation> getLocationMap() {
        return locationMap;
    }
}