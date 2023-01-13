package com.forensic.mart.ChatSystem.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.forensic.mart.ChatSystem.BeanFiles.Bean_allUsers;
import com.forensic.mart.ChatSystem.ChatScreen;
import com.forensic.mart.R;

import java.util.ArrayList;
import java.util.List;


public class AllUesrs_setContent extends RecyclerView.Adapter<AllUesrs_setContent.InnerClassViewHolder> {

    Context mContext;
    private List<Bean_allUsers> mClients;
    private List<Bean_allUsers> FilteredList;
    private String if_unread_msgs,myCrads,myName;

    public AllUesrs_setContent(Context context,List<Bean_allUsers> lst,String if_unread_msg,String myCrad,String myNam) {
        this.mContext = context;
        this.mClients = lst;
        FilteredList = new ArrayList<>(lst);
        this.if_unread_msgs = if_unread_msg;
        this.myCrads  =myCrad;
        this.myName = myNam;
    }


    @NonNull
    @Override
    public InnerClassViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(mContext);
        View view= inflater.inflate(R.layout.show_chat_single_item,parent,false);
        //RecyclerViewHolder holder=new RecyclerViewHolder(view);
        return new InnerClassViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AllUesrs_setContent.InnerClassViewHolder holder, int position) {

        Bean_allUsers clients = mClients.get(position);
        //checking if its me
       /* if (clients.getuserName().equals("owner"))
            holder.firmName_text.setText("You");
        else*/
            holder.firmName_text.setText(clients.getName());

        holder.gstNo_text.setText(clients.getStatus());
        //opening chats
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ChatScreen.class);
                intent.putExtra("sender_id",myCrads);
                intent.putExtra("receiver_id", clients.getId());
                intent.putExtra("receiver_name",clients.getName());
                intent.putExtra("userName", myName);
                mContext.startActivity(intent);
            }
        });

        /*//setting if user has unseen msg
        if (clients.getNewMsg()!=null && clients.getNewMsg().equals("new"))
            holder.cardView.setCardBackgroundColor(Color.parseColor("#CE16F116"));
        else
            holder.cardView.setCardBackgroundColor(Color.parseColor("#DADCDA"));*/
    }

    @Override
    public int getItemCount() {
        return mClients.size();
    }


    public class InnerClassViewHolder extends RecyclerView.ViewHolder {

        TextView firmName_text,gstNo_text;
        CardView cardView;
        public InnerClassViewHolder(View view) {
            super(view);

            firmName_text = view.findViewById(R.id.retailerName_show_chat_single_item_layout);
            gstNo_text = view.findViewById(R.id.gstno_show_chat_single_item_layout);
            cardView = view.findViewById(R.id.card_home_page_chats);
        }
    }

    public Filter getFilter() {
        return startFiltering;
    }

    private Filter startFiltering = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Bean_allUsers> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(FilteredList);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (Bean_allUsers item : FilteredList) {
                    /*if (item.getGSTNo().toLowerCase().contains(filterPattern.toLowerCase()) || item.getuserName().toLowerCase().contains(filterPattern.toLowerCase())) {
                        filteredList.add(item);
                    }*/
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {

            mClients.clear();
            mClients.addAll((List)results.values);
            notifyDataSetChanged();
        }
    };

}
