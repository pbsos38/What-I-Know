package com.example.galaan_baatan.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.galaan_baatan.R;

import java.util.List;
import com.example.galaan_baatan.BeanFiles.Messages_bean;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class message_SetContent extends RecyclerView.Adapter<message_SetContent.ViewHolder>{
    private static final int MSG_TYPE_LEFT = 0;
    private static final int MSG_TYPE_RIGHT = 1;
    private Context mcontxet;
    private List<Messages_bean> mChats;
    private String image_url;
    private String push_id;
    private String email;

    FirebaseUser fuser;
    DatabaseReference myRef,myRef2;

    public message_SetContent(Context context, List<Messages_bean> mChats, String image_url,String push_id,String email)
    {
        this.mChats = mChats;
        this.mcontxet = context;
        this.image_url = image_url;
        this.push_id = push_id;
        this.email = email;

    }

    @NonNull
    @Override
    public message_SetContent.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == MSG_TYPE_RIGHT)
        {
            View view = LayoutInflater.from(mcontxet).inflate(R.layout.chat_item_right, parent,false);
        return new message_SetContent.ViewHolder(view);}
        else
        {
            View view = LayoutInflater.from(mcontxet).inflate(R.layout.chat_item_left, parent,false);
            return new message_SetContent.ViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull message_SetContent.ViewHolder holder, int position) {
        final Messages_bean messages_bean = mChats.get(position);
        holder.show_message.setText(messages_bean.getMessage());


        final String fusers = FirebaseAuth.getInstance().getCurrentUser().getEmail().replace("@","").replace(".","");

        myRef = FirebaseDatabase.getInstance().getReference().child("Chats").child(fusers).child(email.replace("@","").replace(".","")).child(push_id);
        myRef.keepSynced(true);
        myRef.child("status").setValue("seen");

        if (position == mChats.size()-1){
            if (messages_bean.getStatus()!=null && messages_bean.getStatus().equals("seen"))
                holder.status.setText("seen");

        }

       /* if (image_url != null && image_url.equals("Null"))
        {
            holder.profile_image.setImageResource(R.drawable.no_profile);
        }
        else
            Glide.with(mcontxet).load(image_url).into(holder.profile_image);*/
    }

    @Override
    public int getItemCount() {
        return mChats.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        public TextView show_message,status;
        //public ImageView profile_image;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            show_message = itemView.findViewById(R.id.message_all_msg);
            status = itemView.findViewById(R.id.text_seen);
            //profile_image = itemView.findViewById(R.id.profile_image);

        }
    }

    @Override
    public int getItemViewType(int position) {

        fuser = FirebaseAuth.getInstance().getCurrentUser();
        if (mChats.get(position).getSender() != null && mChats.get(position).getSender().equals(fuser.getEmail()))
            return MSG_TYPE_LEFT;
        else
            return MSG_TYPE_RIGHT;

    }
}
