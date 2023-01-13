package com.association.mansabar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.ArrayMap;

import com.association.mansabar.model.Advocate;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class index extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        final Handler handler = new Handler();
        handler.postDelayed(() -> {
            Intent intent= new Intent(index.this, Home.class);
            startActivity(intent);
        }, 4000);


    }
}