package com.example.todoapp.model;

import com.google.gson.annotations.SerializedName;

public class Counter {
    @SerializedName("count")
    private String count;

    public String getCount() {
        return count;
    }


    public void setCount(String count) {
        this.count = count;
    }
}
