package com.example.searching.server;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.searching.R;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class server_searching extends AppCompatActivity {
    RecyclerView recycler;
    LinearLayoutManager linearManager;
    owner_requests_setContent adapter;
    TextView rname,fname,gst,city;
    String Status,retailer_status,owner_authority;
    TextInputEditText search_bar;
    SearchView searchView;

    List<bean_fetch_Requests> aryList_search;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_server_searching);

        search_bar = (TextInputEditText) findViewById(R.id.search_box);
        searchView = findViewById(R.id.search_bbox);
        Status = getIntent().getStringExtra("status");
        recycler = (RecyclerView) findViewById(R.id.recyclerView_owner_request_result);
        linearManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(linearManager);
        retailer_status = getIntent().getStringExtra("retailer_status");
        owner_authority = getIntent().getStringExtra("owner_authority");
        fetchAll();

        search_bar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                adapter.getFilter().filter(s.toString());
            }
        });


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
    }
    void fetchAll()
    {
        setDataToDatabase api= ServerConnection.doConnect();
        api.fetchAllRequests("1",new Callback<bean_fetch_Requests>() {
            @Override
            public void success(bean_fetch_Requests listResponseModel, Response response) {
                //bas ye hi change karna hai
                adapter=new owner_requests_setContent(listResponseModel.data,server_searching.this,retailer_status,owner_authority);
                recycler.setAdapter(adapter);
                //progress.dismiss();
                //Toast.makeText(owner_request_result.this, "Congrats", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }



}