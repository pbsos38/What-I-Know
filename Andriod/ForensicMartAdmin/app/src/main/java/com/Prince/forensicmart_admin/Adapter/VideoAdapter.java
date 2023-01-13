package com.Prince.forensicmart_admin.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.Prince.forensicmart_admin.Activities.Videos;
import com.Prince.forensicmart_admin.Bean.VideoBean;
import com.Prince.forensicmart_admin.R;
import com.Prince.forensicmart_admin.SmallFeatures.ViewVideo;
import com.squareup.picasso.Picasso;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.InnerClassViewHolder> {

    ArrayList<VideoBean.UserBean> aryList;
    static Context ctx;
    public VideoAdapter(ArrayList<VideoBean.UserBean> data, Videos activity) {
        aryList = data;
        ctx = activity;
    }

    @NonNull
    @Override
    public VideoAdapter.InnerClassViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(ctx);
        View view = inflater.inflate(R.layout.layout_videopost, parent, false);
        //RecyclerViewHolder holder=new RecyclerViewHolder(view);
        VideoAdapter.InnerClassViewHolder itemView = new InnerClassViewHolder(view);
        return itemView;
    }

    @Override
    public void onBindViewHolder(@NonNull VideoAdapter.InnerClassViewHolder holder, int position) {
        VideoBean.UserBean user = aryList.get(position);

        if (user.getName()!=null)
        {
            holder.name.setText(user.getName());
        }
        else holder.name.setText("Loading..");

        if (user.getLink()!=null){
            try
            {
                String query = new URL(user.getLink()).getQuery();
                String[] param = query.split("&");
                String id = null;
                for (String row : param) {
                    String[] param1 = row.split("=");
                    if (param1[0].equals("v")) {
                        id = param1[1];
                    }
                }
                String img_url="https://img.youtube.com/vi/"+id+"/0.jpg";
                Picasso.with(ctx).load(img_url).error(R.drawable.noimage).into(holder.iv_youtube_thumnail);


            }
            catch (MalformedURLException e)
            {
                e.printStackTrace();
                holder.iv_play.setVisibility(View.INVISIBLE);
                holder.iv_play.setVisibility(View.GONE);
            }
        }

        holder.iv_play.setOnClickListener(v->{
            Intent intent =new Intent(ctx, ViewVideo.class);
            String[] vedioId = user.getLink().split("=");
            String generated = vedioId[1];
            intent.putExtra("url",generated);
            intent.putExtra("fullurl",user.getLink());
            ctx.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return aryList.size();
    }


    public static class InnerClassViewHolder extends RecyclerView.ViewHolder {
    ImageView iv_youtube_thumnail,iv_play;
    TextView name;
        public InnerClassViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.vedioText);
            iv_youtube_thumnail=itemView.findViewById(R.id.img_thumnail);
            iv_play=itemView.findViewById(R.id.iv_play_pause);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);

    }
}
