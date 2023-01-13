package com.forensic.mart.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.forensic.mart.Activities.UserProfile;
import com.forensic.mart.BeanFiles.Employments_bean;
import com.forensic.mart.R;

import java.util.ArrayList;

public class Employement_adapter extends RecyclerView.Adapter<Employement_adapter.InnerClassViewHolder> {

    ArrayList<Employments_bean.UserBean> aryList;
    static Context ctx;
    String type;
    public Employement_adapter(ArrayList<Employments_bean.UserBean> data, UserProfile dashboard) {
        aryList = data;
        ctx = dashboard;
    }

    @NonNull
    @Override
    public Employement_adapter.InnerClassViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(ctx);
        View view = inflater.inflate(R.layout.user_profile_skill, parent, false);
        return new InnerClassViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Employement_adapter.InnerClassViewHolder holder, int position) {
        Employments_bean.UserBean user = aryList.get(position);
        holder.level.setText(user.getTitle());
        holder.dates.setText(user.getFromDate() + " To " + user.getToDate() );
        holder.other.setText(user.getDetails());
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
