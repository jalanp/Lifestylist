package com.a3a04.android.lifestylist.meal;

import android.content.Context;

import com.a3a04.android.lifestylist.main.MainController;
import com.a3a04.android.lifestylist.database.*;

import java.text.SimpleDateFormat;
import java.util.*;

import com.a3a04.android.lifestylist.database.DatabaseHandler;

/**
 * Created by mattshortt on 2017-03-31.
 */

public class MealController {



    DatabaseHandler userLogs;
    MainController mainControl = new MainController();
    List<WorkoutLog> workoutLogs;
    List<SleepLog> sleepLogs;

    protected MealController(){
        super();
    }

    public MealController(Context context){
        userLogs = new DatabaseHandler(context);
    }

    protected void generateRecommendation(){

        updateRecommendation();

        if(userLogs.getPersonalData(1).getWorkoutToggle()==1 && userLogs.getPersonalData(1).getSleepToggle()==1){//Both on

        }
        else if(userLogs.getPersonalData(1).getWorkoutToggle()==0 && userLogs.getPersonalData(1).getSleepToggle()==1){//Workout off, Sleep on

        }
        else if(userLogs.getPersonalData(1).getWorkoutToggle()==1 && userLogs.getPersonalData(1).getSleepToggle()==0){//Workout on, Sleep off

        }
        else{ //both Workout and Sleep subsystems are off

        }

    }

    protected void updateRecommendation(){


       workoutLogs = mainControl.getWorkoutData();
       sleepLogs = mainControl.getSleepData();


    }

    /**
     *
     * @return all the meal logs
     */
    public List<MealLog> getAllUserMealData(){


        return userLogs.getAllMealLogs();

    }

    /**
     * Returns the last meal eaten by the user
     * @return all the meal logs
     */
    public MealLog getLastUserMealData(){

        return userLogs.getMealLog(-1);

    }

    /**
     *
     * @return Calorie intake for the day
     */
    public int getDailyCalorieData(){

        Calendar c = Calendar.getInstance();

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd");
        String day = (dateFormat.format(c.getTime()));

        int calories = 0;
        List<MealLog> mealLogsList = getAllUserMealData();

        for (int index = 0; index < mealLogsList.size(); index++) {

            if(mealLogsList.get(index).getDate().substring(0, 2).equalsIgnoreCase(day)){
                calories += mealLogsList.get(index).getCalories();
            }

        }


        return calories;

    }

    /**
     *
     * @return Calorie intake from previous day
     */
    public int getYesterdaysCalorieData(){

        Calendar c = Calendar.getInstance();

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd");
        String day = (dateFormat.format(c.getTime()));

        int yesterdayInt = Integer.parseInt(day) - 1;
        String yesterdayString;
        if(yesterdayInt<10){
            yesterdayString = "0" + Integer.toString(yesterdayInt);
        }
        else{
            yesterdayString = Integer.toString(yesterdayInt);
        }


        int calories = 0;
        List<MealLog> mealLogsList = getAllUserMealData();

        for (int index = 0; index < mealLogsList.size(); index++) {

            if(mealLogsList.get(index).getDate().substring(0, 2).equalsIgnoreCase(yesterdayString)){
                calories += mealLogsList.get(index).getCalories();
            }

        }

        return calories;

    }

}
