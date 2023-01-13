package com.example.splash_animations;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import maes.tech.intentanim.CustomIntent;

public class splash_screen extends AppCompatActivity {

    ImageView image;
    TextView logo,slogan;
    Animation topAnim,bottomAnim,left_right;
    private static  int SPLASH_SCREEN = 8000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);

        image = findViewById(R.id.imageView);
        logo = findViewById(R.id.textView);
        slogan = findViewById(R.id.textView2);

        //Animations
        topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);
        left_right = AnimationUtils.loadAnimation(this, R.anim.left_to_right);

//Set animation to elements
        //image.setAnimation(left_right);
        //logo.setAnimation(bottomAnim);
        //slogan.setAnimation(bottomAnim);


        //Calling New Activity after SPLASH_SCREEN seconds 1s = 1000
        new Handler().postDelayed(new Runnable() {
                                      @Override
                                      public void run() {
                                          Intent intent = new Intent(splash_screen.this, splash_screen.class);
                                          startActivity(intent);
                                          CustomIntent.customType(splash_screen.this,"bottom-to-up");
                                          //overridePendingTransition(R.anim.bottom_animation,R.anim.bottom_animation);

                                          /**
                                           * these are the only possibilities for intent animations
                                           *left-to-right
                                           *right-to-left
                                           *bottom-to-up
                                           *up-to-bottom
                                           *fadein-to-fadeout
                                           *rotateout-to-rotatei
                                           **/

                                          finish();
                                      }
                                  }, //Pass time here
                SPLASH_SCREEN);
    }
}
