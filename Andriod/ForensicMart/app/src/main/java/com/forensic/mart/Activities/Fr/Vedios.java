package com.forensic.mart.Activities.Fr;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.forensic.mart.Adapter.VideoAdapter;
import com.forensic.mart.BeanFiles.VideoBean;
import com.forensic.mart.R;
import com.forensic.mart.server.Conn;
import com.forensic.mart.server.SetDataToDatabase;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class Vedios extends Fragment {
    RecyclerView recyclerView;
    VideoAdapter adapter;
    LinearLayoutManager linearManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_vedios, container, false);
        recyclerView = v.findViewById(R.id.video_recycler);
        linearManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearManager);
        fetchPost();

        return v;
    }

    private void fetchPost() {
        SetDataToDatabase con = Conn.doConnect();
        con.fetchVideo(new Callback<VideoBean>() {
            @Override
            public void success(VideoBean videoBean, Response response) { ;
                adapter = new VideoAdapter(videoBean.data, getActivity());
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void failure(RetrofitError error) {
                if (error.getKind().equals(RetrofitError.Kind.NETWORK)) {
                    Toast.makeText(getActivity(), "No Internet connection found.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), error.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}