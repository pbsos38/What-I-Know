package com.association.mansabar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.association.mansabar.admin.Admin_Advocates;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void launch_lawyers(View view) {
        Intent intent = new Intent(getBaseContext(), Advocates.class);
        startActivity(intent);
    }

    public void adminLogin(View view) {
        Intent intent = new Intent(getBaseContext(), Admin_Advocates.class);
        startActivity(intent);
    }

    public void Ecourtes(View view) {
        Intent viewIntent =
                new Intent("android.intent.action.VIEW",
                        Uri.parse("https://districts.ecourts.gov.in/mansa"));
        startActivity(viewIntent);
    }
}