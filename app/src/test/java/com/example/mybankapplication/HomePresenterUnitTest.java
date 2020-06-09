package com.example.mybankapplication;

import com.example.mybankapplication.cleancode.loginScreen.HomeActivityInput;
import com.example.mybankapplication.cleancode.loginScreen.HomePresenter;
import com.example.mybankapplication.cleancode.loginScreen.LoginResponseModel;
import com.example.mybankapplication.cleancode.model.UserAccount;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import java.lang.ref.WeakReference;

/**
 * Created by mkaratadipalayam on 11/10/16.
 */
@RunWith(RobolectricTestRunner.class)
public class HomePresenterUnitTest {
    public static String TAG = HomePresenterUnitTest.class.getSimpleName();

    @Before
    public void setUp(){

    }
    @After
    public void tearDown(){

    }

    @Test
    public void presentHomeMetaData_withValidData_shouldCall_displayHomeMetaData(){
        //Given
        HomePresenter homePresenter = new HomePresenter();
        final LoginResponseModel homeResponse = new LoginResponseModel();

        homeResponse.setUserAccount(new UserAccount());

        HomeActivityInputSpy homeActivityInputSpy = new HomeActivityInputSpy();
        homePresenter.output = new WeakReference<HomeActivityInput>(homeActivityInputSpy);

        //When
        homePresenter.presentHomeData(homeResponse);

        //Then
        Assert.assertTrue("When the valid input is passed to HomePresenter Then displayHomeMetaData should be called", homeActivityInputSpy.isdisplayHomeMetaDataCalled);
    }

    @Test
    public void presentHomeMetaData_withoutError_shouldNotCall_displayHomeMetaData(){
        //Given
        HomePresenter homePresenter = new HomePresenter();
        LoginResponseModel homeResponse = new LoginResponseModel();
        homeResponse.setUserAccount(null);

        HomeActivityInputSpy homeActivityInputSpy = new HomeActivityInputSpy();
        homePresenter.output = new WeakReference<HomeActivityInput>(homeActivityInputSpy);

        //When
        homePresenter.presentHomeData(homeResponse);

        //Then
        Assert.assertFalse("When null input is passed to HomePresenter Then displayHomeMetaData should NOT be called", homeActivityInputSpy.isdisplayHomeMetaDataCalled);
    }

    private class HomeActivityInputSpy implements HomeActivityInput {
        public boolean isdisplayHomeMetaDataCalled = false;
        public LoginResponseModel homeViewModelCopy;


        @Override
        public void displayHomeMetaData(LoginResponseModel loginResponseModel) {
            isdisplayHomeMetaDataCalled = true;
            homeViewModelCopy = loginResponseModel;
        }
    }
}
