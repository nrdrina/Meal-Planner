package com.example.organizerecipe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class testMenuFavourite extends AppCompatActivity {

    private ArrayAdapter<String> adapter;
    private ArrayList<String> menuItems;
    private Button viewProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_menu_favourite);
        EdgeToEdge.enable(this);

        ListView menuListView = findViewById(R.id.menuListView);
        menuItems = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, menuItems);
        menuListView.setAdapter(adapter);

        setupMenuButtons();
        setupListViewListener(menuListView);

        viewProfile = findViewById(R.id.viewProfile);
        viewProfile.setOnClickListener(this::viewProfileClicked);
    }

    private void setupMenuButtons() {
        Button btnBread = findViewById(R.id.btnbread);
        Button btnEgg = findViewById(R.id.btnegg);
        Button btnBurger = findViewById(R.id.btnburger);
        Button btnSausage = findViewById(R.id.btnsosej);

        btnBread.setOnClickListener(v -> addItemToList("Bread"));
        btnEgg.setOnClickListener(v -> addItemToList("Egg"));
        btnBurger.setOnClickListener(v -> addItemToList("Burger"));
        btnSausage.setOnClickListener(v -> addItemToList("Sausage"));
    }

    private void addItemToList(String item) {
        menuItems.add(item);
        adapter.notifyDataSetChanged();
    }

    private void setupListViewListener(ListView listView) {
        listView.setOnItemClickListener((parent, view, position, id) -> {
            menuItems.remove(position);  // Remove the item from list
            adapter.notifyDataSetChanged();  // Notify adapter about the data change
        });
    }

    private void viewProfileClicked(View v) {
        Intent intent = new Intent(testMenuFavourite.this, Userprofile.class);
        intent.putStringArrayListExtra("selectedItems", menuItems);
        startActivity(intent);
    }
}


