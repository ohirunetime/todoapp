package com.ohirunetime.todoapp.model;

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




    @Expose
    @SerializedName("name")
    private String name;

    @Expose
    @SerializedName("created_at")
    private String created_at;


    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdate_at() {
        return update_at;
    }

    public void setUpdate_at(String update_at) {
        this.update_at = update_at;
    }

    @Expose
    @SerializedName("update_at")
    private String update_at;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
