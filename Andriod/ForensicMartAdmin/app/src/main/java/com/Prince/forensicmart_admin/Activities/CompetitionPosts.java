package com.Prince.forensicmart_admin.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Process;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.Prince.forensicmart_admin.Adapter.CompetitionAdapter;
import com.Prince.forensicmart_admin.Adapter.CourseAdapter;
import com.Prince.forensicmart_admin.Adapter.PostsAdpater;
import com.Prince.forensicmart_admin.Bean.Competition_bean;
import com.Prince.forensicmart_admin.Bean.Posts_beans;
import com.Prince.forensicmart_admin.R;
import com.Prince.forensicmart_admin.SmallFeatures.ViewDialog;
import com.Prince.forensicmart_admin.server.Conn;
import com.Prince.forensicmart_admin.server.Connection;
import com.Prince.forensicmart_admin.server.SetDataToDatabase;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.snackbar.Snackbar;

import java.util.Objects;
import java.util.Set;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class CompetitionPosts extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CompetitionAdapter adapter;
    ViewDialog progressBar;
    private MaterialToolbar materialToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_competition_posts);
        recyclerView = findViewById(R.id.recycler_dashboard);
        LinearLayoutManager linearManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearManager);
        progressBar = new ViewDialog(this);
        materialToolbar = findViewById(R.id.topAppBar);
        fetchData();

        materialToolbar.setNavigationOnClickListener(v->{super.onBackPressed();});

    }


    private void fetchData(){
        progressBar.showSnackBar();
        SetDataToDatabase con = Conn.doConnect();
        con.Competition_list("getComp", "", new Callback<Competition_bean>() {
            @Override
            public void success(Competition_bean competition_bean, Response response) {
                progressBar.hideSnackBar();
                if (competition_bean.data.size()==0) competition_bean.data.add(0,new Competition_bean.UserBean("no","no","no","no","","","","","","",""));
                adapter = new CompetitionAdapter(competition_bean.data, CompetitionPosts.this);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void failure(RetrofitError error) {
                progressBar.hideSnackBar();
                if (error.getKind().equals(RetrofitError.Kind.NETWORK))
                    Snackbar.make(findViewById(android.R.id.content), "No Internet Connection!\nPlease try again later.", Snackbar.LENGTH_LONG).show();
                else
                    Snackbar.make(findViewById(android.R.id.content), error.toString() + "", Snackbar.LENGTH_LONG).show();

            }
        });
    }

    public void AddCompetition(View view) {
        Intent intent = new Intent(CompetitionPosts.this,ManageCompetitions.class);
        intent.putExtra("path","add");
        startActivity(intent);
    }
}