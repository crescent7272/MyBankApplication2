package com.example.mybankapplication.cleancode.loginScreen;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginRequestModel {


    @SerializedName("user")
    @Expose
    private String  user = null;

    @SerializedName("password")
    @Expose
    private String password;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * No args constructor for use in serialization
     *
     */



    public LoginRequestModel() {
    }

    /**
     *
     * @param user
     * @param password
     */
    public LoginRequestModel(String user, String password) {
        super();
        this.user = user;
        this.password = password;
    }

}
