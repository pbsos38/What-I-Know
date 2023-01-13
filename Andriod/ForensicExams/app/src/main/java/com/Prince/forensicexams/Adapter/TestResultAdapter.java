package com.Prince.forensicexams.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.Prince.forensicexams.BeanFiles.TestQuestion_bean;
import com.Prince.forensicexams.BeanFiles.TestQuestion_bean_result;
import com.Prince.forensicexams.R;
import com.Prince.forensicexams.Test;
import com.google.android.ads.nativetemplates.NativeTemplateStyle;
import com.google.android.ads.nativetemplates.TemplateView;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.formats.UnifiedNativeAd;

import java.util.ArrayList;

public class TestResultAdapter extends RecyclerView.Adapter<TestResultAdapter.InnerClasssViewHolder> {
    ArrayList<TestQuestion_bean.UserBean> aryList;
    Context ctx;
    ArrayList<String> anss;
    ArrayList<String> attemptness;
    ArrayList<String> choosedAnswer;

    public TestResultAdapter(ArrayList<TestQuestion_bean.UserBean> data, Test testPaper,
                             ArrayList<String> answers, ArrayList<String> attempt, ArrayList<String> choosedAns) {
        aryList = data;
        ctx = testPaper;
        anss = answers;
        attemptness = attempt;
        choosedAnswer = choosedAns;
    }

