package com.example.journi.loginSystem.view;

import com.example.journi.Home;
import com.example.journi.loginSystem.controller.AuthController;
import com.example.journi.loginSystem.model.AccountRepository;
import com.example.journi.planner.view.SearchView;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;
import java.util.logging.Logger;

/**
 * Handles the sign-in interface and user authentication logic.
 *
 * Displays a staged sign-in form where the password field appears only
 * after a valid username is entered. Validates credentials through AuthController.
 *
 * Navigates to the main search view upon successful login
 * and displays appropriate messages for success or failure.
 */
public class SignInView {
    private static final double SCENE_WIDTH = 900;
    private static final double SCENE_HEIGHT = 640;
    private static final Logger LOGGER = Logger.getLogger(SignInView.class.getName());

    private final AuthController authController;

    // This initialises the AuthController with the account repository
    public SignInView() throws IOException {
        AccountRepository repo = new AccountRepository("src/main/resources/data/accounts.txt");
        this.authController = new AuthController(repo);
    }

    public void show(Stage stage) {
        Font.loadFont(getClass().getResourceAsStream("/fonts/RacingSansOne-Regular.ttf"), 40);

        // === Labels ===
        Label titleLabel = new Label("Journi Account");
        titleLabel.setStyle("-fx-font-size: 40px; -fx-font-weight: bold;");

        Label subLabel = new Label("Manage your Journi Account");
        subLabel.setStyle("-fx-font-size: 18px;");

        // === Input field ===
        TextField userIDField = new TextField();
        userIDField.setPromptText("Username");
        userIDField.setMaxWidth(300);

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");
        passwordField.setMaxWidth(300);
        passwordField.setVisible(false);

        // === Sign in  ===
        Button signIn = new Button("Continue");
        signIn.getStyleClass().add("continue-btn");

        Label messageLabel = new Label();
        messageLabel.setStyle("-fx-font-size: 16px;");

        Button backBtn = new Button("Back");
        backBtn.getStyleClass().add("back-btn");
        backBtn.setOnAction(e -> Home.show(stage));

        HBox buttonBox = new HBox(10, backBtn, signIn);
        buttonBox.setAlignment(Pos.CENTER);

        VBox root = new VBox(20, titleLabel, subLabel, userIDField, passwordField, buttonBox, messageLabel);        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-background-color: white;");

        Scene scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/css/signIn.css")).toExternalForm());
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Sign In");
        stage.show();

        // === Event Handler ===

        // This is design to be only reveal the password field
        // if the username field is entered
        final Runnable passwordReveal = () -> {
            String userID = userIDField.getText().trim();
            if (userID.isEmpty()) {
                messageLabel.setText("Please enter a username.");
                messageLabel.setStyle("-fx-text-fill: red;");
                return;
            }
            // This reveals the password field
            passwordField.setVisible(true);
            passwordField.requestFocus();
            signIn.setText("Sign In");
            messageLabel.setText("");
        };

        // This check whether the sign in credential
        // is valid or not
        final Runnable tryLogin = () -> {
            String userID = userIDField.getText().trim();
            String password = passwordField.getText();
            // Use the validation method in the authController
            if (authController.checkLogin(userID, password)) {
                messageLabel.setText("Login Successful!");
                messageLabel.setStyle("-fx-text-fill: green;");
                try {
                    new SearchView().show(stage);
                } catch (Exception ex) {
                    LOGGER.severe("Failed to load result screen: " + ex.getMessage());
                    messageLabel.setText("Error loading next screen.");
                }
            } else {
                messageLabel.setText("Invalid credentials. Please try again.");
                messageLabel.setStyle("-fx-text-fill: red;");
                passwordField.clear();
                passwordField.requestFocus();
            }
        };

        signIn.setOnAction(e -> {
            if (!passwordField.isVisible()) {
                passwordReveal.run();
            } else {
                tryLogin.run();
            }
        });

        // User can also use the ENTER key to continue instead of using the button
        userIDField.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER && !passwordField.isVisible()) {
                passwordReveal.run();
            }
        });

        passwordField.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER && passwordField.isVisible()) {
                tryLogin.run();
            }
        });
    }
}