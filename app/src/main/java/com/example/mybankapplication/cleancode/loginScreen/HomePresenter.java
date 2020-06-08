package com.example.mybankapplication.cleancode.loginScreen;


import android.util.Log;
import android.widget.Toast;

import java.lang.ref.WeakReference;


public class HomePresenter implements HomePresenterInput {

    public static String TAG = HomePresenter.class.getSimpleName();

    //weak var output: HomePresenterOutput!
    public WeakReference<HomeActivityInput> output;


    @Override
    public void presentHomeData(LoginResponseModel response) {
        Log.e(TAG, "presentHomeData() called with: response = [" + response + "]");
        //Do your decoration or filtering here if the response isw not null then toast success message
        // if the response is null then toast failure message.

        if( response.getUserAccount() != null  )
        {
            output.get().displayHomeMetaData( response );
        }

    }

}
