package com.example.mealplanner;

import static android.content.Intent.getIntent;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MealInputActivity extends AppCompatActivity {

    EditText mealDetailsEditText;
    Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_input);

        // Get meal name and position from the Intent
        String mealName = getIntent().getStringExtra("mealName");
        int position = getIntent().getIntExtra("position", -1);

        mealDetailsEditText = findViewById(R.id.mealDetailsEditText);
        saveButton = findViewById(R.id.saveButton);

        // Set up UI with the meal name
        mealDetailsEditText.setHint("Enter details for " + mealName);

        saveButton.setOnClickListener(v -> {
            // Get user input and save the data (could save to a database or pass it back to the previous activity)
            String mealDetails = mealDetailsEditText.getText().toString();
            // Do something with the meal details (e.g., save to the database)
            Toast.makeText(MealInputActivity.this, "Meal details saved for " + mealName, Toast.LENGTH_SHORT).show();
            finish(); // Close activity
        });
    }
}