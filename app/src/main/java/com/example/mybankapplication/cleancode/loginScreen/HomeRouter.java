package com.example.mybankapplication.cleancode.loginScreen;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import androidx.annotation.NonNull;

import com.example.mybankapplication.R;
import com.example.mybankapplication.cleancode.statementsScreen.AccountActivity;
import com.example.mybankapplication.cleancode.statementsScreen.StatementsActivity;
import com.google.gson.Gson;

import java.lang.ref.WeakReference;


interface HomeRouterInput {
    public void passDataToNextScene(LoginResponseModel loginResponseModel);
}

public class HomeRouter implements HomeRouterInput {

    public static String TAG = HomeRouter.class.getSimpleName();
    public WeakReference<HomeActivity> activity;
    //HomeWorker


    @Override
    public void passDataToNextScene(LoginResponseModel loginResponseModel) {
        //Based on the position or someother data decide the data for the next scene
        //LoginResponseModel loginResponseModel = activity.get().;
        //intent.putExtra("response",flight);

        Gson gson = new Gson();
        String responseS = gson.toJson(loginResponseModel);
        Log.d(TAG, responseS + "");

        Intent intent = new Intent(activity.get(), StatementsActivity.class);
        intent.putExtra(activity.get().getString(R.string.login_response),responseS);
        // passDataToNextScene(position, intent);
        activity.get().startActivity(intent);

    }

  /*  @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        // Log.e(TAG, "onItemClick() called with: parent = [" + parent + "], view = [" + view + "], position = [" + position + "], id = [" + id + "]");
        Intent intent = navigateToSomeWhere(position);
        passDataToNextScene(position, intent);
        activity.get().startActivity(intent);
    }
*/

}
