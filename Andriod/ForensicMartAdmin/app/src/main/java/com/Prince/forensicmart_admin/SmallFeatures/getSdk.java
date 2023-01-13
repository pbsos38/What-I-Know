package com.Prince.forensicmart_admin.SmallFeatures;

import android.os.Build;
import android.util.Log;

public class getSdk {
    //Current Android version data
    public static double currentVersion(){
        double release=Double.parseDouble(Build.VERSION.RELEASE.replaceAll("(\\d+[.]\\d+)(.*)","$1"));
        Log.d("SDK TYPE",""+release);
        String codeName="Unsupported";//below Jelly bean OR above Oreo
        if(release>=4.1 && release<4.4)codeName="Jelly Bean";
        else if(release<5)codeName="Kit Kat";
        else if(release<6)codeName="Lollipop";
        else if(release<7)codeName="Marshmallow";
        else if(release<8)codeName="Nougat";
        else if(release<9)codeName="Oreo";
        return release;
        // return codeName+" v"+release+", API Level: "+Build.VERSION.SDK_INT;
    }
}
