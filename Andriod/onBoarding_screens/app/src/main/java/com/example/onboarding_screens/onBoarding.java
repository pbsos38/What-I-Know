package com.example.onboarding_screens;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class onBoarding extends AppCompatActivity {

    //Variables
    ViewPager viewPager;
    LinearLayout dotsLayout;
    SliderAdapter sliderAdapter;
    TextView[] dots;
    Button letsGetStarted;
    Animation animation;
    int currentPos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_on_boarding);

        //Hooks
        viewPager = findViewById(R.id.slider);
        dotsLayout = findViewById(R.id.dots);
        letsGetStarted = findViewById(R.id.get_started_btn);

        //Call adapter
        sliderAdapter = new SliderAdapter(this);
        viewPager.setAdapter(sliderAdapter);

        //Dots
        addDots(0);
        viewPager.addOnPageChangeListener(changeListener);


        //Add this code in index file in onCreate method with "SharedPreferences onBoarding;" as a global variable

        //Calling New Activity after SPLASH_SCREEN seconds 1s = 1000
//        new Handler().postDelayed(new Runnable() {
//                                      @Override
//                                      public void run() {
//
//                                          onBoarding = getSharedPreferences(("onBoardingScreen",MODE_PRIVATE);
//                                          boolean isFirstTime = onBoarding.getBoolean("firstTime",true);
//
//                                          if(isFirstTime)
//                                          {
//                                              SharedPreferences.Editor editor = onBoarding.edit();
//                                              editor.putBoolean("firstTime",false);
//
//                                              editor.commit();
//
//                                          }
//                                          else
//                                          {
//                                              Intent intent = new Intent(splash_screen.this, splash_screen.class);
//                                              startActivity(intent);
//                                              finish();
//                                          }
//                                                );
//                                  }, //Pass time here
//                SPLASH_SCREEN);
//
//        }

    }
    public void skip(View view) {
//        startActivity(new Intent(this, UserDashboard.class));
        Toast.makeText(onBoarding.this, "Done", Toast.LENGTH_SHORT).show();
        finish();
    }

    public void next(View view) {
        viewPager.setCurrentItem(currentPos + 1);
    }

    private void addDots(int position) {

        dots = new TextView[4];
        dotsLayout.removeAllViews();

        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);

            dotsLayout.addView(dots[i]);
        }

        if (dots.length > 0) {
            dots[position].setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        }

    }

    ViewPager.OnPageChangeListener changeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addDots(position);
            currentPos = position;

            if (position == 0) {
                letsGetStarted.setVisibility(View.INVISIBLE);
            } else if (position == 1) {
                letsGetStarted.setVisibility(View.INVISIBLE);
            } else if (position == 2) {
                letsGetStarted.setVisibility(View.INVISIBLE);
            } else {
//                animation = AnimationUtils.loadAnimation(this, R.anim.bottom_anim);
//                letsGetStarted.setAnimation(animation);
                Toast.makeText(onBoarding.this, "This is end of onBoarding", Toast.LENGTH_SHORT).show();
                letsGetStarted.setVisibility(View.VISIBLE);
                letsGetStarted.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(onBoarding.this, "Welcome", Toast.LENGTH_SHORT).show();
                    }
                });
            }

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };



}
