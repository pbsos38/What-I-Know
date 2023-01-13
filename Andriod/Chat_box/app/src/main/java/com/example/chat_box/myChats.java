package com.example.chat_box;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.util.ArrayMap;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;

public class myChats extends AppCompatActivity {

    public RecyclerView recyclerView;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference myRef,myRef2;
    private FirebaseRecyclerAdapter<Show_Chat_Conversation_Data_Items, Chat_Conversation_ViewHolder> mFirebaseAdapter;
    public LinearLayoutManager mLinearLayoutManager;
    String Sender_Name;
    ImageView send_icon;
    EditText message_area;
    TextView message, sender;
    ImageView chat_image_incoming,chat_image_outgoing;
    View mView;
    LinearLayout.LayoutParams params,text_params;
    LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_chat_conversation_layout);

        String USER_ID = SignIn.LoggedIn_User_Email.replace("@","").replace(".","");
        firebaseDatabase = FirebaseDatabase.getInstance();
        myRef = FirebaseDatabase.getInstance().getReference().child("Chat").child(USER_ID).child(getIntent().getStringExtra("email").replace("@","").replace(".",""));
        myRef.keepSynced(true);

        myRef2 = FirebaseDatabase.getInstance().getReference().child("Chat").child(getIntent().getStringExtra("email").replace("@","").replace(".","")).child(USER_ID);
        myRef2.keepSynced(true);
        

        //reference variables
        {
            Sender_Name = getIntent().getStringExtra("name");
            recyclerView = (RecyclerView) findViewById(R.id.fragment_chat_recycler_view);
            send_icon = (ImageView) findViewById(R.id.sendButton);
            message_area = (EditText) findViewById(R.id.messageArea);
            mLinearLayoutManager = new LinearLayoutManager(myChats.this);
            recyclerView.setLayoutManager(mLinearLayoutManager);
            mLinearLayoutManager.setStackFromEnd(true);


            message = (TextView)findViewById(R.id.fetch_chat_messgae);
            sender = (TextView)findViewById(R.id.fetch_chat_sender);
            chat_image_incoming = (ImageView) findViewById(R.id.chat_uploaded_image_incoming);
            chat_image_outgoing = (ImageView) findViewById(R.id.chat_uploaded_image_outgoing);

            params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            text_params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layout = (LinearLayout)findViewById(R.id.chat_linear_layout);

        }



        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Map<String, Object> map = (Map<String, Object>) dataSnapshot.getValue();
               // BigInteger newdate ,olddate ;
                //use the for loop here to step over each child and retrieve data


                for (DataSnapshot childSnapshot : dataSnapshot.getChildren()){
                    //String valueFromDB = childSnapshot.getValue(String.class);
                   /* newdate = new BigInteger(childSnapshot.getKey().toString());
                    Log.d("datetime",String.valueOf(newdate));
                    olddate = new BigInteger(childSnapshot.getKey().toString());
                    if(newdate < olddate)
                    {
                        Log.d("datetime",String.valueOf(olddate));
                    }*/

                   Log.d("hello",childSnapshot.toString());
                  //it shares links of data
                    // Log.d("hello",dataSnapshot.getChildren().toString());
                   Log.d("hello",childSnapshot.child("message").getValue().toString());

                   getSender(childSnapshot.child("sender").getValue().toString());
                   getMessage(childSnapshot.child("message").getValue().toString());

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        send_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String messageText = message_area.getText().toString().trim();

                if (!messageText.equals("")) {
                    ArrayMap<String, String> map = new ArrayMap<>();
                    map.put("message", messageText);
                    map.put("sender", SignIn.LoggedIn_User_Email);
                   /* String x = SimpleDateFormat.getDateTimeInstance().format(new Date());
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault());
                    String currentDateandTime = sdf.format(new Date());*/
                    myRef.push().setValue(map);
                    //myRef2.push().setValue(map);
                    message_area.setText("");
                   /* recyclerView.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            recyclerView.smoothScrollToPosition(recyclerView.getAdapter().getItemCount() - 1);

                        }
                    }, 500);*/
                }
            }
        });

    }
    private void getSender(String title) {


        if(title!=null && title.equals(SignIn.LoggedIn_User_Email))
        {
            //Log.d("LOGGED", "getSender: ");
            params.setMargins((SignIn.Device_Width/3),5,10,10);
            text_params.setMargins(15,10,0,5);
            sender.setLayoutParams(text_params);
            mView.setLayoutParams(params);
            mView.setBackgroundResource(R.drawable.shape_outcoming_message);
            sender.setText("YOU");
            chat_image_outgoing.setVisibility(View.VISIBLE);
            chat_image_incoming.setVisibility(View.GONE);

        }
        else
        {
            params.setMargins(10,0,(SignIn.Device_Width/3),10);
            sender.setGravity(Gravity.START);
            text_params.setMargins(60,10,0,5);
            sender.setLayoutParams(text_params);
            mView.setLayoutParams(params);
            mView.setBackgroundResource(R.drawable.shape_incoming_message);
            sender.setText(Sender_Name);
            chat_image_outgoing.setVisibility(View.GONE);
            chat_image_incoming.setVisibility(View.VISIBLE);
        }
    }

    private void getMessage(String title) {


        //if(title!=null && !title.startsWith("https"))
        {

            if(!sender.getText().equals(Sender_Name))
            {
                text_params.setMargins(15,10,22,15);
            }
            else
            {
                text_params.setMargins(65,10,22,15);
            }

            message.setLayoutParams(text_params);
            message.setText(title);
            message.setTextColor(Color.parseColor("#FFFFFF"));
            message.setVisibility(View.VISIBLE);
            chat_image_incoming.setVisibility(View.GONE);
            chat_image_outgoing.setVisibility(View.GONE);
        }
            /*else
            {
                if (chat_image_outgoing.getVisibility()==View.VISIBLE && chat_image_incoming.getVisibility()==View.GONE)
                {
                    chat_image_outgoing.setVisibility(View.VISIBLE);
                    message.setVisibility(View.GONE);
                    Glide.with(itemView.getContext())
                            .load(title)
                            .crossFade()
                            .fitCenter()
                            .placeholder(R.drawable.loading)
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .into(chat_image_outgoing);
                }
                else
                {
                    chat_image_incoming.setVisibility(View.VISIBLE);
                    message.setVisibility(View.GONE);
                    Glide.with(itemView.getContext())
                            .load(title)
                            .crossFade()
                            .fitCenter()
                            .placeholder(R.drawable.loading)
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .into(chat_image_incoming);
                }
            }*/

    }

    private class Chat_Conversation_ViewHolder extends RecyclerView.ViewHolder {

        public Chat_Conversation_ViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;

        }


    }
}