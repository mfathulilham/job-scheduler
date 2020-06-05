package com.example.jobscheduler;

public class MyTask {
    String title,desc,due;

    public MyTask(String title, String desc, String due) {
        this.title = title;
        this.desc = desc;
        this.due = due;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDue() {
        return due;
    }

    public void setDue(String due) {
        this.due = due;
    }
}
