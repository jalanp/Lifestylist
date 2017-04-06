package com.a3a04.android.lifestylist.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.*;

/**
 * Created by Temporary on 2017-04-03.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "dataManager";
    private static final String TABLE_USER_MEAL = "userMeal";
    private static final String TABLE_USER_WORKOUT = "userWorkout";
    private static final String TABLE_USER_SLEEP = "userSleep";
    private static final String TABLE_USER_MIA = "userMIA";
    private static final String TABLE_USER_PERSONAL = "userPersonal";

    private static final String KEY_ID = "id";
    private static final String KEY_DATE = "date";
    private static final String KEY_TIME = "time";
    private static final String KEY_CALORIES = "calories";
    private static final String KEY_ACTIVEMINS = "activeMins";
    private static final String KEY_SLEEPTIME = "sleepTime";
    private static final String KEY_WAKETIME = "wakeTime";
    private static final String KEY_FROMDATE = "fromDate";
    private static final String KEY_TODATE = "toDate";
    private static final String KEY_DETAILS = "details";

    private static final String KEY_NAME = "name";
    private static final String KEY_ADDRESS = "address";
    private static final String KEY_AGE = "age";
    private static final String KEY_HEIGHT = "height";
    private static final String KEY_WEIGHT = "weight";
    private static final String KEY_INTEGRATION = "integration";
    private static final String KEY_GENDER = "gender";
    private static final String KEY_FIRST_LOGIN = "firstLogin";
    private static final String KEY_MEAL_TOGGLE = "mealToggle";
    private static final String KEY_WORKOUT_TOGGLE = "workoutToggle";
    private static final String KEY_SLEEP_TOGGLE = "sleepToggle";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USER_MEAL_TABLE = "CREATE TABLE " + TABLE_USER_MEAL + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_DATE + " TEXT,"
                + KEY_TIME + " TEXT,"
                + KEY_CALORIES + " INTEGER" + ")";
        String CREATE_USER_WORKOUT_TABLE = "CREATE TABLE " + TABLE_USER_WORKOUT + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_DATE + " TEXT,"
                + KEY_TIME + " TEXT,"
                + KEY_ACTIVEMINS + " INTEGER" + ")";
        String CREATE_USER_SLEEP_TABLE = "CREATE TABLE " + TABLE_USER_SLEEP + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_DATE + " TEXT,"
                + KEY_SLEEPTIME + " TEXT,"
                + KEY_WAKETIME + " TEXT" + ")";
        String CREATE_USER_MIA_TABLE = "CREATE TABLE " + TABLE_USER_MIA + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_FROMDATE + " TEXT,"
                + KEY_TODATE + " TEXT,"
                + KEY_DETAILS + " TEXT" + ")";
        String CREATE_USER_PERSONAL_TABLE = "CREATE TABLE " + TABLE_USER_PERSONAL + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_NAME + " TEXT,"
                + KEY_ADDRESS + " TEXT,"
                + KEY_AGE + " INTEGER,"
                + KEY_HEIGHT + " INTEGER,"
                + KEY_WEIGHT + " INTEGER,"
                + KEY_INTEGRATION + " INTEGER,"
                + KEY_GENDER + " INTEGER,"
                + KEY_MEAL_TOGGLE + " INTEGER,"
                + KEY_WORKOUT_TOGGLE + " INTEGER,"
                + KEY_SLEEP_TOGGLE + " INTEGER,"
                + KEY_FIRST_LOGIN + " INTEGER" + ")";
        db.execSQL(CREATE_USER_MEAL_TABLE);
        db.execSQL(CREATE_USER_WORKOUT_TABLE);
        db.execSQL(CREATE_USER_SLEEP_TABLE);
        db.execSQL(CREATE_USER_MIA_TABLE);
        db.execSQL(CREATE_USER_PERSONAL_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER_MEAL);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER_WORKOUT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER_SLEEP);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER_MIA);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER_PERSONAL);
        onCreate(db);
    }

    //CRUD (CREATE, READ, UPDATE, DELETE)

    //CREATE - Meal Log
    public void addLog(MealLog log) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_DATE, log.getDate());
        values.put(KEY_TIME, log.getTime());
        values.put(KEY_CALORIES, log.getCalories());

        db.insert(TABLE_USER_MEAL, null, values);
        db.close();
    }

    //CREATE - Workout Log
    public void addLog(WorkoutLog log) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_DATE, log.getDate());
        values.put(KEY_TIME, log.getTime());
        values.put(KEY_ACTIVEMINS, log.getActiveMins());

        db.insert(TABLE_USER_WORKOUT, null, values);
        db.close();
    }

    //CREATE - Sleep Log
    public void addLog(SleepLog log) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_DATE, log.getDate());
        values.put(KEY_SLEEPTIME, log.getSleepTime());
        values.put(KEY_WAKETIME, log.getWakeTime());

        db.insert(TABLE_USER_SLEEP, null, values);
        db.close();
    }

    //CREATE - MIA Log
    public void addLog(LogMIA log) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_FROMDATE, log.getFromDate());
        values.put(KEY_TODATE, log.getToDate());
        values.put(KEY_DETAILS, log.getDetails());

        db.insert(TABLE_USER_MIA, null, values);
        db.close();
    }

    //CREATE - Personal Data
    public void addData(PersonalData data) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, data.getName());
        values.put(KEY_ADDRESS, data.getAddress());
        values.put(KEY_AGE, data.getAge());
        values.put(KEY_HEIGHT, data.getHeight());
        values.put(KEY_WEIGHT, data.getWeight());
        values.put(KEY_INTEGRATION, data.getIntegration());
        values.put(KEY_GENDER, data.getGender());
        values.put(KEY_FIRST_LOGIN, data.getFirstLogin());
        values.put(KEY_MEAL_TOGGLE, data.getMealToggle());
        values.put(KEY_WORKOUT_TOGGLE, data.getWorkoutToggle());
        values.put(KEY_SLEEP_TOGGLE, data.getSleepToggle());


        db.insert(TABLE_USER_PERSONAL, null, values);
        db.close();
    }

    //READ - One Meal Log
    public MealLog getMealLog(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_USER_MEAL, new String[] { KEY_ID,
                        KEY_DATE, KEY_TIME, KEY_CALORIES }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        MealLog log = new MealLog(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2), Integer.parseInt(cursor.getString(3)));

        return log;
    }

    //READ - One Workout Log
    public WorkoutLog getWorkoutLog(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_USER_WORKOUT, new String[] { KEY_ID,
                        KEY_DATE, KEY_TIME, KEY_ACTIVEMINS }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        WorkoutLog log = new WorkoutLog(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2), Integer.parseInt(cursor.getString(3)));

        return log;
    }

    //READ - One Sleep Log
    public SleepLog getSleepLog(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_USER_SLEEP, new String[] { KEY_ID,
                        KEY_DATE, KEY_SLEEPTIME, KEY_WAKETIME }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        SleepLog log = new SleepLog(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2), cursor.getString(3));

        return log;
    }

    //READ - One MIA Log
    public LogMIA getMIALog(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_USER_MIA, new String[] { KEY_ID,
                        KEY_FROMDATE, KEY_TODATE, KEY_DETAILS }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        LogMIA log = new LogMIA(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2), cursor.getString(3));

        return log;
    }

    //READ - Personal Data
    public PersonalData getPersonalData(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_USER_PERSONAL, new String[] { KEY_ID,
                        KEY_NAME, KEY_ADDRESS, KEY_AGE, KEY_HEIGHT,
                        KEY_WEIGHT, KEY_INTEGRATION, KEY_GENDER,
                        KEY_FIRST_LOGIN}, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        PersonalData data = new PersonalData(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2), Integer.parseInt(cursor.getString(3)),
                Integer.parseInt(cursor.getString(4)), Integer.parseInt(cursor.getString(5)),
                Integer.parseInt(cursor.getString(6)), Integer.parseInt(cursor.getString(7)),
                Integer.parseInt(cursor.getString(8)), Integer.parseInt(cursor.getString(9)),
                Integer.parseInt(cursor.getString(10)), Integer.parseInt(cursor.getString(11)));

        return data;
    }

    //READ - All Meal Logs
    public List<MealLog> getAllMealLogs() {
        List<MealLog> logList = new ArrayList<MealLog>();

        String selectQuery = "SELECT  * FROM " + TABLE_USER_MEAL;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                MealLog m = new MealLog();
                m.setID(Integer.parseInt(cursor.getString(0)));
                m.setDate(cursor.getString(1));
                m.setTime(cursor.getString(2));
                m.setCalories(Integer.parseInt(cursor.getString(3)));
                logList.add(m);
            } while (cursor.moveToNext());
        }
        cursor.close();

        return logList;
    }

    //READ - All Workout Logs
    public List<WorkoutLog> getAllWorkoutLogs() {
        List<WorkoutLog> logList = new ArrayList<WorkoutLog>();

        String selectQuery = "SELECT  * FROM " + TABLE_USER_WORKOUT;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                WorkoutLog w = new WorkoutLog();
                w.setID(Integer.parseInt(cursor.getString(0)));
                w.setDate(cursor.getString(1));
                w.setTime(cursor.getString(2));
                w.setActiveMins(Integer.parseInt(cursor.getString(3)));
                logList.add(w);
            } while (cursor.moveToNext());
        }
        cursor.close();

        return logList;
    }

    //READ - All Sleep Logs
    public List<SleepLog> getAllSleepLogs() {
        List<SleepLog> logList = new ArrayList<SleepLog>();

        String selectQuery = "SELECT  * FROM " + TABLE_USER_SLEEP;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                SleepLog s = new SleepLog();
                s.setID(Integer.parseInt(cursor.getString(0)));
                s.setDate(cursor.getString(1));
                s.setSleepTime(cursor.getString(2));
                s.setWakeTime(cursor.getString(3));
                logList.add(s);
            } while (cursor.moveToNext());
        }
        cursor.close();

        return logList;
    }

    //UPDATE - Meal Log
    public int updateLog(MealLog log) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_DATE, log.getDate());
        values.put(KEY_TIME, log.getTime());
        values.put(KEY_CALORIES, log.getCalories());

        // updating row
        return db.update(TABLE_USER_MEAL, values, KEY_ID + " = ?",
                new String[] { String.valueOf(log.getID()) });
    }

    //UPDATE - Workout Log
    public int updateLog(WorkoutLog log) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_DATE, log.getDate());
        values.put(KEY_TIME, log.getTime());
        values.put(KEY_ACTIVEMINS, log.getActiveMins());

        // updating row
        return db.update(TABLE_USER_WORKOUT, values, KEY_ID + " = ?",
                new String[] { String.valueOf(log.getID()) });
    }

    //UPDATE - Sleep Log
    public int updateLog(SleepLog log) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_DATE, log.getDate());
        values.put(KEY_SLEEPTIME, log.getSleepTime());
        values.put(KEY_WAKETIME, log.getWakeTime());

        // updating row
        return db.update(TABLE_USER_SLEEP, values, KEY_ID + " = ?",
                new String[] { String.valueOf(log.getID()) });
    }

    //UPDATE - MIA Log
    public int updateLog(LogMIA log) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_FROMDATE, log.getFromDate());
        values.put(KEY_TODATE, log.getToDate());
        values.put(KEY_DETAILS, log.getDetails());

        // updating row
        return db.update(TABLE_USER_MIA, values, KEY_ID + " = ?",
                new String[] { String.valueOf(log.getID()) });
    }

    //UPDATE - Personal Data
    public int updateData(PersonalData data) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, data.getName());
        values.put(KEY_ADDRESS, data.getAddress());
        values.put(KEY_AGE, data.getAge());
        values.put(KEY_HEIGHT, data.getHeight());
        values.put(KEY_WEIGHT, data.getWeight());
        values.put(KEY_INTEGRATION, data.getIntegration());
        values.put(KEY_GENDER, data.getGender());
        values.put(KEY_FIRST_LOGIN, data.getFirstLogin());
        values.put(KEY_MEAL_TOGGLE, data.getMealToggle());
        values.put(KEY_WORKOUT_TOGGLE, data.getWorkoutToggle());
        values.put(KEY_SLEEP_TOGGLE, data.getSleepToggle());

        // updating row
        return db.update(TABLE_USER_PERSONAL, values, KEY_ID + " = ?",
                new String[] { String.valueOf(data.getID()) });
    }

    //DELETE - Meal Log
    public void deleteMealLog(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_USER_MEAL, KEY_ID + " = ?",
                new String[] { Integer.toString(id) });
        db.close();
    }

    //DELETE - Workout Log
    public void deleteWorkoutLog(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_USER_WORKOUT, KEY_ID + " = ?",
                new String[] { Integer.toString(id) });
        db.close();
    }

    //DELETE - Sleep Log
    public void deleteSleepLog(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_USER_SLEEP, KEY_ID + " = ?",
                new String[] { Integer.toString(id) });
        db.close();
    }

    //DELETE - MIA Log
    public void deleteMIALog(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_USER_MIA, KEY_ID + " = ?",
                new String[] { Integer.toString(id) });
        db.close();
    }

    //DELETE - Personal Data
    public void deletePersonalData(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_USER_PERSONAL, KEY_ID + " = ?",
                new String[] { Integer.toString(id) });
        db.close();
    }


    //GET COUNT - Meal Log
    public int getMealLogCount() {
        String countQuery = "SELECT * FROM " + TABLE_USER_MEAL;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        // return count
        return cursor.getCount();
    }

    //GET COUNT - Workout Log
    public int getWorkoutLogCount() {
        String countQuery = "SELECT * FROM " + TABLE_USER_WORKOUT;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        // return count
        return cursor.getCount();
    }

    //GET COUNT - Sleep Log
    public int getSleepLogCount() {
        String countQuery = "SELECT * FROM " + TABLE_USER_SLEEP;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        // return count
        return cursor.getCount();
    }

    //GET COUNT - MIA Log
    public int getMIALogCount() {
        String countQuery = "SELECT * FROM " + TABLE_USER_MIA;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        // return count
        return cursor.getCount();
    }

    //close database
    public void closeDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
    }
}
