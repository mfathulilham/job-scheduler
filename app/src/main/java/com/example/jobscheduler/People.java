package com.example.jobscheduler;

public class People {
    private String username,occup;

    public People() {

    }

    public People(String username, String occup) {
        this.username = username;
        this.occup = occup;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getOccup() {
        return occup;
    }

    public void setOccup(String occup) {
        this.occup = occup;
    }
}
