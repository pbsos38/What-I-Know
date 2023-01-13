package com.Prince.forensicexams;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.onesignal.OneSignal;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    private static final String ONESIGNAL_APP_ID ="ec86445a-d014-4bae-9c00-c6b95637ccb4" ;
    SharedPreferences onBoarding,rateStatus;
    InterstitialAd mInterstitialAd;
    private Boolean exit = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);

        // OneSignal Initialization
        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);

        // OneSignal Initialization
        OneSignal.initWithContext(this);
        OneSignal.setAppId(ONESIGNAL_APP_ID);


        onBoarding = getSharedPreferences("onBoardingScreen", MODE_PRIVATE);
        rateStatus = getSharedPreferences("rateStatusScreen", MODE_PRIVATE);
        int isFirstTime = onBoarding.getInt("firstTime", 1);
        int rateStatuss = rateStatus.getInt("rateStatus", 0);

        if (isFirstTime == 3 && rateStatuss==0) {
            SharedPreferences.Editor editor = onBoarding.edit();
            editor.putInt("firstTime", 1);
            editor.commit();
            rateus();

        } else {
            SharedPreferences.Editor editor = onBoarding.edit();
            editor.putInt("firstTime", isFirstTime+1);
            editor.commit();

        }

        AdView mAdView = findViewById(R.id.adView);
        MobileAds.initialize(this, initializationStatus -> {    });
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-1519505533431268/8084966473");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());

    }

    public void btn_practice(View view) {
        startActivity(new Intent(MainActivity.this, TestList.class));
    }

    public void btn_competition(View view) {
        Intent intent=new Intent(MainActivity.this, CompetitionPosts.class);
        intent.putExtra("from","ma");
        startActivity(intent);

    }
    private void rateus(){
        LayoutInflater inf = LayoutInflater.from(this);
        View vw = inf.inflate(R.layout.layout_rateus_notify, null);
        AlertDialog dlg = new AlertDialog.Builder(this).create();
        dlg.setView(vw);
        dlg.setCancelable(true);

        Button now = vw.findViewById(R.id.btnRateNow_rateus_notify);
        Button later = vw.findViewById(R.id.btnLater_rateus_notify);
        Button no = vw.findViewById(R.id.btnNo_rateus_notify);

        now.setOnClickListener(v -> {
            final String appPackageName = getPackageName();
            try {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
                SharedPreferences.Editor editor = rateStatus.edit();
                editor.putInt("rateStatus", 1);
                editor.apply();
            } catch (android.content.ActivityNotFoundException anfe) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
                SharedPreferences.Editor editor = rateStatus.edit();
                editor.putInt("rateStatus", 1);
                editor.apply();
            }
        });
        later.setOnClickListener(v->{
            SharedPreferences.Editor editor = rateStatus.edit();
            editor.putInt("rateStatus", 0);
            editor.apply();
            SharedPreferences.Editor editor2 = onBoarding.edit();
            editor2.putInt("firstTime", 1);
            editor2.commit();
                dlg.dismiss();

        });
        no.setOnClickListener(v->{
            SharedPreferences.Editor editor = rateStatus.edit();
            editor.putInt("rateStatus", -1);
            editor.apply();
            dlg.dismiss();


        });

        dlg.show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("TAg","paused");

    }

    @Override
    public void onBackPressed() {
        if (exit) {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish(); // finish activity
            if (mInterstitialAd.isLoaded()) {
                mInterstitialAd.show();
            } else {
                Log.d("TAG", "The interstitial wasn't loaded yet.");
            }
        } else {
            exit = true;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    exit = false;
                }
            }, 3 * 1000);

        }
    }
}