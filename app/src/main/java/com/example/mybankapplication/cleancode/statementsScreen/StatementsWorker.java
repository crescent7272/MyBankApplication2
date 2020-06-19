package com.example.mybankapplication.cleancode.statementsScreen;

import android.util.Log;

import androidx.annotation.Nullable;

import com.example.mybankapplication.cleancode.api.GetNoticeDataService;
import com.example.mybankapplication.cleancode.api.RetrofitInstance;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

interface StatementsWorkerInput {
    //Define needed interfaces
    void getStatements(Integer id, @Nullable GetStatementsCallbacks callbacks);
}

public class StatementsWorker implements StatementsWorkerInput {

    @Override
    public void getStatements(Integer id, @Nullable final GetStatementsCallbacks callbacks) {


        GetNoticeDataService service = RetrofitInstance.getRetrofitInstance().create(GetNoticeDataService.class);

        /** Call the method with parameter in the interface to get the notice data*/
        Call<StatementsResponse> call = service.getAccountDetails(id);

        /**Log the URL called*/
        Log.d("URL Called", call.request().url() +"");

        call.enqueue(new Callback<StatementsResponse>() {

            @Override
            public void onResponse(Call<StatementsResponse> call, Response<StatementsResponse> response) {
                Log.d("URL Called", call.request().url() + "");

                if(response.code() == 200)
                {
                    Gson gson = new Gson();
                    String responseS    = gson.toJson(response.body());
                    Log.d("URL Called2", responseS + "");
                    if(response.body()!= null)
                        callbacks.onSuccess(response.body());
                    else
                        callbacks.onError(new NullPointerException());
                }
            }

            @Override
            public void onFailure(Call<StatementsResponse> call, Throwable t) {

                Log.d("onfailure", t.getMessage() + "");
                if (callbacks != null)
                    callbacks.onError(t);
            }
        });
    }
}
