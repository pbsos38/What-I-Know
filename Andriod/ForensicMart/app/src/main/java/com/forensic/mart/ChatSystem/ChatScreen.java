package com.forensic.mart.ChatSystem;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.util.ArrayMap;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.forensic.mart.ChatSystem.Adapter.chats_set_content;
import com.forensic.mart.ChatSystem.BeanFiles.chats_bean;
import com.forensic.mart.R;
import com.forensic.mart.SmallFeatures.ViewDialog;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ChatScreen extends AppCompatActivity {

    EditText type_msg;
    ImageView send_btn, no_chats;
    DatabaseReference ref_send_msg2,ref_send_msg, ref_register, ref_read_all_messages,ref_register2;
    RecyclerView recyclerView;
    List<chats_bean> mChat;
    chats_set_content chats_set_content;
    ViewDialog viewDialog;
    private String sender_id,receiver_id,receiver_name,sender_name,client_gstno, client_fname, push_id, Date, time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_screen);
        {
            sender_id = getIntent().getStringExtra("sender_id");
            receiver_id = getIntent().getStringExtra("receiver_id");
            receiver_name = getIntent().getStringExtra("receiver_name");
            sender_name = getIntent().getStringExtra("userName");
        }
        //reference variables
        {
            type_msg = findViewById(R.id.type_msg_Chats_screen);
            send_btn = findViewById(R.id.btn_send_Chats_screen);
            no_chats = findViewById(R.id.no_chat_tex_Chats_screent);

            viewDialog = new ViewDialog(this);

            ref_send_msg = FirebaseDatabase.getInstance().getReference().child("chats").child(sender_id);
            ref_send_msg.keepSynced(true);
        }


//for setting messages
        {
            recyclerView = findViewById(R.id.recycler_view_Chats_screen);
            recyclerView.setHasFixedSize(true);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
            linearLayoutManager.setStackFromEnd(true);
            recyclerView.setLayoutManager(linearLayoutManager);
        }
        Log.d("-----------",sender_id+" " + receiver_id);
        read_all_messages();



    }

    private void read_all_messages() {
        viewDialog.showDialog_cancelable();
        mChat = new ArrayList<>();
        ref_read_all_messages = FirebaseDatabase.getInstance().getReference().child("chats").child(sender_id).child(receiver_id);
        ref_read_all_messages.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mChat.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    // Log.d("off",snapshot.getValue().toString());
                    //Log.d("off",snapshot.getKey());
                    push_id = snapshot.getKey();
                    chats_bean chatsBean = snapshot.getValue(chats_bean.class);
                    assert chatsBean != null;
                    mChat.add(chatsBean);
                }
                chats_set_content = new chats_set_content(ChatScreen.this, mChat, sender_id, client_gstno);
                recyclerView.setAdapter(chats_set_content);
                viewDialog.hideDialog();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void Send_message(View view) {
        viewDialog.showDialog();
        ref_send_msg = FirebaseDatabase.getInstance().getReference().child("chats").child(sender_id).child(receiver_id);
        ref_send_msg2 = FirebaseDatabase.getInstance().getReference().child("chats").child(receiver_id).child(sender_id);
        ref_send_msg.keepSynced(true);
        String typed_msg = type_msg.getText().toString().trim();

        if (!typed_msg.equals("")) {
            @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
            @SuppressLint("SimpleDateFormat") SimpleDateFormat timeFormat = new SimpleDateFormat("hhmm");
            @SuppressLint("SimpleDateFormat") SimpleDateFormat secondFormat = new SimpleDateFormat("ssss");
            Date = dateFormat.format(Calendar.getInstance().getTime());
            time = timeFormat.format(Calendar.getInstance().getTime());
            String seconds = timeFormat.format(Calendar.getInstance().getTime());
            //long seconds= System.currentTimeMillis();

            ArrayMap<String, String> map = new ArrayMap<>();
            map.put("message", typed_msg);
            map.put("sender", sender_id);
            map.put("status", "not_seen");
            map.put("Date", Date);
            map.put("Time", time);
            ref_send_msg.push().setValue(map);
            ref_send_msg2.push().setValue(map);
            type_msg.setText("");
            /*recyclerView.postDelayed(new Runnable() {
                @Override public void run()
                {
                    recyclerView.smoothScrollToPosition(recyclerView.getAdapter().getItemCount()-1);
                }
            }, 500);*/
        }


        //just to create a user for home page
        {
            ref_register = FirebaseDatabase.getInstance().getReference().child("users").child(sender_id).child(receiver_id);
            ref_register.child("Name").setValue(receiver_name);
            ref_register.child("id").setValue(receiver_id);
            ref_register2 = FirebaseDatabase.getInstance().getReference().child("users").child(receiver_id).child(sender_id);
            ref_register2.child("Name").setValue(sender_name);
            ref_register2.child("id").setValue(sender_id);
        }
        //update for new message in box
        {
            String vb;
            if (sender_id != null && sender_id.equals(receiver_id))
                vb = sender_id;
            else
                vb = receiver_id;
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users").child(sender_id).child(vb);
            reference.child("newMsg").setValue("new");
        }



        viewDialog.hideDialog();
    }

}