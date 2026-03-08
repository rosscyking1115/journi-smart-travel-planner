package com.example.journi.loginSystem.model;

/**
 * This is a class that holds basic account information,
 * including username, password, first name, and last name.
 *
 * It also provides methods for converting
 * the account to/from a CSV-compatible file format.
 *
 * Note: Passwords are stored in plain text for simplicity.
 */
public class Account {
    private String username;
    private String password;
    private String firstName;
    private String lastName;

    // === Constructor ===
    public Account(String username, String password, String firstName, String lastName) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    // === Getter ===
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }


    //  Converts the account object into a comma-separated string for txt storage.
    public String toFileString() {
        return String.join(",", username, password, firstName, lastName);
    }

    // Reads a comma-separated string into an Account object.
    public static Account fromFileString(String line) {
        String[] parts = line.split(",");
        if (parts.length != 4) return null;
        return new Account(parts[0], parts[1], parts[2], parts[3]);
    }

    @Override
    public String toString() {
        return String.format("%s (%s)", lastName, username);
    }
}