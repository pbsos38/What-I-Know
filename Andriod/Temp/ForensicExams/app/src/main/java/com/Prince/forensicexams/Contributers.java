package com.Prince.forensicexams;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.Prince.forensicexams.SmallFeatures.CircleTransform;
import com.squareup.picasso.Picasso;

public class Contributers extends AppCompatActivity {

    ImageView prince,archna_kumari,shivani_image,harshit_bharti_image,jaya_gupta,madhav_goyal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contributers);

        prince = findViewById(R.id.prince_image);
        Picasso.with(this).load(R.drawable.prince_developer).transform(new CircleTransform()).into(prince);

        madhav_goyal = findViewById(R.id.madhav_image);
        Picasso.with(this).load(R.drawable.madhav_goyal).transform(new CircleTransform()).into(madhav_goyal);

        archna_kumari = findViewById(R.id.archna_kumari);
        Picasso.with(this).load(R.drawable.archna_kumari).transform(new CircleTransform()).into(archna_kumari);

        shivani_image = findViewById(R.id.shivani_image);
        Picasso.with(this).load(R.drawable.shivani_shailesh_khatri).transform(new CircleTransform()).into(shivani_image);

        harshit_bharti_image = findViewById(R.id.harshit_bharti_image);
        Picasso.with(this).load(R.drawable.harshit_bharti).transform(new CircleTransform()).into(harshit_bharti_image);

        jaya_gupta = findViewById(R.id.jaya_gupta_image);
        Picasso.with(this).load(R.drawable.jaya_gupta).transform(new CircleTransform()).into(jaya_gupta);

    }
}
