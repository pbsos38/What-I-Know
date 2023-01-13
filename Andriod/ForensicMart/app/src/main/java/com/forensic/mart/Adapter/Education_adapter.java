package com.forensic.mart.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.forensic.mart.Activities.UserProfile;
import com.forensic.mart.BeanFiles.Education_bean;
import com.forensic.mart.R;

import java.util.ArrayList;

public class Education_adapter extends RecyclerView.Adapter<Education_adapter.InnerClassViewHolder> {
    ArrayList<Education_bean.UserBean> aryList;
    static Context ctx;

    public Education_adapter(ArrayList<Education_bean.UserBean> data, UserProfile dashboard) {
        aryList = data;
        ctx = dashboard;
    }

    @NonNull
    @Override
    public Education_adapter.InnerClassViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(ctx);
        View view = inflater.inflate(R.layout.user_profile_skill, parent, false);
        return new Education_adapter.InnerClassViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Education_adapter.InnerClassViewHolder holder, int position) {
        Education_bean.UserBean user = aryList.get(position);
        holder.level.setText(user.getDegree());
        holder.dates.setText(user.getFromDate() + " To " + user.getToDate() );
        holder.other.setText(user.getSchool());

    }

    @Override
    public int getItemCount() {
        return aryList.size();
    }

    public class InnerClassViewHolder extends RecyclerView.ViewHolder {
        TextView level,other,dates;

        public InnerClassViewHolder(@NonNull View itemView) {
            super(itemView);

            level = itemView.findViewById(R.id.level_user_profile_skill);
            other = itemView.findViewById(R.id.other_user_profile_skill);
            dates = itemView.findViewById(R.id.start_end_user_profile_skill);
        }
    }
}
