package com.example.noticeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class DepartmentActivity extends AppCompatActivity  {

    private TextView computer,ec,elec,mech,civil,cddm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_department);

        computer = findViewById(R.id.tvcom);
        ec = findViewById(R.id.tvec);
        elec = findViewById(R.id.tvelec);
        mech = findViewById(R.id.tvmech);
        civil = findViewById(R.id.tvcivil);
        cddm = findViewById(R.id.tvcddm);

        computer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ComputerNoticeActivity.class));
            }
        });
        ec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), EcNoticeActivity.class));
            }
        });
        elec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ElecNoticeActivity.class));
            }
        });
        mech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MechNoticeActivity.class));
            }
        });
        civil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), CivilNoticeActivity.class));
            }
        });
       cddm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), CddmNoticeActivity.class));
            }
        });
    }



    }
