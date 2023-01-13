package com.forensic.mart.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.forensic.mart.Activities.Posts;
import com.forensic.mart.BeanFiles.Posts_beans;
import com.forensic.mart.R;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PostsAdpater extends RecyclerView.Adapter<PostsAdpater.InnerViewClassHolder> {
    ArrayList<Posts_beans.UserBean> aryList;
    static Context ctx;
    String type;

    public PostsAdpater(ArrayList<Posts_beans.UserBean> data, Posts dashboard) {
        aryList = data;
        ctx = dashboard;
    }

    @NonNull
    @Override
    public PostsAdpater.InnerViewClassHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(ctx);
        View view = inflater.inflate(R.layout.layout_post, parent, false);
        //RecyclerViewHolder holder=new RecyclerViewHolder(view);
        InnerViewClassHolder itemView = new InnerViewClassHolder(view);
        return itemView;
    }

    @Override
    public void onBindViewHolder(@NonNull InnerViewClassHolder holder, int position) {
        InterstitialAd mInterstitialAd;
        // full screen
        mInterstitialAd = new InterstitialAd(ctx);
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());


        Posts_beans.UserBean user = aryList.get(position);
        holder.name.setText(user.getPostname());
        try {
            Picasso.with(ctx).load("https://www.forensicmart.com/Android/PostPics/" + user.getImage())
                    .error(R.drawable.noimage)
                    .into(holder.image);
        } catch (Exception ignored) {

        }
        holder.visit.setOnClickListener(v -> {
            try {
                type = "visit";

                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                } else {
                    Log.d("TAG", "The interstitial wasn't loaded yet.");
                }


            } catch (Exception e) {
               /* Intent intent = new Intent(ctx, OpenWeb.class);
                intent.putExtra("link",user.getPostlink());
                ctx.startActivity(intent);*/

                Log.d("TAG", e.toString());
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

                if (type.equals("visit")) {
                    Uri uriUrl = Uri.parse(user.getPostlink());
                    Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
                    ctx.startActivity(launchBrowser);
                }


            }
        });
    }

    @Override
    public int getItemCount() {
        return aryList.size();
    }

    public static class InnerViewClassHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView name;
        Button visit, copylink;

        public InnerViewClassHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.addImage);
            name = itemView.findViewById(R.id.postName);
            visit = itemView.findViewById(R.id.visitbtn);
            //copylink = itemView.findViewById(R.id.copylink);
        }
    }


}
