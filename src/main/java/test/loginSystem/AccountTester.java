package test.loginSystem;

import com.example.journi.loginSystem.model.Account;

/**
 * A unit tester for the Account class.
 * Tests:
 * - Getter methods for all fields
 * - Account string conversion to file format (toFileString)
 * - Static reconstruction from CSV string (fromFileString)
 * - Overridden toString() output
 */
public class AccountTester {

    public static void main(String[] args) {
        System.out.println("=== Account Class Tester ===");

        // === Create an Account instance ===
        Account account = new Account("testUser", "1234", "Alice", "Smith");

        // Test 1: getUsername
        System.out.println("Test 1 - getUsername: " + ("testUser".equals(account.getUsername()) ? "PASS" : "FAIL"));

        // Test 2: getPassword
        System.out.println("Test 2 - getPassword: " + ("1234".equals(account.getPassword()) ? "PASS" : "FAIL"));

        // Test 3: getFirstName
        System.out.println("Test 3 - getFirstName: " + ("Alice".equals(account.getFirstName()) ? "PASS" : "FAIL"));

        // Test 4: getLastName
        System.out.println("Test 4 - getLastName: " + ("Smith".equals(account.getLastName()) ? "PASS" : "FAIL"));

        // Test 5: toFileString format
        String fileString = account.toFileString();
        boolean correctFileString = "testUser,1234,Alice,Smith".equals(fileString);
        System.out.println("Test 5 - toFileString: " + (correctFileString ? "PASS" : "FAIL"));

        // Test 6: fromFileString reconstruction
        Account parsed = Account.fromFileString(fileString);
        boolean parsedValid = parsed != null
                && "testUser".equals(parsed.getUsername())
                && "Alice".equals(parsed.getFirstName());
        System.out.println("Test 6 - fromFileString parse: " + (parsedValid ? "PASS" : "FAIL"));

        // Test 7: toString() formatting
        boolean correctToString = "Smith (testUser)".equals(account.toString());
        System.out.println("Test 7 - toString: " + (correctToString ? "PASS" : "FAIL"));
    }
}