package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {



    Button button_ShowBooks, button_Camera, button_GoogleAuth;

    @SuppressLint("CutPasteId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Objects.requireNonNull(getSupportActionBar()).setTitle("  " +
                "                        Books List");


        button_ShowBooks = findViewById(R.id.button_ShowBooks);
        button_ShowBooks.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), List.class);
            startActivity(intent);
        });
        button_Camera = findViewById(R.id.button_Call);
        button_Camera.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), Call.class);
            startActivity(intent);
        });

        button_GoogleAuth = findViewById(R.id.button_Auth);
        button_GoogleAuth.setOnClickListener(v -> {
        });
    }

}