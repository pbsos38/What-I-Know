package com.example.training;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    card_set_content adapter;
    RecyclerView recycler;
    LinearLayoutManager linearManager;
    String[] names ={"Shikhar Dhawan","Virat Kohli","MS Dhoni","Bhuvneshwar Kumar","K. L. Rahul"};
    String[] description ={"Shikhar Dhawan is an Indian international cricketer. A left-handed opening batsman and an occasional right-arm off break bowler, he plays for Delhi in domestic cricket and Delhi Capitals in the IPL.",
            "Virat Kohli is an Indian cricketer who currently captains the India national team. A right-handed top-order batsman, Kohli is regarded as one of the best batsmen in the world."
            ,"Mahendra Singh Dhoni, is an Indian international cricketer who captained the Indian national team in limited-overs formats from 2007 to 2016 and in Test cricket from 2008 to 2014."
            ,"Bhuvneshwar Kumar Singh is an Indian international cricketer who plays all formats of the game. He plays for Uttar Pradesh in domestic cricket and for Sunrisers Hyderabad in the Indian Premier League"
            ,"Kannur Lokesh Rahul, commonly known as KL Rahul, is an Indian international cricketer who plays for Karnataka in domestic cricket and captains Kings XI Punjab in the Indian Premier League."};
    int[] images ={R.drawable.backshikhar,R.drawable.backkholi,R.drawable.backkholi,R.drawable.backbhuvneshwar,R.drawable.backrahul};
    int[] cricketers ={R.drawable.crik1,R.drawable.crik2,R.drawable.crik3,R.drawable.crik4,R.drawable.crik5};
    String[] mobile ={"9464157600","9464157699 ","9464157626","9464157679","9464477898"};
    String[] age ={"34","31","39","30","28"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        recycler=(RecyclerView)findViewById(R.id.main_recycler);
        linearManager=new LinearLayoutManager(this);
        recycler.setLayoutManager(linearManager);

        adapter=new card_set_content(names,mobile,images,cricketers,age,description,MainActivity.this);
        recycler.setAdapter(adapter);
    }


}