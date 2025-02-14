package com.banking.model;

import com.banking.exception.InsufficientFundsException;
import com.banking.exception.InvalidAmount;
import com.banking.util.TransactionLogger;


public class CheckingAccount extends Account {

    private int transactionCount;
   // private static final double TRANSACTION_FEE = 12.00d;

    public CheckingAccount(String number, double balance) {
        super(number, balance);
        this.transactionCount = 0;
    }


    @Override
    public void withdraw(double amount, boolean isTransfer) throws InsufficientFundsException { //withdraw
        if (amount > balance) {
            throw new InsufficientFundsException(accountNumber, amount, balance);
        }
        balance = balance - amount;
        transactionCount++;

        if (!isTransfer) {
            TransactionLogger.logTransactions(accountNumber, -amount, "WITHDRAWAL");
        }
    }
    @Override
    public void deposit(double amount, boolean isTransfer) throws InvalidAmount {
        if (amount <= 0) {
            throw new InvalidAmount(amount);
        }
        balance = balance + amount;
        transactionCount++;
        if (!isTransfer) {
            TransactionLogger.logTransactions(accountNumber, amount, "DEPOSIT");
        }
    }

    @Override
    public void applyMonthlyFees() {
        balance = balance - (balance * (INTEREST_RATE/ 100));
        transactionCount = 0;
    }

    @Override
    public String toString() {
        return String.format("CheckingAccount[number=%s, balance=%.2f, transactions=%d]", accountNumber, balance, transactionCount);
    }
}


