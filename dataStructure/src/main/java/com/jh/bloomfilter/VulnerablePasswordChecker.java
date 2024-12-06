package com.jh.bloomfilter;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * This class provides a fragile password checker using Guava's BloomFilter.
 * The BloomFilter is used to identify weak passwords with high performance,
 * but it may produce false positives (never false negatives).
 * @author johnny
 */
public class VulnerablePasswordChecker {
    private final BloomFilter<String> bloomFilter;

    /**
     * Constructor initializes the BloomFilter with a predefined capacity and false-positive rate.
     *
     * @param weakPasswords   List of weak passwords to preload into the BloomFilter.
     * @param expectedEntries The expected number of weak passwords in the BloomFilter.
     * @param falsePositiveRate The desired false-positive rate (e.g., 0.01 for 1%).
     */
    public VulnerablePasswordChecker(List<String> weakPasswords, int expectedEntries, double falsePositiveRate) {
        // Create a BloomFilter for Strings with specified parameters
        bloomFilter = BloomFilter.create(
                Funnels.stringFunnel(StandardCharsets.UTF_8),
                expectedEntries,
                falsePositiveRate
        );

        // Populate the BloomFilter with weak passwords
        for (String password : weakPasswords) {
            bloomFilter.put(password);
        }
    }

    /**
     * Checks if the given password is potentially weak based on the BloomFilter.
     * Note: This method can produce false positives but guarantees no false negatives.
     *
     * @param password The password to check.
     * @return True if the password is potentially weak, false otherwise.
     */
    public boolean isWeakPassword(String password) {
        return bloomFilter.mightContain(password);
    }

    /**
     * Main method for testing the functionality of the VulnerablePasswordChecker.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        // List of common weak passwords
        List<String> weakPasswords = List.of("123456", "password", "123456789", "qwerty", "abc123", "12345678");

        // Initialize the checker with a capacity of 10,000 entries and a 1% false-positive rate
        VulnerablePasswordChecker checker = new VulnerablePasswordChecker(weakPasswords, 10000, 0.01);

        // Test passwords
        String[] testPasswords = {"123456", "securepassword", "qwerty", "letmein", "password123"};
        for (String testPassword : testPasswords) {
            boolean isWeak = checker.isWeakPassword(testPassword);
            System.out.println("Password: \"" + testPassword + "\" is weak: " + isWeak);
        }
    }
}

