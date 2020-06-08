package com.example.mybankapplication.cleancode.loginScreen;

import androidx.annotation.NonNull;

public interface RevealCourtPlaceCallbacks {
    void onSuccess(@NonNull LoginResponseModel value);

    void onError(@NonNull Throwable throwable);
}
