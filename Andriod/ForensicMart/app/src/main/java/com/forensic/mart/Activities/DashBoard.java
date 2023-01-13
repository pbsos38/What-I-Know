package com.forensic.mart.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.forensic.mart.Activities.Fr.Books;
import com.forensic.mart.Activities.Fr.Events;
import com.forensic.mart.Activities.Fr.Home;
import com.forensic.mart.Activities.Fr.Papers;
import com.forensic.mart.Activities.Fr.Test;
import com.forensic.mart.Activities.Fr.Vedios;
import com.forensic.mart.BeanFiles.ProfileDataPostBean;
import com.forensic.mart.ChatSystem.ChatList;
import com.forensic.mart.R;
import com.forensic.mart.SmallFeatures.CircleTransform;
import com.forensic.mart.server.Conn;
import com.forensic.mart.server.SetDataToDatabase;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.onesignal.OneSignal;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class DashBoard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener  {

    private static final String ONESIGNAL_APP_ID = "ec86445a-d014-4bae-9c00-c6b95637ccb4";
    private Boolean exit = false;
    private String myEmail,username,userID,usersName;
    private ImageView userImage;
    private TextView newPostText;
    NavigationView navigationView;
    Home home;
    TabLayout tabLayout;
    InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);

        // Reference
        {
            newPostText = findViewById(R.id.dashboard_writeText);
            userImage = findViewById(R.id.dashboard_userImage);
        }
        /*Navigation Bar*/
        {
            Toolbar toolbar = findViewById(R.id.toolbar_dashBoard);
            setSupportActionBar(toolbar);

            DrawerLayout drawer = findViewById(R.id.drawer_layout_dashBoard);
            navigationView = findViewById(R.id.nav_view_dashboard);
            //viewFlipper = navigationView.findViewById(R.id.image_slideShow_nav_header);
            //navigationView.inflateMenu(R.menu.owner_navigation_menu);
            navigationView.bringToFront();
            drawer.setDrawerShadow(R.color.drawer_shadow, GravityCompat.START);
            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            drawer.addDrawerListener(toggle);
            toggle.syncState();
            navigationView.setNavigationItemSelectedListener(this);
        }
        //get intents
        {
            myEmail = getIntent().getStringExtra("email");
            username = getIntent().getStringExtra("username");
            userID = getIntent().getStringExtra("userID");
            usersName = getIntent().getStringExtra("userName");
        }
        // Initialasing add post
        LoadUserpic();
        newPostText.setOnClickListener(v->{
            Intent intent = new Intent(this,CreatePost.class);
            intent.putExtra("userId",userID);
            startActivity(intent);
        });

        // push notifications
        {
            // OneSignal Initialization
//            OneSignal.startInit(this)
//                    .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
//                    .unsubscribeWhenNotificationsAreDisabled(true)
//                    .init();

            // OneSignal Initialization
            OneSignal.initWithContext(this);
            OneSignal.setAppId(ONESIGNAL_APP_ID);
            OneSignal.sendTag("user_id",userID);
        }
        // fragments
        {
            ViewPager viewPager = findViewById(R.id.view_pager);
            tabLayout = findViewById(R.id.tab_layout);

            Bundle bundle=new Bundle();
            bundle.putString("userID", userID);
            bundle.putString("userName", usersName);

            Books books = new Books();
            books.setArguments(bundle);
            Events events = new Events();
            events.setArguments(bundle);
            home = new Home();
            //set Fragment class Arguments
            home.setArguments(bundle);
            Papers papers = new Papers();
            papers.setArguments(bundle);
            Test test = new Test();
            test.setArguments(bundle);
            Vedios vedios = new Vedios();
            vedios.setArguments(bundle);

            tabLayout.setupWithViewPager(viewPager);
            ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), 0);
            viewPagerAdapter.addFragment(home, "Home");
            viewPagerAdapter.addFragment(events, "Events");
            viewPagerAdapter.addFragment(books, "Books");
            viewPagerAdapter.addFragment(vedios, "Videos");
            viewPagerAdapter.addFragment(papers, "Research Paper");
            viewPagerAdapter.addFragment(test, "Practice Test");
            viewPager.setAdapter(viewPagerAdapter);

            Objects.requireNonNull(tabLayout.getTabAt(0)).setIcon(R.drawable.ic_twotone_home_24);
            Objects.requireNonNull(tabLayout.getTabAt(1)).setIcon(R.drawable.ic_twotone_event_24);
            Objects.requireNonNull(tabLayout.getTabAt(2)).setIcon(R.drawable.ic_twotone_menu_book_24);
            Objects.requireNonNull(tabLayout.getTabAt(3)).setIcon(R.drawable.ic_twotone_ondemand_video_24);
            Objects.requireNonNull(tabLayout.getTabAt(4)).setIcon(R.drawable.ic_paper);
            Objects.requireNonNull(tabLayout.getTabAt(5)).setIcon(R.drawable.ic_test);
        }

        {
            //ads
            // interstitial ad used when exiting app
            mInterstitialAd = new InterstitialAd(this);
            mInterstitialAd.setAdUnitId(getString(R.string.admob_interstitial_ad));
            mInterstitialAd.loadAd(new AdRequest.Builder().build());

        }
    }

    private void LoadUserpic() {
        SetDataToDatabase con = Conn.doConnect();
        con.profile_data_posts("post_data", userID, new Callback<ProfileDataPostBean>() {
            @Override
            public void success(ProfileDataPostBean profileDataPostBean, Response response) {
                Picasso.with(DashBoard.this).load("https://www.forensicmart.com/uploads/" + profileDataPostBean.data.get(0).getPicture()).transform(new CircleTransform()).error(R.drawable.noimage).into(userImage);
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }

    public void VisitProfile(View view){
        Intent intent = new Intent(DashBoard.this,UserProfile.class);
        intent.putExtra("username",username);
        startActivity(intent);

    }
    private static class ViewPagerAdapter extends FragmentPagerAdapter {

        private List<Fragment> fragments = new ArrayList<>();
        private List<String> fragmentTitle = new ArrayList<>();

        public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
            super(fm, behavior);
        }

        public void addFragment(Fragment fragment, String title) {
            fragments.add(fragment);
            fragmentTitle.add(title);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        /*@Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return fragmentTitle.get(position);
        }*/

    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.Companies_dashboard_menu:
                Intent intent = new Intent(this, Posts.class);
                intent.putExtra("website", "company");
                startActivity(intent);
                break;
            case R.id.College_dashboard_menu:
                Intent intent2 = new Intent(this, Posts.class);
                intent2.putExtra("website", "college");
                startActivity(intent2);
                break;
            /*case R.id.Publications_dashboard_menu:
                Intent intent3 = new Intent(this, Posts.class);
                intent3.putExtra("website", "publication");
                startActivity(intent3);
                break;*/
            case R.id.Societies_dashboard_menu:
                Intent intent4 = new Intent(this, Posts.class);
                intent4.putExtra("website", "society");
                startActivity(intent4);
                break;
            /*case R.id.Facebook_dashboard_menu:
                Intent intent5 = new Intent(this, Posts.class);
                intent5.putExtra("website", "facebook");
                startActivity(intent5);
                break;*/
            case R.id.YouTube_dashboard_menu:
                Intent intent6 = new Intent(this, Posts.class);
                intent6.putExtra("website", "youtube");
                startActivity(intent6);
                break;
            /*case R.id.FSL_dashboard_menu:
                Intent intent7 = new Intent(this, Posts.class);
                intent7.putExtra("website", "fsl");
                startActivity(intent7);
                break;*/
            case R.id.Courses_dashboard_menu:
                Intent intent8 = new Intent(this, Courses.class);
                startActivity(intent8);
                break;
            /*case R.id.Competition_dashboard_menu:
                Intent intent9 = new Intent(this, Posts.class);
                intent9.putExtra("website", "competition");
                startActivity(intent9);
                break;*/
            case R.id.Chats_dashboard_menu:
                Intent intent10 = new Intent(this, ChatList.class);
                intent10.putExtra("userID",userID);
                intent10.putExtra("userName",usersName);
                startActivity(intent10);
                break;
            /*case R.id.Conference_dashboard_menu:
                break;*/
        }

        return false;
    }

    @Override
    public void onBackPressed() {


        if (exit) {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish(); // finish activity
            if (mInterstitialAd.isLoaded()) {
                mInterstitialAd.show();
                mInterstitialAd.setAdListener(new AdListener() {
                    @Override
                    public void onAdFailedToLoad(LoadAdError adError) {
                        Intent intents = new Intent(Intent.ACTION_MAIN);
                        intents.addCategory(Intent.CATEGORY_HOME);
                        intents.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intents);
                    }

                    @Override
                    public void onAdClosed() {
                        Intent intents = new Intent(Intent.ACTION_MAIN);
                        intents.addCategory(Intent.CATEGORY_HOME);
                        intents.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intents);
                    }
                });
            } else {
                Log.d("TAG", "The interstitial wasn't loaded yet.");
            }
        } else {
            Objects.requireNonNull(tabLayout.getTabAt(0)).select();
            exit = true;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    exit = false;
                }
            }, 3 * 1000);

        }
    }
}