package com.example.journi.loginSystem.model;

import java.io.*;
import java.nio.file.*;
import java.util.*;

/**
 * This is a class to manage 'loading', 'validating',
 * and 'saving' user accounts to a text file.
 *
 * All the accounts are stored using a HashMap and persist new accounts
 * to a CSV format.
 *
 * It is used by AuthController to handle login and sign-up
 */
public class AccountRepository {
    private final Path path;
    private final Map<String, Account> accounts = new HashMap<>();

    // Constructs the repository and loads accounts from a given file path.
    public AccountRepository(String filePath) throws IOException {
        this.path = Paths.get(filePath);
        loadAccounts();
    }

    // Loads account data from the file into memory.
    private void loadAccounts() throws IOException {
        if (!Files.exists(path)) return;

        List<String> lines = Files.readAllLines(path);
        for (String line : lines) {
            Account account = Account.fromFileString(line);
            if (account != null) {
                accounts.put(account.getUsername(), account);
            }
        }
    }

    // Checks if a username already exists in the repository.
    public boolean usernameExists(String username) {
        return accounts.containsKey(username);
    }

    // Validates the provided credentials.
    public boolean validate(String username, String password) {
        Account acc = accounts.get(username);
        return acc != null && acc.getPassword().equals(password);
    }

    /**
     * Saves a new account to the repository and appends it to the file.
     *
     * @param account the new account to save
     * @throws IOException if writing to the file fails
     * @throws IllegalArgumentException if the username already exists
     */
    public void saveAccount(Account account) throws IOException {
        if (usernameExists(account.getUsername()))
            throw new IllegalArgumentException("Username already exists");

        accounts.put(account.getUsername(), account);
        Files.write(path, Collections.singletonList(account.toFileString()),
                StandardOpenOption.CREATE, StandardOpenOption.APPEND);
    }
}