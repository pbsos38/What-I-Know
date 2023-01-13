package com.example.chat_box;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ChatActivity extends AppCompatActivity {

    TextView person_name,person_email;
    RecyclerView recyclerView;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference myRef1;
    public FirebaseRecyclerAdapter<Show_Chat_Activity_Data_Items, Show_Chat_ViewHolder> mFirebaseAdapter;
    ProgressBar progressBar;
    LinearLayoutManager mLinearLayoutManager;
    //String myRef;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.show_chat_layout);

        firebaseDatabase = FirebaseDatabase.getInstance();

        myRef1 = firebaseDatabase.getReference("users");
        //myRef.keepSynced(true);
        //this will enable offline support of Firebase database and will load data faster


        progressBar = (ProgressBar) findViewById(R.id.show_chat_progressBar2);

        //Recycler View
        recyclerView = (RecyclerView)findViewById(R.id.show_chat_recyclerView);

        mLinearLayoutManager = new LinearLayoutManager(ChatActivity.this);
        //mLinearLayoutManager.setStackFromEnd(true);

        recyclerView.setLayoutManager(mLinearLayoutManager);
    }

    @Override
    public void onStart() {
        super.onStart();
        progressBar.setVisibility(ProgressBar.VISIBLE);


        mFirebaseAdapter = new FirebaseRecyclerAdapter<Show_Chat_Activity_Data_Items, Show_Chat_ViewHolder>(Show_Chat_Activity_Data_Items.class, R.layout.show_chat_single_item, Show_Chat_ViewHolder.class, myRef1) {

            public void populateViewHolder(final Show_Chat_ViewHolder viewHolder, Show_Chat_Activity_Data_Items model, final int position) {
                //Log.d("LOGGED", "populateViewHolder Called: ");
                progressBar.setVisibility(ProgressBar.INVISIBLE);

                //if (!model.getName().equals("Null")) {
                String x= model.getName();
                if (x != null) {
                    //viewHolder.Person_Name(model.getName());
                    viewHolder.person_name.setText(model.getName());
                    //viewHolder.Person_Image(model.getImage_Url());

                    //viewHolder.Person_Email(model.getEmail());
                    if(model.getEmail().equals(SignIn.LoggedIn_User_Email))
                    {// if we retrive data from database , we will check if if it's me or not and if it's me then details will not be shown in page.

                        //viewHolder.itemView.setVisibility(View.GONE);
                        viewHolder.Layout_hide();

                        //recyclerView.getChildAdapterPosition(viewHolder.itemView.getRootView());
                        // viewHolder.itemView.set;
                    }
                    else
                        viewHolder.person_email.setText(model.getEmail());
                        //viewHolder.Person_Email(model.getEmail());

                }


                //OnClick Item
                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(final View v) {

                        DatabaseReference ref = mFirebaseAdapter.getRef(position);
                        ref.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {

                                String retrieve_name = dataSnapshot.child("Name").getValue(String.class);
                                String retrieve_Email = dataSnapshot.child("Email").getValue(String.class);
                                String retrieve_url = dataSnapshot.child("Image_URL").getValue(String.class);

                                Intent intent = new Intent(getApplicationContext(),ChatConversationActivity.class);
                                intent.putExtra("image_id", retrieve_url);
                                intent.putExtra("email", retrieve_Email);
                                intent.putExtra("name", retrieve_name);

                                startActivity(intent);
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });
                    }
                });
            }
        };

        recyclerView.setAdapter(mFirebaseAdapter);





    }


    //View Holder For Recycler View
    public static class Show_Chat_ViewHolder extends RecyclerView.ViewHolder {
        private final TextView person_name, person_email;
        private final ImageView person_image;
        private final LinearLayout layout;
        final LinearLayout.LayoutParams params;

        public Show_Chat_ViewHolder(final View itemView) {
            super(itemView);
            person_name = (TextView) itemView.findViewById(R.id.chat_person_name);
            person_email = (TextView) itemView.findViewById(R.id.chat_person_email);
            person_image = (ImageView) itemView.findViewById(R.id.chat_person_image);
            layout = (LinearLayout)itemView.findViewById(R.id.show_chat_single_item_layout);
            params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);// this will be needed to customise linear layout

        }


        /*private void Person_Name(String title) {
            // Log.d("LOGGED", "Setting Name: ");
            person_name.setText(title);
        }*/
        private void Layout_hide() {
            params.height = 0;
            //itemView.setLayoutParams(params);
            layout.setLayoutParams(params);

        }


        private void Person_Email(String title) {
            person_email.setText(title);
        }


        private void Person_Image(String url) {

            if (!url.equals("Null")) {
                Glide.with(itemView.getContext())
                        .load(url)
                        .crossFade()
                        .thumbnail(0.5f)
                        .placeholder(R.drawable.loading)
                        .bitmapTransform(new CircleTransform(itemView.getContext()))
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(person_image);
            }

        }


    }

}