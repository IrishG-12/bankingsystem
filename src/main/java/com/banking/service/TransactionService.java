package com.banking.service;

import com.banking.exception.InsufficientFundsException;
import com.banking.exception.InvalidAmount;
import com.banking.model.Account;
import com.banking.model.Transferable;
import com.banking.util.TransactionLogger;

public class TransactionService implements Transferable {

//    public static void transferFunds(Account fromAccount, Account toAccount, double amount) throws InsufficientFundsException , InvalidAmount {
//
//        if (fromAccount == null || toAccount == null) {
//            System.out.println("Invalid account details.");
//
//        }
//
//        if (amount <= 0) {
//            System.out.println("Transfer amount must be greater than zero.");
//
//        }
//        synchronized (fromAccount) {
//            if (fromAccount.getBalance() < amount) {
//                System.out.println("Insufficient balance in " + fromAccount.getAccountNumber());
//
//            }
//
//            fromAccount.withdraw(amount);
//            toAccount.deposit(amount);
//
//            System.out.println("Transfer successful! $" + amount + " transferred from "
//                    + fromAccount.getAccountNumber() + " to " + toAccount.getAccountNumber());
//
//        }
//    }

    @Override
    public boolean transfer(Account fromAccount, Account toAccount, double amount) throws InsufficientFundsException, InvalidAmount {
        if (fromAccount == null || toAccount == null) {
            throw new IllegalArgumentException("Invalid account details. Accounts cannot be null.");
        }

        //amount to transfer should not be 0
        if (amount <= 0) {
            throw new InvalidAmount(amount);
        }
        //check first if sender has balance
        if (fromAccount.getBalance() < amount) {
            throw new InsufficientFundsException(fromAccount.getAccountNumber(), amount, fromAccount.getBalance());
        }

        fromAccount.withdraw(amount,true);
        toAccount.deposit(amount,true);

        try {
            TransactionLogger.logTransactions(fromAccount.getAccountNumber(), -amount, "Transfer to " + toAccount.getAccountNumber());
            TransactionLogger.logTransactions(toAccount.getAccountNumber(), amount, "Transfer from " + fromAccount.getAccountNumber());

        }catch(Exception e){
            System.out.println(e.getMessage());
        }
            System.out.println("Transfer successful! $" + amount + " transferred from "
                    + fromAccount.getAccountNumber() + " to " + toAccount.getAccountNumber());

            return true;
        }

    }
