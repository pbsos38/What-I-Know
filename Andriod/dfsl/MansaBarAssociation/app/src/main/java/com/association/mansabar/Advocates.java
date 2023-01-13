package com.association.mansabar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.association.mansabar.adapter.Adapter_Advocates;
import com.association.mansabar.model.Advocate;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.Collectors;

public class Advocates extends AppCompatActivity {

    RecyclerView recyclerView;
    Adapter_Advocates adapter_advocates;
    String push_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advocates);

        {
            recyclerView = findViewById(R.id.advocatesList);
            recyclerView.setHasFixedSize(true);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
            linearLayoutManager.setStackFromEnd(true);
            recyclerView.setLayoutManager(linearLayoutManager);
        }
        List<Advocate> list = new ArrayList<>();
        DatabaseReference ref_read_all_messages = FirebaseDatabase.getInstance().getReference().child("Advocates");
//        list.add(new Advocate("Name","Enroll No","picname.jpg","mobile","chamberno"));

        ref_read_all_messages.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    push_id = snapshot.getKey();
                    Advocate advocate = snapshot.getValue(Advocate.class);
                    assert advocate != null;
                    list.add(advocate);
                }
                List<Advocate> newList;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                    newList =  list.stream().sorted(Comparator.comparing(Advocate::getName)).collect(Collectors.toList());
                }else{
                    newList = list;
                }
                adapter_advocates = new Adapter_Advocates(Advocates.this, list);
                recyclerView.setAdapter(adapter_advocates);
                recyclerView.scrollToPosition(0);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }
}