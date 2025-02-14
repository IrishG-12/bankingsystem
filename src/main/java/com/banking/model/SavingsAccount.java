package com.banking.model;

import com.banking.exception.InsufficientFundsException;
import com.banking.exception.InvalidAmount;
import com.banking.util.TransactionLogger;
import javax.security.auth.login.AccountException;


public class SavingsAccount extends Account {


    public SavingsAccount(String accountNumber, double balance) {
        super(accountNumber, balance);

    }

    @Override
    public void withdraw(double amount, boolean isTransfer) throws InsufficientFundsException {
        if (amount > balance) {
            throw new InsufficientFundsException(accountNumber, amount, balance);
        }
        balance = balance - amount;
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
        if (!isTransfer) {
            TransactionLogger.logTransactions(accountNumber, amount, "DEPOSIT");
        }
    }
    @Override
    public void applyMonthlyFees() {
        balance = balance + (balance * (INTEREST_RATE / 100));
    }

    @Override
    public String toString() {
        return String.format("SavingsAccount[number=%s, balance=%.2f, interestRate=%.2f%%]", accountNumber, balance,INTEREST_RATE);
    }
}
