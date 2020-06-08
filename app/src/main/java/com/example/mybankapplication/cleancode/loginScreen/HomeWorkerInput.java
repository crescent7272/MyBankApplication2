package com.example.mybankapplication.cleancode.loginScreen;

import androidx.annotation.Nullable;

public interface HomeWorkerInput {
    //Define needed interfaces
    void getUserAccount(String username, String password, @Nullable RevealCourtPlaceCallbacks callbacks);
}
