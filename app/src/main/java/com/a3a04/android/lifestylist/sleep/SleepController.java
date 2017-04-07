package com.a3a04.android.lifestylist.sleep;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.a3a04.android.lifestylist.database.DatabaseHandler;
import com.a3a04.android.lifestylist.database.MealLog;
import com.a3a04.android.lifestylist.database.SleepLog;
import com.a3a04.android.lifestylist.database.WorkoutLog;
import com.a3a04.android.lifestylist.main.MainController;

import java.util.List;

/**
 * Created by mattshortt on 2017-03-31.
 */

public class SleepController {

    DatabaseHandler userLogs;
    MainController mainControl = new MainController();
    List<MealLog> mealLogs;
    List<WorkoutLog> workoutLogs;
    List<SleepLog> sleepLogs;
    double sleepToday;
    double sleepYesterday;
    Context c;
    int mealToggle;
    int workoutToggle;

    public SleepController(){
        super();
    }

    public SleepController(Context context){
        userLogs = new DatabaseHandler(context);
        c = context;
    }

    protected double generateRecommendation(){

        updateRecommendation();

        SharedPreferences prefs = c.getSharedPreferences("SharedPref", Context.MODE_PRIVATE);
        mealToggle = prefs.getInt("MealToggle", -1);
        workoutToggle = prefs.getInt("WorkoutToggle", -1);

        double recommendedSleep = 8.0;
        if(sleepLogs.size() >= 1){
            sleepToday = getTimeSlept(1);
        }
        if(sleepLogs.size() >= 2){
            sleepYesterday = getTimeSlept(2);
        }


        if(mealToggle==1 && workoutToggle==1){//Both on

        }
        else if(mealToggle==0 && workoutToggle==1){//Meal off, Workout on

            recommendedSleep = onlySleepRecommendation();
            int activeMinsToday = mainControl.getActiveMinsToday();

            if(activeMinsToday >= 60){
                recommendedSleep = recommendedSleep + 1.2;
            }
            if(activeMinsToday > 30 && activeMinsToday < 60){
                recommendedSleep = recommendedSleep + 0.02*activeMinsToday;
            }



        }
        else if(mealToggle==1 && workoutToggle==0){//Meal on, Workout off

        }
        else{ //both Meal and Workout subsystems are off

            recommendedSleep = onlySleepRecommendation();

        }

        return recommendedSleep;

    }

    protected double onlySleepRecommendation(){
        double recommendedSleep;

        if(sleepToday < 8.0 && sleepYesterday < 8.0){
            recommendedSleep = (8.0-sleepToday)*0.4 + (8.0-sleepYesterday)*0.2 + 8.0;
        }
        else if(sleepToday < 8.0){
            recommendedSleep = (8.0-sleepToday)*0.5 + 8.0;
        }
        else{
            recommendedSleep = 8.0;
        }

        return recommendedSleep;

    }

    protected void updateRecommendation(){

        mealLogs = mainControl.getMealData();
        workoutLogs = mainControl.getWorkoutData();

    }

    /**
     *
     * @return all the sleep logs
     */
    public List<SleepLog> getAllUserSleepData(){

        return userLogs.getAllSleepLogs();

    }

    /**
     *
     * @return Amount of time user slept
     */
    public double getTimeSlept(int daysAgo){

        sleepLogs = userLogs.getAllSleepLogs();
        String sleepTime =  sleepLogs.get(sleepLogs.size()-daysAgo).getWakeTime();
        String wakeTime  =  sleepLogs.get(sleepLogs.size()-daysAgo).getSleepTime();

        int sleepTimeHours = Integer.parseInt(sleepTime.substring(0,2));
        int sleepTimeMinutes = Integer.parseInt(sleepTime.substring(3));
        double sleepTimeValue = sleepTimeHours + sleepTimeMinutes/60.0;

        int wakeTimeHours = Integer.parseInt(wakeTime.substring(0,2));
        int wakeTimeMinutes = Integer.parseInt(wakeTime.substring(3));
        double wakeTimeValue = wakeTimeHours + wakeTimeMinutes/60.0;

        double timeSlept;

//        Log.d("sleepT", Double.toString(sleepTimeValue));
//        Log.d("wakeT", Double.toString(wakeTimeValue));

        if(sleepTimeValue > wakeTimeValue){
            timeSlept = (24-sleepTimeValue) + wakeTimeValue;
        }
        else{
            timeSlept = wakeTimeValue - sleepTimeValue;
        }

        return timeSlept;

    }




}
