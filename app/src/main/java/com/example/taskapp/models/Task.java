package com.example.taskapp.models;

import java.io.Serializable;
import java.util.Date;

public class Task implements Serializable {
    public String title;
    public String desc;

    public Task(String title,String desc) {
        this.title = title;
        this.desc = desc;

    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
