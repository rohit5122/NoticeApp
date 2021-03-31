package com.example.noticeapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.net.URL;

import static android.os.Environment.DIRECTORY_DOWNLOADS;

public class FetchActivity extends AppCompatActivity {

    TextView tvftitle, tvfdes, tvfile, tvfdate;
    FirebaseStorage firebaseStorage;
    Uri url;
    FirebaseFirestore fstore;
    DatabaseReference dbR;
    StorageReference storageReference;
    StorageReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_fetch);

        tvftitle = findViewById(R.id.tvftitle);
        tvfdes = findViewById(R.id.tvfdes);
        tvfile = findViewById(R.id.tvfile);
        tvfdate = findViewById(R.id.tvfdate);
        firebaseStorage = FirebaseStorage.getInstance();
        fstore = FirebaseFirestore.getInstance();
        FirebaseFirestore databaseReference = fstore.getInstance();

      //  DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();







        tvftitle.setText(getIntent().getStringExtra("Title").toString());
        tvfdes.setText(getIntent().getStringExtra("Discription").toString());
        tvfdate.setText(getIntent().getStringExtra("Date").toString());
        tvfile.setText(getIntent().getStringExtra("File_Path").toString());
        
        tvfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                download();
            }
        });





    }

    public void download() {
        String name = tvfile.getText().toString();

        storageReference = firebaseStorage.getInstance().getReference();
        ref = storageReference.child("Uploads/")/*.child(""+name)*/;

        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                String url = uri.toString();
                downloadFile(FetchActivity.this,""+name, ".pdf",DIRECTORY_DOWNLOADS,url);

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });

    }

    public void downloadFile(Context context,String fileName,String fileExtension,String destinationDirectory,String url) {

        DownloadManager downloadManager = (DownloadManager) context
                .getSystemService(Context.DOWNLOAD_SERVICE);
        Uri uri = Uri.parse(url);
        DownloadManager.Request request = new DownloadManager.Request(uri);

        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalFilesDir(context,destinationDirectory,fileName + fileExtension);

        downloadManager.enqueue(request);

    }

}