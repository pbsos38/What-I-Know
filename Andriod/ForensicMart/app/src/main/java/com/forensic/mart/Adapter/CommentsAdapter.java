package com.forensic.mart.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.forensic.mart.BeanFiles.Comments_bean;
import com.forensic.mart.BeanFiles.ProfileDataPostBean;
import com.forensic.mart.R;
import com.forensic.mart.server.Conn;
import com.forensic.mart.server.SetDataToDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.InnerViewClassHolder> {
    ArrayList<Comments_bean.UserBean> aryList;
    Context ctx;

    public CommentsAdapter(ArrayList<Comments_bean.UserBean> data, Context bottomSheet_comments) {
        aryList = data;
        ctx = bottomSheet_comments;

    }

    @NonNull
    @Override
    public CommentsAdapter.InnerViewClassHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(ctx);
        View view = inflater.inflate(R.layout.comment_view, parent, false);
        return new InnerViewClassHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull CommentsAdapter.InnerViewClassHolder holder, int position) {


        Comments_bean.UserBean user = aryList.get(position);
        holder.displayComment.setText(user.getMessage());

        if (!user.getId().equals("-1")) {// loading username for comment
            SetDataToDatabase con = Conn.doConnect();
            con.profile_data_posts("post_data", user.getUser_id(), new Callback<ProfileDataPostBean>() {
                @Override
                public void success(ProfileDataPostBean profileDataPostBean, Response response) {
                    holder.commentersName.setText(profileDataPostBean.data.get(0).getName());
                    try {
                        Picasso.with(ctx).load("https://www.forensicmart.com/uploads/" + profileDataPostBean.data.get(0).getPicture())
                                .into(holder.commentersImage);
                    } catch (Exception e) {
                        holder.commentersImage.setVisibility(View.INVISIBLE);
                        holder.commentersImage.setVisibility(View.GONE);
                    }
                }

                @Override
                public void failure(RetrofitError error) {
                }
            });
        } else {
            holder.commentersImage.setVisibility(View.INVISIBLE);
            holder.commentersImage.setVisibility(View.GONE);
            holder.commentersName.setVisibility(View.INVISIBLE);
            holder.commentersName.setVisibility(View.GONE);
            holder.Separator.setVisibility(View.INVISIBLE);
            holder.Separator.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        if (aryList.size() == 0)
            return 1;
        else
            return aryList.size();
    }

    public static class InnerViewClassHolder extends RecyclerView.ViewHolder {

        TextView commentersName, displayComment;
        ImageView commentersImage;
        View Separator;

        public InnerViewClassHolder(@NonNull View itemView) {
            super(itemView);
            commentersName = itemView.findViewById(R.id.commentersName_Comment_view);
            displayComment = itemView.findViewById(R.id.displayComment_Comment_view);
            commentersImage = itemView.findViewById(R.id.commentersImage_comment_view);
            Separator = itemView.findViewById(R.id.separator);
        }
    }


}
