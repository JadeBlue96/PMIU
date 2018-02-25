package com.example.user.weatherappproject.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by User on 18/02/2018.
 */

public class Weather {

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("main")
    @Expose
    String main;

    @SerializedName("description")
    @Expose
    String description;

    @SerializedName("icon")
    @Expose
    String icon;

    public Weather() {
    }

    public Weather(int id, String main, String description, String icon) {
        this.id = id;
        this.main = main;
        this.description = description;
        this.icon = icon;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}