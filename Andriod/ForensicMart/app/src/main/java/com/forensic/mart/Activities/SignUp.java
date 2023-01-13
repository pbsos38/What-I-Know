package com.forensic.mart.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.MenuItem;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.forensic.mart.Activities.menuActivities.Aboutus;
import com.forensic.mart.Activities.menuActivities.ContactUs;
import com.forensic.mart.Activities.menuActivities.FAQs;
import com.forensic.mart.Activities.menuActivities.PrivacyPolicy;
import com.forensic.mart.Activities.menuActivities.Services;
import com.forensic.mart.Activities.menuActivities.TandC;
import com.forensic.mart.Activities.menuActivities.events;
import com.forensic.mart.R;
import com.forensic.mart.SmallFeatures.Country_id_lib;
import com.forensic.mart.SmallFeatures.ViewDialog;
import com.forensic.mart.server.Conn;
import com.forensic.mart.server.ServerResponse;
import com.forensic.mart.server.SetDataToDatabase;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.hbb20.CountryCodePicker;

import java.util.Random;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class SignUp extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    TextInputLayout name_lay, username_lay, mob_lay, email_lay, password_lay, repeat_password_lay;
    TextInputEditText name, username, mob, email, password, repeat_password;
    ViewDialog viewDialog;
    CountryCodePicker countryCodePicker;
    AutoCompleteTextView editTextFilledExposedDropdown;
    int country_id;
    private DrawerLayout drawer;
    NavigationView navigationView;
    ViewFlipper viewFlipper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //reference
        {
            name_lay = findViewById(R.id.name_login_layout);
            username_lay = findViewById(R.id.username_login_layout);
            mob_lay = findViewById(R.id.mobile_login_layout);
           // gender_lay = findViewById(R.id.gender_login_layout);
            email_lay = findViewById(R.id.email_login_layout);
            //country_lay = findViewById(R.id.country_login_layout);
            password_lay = findViewById(R.id.password_login_layout);
            repeat_password_lay = findViewById(R.id.repeatpassword_login_layout);
            name = findViewById(R.id.name_login_editabble);
            username = findViewById(R.id.username_login_editabble);
            mob = findViewById(R.id.mobile_login_editabble);
            //gender = findViewById(R.id.gender_login_editabble);
            email = findViewById(R.id.email_login_editabble);
            //country = findViewById(R.id.country_login_editabble);
            password = findViewById(R.id.password_login_editabble);
            repeat_password = findViewById(R.id.repeatpassword_login_editabble);
            viewDialog = new ViewDialog(this);
            countryCodePicker = findViewById(R.id.country_code_picker);
            //viewFlipper = findViewById(R.id.image_slideShow_nav_header);
        }
       /* //setting gender menu
        {
            final String[] COUNTRIES = new String[] {"Select", "Male", "Female", "Other"};

            ArrayAdapter<String> adapter =
                    new ArrayAdapter<String>(
                            getBaseContext(),
                            R.layout.dropdown_menu_popup_item,
                            COUNTRIES);

            editTextFilledExposedDropdown = findViewById(R.id.filled_exposed_dropdown);
            editTextFilledExposedDropdown.setAdapter(adapter);

            editTextFilledExposedDropdown.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    gender = parent.getItemAtPosition(position).toString();
                }
            });

        }*/
        /* Navigation Bar*/
        {
            Toolbar toolbar = findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);

            drawer = findViewById(R.id.drawer_layout);
            navigationView = findViewById(R.id.nav_view);
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
        Country_id_lib country_id_lib = new Country_id_lib();
        countryCodePicker.setOnCountryChangeListener(() -> country_id = country_id_lib.gen(countryCodePicker.getSelectedCountryNameCode()));
    }

    public void signup_login(View view) {

        if (check_EmptyNess()) {
            viewDialog.showDialog();
            //&country_id=99&dial_code=91
            SetDataToDatabase con = Conn.doConnect();
            Random rand = new Random();
            int x = rand.nextInt(500);
            con.signup(x,name.getText().toString(), username.getText().toString(), email.getText().toString(), mob.getText().toString(),
                    password.getText().toString(), country_id,countryCodePicker.getSelectedCountryCode(), new Callback<ServerResponse>() {
                        @Override
                        public void success(ServerResponse serverResponse, Response response) {
                            viewDialog.hideDialog();
                            serverRespone(serverResponse.msg);
                            if (serverResponse.msg.equals("User Registered.")){
                                Intent intent = new Intent(SignUp.this, unverified_mobile.class);
                                intent.putExtra("email",email.getText().toString());
                                intent.putExtra("country_id",country_id);
                                intent.putExtra("phone", countryCodePicker.getSelectedCountryCode()+mob.getText().toString());
                                startActivity(intent);
                            }
                            else
                                Toast.makeText(SignUp.this, ""+serverResponse.msg, Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void failure(RetrofitError error) {
                            if (error.getKind().equals(RetrofitError.Kind.NETWORK)) {
                                Toast.makeText(SignUp.this, "No Internet connection found.", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(SignUp.this, error.toString(), Toast.LENGTH_SHORT).show();
                            }
                            viewDialog.hideDialog();
                        }
                    }
            );
        }
    }

    private void serverRespone(String string){
        switch (string) {
            case "User name already registered.":
                username_lay.setHelperTextEnabled(false);
                username_lay.setErrorEnabled(true);
                username_lay.setError(string);
                username.requestFocus();
                break;
            case "Sorry! Similar Email is already registered":
                email_lay.setHelperTextEnabled(false);
                email_lay.setErrorEnabled(true);
                email_lay.setError(string);
                email.requestFocus();
                break;
            case "Phone No. already registered.":
                mob_lay.setHelperTextEnabled(false);
                mob_lay.setErrorEnabled(true);
                mob_lay.setError("Phone Number Already Registered.\n Please Try another one");
                mob.requestFocus();
                break;
        }
    }
    private boolean check_EmptyNess() {
        if (name.getText().toString().trim().isEmpty()) {
            name_lay.setHelperTextEnabled(false);
            name_lay.setErrorEnabled(true);
            name_lay.setError("Please fill your Name");
            name.requestFocus();
            return false;
        }
        if (username.getText().toString().trim().isEmpty()) {
            username_lay.setHelperTextEnabled(false);
            username_lay.setErrorEnabled(true);
            username_lay.setError("Please fill your UserName");
            username.requestFocus();
            return false;
        }
        if (mob.getText().toString().trim().isEmpty()) {
            mob_lay.setHelperTextEnabled(false);
            mob_lay.setErrorEnabled(true);
            mob_lay.setError("Please fill your Mobile Number");
            mob.requestFocus();
            return false;
        }
        if (email.getText().toString().trim().isEmpty()) {
            email_lay.setHelperTextEnabled(false);
            email_lay.setErrorEnabled(true);
            email_lay.setError("Please fill your Email");
            email.requestFocus();
            return false;
        }

      /*  if (gender.equals("Select")){
            Toast.makeText(this, "Please select Your gender", Toast.LENGTH_SHORT).show();
            return false;
        }*/

       /* if (gender.getText().toString().trim().isEmpty()) {
            gender_lay.setHelperTextEnabled(false);
            gender_lay.setErrorEnabled(true);
            gender_lay.setError("Please fill your gender");
            gender.requestFocus();
            return false;
        }*/
        /*if (country.getText().toString().trim().isEmpty()) {
            country_lay.setHelperTextEnabled(false);
            country_lay.setErrorEnabled(true);
            country_lay.setError("Please fill your Country");
            country.requestFocus();
            return false;
        }*/
        if (password.getText().toString().trim().isEmpty()) {
            password_lay.setHelperTextEnabled(false);
            password_lay.setErrorEnabled(true);
            password_lay.setError("Please fill your Password");
            password.requestFocus();
            return false;
        }
        if (repeat_password.getText().toString().trim().isEmpty()) {
            repeat_password_lay.setHelperTextEnabled(false);
            repeat_password_lay.setErrorEnabled(true);
            repeat_password_lay.setError("Please fill your Password again.");
            repeat_password.requestFocus();
            return false;
        }
        if (!password.getText().toString().equals(repeat_password.getText().toString())) {
            Toast.makeText(this, "Your passwords don't match.\nPlease recheck.", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    public void show_password(View view) {
        password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
    }

    public void show_repeat_password(View view) {
        repeat_password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
    }

    public void login_text(View view) {
        Intent intent = new Intent(SignUp.this, Login.class);
        startActivity(intent);
    }


   /* public void slideShow_navigation(int image) {
        ImageView imageView = new ImageView(this);
        imageView.setBackgroundResource(image);

        viewFlipper.addView(imageView);
        viewFlipper.setFlipInterval(2000);
        viewFlipper.setAutoStart(true);
        viewFlipper.setInAnimation(this, android.R.anim.slide_in_left);
        viewFlipper.setOutAnimation(this, android.R.anim.slide_out_right);
    }*/
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