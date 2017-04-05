package com.a3a04.android.lifestylist.meal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.a3a04.android.lifestylist.R;
import com.a3a04.android.lifestylist.database.MealLog;
import com.a3a04.android.lifestylist.database.DatabaseHandler;

import java.text.SimpleDateFormat;
import java.util.*;

public class MealActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal);

        TextView t = (TextView)findViewById(R.id.textView);
        t.setMovementMethod(new ScrollingMovementMethod());
        DatabaseHandler db = new DatabaseHandler(getApplicationContext());

        List<MealLog> stuff = db.getAllMealLogs();
        for (int i = 0; i < stuff.size(); i++){
            t.append(stuff.get(i).getID() + "\t\t"
                    + stuff.get(i).getDate() + "\t\t"
                    + stuff.get(i).getTime() + "\t\t"
                    + stuff.get(i).getCalories());
            t.append("\n");
        }

        db.closeDB();
    }

    public void addItem(View view){
        DatabaseHandler db = new DatabaseHandler(getApplicationContext());

        Calendar c = Calendar.getInstance();

        SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm:ss");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        String time = (timeFormat.format(c.getTime()));
        String date = (dateFormat.format(c.getTime()));

        //String date = c.get(Calendar.YEAR) + "/" + c.get(Calendar.MONTH) + "/" + c.get(Calendar.DAY_OF_MONTH);
        //String time = c.get(Calendar.HOUR) + ":" + c.get(Calendar.MINUTE) + ":" + c.get(Calendar.SECOND);

        int x = 0;

        try {
            EditText t = (EditText)findViewById(R.id.editText);
            x = Integer.parseInt(t.getText().toString());
        } catch(NumberFormatException e){
            Log.v("error","not number");
        }

        db.addLog(new MealLog(date, time, x));

        db.closeDB();

        this.updateTextView();
    }

    public void updateTextView(){
        TextView t = (TextView)findViewById(R.id.textView);
        DatabaseHandler db = new DatabaseHandler(getApplicationContext());

        t.setText("");

        List<MealLog> stuff = db.getAllMealLogs();
        for (int i = 0; i < stuff.size(); i++){
            t.append(stuff.get(i).getID() + "\t\t"
                    + stuff.get(i).getDate() + "\t\t"
                    + stuff.get(i).getTime() + "\t\t"
                    + stuff.get(i).getCalories());
            t.append("\n");
        }

        db.closeDB();
    }

    public void removeItem(View view){
        DatabaseHandler db = new DatabaseHandler(getApplicationContext());

        List<MealLog> stuff = db.getAllMealLogs();
        if (stuff.size() > 0) {
            db.deleteMealLog(stuff.get(stuff.size() - 1).getID());
        }

        db.closeDB();

        this.updateTextView();
    }
}
