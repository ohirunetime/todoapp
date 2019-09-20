package com.example.todoapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Todo {
    @SerializedName("id")
    private int id;

    @Expose
    @SerializedName("description")
    private String description;

    @Expose
    @SerializedName("uuid")
    private String uuid;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Expose
    @SerializedName("name")
    private String name;


    public String getTo_char() {
        return to_char;
    }

    public void setTo_char(String to_char) {
        this.to_char = to_char;
    }

    @SerializedName("to_char")
    private String to_char;

    public Todo(int id, String description, String uuid, String to_char) {
        this.id = id;
        this.description = description;
        this.uuid = uuid;
        this.to_char = to_char;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }




}
