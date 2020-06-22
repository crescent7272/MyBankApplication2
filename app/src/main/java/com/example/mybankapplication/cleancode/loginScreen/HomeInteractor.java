package com.example.mybankapplication.cleancode.loginScreen;

import android.util.Log;

import androidx.annotation.NonNull;

public class HomeInteractor implements HomeInteractorInput{

    public static String TAG = HomeInteractor.class.getSimpleName();
    public HomePresenterInput output;
    public HomeWorkerInput aHomeWorkerInput;

    public HomeWorkerInput getHomeWorkerInput() {
        if (aHomeWorkerInput == null) return new HomeWorker();
        return aHomeWorkerInput;
    }

    public void setHomeWorkerInput(HomeWorkerInput aHomeWorkerInput) {
        this.aHomeWorkerInput = aHomeWorkerInput;
    }

    @Override
    public void fetchHomeMetaData(LoginRequestModel request) {

        //retrieve from the shared preferences
        Log.e(TAG,"In method fetchHomeMetaData");
        aHomeWorkerInput = getHomeWorkerInput();

        aHomeWorkerInput.getUserAccount(request.getUser(), request.getPassword(),
                new RevealCourtPlaceCallbacks() {
                    @Override
                    public void onSuccess(@NonNull LoginResponseModel loginResponseModel) {


                        output.presentHomeData(loginResponseModel);
                    }

                    @Override
                    public void onError(@NonNull Throwable throwable) {
                        throwable.printStackTrace();

                    }
                });



    }
}
