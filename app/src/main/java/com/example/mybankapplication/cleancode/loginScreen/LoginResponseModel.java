package com.example.mybankapplication.cleancode.loginScreen;

import com.example.mybankapplication.model.UserAccount;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

class LoginModel {
    //LoginRequestModel loginRequestModel = new LoginRequestModel();

}

class LoginViewModel {
    //filter to have only the needed data
    private LoginRequestModel loginRequestModel = new LoginRequestModel();

    public LoginRequestModel getLoginRequestModel() {
        return loginRequestModel;
    }

}


public class LoginResponseModel {

    @SerializedName("userAccount")
    @Expose
    private UserAccount userAccount;
    @SerializedName("error")
    @Expose
    private Error error;

    /**
     * No args constructor for use in serialization
     *
     */
    public LoginResponseModel() {
    }

    /**
     *
     * @param userAccount
     * @param error
     */
    public LoginResponseModel(UserAccount userAccount, Error error) {
        super();
        this.userAccount = userAccount;
        this.error = error;
    }

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }

}
