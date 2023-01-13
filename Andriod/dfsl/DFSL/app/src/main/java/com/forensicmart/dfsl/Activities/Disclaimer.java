package com.forensicmart.dfsl.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.forensicmart.dfsl.Activities.ContactUS;
import com.forensicmart.dfsl.R;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

public class Disclaimer extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.forensicmart.dfsl.R.layout.activity_disclaimer);
        TextView text = findViewById(R.id.ID_textView_Disclaimer);
        text.setText("Digital Forensic Science Library(DFSL) provides all the relevant content related to Forensic Science at one place. All the content is assembled from publicly available respective sources. DFSL has no responsibility or liability for authenticity, accuracy, Completeness, relevance and suitability of these content. Assuming that you accept that any document utilised in this library/application or its property encroaches your copyright, you may send us a notification including all the significant data expected for confirmation at forensicmart@gmail.com\n" +
                "You can also send us your questions at similar email address and we will hit you as soon as possible.\n\n");

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