package com.example.journi.loginSystem.controller;

import com.example.journi.loginSystem.model.Account;
import com.example.journi.loginSystem.model.AccountRepository;
import java.io.IOException;

/**
 * This is a controller class to manage user authentication
 * It handles sign-in validation, username checks, and account creation
 */
public class AuthController {
    private final AccountRepository repo;

    public AuthController(AccountRepository repo) {
        this.repo = repo;
    }

    //Checks if the input username already exists in the repository
    public boolean isExists(String username) {
        return repo.usernameExists(username);
    }

    //Check the login credentials
    public boolean checkLogin(String username, String password) {
        return repo.validate(username, password);
    }

    // Registers a new user account
    public void signUp(String username, String password, String firstName, String lastName) throws IOException {
        Account acc = new Account(username, password, firstName, lastName);
        repo.saveAccount(acc);
    }
}