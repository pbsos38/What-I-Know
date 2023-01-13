package com.example.galaan_baatan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.ArrayMap;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.galaan_baatan.BeanFiles.User_bean;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import com.example.galaan_baatan.BeanFiles.Messages_bean;
import com.example.galaan_baatan.Adapter.message_SetContent;
public class Messages extends AppCompatActivity {

    CircleImageView circleImageView;
    TextView username;
    ImageView profile_image,send;
    FirebaseUser fuser;
    DatabaseReference reference,myRef,myRef2;
    String email,push_id;
    EditText msg;
    message_SetContent message_setContent;
    List<Messages_bean> mChat;
    Messages_bean messages_bean;

    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages);

        //profile_image = findViewById(R.id.profile_image);
        username = findViewById(R.id.username_msg);
        email = getIntent().getStringExtra("Email");
        send = findViewById(R.id.sendButton);
        msg = findViewById(R.id.messageArea);
        //for setting messages
        {
            recyclerView = findViewById(R.id.fragment_chat_recycler_view);
            recyclerView.setHasFixedSize(true);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
            linearLayoutManager.setStackFromEnd(true);
            recyclerView.setLayoutManager(linearLayoutManager);
        }
        fuser = FirebaseAuth.getInstance().getCurrentUser();
        final String fusers = fuser.getEmail().replace("@","").replace(".","");
        reference = FirebaseDatabase.getInstance().getReference("users").child(email.replace(".","").replace("@",""));

        myRef = FirebaseDatabase.getInstance().getReference().child("Chats").child(fusers).child(email.replace("@","").replace(".",""));
        myRef.keepSynced(true);
        //Log.d("LOGGED", "myRef : " + myRef);
        myRef2 = FirebaseDatabase.getInstance().getReference().child("Chats").child(email.replace("@","").replace(".","")).child(fusers);
        myRef2.keepSynced(true);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User_bean user_bean = dataSnapshot.getValue(User_bean.class);
                username.setText(user_bean.getEmail());
                if (user_bean.getImage_Url()!=null && user_bean.getImage_Url().equals("Null"))
                {
                    // profile_image.setImageResource(R.drawable.no_profile);
                }
                else
                {
                    //Glide.with(Messages.this).load(user_bean.getImage_Url()).into(profile_image);
                }
                allMessages(fuser.getEmail(),email,user_bean.getImage_Url());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String messageText = msg.getText().toString().trim();

                if(!messageText.equals("")){
                    ArrayMap<String, String> map = new ArrayMap<>();
                    map.put("message", messageText);
                    map.put("sender", fusers );
                    map.put("receiver", email );
                    map.put("status", "not_seen" );
                    String x = SimpleDateFormat.getDateTimeInstance().format(new Date());
                    myRef.push().setValue(map);
                    myRef2.push().setValue(map);
                    msg.setText("");
                    recyclerView.postDelayed(new Runnable() {
                        @Override public void run()
                        {
                            recyclerView.smoothScrollToPosition(recyclerView.getAdapter().getItemCount()-1);

                        }
                    }, 500);
                }
            }
        });
    }
    private void allMessages(final String myid, final  String userid, final String image_url)
    {
        mChat = new ArrayList<>();
    //reference = FirebaseDatabase.getInstance().getReference("Chats").child();
    myRef.addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            mChat.clear();
            for (DataSnapshot snapshot : dataSnapshot.getChildren())
            {
                Log.d("off",snapshot.getValue().toString());
                push_id = snapshot.getKey();

                Messages_bean messages_bean = snapshot.getValue(Messages_bean.class);
                assert messages_bean !=null;
//                if (messages_bean.getReceiver() != null && messages_bean.getReceiver().equals(myid) && messages_bean.getSender().equals(userid) ||
//                           messages_bean.getSender() !=null && messages_bean.getReceiver().equals(userid) && messages_bean.getSender().equals(myid))
                myRef.child(push_id).child("status").setValue("seen");
                mChat.add(messages_bean);
//                Log.d("off","f"+messages_bean.getMessage());
            }
            message_setContent = new message_SetContent(Messages.this, mChat,image_url,push_id,email);
            recyclerView.setAdapter(message_setContent);
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    });
    }


    private void status(String status){
        reference = FirebaseDatabase.getInstance().getReference("users").child(FirebaseAuth.getInstance().getCurrentUser().getEmail().replace("@","").replace(".",""));

        reference.child("Status").setValue(status);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        status("offline");
    }

    @Override
    protected void onResume() {
        super.onResume();
        status("online");
    }

    @Override
    protected void onPause() {
        super.onPause();
        status("offline");
    }
}