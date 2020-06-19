package com.example.mybankapplication.cleancode.loginScreen;

import android.util.Log;

import androidx.annotation.Nullable;

import com.example.mybankapplication.cleancode.api.GetNoticeDataService;
import com.example.mybankapplication.cleancode.api.RetrofitInstance;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


//call the api
public class HomeWorker implements HomeWorkerInput{

    String username;
    @Override
    public void getUserAccount(String username, String password, @Nullable final RevealCourtPlaceCallbacks callbacks) {


        LoginRequestModel loginRequestModel = new LoginRequestModel(username, password);
        //Call api
        /** Create handle for the RetrofitInstance interface*/
        GetNoticeDataService service = RetrofitInstance.getRetrofitInstance().create(GetNoticeDataService.class);

        /** Call the method with parameter in the interface to get the notice data*/
        Call<LoginResponseModel> call = service.login(loginRequestModel);

        /**Log the URL called*/
        Log.d("URL Called", call.request().url() +"");

        call.enqueue(new Callback<LoginResponseModel>() {

            @Override
            public void onResponse(Call<LoginResponseModel> call, Response<LoginResponseModel> response) {
                Log.d("URL Called", call.request().url() + "");

                if(response.code() == 200 && (response.body() != null ? response.body().getUserAccount() : null) !=null
                        && response.body().getUserAccount().getUserId()!=null)
                {
                    Gson gson = new Gson();
                    String responseS = gson.toJson(response.body());
                    Log.d("URL Called2", responseS + "");

                    //send respond to router.
                    if (callbacks != null)
                        callbacks.onSuccess(response.body());
                }
            }

            @Override
            public void onFailure(Call<LoginResponseModel> call, Throwable t) {
                Log.d("onfailure", t.getMessage() + "");
                if (callbacks != null)
                    callbacks.onError(t);
            }
        });

    }
}
