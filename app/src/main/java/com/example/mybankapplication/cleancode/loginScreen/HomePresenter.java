package com.example.mybankapplication.cleancode.loginScreen;


import android.util.Log;
import android.widget.Toast;

import java.lang.ref.WeakReference;


public class HomePresenter implements HomePresenterInput {

    public static String TAG = HomePresenter.class.getSimpleName();
    public WeakReference<HomeActivityInput> output;


    @Override
    public void presentHomeData(LoginResponseModel response) {
        Log.e(TAG, "presentHomeData() called with: response = [" + response + "]");

        if( response.getUserAccount() != null  )
        {
            output.get().displayHomeMetaData( response );
        }

    }

}
