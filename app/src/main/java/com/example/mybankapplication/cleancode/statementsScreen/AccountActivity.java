package com.example.mybankapplication.cleancode.statementsScreen;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mybankapplication.R;
import com.example.mybankapplication.activity.HomeActivity;
import com.example.mybankapplication.adapter.StatementAdapter;
import com.example.mybankapplication.model.AccountResponseModel;
import com.example.mybankapplication.model.LoginResponseModel;
import com.example.mybankapplication.my_interface.GetNoticeDataService;
import com.example.mybankapplication.network.RetrofitInstance;
import com.example.mybankapplication.util.Utils;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

interface AccountActivityInput {
    public void displayHomeMetaData( com.example.mybankapplication.cleancode.loginScreen.LoginResponseModel loginResponseModel);
}

public class AccountActivity extends AppCompatActivity {

    private Gson gson;
    private LoginResponseModel responseModel;
    private TextView  fullName;
    private TextView bankAccount;
    private TextView balance;
    private ImageView img_logout;
    private RecyclerView recyclerView;
    private StatementAdapter statementAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Utils.setLightStatusBar(this);
        //Utils.clearLightStatusBar(this);

        setContentView(R.layout.activity_account);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorBase));
        }

        Intent intent = getIntent();
        String loginResponseJson = intent.getStringExtra("response");



        fullName = findViewById(R.id.fullname);
        bankAccount = findViewById(R.id.conta);
        balance = findViewById(R.id.saldo);
        img_logout = findViewById(R.id.img_logout);

        fullName.setText(responseModel.getUserAccount().getName());
        bankAccount.setText(responseModel.getUserAccount().getBankAccount());

        gson = new Gson();
        Log.d("AccountActivity", loginResponseJson+"");

        responseModel = gson.fromJson(loginResponseJson, LoginResponseModel.class);
        balance.setText("R$"+ Utils.formatCurrency(responseModel.getUserAccount().getBalance()));

        img_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(AccountActivity.this, HomeActivity.class);
                AccountActivity.this.startActivity(myIntent);
            }
        });
        //Make 2nd API call
        /** Create handle for the RetrofitInstance interface*/
        GetNoticeDataService service = RetrofitInstance.getRetrofitInstance().create(GetNoticeDataService.class);

        /** Call the method with parameter in the interface to get the notice data*/
        Call<AccountResponseModel> call = service.getAccountDetails(responseModel.getUserAccount().getUserId());

        /**Log the URL called*/
        Log.d("URL Called", call.request().url() +"");

        call.enqueue(new Callback<AccountResponseModel>() {

            @Override
            public void onResponse(Call<AccountResponseModel> call, Response<AccountResponseModel> response) {
                Log.d("URL Called", call.request().url() + "");

                if(response.code() == 200)
                {
                    Gson gson = new Gson();
                    String responseS = gson.toJson(response.body());
                    Log.d("URL Called2", responseS + "");

                    recyclerView = findViewById(R.id.statementList);
                    if (response.body() != null) {
                        statementAdapter = new StatementAdapter(response.body().getStatementList());
                        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());

                        recyclerView.setLayoutManager(mLayoutManager);
                        recyclerView.setItemAnimator(new DefaultItemAnimator());
                        recyclerView.setAdapter(statementAdapter);

                    }

                }
            }

            @Override
            public void onFailure(Call<AccountResponseModel> call, Throwable t) {

                Log.d("onfailure", t.getMessage() + "");
                Toast.makeText(AccountActivity.this, "An error occurred ... Data could not be retrieved: "+t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }


}
