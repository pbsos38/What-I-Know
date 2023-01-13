package com.forensic.mart.Activities.menuActivities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.forensic.mart.R;
import com.forensic.mart.Activities.menuActivities.menu_adapter.EventAdapter;

public class events extends AppCompatActivity {
    private int[] event ={R.drawable.eventone,R.drawable.eventtwo,R.drawable.eventthree,R.drawable.eventfour};
    EventAdapter adapter;
    RecyclerView recycler;
    LinearLayoutManager linearManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

        recycler=(RecyclerView)findViewById(R.id.recycler_events);
        linearManager=new LinearLayoutManager(this);
        recycler.setLayoutManager(linearManager);

        adapter=new EventAdapter(event,events.this);
        recycler.setAdapter(adapter);

    }
}