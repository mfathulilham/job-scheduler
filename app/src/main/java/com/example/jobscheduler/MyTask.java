package com.example.jobscheduler;

public class MyTask {
    private String title,desc,due;

    public MyTask() {

    }

    public MyTask(String title, String desc, String due) {
        this.title = title;
        this.desc = desc;
        this.due = due;
    }

    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }

    public String getDue() {
        return due;
    }

}
