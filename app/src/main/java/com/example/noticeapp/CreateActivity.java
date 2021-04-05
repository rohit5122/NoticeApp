package com.example.noticeapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class CreateActivity extends AppCompatActivity {

    public static final String TAG = "TAG";

    EditText title,discription;
    Button Upload,selectfile;
    TextView notification,tvdate;
    Uri pdfUri;
    FirebaseStorage fStorage;
    FirebaseFirestore fStore;
    ProgressDialog progressDialog;
    TextView dspinner;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // getSupportActionBar().hide();
        setContentView(R.layout.activity_create);

        title = findViewById(R.id.ntitle);
        discription = findViewById(R.id.ndiscription);
        Upload = findViewById(R.id.upload);
        selectfile = findViewById(R.id.selectfile);
        notification = findViewById(R.id.notification);
        tvdate = findViewById(R.id.tvdate);
        dspinner = findViewById(R.id.dspinner);
        fStore = FirebaseFirestore.getInstance();
        fStorage = FirebaseStorage.getInstance();
        fAuth = FirebaseAuth.getInstance();

        Calendar calendar = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance(DateFormat.LONG).format(calendar.getTime());
        tvdate.setText(currentDate);

      /*  ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(CreateActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.Type) );
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dspinner.setAdapter(myAdapter);*/

        String userId;
        userId = fAuth.getCurrentUser().getUid();

        DocumentReference documentReference = fStore.collection("Users").document(userId);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {

                dspinner.setText(value.getString("Department"));

            }
        });






        selectfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(ContextCompat.checkSelfPermission(CreateActivity.this,Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED){

                    selectpdf();
                }
                else{
                    ActivityCompat.requestPermissions(CreateActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 9);
                }


            }
        });


        Upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Nttitle = title.getText().toString();
                String ntdis = discription.getText().toString();

                  String url = pdfUri.toString();
             //  String url = notification.getText().toString();
                String date = tvdate.getText().toString();
                String ndept = dspinner.getText().toString();




                if (TextUtils.isEmpty(Nttitle)) {
                    Toast.makeText(CreateActivity.this, "Please Enter Title", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(ntdis)) {
                    Toast.makeText(CreateActivity.this, "Please Enter Discription", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (pdfUri != null){
                    uploadFile(pdfUri);
                }
                if(ndept.equals("Select Department")){
                    Toast.makeText(CreateActivity.this, "Please Select Department", Toast.LENGTH_SHORT).show();
                    return;
                }
                else {
                    Toast.makeText(CreateActivity.this, "Select a File", Toast.LENGTH_SHORT).show();
                }


              if (ndept.equals("Computer")){
                  Map<String, String> noticeMap = new HashMap<>();

                  noticeMap.put("Title",Nttitle);
                  noticeMap.put("Discription",ntdis);
                  noticeMap.put("Date",date);
                  noticeMap.put("File_Path",url);

                  fStore.collection("Computer Notice").add(noticeMap).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                      @Override
                      public void onSuccess(DocumentReference documentReference) {

                          Toast.makeText(CreateActivity.this, "Notice Created", Toast.LENGTH_SHORT).show();
                          startActivity(new Intent(getApplicationContext(), ComputerNoticeActivity.class));

                      }
                  }).addOnFailureListener(new OnFailureListener() {
                      @Override
                      public void onFailure(@NonNull Exception e) {

                          String error = e.getMessage();
                          Toast.makeText(CreateActivity.this, "Error !!!" + error, Toast.LENGTH_SHORT).show();

                      }
                  });
              }

                if(ndept.equals("EC")){
                    Map<String, String> noticeMap = new HashMap<>();

                    noticeMap.put("Title",Nttitle);
                    noticeMap.put("Discription",ntdis);
                    noticeMap.put("Date",date);
                    noticeMap.put("File_Path",url);

                    fStore.collection("EC Notice").add(noticeMap).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {

                            Toast.makeText(CreateActivity.this, "Notice Created", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), EcNoticeActivity.class));

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                            String error = e.getMessage();
                            Toast.makeText(CreateActivity.this, "Error !!!" + error, Toast.LENGTH_SHORT).show();

                        }
                    });
                }
                if(ndept.equals("Electrical")){
                    Map<String, String> noticeMap = new HashMap<>();

                    noticeMap.put("Title",Nttitle);
                    noticeMap.put("Discription",ntdis);
                    noticeMap.put("Date",date);
                    noticeMap.put("File_Path",url);

                    fStore.collection("Electrical Notice").add(noticeMap).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {

                            Toast.makeText(CreateActivity.this, "Notice Created", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), ElecNoticeActivity.class));

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                            String error = e.getMessage();
                            Toast.makeText(CreateActivity.this, "Error !!!" + error, Toast.LENGTH_SHORT).show();

                        }
                    });
                }

                if(ndept.equals("Mechanical")){
                    Map<String, String> noticeMap = new HashMap<>();

                    noticeMap.put("Title",Nttitle);
                    noticeMap.put("Discription",ntdis);
                    noticeMap.put("Date",date);
                    noticeMap.put("File_Path",url);

                    fStore.collection("Mechanical Notice").add(noticeMap).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {

                            Toast.makeText(CreateActivity.this, "Notice Created", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), MechNoticeActivity.class));

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                            String error = e.getMessage();
                            Toast.makeText(CreateActivity.this, "Error !!!" + error, Toast.LENGTH_SHORT).show();

                        }
                    });
                }
                if(ndept.equals("Civil")){
                    Map<String, String> noticeMap = new HashMap<>();

                    noticeMap.put("Title",Nttitle);
                    noticeMap.put("Discription",ntdis);
                    noticeMap.put("Date",date);
                    noticeMap.put("File_Path",url);

                    fStore.collection("Civil Notice").add(noticeMap).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {

                            Toast.makeText(CreateActivity.this, "Notice Created", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), CivilNoticeActivity.class));

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                            String error = e.getMessage();
                            Toast.makeText(CreateActivity.this, "Error !!!" + error, Toast.LENGTH_SHORT).show();

                        }
                    });
                }
                if(ndept.equals("CDDM")){
                    Map<String, String> noticeMap = new HashMap<>();

                    noticeMap.put("Title",Nttitle);
                    noticeMap.put("Discription",ntdis);
                    noticeMap.put("Date",date);
                    noticeMap.put("File_Path",url);

                    fStore.collection("CDDM Notice").add(noticeMap).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {

                            Toast.makeText(CreateActivity.this, "Notice Created", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), CddmNoticeActivity.class));

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                            String error = e.getMessage();
                            Toast.makeText(CreateActivity.this, "Error !!!" + error, Toast.LENGTH_SHORT).show();

                        }
                    });
                }
                if(ndept.equals("General")){
                    Map<String, String> noticeMap = new HashMap<>();

                    noticeMap.put("Title",Nttitle);
                    noticeMap.put("Discription",ntdis);
                    noticeMap.put("Date",date);
                    noticeMap.put("File_Path",url);

                    fStore.collection("General Notice").add(noticeMap).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {

                            Toast.makeText(CreateActivity.this, "Notice Created", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), ComputerNoticeActivity.class));

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                            String error = e.getMessage();
                            Toast.makeText(CreateActivity.this, "Error !!!" + error, Toast.LENGTH_SHORT).show();

                        }
                    });
                }
                if(ndept.equals("Library")){
                    Map<String, String> noticeMap = new HashMap<>();

                    noticeMap.put("Title",Nttitle);
                    noticeMap.put("Discription",ntdis);
                    noticeMap.put("Date",date);
                    noticeMap.put("File_Path",url);

                    fStore.collection("Library Notice").add(noticeMap).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {

                            Toast.makeText(CreateActivity.this, "Notice Created", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), ComputerNoticeActivity.class));

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                            String error = e.getMessage();
                            Toast.makeText(CreateActivity.this, "Error !!!" + error, Toast.LENGTH_SHORT).show();

                        }
                    });
                }
                if(ndept.equals("GTU")){
                    Map<String, String> noticeMap = new HashMap<>();

                    noticeMap.put("Title",Nttitle);
                    noticeMap.put("Discription",ntdis);
                    noticeMap.put("Date",date);
                    noticeMap.put("File_Path",url);

                    fStore.collection("GTU Notice").add(noticeMap).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {

                            Toast.makeText(CreateActivity.this, "Notice Created", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), ComputerNoticeActivity.class));

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                            String error = e.getMessage();
                            Toast.makeText(CreateActivity.this, "Error !!!" + error, Toast.LENGTH_SHORT).show();

                        }
                    });
                }

            }

        });






        }

    private void uploadFile(Uri pdfUri) {

         progressDialog = new ProgressDialog(this);
         progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
         progressDialog.setTitle("Uploading File...");
         progressDialog.setProgress(0);
         progressDialog.show();

        StorageReference storageReference = fStorage.getReference();
        String filename = System.currentTimeMillis()+"";
        storageReference.child("Uploads").child(filename).putFile(pdfUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        String url = taskSnapshot.getMetadata().getReference().getDownloadUrl().toString();
              /* Map<String, String> noticeMap = new HashMap<>();

                noticeMap.put("File Path",url);
                fStore.collection("Notice").add(noticeMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        if (task.isSuccessful()) {

                            Toast.makeText(CreateActivity.this, "File Successfully Uploaded", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(CreateActivity.this, "File not Successfully Uploaded", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                */


                    }
                }).addOnFailureListener(e -> Toast.makeText(CreateActivity.this, "File not Successfully Uploaded", Toast.LENGTH_SHORT).show()).addOnProgressListener(snapshot -> {

                    int currentprogress = (int) (100 * snapshot.getBytesTransferred() / snapshot.getTotalByteCount());
                    progressDialog.setProgress(currentprogress);
                    if (currentprogress == 100 * snapshot.getBytesTransferred() / 100) {
                        // progressDialog.hide();

                    }

                });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == 9 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            selectpdf();
        }
        else {
            Toast.makeText(CreateActivity.this, "Please Provide Permission..", Toast.LENGTH_SHORT).show();
        }

    }

    private void selectpdf() {

        Intent intent = new Intent();
        intent.setType("application/pdf");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, 86);
    }

    @SuppressLint("MissingSuperCall")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (requestCode == 86  && resultCode == RESULT_OK && data != null){
            pdfUri = data.getData();
            notification.setText("File is Selcted : " + data.getData().getLastPathSegment());
        }
        else {
            Toast.makeText(CreateActivity.this, "Please Select a File", Toast.LENGTH_SHORT).show();
        }

    }
}