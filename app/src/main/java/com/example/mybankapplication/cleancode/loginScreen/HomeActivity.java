package com.example.mybankapplication.cleancode.loginScreen;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mybankapplication.R;
import com.example.mybankapplication.cleancode.util.Utils;

public class HomeActivity extends AppCompatActivity implements HomeActivityInput {

    private EditText username;
    private EditText password;
    public static SharedPreferences sharedPreferences;

    public HomeInteractorInput output;
    public HomeRouter router;
    HomeWorker worker;
    public static String TAG = HomeActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Utils.setLightStatusBar(this);

        sharedPreferences = getPreferences(Context.MODE_PRIVATE);

        HomeConfigurator.INSTANCE.configure(this);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        Button login = findViewById(R.id.login);


        fetchMetaData();

        login.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {

                 if(Utils.isValidUsername(username.getText().toString()) && Utils.isValidPassword(password.getText().toString()) ) {

                     LoginRequestModel loginRequestModel = new LoginRequestModel(username.getText().toString(), password.getText().toString());
                     output.fetchHomeMetaData(loginRequestModel);
                 }
                 else
                 {
                     Toast.makeText(HomeActivity.this, "Username and/or password is invalid!", Toast.LENGTH_SHORT).show();
                 }
             }
         }
        );

    }

    public void fetchMetaData() {

        String username = sharedPreferences.getString(getString(R.string.username),"");
        String password = sharedPreferences.getString(getString(R.string.password),"");

        this.username.setText(username);
        this.password.setText(password);

    }


    @Override
    public void displayHomeMetaData( LoginResponseModel loginResponseModel ) {
        Log.e(TAG, "displayHomeMetaData() called  = ");

        sharedPreferences.edit().
                putString(getString(R.string.username), username.getText().toString())
                .apply();

        sharedPreferences.edit().
                putString(getString(R.string.password), password.getText().toString())
                .apply();

        if(loginResponseModel.getUserAccount() != null && loginResponseModel.getUserAccount().getName() != null && !loginResponseModel.getUserAccount().getName().equals("")  ) {
            Toast.makeText(this, "Login is successfull!", Toast.LENGTH_SHORT).show();

            router.passDataToNextScene(loginResponseModel);

        }
        else
            Toast.makeText(this, "Login is failed!", Toast.LENGTH_SHORT).show();
    }
}
