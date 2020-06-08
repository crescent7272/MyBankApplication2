package com.example.mybankapplication.cleancode.statementsScreen;

import android.util.Log;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

interface StatementsPresenterInput {
    public void presentStatementsData(StatementsResponse response);
}


public class StatementsPresenter implements StatementsPresenterInput {

    public static String TAG = StatementsPresenter.class.getSimpleName();

    //weak var output: HomePresenterOutput!
    public WeakReference<StatementsActivityInput> output;


    @Override
    public void presentStatementsData(StatementsResponse response) {
        // Log.e(TAG, "presentStatementsData() called with: response = [" + response + "]");
        //Do your decoration or filtering here
        StatementsViewModel statementsViewModel = new StatementsViewModel();
        statementsViewModel.statementList = new ArrayList<>();


        if (response.getStatementList() != null) {

            for (StatementList fm : response.getStatementList()) {
                statementsViewModel.statementList.add(new StatementList(fm.getTitle(),fm.getDesc(), fm.getDate(),fm.getValue()));

                //Decoration
                /*
                Calendar startingTime = getCalendar(fvm.startingTime);
                long daysDiff = getDaysDiff(getCurrentTime().getTimeInMillis(),startingTime.getTimeInMillis());
                setDaysFlyDecorationText(fvm, daysDiff,getCurrentTime().getTimeInMillis(),startingTime.getTimeInMillis());
                   */
            }
            output.get().displayStatementsData(statementsViewModel);
        }

    }


}
