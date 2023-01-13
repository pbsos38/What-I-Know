package com.example.ads.native2;


import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ads.R;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.formats.UnifiedNativeAd;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    card_set_content adapter;
    RecyclerView recycler;
    LinearLayoutManager linearManager;
    ArrayList<String> names;
    public static final int adPos = 1;
    AdLoader adLoader;
    ArrayList<UnifiedNativeAd> unifiedNativeAds = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        recycler=(RecyclerView)findViewById(R.id.main_recycler);
        linearManager=new LinearLayoutManager(this);
        recycler.setLayoutManager(linearManager);

        names =new ArrayList<>();
        names.add("Shikhar Dhawan");
        names.add("Virat Kohli");
        names.add("MS Dhoni");
        names.add("Bhuvneshwar Kumar");
        names.add("K. L. Rahul");



//        MobileAds.initialize(this,getResources().getString(R.string.admob_app_id));
       /* int x=0;
        for (int c=0;c<names.size();c++){
            if (x!=0){
                names.add(c,"ad");
                x=0;
            }
            else
                if (x==0){
                    x=1;
                }
        }
*/
        adapter=new card_set_content(names,MainActivity.this);
        recycler.setAdapter(adapter);
        Log.d("TAG", ""+names);




    }




}