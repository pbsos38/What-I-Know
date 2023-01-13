package com.forensic.mart.Activities.Fr;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.forensic.mart.Adapter.WebPostAdapter;
import com.forensic.mart.BeanFiles.WebPost_bean;
import com.forensic.mart.R;
import com.forensic.mart.SmallFeatures.ViewDialog;
import com.forensic.mart.server.Conn;
import com.forensic.mart.server.SetDataToDatabase;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class Events extends Fragment {
    RecyclerView recyclerView;
    WebPostAdapter adapter;
    LinearLayoutManager linearManager;
    private String userID,userName;
    int minID;
    private ProgressBar progressBar;
    ViewDialog viewDialog;
    ArrayList<WebPost_bean.UserBean> userBeans = new ArrayList<>();
    private boolean waiting =false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_events, container, false);
        progressBar = v.findViewById(R.id.events_progressBar);
        recyclerView = v.findViewById(R.id.recycler_events);
        linearManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearManager);
        viewDialog = new ViewDialog(getActivity());

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NotNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (!recyclerView.canScrollVertically(1) && newState==RecyclerView.SCROLL_STATE_IDLE) {
                    Log.d("-----","end of events"+minID);
                    progressBar.setVisibility(View.VISIBLE);
                    if (!waiting) {
                        waiting =true;
                        LoadMore(minID);
                    }
                }

            }
        });

        fetchPost();
        // getting intents/ arguments
        {
            assert getArguments() != null;
            userID = getArguments().getString("userID");
            userName = getArguments().getString("userName");
        }
        return v;
    }

    private void fetchPost(){
        viewDialog.showDialog_cancelable();
        SetDataToDatabase con = Conn.doConnect();
        con.loadWEbPosts_specific("start_others","1", new Callback<WebPost_bean>() {
            @Override
            public void success(WebPost_bean webPost_bean, Response response) {
                /*
                userBeans.clear();*/
                userBeans = webPost_bean.data;
                viewDialog.hideDialog();
                int x=0;
                // to get least post id and this will be used for requesting new posts starting from this minId;
                while (x<userBeans.size()){minID=userBeans.get(x).getId();x++; }
                adapter = new WebPostAdapter(userBeans,getActivity(),userID,userName);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void failure(RetrofitError error) {
                if(error.getKind().equals(RetrofitError.Kind.NETWORK))
                    Toast.makeText(getActivity(), "No Internet connection found.", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getContext(), error.toString(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void LoadMore(int minID_r){
        SetDataToDatabase con = Conn.doConnect();
        con.loadWEbPosts_specific_more("more_others","1", minID_r, new Callback<WebPost_bean>() {
            @Override
            public void success(WebPost_bean webPost_bean, Response response) {
                progressBar.setVisibility(View.INVISIBLE);
                progressBar.setVisibility(View.GONE);
                userBeans.addAll(webPost_bean.data);
                waiting = false;
                int x=0;
                while (x<userBeans.size()){minID=userBeans.get(x).getId();x++;}
            }

            @Override
            public void failure(RetrofitError error) {
                if(error.getKind().equals(RetrofitError.Kind.NETWORK))
                {
                    Toast.makeText(getActivity(), "No Internet connection found.", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getContext(), error.toString(),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}