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

public class Home extends Fragment {

    RecyclerView recyclerView;
    WebPostAdapter adapter;
    LinearLayoutManager linearManager;
    ViewDialog viewDialog;
    ArrayList<WebPost_bean.UserBean> userBeans = new ArrayList<>();
    int minID;
    private ProgressBar progressBar;
    private String userID,userName;
    private boolean waiting =false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = view.findViewById(R.id.recycler_home);

        progressBar = view.findViewById(R.id.home_progressBar);
        viewDialog = new ViewDialog(getActivity());
        linearManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearManager);
        fetchPost();

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NotNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (!recyclerView.canScrollVertically(1) && newState==RecyclerView.SCROLL_STATE_IDLE) {
                    Log.d("-----","end"+minID);
                    progressBar.setVisibility(View.VISIBLE);
                    if (!waiting) {
                        waiting =true;
                        LoadMore(minID);
                    }
                }
            }
        });
        // getting intents/ arguments
        {
            assert getArguments() != null;
            userID = getArguments().getString("userID");
            userName = getArguments().getString("userName");
        }

        return view;
    }

    private void fetchPost(){
        viewDialog.showDialog_cancelable();
        SetDataToDatabase con = Conn.doConnect();
        con.loadWEbPosts("start_home", new Callback<WebPost_bean>() {
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
    con.loadWEbPosts_more("more_home", minID_r, new Callback<WebPost_bean>() {
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