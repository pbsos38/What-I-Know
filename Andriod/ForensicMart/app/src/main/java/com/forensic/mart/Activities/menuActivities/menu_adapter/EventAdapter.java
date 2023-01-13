package com.forensic.mart.Activities.menuActivities.menu_adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.forensic.mart.Activities.menuActivities.events;
import com.forensic.mart.R;
import com.squareup.picasso.Picasso;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.InnerClassViewHolder> {
    private int[] imagess;
    static Context ctx;
    public EventAdapter(int[] event, events events) {
        this.imagess=event;
        this.ctx = events;
    }

    @NonNull
    @Override
    public InnerClassViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(ctx);
        View view= inflater.inflate(R.layout.single_event_item,parent,false);
        EventAdapter.InnerClassViewHolder itemView=new EventAdapter.InnerClassViewHolder(view);

        return itemView;
    }

    @Override
    public void onBindViewHolder(@NonNull InnerClassViewHolder holder, int position) {
        holder.imageView.setImageResource(imagess[position]);
        Picasso.with(ctx).load(imagess[position]);

    }

    @Override
    public int getItemCount() {
        return imagess.length;
    }

    public class InnerClassViewHolder extends RecyclerView.ViewHolder {
       ImageView imageView;
        public InnerClassViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_single__event_item);
        }
    }
}
