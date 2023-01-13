package com.Prince.forensictraining;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.core.Tag;

public class MainActivity extends AppCompatActivity {

    DatabaseReference reference;
    LinearLayout pri;
    boolean act = true;
    private String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        pri = findViewById(R.id.main_layout_mainActivity);
        reference = FirebaseDatabase.getInstance().getReference().child("users");
        uid = getIntent().getStringExtra("uid");
        reference.child(uid).child("status").setValue("on");
        start();
    }

    private void start() {



    }
    public void logout(View view){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("users").child(uid);
        reference.child("status").setValue("off");
        startActivity(new Intent(MainActivity.this,New_login.class));
        finish();

    }

    private Handler handler = new Handler();
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            //Log.d("TAG","start");
            reference.child(uid).child("status").setValue("on");
            handler.postDelayed(this, 1000);
        }
    };
    @Override
    protected void onStart() {
        act = true;
        super.onStart();
        Log.d("TAG","start");
        handler.postDelayed(runnable, 1000);
        //start(0,false);
    }

//Start

    @Override
    protected void onPause() {
        //act=false;
       // ref_read_user.child("status").setValue("off");
        Log.d("TAG","pause");
        handler.removeCallbacks(runnable);
        super.onPause();
    }


    @Override
    protected void onStop() {
        //act=false;
        //ref_read_user.child("status").setValue("off");
        handler.removeCallbacks(runnable);
        Log.d("TAG","stop");
        super.onStop();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        Log.d("TAG","postresume");
        // start(0,false);
        start();
        act = true;

    }

    @Override
    protected void onDestroy() {
        //act=false;
        //ref_read_user.child("status").setValue("off");
        Log.d("TAG","destroy");
        handler.removeCallbacks(runnable);
        super.onDestroy();

    }

    @Override
    protected void onResume() {
        act = true;
        super.onResume();
        Log.d("TAG","resume");
       // start(0,false);

    }

    @Override
    public void onBackPressed() {
        //act=false;
        //ref_read_user.child("status").setValue("off");
        //Log.d("TAG","back");
        handler.removeCallbacks(runnable);
        super.onBackPressed();

    }
}