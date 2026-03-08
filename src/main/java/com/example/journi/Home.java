package com.example.journi;

import com.example.journi.loginSystem.view.SignInView;
import com.example.journi.loginSystem.view.SignUpView;
import com.example.journi.planner.view.SearchView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.scene.text.Font;

import java.util.Objects;

/**
 * The entry point and main homepage of the Journi application.
 *
 * Displays a welcoming interface with options to sign in, sign up, or start as a guest.
 *
 * Loads custom fonts and provides navigation to the sign-in, sign-up, and search views.
 * Acts as the launch screen when the application starts.
 */
public class Home extends Application {
    private static final double SCENE_WIDTH = 1280;
    private static final double SCENE_HEIGHT = 832;

    public static void main(String[] args) {
        launch(args);
    }

    // This is the entry point to initiate the program
    @Override
    public void start(Stage homeStage) throws Exception {
        // Fonts are loaded here to ensure the font style is correctly being applied in the UI
        Font.loadFont(getClass().getResourceAsStream("/fonts/Limelight-Regular.ttf"), 40);
        Font.loadFont(getClass().getResourceAsStream("/fonts/sansita-one.regular.ttf"), 40);
        Font.loadFont(getClass().getResourceAsStream("/fonts/RacingSansOne-Regular.ttf"), 40);
        // Show the homepage when the program is first being launched
        show(homeStage);
    }

    public static void show(Stage stage) {

        // === Text ===
        Text logo = new Text("Journi");
        logo.getStyleClass().add("logo");
        logo.setX(50);
        logo.setY(70);

        Text slogan = new Text("Journi with You Every Step of the Way");
        slogan.getStyleClass().add("slogan");
        slogan.setWrappingWidth(470);
        slogan.setTextAlignment(TextAlignment.CENTER);
        slogan.setX(400);
        slogan.setY(350);


        // === Sign up button ===
        Button signUp = new Button("Sign Up");
        signUp.getStyleClass().add("signUp-btn");
        signUp.setLayoutX(1000);
        signUp.setLayoutY(35);
        signUp.setOnAction(e -> {
            try {
                SignUpView view = new SignUpView();
                view.show(stage);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });

        // === Sign in button ===
        Button signIn = new Button("Sign In");
        signIn.getStyleClass().add("signIn-btn");
        signIn.setLayoutX(1120);
        signIn.setLayoutY(32);
        signIn.setOnAction(e -> {
            try {
                SignInView view = new SignInView();
                view.show(stage);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });

        // === Start button ===
        Button start = new Button("Start your journi");
        start.getStyleClass().add("start-btn");
        start.setLayoutX(520);
        start.setLayoutY(630);
        start.setOnAction(e -> {
            try {
                SearchView view = new SearchView();
                view.show(stage);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });

        // === Layout ===
        Pane root = new Pane(logo, slogan, signUp, signIn, start);
        root.getStyleClass().add("home-root");

        Scene scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);
        scene.getStylesheets().add(Objects.requireNonNull(Home.class.getResource("/css/home.css")).toExternalForm());
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Home");
        stage.show();
    }
}