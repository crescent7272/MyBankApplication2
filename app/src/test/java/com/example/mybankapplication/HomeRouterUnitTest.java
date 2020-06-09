package com.example.mybankapplication;


import com.example.mybankapplication.cleancode.loginScreen.HomeActivity;
import com.example.mybankapplication.cleancode.loginScreen.HomeRouter;
import com.example.mybankapplication.cleancode.loginScreen.LoginResponseModel;
import com.example.mybankapplication.cleancode.statementsScreen.StatementsActivity;
import com.example.mybankapplication.cleancode.model.UserAccount;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import java.lang.ref.WeakReference;

/**
 * Created by mkaratadipalayam on 28/05/17.
 */

@RunWith(RobolectricTestRunner.class)
public class HomeRouterUnitTest {


    public static String TAG = HomeRouterUnitTest.class.getSimpleName();
    HomeActivity homeActivity;
    @Before
    public void setUp(){
        homeActivity = Robolectric.buildActivity(HomeActivity.class)
                .create()
                .resume()
                .get();
    }
    @After
    public void tearDown(){

    }

    @Test
    public void homeRouter_passDataToNextScene() {
        //Given
        HomeRouter homeRouter = new HomeRouter();

        //homeActivity.listOfVMFlights = flightList;
        homeActivity.router = homeRouter;
        homeRouter.activity = new WeakReference<HomeActivity>(homeActivity);

        //homeRouter.setCurrentTime(currentTime);

        UserAccount userAccount = new UserAccount(5, "hilal","1234567", "conta", 12.1);

        LoginResponseModel loginResponseModel = new LoginResponseModel(userAccount,new Error());

        //When - Past Trip is Input

        homeRouter.passDataToNextScene(loginResponseModel);

        //Then
        String targetActivityName = StatementsActivity.class.getName();
        Assert.assertEquals("When the loginResponseModel is passed to HomeRouter"
                +" Then next Intent should be StatementsActivity",targetActivityName, StatementsActivity.class.getName());
    }



}
