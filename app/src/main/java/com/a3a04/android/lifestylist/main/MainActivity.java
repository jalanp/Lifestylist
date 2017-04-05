package com.a3a04.android.lifestylist.main;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.a3a04.android.lifestylist.R;
import com.a3a04.android.lifestylist.meal.MealActivity;
import com.a3a04.android.lifestylist.sleep.SleepActivity;
import com.a3a04.android.lifestylist.workout.WorkoutActivity;


// Home Screen
public class MainActivity extends AppCompatActivity {

    ActionBar mActionBar;
    Button mMealBtn, mWorkoutBtn, mSleepBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mActionBar = getSupportActionBar();
        mActionBar.setTitle("Dashboard");
        mActionBar.setHomeButtonEnabled(true);
        mActionBar.setDisplayHomeAsUpEnabled(true);
        mActionBar.setHomeAsUpIndicator(R.drawable.ic_home_white_24dp);
        mActionBar.show();

        mMealBtn = (Button) findViewById(R.id.btn_meal);
        mWorkoutBtn = (Button) findViewById(R.id.btn_workout);
        mSleepBtn = (Button) findViewById(R.id.btn_sleep);

        mMealBtn.setTextColor(getResources().getColor(R.color.colorBlack));
        mWorkoutBtn.setTextColor(getResources().getColor(R.color.colorBlack));
        mSleepBtn.setTextColor(getResources().getColor(R.color.colorBlack));
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

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_actionbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                //Already on the home dashboard screen
                return true;

            case R.id.action_settings:
                Intent mSettingIntent = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(mSettingIntent);
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

}
