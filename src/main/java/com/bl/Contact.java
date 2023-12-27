package com.bl;

import java.io.Serializable;

public class Contact implements Serializable {
    public String firstName;
    public String lastName;
    public String address;
    public String city;
    public String state;
    public String zip;
    public String phoneNumber;
    public String email;

    public Contact(String firstName, String lastName, String address, String city, String state, String zip, String phoneNumber, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    @Override
    public String toString() {
        return "Name: " + getFullName() +
                "\nAddress: " + address +
                "\nCity: " + city +
                "\nState: " + state +
                "\nZIP: " + zip +
                "\nPhone Number: " + phoneNumber +
                "\nEmail: " + email +
                "\n-----------------------";
    }
    public String getFullName() {
        return firstName + " " + lastName;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getZip() {
        return zip;
    }
}
