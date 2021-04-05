package com.example.noticeapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class BaseActivity extends AppCompatActivity {

    NavigationView nav;
    ActionBarDrawerToggle toggle;
    DrawerLayout drawerLayout;
    FirebaseAuth fAuth;
    FirebaseFirestore fstore;
    TextView uname,udep;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        uname = findViewById(R.id.uname);
        udep = findViewById(R.id.udep);
        fAuth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();
        FirebaseUser fAuthCurrentUser = fAuth.getCurrentUser();

        String uId;

        nav = findViewById(R.id.navmenu);
        drawerLayout = findViewById(R.id.drawer);

        toggle= new ActionBarDrawerToggle(BaseActivity.this,drawerLayout,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);





        nav.setNavigationItemSelectedListener(item -> {

            switch (item.getItemId()){
                case R.id.menu_home :
                    startActivity(new Intent(getApplicationContext(), GroupActivity.class));
                    drawerLayout.closeDrawer(GravityCompat.START);
                    break;
                case R.id.menu_setting :
                    Toast.makeText(getApplicationContext(), "Open Settings", Toast.LENGTH_SHORT).show();
                    //   startActivity(new Intent(getApplicationContext(), GeneralNoticeActivity.class));
                    drawerLayout.closeDrawer(GravityCompat.START);
                    break;
                case R.id.menu_logout :
                    //  Toast.makeText(GroupActivity.this, "User Log out !!!", Toast.LENGTH_SHORT).show();
                    FirebaseAuth.getInstance().signOut();
                    startActivity(new Intent(getApplicationContext(), GroupActivity.class));
                    drawerLayout.closeDrawer(GravityCompat.START);
                    break;
            }
            return true;
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.cmenu,menu);
        return true;
    }


    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        switch (item.getItemId()){
            case R.id.cm1:
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                return true;
            case R.id.cm2:
                // Toast.makeText(this, "Welcome!!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), UserActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}