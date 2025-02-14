package com.banking.exception;

public class InvalidAmount extends RuntimeException {

    public InvalidAmount(double amount) {

        super("Error: You are trying to deposit " +amount + " on your account. Deposit amount must be positive.");
    }
}
