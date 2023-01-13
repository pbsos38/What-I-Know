package com.forensic.mart.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.forensic.mart.Adapter.Education_adapter;
import com.forensic.mart.Adapter.Employement_adapter;
import com.forensic.mart.BeanFiles.Education_bean;
import com.forensic.mart.BeanFiles.Employments_bean;
import com.forensic.mart.BeanFiles.Profile_data_bean;
import com.forensic.mart.R;
import com.forensic.mart.SmallFeatures.CircleTransform;
import com.forensic.mart.SmallFeatures.ViewDialog;
import com.forensic.mart.server.Conn;
import com.forensic.mart.server.SetDataToDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class UserProfile extends AppCompatActivity {

    private TextView username, email_verify, mobile_verify, email, mobile, designation, country;
    private ImageView profilePic, email_verify_pic, mobile_verify_pic;
    private ArrayList<Profile_data_bean> data;
    private String myUsername;
    RecyclerView recyclerView, recyclerView2;
    Education_adapter adapter;
    Employement_adapter adapter2;
    LinearLayoutManager linearManager, linearManager2;
    private ViewDialog viewDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        {
            username = findViewById(R.id.username_user_profile);
            email_verify = findViewById(R.id.email_verify_status_user_profile);
            mobile_verify = findViewById(R.id.mobile_verify_status_user_profile);
            email = findViewById(R.id.email_user_profile);
            mobile = findViewById(R.id.mobile_user_profile);
            designation = findViewById(R.id.designation_user_profile);
            //country = findViewById(R.id.country_user_profile);
            profilePic = findViewById(R.id.profile_image_user_profile);
            email_verify_pic = findViewById(R.id.email_verify_icon_user_profile);
            mobile_verify_pic = findViewById(R.id.mobile_verify_icon_user_profile);

            recyclerView = findViewById(R.id.recycler_education);
            linearManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(linearManager);

            recyclerView2 = findViewById(R.id.recycler_employ);
            linearManager2 = new LinearLayoutManager(this);
            recyclerView2.setLayoutManager(linearManager2);

            viewDialog = new ViewDialog(this);
        }
        //get intents
        {
            myUsername = getIntent().getStringExtra("username");
        }

        fetchData();
    }

    private void fetchData() {
        viewDialog.showDialog();
        SetDataToDatabase con = Conn.doConnect();
        con.fetchData(myUsername, "main", new Callback<Profile_data_bean>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void success(Profile_data_bean profile_data_bean, Response response) {

                viewDialog.hideDialog();
                username.setText(profile_data_bean.data.get(0).getUsername());
                mobile.setText("Mobile:" + profile_data_bean.data.get(0).getPhone());
                designation.setText("Designation: " + profile_data_bean.data.get(0).getDesignation());
                email.setText("Email: " + profile_data_bean.data.get(0).getEmail());
                Picasso.with(UserProfile.this).load("https://www.forensicmart.com/uploads/" + profile_data_bean.data.get(0).getPicture())
                        .transform(new CircleTransform()).fit().error(R.drawable.noimage).into(profilePic);
                if (profile_data_bean.data.get(0).getEmail_verify().equals("1")) {
                    email_verify_pic.setImageResource(R.drawable.verified);
                    email_verify.setText("Verified");
                } else {
                    email_verify_pic.setImageResource(R.drawable.unverified);
                    email_verify.setText("Un-Verified");
                }

                if (profile_data_bean.data.get(0).getPhone_verify().equals("1")) {
                    mobile_verify_pic.setImageResource(R.drawable.verified);
                    mobile_verify.setText("Verified");
                } else {
                    mobile_verify_pic.setImageResource(R.drawable.unverified);
                    mobile_verify.setText("Un-Verified");
                }
                SetDetailData(profile_data_bean.data.get(0).getId());

            }

            @Override
            public void failure(RetrofitError error) {
                if (error.getKind().equals(RetrofitError.Kind.NETWORK)) {
                    Toast.makeText(UserProfile.this, "No Internet connection found.", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(UserProfile.this, error.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void SetDetailData(String id) {
        SetDataToDatabase con = Conn.doConnect();
        con.fetchEduData(id, "edu", new Callback<Education_bean>() {
            @Override
            public void success(Education_bean education_bean, Response response) {
                adapter = new Education_adapter(education_bean.data, UserProfile.this);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void failure(RetrofitError error) {
                if (error.getKind().equals(RetrofitError.Kind.NETWORK)) {
                    Toast.makeText(UserProfile.this, "No Internet connection found.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(UserProfile.this, error.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        con.fetchEmployData(id, "employ", new Callback<Employments_bean>() {
            @Override
            public void success(Employments_bean employments_bean, Response response) {
                adapter2 = new Employement_adapter(employments_bean.data, UserProfile.this);
                recyclerView2.setAdapter(adapter2);
            }

            @Override
            public void failure(RetrofitError error) {
                if (error.getKind().equals(RetrofitError.Kind.NETWORK)) {
                    Toast.makeText(UserProfile.this, "No Internet connection found.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(UserProfile.this, error.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void editProfile(View view) {
        Intent intent =new Intent(this,Edit_user_profile.class);
        intent.putExtra("username",myUsername);
        startActivity(intent);
    }
}