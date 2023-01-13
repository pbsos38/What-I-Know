package com.forensicmart.dfsl.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.forensicmart.dfsl.R;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

public class MoreApps extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_apps);

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
        {
            ImageView forensicMart, forensicExams, kanakDhara;
            Button forensicMartBtn,forensicExams_btn, KanakDhara_btn;
            forensicMart = findViewById(R.id.ForensicMart);
            forensicExams = findViewById(R.id.ForensicExams_img);
            kanakDhara = findViewById(R.id.KanakDharaStotram_img);
            forensicExams_btn = findViewById(R.id.ID_btn_viewForensicExam);
            forensicMartBtn = findViewById(R.id.ID_btn_viewForensicMart);
            KanakDhara_btn = findViewById(R.id.ID_btn_viewKanakDharaStotram);
            Picasso.with(this).load("https://play-lh.googleusercontent.com/QjaRHlF1cE0IHigXqjtBv50qKVNoiaoymaDAXnP7w8NbC6GU7Slc5a2PrJpInbaCjg=w240-h480-rw").transform(new CircleTransform()).into(forensicMart);
            Picasso.with(this).load("https://play-lh.googleusercontent.com/J78EjKe_lEeIVYlRYy1hQXeEmRtaIFiqIf-bRa83ZX82U_qXhWwNKKO9VO75HmUi7N4=w240-h480-rw").transform(new CircleTransform()).into(forensicExams);
            Picasso.with(this).load("https://play-lh.googleusercontent.com/ulG3AzTcxFy6RFmxRI3Y5TJl8sb1VIysOW-mXEN4XHiN1InP__HNXt334CTyw5rBrA=w240-h480-rw").transform(new CircleTransform()).into(kanakDhara);
            forensicExams_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Uri uri = Uri.parse("https://play.google.com/store/apps/details?id=com.Prince.forensicexams"); // missing 'http://' will cause crashed
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);
                }
            });
            forensicMartBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Uri uri = Uri.parse("https://play.google.com/store/apps/details?id=com.forensic.mart"); // missing 'http://' will cause crashed
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);
                }
            });
            KanakDhara_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Uri uri = Uri.parse("https://play.google.com/store/apps/details?id=com.tanzible.kanakdharastrotram"); // missing 'http://' will cause crashed
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);
                }
            });
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
                startActivity(new Intent(this, Share.class));

                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}