package com.banking;

import com.banking.exception.InsufficientFundsException;
import com.banking.exception.InvalidAmount;
import com.banking.model.CheckingAccount;
import com.banking.model.SavingsAccount;
import com.banking.service.AccountService;
import com.banking.service.TransactionService;
import com.banking.util.TransactionLogger;


public class Main {
    public static void main(String[] args) {

        SavingsAccount savings = new SavingsAccount("SAV001", 1500.00);
        CheckingAccount checking = new CheckingAccount("CHK001", 2000.00);

        try {

            AccountService.accounts.add(savings);
            AccountService.accounts.add(checking);

//            AccountService.createSavingsAccount(1000);
//            AccountService.createCheckingAccount(3000);
//            AccountService.showAccounts();


            //simple saving and checking
            System.out.println(savings);
            System.out.println();

            System.out.println("Initial Account States:");
            //AccountService.showAccounts();
            System.out.println(savings);
            System.out.println(checking);

            //first transaction
            savings.deposit(300,false);
            checking.withdraw(300,false);

            System.out.println("\nAfter Transactions:");
            System.out.println(savings);
            System.out.println(checking);

//            checking.withdraw(450);
//            System.out.println(checking); -> just to check that transaction is counting

            //implement method for fees
            savings.applyMonthlyFees();
            checking.applyMonthlyFees();

            System.out.println("\nAfter Monthly Fees:");
            System.out.println(savings);
            System.out.println(checking);


            System.out.println("\nAccounts Above 1500:");
            AccountService.getAccountsAbove1500();

            System.out.println("\nAccounts Sorted By Balance:");
            AccountService.sortAccountByBalance();

            //total of all the balance in the list
            System.out.println();
            AccountService.getTotalBalance();


            System.out.println("\nLooking up account: SAV001");
            AccountService.searchAccountNumber("SAV001");
            System.out.println();




            double amount = 5000.00;
            System.out.println("\nDemonstrating Exception Handling:");
            System.out.println("\nAttempting to withdraw $" + amount + " from savings");

            try {
                savings.withdraw(amount, false);
                System.out.println("Withdrawal successful!");
            } catch (InsufficientFundsException e) {
                System.out.println("Withdrawal failed: " + e.getMessage());
            }

            System.out.println("Available balance: $" + savings.getBalance());
           // System.out.println(savings);



            System.out.println("\nLooking up non-existent account");
            try {
                AccountService.searchAccountNumber("INVALID123");
            } catch (Exception e) {
                System.out.println( e.getMessage());
            }

            System.out.println("\nAttempting invalid deposit");
            double deposit = -50;
            try {
                checking.deposit(deposit,false);
            } catch (InvalidAmount e) {
                System.out.println("Deposit failed: " + e.getMessage());
            }

            System.out.println(savings);
            System.out.println(checking);
            System.out.println("\nTransfer funds:");
            try {
                // Perform transfer
                TransactionService ts = new TransactionService();
                boolean success = ts.transfer(savings, checking, 250.0);

                if (success) {
                    System.out.println("Updated balances:");
                    System.out.println("Savings Balance: $" + savings.getBalance());
                    System.out.println("Checking Balance: $" + checking.getBalance());
                }
            } catch (InvalidAmount | InsufficientFundsException e) {
                System.out.println("Transfer failed: " + e.getMessage());
            }

            TransactionLogger.printAllTransactions();


//
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        }
    }

