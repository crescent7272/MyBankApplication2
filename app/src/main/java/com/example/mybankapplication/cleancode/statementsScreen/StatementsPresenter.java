package com.example.mybankapplication.cleancode.statementsScreen;

import com.example.mybankapplication.cleancode.model.StatementList;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

interface StatementsPresenterInput {
    public void presentStatementsData(StatementsResponse response);
}


public class StatementsPresenter implements StatementsPresenterInput {

    public static String TAG = StatementsPresenter.class.getSimpleName();
    public WeakReference<StatementsActivityInput> output;


    @Override
    public void presentStatementsData(StatementsResponse response) {

        StatementsViewModel statementsViewModel = new StatementsViewModel();
        statementsViewModel.statementList = new ArrayList<>();


        if (response.getStatementList() != null) {

            for (StatementList fm : response.getStatementList()) {
                statementsViewModel.statementList.add(new StatementList(fm.getTitle(),fm.getDesc(), fm.getDate(),fm.getValue()));
            }
            output.get().displayStatementsData(statementsViewModel);
        }

    }


}
