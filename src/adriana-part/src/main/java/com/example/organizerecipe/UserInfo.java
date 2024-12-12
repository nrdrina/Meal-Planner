package com.example.organizerecipe;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class UserInfo extends AppCompatActivity {

    Button btnUpdate, btnBack;
    EditText usernameTextView, passwordTextView;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        EdgeToEdge.enable(this);

        dbHelper = new DBHelper(this);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnBack = findViewById(R.id.btnBacktoUser);
        usernameTextView = findViewById(R.id.usernameDisplay);
        passwordTextView = findViewById(R.id.passwordDisplay);

        // Retrieve and display user details
        String username = getIntent().getStringExtra("Username");
        displayUserDetails(username);

        btnBack.setOnClickListener(v -> finish());
        btnUpdate.setOnClickListener(v -> {
            updateUserData(username);
        });
    }

    private void displayUserDetails(String username) {
        Cursor cursor = dbHelper.getUserDetails(username);
        if (cursor != null && cursor.moveToFirst()) {
            // Using getColumnIndexOrThrow to ensure the column exists
            int passwordIndex = cursor.getColumnIndexOrThrow("password");
            String password = cursor.getString(passwordIndex);
            usernameTextView.setText(username);
            passwordTextView.setText(password);
            cursor.close();
        } else {
            // Handle the case where cursor is empty or invalid
            Toast.makeText(this, "No user data found", Toast.LENGTH_SHORT).show();
        }
    }
    private void updateUserData(String username) {
        String newUsername = usernameTextView.getText().toString();
        String newPassword = passwordTextView.getText().toString();
        if (dbHelper.updateUserData(username, newUsername, newPassword)) {
            Toast.makeText(this, "Update successful", Toast.LENGTH_SHORT).show();
            // Optionally, navigate back or do other stuff
        } else {
            Toast.makeText(this, "Update failed", Toast.LENGTH_SHORT).show();
        }
    }
}