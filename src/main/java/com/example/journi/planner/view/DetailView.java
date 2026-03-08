package com.example.journi.planner.view;

import com.example.journi.planner.model.Destination;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.awt.Desktop;
import java.net.URI;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Displays a pop-up window with detailed information about a selected destination.
 *
 * Shows destination name, type, travel time, travel mode, best season, and description.
 * Includes buttons to open external links for Google search, weather info,
 * transport routes, and hotels if the destination is not a day trip.
 */
public class DetailView {
    private static final double SCENE_WIDTH = 600;
    private static final double SCENE_HEIGHT = 480;

    private static final Logger logger = Logger.getLogger(DetailView.class.getName());

    private final Destination destination;

    public DetailView(Destination destination) {
        this.destination = destination;
    }

    public void show(Stage stage) {
        VBox root = new VBox(15);
        root.setPadding(new Insets(30));

        // === Title with Google Search ===
        String searchQuery = destination.getName() + ", " + destination.getPlaceType();
        Hyperlink nameLink = new Hyperlink(destination.getName());
        nameLink.getStyleClass().add("detail-title");
        nameLink.setOnAction(e -> {
            try {
                Desktop.getDesktop().browse(new URI("https://www.google.com/search?q=" + searchQuery.replace(" ", "+")));
            } catch (Exception ex) {
                logger.log(Level.SEVERE, "Failed to open external link", ex);
            }
        });

        // === External Links ===
        Button weatherBtn = new Button("🌤 Weather Info");
        weatherBtn.setOnAction(e -> {
            try {
                String query = destination.getName().replace(" ", "+") + "+weather";
                Desktop.getDesktop().browse(new URI("https://www.google.com/search?q=" + query));
            } catch (Exception ex) {
                logger.log(Level.SEVERE, "Failed to open external link", ex);
            }
        });

        Button transportBtn = new Button("🚆 Transport Info");
        transportBtn.setOnAction(e -> {
            try {
                Desktop.getDesktop().browse(new URI("https://www.google.com/search?q=Sheffield+to+" + destination.getName().replace(" ", "+")));
            } catch (Exception ex) {
                logger.log(Level.SEVERE, "Failed to open external link", ex);
            }
        });

        // === Optional Hotel Button ===
        if (!destination.isDayTrip()) {
            Button hotelBtn = new Button("🏨 Find Hotels Nearby");
            hotelBtn.setOnAction(e -> {
                try {
                    String query = destination.getName().replace(" ", "+") + "+hotel";
                    Desktop.getDesktop().browse(new URI("https://www.google.com/search?q=" + query));
                } catch (Exception ex) {
                    logger.log(Level.SEVERE, "Failed to open external link", ex);
                }
            });
            root.getChildren().add(hotelBtn);
        }

        // === Basic Info ===
        Label type = new Label("Type: " + destination.getPlaceType());
        Label season = new Label("Best Season: " + destination.getBestSeason());
        Label duration = new Label("Travel Time: %.1f hours".formatted(destination.getTravelTime()));
        Label mode = new Label("Travel Mode: " + destination.getTravelMode());

        // === Description Section ===
        Label descHeader = new Label("About This Destination:");
        descHeader.getStyleClass().add("section-title");

        Label desc = new Label(destination.getDescription());
        desc.setWrapText(true);

        // === Assemble Layout ===
        root.getChildren().addAll(
                nameLink,
                weatherBtn,
                transportBtn,
                type,
                season,
                duration,
                mode,
                descHeader,
                desc
        );

        Scene scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/css/detail.css")).toExternalForm());

        stage.setScene(scene);
        stage.setTitle(destination.getName() + " - Details");
        stage.setResizable(false);
        stage.show();
    }
}