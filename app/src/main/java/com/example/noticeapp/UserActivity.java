package com.example.noticeapp;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

@SuppressWarnings("ALL")
public class UserActivity extends AppCompatActivity {

    TextView uname,udep,uemail,unum;
  //  EditText mEmail, mPassword;
    FirebaseAuth fAuth;
    FirebaseFirestore fstore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
    //    setContentView(R.layout.activity_login);

        uname = findViewById(R.id.tvuname);
        udep = findViewById(R.id.tvudep);
        uemail = findViewById(R.id.tvuemail);
        unum = findViewById(R.id.tvunum);
        fAuth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();

      //  mEmail = findViewById(R.id.txtuserid);
        //mPassword = findViewById(R.id.txtpassword);
        FirebaseUser fAuthCurrentUser = fAuth.getCurrentUser();

        String uId;

        assert fAuthCurrentUser != null;
        uId = fAuthCurrentUser.getUid();

       // String email = mEmail.getText().toString().trim();
       // String password = mPassword.getText().toString().trim();

        if (uId != null) {
            DocumentReference documentReference = fstore.collection("Users").document(uId);
            documentReference.addSnapshotListener(UserActivity.this, new EventListener<DocumentSnapshot>() {
                @Override
                public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                    uname.setVisibility(View.VISIBLE);
                    udep.setVisibility(View.VISIBLE);
                    uemail.setVisibility(View.VISIBLE);
                    unum.setVisibility(View.VISIBLE);
                    uname.setText(value.getString("Fname"));
                    udep.setText(value.getString("Department"));
                    uemail.setText(value.getString("Email"));
                    unum.setText(value.getString("MobileNo"));
                }
            });


        }
        else{
            uname.setVisibility(View.GONE);
            udep.setVisibility(View.GONE);
            uemail.setVisibility(View.GONE);
            unum.setVisibility(View.GONE);

        }







    }
}