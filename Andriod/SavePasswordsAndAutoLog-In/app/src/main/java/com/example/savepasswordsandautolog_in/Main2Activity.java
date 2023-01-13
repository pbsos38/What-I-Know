package com.example.savepasswordsandautolog_in;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    EditText uid2,pwd2;
    Button loggedin,logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        uid2=findViewById(R.id.uid2);
        pwd2=findViewById(R.id.pwd2);
        loggedin=findViewById(R.id.login_btn2);
        logout=findViewById(R.id.logout_btn);
        String sessionId = getIntent().getStringExtra("uid");
        //Toast.makeText(this, sessionId, Toast.LENGTH_SHORT).show();
        uid2.setText(sessionId);

        String sessionpwd = getIntent().getStringExtra("uid");
        //Toast.makeText(this, sessionpwd, Toast.LENGTH_SHORT).show();
        pwd2.setText(sessionpwd);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences=getSharedPreferences("checkbox",MODE_PRIVATE);
                SharedPreferences.Editor editor=preferences.edit();
                editor.putString("remember","false");
                editor.apply();
                finish();
            }
        });
    }
}
