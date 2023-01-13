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

import com.forensicmart.dfsl.R;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

public class AboutUs extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private ImageView madhav_goyal;
    private DrawerLayout drawer;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        TextView aboutUs = findViewById(R.id.ID_textView_aboutUs);
        aboutUs.setText("Digital Forensic Science Library is a virtual asset of learning which isn't simply a vault with search/peruse offices yet gives a host administration to the learner.\n\n" +
                "Sifted and unified looking is utilised to work with centered looking so learner can track down the right asset with least exertion and in least time. DFSL gives learner bunch explicit administration's for example assessment preliminary for school & undergraduates and occupation hopeful administration's for specialists and general students are likewise given. \n\n" +
                "It works to offer help for all scholastic levels including research scholars and deep rooted students. All trainers and distinctively abled students. It is intended to empower individuals to gain and plan from best practice from everywhere  from the world and to work with researchers to perform interlinked investigation of numerous assets.\n\n");

        madhav_goyal = findViewById(R.id.madhav_image);
        Picasso.with(this).load(R.drawable.madhav_goyal).transform(new CircleTransform()).into(madhav_goyal);
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