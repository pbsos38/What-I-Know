package com.example.training;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
// to create bottom sheet just create a resource file and java file separately
public class BottomSheetDialog extends BottomSheetDialogFragment {
   @BindView(R.id.display_age)
    TextView agee;
   @BindView(R.id.display_name)TextView namee;
    public String name,age,desription;
    Activity activityy;
    int image;

    public BottomSheetDialog(Activity activity,String s, String s1,int i1,String s2) {
        this.name=s;
        this.age=s1;
        this.activityy = activity;
        this.image = i1;
        this.desription = s2;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.bottom_sheet_view,container,false);
        ButterKnife.bind(this,v);
        //TextView namee = v.findViewById(R.id.display_name);
        //agee = v.findViewById(R.id.display_age);
        namee.setText(name);
        ImageView images =v.findViewById(R.id.image_bottomSheet);
        TextView description = v.findViewById(R.id.display_description);
        Picasso.get().load(image).transform(new CircleTransform()).into(images);
        description.setText(desription);
        if(age!=null) {
            agee.setText(age);
        }
        ImageView close = v.findViewById(R.id.close);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        return v;
    }

    /*public interface BottomSheetListner{
        void onButtonClicked(String text);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {

            mlistner = (BottomSheetListner) context;
        }
        catch (ClassCastException e)
            {throw new ClassCastException(context.toString() + "anything");}
    }*/
}
