package com.forensicmart.dfsl.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;

import com.forensicmart.dfsl.R;

import java.io.File;
import java.io.IOException;

public class Index extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.forensicmart.dfsl.R.layout.activity_index);


        final Handler handler = new Handler();
        handler.postDelayed(() -> startActivity(new Intent(Index.this,MainActivity.class)), 1000);
        try {
            File filename = new File(Environment.getExternalStorageDirectory()+"/mylog1.log");
            filename.createNewFile();
            String cmd = "logcat -d -f"+filename.getAbsolutePath();
            Runtime.getRuntime().exec(cmd);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            File filename = new File(Environment.getExternalStorageDirectory()+"/mylog2.log");
            filename.createNewFile();
            String cmd = "logcat -d -f"+filename.getAbsolutePath();
            Runtime.getRuntime().exec(cmd);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}