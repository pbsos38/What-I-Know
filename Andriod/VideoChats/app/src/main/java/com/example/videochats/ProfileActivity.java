package com.example.videochats;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class ProfileActivity extends AppCompatActivity {
    String receiverUserID = "", receiverUsrImage = "", receiverUserName = "";
    ImageView background_profile_view;
    TextView name_profile;
    Button add_friend, decline_friend;
    FirebaseAuth mAuth;
    String senderUserId;
    String currentState = "new";
    DatabaseReference friendRequestRef, contactRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        mAuth = FirebaseAuth.getInstance();
        senderUserId = mAuth.getCurrentUser().getUid();
        friendRequestRef = FirebaseDatabase.getInstance().getReference().child("Friend Requests");
        contactRef = FirebaseDatabase.getInstance().getReference().child("Contacts");


        receiverUserID = getIntent().getExtras().get("visit_user_id").toString();
        receiverUsrImage = getIntent().getExtras().get("profile_image").toString();
        receiverUserName = getIntent().getExtras().get("profile_name").toString();

        background_profile_view = findViewById(R.id.background_profile_view);
        name_profile = findViewById(R.id.name_profile);
        add_friend = findViewById(R.id.add_friend);
        decline_friend = findViewById(R.id.decline_friend);

        Picasso.get().load(receiverUsrImage).into(background_profile_view);
        name_profile.setText(receiverUserName);

        managClickEvents();
    }

    private void managClickEvents() {

        friendRequestRef.child(senderUserId)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.hasChild(receiverUserID)){
                            String request_type = snapshot.child(receiverUserID).child("request_type").getValue().toString();
                            if (request_type.equals("sent")){
                                currentState = "request_sent";
                                add_friend.setText("cancel Friend Request");
                            }
                            else
                                if (request_type.equals("received")){
                                    currentState = "request_received";
                                    add_friend.setText("Accept Friend Request");
                                decline_friend.setVisibility(View.VISIBLE);
                                decline_friend.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        cancelFriendRequest();
                                    }
                                });
                                }
                        }
                        else
                        {
                            contactRef.child(senderUserId).
                                    addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                            if (snapshot.hasChild(receiverUserID)){
                                                currentState = "friends";
                                                add_friend.setText("Delete Contact");

                                            }
                                            else
                                                currentState = "new";
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError error) {

                                        }
                                    });
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
        if (senderUserId.equals(receiverUserID)) {
            add_friend.setVisibility(View.GONE);
        } else
            add_friend.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (senderUserId.equals("new")) {

                        SendFirendRequest();
                    } else if (currentState.equals("request_sent")) {
                        cancelFriendRequest();
                    } else if (currentState.equals("request_received")) {
                        AcceptFriendRequest();
                    } else if (currentState.equals("request_sent")) {
                        cancelFriendRequest();
                    }
                }
            });
    }

    private void AcceptFriendRequest() {
        contactRef.child(senderUserId).child(receiverUserID).child("Contact").setValue("Saved")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            contactRef.child(receiverUserID).child(senderUserId).child("Contact").setValue("Saved")
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                friendRequestRef.child(senderUserId).child(receiverUserID).removeValue()
                                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                            @Override
                                                            public void onComplete(@NonNull Task<Void> task) {
                                                                if (task.isSuccessful()) {
                                                                    friendRequestRef.child(receiverUserID).child(senderUserId).removeValue()
                                                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                                @Override
                                                                                public void onComplete(@NonNull Task<Void> task) {
                                                                                    if (task.isSuccessful()) {
                                                                                        currentState = "friends";
                                                                                        add_friend.setText("Delete Contact");
                                                                                        decline_friend.setVisibility(View.GONE);
                                                                                    }
                                                                                }
                                                                            });
                                                                }
                                                            }
                                                        });
                                            }
                                        }
                                    });

                        }
                    }
                });

    }

    private void cancelFriendRequest() {
        friendRequestRef.child(senderUserId).child(receiverUserID).removeValue()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            friendRequestRef.child(receiverUserID).child(senderUserId).removeValue()
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                currentState = "new";
                                                add_friend.setText("Add Friend");
                                            }
                                        }
                                    });
                        }
                    }
                });

    }

    private void SendFirendRequest() {

        friendRequestRef.child(senderUserId).child(receiverUserID).child("request_type").setValue("sent")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            friendRequestRef.child(receiverUserID).child(senderUserId).child("request_type").setValue("received")
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                currentState = "request_sent";
                                                add_friend.setText("cancel Friend Request");
                                                Toast.makeText(ProfileActivity.this, "Request Sent", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                        }
                    }
                });
    }
}