package com.forensic.mart.ChatSystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.SearchView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.forensic.mart.ChatSystem.Adapter.AllUesrs_setContent;
import com.forensic.mart.ChatSystem.BeanFiles.Bean_allUsers;
import com.forensic.mart.R;
import com.forensic.mart.SmallFeatures.ViewDialog;

import java.util.ArrayList;
import java.util.List;

public class ChatList extends AppCompatActivity {
    RecyclerView recyclerView;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference ref_read_all_clients;
    List<Bean_allUsers> mClients;
    AllUesrs_setContent allUesrs_setContent;
    LinearLayoutManager mLinearLayoutManager;
    ViewDialog viewDialog;
    SearchView searchView;
    private String userName,userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_list);
        //get intents
        {
            userID = getIntent().getStringExtra("userID");
            userName = getIntent().getStringExtra("userName");
        }
        firebaseDatabase = FirebaseDatabase.getInstance();

        //myRef.keepSynced(true);
        //this will enable offline support of Firebase database and will load data faster
        searchView = findViewById(R.id.search_Box_home_page_chats);
        //Recycler View
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView_Home_Page_Chats);

        mLinearLayoutManager = new LinearLayoutManager(ChatList.this);
        //mLinearLayoutManager.setStackFromEnd(true);

        recyclerView.setLayoutManager(mLinearLayoutManager);

        viewDialog = new ViewDialog(this);
        read_all_clients();

        //search
        {
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    allUesrs_setContent.getFilter().filter(newText);
                    return false;
                }
            });
        }
    }

    private void read_all_clients() {
        viewDialog.showDialog();
        mClients = new ArrayList<>();
        ref_read_all_clients = FirebaseDatabase.getInstance().getReference("users").child(userID);
        ref_read_all_clients.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mClients.clear();
                String if_unread_msg = null;
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Log.d("off", snapshot.getValue().toString());
                    Log.d("off", snapshot.getKey());
                    //push_id = snapshot.getKey();
                    //if_unread_msg = check_unread_msg(snapshot.getKey());
                    //Log.d("papapapa", if_unread_msg + "=" + snapshot.getKey());

                    Bean_allUsers bean_allUsers = snapshot.getValue(Bean_allUsers.class);
                    assert bean_allUsers != null;
                    mClients.add(bean_allUsers);
                }
                allUesrs_setContent = new AllUesrs_setContent(ChatList.this, mClients, null,userID,userName);
                recyclerView.setAdapter(allUesrs_setContent);
                viewDialog.hideDialog();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}