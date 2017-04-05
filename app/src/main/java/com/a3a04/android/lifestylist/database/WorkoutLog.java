package com.a3a04.android.lifestylist.database;

/**
 * Created by Temporary on 2017-04-03.
 */

public class WorkoutLog {

    private int id;
    private String date;
    private String time;
    private int activeMins;

    public WorkoutLog(){
        this.id = 0;
        this.date = "";
        this.time = "";
        this.activeMins = 0;
    }

    public WorkoutLog(String date, String time, int activeMins){
        this.date = date;
        this.time = time;
        this.activeMins = activeMins;
    }

    public WorkoutLog(int id, String date, String time, int activeMins){
        this.id = id;
        this.date = date;
        this.time = time;
        this.activeMins = activeMins;
    }

    public void setID(int id){
        this.id = id;
    }

    public void setDate(String date){
        this.date = date;
    }

    public void setTime(String time){
        this.time = time;
    }

    public void setActiveMins(int activeMins){
        this.activeMins = activeMins;
    }

    public int getID(){
        return this.id;
    }

    public String getDate(){
        return this.date;
    }

    public String getTime(){
        return this.time;
    }

    public int getActiveMins(){
        return this.activeMins;
    }
}
