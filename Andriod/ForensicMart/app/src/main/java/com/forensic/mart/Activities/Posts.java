package com.forensic.mart.Activities;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.forensic.mart.Adapter.PostsAdpater;
import com.forensic.mart.BeanFiles.Posts_beans;
import com.forensic.mart.R;
import com.forensic.mart.SmallFeatures.ViewDialog;
import com.forensic.mart.server.Conn;
import com.forensic.mart.server.SetDataToDatabase;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import com.forensic.mart.SmallFeatures.myString;

public class Posts extends AppCompatActivity {
    RecyclerView recyclerView;
    PostsAdpater adapter;
    LinearLayoutManager linearManager;
    myString myString;
    String website;
    ViewDialog viewDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posts);
        myString = new myString(this);
        recyclerView = findViewById(R.id.recycler_dashboard);
        linearManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearManager);
        website= getIntent().getStringExtra("website");
        viewDialog = new ViewDialog(this);
        fetchPost();

    }

    public void fetchPost() {
        viewDialog.showDialog_cancelable();
        SetDataToDatabase con = Conn.doConnect();
        con.fetchPosts(website,new Callback<Posts_beans>(){
            @Override
            public void success(Posts_beans posts_beans, Response response) {
                adapter = new PostsAdpater(posts_beans.data, Posts.this);
                recyclerView.setAdapter(adapter);
                viewDialog.hideDialog();
            }

            @Override
            public void failure(RetrofitError error) {
                viewDialog.hideDialog();
                if(error.getKind().equals(RetrofitError.Kind.NETWORK))
                {
                    Toast.makeText(Posts.this, "No Internet connection found.", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(Posts.this, error.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}