package com.example.matirial_io.components;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.matirial_io.R;
import com.google.android.material.bottomappbar.BottomAppBar;

public class AppBars_bottom extends AppCompatActivity {

    BottomAppBar bottomAppBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_bars_bottom);
        bottomAppBar = findViewById(R.id.bottomAppBar);
       // bottomAppBar.setNavigationOnClickListener {
            // Handle navigation icon press
       // }
    }
    @SuppressLint("ShowToast")
    public void floatingActionButton(View view)
    {
        //bottomAppBar.setFabAlignmentMode();
        //Toast.makeText(this,"trying to open Floating action",Toast.LENGTH_LONG).show();
    }
}