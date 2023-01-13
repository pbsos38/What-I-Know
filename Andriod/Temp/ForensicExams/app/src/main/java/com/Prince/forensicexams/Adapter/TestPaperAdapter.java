package com.Prince.forensicexams.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.Prince.forensicexams.BeanFiles.TestQuestion_bean;
import com.Prince.forensicexams.R;
import com.Prince.forensicexams.Test;
import com.google.android.ads.nativetemplates.NativeTemplateStyle;
import com.google.android.ads.nativetemplates.TemplateView;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import java.util.ArrayList;

public class TestPaperAdapter extends RecyclerView.Adapter<TestPaperAdapter.InnerClasssViewHolder> {
    public ArrayList<TestQuestion_bean.UserBean> aryList;
    Context ctx;
    private ArrayList<String> anss;
    private ArrayList<String> attemptness;
    private ArrayList<String> choosedOptions;
    String id0;

    public TestPaperAdapter(ArrayList<TestQuestion_bean.UserBean> data, Test testPaper, String id,
                            ArrayList<String> answers, ArrayList<String> attempt, ArrayList<String> choosedOption) {
        aryList = data;
        ctx = testPaper;
        anss = answers;
        attemptness = attempt;
        choosedOptions = choosedOption;
        id0 = id;

    }

    @NonNull
    @Override
    public InnerClasssViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        if (viewType == 0) {
            LayoutInflater inflater = LayoutInflater.from(ctx);
        View view = inflater.inflate(R.layout.layout_noques, parent, false);
            return new InnerClasssViewHolder(view);
        } else if (viewType == 2) {
            LayoutInflater inflater = LayoutInflater.from(ctx);
            View view = inflater.inflate(R.layout.layout_native_ads, parent, false);
            return new InnerClasssViewHolder(view);

        } else {
            LayoutInflater inflaterr = LayoutInflater.from(ctx);
            View viewv = inflaterr.inflate(R.layout.layout_test_question, parent, false);
            return new InnerClasssViewHolder(viewv);
        }
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull InnerClasssViewHolder holder, int position) {
        int quesno = position + 1;
        if (holder.getItemViewType() == 1) {

            TestQuestion_bean.UserBean user = aryList.get(position);
            //id0 = user.getQuizId();
            holder.question.setText("Ques: " + user.getQuestion());
            holder.optA.setText(user.getOptA());
            holder.optB.setText(user.getOptB());
            holder.optC.setText(user.getOptC());
            holder.optD.setText(user.getOptD());

            holder.optA.setChecked(false);
            holder.optB.setChecked(false);
            holder.optC.setChecked(false);
            holder.optD.setChecked(false);

            anss.add(position, "");
            attemptness.add(position, "");
            holder.optA.setOnCheckedChangeListener((buttonView, isChecked) -> {
                if (isChecked) {
                    attemptness.set(position, "attempted at " + position);
//                    choosedOptions.add("choosed option A at:"+position );
                    holder.optB.setChecked(false);
                    holder.optC.setChecked(false);
                    holder.optD.setChecked(false);
                    choosedOptions.set(position, "A");
                    if (user.getAnswer().equals("A")) {
                        anss.set(position, "correct at " + position);
                    } else
                        anss.set(position, "incorrect at " + position);
                }
            });
            holder.optB.setOnCheckedChangeListener((buttonView, isChecked) -> {
                if (isChecked) {
                    attemptness.set(position, "attempted at " + position);
                    choosedOptions.set(position, "B");
                    holder.optA.setChecked(false);
                    holder.optC.setChecked(false);
                    holder.optD.setChecked(false);
                    if (user.getAnswer().equals("B")) {
                        anss.set(position, "correct at " + position);
                    } else
                        anss.set(position, "incorrect at " + position);
                }
            });
            holder.optC.setOnCheckedChangeListener((buttonView, isChecked) -> {
                if (isChecked) {
                    attemptness.set(position, "attempted at " + position);
                    choosedOptions.set(position, "C");
                    holder.optB.setChecked(false);
                    holder.optA.setChecked(false);
                    holder.optD.setChecked(false);

                    if (user.getAnswer().equals("C")) {
                        anss.set(position, "correct at " + position);
                    } else
                        anss.set(position, "incorrect at " + position);
                }
            });
            holder.optD.setOnCheckedChangeListener((buttonView, isChecked) -> {
                if (isChecked) {
                    attemptness.set(position, "attempted at " + position);
                    choosedOptions.set(position, "D");
                    holder.optB.setChecked(false);
                    holder.optC.setChecked(false);
                    holder.optA.setChecked(false);
                    if (user.getAnswer().equals("D")) {
                        anss.set(position, "correct at " + position);
                    } else
                        anss.set(position, "incorrect at " + position);
                }
            });
        }
        else
            if (holder.getItemViewType()==2){
//                MobileAds.initialize(ctx, ctx.getString(R.string.admob_app_id));
//                AdLoader adLoader = new AdLoader.Builder(ctx, "ca-app-pub-3940256099942544/2247696110")
//                AdLoader adLoader = new AdLoader.Builder(ctx, ctx.getString(R.string.admob_native2_ad))
//                        .forUnifiedNativeAd(unifiedNativeAd -> {
//                            NativeTemplateStyle styles = new NativeTemplateStyle.Builder().build();
//                            holder.template.setStyles(styles);
//                            holder.template.setNativeAd(nativeAd); })
//                        .withAdListener(new AdListener() {
//                            @Override
//                            public void onAdFailedToLoad(LoadAdError error) {
//                                super.onAdFailedToLoad(error);
//                                String errorDomain = error.getDomain();
//                                int errorCode = error.getCode();
//                                String errorMessage = error.getMessage();
//                                //ResponseInfo responseInfo = error.getResponseInfo();
//                                AdError cause = error.getCause();
//                                Log.d("Adssad", errorDomain+"\n"+errorCode+"\n"+errorMessage+"\n"+cause);
//                                Log.d("Native ADs","failed to load");
//                            }
//
//                        }).build();

                anss.add(position, "");
                attemptness.add(position, "");
//                adLoader.loadAd(new AdRequest.Builder().build());

                Log.d("Native Banner Ads","attempting to load native ads");
                anss.add(position, "");
                attemptness.add(position, "");
            }
    }

    @Override
    public int getItemCount() {

        return aryList.size();
    }

    public static class InnerClasssViewHolder extends RecyclerView.ViewHolder {
        TextView question;
        CheckBox optA, optB, optC, optD;
        TemplateView template;
        View nativeAdLayout;
        LinearLayout adContainer ;
        public InnerClasssViewHolder(@NonNull View itemView) {
            super(itemView);
            question = itemView.findViewById(R.id.justText_testQuestion);
            optA = itemView.findViewById(R.id.justText_testQuestion_optionA);
            optB = itemView.findViewById(R.id.justText_testQuestion_optionB);
            optC = itemView.findViewById(R.id.justText_testQuestion_optionC);
            optD = itemView.findViewById(R.id.justText_testQuestion_optionD);
            template = itemView.findViewById(R.id.my_template);
            nativeAdLayout = itemView.findViewById(R.id.native_ad_container);
            adContainer = itemView.findViewById(R.id.banner_container);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (aryList.size() == 0)
            return 0;
        else if (aryList.get(position).getQuestion().equals("ad")){
            return 2;
        }
        else
            return 1;

    }

}
