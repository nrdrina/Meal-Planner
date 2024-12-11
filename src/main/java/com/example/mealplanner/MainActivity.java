package com.example.mealplanner;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize DatabaseHelper
        dbHelper = new DatabaseHelper(this);

        // Add a new meal
        long newMealId = dbHelper.addMeal("Pizza", "Delicious cheese pizza", "2024-12-11");

        // Get all meals
        List<DatabaseHelper.Meal> meals = dbHelper.getAllMeals();

        // Example: Print meals
        for (DatabaseHelper.Meal meal : meals) {
            System.out.println("Meal: " + meal.getName());
        }
    }
}