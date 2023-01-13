package com.example.training;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.squareup.picasso.Picasso;


class card_set_content extends RecyclerView.Adapter<card_set_content.InnerClassViewHolder> {

    String[] namee;
    String[] mobilee;
    int[] imagess;
    int[] cricketerss;
    static Context ctx;
    String[] agee;
    String[] desription;

    public card_set_content(String[] name,String[] mobile,int[] images,int[] cricketers,String[] age,String[] desriptions, MainActivity ct) {
        namee=name;
        mobilee=mobile;
        imagess=images;
        cricketerss=cricketers;
        agee=age;
        desription=desriptions;
        ctx=ct;
    }


    @NonNull
    @Override
    public InnerClassViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(ctx);
        View view= inflater.inflate(R.layout.single_item,parent,false);
        card_set_content.InnerClassViewHolder itemView=new card_set_content.InnerClassViewHolder(view);
        return itemView;
    }

    @Override
    public void onBindViewHolder(@NonNull final InnerClassViewHolder holder, final int position) {
    holder.imageView.setImageResource(imagess[position]);
        Picasso.get().load(cricketerss[position]).transform(new CircleTransform()).into(holder.personality);
    holder.nameView.setText(namee[position]);
    holder.callBtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Intent it=new Intent();
            it.setAction(Intent.ACTION_DIAL);
            it.setData(Uri.parse("tel:"+mobilee[position]));
            ctx.startActivity(it);
        }
    });

    holder.single_item_card.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            BottomSheetDialog bottomSheetDialog = new BottomSheetDialog((Activity)ctx,namee[position],agee[position],cricketerss[position],desription[position]);
            bottomSheetDialog.show(((FragmentActivity)ctx).getSupportFragmentManager(),"bottomSheetDialog");
        }
    });
    }


    @Override
    public int getItemCount() {
        return namee.length;
    }

    public class InnerClassViewHolder  extends RecyclerView.ViewHolder{
        public BottomSheetBehavior sheetBehavior;
        //LinearLayout imageView;
        ImageView imageView,personality;
        TextView nameView;
        Button callBtn,mailBtn;
        private LinearLayout bottom_sheet;
        CardView single_item_card;
        public InnerClassViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            personality = itemView.findViewById(R.id.person_image);
            nameView  =itemView.findViewById(R.id.name);
            callBtn  =(Button)itemView.findViewById(R.id.call_btn);
            single_item_card  =itemView.findViewById(R.id.single_item_card);
  //          bottom_sheet = itemView.findViewById(R.id.bottom_sheet);
//            sheetBehavior = BottomSheetBehavior.from(bottom_sheet);

        }
    }
}
