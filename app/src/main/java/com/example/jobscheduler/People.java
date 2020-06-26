package com.example.jobscheduler;

public class People {
    private String username,occup,email,address,phone;

    public People() {

    }

    public People(String username, String occup, String email, String address, String phone) {
        this.username = username;
        this.occup = occup;
        this.email = email;
        this.address = address;
        this.phone = phone;
    }

    public String getUsername() {
        return username;
    }

    public String getOccup() {
        return occup;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }
}
