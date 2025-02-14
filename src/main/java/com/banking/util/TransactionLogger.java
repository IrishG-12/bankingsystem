package com.banking.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TransactionLogger {


    private static final Path TRANSACTIONS_PATH = Path.of("transactions.txt");

    public static void logTransactions(String accountNumber, double amount, String transactionType) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String timestamp = LocalDateTime.now().format(formatter);

            String transaction = String.format("[Time: %s] Account: %s | Amount: $%.2f | Transaction Type: %s%n",
                   timestamp, accountNumber, amount, transactionType);

            Files.writeString(TRANSACTIONS_PATH, transaction,
                    StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }


    public static void printAllTransactions() {
        try {
            String transactions = Files.readString(TRANSACTIONS_PATH);
            System.out.println("Transaction History:\n--------------------------\n" + transactions);
        } catch (IOException e) {
            System.out.println("Error reading transaction log: " + e.getMessage());
        }
    }
}



