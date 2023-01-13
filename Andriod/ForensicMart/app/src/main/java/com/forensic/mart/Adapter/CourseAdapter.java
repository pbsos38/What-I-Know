package com.forensic.mart.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.forensic.mart.Activities.Courses;
import com.forensic.mart.BeanFiles.Courses_bean;
import com.forensic.mart.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.InnerViewClassHolder> {
    ArrayList<Courses_bean.UserBean> aryList;
    static Context ctx;
    String card = "unexpanded";
    public CourseAdapter(ArrayList<Courses_bean.UserBean> data, Courses courses) {
        aryList = data;
        ctx = courses;
    }

    @NonNull
    @Override
    public InnerViewClassHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(ctx);
        View view = inflater.inflate(R.layout.layout_courses, parent, false);
        //RecyclerViewHolder holder=new RecyclerViewHolder(view);
        CourseAdapter.InnerViewClassHolder itemView = new CourseAdapter.InnerViewClassHolder(view);
        return itemView;
    }

    @Override
    public void onBindViewHolder(@NonNull InnerViewClassHolder holder, int position) {
        Courses_bean.UserBean user = aryList.get(position);
        holder.cName.setText(user.getName());
        Picasso.with(ctx)
                .load("https://www.forensicmart.com/Android/coursePics/" + user.getImage())
                .error(R.drawable.noimage)
                .into(holder.courseImage);

        holder.cStatus.setText(user.getStatus());

        AtomicReference<Boolean> length = new AtomicReference<>(true);
        holder.expand.setOnClickListener(v->{
            if (length.get()){
                holder.hiddenLayout.setVisibility(View.VISIBLE);
                //holder.expand.setImageResource(R.drawable.ic_baseline_keyboard_arrow_up_24);
                length.set(false);
            }
            else if (!length.get())
            {
                holder.hiddenLayout.setVisibility(View.INVISIBLE);
                holder.hiddenLayout.setVisibility(View.GONE);
                //holder.expand.setImageResource(R.drawable.ic_baseline_keyboard_arrow_down_24);
                length.set(true);
                //card = "upexpanded";
            }
        });
    }

    @Override
    public int getItemCount() {
        if (aryList == null) {
            return 0;
        } else
            return aryList.size();
    }

    public static class InnerViewClassHolder extends RecyclerView.ViewHolder {

        TextView cName, cStatus;
        ImageView courseImage;
        TextView expand;
        LinearLayout hiddenLayout;

        public InnerViewClassHolder(@NonNull View itemView) {
            super(itemView);

            cName = itemView.findViewById(R.id.course_name);
            cStatus = itemView.findViewById(R.id.course_detail_text);
            courseImage = itemView.findViewById(R.id.course_image);
            expand = itemView.findViewById(R.id.details_courses);
            hiddenLayout = itemView.findViewById(R.id.expand_layout);
        }


    }
}
