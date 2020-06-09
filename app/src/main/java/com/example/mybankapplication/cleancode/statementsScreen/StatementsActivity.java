package com.example.mybankapplication.cleancode.statementsScreen;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mybankapplication.R;
import com.example.mybankapplication.cleancode.loginScreen.HomeActivity;
import com.example.mybankapplication.cleancode.loginScreen.LoginResponseModel;
import com.example.mybankapplication.cleancode.util.Utils;
import com.google.gson.Gson;

interface StatementsActivityInput {
    public void displayStatementsData(StatementsViewModel viewModel);
    public void populateViews(LoginResponseModel viewModel);
}


public class StatementsActivity extends AppCompatActivity
        implements StatementsActivityInput {

    public static String TAG = StatementsActivity.class.getSimpleName();
    StatementsInteractorInput output;
    StatementsRouter router;
    private Gson gson;
    private LoginResponseModel responseModel;
    private TextView fullName;
    private TextView bankAccount;
    private TextView balance;
    private ImageView img_logout;
    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //do the setup

        setContentView(R.layout.activity_account);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorBase));
        }


        bindViews();
        StatementsConfigurator.INSTANCE.configure(this);

        responseModel = fetchMetaData();
        populateViews(responseModel);
        StatementsRequest aStatementsRequest = new StatementsRequest();
        //populate the request

        aStatementsRequest.idUser = responseModel.getUserAccount().getUserId();
        output.fetchStatementsData(aStatementsRequest);
        // Do other work
    }

    private void bindViews() {
        fullName = findViewById(R.id.fullname);
        bankAccount = findViewById(R.id.conta);
        balance = findViewById(R.id.saldo);
        img_logout = findViewById(R.id.img_logout);

        img_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*
                *
                * TODO maybe define a router?*/
                Intent myIntent = new Intent(StatementsActivity.this, HomeActivity.class);
                StatementsActivity.this.startActivity(myIntent);
            }
        });

    }

    public LoginResponseModel fetchMetaData() {

        Intent intent = getIntent();
        String loginResponseJson = intent.getStringExtra(getString(R.string.login_response));

        gson = new Gson();
        Log.d("AccountActivity", loginResponseJson+"");

        responseModel = gson.fromJson(loginResponseJson, LoginResponseModel.class);

        return responseModel;
        //String username = sharedPreferences.getString(getString(R.string.username),"");
        //String password = sharedPreferences.getString(getString(R.string.password),"");

        // create Request and set the needed input
        /*
        LoginRequestModel homeRequest = new LoginRequestModel();

        homeRequest.setUser(username);
        homeRequest.setPassword(password);
        */
        //this.username.setText(username);
        //this.password.setText(password);

        // Call the output to fetch the data
    }

    @Override
    public void populateViews(LoginResponseModel viewModel) {
        fullName.setText(responseModel.getUserAccount().getName());
        bankAccount.setText(responseModel.getUserAccount().getBankAccount());
        balance.setText("R$"+ Utils.formatCurrency(responseModel.getUserAccount().getBalance()));
    }

    @Override
    public void displayStatementsData(StatementsViewModel viewModel) {
        Log.e(TAG, "displayStatementsData() called with: viewModel = [" + viewModel + "]");
        // Deal with the data

        recyclerView = findViewById(R.id.statementList);
        if (viewModel != null) {
            StatementAdapter statementAdapter = new StatementAdapter(viewModel.statementList);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());

            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(statementAdapter);
        }


    }



}


