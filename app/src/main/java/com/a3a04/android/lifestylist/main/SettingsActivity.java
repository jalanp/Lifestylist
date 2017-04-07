package com.a3a04.android.lifestylist.main;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.a3a04.android.lifestylist.R;
import com.a3a04.android.lifestylist.database.DatabaseHandler;

import com.a3a04.android.lifestylist.database.PersonalData;
import com.a3a04.android.lifestylist.database.WorkoutLog;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;


public class SettingsActivity extends AppCompatActivity {

    ActionBar mActionBar;
    EditText input1,input2,input3,input4,input5;
    ToggleButton sleep,meal,workout;
    SeekBar Fitbit;
    DatabaseHandler db;


    public static final String MY_PREFS_NAME = "SharedPref";
    int mealToggle;
    int sleepToggle;
    int workoutToggle;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        mActionBar = getSupportActionBar();
        mActionBar.setTitle("Settings");
        mActionBar.setIcon(R.drawable.ic_home_white_24dp);
        mActionBar.setHomeButtonEnabled(true);
        mActionBar.setDisplayHomeAsUpEnabled(true);
        mActionBar.show();

        meal = (ToggleButton) findViewById(R.id.MealToggle);
        sleep = (ToggleButton) findViewById(R.id.SleepToggle);
        workout = (ToggleButton) findViewById(R.id.WorkoutToggle);

        //meal.isChecked();

        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        mealToggle = prefs.getInt("MealToggle", -1);
        sleepToggle = prefs.getInt("SleepToggle", -1);
        workoutToggle = prefs.getInt("WorkoutToggle", -1);

        if (mealToggle == 0){
            meal.setChecked(false);
        }

        if (mealToggle == 1){
            meal.setChecked(true);
        }

        if (sleepToggle == 0){
            sleep.setChecked(false);
        }

        if (sleepToggle == 1){
            sleep.setChecked(true);
        }

        if (workoutToggle == 0){
            workout.setChecked(false);
        }

        if (workoutToggle == 1){
            workout.setChecked(true);
        }

        EditText t1 = (EditText)findViewById(R.id.input1);
        EditText t2 = (EditText)findViewById(R.id.input2);
        EditText t3 = (EditText)findViewById(R.id.input3);
        EditText t4 = (EditText)findViewById(R.id.input4);
        EditText t5 = (EditText)findViewById(R.id.input5);

        DatabaseHandler db = new DatabaseHandler(getApplicationContext());
        PersonalData user;
        user = db.getPersonalData(1);



        t1.setText(user.getName());
        if(user.getGender() == 0){
            t2.setText("M");
        }
        else if(user.getGender() == 1){
            t2.setText("F");
        }
        t3.setText(Integer.toString(user.getAge()));
        t4.setText(Integer.toString(user.getHeight()));
        t5.setText(Integer.toString(user.getWeight()));

        db.close();


    }

    public void logSettings(View view){
        DatabaseHandler db = new DatabaseHandler(getApplicationContext());

        String name;
        int gender = 0; // M=0, F=1
        int age;
        int height;
        int weight;




        EditText t1 = (EditText)findViewById(R.id.input1);
        name = t1.getText().toString();

        EditText t2 = (EditText)findViewById(R.id.input2);
        if(t2.getText().toString().equalsIgnoreCase("M")){
            gender = 0;
        }
        else if(t2.getText().toString().equalsIgnoreCase("F")){
            gender = 1;
        }

        EditText t3 = (EditText)findViewById(R.id.input3);
        age = Integer.parseInt(t3.getText().toString());

        EditText t4 = (EditText)findViewById(R.id.input4);
        height = Integer.parseInt(t4.getText().toString());

        EditText t5 = (EditText)findViewById(R.id.input5);
        weight = Integer.parseInt(t5.getText().toString());

        db.updateData(new PersonalData(1, name, "default", age, height, weight, 0, gender, 0, 0, 0, 0));


        db.closeDB();

        Intent intent = new Intent(SettingsActivity.this,MainActivity.class);
        startActivity(intent);
        finish();

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(SettingsActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }



    public void mealToggleChecker(View view){
        if ((!sleep.isChecked()) & (!workout.isChecked())){
            meal.setChecked(true);
        }

        SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();

        ToggleButton toggleMeal = (ToggleButton) view.findViewById(R.id.MealToggle);
        String toggleValue = toggleMeal.getText().toString();
        if (toggleValue.contains("OFF")){
            editor.putInt("MealToggle", 0);
            editor.commit();
            Log.d("test1","Commit");


        } else if (toggleValue.contains("ON")){
            editor.putInt("MealToggle", 1);
            editor.commit();
            Log.d("test1","Commit");

        }


    }

    public void sleepToggleChecker(View view){
        if ((!meal.isChecked()) & (!workout.isChecked())){
            sleep.setChecked(true);
        }

        SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();

        ToggleButton toggleMeal = (ToggleButton) view.findViewById(R.id.SleepToggle);
        String toggleValue = toggleMeal.getText().toString();
        if (toggleValue.contains("OFF")){
            editor.putInt("SleepToggle", 0);
            editor.commit();
            Log.d("test1","Commit");

        } else if (toggleValue.contains("ON")){
            editor.putInt("SleepToggle", 1);
            editor.commit();
            Log.d("test1","Commit");

        }

    }

    public void workoutToggleChecker(View view){
        if ((!meal.isChecked()) & (!sleep.isChecked())){
            workout.setChecked(true);
        }

        SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();

        //db = new DatabaseHandler(getApplicationContext());
        ToggleButton toggleMeal = (ToggleButton) view.findViewById(R.id.WorkoutToggle);
        String toggleValue = toggleMeal.getText().toString();
        if (toggleValue.contains("OFF")){
            //db.getPersonalData(1).setWorkoutToggle(0)
            editor.putInt("WorkoutToggle", 0);
            editor.commit();
            Log.d("test1","Commit");

        } else if (toggleValue.contains("ON")){
            //db.getPersonalData(1).setWorkoutToggle(1);
            editor.putInt("WorkoutToggle", 1);
            editor.commit();
            Log.d("test1","Commit");

        }
        //db.closeDB();

    }
}
