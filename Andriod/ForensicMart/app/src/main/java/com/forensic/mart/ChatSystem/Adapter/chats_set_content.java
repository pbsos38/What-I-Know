package com.forensic.mart.ChatSystem.Adapter;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.forensic.mart.ChatSystem.BeanFiles.chats_bean;
import com.forensic.mart.R;

import java.util.List;


public class chats_set_content extends RecyclerView.Adapter<chats_set_content.ViewHolder> {
    private static final int MSG_TYPE_LEFT = 0;
    private static final int MSG_TYPE_RIGHT = 1;
    private Context mcontxet;
    private List<chats_bean> mChats;
    //current_user == sender;
    private String current_user;
    private String client_gst;

    DatabaseReference ref_read_all_msg;

    public chats_set_content(@NonNull Context context, List<chats_bean> mChats, String current_user, String clientGst) {
        this.mcontxet = context;
        this.mChats = mChats;
        this.current_user = current_user;
        this.client_gst = clientGst;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == MSG_TYPE_RIGHT) {
            View view = LayoutInflater.from(mcontxet).inflate(R.layout.chat_item_right, parent, false);
            return new chats_set_content.ViewHolder(view);
        } else {
            View view = LayoutInflater.from(mcontxet).inflate(R.layout.chat_item_left, parent, false);
            return new chats_set_content.ViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final chats_bean chatsBean = mChats.get(position);


        if (chatsBean.getMessage() != null && !chatsBean.getMessage().startsWith("https")) {
            //holder.profile_image.setImageResource(R.drawable.no_profile);
            holder.image_msg.setVisibility(View.INVISIBLE);
            holder.image_msg.setVisibility(View.GONE);
            holder.show_message.setVisibility(View.VISIBLE);
            holder.show_message.setText(chatsBean.getMessage());
        } else {
            try {
                holder.show_message.setVisibility(View.INVISIBLE);
                holder.show_message.setVisibility(View.GONE);
                holder.image_msg.setVisibility(View.VISIBLE);
                //Picasso.get().load(chatsBean.getMessage()).fit().centerCrop().into(holder.image_msg);
                Glide.with(mcontxet).load(chatsBean.getMessage()).into(holder.image_msg);
            } catch (Exception e) {
            }
        }

        holder.show_message.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ClipData myClip;
                ClipboardManager clipboard = (ClipboardManager) mcontxet.getSystemService(Context.CLIPBOARD_SERVICE);
                myClip = ClipData.newPlainText("text", chatsBean.getMessage());
                assert clipboard != null;
                clipboard.setPrimaryClip(myClip);
                Toast.makeText(mcontxet, "Text Copied", Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        /*if (position == mChats.size() - 1) {
            if (chatsBean.getStatus() != null && !chatsBean.getSender().equals(current_user)) {
                if (chatsBean.getStatus().equals("seen")) {
                    holder.chat_item_left.getLayoutParams().width = ViewGroup.LayoutParams.MATCH_PARENT;
                    holder.show_message.setVisibility(View.INVISIBLE);
                    holder.show_message.setVisibility(View.GONE);
                    holder.image_msg.setVisibility(View.INVISIBLE);
                    holder.image_msg.setVisibility(View.GONE);
                    holder.status.setVisibility(View.VISIBLE);
                    holder.status.setText("seen");
                } else {
                    holder.chat_item_left.getLayoutParams().width = ViewGroup.LayoutParams.MATCH_PARENT;
                    holder.show_message.setVisibility(View.INVISIBLE);
                    holder.show_message.setVisibility(View.GONE);
                    holder.image_msg.setVisibility(View.INVISIBLE);
                    holder.image_msg.setVisibility(View.GONE);
                    holder.status.setVisibility(View.VISIBLE);
                    holder.status.setText("Delivered");

                }
            }
        }*/


        if (position == mChats.size() - 1) {
            if (!chatsBean.getSender().equals(current_user))
            {
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users").child(chatsBean.getSender());
                reference.child("newMsg").setValue("noNew");
            }
        }


    }

    @Override
    public int getItemCount() {
        return mChats.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView show_message, status;
        ImageView image_msg, enlarge_image;
        RelativeLayout chat_item_left;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            show_message = itemView.findViewById(R.id.message_all_msg);
            status = itemView.findViewById(R.id.text_seen);
            image_msg = itemView.findViewById(R.id.pic_all_msg);
            //enlarge_image = itemView.findViewById(R.id.fetch_enlarge_image);
            chat_item_left = itemView.findViewById(R.id.relativeLayout_chat_item_left);
        }
    }

    @Override
    public int getItemViewType(int position) {

        if (mChats.get(position).getSender() != null && mChats.get(position).getSender().equals(current_user))
            return MSG_TYPE_RIGHT;
        else
            return MSG_TYPE_LEFT;

    }

}
