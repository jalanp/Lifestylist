package com.a3a04.android.lifestylist.main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.a3a04.android.lifestylist.R;
import com.a3a04.android.lifestylist.meal.MealActivity;
import com.a3a04.android.lifestylist.sleep.SleepActivity;
import com.a3a04.android.lifestylist.workout.WorkoutActivity;


// Home Screen
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void openMeal(View view){
        Intent intent = new Intent(MainActivity.this, MealActivity.class);
        startActivity(intent);
    }

    public void openWorkout(View view){
        Intent intent = new Intent(MainActivity.this, WorkoutActivity.class);
        startActivity(intent);
    }

    public void openSleep(View view){
        Intent intent = new Intent(MainActivity.this, SleepActivity.class);
        startActivity(intent);
    }

    // WASSSSUPPPPPPP test
    // test

}
