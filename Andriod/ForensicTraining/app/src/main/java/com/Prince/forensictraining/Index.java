package com.Prince.forensictraining;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Index extends AppCompatActivity {
    DataBaseHelper mydb;
    String x = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//references
        {
            mydb = new DataBaseHelper(this);
        }
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                autoLogin();
            }
        }, 1000);

    }
    private void autoLogin() {

        String extracteduid = null, extractedpwd = null;
        Cursor res = mydb.distictvalues();
        Cursor res2 = mydb.fetchData();

        while (res.moveToNext()) {
            x = String.valueOf(res.getString(0));
        }
        while (res2.moveToNext()) {
            extracteduid = res2.getString(0);
            extractedpwd = res2.getString(1);
        }

        if (res.getCount() == 0) {
            Intent intent = new Intent(Index.this, New_login.class);
            startActivity(intent);
            return;
        } else if (res.getCount() == 1) {
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("users");
            final String finalExtracteduid = extracteduid;
            final String finalExtractedpwd = extractedpwd;
            reference.child(extracteduid).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (!snapshot.exists()) {
                        startActivity(new Intent(Index.this,New_login.class));
                    }
                    else {
                        login(finalExtracteduid, finalExtractedpwd);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
    }
    private void login(final String suid, final String spwd){
        final DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("users").child(suid);


        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (spwd.equals(snapshot.child("pwd").getValue().toString()))
                {
                    if (snapshot.child("status").getValue().toString().equals("off")) {
                        Intent intent = new Intent(Index.this, MainActivity.class);
                        intent.putExtra("uid",suid);
                        startActivity(intent);
                        finish() ;

                    }else{
                        Intent intent = new Intent(Index.this,AlredyLoged.class);
                        intent.putExtra("uid",suid);
                        startActivity(intent);
                    }
                }
                else
                    startActivity(new Intent(Index.this,New_login.class));

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}