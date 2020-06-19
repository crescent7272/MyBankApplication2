package com.example.mybankapplication;

import androidx.annotation.Nullable;

import com.example.mybankapplication.cleancode.loginScreen.HomeInteractor;
import com.example.mybankapplication.cleancode.loginScreen.HomePresenterInput;
import com.example.mybankapplication.cleancode.loginScreen.HomeWorker;
import com.example.mybankapplication.cleancode.loginScreen.HomeWorkerInput;
import com.example.mybankapplication.cleancode.loginScreen.LoginResponseModel;
import com.example.mybankapplication.cleancode.loginScreen.RevealCourtPlaceCallbacks;
import com.example.mybankapplication.cleancode.loginScreen.LoginRequestModel;
import com.example.mybankapplication.cleancode.util.Utils;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by mkaratadipalayam on 12/10/16.
 */
@RunWith(RobolectricTestRunner.class)
public class HomeInteractorUnitTest {
    @Before
    public void setUp(){

    }
    @After
    public void tearDown(){

    }

    @Test
    public void fetchHomeMetaData_with_validInput_shouldCall_presentHomeMetaData(){
        //Given
        HomeInteractor homeInteractor = new HomeInteractor();
        LoginRequestModel homeRequest = new LoginRequestModel();
        homeRequest.setUser("test_user");
        homeRequest.setPassword("Test@1");
        HomePresenterInputSpy homePresenterInputSpy = new HomePresenterInputSpy();
        homeInteractor.output = homePresenterInputSpy;
        //When
        homeInteractor.fetchHomeMetaData(homeRequest);

        //Then
        Assert.assertTrue("When the valid input is passed to HomeInteractor " +
                        "Then presentHomeMetaData should be called",
                 homePresenterInputSpy.presentHomeMetaDataIsCalled);
    }

    @Test
    public void fetchHomeMetaData_with_validInput_shouldCall_Worker_getUserAccount(){
        //Given
        HomeInteractor homeInteractor = new HomeInteractor();
        LoginRequestModel homeRequest = new LoginRequestModel();
        homeRequest.setUser("test_user");
        homeRequest.setPassword("Test@1");
        //Setup TestDoubles
        homeInteractor.output = new HomePresenterInputSpy();
        HomeWorkerInputSpy flightWorkerInputSpy = new HomeWorkerInputSpy();
        homeInteractor.setHomeWorkerInput(flightWorkerInputSpy);

        //When
        homeInteractor.fetchHomeMetaData(homeRequest);

        //Then
        Assert.assertTrue("When the input is passed to HomeInteractor is FutureTrip" +
                "Then getFutureFlights should be called in Worker",
                flightWorkerInputSpy.isgetHomeWorkerMethodCalled);
    }

    @Test
    public void fetchHomeMetaData_with_invalidInput_shouldNotCall_Worker_getPastTrips(){
        //Given
        HomeInteractor homeInteractor = new HomeInteractor();
        ArrayList<String> invalidEmails = new ArrayList<>(Arrays. asList(

                "plainaddress",
                "#@%^%#$@#$@#.com",
                "@example.com",
                "Joe Smith <email@example.com>",
                "email.example.com",
                "email@example@example.com",
                ".email@example.com",
                "email.@example.com",
                "email..email@example.com",
                "あいうえお@example.com",
                "email@example.com (Joe Smith)",
                "email@example",
                "email@-example.com",
                "email@example.web",
                "email@111.222.333.44444",
                "email@example..com",
                "Abc..123@example.com",
                "\"(),:;<>[\\]@example.com",
                "just\"not\"right@example.com",
                "this\\ is\"really\"not\\\\allowed@example.com"

        ));


        //Setup TestDoubles
        homeInteractor.output = new HomePresenterInputSpy();
        HomeWorkerInputSpy flightWorkerInputSpy = new HomeWorkerInputSpy();
        homeInteractor.setHomeWorkerInput(flightWorkerInputSpy);


        LoginRequestModel homeRequest = new LoginRequestModel();
        //when password is valid
        homeRequest.setPassword("Test@1");

        //when username is invalid
        for(String email : invalidEmails)
        {
            homeRequest.setUser(email);
            //When
            homeInteractor.fetchHomeMetaData(homeRequest);

            //Then
            Assert.assertTrue("When the invalid input is passed to HomeInteractor is FutureTrip" +
                            "Then getFutureFlights should be called in Worker",
                    flightWorkerInputSpy.isgetHomeWorkerMethodCalled);

        }

    }

    private class HomePresenterInputSpy implements HomePresenterInput {

        boolean presentHomeMetaDataIsCalled = false;
        LoginResponseModel homeResponseCopy;


        @Override
        public void presentHomeData(LoginResponseModel response) {
            presentHomeMetaDataIsCalled = true;
            homeResponseCopy = response;
        }
    }

    private class HomeWorkerInputSpy implements HomeWorkerInput {

        boolean isgetHomeWorkerMethodCalled = false;

        @Override
        public void getUserAccount(String username, String password, @Nullable RevealCourtPlaceCallbacks callbacks) {
            boolean isgetHomeWorkerMethodCalled = true;
            callbacks.onSuccess(new LoginResponseModel());
        }
    }

    private class HomeWorkerInputReturnNullSpy implements HomeWorkerInput {

        boolean isgetHomeWorkerMethodCalled = false;

        @Override
        public void getUserAccount(String username, String password, @Nullable RevealCourtPlaceCallbacks callbacks) {
            isgetHomeWorkerMethodCalled = true;

            callbacks.onError(new Throwable());
        }
    }
}
