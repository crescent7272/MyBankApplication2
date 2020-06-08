package com.example.mybankapplication.cleancode.statementsScreen;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

class StatementsModel {

}

class StatementsViewModel {

    //filter to have only the needed data

    ArrayList<StatementList> statementList;

}

class StatementsRequest {

    Integer idUser;
}

public class StatementsResponse {


    @SerializedName("statementList")
    @Expose
    private List<StatementList> statementList = null;

    @SerializedName("error")
    @Expose
    private Error error;

    /**
     * No args constructor for use in serialization
     *
     */
    public StatementsResponse() {
    }

    /**
     *
     * @param statementList
     * @param error
     */
    public StatementsResponse(List<StatementList> statementList, Error error) {
        super();
        this.statementList = statementList;
        this.error = error;
    }

    public List<StatementList> getStatementList() {
        return statementList;
    }

    public void setStatementList(List<StatementList> statementList) {
        this.statementList = statementList;
    }

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }


}
