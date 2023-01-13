package com.Prince.forensicexams;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.Prince.forensicexams.Adapter.CompPostAdapter;
import com.Prince.forensicexams.BeanFiles.CompPosts_bean;
import com.Prince.forensicexams.SmallFeatures.ViewDialog;
import com.Prince.forensicexams.server.Conn;
import com.Prince.forensicexams.server.ServerResponse;
import com.Prince.forensicexams.server.SetDataToDatabase;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.mime.TypedFile;

public class CompetitionPosts extends AppCompatActivity {

    private ViewDialog viewDialog;
    private RecyclerView recyclerView;
    private CompPostAdapter adapter;

    private String myUrl;
    private String from;
    private TextInputLayout postlink_lay;
    private TextInputEditText postlink;


    ArrayList<CompPosts_bean.UserBean> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_competition_posts);
        viewDialog = new ViewDialog(this);
        recyclerView = findViewById(R.id.recycler_dashboard);
        LinearLayoutManager linearManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearManager);

        postlink = findViewById(R.id.postLink);
        postlink_lay = findViewById(R.id.postLink_lay);

        from = getIntent().getStringExtra("from");
        if (getIntent().getStringExtra("from").equals("ma")) {
            fetchpost("ma");
        } else if (getIntent().getStringExtra("from").equals("cp")) {
            String myFilePath = getIntent().getStringExtra("file");
            myUrl = getIntent().getStringExtra("url");
            //data.add(0,new CompPosts_bean.UserBean(0,myFilePath,myUrl,""));
            fetchpost("cp");

        }

    }

    public void post(View view) {
        /*bottom.setVisibility(View.VISIBLE);
        top.setVisibility(View.GONE);
        top.setVisibility(View.INVISIBLE);*/

        startActivity(new Intent(CompetitionPosts.this, CreatePost.class));
    }

    private void fetchpost(String s) {

        viewDialog.showDialog();
        SetDataToDatabase con = Conn.doConnect();
        con.fPosts(new Callback<CompPosts_bean>() {
            @Override
            public void success(CompPosts_bean compPosts_bean, Response response) {
                viewDialog.hideDialog();
                data = compPosts_bean.data;
                if (s.equals("cp")) {
                    // data.add(0,new CompPosts_bean.UserBean(0,myFilePath,myUrl,"rt"));
                    adapter = new CompPostAdapter(data, CompetitionPosts.this);
                    recyclerView.setAdapter(adapter);
                } else {
                    adapter = new CompPostAdapter(data, CompetitionPosts.this);
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void failure(RetrofitError error) {
                viewDialog.hideDialog();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("TAg","Destroyed");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("TAg","stoped");

    }




}