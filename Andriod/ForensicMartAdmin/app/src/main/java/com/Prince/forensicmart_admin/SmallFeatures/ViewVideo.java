package com.Prince.forensicmart_admin.SmallFeatures;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.Prince.forensicmart_admin.R;
import com.Prince.forensicmart_admin.server.PlayerConfig;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class ViewVideo extends YouTubeBaseActivity {

    YouTubePlayerView youTubePlayerView;
    YouTubePlayer.OnInitializedListener onInitializedListener;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_view_vedio);
        String url = getIntent().getStringExtra("url");
        String fullLink = getIntent().getStringExtra("fullurl");


       /* AdView mAdView;
        mAdView = findViewById(R.id.adView);
        MobileAds.initialize(this,getString(R.string.admob_app_id));
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);*/


        if (getSdk.currentVersion() > 7) {
            try {
                youTubePlayerView = findViewById(R.id.youtube_player);
                onInitializedListener = new YouTubePlayer.OnInitializedListener() {
                    @Override
                    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                        youTubePlayer.loadVideo(url);
                    }

                    @Override
                    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

                    }
                };

                youTubePlayerView.initialize(PlayerConfig.API_KEY, onInitializedListener);
            } catch (Exception e) {
               /* Uri uriUrl = Uri.parse(fullLink);
                Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
                startActivity(launchBrowser);*/
            }
        } else {
            // Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(fullLink));
            Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(fullLink));
            startActivity(webIntent);
            try {
                //   startActivity(appIntent);
            } catch (ActivityNotFoundException ex) {
            }

        }

    }

}
