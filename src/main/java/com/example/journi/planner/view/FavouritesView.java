package com.example.journi.planner.view;

import com.example.journi.planner.model.Destination;
import com.example.journi.planner.model.FavouritesManager;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Displays the list of destinations marked as favourites by the user.
 *
 * Uses FavouritesManager to retrieve favourite destinations and shows them as cards
 * inside a scrollable view. Automatically refreshes the list when items are updated.
 */
public class FavouritesView {
    public void show(Stage stage) {
        // === Container for Cards ===
        VBox cards = new VBox(20);
        cards.setPadding(new Insets(30));
        cards.setAlignment(Pos.TOP_CENTER);

        // === Scrollable Scene Setup ===
        Scene scene = new Scene(new ScrollPane(cards), 800, 600);
        stage.setScene(scene);
        stage.setTitle("Your Favourites");

        // === Refresh Logic ===
        // Use an array to hold a mutable lambda reference for refreshing the view
        Runnable[] refresh = new Runnable[1];
        refresh[0] = () -> {
            cards.getChildren().clear();
            for (Destination d : FavouritesManager.getFavourites()) {
                // Reuse ResultView’s createCard with a refresh callback
                cards.getChildren().add(new ResultView().createCard(d, refresh[0]));
            }
        };
        refresh[0].run();

        stage.show();
    }
}