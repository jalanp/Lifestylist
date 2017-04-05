package com.a3a04.android.lifestylist.database;

/**
 * Created by Temporary on 2017-04-03.
 */

public class MealLog {

    private int id;
    private String date;
    private String time;
    private int calories;

    public MealLog(){
        this.id = 0;
        this.date = "";
        this.time = "";
        this.calories = 0;
    }

    public MealLog(String date, String time, int calories){
        this.date = date;
        this.time = time;
        this.calories = calories;
    }

    public MealLog(int id, String date, String time, int calories){
        this.id = id;
        this.date = date;
        this.time = time;
        this.calories = calories;
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

    public void setCalories(int calories){
        this.calories = calories;
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

    public int getCalories(){
        return this.calories;
    }
}
