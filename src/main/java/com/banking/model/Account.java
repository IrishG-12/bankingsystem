package com.banking.model;

import com.banking.exception.InsufficientFundsException;
import com.banking.exception.InvalidAmount;


import static com.banking.service.AccountService.accounts;

public abstract class Account {

    protected String accountNumber;
    protected double balance;
    protected double INTEREST_RATE = 2.50;

    public abstract void withdraw(double amount, boolean isTransfer) throws InsufficientFundsException;
    public abstract void deposit(double amount, boolean isTransfer) throws InvalidAmount;

    public abstract void applyMonthlyFees();

    public Account(String accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }



    @Override
    public String toString() {
        return String.format("%s[number=%s, balance=%.2f]", getClass().getSimpleName(), accountNumber, balance);
    }
}