package com.example.chat_system.attemp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.chat_system.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class launcher extends AppCompatActivity {

    EditText uid,pwd;
    Button login;
    FirebaseDatabase fbdb;
    DatabaseReference dbref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);

        //reference
        {
            uid = findViewById(R.id.uid);
            pwd = findViewById(R.id.pwd);
            login = findViewById(R.id.login);
        }

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fbdb = FirebaseDatabase.getInstance();
                //set path as a table name
                dbref = fbdb.getReference("users");

                String suid = uid.getText().toString();
                String spwd = pwd.getText().toString();

                database_helper_signup helper = new database_helper_signup(suid,spwd);
                dbref.child(suid).setValue(helper);
                Toast.makeText(launcher.this, "done", Toast.LENGTH_SHORT).show();

                startActivity(new Intent(getApplicationContext(), all_users.class));


            }
        });

    }
}