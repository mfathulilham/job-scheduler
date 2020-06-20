package com.example.jobscheduler;

public class MyTask {
    private String title,desc,due,keyMyTask;

    public MyTask() {

    }

    public MyTask(String title, String desc, String due, String keyMyTask) {
        this.title = title;
        this.desc = desc;
        this.due = due;
        this.keyMyTask = keyMyTask;
    }

    public String getKeyMyTask() {
        return keyMyTask;
    }

    public void setKeyMyTask(String keyMyTask) {
        this.keyMyTask = keyMyTask;
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
