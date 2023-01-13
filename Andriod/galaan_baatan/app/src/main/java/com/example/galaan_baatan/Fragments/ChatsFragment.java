package com.example.galaan_baatan.Fragments;

import android.os.Bundle;

import androidx.annotation.LongDef;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.galaan_baatan.BeanFiles.User_bean;
import com.example.galaan_baatan.R;
import com.example.galaan_baatan.Adapter.AllUser_SetContent;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import com.example.galaan_baatan.BeanFiles.Messages_bean;
public class ChatsFragment extends Fragment {

    private RecyclerView recyclerView;
    private AllUser_SetContent allUser_setContent;
    private List<User_bean> mUsers;
    FirebaseUser fUser;
    DatabaseReference reference;
    private List<String> userlist;

@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chats, container, false);
        recyclerView = view.findViewById(R.id.recycler_view_all_chats);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        userlist = new ArrayList<>();
        reference = FirebaseDatabase.getInstance().getReference("Chats");

        reference.addValueEventListener(new ValueEventListener() {
             @Override
             public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                 userlist.clear();
                  for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                      Messages_bean messages_bean = snapshot.getValue(Messages_bean.class);

                      if (messages_bean.getSender() != null && messages_bean.getSender().equals(fUser.getEmail())){
                          userlist.add(messages_bean.getReceiver());
                      }
                      if (messages_bean.getReceiver() != null && messages_bean.getReceiver().equals(fUser.getEmail())){
                          userlist.add(messages_bean.getSender());
                      }
                  }
                  readChats();
             }

             @Override
             public void onCancelled(@NonNull DatabaseError databaseError) {

             }
         });


        return view;
    }

    private void readChats(){
    mUsers = new ArrayList<>();

    reference = FirebaseDatabase.getInstance().getReference("Chats");
     reference.addValueEventListener(new ValueEventListener() {
         @Override
         public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
             mUsers.clear();
             for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                 Log.d("hello",snapshot.getKey());
                 Log.d("hello",snapshot.getValue().toString());
                 User_bean user_bean = snapshot.getValue(User_bean.class);


                 /*for ( String id: userlist){
                     if (user_bean.getEmail() != null && user_bean.getEmail().equals(id)){
                       if (mUsers.size() != 0 ){
                           for (User_bean user_bean1 : mUsers){
                               if (!user_bean.getEmail().equals(user_bean1.getEmail())){
                                   mUsers.add(user_bean);
                               }
                           }
                       }
                       else
                           mUsers.add(user_bean);
                     }
                 }
*/             }

             allUser_setContent = new AllUser_SetContent(getContext(), mUsers,true);
             recyclerView.setAdapter(allUser_setContent);
         }

         @Override
         public void onCancelled(@NonNull DatabaseError databaseError) {

         }
     });
    }
}