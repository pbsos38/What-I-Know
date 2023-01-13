package com.association.mansabar.adapter;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.association.mansabar.R;
import com.association.mansabar.admin.Admin_Advocates;
import com.association.mansabar.model.Advocate;
import com.bumptech.glide.Glide;

import java.util.List;

public class Adapter_Advocates extends RecyclerView.Adapter<Adapter_Advocates.ViewHolder> {
    private Context mcontxet;
    private List<Advocate> lAdvocate;

    ImageView advocatePic;
    TextView advocateName, advocateChamber, advocateEnroll, advocateMobile;

//    DatabaseReference ref_read_all_msg;

    public Adapter_Advocates(@NonNull Context context, List<Advocate> lAdvocate) {
        this.mcontxet = context;
        this.lAdvocate = lAdvocate;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mcontxet).inflate(R.layout.view_advocate, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final Advocate item = lAdvocate.get(position);
        advocateName.setText("Name: "+item.getName());
        advocateChamber.setText("Chamber Number: "+item.getChamberNo());
        advocateEnroll.setText("Enroll No: "+item.getEnrollNo());
        advocateMobile.setText("Mobile No: "+ item.getMobile());
        Glide.with(mcontxet).load(item.getPicPath().equals("null")?R.drawable.ic_user_foreground:item.getPicPath()).into(advocatePic);


    }

    @Override
    public int getItemCount() {
        return lAdvocate.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            advocatePic = itemView.findViewById(R.id.view_advocate__advocatePic);
            advocateName = itemView.findViewById(R.id.view_advocate__advocateName);
            advocateChamber = itemView.findViewById(R.id.view_advocate__advocateChamber);
            advocateEnroll = itemView.findViewById(R.id.view_advocate__advocateEnroll);
            advocateMobile = itemView.findViewById(R.id.view_advocate__advocateMobile);

        }
    }

}
