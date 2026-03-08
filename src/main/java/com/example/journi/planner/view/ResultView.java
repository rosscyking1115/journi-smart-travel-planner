package com.example.journi.planner.view;

import com.example.journi.Home;
import com.example.journi.planner.model.*;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.*;

/**
 * Displays the result screen with a list of destinations matching user preferences.
 *
 * Supports filtering by place type and travel time, sorting by travel duration,
 * toggling location info display, and marking destinations as favourites.
 *
 * Allows users to open detailed views or access their favourites from the result page.
 */
public class ResultView {
    private boolean showLocation = false;  // default state for location info toggle
    private boolean sortAscending = true;  // default sort order

    // === Data Structures ===

    private final DestinationReader reader = new DestinationReader();
    // A map that stores location info by destination ID for a quick lookup (O(1) time).
    private final Map<Integer, AbstractLocation> locationMap = reader.getLocationMap();
    // Stores all destinations matching current filters; keeps order and allows sorting.
    private List<Destination> matches = new ArrayList<>();

    private static final double SCENE_WIDTH  = 1512;
    private static final double SCENE_HEIGHT = 982;

    /**
     * Shows the full result screen based on filters.
     * @param stage the window to display in
     * @param typeFilter user-selected place type (can be null)
     * @param timeFilter user-selected travel time range (can be null)
     */
    public void show(Stage stage,
                     PlaceType typeFilter,
                     TravelTime timeFilter) {

        // === Load & Filter Data ===
        matches = new ArrayList<>(reader.getDestinations().stream()
                .filter(d -> typeFilter == null || d.getPlaceType().equals(typeFilter.toString()))
                .filter(d -> timeFilter == null || timeFilter.matches(d.getTravelTime()))
                .toList());

        // === Result Cards ===
        VBox cards = new VBox(20);
        cards.setPadding(new Insets(30));
        cards.setAlignment(Pos.TOP_CENTER);

        if (matches.isEmpty()) {
            cards.getChildren().add(new Text("No destinations are found."));
        } else {
            for (Destination d : matches) cards.getChildren().add(createCard(d, null));
        }

        // === Scrollable Area ===
        ScrollPane scroll = new ScrollPane(cards);
        scroll.setFitToWidth(true);
        scroll.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");

        // === Top Bar Background ===
        Pane backgroundBar = new Pane();
        backgroundBar.setPrefHeight(80);
        backgroundBar.setStyle("-fx-background-color: #2D2D2D;");

        // === Back Button ===
        Button back = new Button("◀  Back");
        back.getStyleClass().add("back-btn");
        back.setOnAction(e -> {
            try {
                new SearchView().show(stage);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
        StackPane.setAlignment(back, Pos.TOP_RIGHT);
        StackPane.setMargin(back, new Insets(22, 20, 20, 20));

        // === Logo ===
        Label logoBlock = new Label("Journi");
        logoBlock.getStyleClass().add("logo-block");
        StackPane.setAlignment(logoBlock, Pos.CENTER_LEFT);
        StackPane.setMargin(logoBlock, new Insets(0, 0, 0, 60));

        // === Filter Summary Label ===
        Label filterSummary = new Label("Place Type: %s     Travel Time: %s"
                .formatted(typeFilter != null ? typeFilter : "Any",
                        timeFilter != null ? timeFilter : "Any"));
        filterSummary.getStyleClass().add("filter-summary");
        StackPane.setAlignment(filterSummary, Pos.CENTER);

        // === Favourites Button ===
        Button favButton = new Button("❤ Your Favourites");
        favButton.getStyleClass().add("favourites-btn");
        favButton.setOnAction(e -> new FavouritesView().show(new Stage()));
        StackPane.setAlignment(favButton, Pos.TOP_LEFT);
        StackPane.setMargin(favButton, new Insets(22, 0, 20, 1020));

        // === Location Toggle Button ===
        Button locationBtn = new Button("📍 Show Location Info");
        locationBtn.getStyleClass().add("location-btn");
        locationBtn.setOnAction(e -> {
            showLocation = !showLocation;
            locationBtn.setText(showLocation ? "📍 Hide Location Info" : "📍 Show Location Info");

            // Rebuild result cards with or without location info
            cards.getChildren().clear();
            for (Destination d : matches) {
                cards.getChildren().add(createCard(d, null));
            }
        });

        // === Sort Toggle Button ===
        Button sortBtn = new Button("Sort by Time ↑");
        sortBtn.getStyleClass().add("sort-btn");
        sortBtn.setOnAction(e -> {
            sortAscending = !sortAscending;

            // Anonymous Comparator class
            matches.sort(new Comparator<Destination>() {
                @Override
                public int compare(Destination d1, Destination d2) {
                    return sortAscending
                            ? Double.compare(d1.getTravelTime(), d2.getTravelTime())
                            : Double.compare(d2.getTravelTime(), d1.getTravelTime());
                }
            });

            sortBtn.setText(sortAscending ? "Sort by Time ↑" : "Sort by Time ↓");

            // Rebuild cards after sorting
            cards.getChildren().clear();
            for (Destination d : matches) {
                cards.getChildren().add(createCard(d, null));
            }
        });

        // === Top Control Bar (Sort + Location Toggle) ===
        BorderPane resultBox = new BorderPane();
        resultBox.setLeft(locationBtn);
        resultBox.setRight(sortBtn);
        resultBox.setPadding(new Insets(20, 30, 10, 30));

        // === Compose Full TopBar ===
        StackPane topBar = new StackPane(backgroundBar, logoBlock, filterSummary, back, favButton);

        // === Combine Layout ===
        VBox root = new VBox(topBar, resultBox, scroll);
        root.setSpacing(10);
        root.getStyleClass().add("result-root");

        // === Final Scene Setup ===
        Scene scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);
        scene.getStylesheets().add(
                Objects.requireNonNull(Home.class.getResource("/css/result.css")).toExternalForm());

        stage.setScene(scene);
        stage.setTitle("Result");
        stage.show();
    }

    /**
     * Creates a destination card displaying basic info, trip eligibility, and a heart button.
     */
    public Pane createCard(Destination d, Runnable refreshCallback) {
        // === Text ===
        Text name = new Text(d.getName());
        name.getStyleClass().add("card-title");

        Text meta = new Text("%s • %.1f h • %s • %s"
                .formatted(d.getPlaceType(), d.getTravelTime(), d.getTravelMode(), d.getBestSeason()));
        meta.getStyleClass().add("card-info");

        Text tripStatus = new Text(d.isDayTrip() ? "✓ Day Trip Eligible" : "✘ Not a Day Trip");
        tripStatus.getStyleClass().add(d.isDayTrip() ? "status-yes" : "status-no");

        // === Heart Button ===
        Button heartBtn = new Button(d.isAdd() ? "❤" : "♡");
        heartBtn.setOnAction(e -> {
            FavouritesManager.toggleFavourite(d);
            heartBtn.setText(d.isAdd() ? "❤" : "♡");

            // Refresh the view if removed from favourites
            if (!d.isAdd() && refreshCallback != null) {
                refreshCallback.run();
            }
        });

        // === Layout ===
        HBox topRow = new HBox(10, name, heartBtn);
        topRow.setAlignment(Pos.CENTER_LEFT);

        VBox card = new VBox(6, topRow, meta, tripStatus);

        // === Optional Location Info ===
        if (showLocation) {
            AbstractLocation loc = locationMap.get(d.getId());
            String fullName = (loc != null) ? loc.getLocationSummary() : "Location info unavailable";
            Text locationText = new Text("📍 " + fullName);
            locationText.getStyleClass().add("location-info");
            card.getChildren().add(locationText);
        }

        // === Style & Click Behavior ===
        card.getStyleClass().add("card");
        card.setPadding(new Insets(15));
        card.setOnMouseClicked(e -> new DetailView(d).show(new Stage()));

        return card;
    }
}