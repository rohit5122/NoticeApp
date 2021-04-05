package com.example.noticeapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.nio.channels.Selector;
import java.util.HashMap;
import java.util.Map;


public class SignupActivity extends AppCompatActivity {


    public static final String TAG = "TAG";

    EditText Mname, mnum, memail, mpassword;
    Button signup;
    FirebaseAuth fAuth;
    ProgressBar pbar;
    FirebaseFirestore fStore;
    String userId;
    Spinner Mdepartment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_signup);

        Mname = findViewById(R.id.name);
        mnum = findViewById(R.id.Phone);
       Mdepartment = findViewById(R.id.department);
        memail = findViewById(R.id.EmailAddress);
        mpassword = findViewById(R.id.Password);
        signup = findViewById(R.id.signup);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        pbar = findViewById(R.id.progressBar);

        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(SignupActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.Departments) );
            myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            Mdepartment.setAdapter(myAdapter);

         if (fAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
        }

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = memail.getText().toString().trim();
                String password = mpassword.getText().toString().trim();
                String name = Mname.getText().toString().trim();
                String num = mnum.getText().toString().trim();
                String dept = Mdepartment.getSelectedItem().toString().trim();

                if (TextUtils.isEmpty(email)){
                     memail.setError("Email is Required");
                    return;
                }
                if(dept.equals("Select Department")){
                    Toast.makeText(SignupActivity.this, "Please Select Department", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(num)){
                    mnum.setError("Mobile No. is Required");
                    return;
                }
                if (TextUtils.isEmpty(name)){
                    Mname.setError("Name is Required");
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    mpassword.setError("Password is Required");
                    return;
                }


                if (mpassword.length() < 6){
                    mpassword.setError("Password Must be >= 6 Characters");
                    return;
                }

               pbar.setVisibility(View.VISIBLE);



                // Register the user in firebase

                fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            //send Varification Email
                            userId = fAuth.getCurrentUser().getUid();
                            DocumentReference documentReference = fStore.collection("Users").document(userId);
                            Map<String, Object> user = new HashMap<>();
                            user.put("Fname", name);
                            user.put("MobileNo", num);
                            user.put("Email", email);
                            user.put("Department", dept);
                            user.put("Password", password);
                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.d(TAG, "OnSuccess : user profile is Created For" + userId);
                                }
                            });
                            startActivity(new Intent(getApplicationContext(), LoginActivity.class));


                            Toast.makeText(SignupActivity.this, "User Created.", Toast.LENGTH_SHORT).show();


                            FirebaseUser fuser = fAuth.getCurrentUser();
                            fuser.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(SignupActivity.this, "Verification Email Has been Sent", Toast.LENGTH_SHORT).show();

                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.d(TAG, "OnFailure: Email not Sent" + e.getMessage());
                                }
                            });



                            if(dept.equals("Select Department")){
                                Toast.makeText(SignupActivity.this, "Please select department", Toast.LENGTH_SHORT).show();
                                pbar.setVisibility(View.GONE);
                            }



                            } else {
                                Toast.makeText(SignupActivity.this, "Error ! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                pbar.setVisibility(View.GONE);
                            }

                    }
                });

            }
        });
    }
}