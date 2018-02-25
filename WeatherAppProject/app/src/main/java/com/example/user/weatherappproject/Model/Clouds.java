package com.example.user.weatherappproject.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by User on 18/02/2018.
 */

public class Clouds {

    @SerializedName("all")
    @Expose
    private int all;

    public Clouds(int all) {
        this.all = all;
    }

    public int getAll() {
        return all;
    }

    public void setAll(int all) {
        this.all = all;
    }
}
