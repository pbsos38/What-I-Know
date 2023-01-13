package com.example.push_notification;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.onesignal.OneSignal;

public class secondary_activity extends AppCompatActivity {
    TextView displayemail;
    String email;
    Button send,logout;
    push_notification push;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondary_activity);
        OneSignal.startInit(this)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .unsubscribeWhenNotificationsAreDisabled(true)
                .init();

         email = getIntent().getStringExtra("email");
         send = findViewById(R.id.sendnotification);
         logout = findViewById(R.id.logout);
        displayemail = findViewById(R.id.displayemail);
        displayemail.setText(email);
        OneSignal.sendTag("email",email);

        push = new push_notification(this);


        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                push.sendNotification(email,getString(R.string.app_name));
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                OneSignal.setSubscription(false);
                startActivity(new Intent(secondary_activity.this , MainActivity.class));

            }
        });




    }



}