package com.example.galaan_baatan.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.galaan_baatan.Adapter.AllUser_SetContent;
import com.example.galaan_baatan.BeanFiles.User_bean;
import com.example.galaan_baatan.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UsersFragment extends Fragment {

    private RecyclerView recyclerView;
    private List<User_bean> mUsers;
    private AllUser_SetContent allUser_setContent;
    EditText search_edittext;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_users, container, false);

        recyclerView = view.findViewById(R.id.recycler_view_all_users);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mUsers = new ArrayList<>();
        readUsers();

        search_edittext = view.findViewById(R.id.search_users);
        search_edittext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                searchUser(s.toString   ());
            }

            @Override
            public void afterTextChanged(Editable s) {
                searchUser(s.toString());

            }
        });
        return view;
    }

    private void searchUser(String toString) {
        final FirebaseUser fuser = FirebaseAuth.getInstance().getCurrentUser();
        Query query = FirebaseDatabase.getInstance().getReference("users").orderByChild("Name")
                .startAt(toString)
                .endAt(toString + "\uf8ff");

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                mUsers.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    User_bean user_bean = snapshot.getValue(User_bean.class);

                    assert user_bean != null;
                    assert fuser != null;

                        mUsers.add(user_bean);
                    if (user_bean.getEmail() != null && !user_bean.getEmail().equals(fuser.getEmail())) {
                    } else {
                    }
                }
                allUser_setContent = new AllUser_SetContent(getContext(), mUsers, true);
                recyclerView.setAdapter(allUser_setContent);
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void readUsers() {
        final FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (search_edittext.getText().toString().equals(""))
                {
                    mUsers.clear();
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        Log.d("recieve:", Objects.requireNonNull(snapshot.getKey()));
                        Log.d("recieve:", Objects.requireNonNull(snapshot.getValue()).toString());
                        User_bean user_bean = snapshot.getValue(User_bean.class);

                        assert user_bean != null;
                        assert firebaseUser != null;

                        if (user_bean.getId() != null && !user_bean.getId().equals(firebaseUser.getUid())) {
                        } else {
                            mUsers.add(user_bean);
                        }
                    }
                    allUser_setContent = new AllUser_SetContent(getContext(), mUsers, true);
                    recyclerView.setAdapter(allUser_setContent);
                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}