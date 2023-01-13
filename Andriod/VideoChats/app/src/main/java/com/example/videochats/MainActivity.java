package com.example.videochats;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView navigationView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navigationView = findViewById(R.id.bottom_navigation);
        navigationView.setOnNavigationItemReselectedListener(navigationItemSelectedListner);

    }

    private BottomNavigationView.OnNavigationItemReselectedListener navigationItemSelectedListner = new BottomNavigationView.OnNavigationItemReselectedListener() {
        @Override
        public void onNavigationItemReselected(@NonNull MenuItem item) {
            switch (item.getItemId()){
                case R.id.page_1:
                    startActivity(new Intent(MainActivity.this,MainActivity.class));
                    break;
                case R.id.page_2:
                    startActivity(new Intent(MainActivity.this,Settings.class));
                    break;
                case R.id.page_3:
                    startActivity(new Intent(MainActivity.this,Notification.class));
                    break;
                case R.id.page_4:
                    FirebaseAuth.getInstance().signOut();
                    startActivity(new Intent(MainActivity.this,MainActivity.class));
                    finish();
                    break;
            }
        }
    };


}

