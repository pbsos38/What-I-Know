package com.forensic.mart.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import com.forensic.mart.Activities.menuActivities.Aboutus;
import com.forensic.mart.Activities.menuActivities.ContactUs;
import com.forensic.mart.Activities.menuActivities.FAQs;
import com.forensic.mart.Activities.menuActivities.PrivacyPolicy;
import com.forensic.mart.Activities.menuActivities.Services;
import com.forensic.mart.Activities.menuActivities.TandC;
import com.forensic.mart.Activities.menuActivities.events;
import com.forensic.mart.BeanFiles.Profile_data_bean;
import com.forensic.mart.LocalDatabase.DataBaseHelper;
import com.forensic.mart.R;
import com.forensic.mart.SmallFeatures.ViewDialog;
import com.forensic.mart.server.Conn;
import com.forensic.mart.server.ServerResponse;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.forensic.mart.server.SetDataToDatabase;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class Login extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    TextInputEditText username, password;
    TextInputLayout username_lay, password_lay;
    CheckBox chk_rem;
    ViewDialog viewDialog;
    DataBaseHelper mydb;
    private DrawerLayout drawer;
    NavigationView navigationView;
    //ViewFlipper viewFlipper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        //reference
        {
            username = findViewById(R.id.username_login2_editabble);
            password = findViewById(R.id.Password_login2_editabble);
            username_lay = findViewById(R.id.username_login2_layout);
            password_lay = findViewById(R.id.password_login2_layout);
            chk_rem = findViewById(R.id.chkRemember_dashboard);
            viewDialog = new ViewDialog(this);
            mydb = new DataBaseHelper(this);
        }

        /* Navigation Bar*/
        {
            Toolbar toolbar = findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);

            drawer = findViewById(R.id.drawer_layout);
            navigationView = findViewById(R.id.nav_view);
            //viewFlipper = navigationView.findViewById(R.id.image_slideShow_nav_header);
            //navigationView.inflateMenu(R.menu.owner_navigation_menu);
            navigationView.bringToFront();
            drawer.setDrawerShadow(R.color.drawer_shadow, GravityCompat.START);
            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            drawer.addDrawerListener(toggle);
            toggle.syncState();
            navigationView.setNavigationItemSelectedListener(this);
            /*int events[] = {R.drawable.eventone, R.drawable.eventtwo, R.drawable.eventthree, R.drawable.eventfour};
            for (int event : events)
                slideShow_navigation(event);*/
        }
    }

    public void login_login2(View view) {
        if (check_EmptyNess()) {

            viewDialog.showDialog();
            SetDataToDatabase con = Conn.doConnect();
            con.login(username.getText().toString(), password.getText().toString(), new Callback<ServerResponse>() {
                @Override
                public void success(ServerResponse serverResponse, Response response) {
                    viewDialog.hideDialog();
                    if (serverResponse.msg.equals("Welcome")) {
                        if (chk_rem.isChecked()) {
                            saveinfo(username.getText().toString(), password.getText().toString());
                        }
                        fetchdata(serverResponse.msg);
                    } else if (serverResponse.msg.equals("unverified")) {
                        fetchdata(serverResponse.msg);
                    } else
                        Toast.makeText(Login.this, "" + serverResponse.msg, Toast.LENGTH_SHORT).show();
                }

                @Override
                public void failure(RetrofitError error) {
                    viewDialog.hideDialog();
                    if (error.getKind().equals(RetrofitError.Kind.NETWORK)) {
                        Toast.makeText(Login.this, "No Internet connection found.", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(Login.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void fetchdata(String string) {
        SetDataToDatabase con = Conn.doConnect();
        con.fetchData(username.getText().toString(),"main", new Callback<Profile_data_bean>() {
            @Override
            public void success(Profile_data_bean profile_data_bean, Response response) {

                if (string.equals("Welcome")) {
                    if (profile_data_bean.data.get(0).getPhone_verify().equals("unverified")) {
                        Intent intent = new Intent(Login.this, unverified_mobile.class);
                        intent.putExtra("phone", profile_data_bean.data.get(0).getPhone());
                        intent.putExtra("username", username.getText().toString());
                        intent.putExtra("userID", profile_data_bean.data.get(0).getId());
                        mydb.updateUserId(profile_data_bean.data.get(0).getId(),profile_data_bean.data.get(0).getUsername());
                        startActivity(intent);

                    } else {
                        Intent intent = new Intent(Login.this, DashBoard.class);
                        intent.putExtra("email", profile_data_bean.data.get(0).getEmail());
                        intent.putExtra("phoneVerificationStatus", profile_data_bean.getPhone_verify());
                        intent.putExtra("userID", profile_data_bean.data.get(0).getId());
                        intent.putExtra("userName", profile_data_bean.data.get(0).getName());
                        if(mydb.updateUserId(profile_data_bean.data.get(0).getId(),profile_data_bean.data.get(0).getUsername())){
                            Log.d("------","Updated");
                        }
                        else
                            Log.d("------","Update failed");

                        startActivity(intent);
                    }
                } else if (string.equals("unverified")) {
                    Intent intent = new Intent(Login.this, otpInput.class);
                    intent.putExtra("email", profile_data_bean.getEmail());
                    mydb.updateUserId(profile_data_bean.data.get(0).getId(),profile_data_bean.data.get(0).getUsername());
                    intent.putExtra("userID", profile_data_bean.data.get(0).getId());
                    startActivity(intent);
                }
            }

            @Override
            public void failure(RetrofitError error) {
                viewDialog.hideDialog();
                if (error.getKind().equals(RetrofitError.Kind.NETWORK)) {
                    Toast.makeText(Login.this, "No Internet connection found.", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(Login.this, error.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean check_EmptyNess() {
        if (username.getText().toString().trim().isEmpty()) {
            username_lay.setHelperTextEnabled(false);
            username_lay.setErrorEnabled(true);
            username_lay.setError("Please fill your Username");
            username.requestFocus();
            return false;
        }
        if (password.getText().toString().trim().isEmpty()) {
            password_lay.setHelperTextEnabled(false);
            password_lay.setErrorEnabled(true);
            password_lay.setError("Please fill your Password");
            password.requestFocus();
            return false;
        }
        return true;
    }

    public void show_repeat_password(View view) {
        password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
    }

    public void signup_text(View view) {
        /*Intent intent = new Intent(Login.this, SignUp.class);
        startActivity(intent);*/
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.forensicmart.com/#"));
        startActivity(browserIntent);

    }

    public void saveinfo(@NonNull String suid, @NonNull String spwd) {

        int no_ofEntries = mydb.getProfilesCount();
        if (no_ofEntries == 0) {
            //Toast.makeText(this, String.valueOf(no_ofEntries), Toast.LENGTH_SHORT).show();
            boolean isInserted = mydb.insertData(suid, spwd,"0");
            if (isInserted) {
                //Toast.makeText(login_dashboard.this, "Remembered", Toast.LENGTH_SHORT).show();
            } else
                Toast.makeText(Login.this, "Some error occurred while saving password", Toast.LENGTH_SHORT).show();
        } else if (no_ofEntries >= 1) {
            boolean deletedRows = mydb.deleteData();
            if (deletedRows) {
                boolean isInserted = mydb.insertData(suid, spwd,"0");
                if (isInserted) {
                    //Toast.makeText(login_dashboard.this, "Remembered", Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(Login.this, "Some error occurred while saving password", Toast.LENGTH_SHORT).show();
            } else
                Toast.makeText(Login.this, "Some error occurred while saving password", Toast.LENGTH_SHORT).show();
        }
    }

    public void forget_text(View view) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.forensicmart.com/#"));
        startActivity(browserIntent);
       /* ForgetPassword bottomSheetDialog = new ForgetPassword(this);
        bottomSheetDialog.show((Login.this).getSupportFragmentManager(),"ForgetPassword");*/

    }

  /*  public void slideShow_navigation(int image) {
        ImageView imageView = new ImageView(this);
        imageView.setBackgroundResource(image);

        viewFlipper.addView(imageView);
        viewFlipper.setFlipInterval(2000);
        viewFlipper.setAutoStart(true);
        viewFlipper.setInAnimation(this, android.R.anim.slide_in_left);
        viewFlipper.setOutAnimation(this, android.R.anim.slide_out_right);
    }
*/
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.unLogged_menu_events:
                Intent intent = new Intent(this, events.class);
                startActivity(intent);
                break;
            case R.id.unLogged_menu_services:
                Intent intent2 = new Intent(this, Services.class);
                startActivity(intent2);
                break;
            case R.id.unLogged_menu_faqs:
                Intent intent3 = new Intent(this, FAQs.class);
                startActivity(intent3);
                break;
            case R.id.unLogged_menu_TAndC:
                Intent intent4 = new Intent(this, TandC.class);
                startActivity(intent4);
                break;
            case R.id.unLogged_menu_privacy:
                Intent intent5 = new Intent(this, PrivacyPolicy.class);
                startActivity(intent5);
                break;
            case R.id.unLogged_menu_contactUs:
                Intent intent6 = new Intent(this, ContactUs.class);
                startActivity(intent6);
                break;
            case R.id.unLogged_menu_aboutUs:
                Intent intent7 = new Intent(this, Aboutus.class);
                startActivity(intent7);
                break;
        }
        return false;
    }
}