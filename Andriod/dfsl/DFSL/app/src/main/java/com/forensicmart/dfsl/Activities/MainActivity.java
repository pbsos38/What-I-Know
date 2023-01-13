package com.forensicmart.dfsl.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.text.method.ScrollingMovementMethod;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.forensicmart.dfsl.Notes;
import com.forensicmart.dfsl.R;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.navigation.NavigationView;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;
    private NavigationView navigationView;
    private Button btn_video, btn_notes, btn_books;
    private TextView brandName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//      Drawer setting
        {
            Toolbar toolbar = findViewById(R.id.toolbar_customer_dashboard);
            setSupportActionBar(toolbar);
            findViewById(R.id.ID_navigationBar_brand).setSelected(true);;
            drawer = findViewById(R.id.drawer_layout_customer_dashboard);
            navigationView = findViewById(R.id.nav_view_customer_dashboard);
            //navigationView.inflateMenu(R.menu.owner_navigation_menu);
            navigationView.bringToFront();
            drawer.setDrawerShadow(R.color.drawer_shadow, GravityCompat.START);
            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            drawer.addDrawerListener(toggle);
            toggle.syncState();
            navigationView.inflateMenu(R.menu.navigation_menu);
            navigationView.setNavigationItemSelectedListener((NavigationView.OnNavigationItemSelectedListener) this);
        }
//        References
        {
            btn_books = findViewById(R.id.ID_btn_books);
            btn_notes = findViewById(R.id.ID_btn_notesGallery);
            btn_video = findViewById(R.id.ID_btn_vedioGallery);
        }
//        Listeners
        {
            btn_video.setOnClickListener(view -> {
                Intent intent = new Intent(this, Video.class);
                startActivity(intent);
            });
            btn_books.setOnClickListener(view -> {
                Intent intent = new Intent(this, Books.class);
                startActivity(intent);
            });
            btn_notes.setOnClickListener(view -> {
                Intent intent = new Intent(this, Notes.class);
                startActivity(intent);
            });
            try {
//                File filename = new File(Environment.getExternalStorageDirectory()+"/mylog1-1.log");
//                filename.createNewFile();
                String cmd = "logcat -d ";
                Toast.makeText(this,cmd, Toast.LENGTH_LONG).show();
                Runtime.getRuntime().exec(cmd);
            } catch (IOException e) {
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
            try {
                File filename = new File(Environment.getExternalStorageDirectory()+"/mylog2-1.log");
                filename.createNewFile();
                String cmd = "logcat -d -f"+filename.getAbsolutePath();
                Runtime.getRuntime().exec(cmd);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.ID_menuList_Home:
                startActivity(new Intent(this,MainActivity.class));
                break;
            case R.id.ID_menuList_disclaimer:
                startActivity(new Intent(this,Disclaimer.class));
                break;
            case R.id.ID_menuList_aboutUS:
                startActivity(new Intent(this,AboutUs.class));
                break;
            case R.id.ID_menuList_contactUs:
                Intent intent = new Intent(this, ContactUS.class);
                startActivity(intent);

                break;
            case R.id.ID_menuList_feedBack:
                break;
            case R.id.ID_menuList_moreApps:
                startActivity(new Intent(this, MoreApps.class));

                break;
            case R.id.ID_menuList_Policies:
                startActivity(new Intent(this,PrivacyPolicy.class));
                break;
            case R.id.ID_menuList_rateUS:
                break;
            case R.id.ID_menuList_share:
                startActivity(new Intent(this,Share.class));
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}