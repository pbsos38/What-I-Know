package com.example.videochats;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class HomeAcivity extends AppCompatActivity {

    RecyclerView mContactListner;

    ImageView findPeoplebtn;
    BottomNavigationView navigationView;

    DatabaseReference contactRef, usersRef;
    FirebaseAuth mAuth;
    String currentUserId = "", uerName = "", profileImage = "", calledBy="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_acivity);

        mContactListner = findViewById(R.id.contact_list);
        findPeoplebtn = findViewById(R.id.find_people_btn);
        navigationView = findViewById(R.id.bottom_navigation);
        navigationView.setOnNavigationItemReselectedListener(navigationItemSelectedListner);

        mAuth = FirebaseAuth.getInstance();
        currentUserId = mAuth.getCurrentUser().getUid();
        contactRef = FirebaseDatabase.getInstance().getReference().child("Contacts");
        usersRef = FirebaseDatabase.getInstance().getReference().child("Users");

        mContactListner.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        findPeoplebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeAcivity.this, FindPeople.class));

            }
        });

    }

    private BottomNavigationView.OnNavigationItemReselectedListener navigationItemSelectedListner = new BottomNavigationView.OnNavigationItemReselectedListener() {
        @Override
        public void onNavigationItemReselected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.page_1:
                    startActivity(new Intent(HomeAcivity.this, MainActivity.class));
                    break;
                case R.id.page_2:
                    startActivity(new Intent(HomeAcivity.this, Settings.class));
                    break;
                case R.id.page_3:
                    startActivity(new Intent(HomeAcivity.this, Notification.class));
                    break;
                case R.id.page_4:
                    FirebaseAuth.getInstance().signOut();
                    startActivity(new Intent(HomeAcivity.this, MainActivity.class));
                    finish();
                    break;
            }
        }
    };

    @Override
    protected void onStart() {
        super.onStart();

        checkForRecievingCalls();
        validateUser();

        FirebaseRecyclerOptions<Contacts> options = new FirebaseRecyclerOptions.Builder<Contacts>()
                .setQuery(contactRef.child(currentUserId), Contacts.class)
                .build();

        FirebaseRecyclerAdapter<Contacts, ContactViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Contacts, ContactViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull final ContactViewHolder holder, int i, @NonNull Contacts contacts) {
                final String listUserId = getRef(i).getKey();


                usersRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            uerName = snapshot.child("name").getValue().toString();
                            profileImage = snapshot.child("image").getValue().toString();
                            holder.userNameText.setText(uerName);
                            Picasso.get().load(profileImage).into(holder.profileImageView);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

                holder.callBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(HomeAcivity.this, CallingActivity.class);
                        intent.putExtra("visit_user_id", listUserId);
                        startActivity(intent);
                        finish();
                    }
                });

            }

            @NonNull
            @Override
            public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.find_friend_layout, parent, false);
                ContactViewHolder viewHolder = new ContactViewHolder(view);
                return viewHolder;

            }
        };


        mContactListner.setAdapter(firebaseRecyclerAdapter);
        firebaseRecyclerAdapter.startListening();

    }

    private void validateUser() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        reference.child("Users").child(currentUserId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    startActivity(new Intent(HomeAcivity.this, Settings.class));
                    finish();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public static class ContactViewHolder extends RecyclerView.ViewHolder {
        TextView userNameText;
        Button callBtn;
        ImageView profileImageView;
        RelativeLayout cardView;

        public ContactViewHolder(@NonNull View itemView) {
            super(itemView);

            userNameText = itemView.findViewById(R.id.name_contact);
            callBtn = itemView.findViewById(R.id.call_btn);
            profileImageView = itemView.findViewById(R.id.image_contact);
            cardView = itemView.findViewById(R.id.card_view);

        }
    }

    private void checkForRecievingCalls() {
        usersRef.child(currentUserId)
                .child("Ringing")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        if (snapshot.hasChild("ringing")){
                            calledBy = snapshot.child("ringing").getValue().toString();
                            Intent intent = new Intent(HomeAcivity.this, CallingActivity.class);
                            intent.putExtra("visit_user_id", calledBy);
                            startActivity(intent);
                            finish();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }
}