package test.loginSystem;

import com.example.journi.loginSystem.model.Account;
import com.example.journi.loginSystem.model.AccountRepository;

import java.io.File;
import java.io.IOException;

/**
 * A integration tester for the AccountRepository class.
 * Tests:
 * - Saving new accounts to a file
 * - Checking if usernames exist
 * - Validating correct and incorrect login credentials
 * - Handling of duplicate account creation
 * - Temporary file I/O simulation for testing persistence
 */
public class AccountRepositoryTester {

    public static void main(String[] args) {
        try {
            // === Create temporary file for test ===
            File tempFile = File.createTempFile("accounts_test", ".txt");
            String testFilePath = tempFile.getAbsolutePath();
            System.out.println("Testing with temporary file: " + testFilePath);

            // === Initialize repository ===
            AccountRepository repo = new AccountRepository(testFilePath);

            // Test 1: Save a new account
            Account newAcc = new Account("tester1", "pass123", "Test", "User");
            repo.saveAccount(newAcc);
            System.out.println("Test 1 - Save account: PASS");

            // Test 2: Check if username exists
            boolean exists = repo.usernameExists("tester1");
            System.out.println("Test 2 - Username exists: " + (exists ? "PASS" : "FAIL"));

            // Test 3: Validate correct login
            boolean loginSuccess = repo.validate("tester1", "pass123");
            System.out.println("Test 3 - Login with correct password: " + (loginSuccess ? "PASS" : "FAIL"));

            // Test 4: Validate incorrect login
            boolean loginFail = repo.validate("tester1", "wrongpass");
            System.out.println("Test 4 - Login with wrong password: " + (!loginFail ? "PASS" : "FAIL"));

            // Test 5: Duplicate account save should fail
            boolean duplicateTestPassed = false;
            try {
                repo.saveAccount(newAcc);
                System.out.println("Test 5 - Save duplicate: FAIL (no error thrown)");
            } catch (IllegalArgumentException e) {
                System.out.println("Test 5 - Save duplicate: PASS (error caught)");
                duplicateTestPassed = true;
            }

            // Test 6: Clean up the temporary file
            boolean deleted = tempFile.delete();
            System.out.println("Test 6 - Temp file deleted: " + (deleted ? "PASS" : "FAIL"));

        } catch (IOException e) {
            System.err.println("IOException during test: " + e.getMessage());
        }
    }
}