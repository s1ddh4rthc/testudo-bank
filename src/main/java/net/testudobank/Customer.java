package net.testudobank;

public class Customer {
    private String firstName;
    private String lastName;
    private String customerID;
    private String password;
    private int balanceInPennies;
    private int minimumBalanceInPennies;

    public Customer(String firstName, String lastName, String customerID, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.customerID = customerID;
        this.password = password;
        this.balanceInPennies = 0;
        this.minimumBalanceInPennies = 0;
    }

    public Customer() {
        this.firstName = null;
        this.lastName = null;
        this.customerID = null;
        this.password = null;
        this.balanceInPennies = 0;
        this.minimumBalanceInPennies = 0;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setBalanceInPennies(int balanceInPennies) {
        this.balanceInPennies = balanceInPennies;
    }

    public int getBalanceInPennies() {
        return balanceInPennies;
    }

    public double getBalanceInDollars() {
        return (balanceInPennies / 100);
    }

    public void setMinimumBalanceInPennies(int minimumBalanceInPennies) {
        this.minimumBalanceInPennies = minimumBalanceInPennies;
    } 
    
    public int getMinimumBalanceInPennies() {
        return minimumBalanceInPennies;
    }

    public double getMinimumBalanceInDollars() {
        return (minimumBalanceInPennies / 100);
    }
}
