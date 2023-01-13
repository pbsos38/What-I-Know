package com.forensic.mart.Activities.menuActivities.menu_adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.forensic.mart.Activities.menuActivities.Services;
import com.forensic.mart.R;

public class ServicesAdapter extends RecyclerView.Adapter<ServicesAdapter.InnerClassViewHolder> {
   String[] service;
    static Context ctx;
    public ServicesAdapter(String[] services, Services ct) {
    this.service = services;
        ctx=ct;
    }

    @NonNull
    @Override
    public InnerClassViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(ctx);
        View view= inflater.inflate(R.layout.show_serrvice_item,parent,false);
        ServicesAdapter.InnerClassViewHolder itemView=new ServicesAdapter.InnerClassViewHolder(view);
        return itemView;
    }

    @Override
    public void onBindViewHolder(@NonNull InnerClassViewHolder holder, int position) {
        holder.serviceText.setText(service[position]);
    }

    @Override
    public int getItemCount() {
        return service.length;
    }

    public class InnerClassViewHolder extends RecyclerView.ViewHolder {
       TextView serviceText;
        public InnerClassViewHolder(@NonNull View itemView) {
            super(itemView);
            serviceText = itemView.findViewById(R.id.justText_services);
        }
    }
}
