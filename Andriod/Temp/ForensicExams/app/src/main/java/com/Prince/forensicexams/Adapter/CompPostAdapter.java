package com.Prince.forensicexams.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.Prince.forensicexams.BeanFiles.CompPosts_bean;
import com.Prince.forensicexams.CompetitionPosts;
import com.Prince.forensicexams.Index;
import com.Prince.forensicexams.MainActivity;
import com.Prince.forensicexams.R;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CompPostAdapter extends RecyclerView.Adapter<CompPostAdapter.InnerClassViewHolder> {
    ArrayList<CompPosts_bean.UserBean> aryList;
    static Context ctx;

    public CompPostAdapter(ArrayList<CompPosts_bean.UserBean> data, CompetitionPosts competitionPosts) {
        aryList = data;
        ctx = competitionPosts;
    }

    @NonNull
    @Override
    public InnerClassViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(ctx);
        View view = inflater.inflate(R.layout.layout_post, parent, false);
        return new InnerClassViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InnerClassViewHolder holder, int position) {
        InterstitialAd mInterstitialAd;
        // full screen
        mInterstitialAd = new InterstitialAd(ctx);
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());

        CompPosts_bean.UserBean user = aryList.get(position);
        try {
            Picasso.with(ctx).load("https://www.forensicmart.com/Android/CompPics/" + user.getPic())
                    .error(R.drawable.noimage).into(holder.image);
        } catch (Exception e) {
            Log.d("TAG", "");
        }
  /*
            if (user.getUploadedat().equals("rt")){
                final Handler handler = new Handler();
                handler.postDelayed(() ->
                        Picasso.with(ctx).load("https://www.forensicmart.com/Android/CompPics/"+user.getPic())
                        .error(R.drawable.noques).into(holder.image), 3000);
            }*/
        holder.visit.setOnClickListener(v -> {
            if (user.getUrl().startsWith("http")) {
                try {

                    if (mInterstitialAd.isLoaded()) {
                        mInterstitialAd.show();
                    } else {
                        Log.d("TAG", "The interstitial wasn't loaded yet.");
                    }


                } catch (Exception e) {
                    Log.d("TAG", e.toString());
                }
            } else {
                Toast.makeText(ctx, "It seems there is something wrong with url.", Toast.LENGTH_SHORT).show();
            }
        });
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdOpened() {
                super.onAdOpened();
            }

            @Override
            public void onAdClosed() {
                // Load the next interstitial.
                mInterstitialAd.loadAd(new AdRequest.Builder().build());

                try {
                    Uri uriUrl = Uri.parse(user.getUrl());
                    Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
                    ctx.startActivity(launchBrowser);
                } catch (Exception e) {
                    Toast.makeText(ctx, "It seems there is something wrong with url.", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }

    @Override
    public int getItemCount() {
        return aryList.size();
    }

    public static class InnerClassViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        Button visit;

        public InnerClassViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.addImage);
            visit = itemView.findViewById(R.id.visitbtn);
        }
    }

}
