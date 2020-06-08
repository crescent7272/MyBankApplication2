package com.example.mybankapplication.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mybankapplication.R;
import com.example.mybankapplication.model.LoginRequestModel;
import com.example.mybankapplication.model.LoginResponseModel;
import com.example.mybankapplication.my_interface.GetNoticeDataService;
import com.example.mybankapplication.network.RetrofitInstance;
import com.example.mybankapplication.util.Utils;
import com.google.gson.Gson;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

interface HomeInteractorInput {
    public void fetchHomeMetaData( /* HomeRequest request */ );
}

public class HomeActivity extends AppCompatActivity {

    private EditText username;
    private EditText password;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Utils.setLightStatusBar(this);

        sharedPreferences = getPreferences(Context.MODE_PRIVATE);

            /* min API 19 does not support
            sharedPreferences = EncryptedSharedPreferences.create(
                    "secret_shared_prefs",
                    masterKeyAlias,
                    getApplicationContext(),
                    EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                    EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
            );
            */


        // use the shared preferences and editor as you normally would
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        Button login = findViewById(R.id.login);

        username.setText(sharedPreferences.getString(getString(R.string.username), ""));
        password.setText(sharedPreferences.getString(getString(R.string.password), ""));

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(Utils.isValidUsername(password.getText().toString()) && Utils.isValidPassword(password.getText().toString()) )
                {

                    LoginRequestModel loginRequestModel = new LoginRequestModel(username.getText().toString(), password.getText().toString());
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
                                sharedPreferences.edit().putString(getString(R.string.username), username.getText().toString())
                                    .apply();

                                sharedPreferences.edit().putString(getString(R.string.password), password.getText().toString())
                                    .apply();
                                Gson gson = new Gson();
                                String responseS = gson.toJson(response.body());
                                Log.d("URL Called2", responseS + "");

                                Intent myIntent = new Intent(HomeActivity.this, AccountActivity.class);
                                myIntent.putExtra("response",responseS);
                                HomeActivity.this.startActivity(myIntent);

                            }



                        }

                        @Override
                        public void onFailure(Call<LoginResponseModel> call, Throwable t) {
                            Log.d("onfailure", t.getMessage() + "");
                            Toast.makeText(HomeActivity.this, "An error occurred ... Error message: "+t.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    });
                }

                else
                {
                    Toast.makeText(HomeActivity.this, "Username or password is invalid!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }



}
