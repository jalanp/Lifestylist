package com.a3a04.android.lifestylist.main;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.a3a04.android.lifestylist.R;
import com.a3a04.android.lifestylist.database.DatabaseHandler;
import com.a3a04.android.lifestylist.database.PersonalData;
import com.a3a04.android.lifestylist.meal.MealActivity;
import com.a3a04.android.lifestylist.sleep.SleepActivity;
import com.a3a04.android.lifestylist.workout.WorkoutActivity;

import java.util.List;


// Home Screen
public class MainActivity extends AppCompatActivity {

    ActionBar mActionBar;
    Button mMealBtn, mWorkoutBtn, mSleepBtn,mSettingsBtn;
    public static final String MY_PREFS_NAME = "SharedPref";
    int mealToggle;
    int sleepToggle;
    int workoutToggle;

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

        //DatabaseHandler db = new DatabaseHandler(getApplicationContext());

        //db.addData(new PersonalData());



//        int mealToggle = db.getPersonalData(1).getMealToggle();
//        int sleepToggle = db.getPersonalData(1).getSleepToggle();
//        int workoutToggle = db.getPersonalData(1).getWorkoutToggle();
//
//        Log.d("test","A: "+ db.getPersonalData(1).getMealToggle());
//        Log.d("test","B: "+db.getPersonalData(1).getWorkoutToggle());
//        Log.d("test","C: "+db.getPersonalData(1).getSleepToggle());


        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        int restoredText = prefs.getInt("FirstTime", -1);
        if (restoredText == -1) {
            SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
            editor.putInt("FirstTime", 1);
            editor.putInt("SleepToggle", 1);
            editor.putInt("MealToggle", 1);
            editor.putInt("WorkoutToggle", 1);
            editor.commit();
            DatabaseHandler db = new DatabaseHandler(getApplicationContext());
            db.addData(new PersonalData());
            db.close();
            Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
            startActivity(intent);
            finish();
        } else {

            mealToggle = prefs.getInt("MealToggle", -1);
            sleepToggle = prefs.getInt("SleepToggle", -1);
            workoutToggle = prefs.getInt("WorkoutToggle", -1);

            Log.d("test1","A"+ mealToggle);
            Log.d("test1","B"+sleepToggle);
            Log.d("test1","C"+workoutToggle);
            Log.d("test1","Commit");

            if (mealToggle == 0){
                mMealBtn.setEnabled(false);
                mMealBtn.setTextColor(getResources().getColor(R.color.colorGrey));

            }

            if (mealToggle == 1) {
                mMealBtn.setEnabled(true);
                mMealBtn.setTextColor(getResources().getColor(R.color.colorBlack));

            }
            Log.d("test",String.valueOf(sleepToggle));

            if (sleepToggle == 0){
                Log.d("test",String.valueOf(sleepToggle));
                mSleepBtn.setEnabled(false);
                mSleepBtn.setTextColor(getResources().getColor(R.color.colorGrey));

            }
            if (sleepToggle == 1){
                Log.d("test","onnnnn");
                mSleepBtn.setEnabled(true);
                mSleepBtn.setTextColor(getResources().getColor(R.color.colorBlack));

            }
            if (workoutToggle == 0){
                mWorkoutBtn.setEnabled(false);
                mWorkoutBtn.setTextColor(getResources().getColor(R.color.colorGrey));

            }
            if (workoutToggle == 1){
                mWorkoutBtn.setEnabled(true);
                mWorkoutBtn.setTextColor(getResources().getColor(R.color.colorBlack));
            }
            updateTextView();

        }




       // db.closeDB();
    }

    public void updateTextView(){
        TextView t = (TextView)findViewById(R.id.workoutSummary);
        DatabaseHandler db = new DatabaseHandler(getApplicationContext());

        t.setText("");

        PersonalData user = db.getPersonalData(1);

        t.append("Name: " + user.getName() + "\n");
        t.append("Age: " + user.getAge() + "\n");
        t.append("Gender: " + user.getGender() + "\n");
        t.append("Height: " + user.getHeight() + "\n");
        t.append("Weight: " + user.getWeight() + "\n");




        db.closeDB();
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
                finish();
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

}
