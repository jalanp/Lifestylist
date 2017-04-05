package com.a3a04.android.lifestylist.database;

/**
 * Created by Temporary on 2017-04-03.
 */

public class LogMIA {

    private int id;
    private String fromDate;
    private String toDate;
    private String details;

    public LogMIA(){
        this.id = 0;
        this.fromDate = "";
        this.toDate = "";
        this.details = "";
    }

    public LogMIA(String fromDate, String toDate, String details){
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.details = details;
    }

    public LogMIA(int id, String fromDate, String toDate, String details){
        this.id = id;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.details = details;
    }

    public void setID(int id){
        this.id = id;
    }

    public void setFromDate(String fromDate){
        this.fromDate = fromDate;
    }

    public void setToDate(String toDate){
        this.toDate = toDate;
    }

    public void setDetails(String details){
        this.details = details;
    }

    public int getID(){
        return this.id;
    }

    public String getFromDate(){
        return this.fromDate;
    }

    public String getToDate(){
        return this.toDate;
    }

    public String getDetails(){
        return this.details;
    }
}
