package net.testudobank;

import java.time.LocalDateTime;

public class SavingsGoal {
    private String goalID;
    private String accountID;
    private String goalName;
    private double targetAmount;
    private double currentAmount;
    private LocalDateTime deadline;
    private SavingsAccount linkedAccount; // Assuming each goal is linked to a specific Savings Account


    // Constructor (optional, can be helpful for initialization)
    public SavingsGoal() {
    }

    public SavingsGoal(String goalID, String accountID, String goalName, double targetAmount, double currentAmount, LocalDateTime deadline) {
        this.goalID = goalID;
        this.accountID = accountID;
        this.goalName = goalName;
        this.targetAmount = targetAmount;
        this.currentAmount = currentAmount;
        this.deadline = deadline;
    }

    // Getter and setter for goalID
    public String getGoalID() {
        return goalID;
    }

    public void setGoalID(String goalID) {
        this.goalID = goalID;
    }

    // Getter and setter for accountID
    public String getAccountID() {
        return accountID;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    // Getter and setter for goalName
    public String getGoalName() {
        return goalName;
    }

    public void setGoalName(String goalName) {
        this.goalName = goalName;
    }

    // Getter and setter for targetAmount
    public double getTargetAmount() {
        return targetAmount;
    }

    public void setTargetAmount(double targetAmount) {
        this.targetAmount = targetAmount;
    }

    // Getter and setter for currentAmount
    public double getCurrentAmount() {
        return currentAmount;
    }

    public void setCurrentAmount(double currentAmount) {
        this.currentAmount = currentAmount;
    }

    // Getter and setter for deadline
    public LocalDateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }

    public void contribute(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount cannot be negative");
        }
        double newCurrentAmount = this.currentAmount + amount;
        if (newCurrentAmount > this.targetAmount) {
            throw new IllegalArgumentException("Contribution exceeds target amount");
        }
        this.linkedAccount.deductFunds(amount); // Ensure funds are deducted from the linked savings account
        this.currentAmount = newCurrentAmount;
    }

    // Additional getters and setters
    public SavingsAccount getLinkedAccount() {
        return linkedAccount;
    }

    public void setLinkedAccount(SavingsAccount linkedAccount) {
        this.linkedAccount = linkedAccount;
    }

}

