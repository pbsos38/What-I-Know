package com.Prince.forensictraining;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class New_login extends AppCompatActivity {

    TextView login_text;
    TextInputLayout uid_layout, pwd_layout;
    TextInputEditText uid_editText, pwd_editText;
    CheckBox rememberMe;
    Button sign_up;
    DataBaseHelper mydb;
    private String suid, spwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_login);

        //reference variables
        {
            //text views
            login_text = findViewById(R.id.justtext_login_dashboard);
            //sign_up_text = findViewById(R.id.justtext_signup_dashboard);

            //text input layouts
            uid_layout = findViewById(R.id.uid_dashboard);
            pwd_layout = findViewById(R.id.pwd_dashboard);

            //text input layouts editText
            uid_editText = findViewById(R.id.uid_dashboard_editable);
            pwd_editText = findViewById(R.id.pwd_dashboard_editable);

            //checkbox
            rememberMe = findViewById(R.id.chkRemember_dashboard);
            //buttons
            sign_up = findViewById(R.id.btnSignUp_for_customer_dashboard);

            //others
            mydb = new DataBaseHelper(this);


        }


        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CheckUser();
            }
        });
    }

    private void CheckUser() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("users");
        suid = uid_editText.getText().toString();
        if (suid.isEmpty()) {
            uid_layout.setHelperTextEnabled(false);
            uid_layout.setErrorEnabled(true);
            uid_layout.setError("Please fill your UserName");
            return;
        }

        reference.child(suid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (!snapshot.exists()) {
                    uid_layout.setHelperTextEnabled(false);
                    uid_layout.setErrorEnabled(true);
                    uid_layout.setError("No such User Found");
                    uid_editText.requestFocus();
                }
                else {
                    uid_layout.setErrorEnabled(false);
                    uid_layout.setHelperTextEnabled(true);
                    login(suid);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void login(final String suid){
        final DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("users").child(suid);
        spwd = pwd_editText.getText().toString();
        if (spwd.isEmpty()) {
            pwd_layout.setHelperTextEnabled(false);
            pwd_layout.setErrorEnabled(true);
            pwd_layout.setError("Please fill your Password");
            return;


        }
        if (rememberMe.isChecked()) {
            saveinfo(suid, spwd);
            //Toast.makeText(login_dashboard.this, "Ok, we will remember for you", Toast.LENGTH_SHORT).show();
        }


        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (spwd.equals(snapshot.child("pwd").getValue().toString()))
                {
                    if (snapshot.child("status").getValue().toString().equals("off")) {
                        Intent intent = new Intent(New_login.this, MainActivity.class);
                        intent.putExtra("uid",suid);
                        startActivity(intent);
                        finish();

                    }else
                    {
                        Intent intent = new Intent(New_login.this,AlredyLoged.class);
                        intent.putExtra("uid",suid);
                        startActivity(intent);
                    }
                }
                else
                    Toast.makeText(New_login.this, "Entered Password is wrong", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    public void saveinfo(@NonNull String suid, @NonNull String spwd) {

        int no_ofEntries = mydb.getProfilesCount();
        if (no_ofEntries == 0) {
            //Toast.makeText(this, String.valueOf(no_ofEntries), Toast.LENGTH_SHORT).show();
            boolean isInserted = mydb.insertData(suid, spwd);
            if (isInserted) {
                //Toast.makeText(login_dashboard.this, "Remembered", Toast.LENGTH_SHORT).show();
            } else
                Toast.makeText(New_login.this, "Some error occurred while saving password", Toast.LENGTH_SHORT).show();
        } else if (no_ofEntries >= 1) {
            boolean deletedRows = mydb.deleteData();
            if (deletedRows) {
                boolean isInserted = mydb.insertData(suid, spwd);
                if (isInserted) {
                    //Toast.makeText(login_dashboard.this, "Remembered", Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(New_login.this, "Some error occurred while saving password", Toast.LENGTH_SHORT).show();
            } else
                Toast.makeText(New_login.this, "Some error occurred while saving password", Toast.LENGTH_SHORT).show();
        }
    }


}