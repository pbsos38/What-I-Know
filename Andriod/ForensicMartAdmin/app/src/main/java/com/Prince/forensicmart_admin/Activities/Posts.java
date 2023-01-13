package com.Prince.forensicmart_admin.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.Prince.forensicmart_admin.Adapter.PostsAdpater;
import com.Prince.forensicmart_admin.Bean.Posts_beans;
import com.Prince.forensicmart_admin.R;
import com.Prince.forensicmart_admin.SmallFeatures.myString;
import com.Prince.forensicmart_admin.server.Conn;
import com.Prince.forensicmart_admin.server.SetDataToDatabase;
import com.google.android.material.appbar.MaterialToolbar;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class Posts extends AppCompatActivity {
    RecyclerView recyclerView;
    PostsAdpater adapter;
    LinearLayoutManager linearManager;
    myString myString;
    String website;
    private MaterialToolbar materialToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posts);
        materialToolbar = findViewById(R.id.topAppBar);
        myString = new myString(this);
        recyclerView = findViewById(R.id.recycler_dashboard);
        linearManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearManager);
        website= getIntent().getStringExtra("website");
        fetchPost();

        materialToolbar.setNavigationOnClickListener(v->{super.onBackPressed();});

    }

    public void fetchPost() {
        SetDataToDatabase con = Conn.doConnect();
        con.fetchPosts(website,new Callback<Posts_beans>(){
            @Override
            public void success(Posts_beans posts_beans, Response response) {
                if (posts_beans.data.size()==0) posts_beans.data.add(0,new Posts_beans.UserBean("no","no","no","no"));
                adapter = new PostsAdpater(posts_beans.data, Posts.this);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void failure(RetrofitError error) {
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