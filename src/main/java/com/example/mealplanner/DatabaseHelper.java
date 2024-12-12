package com.example.mealplanner;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "MealPlanner.db";
    private static final int DATABASE_VERSION = 1;

    // Table and column names
    private static final String TABLE_MEALS = "meals";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_DAY = "day";
    private static final String COLUMN_MEAL_NAME = "meal_name";
    private static final String COLUMN_MEAL_DETAILS = "meal_details";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_MEALS + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_DAY + " TEXT, " +
                COLUMN_MEAL_NAME + " TEXT, " +
                COLUMN_MEAL_DETAILS + " TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MEALS);
        onCreate(db);
    }

    // Insert meal
    public boolean insertMeal(String day, String mealName, String mealDetails) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_DAY, day);
        values.put(COLUMN_MEAL_NAME, mealName);
        values.put(COLUMN_MEAL_DETAILS, mealDetails);

        long result = db.insert(TABLE_MEALS, null, values);
        return result != -1; // Return true if insert is successful
    }

    // Retrieve all meals for a specific day
    public List<String> getMealsByDay(String day) {
        SQLiteDatabase db = this.getReadableDatabase();
        List<String> meals = new ArrayList<>();
        Cursor cursor = db.query(TABLE_MEALS,
                new String[]{COLUMN_MEAL_NAME, COLUMN_MEAL_DETAILS},
                COLUMN_DAY + "=?",
                new String[]{day},
                null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                String meal = cursor.getString(0) + ": " + cursor.getString(1);
                meals.add(meal);
            } while (cursor.moveToNext());
            cursor.close();
        }
        return meals;
    }

    // Clear all meals for testing or refreshing
    public void clearMeals() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_MEALS);
    }
}
