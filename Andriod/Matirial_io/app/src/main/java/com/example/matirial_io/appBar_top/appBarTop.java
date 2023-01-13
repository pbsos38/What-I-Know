package com.example.matirial_io.appBar_top;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.matirial_io.R;
import com.google.android.material.appbar.AppBarLayout;

public class appBarTop extends AppCompatActivity {

    Toolbar myChildToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_bar_top);

         myChildToolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(myChildToolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.favorite:
                Toast.makeText(this, "Trying to open favourites", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.search:
                Toast.makeText(this, "Trying to open search results", Toast.LENGTH_SHORT).show();
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
    }

}