package com.a3a04.android.lifestylist.database;

/**
 * Created by Temporary on 2017-04-03.
 */

public class SleepLog {

    private int id;
    private String date;
    private String sleepTime;
    private String wakeTime;

    public SleepLog(){
        this.id = 0;
        this.date = "";
        this.sleepTime = "";
        this.wakeTime = "";
    }

    public SleepLog(String date, String wakeTime, String sleepTime){
        this.date = date;
        this.wakeTime = wakeTime;
        this.sleepTime = sleepTime;
    }

    public SleepLog(int id, String date, String wakeTime, String sleepTime){
        this.id = id;
        this.date = date;
        this.sleepTime = sleepTime;
        this.wakeTime = wakeTime;
    }

    public void setID(int id){
        this.id = id;
    }

    public void setDate(String date){
        this.date = date;
    }

    public void setWakeTime(String wakeTime){
        this.wakeTime = wakeTime;
    }

    public void setSleepTime(String sleepTime){
        this.sleepTime = sleepTime;
    }

    public int getID(){
        return this.id;
    }

    public String getDate(){
        return this.date;
    }

    public String getWakeTime(){
        return this.wakeTime;
    }

    public String getSleepTime(){
        return this.sleepTime;
    }
}
