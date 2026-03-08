package com.example.journi.planner.view;

import com.example.journi.Home;
import com.example.journi.planner.model.PlaceType;
import com.example.journi.planner.model.TravelTime;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Objects;

/**
 * Displays the main search interface for the Journi application.
 *
 * Allows users to filter destinations by type and travel time,
 * then search for matching results.
 *
 * Acts as the core entry point after login and handles navigation to the results page.
 */
public class SearchView {

    final double SCENE_WIDTH = 1512;
    final double SCENE_HEIGHT = 982;
    final double NAV_HEIGHT = 500;

    public void show(Stage stage) throws Exception {

        // === Background banner ===
        ImageView topBanner = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/banner.jpg"))));
        topBanner.setFitWidth(SCENE_WIDTH);
        topBanner.setFitHeight(600);
        topBanner.setPreserveRatio(false);

        // === Title ===
        Text title = new Text("Journi – Smart Travel Planner");
        title.getStyleClass().add("title");

        // === Sign Out Button ===
        Button signOutBtn = new Button("Sign out");
        signOutBtn.getStyleClass().add("signOut-btn");
        signOutBtn.setOnAction(event -> {
            try {
                Home.show(stage);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });

        // === Top Bar Layout ===
        Pane topBar = new Pane();
        topBar.getChildren().addAll(title, signOutBtn);
        title.relocate(500, 50);
        signOutBtn.relocate(1300, 25);

        // === Slogan Text ===
        Text slogan = new Text("Find your dream destination");
        slogan.getStyleClass().add("slogan");
        slogan.setWrappingWidth(750);
        slogan.setTranslateX(-20);
        slogan.setTranslateY(150);

        VBox sloganBox = new VBox(slogan);
        sloganBox.setAlignment(Pos.CENTER_LEFT);
        sloganBox.setPadding(new Insets(100, 0, 20, 0));

        // === Filter: Destination Type ===
        ComboBox<PlaceType> destinationType = new ComboBox<>();
        destinationType.setPromptText("Destination type");
        destinationType.getItems().addAll(PlaceType.values());
        destinationType.getStyleClass().add("filter-box");

        // === Filter: Travel Time ===
        ComboBox<TravelTime> travelTime = new ComboBox<>();
        travelTime.setPromptText("Travel time");
        travelTime.getItems().addAll(TravelTime.values());
        travelTime.getStyleClass().add("filter-box");

        // === Search Button ===
        Button searchBtn = new Button("Search");
        searchBtn.getStyleClass().add("search-btn");
        searchBtn.setOnAction(e -> {
            if (destinationType.getValue() == null && travelTime.getValue() == null) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION,
                        "Please choose at least one filter.");
                alert.showAndWait();
                return;
            }
            try {
                new ResultView().show(stage,
                        destinationType.getValue(),
                        travelTime.getValue());
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });

        // === Filter Row Layout ===
        HBox filterRow = new HBox(30, destinationType, travelTime, searchBtn);
        filterRow.setAlignment(Pos.CENTER);

        VBox searchBox = new VBox(filterRow);
        searchBox.setAlignment(Pos.CENTER);
        searchBox.getStyleClass().add("search-box");

        // === Header Composition ===
        VBox overlayTop = new VBox(topBar, sloganBox);
        overlayTop.setAlignment(Pos.TOP_CENTER);
        overlayTop.setSpacing(20);

        StackPane header = new StackPane(topBanner, overlayTop, searchBox);
        header.setPrefHeight(NAV_HEIGHT);
        header.getStyleClass().add("header");

        // === Root Layout ===
        BorderPane root = new BorderPane();
        root.setTop(header);
        root.getStyleClass().add("search-root");

        Scene scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);
        scene.getStylesheets().add(Objects.requireNonNull(Home.class.getResource("/css/search.css")).toExternalForm());

        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Search");
        stage.show();
    }
}