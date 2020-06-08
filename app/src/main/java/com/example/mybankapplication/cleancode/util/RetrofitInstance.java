package com.example.mybankapplication.cleancode.util;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {

    private static Retrofit retrofit;
    private static final String BASE_URL = "https://bank-app-test.herokuapp.com/api/";

    public static String getBaseUrl() {
        return BASE_URL;
    }

    /**
     * Create an instance of Retrofit object
     */

    public static Retrofit getRetrofitInstance() {
        //if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(getBaseUrl())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();


       // }

        return retrofit;

    }


}