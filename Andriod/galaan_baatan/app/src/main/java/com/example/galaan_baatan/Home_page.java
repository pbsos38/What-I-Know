package com.example.galaan_baatan;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.galaan_baatan.BeanFiles.Messages_bean;
import com.example.galaan_baatan.Fragments.ChatsFragment;
import com.example.galaan_baatan.Fragments.ProfileFragment;
import com.example.galaan_baatan.Fragments.UsersFragment;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Home_page extends AppCompatActivity {

    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

       final TabLayout tabLayout = findViewById(R.id.tab_layout);
        final ViewPager viewPager = findViewById(R.id.view_pager);

        reference = FirebaseDatabase.getInstance().getReference("Chats").child(FirebaseAuth.getInstance().getCurrentUser().getEmail().replace("@","").replace(".",""));
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
            int unread = 0;
            for (DataSnapshot snapshot: dataSnapshot.getChildren() ){
                Messages_bean messages_bean = snapshot.getValue(Messages_bean.class);
                if (messages_bean.getStatus()!=null && messages_bean.getStatus().equals("not_seen"))
                    unread++;
            }
            if (unread == 0)
                viewPagerAdapter.addFragment(new ChatsFragment(),"Chats");
            else
                viewPagerAdapter.addFragment(new ChatsFragment(),"("+unread+") Chats");

            viewPagerAdapter.addFragment(new ProfileFragment(),"Profile");
            viewPagerAdapter.addFragment(new UsersFragment(),"Users");

            viewPager.setAdapter(viewPagerAdapter);
            tabLayout.setupWithViewPager(viewPager);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    public static class ViewPagerAdapter extends FragmentPagerAdapter{

        private ArrayList<Fragment> fragments;
        private ArrayList<String> titles;

        public ViewPagerAdapter(@NonNull FragmentManager fm) {
            super(fm,BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
            this.fragments = new ArrayList<>();
            this.titles = new ArrayList<>();
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
        public void addFragment(Fragment fragment,String title)
        {
            fragments.add(fragment);
            titles.add(title);
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return titles.get(position);
        }
    }

    //online - offline

    private void status(String status){
         reference = FirebaseDatabase.getInstance().getReference("users").child(FirebaseAuth.getInstance().getCurrentUser().getEmail().replace("@","").replace(".",""));

        reference.child("Status").setValue(status);
    }

    @Override
    protected void onResume() {
        super.onResume();
    status("online");
    }

    @Override
    protected void onPause() {
        super.onPause();
        status("offline");
    }
}