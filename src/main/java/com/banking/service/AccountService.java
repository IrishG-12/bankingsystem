package com.banking.service;


import com.banking.exception.InsufficientFundsException;
import com.banking.exception.InvalidAccount;
import com.banking.model.CheckingAccount;
import com.banking.model.SavingsAccount;
import com.banking.model.Account;
import com.banking.util.AccountNumberGenerator;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


public class AccountService {

    public static final List<Account> accounts = new ArrayList<>();
    public static List<Account> filteredAccounts;

    public static void createSavingsAccount(double balance) {
        validateSavingsAccountDetails(balance);
        String accountNumber = AccountNumberGenerator.generateSavingsAccountNumber();
        SavingsAccount sa = new SavingsAccount(accountNumber, balance);
        accounts.add(sa);
    }

    public static void createCheckingAccount(double balance) {
        validateCheckingAccountDetails(balance);
        String accountNumber = AccountNumberGenerator.generateCheckingAccountNumber();
        CheckingAccount ca = new CheckingAccount(accountNumber, balance);
        accounts.add(ca);
    }


    private static void validateSavingsAccountDetails(double balance) {
        if (balance < 1000) {
            throw new IllegalArgumentException("Error: Minimum balance for a savings account is $1000.");
        }
//        if (interestRate == 0) {
//            throw new IllegalArgumentException("Error: Interest Rate cannot be 0.");
//        }
    }
    private static void validateCheckingAccountDetails(double balance) {
        if (balance < 3000) {
            throw new IllegalArgumentException("Error: Minimum balance for a savings account is $1000.");
        }
//        if (interestRate == 0) {
//            throw new IllegalArgumentException("Error: Interest Rate cannot be 0.");
//        }
    }

    public static void searchAccountNumber(String accountNumber) throws InvalidAccount {
        boolean found = false;
        for (Account account : accounts) {
            if (account instanceof SavingsAccount && account.getAccountNumber().equals(accountNumber)) {
                String savingsString = account.toString(); // Uses overridden toString()
                System.out.println(savingsString);
                found = true;
                break;

            } else if (account instanceof CheckingAccount && account.getAccountNumber().equals(accountNumber)) {
                String savingsString = account.toString(); // Uses overridden toString()
                System.out.println(savingsString);
                found = true;
                break;
            }
        }
        if (!found) {
            throw new InvalidAccount(accountNumber);
        }
    }

    public static void getAccountsAbove1500() {
       filteredAccounts = accounts.stream()
                .filter(acc -> acc.getBalance() > 1500)
                .collect(Collectors.toList());

        filteredAccounts.forEach(System.out::println);
    }

    public static void sortAccountByBalance() {
        filteredAccounts = accounts.stream()
                .filter(acc -> acc.getBalance() > 1500)
                .sorted(Comparator.comparingDouble(Account::getBalance))
                .collect(Collectors.toList());

        filteredAccounts.forEach(System.out::println);

    }

    public static void getTotalBalance() {
        double total = 0.0;
        for (Account acc : accounts) {
            total += acc.getBalance();
        }
        System.out.println("Total Balance: $" + total);

    }

    public static void showAccounts() {
        if (accounts.isEmpty()) {
            System.out.println("No accounts found.");
            return;
        }

        accounts.forEach(System.out::println);
    }

}