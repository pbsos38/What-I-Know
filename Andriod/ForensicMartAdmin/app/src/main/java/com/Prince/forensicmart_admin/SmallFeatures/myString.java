package com.Prince.forensicmart_admin.SmallFeatures;

import android.app.Activity;
import android.text.Editable;

import androidx.annotation.NonNull;

import java.util.Objects;

public class myString {
    Activity activity;
    public myString(@NonNull Activity activity) {
        this.activity = activity;
    }

    public String no(Editable x){
        String y =Objects.requireNonNull(x).toString();
        return y ;
    }
}
