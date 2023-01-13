package com.Prince.forensicmart_admin.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.Prince.forensicmart_admin.Activities.CompetitionPosts;
import com.Prince.forensicmart_admin.Activities.ManageCompetitions;
import com.Prince.forensicmart_admin.Bean.Competition_bean;
import com.Prince.forensicmart_admin.R;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;

public class CompetitionAdapter extends RecyclerView.Adapter<CompetitionAdapter.InnerViewClassHolder> {
    ArrayList<Competition_bean.UserBean> aryList;
    Context ctx;

    public CompetitionAdapter(ArrayList<Competition_bean.UserBean> data, CompetitionPosts competitionPosts) {
        this.aryList = data;
        this.ctx = competitionPosts;
    }

    @NonNull
    @Override
    public CompetitionAdapter.InnerViewClassHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == -1) {
            LayoutInflater inflater = LayoutInflater.from(ctx);
            View view = inflater.inflate(R.layout.layouy_nodata, parent, false);
            return new CompetitionAdapter.InnerViewClassHolder(view);
        } else {
            LayoutInflater inflater = LayoutInflater.from(ctx);
            View view = inflater.inflate(R.layout.view_competition, parent, false);
            return new CompetitionAdapter.InnerViewClassHolder(view);
        }
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull CompetitionAdapter.InnerViewClassHolder holder, int position) {
        Competition_bean.UserBean user = aryList.get(position);
        if (!user.getCid().equals("no")) {
            holder.name.setText(user.getName());
            switch (user.getIsLive()) {
                case "-1":
                    holder.status.setText("Closed");
                    break;
                case "0":
                    holder.status.setText("Not Live");
                    break;
                case "1":
                    holder.status.setText("Live");
                    break;
            }

            holder.cardView.setOnClickListener(v->{
                Intent intent = new Intent(ctx,ManageCompetitions.class);
                intent.putExtra("path","compList");
                intent.putExtra("id",user.getCid());
                ctx.startActivity(intent);
            });
        }

        }
    public static class InnerViewClassHolder extends RecyclerView.ViewHolder {

        private TextView name;
        private TextView status;
        private MaterialCardView cardView;
        public InnerViewClassHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.view_competition_name);
            status = itemView.findViewById(R.id.view_competition_status);
            cardView =itemView.findViewById(R.id.view_competition_mainCard);
        }
    }
    @Override
    public int getItemCount() {
        return aryList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (aryList.size() == 1 && aryList.get(position).getCid().equals("no"))
            return -1;
        else
            return position;
    }
}
