package com.a3a04.android.lifestylist.meal;

import android.content.Context;
import android.util.Log;
import android.content.SharedPreferences;


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

    List<MealLog> todaysLogs;
    ArrayList<String[]> mealItems, breakfasts, lunches, dinners, snacks;
    int calorieBenchmark;

    Context c;
    int sleepToggle;
    int workoutToggle;

    protected MealController(){
        super();
    }

    public MealController(Context context){
        userLogs = new DatabaseHandler(context);

        String mealDB = "Peanut Butter & Jelly Sandwich;breakfast;400\n" +
                "Greek Yogurt with Maple-Nut Granola;breakfast;400\n" +
                "Scrambled Eggs on Toast;breakfast;600\n" +
                "Sausage, Egg and Cheese Breakfast Bagel;breakfast;600\n" +
                "English Breakfast;breakfast;800\n" +
                "Blueberry, Nut and Cinnamon Oatmeal;breakfast;800\n" +
                "Quinoa & Mozarella Salad;lunch;700\n" +
                "Chicken and Vegetable Pita Pockets;lunch;700\n" +
                "Tofu, Cashew, and Broccoli Stir-Fry;lunch;1000\n" +
                "Bacon and Sweet Potato Burrito;lunch;1000\n" +
                "Grilled Chicken and Vegetable Salad with Wheat Rolls;lunch;1200\n" +
                "Mixed Vegetable Linguini with Sweet Potatoes;lunch;1200\n" +
                "Goat's Cheese, Walnut, and Cranberry Salad with Pita Bread;dinner;700\n" +
                "Shrimp and Cherry Tomato Pasta;dinner;700\n" +
                "Salmon Fillet with Brown Rice and Veggies;dinner;1000\n" +
                "Zucchini and Parmesan Frittata with Potato Wedges;dinner;1000\n" +
                "Spaghetti Bolognese with Green Salad;dinner;1200\n" +
                "Broiled Pork Chop with Baked Potatoes and Mashed Squash;dinner;1200\n" +
                "Fruit;snack;100\n" +
                "Granola Bar;snack;100\n" +
                "Handful of Nuts;snack;200\n" +
                "Vegetable Salad;snack;200\n" +
                "Fruit Smoothie with Nuts;snack;300\n" +
                "Cliff Bar;snack;300";

        String[] lines = mealDB.split("\n");

        mealItems = new ArrayList<String[]>();

        for (int i = 0; i < lines.length; i++){
            mealItems.add(lines[i].split(";"));
        }
        for (String[] m : mealItems){
            switch (m[1]){
                case "breakfast":
                    breakfasts.add(m);
                    break;
                case "lunch":
                    lunches.add(m);
                    break;
                case "dinner":
                    dinners.add(m);
                    break;
                case "snack":
                    snacks.add(m);
                    break;
            }
        }

        c = context;

    }

    public String[] generateRecommendation(){

        updateRecommendation();

        String[] recommendation = {};
        int age = 20;
        int gender = 0;

        switch (gender){
            case 0:
                if (age < 14) {
                    calorieBenchmark = 1800;
                } else if (age < 19){
                    calorieBenchmark = 2100;
                } else if (age < 50){
                    calorieBenchmark = 2600;
                } else {
                    calorieBenchmark = 2100;
                }
                break;
            case 1:
                if (age < 19){
                    calorieBenchmark = 1800;
                } else if (age < 50){
                    calorieBenchmark = 2100;
                } else {
                    calorieBenchmark = 1800;
                }
        }

        SharedPreferences prefs = c.getSharedPreferences("SharedPref", Context.MODE_PRIVATE);
        sleepToggle = prefs.getInt("SleepToggle", -1);
        workoutToggle = prefs.getInt("WorkoutToggle", -1);


        if(workoutToggle==1 && sleepToggle==1){//Both on
            double timeSlept = 7.5;
            double weightLB = 140;
            int activeMins = 30;
            double weightKG = 140/2.2;

            int burnedCalsWorkout = Double.valueOf(0.0175 * 8 * weightLB * activeMins).intValue();
            int burnedCalsSleep = Double.valueOf(0.42 * weightKG * timeSlept).intValue();
            recommendation = recommendMeal(getCurrentCalories() + burnedCalsWorkout + burnedCalsSleep);
        }
        else if(workoutToggle==0 && sleepToggle==1){//Workout off, Sleep on
            double timeSlept = 7.5;
            double weight = 140;

            int burnedCals = Double.valueOf(0.42 * weight * timeSlept).intValue();
            recommendation = recommendMeal(getCurrentCalories() + burnedCals);
        }
        else if(workoutToggle==1 && sleepToggle==0){//Workout on, Sleep off
            int activeMins = 30;
            double weight = 140/2.2;

            int burnedCals = Double.valueOf(0.0175 * 8 * weight * activeMins).intValue();
            recommendation = recommendMeal(getCurrentCalories() + burnedCals);
        }
        else{ //both Workout and Sleep subsystems are off
            recommendation = recommendMeal(getCurrentCalories());
        }

        return recommendation;
    }

    protected int getCurrentCalories(){
        Calendar c = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String date = (dateFormat.format(c.getTime()));
        todaysLogs = new ArrayList<MealLog>();

        for (MealLog m : userLogs.getAllMealLogs()) {
            if (m.getDate().equals(date)) {
                todaysLogs.add(m);
            }
        }
        int currentCalories = calorieBenchmark;
        for (MealLog m : todaysLogs){
            currentCalories -= m.getCalories();
        }
        return currentCalories;
    }

    protected String[] recommendMeal(int cals){
        String[] recommendation = {};

        switch (todaysLogs.size()){
            case 3:
                if (cals >= 800){
                    recommendation = recommendDinner(cals);
                } else {
                    recommendation = recommendSnack(cals);
                }
                break;
            case 2:
                recommendation = recommendDinner(cals);
                break;
            case 1:
                recommendation = recommendLunch(cals/2);
                break;
            case 0:
                recommendation = recommendBreakfast(cals/3);
                break;
        }
        return recommendation;
    }

    protected String[] recommendBreakfast(int cals){
        if (cals >= 800){
            Random r = new Random();
            return breakfasts.get(r.nextInt(2)+4);
        } else {
            Random r = new Random();
            return breakfasts.get(r.nextInt(4));
        }
    }

    protected String[] recommendLunch(int cals){
        if (cals >= 1200){
            Random r = new Random();
            return lunches.get(r.nextInt(2)+4);
        } else if (cals >= 1000){
            Random r = new Random();
            return lunches.get(r.nextInt(2)+2);
        } else {
            Random r = new Random();
            return lunches.get(r.nextInt(2));
        }
    }

    protected String[] recommendDinner(int cals){
        if (cals >= 1200){
            Random r = new Random();
            return dinners.get(r.nextInt(2)+4);
        } else if (cals >= 1000){
            Random r = new Random();
            return dinners.get(r.nextInt(2)+2);
        } else {
            Random r = new Random();
            return dinners.get(r.nextInt(2));
        }
    }

    protected String[] recommendSnack(int cals){
        if (cals >= 300){
            Random r = new Random();
            return snacks.get(r.nextInt(2)+4);
        } else if (cals >= 200){
            Random r = new Random();
            return snacks.get(r.nextInt(2)+2);
        } else {
            Random r = new Random();
            return snacks.get(r.nextInt(2));
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

        return userLogs.getMealLog(getAllUserMealData().size() -1);

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
