package com.Prince.forensicmart_admin.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.Prince.forensicmart_admin.Adapter.VideoAdapter;
import com.Prince.forensicmart_admin.Bean.VideoBean;
import com.Prince.forensicmart_admin.R;
import com.Prince.forensicmart_admin.server.Conn;
import com.Prince.forensicmart_admin.server.SetDataToDatabase;
import com.google.android.material.appbar.MaterialToolbar;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class Videos extends AppCompatActivity {

    RecyclerView recyclerView;
    VideoAdapter adapter;
    LinearLayoutManager linearManager;
    private MaterialToolbar materialToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videos);
        materialToolbar = findViewById((R.id.topAppBar));
        recyclerView = findViewById(R.id.video_recycler);
        linearManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearManager);
        fetchPost();

        materialToolbar.setNavigationOnClickListener(v->{super.onBackPressed();});
    }

    private void fetchPost() {
        SetDataToDatabase con = Conn.doConnect();
        con.fetchVideo(new Callback<VideoBean>() {
            @Override
            public void success(VideoBean videoBean, Response response) { ;
                adapter = new VideoAdapter(videoBean.data, Videos.this);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void failure(RetrofitError error) {
                if (error.getKind().equals(RetrofitError.Kind.NETWORK)) {
                    Toast.makeText(Videos.this, "No Internet connection found.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Videos.this, error.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}