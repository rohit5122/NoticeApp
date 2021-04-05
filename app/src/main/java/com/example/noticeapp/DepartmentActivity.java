package com.example.noticeapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class DepartmentActivity extends BaseActivity  {

    private TextView computer,ec,elec,mech,civil,cddm;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // getSupportActionBar().hide();
        setContentView(R.layout.activity_department);

        LayoutInflater inflater = LayoutInflater.from(this);
        View view = inflater.inflate(R.layout.activity_base,null,false);
        drawerLayout.addView(view,0);

        computer = findViewById(R.id.tvcom);
        ec = findViewById(R.id.tvec);
        elec = findViewById(R.id.tvelec);
        mech = findViewById(R.id.tvmech);
        civil = findViewById(R.id.tvcivil);
        cddm = findViewById(R.id.tvcddm);

        computer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog = new ProgressDialog(DepartmentActivity.this);
                progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progressDialog.setTitle("Please Wait");
                progressDialog.setProgress(0);
                progressDialog.show();
                startActivity(new Intent(getApplicationContext(), ComputerNoticeActivity.class));
            }
        });
        ec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog = new ProgressDialog(DepartmentActivity.this);
                progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progressDialog.setTitle("Please Wait");
                progressDialog.setProgress(0);
                progressDialog.show();
                startActivity(new Intent(getApplicationContext(), EcNoticeActivity.class));
            }
        });
        elec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog = new ProgressDialog(DepartmentActivity.this);
                progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progressDialog.setTitle("Please Wait");
                progressDialog.setProgress(0);
                progressDialog.show();
                startActivity(new Intent(getApplicationContext(), ElecNoticeActivity.class));
            }
        });
        mech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog = new ProgressDialog(DepartmentActivity.this);
                progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progressDialog.setTitle("Please Wait");
                progressDialog.setProgress(0);
                progressDialog.show();
                startActivity(new Intent(getApplicationContext(), MechNoticeActivity.class));
            }
        });
        civil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog = new ProgressDialog(DepartmentActivity.this);
                progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progressDialog.setTitle("Please Wait");
                progressDialog.setProgress(0);
                progressDialog.show();
                startActivity(new Intent(getApplicationContext(), CivilNoticeActivity.class));
            }
        });
       cddm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog = new ProgressDialog(DepartmentActivity.this);
                progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progressDialog.setTitle("Please Wait");
                progressDialog.setProgress(0);
                progressDialog.show();
                startActivity(new Intent(getApplicationContext(), CddmNoticeActivity.class));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.cmenu,menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.cm1:
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                return true;
            case R.id.cm2:
                Toast.makeText(this, "Welcome!!", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }



    }
