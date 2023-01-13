package com.example.firebase_database;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference reference;
    EditText email,phone,pwd;
    Button senddata,fetchData,update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = findViewById(R.id.email);
        pwd = findViewById(R.id.pwd);
        phone = findViewById(R.id.phone);
        senddata = findViewById(R.id.send_data);
        fetchData = findViewById(R.id.fetch_data);
        update = findViewById(R.id.update);
        senddata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database = FirebaseDatabase.getInstance();

                //set path as a table name
                reference = database.getReference("users");

                String email_string = email.getText().toString();
                String phone_string = phone.getText().toString();
                String pwd_string = pwd.getText().toString();
                signup_dataBaseHelper helper = new signup_dataBaseHelper(email_string,phone_string,pwd_string);

                reference.child(email_string).setValue(helper);

                Toast.makeText(MainActivity.this, "sent", Toast.LENGTH_SHORT).show();


            }
        });


        fetchData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String enteredemail = email.getText().toString();
                final String enteredpwd = pwd.getText().toString().trim();

                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
                Query chkuser = reference.orderByChild("email").equalTo(enteredemail);

                chkuser.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        if(dataSnapshot.exists())
                        {
                            String pwdfromdb = dataSnapshot.child(enteredemail).child("pwd").getValue(String.class);

                            if(pwdfromdb != null && pwdfromdb.equals(enteredpwd))
                            {
                                String fetchedphoneno = dataSnapshot.child(enteredemail).child("phone").getValue(String.class);

                                phone.setText(fetchedphoneno);
                            }
                            else
                            {
                                phone.setText("wrong password");
                            }
                        }
                        else
                        {
                            phone.setText("no user");
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String enteredemail = email.getText().toString();
                final String enteredpwd = pwd.getText().toString().trim();
                final String enteredphone = phone.getText().toString().trim();

                database = FirebaseDatabase.getInstance();
                //set path as a table name
                reference = database.getReference("users");

                reference.child("pwd").setValue(enteredpwd);
                reference.child("phone").setValue(enteredphone);
                Toast.makeText(MainActivity.this, "updated", Toast.LENGTH_SHORT).show();
            }
        });
    }
}