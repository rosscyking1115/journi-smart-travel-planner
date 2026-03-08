package com.example.journi.loginSystem.view;

import com.example.journi.Home;
import com.example.journi.loginSystem.controller.AuthController;
import com.example.journi.loginSystem.model.AccountRepository;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;
import java.util.logging.Logger;

/**
 * Handles the sign-up interface and account registration logic.
 *
 * Displays a form for collecting new user details including first name,
 * last name, username, and password. Validates all fields before submitting.
 *
 * Uses AuthController to check if the username already exists and
 * to create a new account if valid. Returns to the home screen on success.
 */
public class SignUpView {
    private static final double SCENE_WIDTH = 900;
    private static final double SCENE_HEIGHT = 640;
    private static final Logger LOGGER = Logger.getLogger(SignUpView.class.getName());

    private final AuthController authController;

    // This initialises AuthController with the repository
    public SignUpView() throws IOException {
        this.authController = new AuthController(new AccountRepository("src/main/resources/data/accounts.txt"));
    }

    public void show(Stage stage) {
        // === Input field ===
        TextField firstNameField = new TextField();
        firstNameField.setPromptText("First Name");
        firstNameField.setMaxWidth(400);

        TextField lastNameField = new TextField();
        lastNameField.setPromptText("Last Name");
        lastNameField.setMaxWidth(400);

        TextField usernameField = new TextField();
        usernameField.setPromptText("Username (Email or ID)");
        usernameField.setMaxWidth(400);

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");
        passwordField.setMaxWidth(400);

        Label messageLabel = new Label();
        messageLabel.setStyle("-fx-text-fill: red;");

        Button signUp = new Button("Continue");
        signUp.getStyleClass().add("continue-btn");

        // === Sign-Up Logic ===
        signUp.setOnAction(event -> {
            String firstName = firstNameField.getText().trim();
            String lastName = lastNameField.getText().trim();
            String username = usernameField.getText().trim();
            String password = passwordField.getText();

            // Check all fields are filled
            if (firstName.isEmpty() || lastName.isEmpty() || username.isEmpty() || password.isEmpty()) {
                messageLabel.setText("Please fill in all fields.");
                return;
            }

            // Check if the username exists or register a new account
            try {
                if (authController.isExists(username)) {
                    messageLabel.setText("Username already exists.");
                } else {
                    authController.signUp(username, password, firstName, lastName);
                    messageLabel.setStyle("-fx-text-fill: green;");
                    messageLabel.setText("Account created successfully!");

                    Home.show(stage);
                }
            } catch (Exception e) {
                LOGGER.severe("Error creating account: " + e.getMessage());
                messageLabel.setText("Error creating account.");
            }
        });

        // === Back Button ===
        Button backBtn = new Button("Back");
        backBtn.getStyleClass().add("back-btn");
        backBtn.setOnAction(e -> Home.show(stage));

        HBox buttonBox = new HBox(10, backBtn, signUp);
        buttonBox.setAlignment(Pos.CENTER);

        VBox inputBox = new VBox(15,
                firstNameField,
                lastNameField,
                usernameField,
                passwordField,
                buttonBox,
                messageLabel
        );
        inputBox.setAlignment(Pos.CENTER);
        inputBox.setPrefWidth(400);

        // === Layout ===
        BorderPane root = new BorderPane(inputBox);
        root.setPrefSize(SCENE_WIDTH, SCENE_HEIGHT);
        BorderPane.setAlignment(inputBox, Pos.CENTER);

        Scene scene = new Scene(root);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/css/signUp.css")).toExternalForm());

        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Create Your Account");
        stage.show();
    }
}