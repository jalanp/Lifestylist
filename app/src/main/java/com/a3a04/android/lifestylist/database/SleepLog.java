package com.a3a04.android.lifestylist.database;

/**
 * Created by Temporary on 2017-04-03.
 */

public class SleepLog {

    private int id;
    private String date;
    private String wakeTime;
    private int timeSlept;

    public SleepLog(){
        this.id = 0;
        this.date = "";
        this.wakeTime = "";
        this.timeSlept = 0;
    }

    public SleepLog(String date, String wakeTime, int timeSlept){
        this.date = date;
        this.wakeTime = wakeTime;
        this.timeSlept = timeSlept;
    }

    public SleepLog(int id, String date, String wakeTime, int timeSlept){
        this.id = id;
        this.date = date;
        this.wakeTime = wakeTime;
        this.timeSlept = timeSlept;
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

    public void setTimeSlept(int timeSlept){
        this.timeSlept = timeSlept;
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

    public int getTimeSlept(){
        return this.timeSlept;
    }
}
