package com.Prince.forensicmart_admin.Adapter;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.Prince.forensicmart_admin.Activities.Dashboard;
import com.Prince.forensicmart_admin.Activities.OpenWeb;
import com.Prince.forensicmart_admin.Activities.Posts;
import com.Prince.forensicmart_admin.Bean.Posts_beans;
import com.Prince.forensicmart_admin.R;
import com.google.android.material.textfield.TextInputEditText;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PostsAdpater extends RecyclerView.Adapter<PostsAdpater.InnerViewClassHolder> {
    ArrayList<Posts_beans.UserBean> aryList;
    static Context ctx;

    public PostsAdpater(ArrayList<Posts_beans.UserBean> data, Posts dashboard) {
        aryList = data;
        ctx = dashboard;
    }

    @NonNull
    @Override
    public PostsAdpater.InnerViewClassHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == -1) {
            LayoutInflater inflater = LayoutInflater.from(ctx);
            View view = inflater.inflate(R.layout.layouy_nodata, parent, false);
            //RecyclerViewHolder holder=new RecyclerViewHolder(view);
            return new InnerViewClassHolder(view);
        } else {
            LayoutInflater inflater = LayoutInflater.from(ctx);
            View view = inflater.inflate(R.layout.layout_post, parent, false);
            //RecyclerViewHolder holder=new RecyclerViewHolder(view);
            return new InnerViewClassHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull InnerViewClassHolder holder, int position) {
        Posts_beans.UserBean user = aryList.get(position);
        if (!user.getWebsite().equals("no")) {
            holder.name.setText(user.getPostname());
            try {
                Picasso.with(ctx).load("https://www.forensicmart.com/Android/PostPics/" + user.getImage())
                        .fit()
                        .error(R.drawable.noimage).into(holder.image);
            } catch (Exception ignored) {

            }
            holder.visit.setOnClickListener(v -> {
                try {
                    Uri uriUrl = Uri.parse(user.getPostlink());
                    Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
                    ctx.startActivity(launchBrowser);
                } catch (Exception e) {
                    Intent intent = new Intent(ctx, OpenWeb.class);
                    intent.putExtra("link", user.getPostlink());
                    ctx.startActivity(intent);
                }
            });
        }
/*
        holder.copylink.setOnClickListener(v->{
            ClipData myClip;
            ClipboardManager clipboard = (ClipboardManager) ctx.getSystemService(Context.CLIPBOARD_SERVICE);
            myClip = ClipData.newPlainText("text", user.getPostlink());
            assert clipboard != null;
            clipboard.setPrimaryClip(myClip);
            Toast.makeText(ctx, "Link Copied", Toast.LENGTH_SHORT).show();
        });
*/

    }

    @Override
    public int getItemCount() {
        return aryList.size();
    }

    public static class InnerViewClassHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView name;
        Button visit;

        /*,copylink;*/
        public InnerViewClassHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.addImage);
            name = itemView.findViewById(R.id.postName);
            visit = itemView.findViewById(R.id.visitbtn);
            //copylink = itemView.findViewById(R.id.copylink);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (aryList.size() == 1 && aryList.get(position).getImage().equals("no"))
            return -1;
        else
            return position;
    }
}
