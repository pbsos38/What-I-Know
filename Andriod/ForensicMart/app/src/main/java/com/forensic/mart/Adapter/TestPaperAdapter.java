package com.forensic.mart.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.ads.nativetemplates.NativeTemplateStyle;
import com.google.android.ads.nativetemplates.TemplateView;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.forensic.mart.BeanFiles.TestQuestion_bean;
import com.forensic.mart.R;

import java.util.ArrayList;

public class TestPaperAdapter extends RecyclerView.Adapter<TestPaperAdapter.InnerClasssViewHolder> {
    ArrayList<TestQuestion_bean.UserBean> aryList;
    Context ctx;
    ArrayList<String> anss;
    ArrayList<String> attemptness;
    ArrayList<String> choosedOptions;
    String id0;

    public TestPaperAdapter(ArrayList<TestQuestion_bean.UserBean> data, FragmentActivity testPaper, String id,
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
            id0 = user.getQuizId();
            holder.question.setText("Ques: " + user.getQuestion());
            holder.optA.setText(user.getOptionA());
            holder.optB.setText(user.getOptionB());
            holder.optC.setText(user.getOptionC());
            holder.optD.setText(user.getOptionD());

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
                MobileAds.initialize(ctx, ctx.getString(R.string.admob_app_id));
                AdLoader adLoader = new AdLoader.Builder(ctx, ctx.getString(R.string.admob_native_ad))
                        .forUnifiedNativeAd(unifiedNativeAd -> {
                            NativeTemplateStyle styles = new NativeTemplateStyle.Builder().build();
                            holder.template.setStyles(styles);
                            holder.template.setNativeAd(unifiedNativeAd); })
                        .withAdListener(new AdListener() {
                            @Override
                            public void onAdFailedToLoad(LoadAdError loadAdError) {
                                super.onAdFailedToLoad(loadAdError);
                                Log.d("Native ADs","failed to load");
                                holder.template.setVisibility(View.INVISIBLE);
                                holder.template.setVisibility(View.GONE);
                            }

                        }).build();
                anss.add(position, "");
                attemptness.add(position, "");
                adLoader.loadAd(new AdRequest.Builder().build());

                Log.d("Native Banner Ads","attempting to load native ads");

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
        else if (aryList.get(position).getQuizId().equals("ad")){
            return 2;
        }
        else
            return 1;

    }
}
