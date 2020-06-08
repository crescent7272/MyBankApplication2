package com.example.mybankapplication.cleancode.statementsScreen;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.mybankapplication.cleancode.loginScreen.LoginResponseModel;

interface StatementsInteractorInput {
    public void fetchStatementsData(StatementsRequest request);
}

interface GetStatementsCallbacks {
    void onSuccess(@NonNull StatementsResponse value);
    void onError(@NonNull Throwable throwable);
}


public class StatementsInteractor implements StatementsInteractorInput {

    public static String TAG = StatementsInteractor.class.getSimpleName();
    public StatementsPresenterInput output;
    public StatementsWorkerInput aStatementsWorkerInput;

    public StatementsWorkerInput getStatementsWorkerInput() {
        if (aStatementsWorkerInput == null) return new StatementsWorker();
        return aStatementsWorkerInput;
    }

    public void setStatementsWorkerInput(StatementsWorkerInput aStatementsWorkerInput) {
        this.aStatementsWorkerInput = aStatementsWorkerInput;
    }

    @Override
    public void fetchStatementsData(StatementsRequest request) {
        aStatementsWorkerInput = getStatementsWorkerInput();
        // Call the workers
        aStatementsWorkerInput.getStatements(request.idUser, new GetStatementsCallbacks() {

            @Override
            public void onSuccess(@NonNull StatementsResponse statementsResponse) {
                output.presentStatementsData(statementsResponse);
            }

            @Override
            public void onError(@NonNull Throwable throwable) {
                throwable.printStackTrace();

            }
        });
    }
}
