package com.example.jobscheduler;

public class MyTask {
    private String title,
            desc,
            keyMyTask,
            date,
            time;

    public MyTask() {
    }

    public MyTask(String title, String desc, String keyMyTask, String date, String time) {
        this.title = title;
        this.desc = desc;
        this.keyMyTask = keyMyTask;
        this.date = date;
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }

    public String getKeyMyTask() {
        return keyMyTask;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }
}
