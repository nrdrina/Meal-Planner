package com.example.organizerecipe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Userprofile extends AppCompatActivity {
    private Button btnLogout;
    private DBHelper dbHelper;
    private TextView greeting, userInfo;
    private ArrayList<String> menuItems;  // Declare menuItems as a field of the class

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userprofile);
        EdgeToEdge.enable(this);

        dbHelper = new DBHelper(this);
        btnLogout = findViewById(R.id.btnLogout);
        greeting = findViewById(R.id.userAccname);
        userInfo = findViewById(R.id.txtUserInfo);

        String username = getIntent().getStringExtra("Username");
        if (username != null && !username.isEmpty()) {
            greeting.setText("Hello, " + username);
        } else {
            greeting.setText("Hello, Guest");
        }

//        userInfo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Intent intent = new Intent(getApplicationContext(), UserInfo.class);
//                startActivity(intent);
//
//                Toast.makeText(Userprofile.this, "Successfully click", Toast.LENGTH_SHORT).show();
//            }
//        });
        userInfo.setOnClickListener(v -> {
            Intent intent = new Intent(Userprofile.this, UserInfo.class);
            intent.putExtra("Username", username);  // Pass the username
            startActivity(intent);
            Toast.makeText(Userprofile.this, "Navigating to user information", Toast.LENGTH_SHORT).show();
        });

        btnLogout.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), Login.class);
            Toast.makeText(this, "Log Out Successfully", Toast.LENGTH_SHORT).show();
            startActivity(intent);
            finish();
        });
    }


//    private void setupMenuListView() {
//        ListView menuListView = findViewById(R.id.menuListView);
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, menuItems);
//        menuListView.setAdapter(adapter);
//
//        // Handling item clicks within the list
//        menuListView.setOnItemClickListener((parent, view, position, id) -> {
//            menuItems.remove(position);  // Remove the item from the list
//            adapter.notifyDataSetChanged();  // Notify the adapter of the data change
//        });
//    }


}

