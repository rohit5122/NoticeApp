package com.example.noticeapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.auth.api.signin.SignInAccount;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class ComputerNoticeActivity extends BaseActivity {

    FloatingActionButton create;
    RecyclerView recView;
    FirebaseFirestore fstore;
    FirebaseAuth fAuth;
    myadapter myadapter;
    ArrayList<model> nlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_notice);

        LayoutInflater inflater = LayoutInflater.from(this);
        View view = inflater.inflate(R.layout.activity_base,null,false);
        drawerLayout.addView(view,0);

        recView = findViewById(R.id.recyclerview);
        create = findViewById(R.id.fbutton);
        fstore = FirebaseFirestore.getInstance();
        fAuth = FirebaseAuth.getInstance();
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

        FirebaseUser fAuthCurrentUser = fAuth.getCurrentUser();

        String uId;
//       assert fAuthCurrentUser != null;
//        uId = fAuthCurrentUser.getUid();
        if ( fAuthCurrentUser != null) {
            create.isClickable();
        }








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