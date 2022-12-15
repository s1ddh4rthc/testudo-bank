package net.testudobank;

public class Customer {
    private String firstName;
    private String lastName;
    private String customerID;
    private String password;

    public Customer(String firstName, String lastName, String customerID, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.customerID = customerID;
        this.password = password;
    }

    public Customer() {
        this.firstName = null;
        this.lastName = null;
        this.customerID = null;
        this.password = null;
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
}
