package com.example.noticeapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class ComputerNoticeActivity extends AppCompatActivity {

    FloatingActionButton create;
    RecyclerView recView;
    FirebaseFirestore fstore;
    myadapter myadapter;
    ArrayList<model> nlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_notice);

        recView = findViewById(R.id.recyclerview);
        fstore = FirebaseFirestore.getInstance();
        nlist = new ArrayList<>();
        myadapter = new myadapter(nlist);
        recView.setAdapter(myadapter);

        fstore.collection("Computer Notice").orderBy("Date", Query.Direction.DESCENDING).get()
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


        create = findViewById(R.id.fbutton);

                create.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(getApplicationContext(),CreateActivity.class));
                    }
                });

    }
}