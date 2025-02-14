package com.banking.model;

import com.banking.exception.InsufficientFundsException;
import com.banking.exception.InvalidAmount;

public interface Transferable {
    boolean transfer(Account fromAccount, Account toAccount, double amount) throws InsufficientFundsException, InvalidAmount;

}
