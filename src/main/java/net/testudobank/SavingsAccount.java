package net.testudobank;

import java.util.UUID;

public class SavingsAccount {
    private String accountID;
    private String customerID;
    private double balance;
    private double interestRate;

    // Constructor (optional, can help in certain implementations)
    public SavingsAccount() {
    }

    public SavingsAccount(String accountID, String customerID, double balance, double interestRate) {
        this.accountID = accountID;
        this.customerID = customerID;
        this.balance = balance;
        this.interestRate = interestRate;
    }
    
    public SavingsAccount(String customerID, double balance, double interestRate) {
        this.customerID = customerID;
        this.balance = balance;
        this.interestRate = interestRate;
        // Generate a unique accountID or set it elsewhere if system design requires
        this.accountID = UUID.randomUUID().toString();
    }


    // Getter and Setter for accountID
    public String getAccountID() {
        return accountID;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    // Getter and Setter for customerID
    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    // Getter and Setter for balance
    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    // Getter and Setter for interestRate
    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public void deductFunds(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Cannot deduct a negative amount");
        }
        if (this.balance < amount) {
            throw new IllegalArgumentException("Insufficient funds to deduct");
        }
        this.balance -= amount;
    }

    // Optional: Method to add funds
    public void addFunds(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Cannot add a negative amount");
        }
        this.balance += amount;
    }

}
