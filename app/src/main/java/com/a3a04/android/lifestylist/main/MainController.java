package com.a3a04.android.lifestylist.main;


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

    public MainController(){
        super();
    }

    public void updateRecommendationRequest(int id){

    }

    // Won't work because need to return three different things.
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

}
