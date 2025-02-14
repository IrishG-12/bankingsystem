package com.banking.exception;

public class InsufficientFundsException extends Exception {


    public InsufficientFundsException(String accountNumber, double requestedAmount, double balance) {
        super("Error: Insufficient funds in account " + accountNumber +
                ": requested " + String.format("%.2f", requestedAmount) +
                ", available $" + String.format("%.2f", balance));
    }

}

