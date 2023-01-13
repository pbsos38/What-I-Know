package com.example.ads.native2;


import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ads.R;
import com.google.android.ads.nativetemplates.TemplateView;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.formats.UnifiedNativeAd;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.util.ArrayList;

class card_set_content extends RecyclerView.Adapter<card_set_content.InnerClassViewHolder> {

    ArrayList namee;
    static Context ctx;

    public card_set_content(ArrayList name, MainActivity ct) {
        namee = name;
        ctx = ct;
    }


    @NonNull
    @Override
    public InnerClassViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 1) {

            LayoutInflater inflater = LayoutInflater.from(ctx);
            View view = inflater.inflate(R.layout.layout_native_ads, parent, false);
            card_set_content.InnerClassViewHolder itemView = new card_set_content.InnerClassViewHolder(view);
            return itemView;
        } else {
            LayoutInflater inflater = LayoutInflater.from(ctx);
            View view = inflater.inflate(R.layout.single_item, parent, false);
            card_set_content.InnerClassViewHolder itemView2 = new card_set_content.InnerClassViewHolder(view);
            return itemView2;

        }

    }

    @Override
    public void onBindViewHolder(@NonNull final InnerClassViewHolder holder, final int position) {

        Log.d("TAG",""+namee);
        final int itemType = getItemViewType(position);
        if (itemType == 1) {
            namee.add(position,"");
            MobileAds.initialize(ctx, new OnInitializationCompleteListener() {
                @Override
                public void onInitializationComplete(InitializationStatus initializationStatus) {

                }
            });

            AdLoader.Builder builder = new AdLoader.Builder(ctx, ctx.getString(R.string.admob_app_id));
            builder.forUnifiedNativeAd(new UnifiedNativeAd.OnUnifiedNativeAdLoadedListener() {
                @Override
                public void onUnifiedNativeAdLoaded(UnifiedNativeAd unifiedNativeAd) {
                    TemplateView templateView = holder.builder.findViewById((R.id.my_template));
                    templateView.setNativeAd(unifiedNativeAd);
                }
            });
            AdLoader adLoader = builder.build();
            AdRequest adRequest1 = new AdRequest.Builder().build();
            adLoader.loadAd(adRequest1);
        } else if (itemType == 0) {
            holder.nameView.setText(namee.get(position).toString());
        }
    }


    @Override
    public int getItemCount() {
        return 7;
    }

    public class InnerClassViewHolder extends RecyclerView.ViewHolder {
        public Activity builder;
        TextView nameView;

        public InnerClassViewHolder(@NonNull View itemView) {
            super(itemView);

            nameView = itemView.findViewById(R.id.name);

        }
    }

    @Override
    public int getItemViewType(int position) {
        /* int x=0;
        for (int c=0;c<namee.size();c++){
            if (x!=0){
                namee.add(c,"ad");
                x=0;
            }
            else
                if (x==0){
                    x=1;
                }
        }*/
        if (position ==0 || position==4)
        {
            return 1;
        }
        else
            return 0;
    }
}
