package com.example.organizerecipe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.organizerecipe.R;

public class Signup extends AppCompatActivity {

    EditText username, password, repassword;
    Button signup, signin;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_signup);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        repassword = (EditText) findViewById(R.id.repassword);

        signup = (Button) findViewById(R.id.btnsignup);
        signin = (Button) findViewById(R.id.btnsignin);

        DB = new DBHelper(this);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String repass = repassword.getText().toString();

                if(user.isEmpty() || pass.isEmpty() || repass.isEmpty())
                    Toast.makeText(Signup.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else{
                    if (pass.equals(repass)){
                        Boolean checkuser = DB.checkusername(user);
                        if(!checkuser){
                            Boolean insert = DB.insertData(user, pass);
                            if (insert) {
                                Toast.makeText(Signup.this, "Registered successfully", Toast.LENGTH_SHORT).show();
//                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//                                startActivity(intent);
                            } else {
                                Toast.makeText(Signup.this, "Registered failed", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(Signup.this, "User already exists! please sign in", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(Signup.this, "Password not matching", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);

            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}