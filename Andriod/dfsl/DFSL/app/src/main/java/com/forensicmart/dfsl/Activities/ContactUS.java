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
import android.widget.Toast;

import com.forensicmart.dfsl.Activities.server.Conn;
import com.forensicmart.dfsl.Activities.server.ServerRes;
import com.forensicmart.dfsl.Activities.server.SetDataToDatabase;
import com.forensicmart.dfsl.R;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Random;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class ContactUS extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener  {
    TextInputLayout subject,email,msg;
    TextInputEditText subject_text,email_text,msg_text;
    private DrawerLayout drawer;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);
        {
            subject = findViewById(R.id.ID_ContactUS_name);
            subject_text = findViewById(R.id.ID_ContactUS_name_input);
            email = findViewById(R.id.ID_ContactUS_email);
            email_text = findViewById(R.id.ID_ContactUS_email_input);
            msg = findViewById(R.id.ID_ContactUS_msg);
            msg_text = findViewById(R.id.ID_ContactUS_msg_input);

        }
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
            navigationView.inflateMenu(com.forensicmart.dfsl.R.menu.navigation_menu);
            navigationView.setNavigationItemSelectedListener((NavigationView.OnNavigationItemSelectedListener) this);
        }

    }

    public void send_contact(View view) {
        SetDataToDatabase con = Conn.doConnect();
        Random rand = new Random();
        int x = rand.nextInt(500);
        con.ContactUS(subject_text.getText().toString(), email_text.getText().toString(), msg_text.getText().toString(), new Callback<ServerRes>() {
            @Override
            public void success(ServerRes serverResponse, Response response) {
                Toast.makeText(ContactUS.this, "Sent!", Toast.LENGTH_LONG).show();
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(ContactUS.this, "Error Occurred. Please Try Again"+error, Toast.LENGTH_LONG).show();
            }
        });
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