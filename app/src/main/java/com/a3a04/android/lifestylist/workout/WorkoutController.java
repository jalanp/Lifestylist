package com.a3a04.android.lifestylist.workout;

import android.content.Context;
import android.content.SharedPreferences;

import com.a3a04.android.lifestylist.database.DatabaseHandler;
import com.a3a04.android.lifestylist.database.SleepLog;
import com.a3a04.android.lifestylist.database.MealLog;
import com.a3a04.android.lifestylist.database.WorkoutLog;
import com.a3a04.android.lifestylist.main.MainController;
import com.a3a04.android.lifestylist.sleep.SleepController;

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
    int actMinToday;
    int actMinYesterday;
    Context c;
    int mealToggle;
    int sleepToggle;
    //SleepController sleepControl = new SleepController();


    protected WorkoutController(){
        super();
    }

    public WorkoutController(Context context){
        userLogs = new DatabaseHandler(context);
        c = context;
    }

    protected int generateRecommendation(){

        updateRecommendation();
        SharedPreferences prefs = c.getSharedPreferences("SharedPref", Context.MODE_PRIVATE);
        mealToggle = prefs.getInt("MealToggle", -1);
        sleepToggle = prefs.getInt("SleepToggle", -1);

        int recommendedActiveMins = 30;
        actMinToday     = getDailyActiveMinutesData();
        actMinYesterday = getYesterdaysActiveMinutesData();

        if(mealToggle==1 && sleepToggle==1){//Both on
            int excessCalories = 100;
            double weight = 150/2.2;

            recommendedActiveMins = onlyWorkoutActiveMins();

            int extraMealMins = Double.valueOf(excessCalories / (8 * 0.0175 * weight)).intValue();

            double totalSleepTime = mainControl.getTimeSlept(1);
            int extraSleepMins = 0;

            if (totalSleepTime > 4.0 && totalSleepTime < 8.0){

                extraSleepMins = (int) ((8.0 - totalSleepTime)*0.125*recommendedActiveMins);

            }
            else if (totalSleepTime < 4.0){

                extraSleepMins = 0;

            }
            else if(totalSleepTime > 8.0){

                if(totalSleepTime <= 12.0){
                    extraSleepMins = (int) ((totalSleepTime-8.0)*(30/4));
                }
                else{
                    extraSleepMins = 30;
                }
            }
            recommendedActiveMins = recommendedActiveMins + ((extraMealMins + extraSleepMins) / 2);
        }
        else if(mealToggle==0 && sleepToggle==1){//Meal off, Sleep on

            recommendedActiveMins = onlyWorkoutActiveMins();
            double totalSleepTime = mainControl.getTimeSlept(1);

            if (totalSleepTime > 4.0 && totalSleepTime < 8.0){

                recommendedActiveMins = (int) ((8.0 - totalSleepTime)*0.125*recommendedActiveMins);

            }
            else if (totalSleepTime < 4.0){

                recommendedActiveMins = 0;

            }
            else if(totalSleepTime > 8.0){

                if(totalSleepTime <= 12.0){
                    recommendedActiveMins = recommendedActiveMins + (int) ((totalSleepTime-8.0)*(30/4));
                }
                else{
                    recommendedActiveMins = recommendedActiveMins + 30;
                }
            }
        }
        else if(mealToggle==1 && sleepToggle==0){//Meal on, Sleep off
            int excessCalories = 100;
            double weight = 150/2.2;

            int extraMins = Double.valueOf(excessCalories / (8 * 0.0175 * weight)).intValue();
            recommendedActiveMins = onlyWorkoutActiveMins() + extraMins;
        }
        else{ //both Meal and Sleep subsystems are off
            recommendedActiveMins = onlyWorkoutActiveMins();
        }
        return recommendedActiveMins;
    }


    protected int onlyWorkoutActiveMins(){

        int recommendedActiveMins;

        if(actMinToday < 30 && actMinYesterday < 30){
            recommendedActiveMins = (30 - actMinToday) + (int)((30 - actMinYesterday)*0.5);
        }
        else if(actMinToday < 30){
            recommendedActiveMins = 30 - actMinToday;
        }
        else{
            recommendedActiveMins = 0;
        }

        return recommendedActiveMins;

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
