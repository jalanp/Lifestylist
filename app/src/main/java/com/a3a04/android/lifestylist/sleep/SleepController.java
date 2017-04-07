package com.a3a04.android.lifestylist.sleep;

import android.content.Context;

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

    protected SleepController(){
        super();
    }

    public SleepController(Context context){
        userLogs = new DatabaseHandler(context);
    }

    protected void generateRecommendation(){

        updateRecommendation();

        if(userLogs.getPersonalData(1).getMealToggle()==1 && userLogs.getPersonalData(1).getWorkoutToggle()==1){//Both on

        }
        else if(userLogs.getPersonalData(1).getMealToggle()==0 && userLogs.getPersonalData(1).getWorkoutToggle()==1){//Meal off, Workout on

        }
        else if(userLogs.getPersonalData(1).getMealToggle()==1 && userLogs.getPersonalData(1).getWorkoutToggle()==0){//Meal on, Workout off

        }
        else{ //both Meal and Workout subsystems are off

        }

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
    public double getTimeSlept(){

        sleepLogs = userLogs.getAllSleepLogs();
        String sleepTime =  sleepLogs.get(sleepLogs.size()-1).getSleepTime();
        String wakeTime  =  sleepLogs.get(sleepLogs.size()-1).getWakeTime();

        int sleepTimeHours = Integer.parseInt(sleepTime.substring(0,2));
        int sleepTimeMinutes = Integer.parseInt(sleepTime.substring(3));
        double sleepTimeValue = sleepTimeHours + sleepTimeMinutes/60.0;

        int wakeTimeHours = Integer.parseInt(wakeTime.substring(0,2));
        int wakeTimeMinutes = Integer.parseInt(wakeTime.substring(3));
        double wakeTimeValue = wakeTimeHours + wakeTimeMinutes/60.0;

        double timeSlept;

        if(sleepTimeValue < wakeTimeValue){
            timeSlept = (24-sleepTimeValue) + wakeTimeValue;
        }
        else{
            timeSlept = wakeTimeValue - sleepTimeValue;
        }

        return timeSlept;

    }




}
