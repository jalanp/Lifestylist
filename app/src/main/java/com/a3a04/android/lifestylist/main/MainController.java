package com.a3a04.android.lifestylist.main;


import android.content.Context;
import android.content.SharedPreferences;

import com.a3a04.android.lifestylist.database.DatabaseHandler;
import com.a3a04.android.lifestylist.database.MealLog;
import com.a3a04.android.lifestylist.database.SleepLog;
import com.a3a04.android.lifestylist.database.WorkoutLog;
import com.a3a04.android.lifestylist.meal.MealController;
import com.a3a04.android.lifestylist.sleep.SleepController;
import com.a3a04.android.lifestylist.workout.WorkoutController;

import java.util.List;

/**
 * Created by mattshortt on 2017-03-29.
 */

public class MainController {


    DatabaseHandler userLogs;
    MealController mealControl;
    WorkoutController workoutControl;
    SleepController sleepControl;
    Context c;


    public MainController(){
        super();
        //mealControl = new MealController();
        //workoutControl = new WorkoutController();
        //sleepControl = new SleepController();
    }

    public MainController(Context context){
        super();
        mealControl = new MealController(context);
        workoutControl = new WorkoutController(context);
        sleepControl = new SleepController(context);
        c = context;
    }

    public void updateRecommendationRequest(int id){

        SharedPreferences prefs = c.getSharedPreferences("SharedPref", Context.MODE_PRIVATE);
        int mealToggle = prefs.getInt("MealToggle", -1);
        int sleepToggle = prefs.getInt("SleepToggle", -1);
        int workoutToggle = prefs.getInt("WorkoutToggle", -1);

        switch (id){
            case 1:
                if (workoutToggle == 1)
                    workoutControl.generateRecommendation();
                if (sleepToggle == 1)
                    sleepControl.generateRecommendation();
            case 2:
                if (mealToggle == 1)
                    mealControl.generateRecommendation();
                if (sleepToggle == 1)
                    sleepControl.generateRecommendation();
            case 3:
                if (workoutToggle == 1)
                    workoutControl.generateRecommendation();
                if (mealToggle == 1)
                    mealControl.generateRecommendation();
        }


    }

    // Won't work because need to return three different things. DEPRECATED
//    public void getSubsystemData(int id){
//
//        switch(id){
//            case 1:
//                getMealData();
//            case 2:
//                getWorkoutData();
//            case 3:
//                getSleepData();
//        }
//
//    }


    /**
     * if statement so that when the meal subsystem is turned off the recommendations
     * don't get affected by meals
     * @return all the meal logs
     */
    public List<MealLog> getMealData(){


        return mealControl.getAllUserMealData();

    }

    /**
     * if statement so that when the workout subsystem is turned off the recommendations
     * don't get affected by workouts
     * @return all the workouts logs
     */
    public List<WorkoutLog> getWorkoutData(){


        return workoutControl.getAllUserWorkoutData();

    }

    /**
     * if statement so that when the sleep subsystem is turned off the recommendations
     * don't get affected by sleep
     * @return all the sleep logs
     */
    public List<SleepLog> getSleepData(){

        return sleepControl.getAllUserSleepData();

    }

    public double getTimeSlept(int daysAgo){
        return sleepControl.getTimeSlept(daysAgo);
    }

    public int getActiveMinsToday(){
        return workoutControl.getDailyActiveMinutesData();
    }

    public int getActiveMinsYesterday(){
        return workoutControl.getYesterdaysActiveMinutesData();
    }

    public int getCaloriesToday(){
        return mealControl.getDailyCalorieData();
    }

    public int getCaloriesYesterday(){
        return mealControl.getYesterdaysCalorieData();
    }

    public int getRecommendedActiveMinutes(){
        return workoutControl.generateRecommendation();
    }

    public double getRecommendedSleepTime(){
        return sleepControl.generateRecommendation();
    }

    public int getAge(){
        DatabaseHandler db = new DatabaseHandler(c);
        return db.getPersonalData(1).getAge();
    }

    public int getGender(){
        DatabaseHandler db = new DatabaseHandler(c);
        return db.getPersonalData(1).getGender();
    }

    public int getWeight(){
        DatabaseHandler db = new DatabaseHandler(c);
        return db.getPersonalData(1).getWeight();
    }

}
