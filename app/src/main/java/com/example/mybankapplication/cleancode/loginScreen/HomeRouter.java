package com.example.mybankapplication.cleancode.loginScreen;

import android.content.Intent;
import android.util.Log;

import com.example.mybankapplication.R;
import com.example.mybankapplication.cleancode.statementsScreen.StatementsActivity;
import com.google.gson.Gson;

import java.lang.ref.WeakReference;


interface HomeRouterInput {
    public void passDataToNextScene(LoginResponseModel loginResponseModel);
}

public class HomeRouter implements HomeRouterInput {

    public static String TAG = HomeRouter.class.getSimpleName();
    public WeakReference<HomeActivity> activity;

    @Override
    public void passDataToNextScene(LoginResponseModel loginResponseModel) {

        Gson gson = new Gson();
        String responseS = gson.toJson(loginResponseModel);
        Log.d(TAG, responseS + "");

        Intent intent = new Intent(activity.get(), StatementsActivity.class);
        intent.putExtra(activity.get().getString(R.string.login_response),responseS);
        activity.get().startActivity(intent);

    }

}
