package com.example.sampe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {

    DataBase dataBase;
    EditText name, age, mobile, addr;
    TextInputLayout namelay,agelay,mobilelay,addrlay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataBase = new DataBase(this);

        name = findViewById(R.id.txtName);
        age = findViewById(R.id.txtAge);
        mobile = findViewById(R.id.txtMobile);
        addr = findViewById(R.id.txtAddr);
        namelay = findViewById(R.id.name);
        agelay = findViewById(R.id.age);
        mobilelay = findViewById(R.id.mobile);
        addrlay = findViewById(R.id.addr);

        mobile.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (mobile.getText().toString().length()<10)
                    mobilelay.setError("Make Sure your Mobile no has 10 Digits.");
                else
                    mobilelay.setErrorEnabled(false);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public void login(View view){

        if (name.getText().toString().trim().isEmpty()) {
            namelay.setError("please enter your Name");
            return ;
        }
        else {
            if (name.getText().toString().length() < 5) {
                namelay.setError("Make sure your name has more than 5 characters");
                return;
            }
        }


        if (age.getText().toString().trim().isEmpty()) {
            agelay.setError("please enter your Age");
            return ;
        }
        if (mobile.getText().toString().trim().isEmpty()) {
            mobilelay.setError("please enter your Mobile no");
            return ;
        }
        else {
            if (mobile.getText().toString().length() <10) {
                mobilelay.setError("Make sure your Mobile no has more than 10 characters");
            }
        }
        if (addr.getText().toString().trim().isEmpty()) {
            addrlay.setError("please enter your Address");
            return ;
        }

        boolean isInserted = dataBase.insertData(name.getText().toString(), age.getText().toString(), mobile.getText().toString(), addr.getText().toString());
        if (isInserted)
            startActivity(new Intent(getBaseContext(), MainActivity2.class));
        else
            Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show();
    }
}