package com.example.mybankapplication.cleancode;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StatementList {


    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("desc")
    @Expose
    private String desc;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("value")
    @Expose
    private Double value;

    /**
     * No args constructor for use in serialization
     *
     */
    public StatementList() {
    }

    /**
     *
     * @param date
     * @param title
     * @param value
     * @param desc
     */
    public StatementList(String title, String desc, String date, Double value) {
        super();
        this.title = title;
        this.desc = desc;
        this.date = date;
        this.value = value;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

}
