package com.example.noticeapp;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class GroupActivity extends AppCompatActivity {

    TextView tvdep, tvlib, tvgtu, tvgen,uname,udep;
    ProgressDialog progressDialog;
    NavigationView nav;
    ActionBarDrawerToggle toggle;
    DrawerLayout drawerLayout;
    FirebaseAuth fAuth;
    FirebaseFirestore fstore;
    ImageView ivdep;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // getSupportActionBar().hide();
        setContentView(R.layout.activity_group);

      //  LayoutInflater inflater = LayoutInflater.from(this);
       // View view = inflater.inflate(R.layout.navheader,null,false);






        tvdep = findViewById(R.id.tvdep);
        ivdep = findViewById(R.id.ivdep);
        tvlib = findViewById(R.id.tvlib);
        tvgtu = findViewById(R.id.tvgtu);
        tvgen = findViewById(R.id.tvgen);


        uname = findViewById(R.id.uname);
        udep = findViewById(R.id.udep);
        fAuth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();
      //  FirebaseUser fAuthCurrentUser = fAuth.getCurrentUser();

        String userId;
        userId = fAuth.getCurrentUser().getUid();
      // uId = fAuthCurrentUser.getUid();

       /*     DocumentReference documentReference = fstore.collection("Users").document(userId);
            documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
                @Override
                public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                    Toast.makeText(GroupActivity.this, ""+error.getMessage(), Toast.LENGTH_SHORT).show();
                    uname.setText(value.getString("Fname"));
                    udep.setText(value.getString("Department"));

                }
            });*/
//java.lang.String com.google.firebase.auth.FirebaseUser.getUid()'

        nav = findViewById(R.id.navmenu);
        drawerLayout = findViewById(R.id.drawer);

        toggle= new ActionBarDrawerToggle(GroupActivity.this,drawerLayout,R.string.open,R.string.close);
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
                    Toast.makeText(GroupActivity.this, "Open Settings", Toast.LENGTH_SHORT).show();
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

        ivdep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressDialog = new ProgressDialog(GroupActivity.this);
                progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progressDialog.setTitle("Please Wait");
                progressDialog.setProgress(0);
                progressDialog.show();
                startActivity(new Intent(GroupActivity.this, DepartmentActivity.class));

            }
        });
        tvgen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressDialog = new ProgressDialog(GroupActivity.this);
                progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progressDialog.setTitle("Please Wait");
                progressDialog.setProgress(0);
                progressDialog.show();
                startActivity(new Intent(GroupActivity.this, GeneralNoticeActivity.class));

            }
        });
        tvgtu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressDialog = new ProgressDialog(GroupActivity.this);
                progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progressDialog.setTitle("Please Wait");
                progressDialog.setProgress(0);
                progressDialog.show();
                startActivity(new Intent(GroupActivity.this, GTUActivity.class));

            }
        });
        tvlib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressDialog = new ProgressDialog(GroupActivity.this);
                progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progressDialog.setTitle("Please Wait");
                progressDialog.setProgress(0);
                progressDialog.show();
                startActivity(new Intent(GroupActivity.this, LibraryActivity.class));

            }
        });





    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.cmenu,menu);
        return true;
    }

  
   // @SuppressLint("NonConstantResourceId")
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
      // return super.onOptionsItemSelected(item);
    }
}