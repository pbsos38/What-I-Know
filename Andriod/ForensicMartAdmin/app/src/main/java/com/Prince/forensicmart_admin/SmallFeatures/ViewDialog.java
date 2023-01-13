package com.Prince.forensicmart_admin.SmallFeatures;

import android.app.Activity;
import android.app.Dialog;
import android.view.Window;
import android.widget.ImageView;
import com.Prince.forensicmart_admin.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.google.android.material.snackbar.Snackbar;

public class ViewDialog {

    Activity activity;
    Dialog dialog;
    Snackbar snackbar ;
    public ViewDialog(Activity activity) {
        this.activity = activity;
    }

    public void showDialog() {

        dialog  = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.custom_loading_layout);

        ImageView gifImageView = dialog.findViewById(R.id.custom_loading_imageView);

        GlideDrawableImageViewTarget imageViewTarget = new GlideDrawableImageViewTarget(gifImageView);
        Glide.with(activity)
                .load(R.drawable.loading)
                .placeholder(R.drawable.loading)
                .centerCrop()
                .crossFade()
                .into(imageViewTarget);

        dialog.show();
    }

    public void hideDialog(){
        dialog.dismiss();
    }

    public void  showSnackBar(){
        snackbar=(Snackbar.make(activity.findViewById(android.R.id.content),"Please Wait....",Snackbar.LENGTH_INDEFINITE));
        snackbar.show();
    }
    public void hideSnackBar(){
        snackbar.dismiss();
    }

}
