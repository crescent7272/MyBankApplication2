package com.example.mybankapplication.cleancode.statementsScreen;

import com.example.mybankapplication.model.LoginRequestModel;
import com.example.mybankapplication.model.LoginResponseModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface GetNoticeDataService {

    /**
     * URL MANIPULATION
     * HTTP request body with the @Body annotation
     */

    @POST("login")
    Call<LoginResponseModel> login(@Body LoginRequestModel notice);

    @GET("statements/{idUser}")
    Call<StatementsResponse> getAccountDetails(@Path("idUser") Integer idUser);

}