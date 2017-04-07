package com.a3a04.android.lifestylist.workout;

import android.content.Context;

import com.a3a04.android.lifestylist.database.DatabaseHandler;
import com.a3a04.android.lifestylist.database.SleepLog;
import com.a3a04.android.lifestylist.database.MealLog;
import com.a3a04.android.lifestylist.database.WorkoutLog;
import com.a3a04.android.lifestylist.main.MainController;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

/**
 * Created by mattshortt on 2017-03-31.
 */

public class WorkoutController {

    DatabaseHandler userLogs;
    MainController mainControl = new MainController();
    List<MealLog> mealLogs;
    List<SleepLog> sleepLogs;

    protected WorkoutController(){
        super();
    }

    public WorkoutController(Context context){
        userLogs = new DatabaseHandler(context);
    }

    protected void generateRecommendation(){

        updateRecommendation();

        if(userLogs.getPersonalData(1).getMealToggle()==1 && userLogs.getPersonalData(1).getSleepToggle()==1){//Both on

        }
        else if(userLogs.getPersonalData(1).getMealToggle()==0 && userLogs.getPersonalData(1).getSleepToggle()==1){//Meal off, Sleep on

        }
        else if(userLogs.getPersonalData(1).getMealToggle()==1 && userLogs.getPersonalData(1).getSleepToggle()==0){//Meal on, Sleep off

        }
        else{ //both Meal and Sleep subsystems are off

        }

    }

    protected void updateRecommendation(){

        mealLogs = mainControl.getMealData();
        sleepLogs = mainControl.getSleepData();

    }

    /**
     *
     * @return all the workout logs
     */
    public List<WorkoutLog> getAllUserWorkoutData(){

        return userLogs.getAllWorkoutLogs();

    }

    /**
     *
     * @return Acive Minutes intake for the day
     */
    public int getDailyActiveMinutesData(){

        Calendar c = Calendar.getInstance();

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd");
        String day = (dateFormat.format(c.getTime()));

        int activeMinutes = 0;
        List<WorkoutLog> workoutLogsList = getAllUserWorkoutData();

        for (int index = 0; index < workoutLogsList.size(); index++) {

            if(workoutLogsList.get(index).getDate().substring(0, 2).equalsIgnoreCase(day)){
                activeMinutes += workoutLogsList.get(index).getActiveMins();
            }

        }


        return activeMinutes;

    }

    /**
     *
     * @return Active Minutes intake from previous day
     */
    public int getYesterdaysActiveMinutesData(){

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


        int activeMinutes = 0;
        List<WorkoutLog> workoutLogsList = getAllUserWorkoutData();

        for (int index = 0; index < workoutLogsList.size(); index++) {

            if(workoutLogsList.get(index).getDate().substring(0, 2).equalsIgnoreCase(yesterdayString)){
                activeMinutes += workoutLogsList.get(index).getActiveMins();
            }

        }

        return activeMinutes;

    }




}
