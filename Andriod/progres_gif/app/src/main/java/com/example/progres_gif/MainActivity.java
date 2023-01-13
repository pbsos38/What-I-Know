package com.example.progres_gif;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    ViewDialog viewDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //..initialize our custom loading dialog here with passing this activity context
        viewDialog = new ViewDialog(this);
        showCustomLoadingDialog();
    }

     void showCustomLoadingDialog() {

        //..show gif
        viewDialog.showDialog();


//        final Handler handler = new Handler();
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                //...here i'm waiting 5 seconds before hiding the custom dialog
//                //...you can do whenever you want or whenever your work is done
//                viewDialog.hideDialog();
//            }
//        }, 5000);
    }
}
