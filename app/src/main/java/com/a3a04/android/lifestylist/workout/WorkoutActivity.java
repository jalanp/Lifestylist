package com.a3a04.android.lifestylist.workout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.a3a04.android.lifestylist.R;
import com.a3a04.android.lifestylist.database.DatabaseHandler;
import com.a3a04.android.lifestylist.database.WorkoutLog;
import com.a3a04.android.lifestylist.main.MainActivity;
import com.a3a04.android.lifestylist.main.MainController;
import com.a3a04.android.lifestylist.main.SettingsActivity;
import com.a3a04.android.lifestylist.meal.MapsActivity;
import com.a3a04.android.lifestylist.meal.MealActivity;
import com.a3a04.android.lifestylist.sleep.SleepActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class WorkoutActivity extends AppCompatActivity {

    ActionBar mActionBar;
    Button mMealBtn, mWorkoutBtn, mSleepBtn;
    Button mGymBtn;
    MainController mainControl;
    WorkoutController workoutControl;
    public static final String MY_PREFS_NAME = "SharedPref";
    int mealToggle;
    int sleepToggle;
    int workoutToggle;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);

        mainControl = new MainController(getApplicationContext());
        workoutControl = new WorkoutController(getApplicationContext());
        renderLayout();

    }

    public void renderLayout(){

        mActionBar = getSupportActionBar();
        mActionBar.setTitle("Workout");
        mActionBar.setHomeButtonEnabled(true);
        mActionBar.setDisplayHomeAsUpEnabled(true);
        mActionBar.setHomeAsUpIndicator(R.drawable.ic_home_white_24dp);
        mActionBar.show();

        mMealBtn = (Button) findViewById(R.id.btn_meal);
        mWorkoutBtn = (Button) findViewById(R.id.btn_workout);
        mSleepBtn = (Button) findViewById(R.id.btn_sleep);

        mGymBtn = (Button) findViewById(R.id.findGym);

        mMealBtn.setTextColor(getResources().getColor(R.color.colorBlack));
        mWorkoutBtn.setTextColor(getResources().getColor(R.color.colorPrimary));
        mSleepBtn.setTextColor(getResources().getColor(R.color.colorBlack));

        TextView t = (TextView)findViewById(R.id.workoutLog);
        t.setMovementMethod(new ScrollingMovementMethod());
        DatabaseHandler db = new DatabaseHandler(getApplicationContext());

//        List<WorkoutLog> stuff = db.getAllWorkoutLogs();
//        for (int i = 0; i < stuff.size(); i++){
//            t.append(stuff.get(i).getID() + "\t\t"
//                    + stuff.get(i).getDate() + "\t\t"
//                    + stuff.get(i).getTime() + "\t\t"
//                    + stuff.get(i).getActiveMins());
//            t.append("\n");
//        }
//        int recActMins =  mainControl.getRecommendedActiveMinutes();
//        t.append("\n");
//        t.append("Recommended Active Minutes:      " + recActMins);
//        t.append("\n");
//        t.append("Active Minutes Today:      " + workoutControl.getDailyActiveMinutesData());
//        t.append("\n");
//        t.append("Active Minutes Yesterday:  " + workoutControl.getYesterdaysActiveMinutesData());

        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        int restoredText = prefs.getInt("FirstTime", -1);
        if (restoredText == -1) {
            SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
            editor.putInt("FirstTime", 1);
            editor.putInt("SleepToggle", 1);
            editor.putInt("MealToggle", 1);
            editor.putInt("WorkoutToggle", 1);
            editor.commit();
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

        }

        db.closeDB();
        try {
            this.updateTextView();
        }catch (Exception e){

        }
    }

    public void logActiveMinutes(View view){
        DatabaseHandler db = new DatabaseHandler(getApplicationContext());

        Calendar c = Calendar.getInstance();

        SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm:ss");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        String time = (timeFormat.format(c.getTime()));
        String date = (dateFormat.format(c.getTime()));

        //String date = c.get(Calendar.YEAR) + "/" + c.get(Calendar.MONTH) + "/" + c.get(Calendar.DAY_OF_MONTH);
        //String time = c.get(Calendar.HOUR) + ":" + c.get(Calendar.MINUTE) + ":" + c.get(Calendar.SECOND);

        int x = 0;


        EditText t = (EditText)findViewById(R.id.activeMinutes);
        x = Integer.parseInt(t.getText().toString());

        db.addLog(new WorkoutLog(date, time, x));

        db.closeDB();

        this.updateTextView();
        mainControl.updateRecommendationRequest(2);
    }

    public void updateTextView(){
        TextView t = (TextView)findViewById(R.id.workoutLog);
        DatabaseHandler db = new DatabaseHandler(getApplicationContext());

        t.setText("");

        List<WorkoutLog> stuff = db.getAllWorkoutLogs();
        for (int i = 0; i < stuff.size(); i++){
            t.append(stuff.get(i).getID() + "\t\t"
                    + stuff.get(i).getDate() + "\t\t"
                    + stuff.get(i).getTime() + "\t\t"
                    + stuff.get(i).getActiveMins());
            t.append("\n");
        }

        int recActMins =  mainControl.getRecommendedActiveMinutes();
        t.append("\n");
        t.append("Recommended Active Minutes:      " + recActMins);
        t.append("\n");
        t.append("Active Minutes Today:      " + workoutControl.getDailyActiveMinutesData());
        t.append("\n");
        t.append("Active Minutes Yesterday:  " + workoutControl.getYesterdaysActiveMinutesData());

        db.closeDB();
    }

    //DEPRECATED
//    public void removeItem(View view){
//        DatabaseHandler db = new DatabaseHandler(getApplicationContext());
//
//        List<WorkoutLog> stuff = db.getAllWorkoutLogs();
//        if (stuff.size() > 0) {
//            db.deleteWorkoutLog(stuff.get(stuff.size() - 1).getID());
//        }
//
//        db.closeDB();
//
//        this.updateTextView();
//    }

    public void openMeal(View view){
        Intent intent = new Intent(WorkoutActivity.this, MealActivity.class);
        startActivity(intent);
        finish();
    }

    public void openWorkout(View view){
        Intent intent = new Intent(WorkoutActivity.this, WorkoutActivity.class);
        startActivity(intent);
        finish();
    }

    public void openSleep(View view){
        Intent intent = new Intent(WorkoutActivity.this, SleepActivity.class);
        startActivity(intent);
        finish();
    }

    public void openMap(View view){
        Intent intent = new Intent(WorkoutActivity.this, MapsActivity.class);
        intent.putExtra("activity","workout");
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
                finish();
                return true;

            case R.id.action_settings:
                Intent mSettingIntent = new Intent(WorkoutActivity.this, SettingsActivity.class);
                startActivity(mSettingIntent);
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }
}
