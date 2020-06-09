package com.example.jobscheduler;

public class User {
    private String username,email,pass,phone,occup,address;

    public User(String username, String email, String pass, String phone, String occup, String address) {
        this.username = username;
        this.email = email;
        this.pass = pass;
        this.phone = phone;
        this.occup = occup;
        this.address = address;
    }

    public User() {
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPass() {
        return pass;
    }

    public String getPhone() {
        return phone;
    }

    public String getOccup() {
        return occup;
    }

    public String getAddress() {
        return address;
    }
}