    @NonNull
    @Override
    public InnerClasssViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 0) {
            LayoutInflater inflater = LayoutInflater.from(ctx);
            View view = inflater.inflate(R.layout.layout_noques, parent, false);
            return new TestResultAdapter.InnerClasssViewHolder(view);
        } else if (viewType == 2) {
            LayoutInflater inflater = LayoutInflater.from(ctx);
            View view = inflater.inflate(R.layout.layout_native_ads, parent, false);
            return new TestResultAdapter.InnerClasssViewHolder(view);

        } else {
            LayoutInflater inflaterr = LayoutInflater.from(ctx);
            View viewv = inflaterr.inflate(R.layout.layout_test_question, parent, false);
            return new TestResultAdapter.InnerClasssViewHolder(viewv);
        }
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull InnerClasssViewHolder holder, int position) {
        MobileAds.initialize(ctx, "ca-app-pub-3692082932732255~5606890252");
        AdLoader adLoader = new AdLoader.Builder(ctx, "ca-app-pub-3692082932732255/6645489817")
                .forUnifiedNativeAd(new UnifiedNativeAd.OnUnifiedNativeAdLoadedListener() {
                    @Override
                    public void onUnifiedNativeAdLoaded(UnifiedNativeAd unifiedNativeAd) {
                        NativeTemplateStyle styles = new
                                NativeTemplateStyle.Builder().build();


                        holder.template.setStyles(styles);
                        holder.template.setNativeAd(unifiedNativeAd);

                    }
                })
                .build();
        if (holder.getItemViewType() == 1) {
            TestQuestion_bean.UserBean user = aryList.get(position);
            int quesno = position + 1;
            holder.question.setText("Ques: " + user.getQuestion());
            holder.optA.setText(user.getOptA());
            holder.optB.setText(user.getOptB());
            holder.optC.setText(user.getOptC());
            holder.optD.setText(user.getOptD());

            holder.optA.setEnabled(false);
            holder.optB.setEnabled(false);
            holder.optC.setEnabled(false);
            holder.optD.setEnabled(false);


            if (choosedAnswer.get(position).equals("no")) {
                assert user.getAnswer() != null;
                if (user.getAnswer().equals("A")) {
                    holder.optA.setBackgroundColor(ctx.getResources().getColor(R.color.colorAccent));
                    holder.optA.setChecked(true);
                    holder.optB.setBackgroundColor(ctx.getResources().getColor(R.color.White));
                    holder.optB.setChecked(false);
                    holder.optC.setBackgroundColor(ctx.getResources().getColor(R.color.White));
                    holder.optC.setChecked(false);
                    holder.optD.setBackgroundColor(ctx.getResources().getColor(R.color.White));
                    holder.optD.setChecked(false);
                    holder.optA.setTextColor(ctx.getResources().getColor(R.color.apptheme_color));
                } else if (user.getAnswer().equals("B")) {
                    holder.optB.setBackgroundColor(ctx.getResources().getColor(R.color.colorAccent));
                    holder.optB.setChecked(true);
                    holder.optA.setBackgroundColor(ctx.getResources().getColor(R.color.White));
                    holder.optA.setChecked(false);
                    holder.optC.setBackgroundColor(ctx.getResources().getColor(R.color.White));
                    holder.optC.setChecked(false);
                    holder.optD.setBackgroundColor(ctx.getResources().getColor(R.color.White));
                    holder.optD.setChecked(false);
                    holder.optB.setTextColor(ctx.getResources().getColor(R.color.apptheme_color));
                } else if (user.getAnswer().equals("C")) {
                    holder.optC.setBackgroundColor(ctx.getResources().getColor(R.color.colorAccent));
                    holder.optC.setChecked(true);
                    holder.optB.setBackgroundColor(ctx.getResources().getColor(R.color.White));
                    holder.optB.setChecked(false);
                    holder.optA.setBackgroundColor(ctx.getResources().getColor(R.color.White));
                    holder.optA.setChecked(false);
                    holder.optD.setBackgroundColor(ctx.getResources().getColor(R.color.White));
                    holder.optD.setChecked(false);
                    holder.optC.setTextColor(ctx.getResources().getColor(R.color.apptheme_color));
                } else if (user.getAnswer().equals("D")) {
                    holder.optD.setBackgroundColor(ctx.getResources().getColor(R.color.colorAccent));
                    holder.optD.setChecked(true);
                    holder.optB.setBackgroundColor(ctx.getResources().getColor(R.color.White));
                    holder.optB.setChecked(false);
                    holder.optC.setBackgroundColor(ctx.getResources().getColor(R.color.White));
                    holder.optC.setChecked(false);
                    holder.optA.setBackgroundColor(ctx.getResources().getColor(R.color.White));
                    holder.optA.setChecked(false);
                    holder.optD.setTextColor(ctx.getResources().getColor(R.color.apptheme_color));
                }
            } else if (choosedAnswer.get(position).equals("A") || choosedAnswer.get(position).equals("B") ||
                    choosedAnswer.get(position).equals("C") || choosedAnswer.get(position).equals("D")) {
                if (choosedAnswer.get(position).equals("A") && user.getAnswer().equals("A")) {
                    holder.optA.setBackgroundColor(ctx.getResources().getColor(R.color.green));
                    holder.optB.setBackgroundColor(ctx.getResources().getColor(R.color.White));
                    holder.optC.setBackgroundColor(ctx.getResources().getColor(R.color.White));
                    holder.optD.setBackgroundColor(ctx.getResources().getColor(R.color.White));
                    holder.optA.setChecked(true);
                    holder.optB.setChecked(false);
                    holder.optC.setChecked(false);
                    holder.optD.setChecked(false);
                    holder.optA.setTextColor(ctx.getResources().getColor(R.color.apptheme_color));
                } else if (choosedAnswer.get(position).equals("B")
                        && user.getAnswer().equals("B")) {
                    holder.optB.setBackgroundColor(ctx.getResources().getColor(R.color.green));
                    holder.optA.setBackgroundColor(ctx.getResources().getColor(R.color.White));
                    holder.optC.setBackgroundColor(ctx.getResources().getColor(R.color.White));
                    holder.optD.setBackgroundColor(ctx.getResources().getColor(R.color.White));
                    holder.optB.setChecked(true);
                    holder.optA.setChecked(false);
                    holder.optC.setChecked(false);
                    holder.optD.setChecked(false);
                    holder.optB.setTextColor(ctx.getResources().getColor(R.color.apptheme_color));
                } else if (choosedAnswer.get(position).equals("C") && user.getAnswer().equals("C")) {
                    holder.optC.setBackgroundColor(ctx.getResources().getColor(R.color.green));
                    holder.optB.setBackgroundColor(ctx.getResources().getColor(R.color.White));
                    holder.optA.setBackgroundColor(ctx.getResources().getColor(R.color.White));
                    holder.optD.setBackgroundColor(ctx.getResources().getColor(R.color.White));
                    holder.optC.setChecked(true);
                    holder.optA.setChecked(false);
                    holder.optB.setChecked(false);
                    holder.optD.setChecked(false);
                    holder.optC.setTextColor(ctx.getResources().getColor(R.color.apptheme_color));
                } else if (choosedAnswer.get(position).equals("D") && user.getAnswer().equals("D")) {
                    holder.optD.setBackgroundColor(ctx.getResources().getColor(R.color.green));
                    holder.optB.setBackgroundColor(ctx.getResources().getColor(R.color.White));
                    holder.optC.setBackgroundColor(ctx.getResources().getColor(R.color.White));
                    holder.optA.setBackgroundColor(ctx.getResources().getColor(R.color.White));
                    holder.optD.setChecked(true);
                    holder.optA.setChecked(false);
                    holder.optB.setChecked(false);
                    holder.optC.setChecked(false);
                    holder.optD.setTextColor(ctx.getResources().getColor(R.color.apptheme_color));
                } else {

                    switch (user.getAnswer()) {
                        case "A":
                            holder.optA.setBackgroundColor(ctx.getResources().getColor(R.color.colorAccent));
                            holder.optA.setChecked(true);
                            holder.optB.setBackgroundColor(ctx.getResources().getColor(R.color.White));
                            holder.optB.setChecked(false);
                            holder.optC.setBackgroundColor(ctx.getResources().getColor(R.color.White));
                            holder.optC.setChecked(false);
                            holder.optD.setBackgroundColor(ctx.getResources().getColor(R.color.White));
                            holder.optD.setChecked(false);
                            holder.optA.setTextColor(ctx.getResources().getColor(R.color.apptheme_color));
                            break;
                        case "B":
                            holder.optB.setBackgroundColor(ctx.getResources().getColor(R.color.colorAccent));
                            holder.optB.setChecked(true);
                            holder.optA.setBackgroundColor(ctx.getResources().getColor(R.color.White));
                            holder.optA.setChecked(false);
                            holder.optC.setBackgroundColor(ctx.getResources().getColor(R.color.White));
                            holder.optC.setChecked(false);
                            holder.optD.setBackgroundColor(ctx.getResources().getColor(R.color.White));
                            holder.optD.setChecked(false);
                            holder.optB.setTextColor(ctx.getResources().getColor(R.color.apptheme_color));
                            break;
                        case "C":
                            holder.optC.setBackgroundColor(ctx.getResources().getColor(R.color.colorAccent));
                            holder.optC.setChecked(true);
                            holder.optB.setBackgroundColor(ctx.getResources().getColor(R.color.White));
                            holder.optB.setChecked(false);
                            holder.optA.setBackgroundColor(ctx.getResources().getColor(R.color.White));
                            holder.optA.setChecked(false);
                            holder.optD.setBackgroundColor(ctx.getResources().getColor(R.color.White));
                            holder.optD.setChecked(false);
                            holder.optC.setTextColor(ctx.getResources().getColor(R.color.apptheme_color));
                            break;
                        case "D":
                            holder.optD.setBackgroundColor(ctx.getResources().getColor(R.color.colorAccent));
                            holder.optD.setChecked(true);
                            holder.optB.setBackgroundColor(ctx.getResources().getColor(R.color.White));
                            holder.optB.setChecked(false);
                            holder.optC.setBackgroundColor(ctx.getResources().getColor(R.color.White));
                            holder.optC.setChecked(false);
                            holder.optA.setBackgroundColor(ctx.getResources().getColor(R.color.White));
                            holder.optA.setChecked(false);
                            holder.optD.setTextColor(ctx.getResources().getColor(R.color.apptheme_color));
                            break;
                    }

                    if (choosedAnswer.get(position).equals("A") && !user.getAnswer().equals("A")) {
                        holder.optA.setBackgroundColor(ctx.getResources().getColor(R.color.red));
                        holder.optA.setChecked(true);
                        holder.optA.setTextColor(ctx.getResources().getColor(R.color.apptheme_color));

                    } else if (choosedAnswer.get(position).equals("B") && !user.getAnswer().equals("B")) {
                        holder.optB.setBackgroundColor(ctx.getResources().getColor(R.color.red));
                        holder.optB.setChecked(true);
                        holder.optB.setTextColor(ctx.getResources().getColor(R.color.apptheme_color));
                    } else if (choosedAnswer.get(position).equals("C") && !user.getAnswer().equals("C")) {
                        holder.optC.setBackgroundColor(ctx.getResources().getColor(R.color.red));
                        holder.optC.setChecked(true);
                        holder.optC.setTextColor(ctx.getResources().getColor(R.color.apptheme_color));
                    } else if (choosedAnswer.get(position).equals("D") && !user.getAnswer().equals("D")) {
                        holder.optD.setBackgroundColor(ctx.getResources().getColor(R.color.red));
                        holder.optD.setChecked(true);
                        holder.optD.setTextColor(ctx.getResources().getColor(R.color.apptheme_color));
                    }

                }

            }
        }
        else
        if (holder.getItemViewType()==2){
            adLoader.loadAd(new AdRequest.Builder().build());

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

        public InnerClasssViewHolder(@NonNull View itemView) {
            super(itemView);
            question = itemView.findViewById(R.id.justText_testQuestion);
            optA = itemView.findViewById(R.id.justText_testQuestion_optionA);
            optB = itemView.findViewById(R.id.justText_testQuestion_optionB);
            optC = itemView.findViewById(R.id.justText_testQuestion_optionC);
            optD = itemView.findViewById(R.id.justText_testQuestion_optionD);
            template = itemView.findViewById(R.id.my_template);

        }
    }

    @Override
    public int getItemViewType(int position) {
        if (aryList.size() == 0)
            return 0;
        else if (aryList.get(position).getQuestion().equals("ad")) {
            return 2;
        } else
            return 1;

    }
}
