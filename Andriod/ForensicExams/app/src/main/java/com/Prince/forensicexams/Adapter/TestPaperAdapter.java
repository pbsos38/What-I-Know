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
import com.Prince.forensicexams.R;
import com.Prince.forensicexams.Test;
import com.google.android.ads.nativetemplates.NativeTemplateStyle;
import com.google.android.ads.nativetemplates.TemplateView;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.formats.UnifiedNativeAd;

import java.util.ArrayList;

public class TestPaperAdapter extends RecyclerView.Adapter<TestPaperAdapter.InnerClasssViewHolder> {
    ArrayList<TestQuestion_bean.UserBean> aryList;
    Context ctx;
    ArrayList<String> anss;
    ArrayList<String> attemptness;
    ArrayList<String> choosedOptions;
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
                anss.add(position, "");
                attemptness.add(position, "");
//                MobileAds.initialize(ctx, "ca-app-pub-1519505533431268~6916189180");
                AdLoader adLoader = new AdLoader.Builder(ctx, "ca-app-pub-1519505533431268/3059672715")
                        .forUnifiedNativeAd(unifiedNativeAd -> {
                            NativeTemplateStyle styles = new
                                    NativeTemplateStyle.Builder().build();
                            holder.template.setStyles(styles);
                            holder.template.setNativeAd(unifiedNativeAd);

                        })
                        .build();
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
        else if (aryList.get(position).getQuestion().equals("ad")){
            return 2;
        }
        else
            return 1;

    }
}
