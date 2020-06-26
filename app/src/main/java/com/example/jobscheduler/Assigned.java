package com.example.jobscheduler;

public class Assigned {
    private String title,
            desc,
            assigned,
            date,
            time;
    public Assigned() {

    }

    public Assigned(String title, String desc, String assigned, String date, String time) {
        this.title = title;
        this.desc = desc;
        this.assigned = assigned;
        this.date = date;
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }

    public String getAssigned() {
        return assigned;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }
}
