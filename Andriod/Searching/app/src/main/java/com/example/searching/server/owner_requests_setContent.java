package com.example.searching.server;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.searching.R;
import com.example.searching.staticMode.ExampleItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class owner_requests_setContent extends RecyclerView.Adapter<owner_requests_setContent.InnerClassViewHolder> implements Filterable
{
    ArrayList<bean_fetch_Requests.UserBean> aryList;
    static Context ctx;
    public static String Retailer_status,owner_authorit;
    private List<bean_fetch_Requests.UserBean> exampleListFull;

    owner_requests_setContent(ArrayList<bean_fetch_Requests.UserBean> lst,Context ct,String retailer_status,String owner_authority)
    {
        aryList=lst;
        ctx=ct;
        Retailer_status = retailer_status;
        owner_authorit = owner_authority;
        exampleListFull = new ArrayList<>(lst);

    }
    @Override
    public int getItemCount() {
        return aryList.size();
    }

    @Override
    public InnerClassViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(ctx);
        View view= inflater.inflate(R.layout.owner_all_requests,parent,false);
        //RecyclerViewHolder holder=new RecyclerViewHolder(view);
        InnerClassViewHolder itemView=new InnerClassViewHolder(view);
        return itemView;

    }

    @Override
    public void onBindViewHolder(InnerClassViewHolder itemView, int index)
    {
        bean_fetch_Requests.UserBean user=aryList.get(index);
        itemView.rname.setText(user.getRname());
        itemView.fname.setText(user.getFname());
        itemView.gst.setText(user.getGst());
        itemView.city.setText(user.getCity());

    }

    public Filter getFilter() {
        return startFiltering;
    }
    private Filter startFiltering = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<bean_fetch_Requests.UserBean> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(exampleListFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (bean_fetch_Requests.UserBean item : exampleListFull) {
                    if (item.getGst().toLowerCase().contains(filterPattern.toLowerCase()) ||item.getFname().toLowerCase().contains(filterPattern.toLowerCase()) ||
                            item.getRname().toLowerCase().contains(filterPattern.toLowerCase())) {
                        filteredList.add(item);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {

            aryList.clear();
            aryList.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };



    static public class InnerClassViewHolder extends RecyclerView.ViewHolder
    {

        TextView rname,fname,gst,city;
        Button doAccept,doDecline,open_chat_box;
        InnerClassViewHolder(View view)
        {
            super(view);
            rname= view.findViewById(R.id.RetailerName_owner_request_result);
            fname= view.findViewById(R.id.firmname_owner_request_result);
            gst= view.findViewById(R.id.gst_owner_request_result);
            city= view.findViewById(R.id.city_owner_request_result);

            doAccept= view.findViewById(R.id.btnAccept_owner_request_result);
            doDecline= view.findViewById(R.id.btnDecline_owner_request_result);
            open_chat_box= view.findViewById(R.id.btnChat_owner_request_result);

        }



    }

}
