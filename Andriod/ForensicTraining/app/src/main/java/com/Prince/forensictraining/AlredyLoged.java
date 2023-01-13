package com.Prince.forensictraining;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AlredyLoged extends AppCompatActivity {

    private String uid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alredy_loged);
        LinearLayout sec = findViewById(R.id.secondary_layout_mainActivity);
        uid = getIntent().getStringExtra("uid");
    }
    public void logout(View view){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("users").child(uid);
        reference.child("status").setValue("off");
        startActivity(new Intent(AlredyLoged.this,New_login.class));
        finish();
    }
}