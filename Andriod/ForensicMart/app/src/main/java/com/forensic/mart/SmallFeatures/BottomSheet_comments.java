package com.forensic.mart.SmallFeatures;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.forensic.mart.Adapter.CommentsAdapter;
import com.forensic.mart.BeanFiles.Comments_bean;
import com.forensic.mart.R;
import com.forensic.mart.server.Conn;
import com.forensic.mart.server.ServerResponse;
import com.forensic.mart.server.SetDataToDatabase;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class BottomSheet_comments extends BottomSheetDialogFragment {
    RecyclerView recyclerView;
    CommentsAdapter adapter;
    LinearLayoutManager linearManager;
    Activity activityy;
    EditText comment_editText;
    ImageView send;
    private String userID;
    private int postID;

    public BottomSheet_comments(Activity activity, String USerID, int POSTID) {
        this.activityy = activity;
        this.userID = USerID;
        this.postID = POSTID;

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.bottomsheet_comments, container, false);
        recyclerView = v.findViewById(R.id.recycler_comments_bottomsheet_comments);
        linearManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearManager);
        send = v.findViewById(R.id.btn_send__bottomsheet_comments);
        comment_editText = v.findViewById(R.id.type_comment_bottomsheet_comments);
        comment_editText.requestFocus();
        fetch();
        send.setOnClickListener(v1 -> {
            postComment(userID, postID);
        });
        return v;
    }

    private void fetch() {
        SetDataToDatabase con = Conn.doConnect();
        con.fetchComments("load_com", String.valueOf(postID), new Callback<Comments_bean>() {
            @Override
            public void success(Comments_bean comments_bean, Response response) {
                adapter = new CommentsAdapter(comments_bean.data, getContext());
                if (comments_bean.data.size()==0){
                    comments_bean.data.add(0,new Comments_bean.UserBean("-1","-1","-1","Be the first one to comment","-1","-1"));
                }
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void failure(RetrofitError error) {
                if (error.getKind().equals(RetrofitError.Kind.NETWORK))
                    Toast.makeText(getContext(), "No Internet connection found.", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getContext(), error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void postComment(String userID, int postID) {

        SetDataToDatabase con = Conn.doConnect();
        con.addComment("add_com", userID, postID, comment_editText.getText().toString(), new Callback<ServerResponse>() {
            @Override
            public void success(ServerResponse serverResponse, Response response) {
                if (serverResponse.msg.equals("Uploading Completed.")) {
                    Toast.makeText(activityy, "Comment Successfully added", Toast.LENGTH_SHORT).show();
                    fetch();
                } else
                    Toast.makeText(activityy, "Comment Uploading Failed", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void failure(RetrofitError error) {
                if (error.getKind().equals(RetrofitError.Kind.NETWORK))
                    Toast.makeText(getContext(), "No Internet connection found.", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getContext(), error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
