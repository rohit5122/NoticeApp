package com.example.noticeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class GroupActivity extends AppCompatActivity {

    TextView tvdep, tvlib, tvgtu, tvgen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_group);

        tvdep = findViewById(R.id.tvdep);
        tvlib = findViewById(R.id.tvlib);
        tvgtu = findViewById(R.id.tvgtu);
        tvgen = findViewById(R.id.tvgen);

        tvdep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), DepartmentActivity.class));
            }
        });

        tvgen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), GeneralNoticeActivity.class));
            }
        });
        tvgtu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), GTUActivity.class));
            }
        });
        tvlib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), LibraryActivity.class));
            }
        });




    }
}