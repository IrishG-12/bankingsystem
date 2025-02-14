package com.banking.util;


public class AccountNumberGenerator {
    private static int counter = 1000; // Start account numbers from 1000

    public static String generateSavingsAccountNumber() {
        return "SAV" + (counter++);
    }

    public static String generateCheckingAccountNumber() {
        return "CHK" + (counter++);
    }
}

