package com.example.savepasswordsandautolog_in;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText uid,pwd;
    CheckBox rem;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        uid=findViewById(R.id.uid);
        pwd = findViewById(R.id.pwd);
        rem = findViewById(R.id.chk_remember);
        login =findViewById(R.id.login_btn);

        SharedPreferences preferences= getSharedPreferences("checkbox",MODE_PRIVATE);
        String checkbox = preferences.getString("remember","");
        if(checkbox.equals("true"))
        {

            String extractedUid=preferences.getString("uid","");
            String extractedpwd=preferences.getString("pwd","");
            Intent intent=new Intent(MainActivity.this,Main2Activity.class);
            intent.putExtra("uid",extractedUid);
            intent.putExtra("pwd",extractedpwd);
            startActivity(intent);
        }
        else
            if(checkbox.equals("false"))
            {
                Toast.makeText(this, "Pls sign in", Toast.LENGTH_SHORT).show();
            }

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MainActivity.this,Main2Activity.class);
                startActivity(intent);

            }
        });
        rem.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(buttonView.isChecked()){

                    SharedPreferences preferences=getSharedPreferences("checkbox",MODE_PRIVATE);
                    SharedPreferences.Editor editor=preferences.edit();
                    editor.putString("remember","true");
                    editor.putString("uid",uid.getText().toString());
                    editor.putString("pwd",pwd.getText().toString());
                    editor.apply();
                    Toast.makeText(MainActivity.this, "Ok, We will remember you", Toast.LENGTH_SHORT).show();
                }
                else if(!buttonView.isChecked()){


                    SharedPreferences preferences=getSharedPreferences("checkbox",MODE_PRIVATE);
                    SharedPreferences.Editor editor=preferences.edit();
                    editor.putString("remember","false");
                    editor.apply();
                    Toast.makeText(MainActivity.this, "Please Remember your password.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        if(rem.isChecked())
        {
            Toast.makeText(this, "checked", Toast.LENGTH_SHORT).show();
        }
    }
}
