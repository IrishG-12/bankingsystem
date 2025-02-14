package com.banking.exception;

public class InvalidAccount extends Exception  {

    public InvalidAccount(String accountNumber) {
        super("Error: Account "+ accountNumber +" does not exist. " );
    }
}
