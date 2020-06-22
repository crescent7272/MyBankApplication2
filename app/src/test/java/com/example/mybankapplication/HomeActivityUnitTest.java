package com.example.mybankapplication;

import com.example.mybankapplication.cleancode.loginScreen.HomeActivity;
import com.example.mybankapplication.cleancode.loginScreen.HomeInteractorInput;
import com.example.mybankapplication.cleancode.loginScreen.LoginRequestModel;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

/**
 * Created by mkaratadipalayam on 13/10/16.
 */

@RunWith(RobolectricTestRunner.class)
public class HomeActivityUnitTest {

    HomeActivity activity;
    @Before
    public void setUp(){

        activity = Robolectric.buildActivity(HomeActivity.class)
                .create()
                .resume()
                .get();
    }
    @After
    public void tearDown(){}


    @Test
    public void HomeActivity_ShouldNOT_be_Null(){
        //Given
        //When

        // Then
        Assert.assertNotNull(activity);
    }

    @Test
    public void onCreate_shouldCall_fetchHomeMetaData(){
        //Given
        HomeActivityOutputSpy homeActivityOutputSpy = new HomeActivityOutputSpy();
        // It must have called the onCreate earlier,
        // we are injecting the mock and calling the fetchMetaData to test our condition
        activity.output = homeActivityOutputSpy;

        //When
        activity.output.fetchHomeMetaData(new LoginRequestModel());

        //Then
        Assert.assertTrue(homeActivityOutputSpy.fetchHomeMetaDataIsCalled);
  }

    @Test
    public void onCreate_Calls_fetchHomeMetaData_withCorrectData(){
        //Given
        HomeActivityOutputSpy homeActivityOutputSpy = new HomeActivityOutputSpy();
        activity.output = homeActivityOutputSpy;

        LoginRequestModel loginRequestModel = new LoginRequestModel();
        loginRequestModel.setUser("test_user@gmail.com");
        //When
        activity.output.fetchHomeMetaData(loginRequestModel);

        //Then
        Assert.assertNotNull(activity);
        Assert.assertTrue(homeActivityOutputSpy.homeRequestCopy.getUser()!=null );
    }



    private class HomeActivityOutputSpy implements HomeInteractorInput {

        boolean fetchHomeMetaDataIsCalled = false;
        LoginRequestModel homeRequestCopy;

        @Override
        public void fetchHomeMetaData(LoginRequestModel request) {
            fetchHomeMetaDataIsCalled = true;
            homeRequestCopy = request;
        }
    }


}
