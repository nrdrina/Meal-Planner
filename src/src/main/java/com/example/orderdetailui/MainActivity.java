package com.example.orderdetailui;

import android.os.Bundle;
import android.util.Log;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DatabaseHelper dbHelper; // Declare DatabaseHelper as a class variable

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize DatabaseHelper
        dbHelper = new DatabaseHelper(this);

        // Set the layout
        setContentView(R.layout.activity_main);

        // Handle favourite button for Nasi Lemak
        ImageButton favouriteButton = findViewById(R.id.favouriteButton);
        favouriteButton.setOnClickListener(v -> handleFavouriteButtonClick("Nasi Lemak", favouriteButton));

        // Handle favourite button for Fried Rice
        ImageButton favouriteButton2 = findViewById(R.id.favouriteButton2);
        favouriteButton2.setOnClickListener(v -> handleFavouriteButtonClick("Fried Rice", favouriteButton2));

        // Handle favourite button for Burger
        ImageButton favouriteButton3 = findViewById(R.id.favouriteButton3);
        favouriteButton3.setOnClickListener(v -> handleFavouriteButtonClick("Burger", favouriteButton3));

        // Handle favourite button for Mac And Cheese
        ImageButton favouriteButton4 = findViewById(R.id.favouriteButton4);
        favouriteButton4.setOnClickListener(v -> handleFavouriteButtonClick("Mac And Cheese", favouriteButton4));

        // Handle favourite button for Pancake
        ImageButton favouriteButton5 = findViewById(R.id.favouriteButton5);
        favouriteButton5.setOnClickListener(v -> handleFavouriteButtonClick("Pancake", favouriteButton5));

        // Handle favourite button for Spaghetti
        ImageButton favouriteButton6 = findViewById(R.id.favouriteButton6);
        favouriteButton6.setOnClickListener(v -> handleFavouriteButtonClick("Spaghetti", favouriteButton6));
    }
    private void handleFavouriteButtonClick(String itemName, ImageButton button) {
        // Fetch item from database
        MenuItem menuItem = dbHelper.getMenuItemByName(itemName);

        // Toggle favourite status
        boolean newFavouriteStatus = !menuItem.isFavourite();
        menuItem.setFavourite(newFavouriteStatus);

        // Update the database
        dbHelper.updateFavouriteStatus(menuItem.getId(), newFavouriteStatus);

        // Update the button icon
        button.setImageResource(newFavouriteStatus
                ? R.drawable.baseline_favorite_24
                : R.drawable.baseline_favorite_border_24);

        // Display a feedback toast
        String message = newFavouriteStatus
                ? itemName + " added to favourites!"
                : itemName + " removed from favourites!";
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

        // Log the action
        Log.d("UI", "Updated favourite status for: " + itemName + " to " + newFavouriteStatus);
    }


    private void addMenuItemToGrid(GridLayout gridLayout, MenuItem menuItem) {
        // Create a CardView-like LinearLayout
        LinearLayout card = new LinearLayout(this);
        card.setOrientation(LinearLayout.VERTICAL);
        card.setPadding(16, 16, 16, 16);
        card.setGravity(android.view.Gravity.CENTER);

        // Add ImageView for food image
        ImageView imageView = new ImageView(this);
        int imageResId = getResources().getIdentifier(menuItem.getImageResourceName(), "drawable", getPackageName());
        imageView.setImageResource(imageResId);
        imageView.setLayoutParams(new LinearLayout.LayoutParams(200, 200));
        card.addView(imageView);

        // Add TextView for food name
        TextView textView = new TextView(this);
        textView.setText(menuItem.getName());
        textView.setGravity(android.view.Gravity.CENTER);
        textView.setTextSize(16);
        textView.setPadding(0, 8, 0, 8);
        card.addView(textView);

        // Add ImageButton for favourite
        ImageButton favouriteButton = new ImageButton(this);
        favouriteButton.setImageResource(menuItem.isFavourite()
                ? R.drawable.baseline_favorite_24
                : R.drawable.baseline_favorite_border_24);
        favouriteButton.setBackgroundColor(0); // Transparent background
        favouriteButton.setLayoutParams(new LinearLayout.LayoutParams(100, 100));

        // Handle favourite button click
        favouriteButton.setOnClickListener(v -> {
            menuItem.setFavourite(!menuItem.isFavourite());
            dbHelper.updateFavouriteStatus(menuItem.getId(), menuItem.isFavourite());
            favouriteButton.setImageResource(menuItem.isFavourite()
                    ? R.drawable.baseline_favorite_24
                    : R.drawable.baseline_favorite_border_24);
        });

        card.addView(favouriteButton);

        // Add the card to the GridLayout
        GridLayout.LayoutParams params = new GridLayout.LayoutParams();
        params.setMargins(16, 16, 16, 16);
        params.width = GridLayout.LayoutParams.WRAP_CONTENT;
        params.height = GridLayout.LayoutParams.WRAP_CONTENT;

        gridLayout.addView(card, params);
    }
}
