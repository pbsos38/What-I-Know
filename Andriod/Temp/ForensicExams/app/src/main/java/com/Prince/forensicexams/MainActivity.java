package com.Prince.forensicexams;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
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

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.RequestConfiguration;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.onesignal.OneSignal;

import java.util.Arrays;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
public class MainActivity extends AppCompatActivity {
    SharedPreferences onBoarding,rateStatus;
    private InterstitialAd mInterstitialAd;
    private Boolean exit = false;


    @SuppressLint("MissingPermission")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);

        // OneSignal Initialization
        OneSignal.startInit(this)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .unsubscribeWhenNotificationsAreDisabled(true)
                .init();


        onBoarding = getSharedPreferences("onBoardingScreen", MODE_PRIVATE);
        rateStatus = getSharedPreferences("rateStatusScreen", MODE_PRIVATE);
        int isFirstTime = onBoarding.getInt("firstTime", 1);
        int rateStatuss = rateStatus.getInt("rateStatus", 0);

        if (isFirstTime == 3 && rateStatuss==0) {
            SharedPreferences.Editor editor = onBoarding.edit();
            editor.putInt("firstTime", 1);
            editor.apply();
            rateus();

        } else {
            SharedPreferences.Editor editor = onBoarding.edit();
            editor.putInt("firstTime", isFirstTime+1);
            editor.apply();

        }

        AdView mAdView;
        MobileAds.initialize(this, initializationStatus -> {});
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        // interstitial ad used when exiting app
        AdRequest adRequest1 = new AdRequest.Builder().build();
        InterstitialAd.load(this,getString(R.string.admob_interstitial_ad), adRequest1,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        // The mInterstitialAd reference will be null until
                        // an ad is loaded.
                        mInterstitialAd = interstitialAd;
                        Log.i("TAG", "onAdLoaded");
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error
                        Log.i("TAg", loadAdError.toString());
                        mInterstitialAd = null;

                    }
                });
//        mInterstitialAd = new InterstitialAd(this);
//        mInterstitialAd.setAdUnitId(getString(R.string.admob_interstitial_ad));
//        mInterstitialAd.loadAd(new AdRequest.Builder().build());


    }

    public void btn_practice(View view) {
        startActivity(new Intent(MainActivity.this, TestList.class));
    }

    public void btn_competition(View view) {
        Intent intent=new Intent(MainActivity.this, CompetitionPosts.class);
        intent.putExtra("from","ma");
        startActivity(intent);

    }

    public void btn_contri(View view){
        startActivity(new Intent(MainActivity.this,Contributers.class));
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
            if (mInterstitialAd!=null) {
                mInterstitialAd.show(this);
                mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                    @Override
                    public void onAdDismissedFullScreenContent() {
                        Intent intents = new Intent(Intent.ACTION_MAIN);
//                        intents.addCategory(Intent.CATEGORY_HOME);
//                        intents.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }

                    @Override
                    public void onAdFailedToShowFullScreenContent(@NonNull AdError adError) {
                        Intent intents = new Intent(Intent.ACTION_MAIN);
//                        intents.addCategory(Intent.CATEGORY_HOME);
//                        intents.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        Log.d("adscheck","asd");
                        startActivity(intent);
                    }
                });


            } else {
                Log.d("TAG", "The interstitial wasn't loaded yet.");
            }
            finish(); // finish activity

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