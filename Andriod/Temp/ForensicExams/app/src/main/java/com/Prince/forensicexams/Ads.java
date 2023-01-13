package com.Prince.forensicexams;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class Ads {
    Context activity;
    SharedPreferences StoredAdStatus, StoredCountAdFailed;
    private boolean adStatus = true;
    private int countAdFailed;

    public Ads(Context activity) {
        this.activity = activity;
        adStatus = StoredAdStatus.getBoolean("newAds", true);
        countAdFailed = StoredCountAdFailed.getInt("adFailedCount", 0);
    }

    public void updateAdStatus() {
        StoredCountAdFailed = activity.getSharedPreferences("adFailedCount", activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = StoredAdStatus.edit();
        editor.putInt("adFailedCount", countAdFailed++);

        StoredAdStatus = activity.getSharedPreferences("newAds", activity.MODE_PRIVATE);
        SharedPreferences.Editor editor1 = StoredAdStatus.edit();

        if (countAdFailed >= 10 && adStatus) {
            editor.putInt("adFailedCount", 0);
            editor1.putBoolean("newAds", false);
            countAdFailed =0;
            adStatus = false;
        } else if (countAdFailed >= 10 && !adStatus) {
            editor.putInt("adFailedCount", 0);
            editor1.putBoolean("newAds", true);
            countAdFailed =0;
            adStatus = true;
        }

        editor.apply();
        editor1.apply();

    }

    public String AppId(){
        if (adStatus)  return String.valueOf(R.string.admob_app_id); else return String.valueOf(R.string.admob_app_id_old);

    }

    public String InterstitialAds() {
        if (adStatus)  return String.valueOf(R.string.admob_interstitial_ad); else return String.valueOf(R.string.admob_interstitial_ad_old);
    }

    public String bannerAds(){
        if (adStatus)  return String.valueOf(R.string.admob_banner_ad); else return String.valueOf(R.string.admob_banner_ad_old);
    }

    public String NativAds(){
        if (adStatus)  return String.valueOf(R.string.admob_native_ad); else return String.valueOf(R.string.admob_native_ad_old);

    }

}
