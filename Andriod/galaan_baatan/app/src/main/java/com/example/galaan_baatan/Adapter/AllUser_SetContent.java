package com.example.galaan_baatan.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.galaan_baatan.BeanFiles.Messages_bean;
import com.example.galaan_baatan.BeanFiles.User_bean;
import com.example.galaan_baatan.Messages;
import com.example.galaan_baatan.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

public class AllUser_SetContent extends RecyclerView.Adapter<AllUser_SetContent.ViewHolder> {
    private Context mcontxet;
    private List<User_bean> mUsers;
    private  boolean ischat;
    String theLastMessage;


    public AllUser_SetContent(Context context, List<User_bean> mUsers, boolean ischat)
    {
        this.mUsers = mUsers;
        this.mcontxet = context;
        this.ischat = ischat;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mcontxet).inflate(R.layout.user_item, parent,false);
        return new AllUser_SetContent.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final User_bean user_bean = mUsers.get(position);
        if (user_bean.getEmail()!=null && user_bean.getEmail() != FirebaseAuth.getInstance().getCurrentUser().getEmail())
        {

            holder.username.setText(user_bean.getEmail());
        if (user_bean.getImage_Url()!=null && user_bean.getImage_Url().equals("Null"))
        {
            holder.profile_image.setImageResource(R.drawable.no_profile);
        } else {
            Glide.with(mcontxet).load(user_bean.getImage_Url()).into(holder.profile_image);
        }

        if (ischat)
        {
            if (user_bean.getStatus() != null && user_bean.getStatus().equals("online"))
                holder.img_on.setVisibility(View.VISIBLE);
        }
        else
        {
            holder.img_off.setVisibility(View.GONE);
            holder.img_on.setVisibility(View.GONE);
        }

        if (ischat){
            lastMessage(holder.last_msg,user_bean.getEmail());
        }else
            holder.last_msg.setVisibility(View.GONE);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mcontxet, Messages.class);
                intent.putExtra("Email",user_bean.getEmail());
                mcontxet.startActivity(intent);
            }
        });
        }
    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
     {
        public TextView username;
        public ImageView profile_image;
        private ImageView img_on;
        private ImageView img_off;
        private TextView last_msg;
         public ViewHolder(@NonNull View itemView) {
             super(itemView);
             username = itemView.findViewById(R.id.username);
             profile_image = itemView.findViewById(R.id.profile_image);

             img_off = itemView.findViewById(R.id.img_off);
             img_on = itemView.findViewById(R.id.img_on);
             last_msg = itemView.findViewById(R.id.last_msg);
         }
     }

     private void lastMessage(final TextView Last_msg, final String email){
        theLastMessage = "default";
         FirebaseUser fuser = FirebaseAuth.getInstance().getCurrentUser();
         final String fusers = fuser.getEmail().replace("@","").replace(".","");

         DatabaseReference myRef = FirebaseDatabase.getInstance().getReference().child("Chats").child(fusers).child(email.replace("@","").replace(".",""));
         myRef.keepSynced(true);

         myRef.addValueEventListener(new ValueEventListener() {
             @Override
             public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                 for (DataSnapshot snapshot: dataSnapshot.getChildren() ){
                     Messages_bean messages_bean = snapshot.getValue(Messages_bean.class);

                     assert messages_bean != null;
                     assert messages_bean.getSender() != null;
                     assert messages_bean.getReceiver() != null;
                     if (messages_bean.getReceiver().equals(fusers) && messages_bean.getSender().equals(email) ||
                                    messages_bean.getReceiver().equals(email) && messages_bean.getSender().equals(fusers) ){
                         theLastMessage = messages_bean.getMessage();
                     }
                 }
                 switch (theLastMessage){
                     case "default":
                         Last_msg.setText("No message");
                         break;

                     default:
                         Last_msg.setText(theLastMessage);
                         break;
                 }
                 theLastMessage = "default";

             }

             @Override
             public void onCancelled(@NonNull DatabaseError error) {

             }
         });


     }

}
