package com.example.noticeapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class GTUActivity extends AppCompatActivity {

    FloatingActionButton create;
    RecyclerView recView;
    FirebaseFirestore fstore;
    myadapter myadapter;
    ArrayList<model> nlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  getSupportActionBar().hide();
        setContentView(R.layout.activity_g_t_u);

        recView = findViewById(R.id.recyclerview);
        fstore = FirebaseFirestore.getInstance();
        nlist = new ArrayList<>();
        myadapter = new myadapter(nlist);
        recView.setAdapter(myadapter);

        fstore.collection("GTU Notice").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                        for (DocumentSnapshot d : list) {
                            model obj = d.toObject(model.class);
                            nlist.add(obj);
                        }
                        myadapter.notifyDataSetChanged();
                    }
                });


        create = findViewById(R.id.create);

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),CreateActivity.class));
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