package com.Prince.forensicmart_admin.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.Prince.forensicmart_admin.LocalDatabase.DataBaseHelper;
import com.Prince.forensicmart_admin.R;
import com.Prince.forensicmart_admin.SmallFeatures.PushNoti;
import com.Prince.forensicmart_admin.SmallFeatures.ViewDialog;
import com.Prince.forensicmart_admin.SmallFeatures.myString;
import com.Prince.forensicmart_admin.server.Conn;
import com.Prince.forensicmart_admin.server.ServerResponse;
import com.Prince.forensicmart_admin.server.SetDataToDatabase;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.onesignal.OneSignal;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class Dashboard extends AppCompatActivity {
    myString myString;
    ViewDialog viewDialog;
    private FirebaseAnalytics mFirebaseAnalytics;
    private MaterialToolbar appBarLayout;
    DataBaseHelper mydb;

    // private DrawerLayout drawer;
    //NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        mydb = new DataBaseHelper(this);
        myString = new myString(this);
        viewDialog = new ViewDialog(this);

        appBarLayout = findViewById(R.id.topAppBar);
        // Obtain the FirebaseAnalytics instance.
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        /*  *//* Navigation Bar*//*
        {
            Toolbar toolbar = findViewById(R.id.toolbar_dashBoard);
            setSupportActionBar(toolbar);

            drawer = findViewById(R.id.drawer_layout_dashBoard);
            navigationView = findViewById(R.id.nav_view_dashboard);
            //viewFlipper = navigationView.findViewById(R.id.image_slideShow_nav_header);
            //navigationView.inflateMenu(R.menu.owner_navigation_menu);
            navigationView.bringToFront();
            drawer.setDrawerShadow(R.color.drawer_shadow, GravityCompat.START);
            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            drawer.addDrawerListener(toggle);
            toggle.syncState();
            navigationView.setNavigationItemSelectedListener(this);
            *//*int events[] = {R.drawable.eventone, R.drawable.eventtwo, R.drawable.eventthree, R.drawable.eventfour};
            for (int event : events)
                slideShow_navigation(event);*//*
        }*/

        //google ads
        {
            AdView mAdView = findViewById(R.id.adView);
            MobileAds.initialize(this,getString(R.string.banner_adunit_id2));
            AdRequest adRequest = new AdRequest.Builder().build();
            mAdView.loadAd(adRequest);

        }

        // OneSignal Initialization
        OneSignal.startInit(this)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .unsubscribeWhenNotificationsAreDisabled(true)
                .init();
        //
        appBarLayout.setNavigationOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        });

    }

    public void post(View view) {
        startActivity(new Intent(Dashboard.this, CreatePost.class));
    }

    public void openCompanies(View view) {
        Intent intent = new Intent(this, Posts.class);
        intent.putExtra("website", "company");
        startActivity(intent);
    }

    public void openColleges(View view) {
        Intent intent2 = new Intent(this, Posts.class);
        intent2.putExtra("website", "college");
        startActivity(intent2);
    }

    public void openPublications(View view) {
        Intent intent3 = new Intent(this, Posts.class);
        intent3.putExtra("website", "publication");
        startActivity(intent3);
    }

    public void openNGOS(View view) {
        Intent intent4 = new Intent(this, Posts.class);
        intent4.putExtra("website", "society");
        startActivity(intent4);
    }

    public void openFB(View view) {
        Intent intent5 = new Intent(this, Posts.class);
        intent5.putExtra("website", "facebook");
        startActivity(intent5);
    }

    public void openYT(View view) {
        Intent intent6 = new Intent(this, Posts.class);
        intent6.putExtra("website", "youtube");
        startActivity(intent6);
    }

    public void openFSL(View view) {
        Intent intent7 = new Intent(this, Posts.class);
        intent7.putExtra("website", "fsl");
        startActivity(intent7);
    }

    public void openCourses(View view) {
        Intent intent8 = new Intent(this, Courses.class);
        startActivity(intent8);
    }

    public void openCompetition(View view) {
        Intent intent9 = new Intent(this, Posts.class);
        intent9.putExtra("website", "competition");
        startActivity(intent9);
    }

    public void openConference(View view) {

    }

    public void openVideos(View view) {
        Intent intent9 = new Intent(this, Videos.class);
        startActivity(intent9);
    }

    public void VideoAdd(View view) {
        final AlertDialog.Builder dlg = new AlertDialog.Builder(this);

        LayoutInflater inf = LayoutInflater.from(this);
        View vw = inf.inflate(R.layout.layout_add_video, null);
        dlg.setView(vw);
        dlg.setCancelable(true);

        Button btn = (Button) vw.findViewById(R.id.AddBtn);
        TextInputEditText name = vw.findViewById(R.id.Video_name);
        TextInputEditText link = vw.findViewById(R.id.PostLink);
        TextInputLayout name_lay = vw.findViewById(R.id.videoName_lay);
        TextInputLayout post_lay = vw.findViewById(R.id.PostLink_lay);
        btn.setOnClickListener(v -> {
            String namee = name.getText().toString().trim();
            if (namee.isEmpty()) {
                name_lay.setHelperTextEnabled(false);
                name_lay.setErrorEnabled(true);
                name_lay.setError("Please Enter Post Name");
                return;
            }

            String linkk = link.getText().toString().trim();
            if (linkk.isEmpty()) {
                post_lay.setHelperTextEnabled(false);
                post_lay.setErrorEnabled(true);
                post_lay.setError("Please enter The Post Link");
                return;

            }

            viewDialog.showDialog();
            SetDataToDatabase con = Conn.doConnect();
            con.addVideo(linkk, namee, new Callback<ServerResponse>() {

                @Override
                public void success(ServerResponse serverResponse, Response response) {
                    viewDialog.hideDialog();
                    Toast.makeText(Dashboard.this, "" + serverResponse.msg, Toast.LENGTH_SHORT).show();
                }

                @Override
                public void failure(RetrofitError error) {
                    if (error.getKind().equals(RetrofitError.Kind.NETWORK)) {
                        Toast.makeText(Dashboard.this, "No Internet connection found.", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(Dashboard.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                    viewDialog.hideDialog();
                }
            });


        });

        dlg.create();
        dlg.show();


    }

    public void logout(View view) {
        Intent intent4 = new Intent(this, Index.class);
        Boolean deletedRows = mydb.deleteData();
        if (deletedRows == true) {
            Toast.makeText(this, "Log-out successful", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Please Log out Again", Toast.LENGTH_SHORT).show();
        }
        startActivity(intent4);

        finish();
    }

    public void AddQuestion(View view) {
        startActivity(new Intent(Dashboard.this, NewQues.class));
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    public void CompetitionAdd(View view) {

        Intent intent = new Intent(Dashboard.this,CompetitionPosts.class);
        intent.putExtra("from","ma");
        startActivity(intent);

    }

    public void CertificateManagers(View view) {
        startActivity(new Intent(Dashboard.this, CertificateManager.class));
    }
}