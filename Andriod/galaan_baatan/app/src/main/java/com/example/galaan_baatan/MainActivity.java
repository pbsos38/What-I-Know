package com.example.galaan_baatan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText email,password,name;
    private Button signin, signup,chat;
    FirebaseUser user;
    static String LoggedIn_User_Email,UserID;
    public static FirebaseDatabase mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (mDatabase == null) {
            mDatabase = FirebaseDatabase.getInstance();
            //mDatabase.setPersistenceEnabled(true);
            FirebaseDatabase.getInstance().setPersistenceEnabled(true);// put this line only once throughout the app
        }

        mAuth = FirebaseAuth.getInstance(); // important Call

        signin = (Button)findViewById(R.id.signin);
        signup = (Button)findViewById(R.id.signup);
        email = (EditText)findViewById(R.id.etEmail);
        password = (EditText)findViewById(R.id.etPassword);
        name = (EditText)findViewById(R.id.etName);

        //Check if User is Already LoggedIn
        if(mAuth.getCurrentUser() != null)
        {
            //User NOT logged In
            Intent intent = new Intent(MainActivity.this , Home_page.class);
            finish();
            startActivity(intent);
        }

        /*user = mAuth.getCurrentUser();
        Log.d("LOGGED", "user: " + user);*/

        /*//Setting the tags for Current User.
        if (user != null) {
            LoggedIn_User_Email =user.getEmail();
        }
*/
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String getemail = email.getText().toString().trim();
                String getepassword = password.getText().toString().trim();

                if(getemail.isEmpty()){
                    Toast.makeText(MainActivity.this, "Type Email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(getepassword.isEmpty()){
                    Toast.makeText(MainActivity.this, "Type Password", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(getepassword.length()<6){
                    Toast.makeText(MainActivity.this, "Password should minimum 6 Character", Toast.LENGTH_SHORT).show();
                    return;
                }
                callsignup(getemail,getepassword);

            }
        });


        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getemail = email.getText().toString().trim();
                String getepassword = password.getText().toString().trim();

                /*if(getemail.isEmpty()){
                    Toast.makeText(MainActivity.this, "Type Email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(getepassword.isEmpty()){
                    Toast.makeText(MainActivity.this, "Type Password", Toast.LENGTH_SHORT).show();
                    return;
                }*/
                callsignin(getemail,getepassword);

            }
        });


        /*chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });*/


    }

    //Create Account
    private void callsignup(String email,String password) {

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                // If sign in fails, display a message to the user. If sign in succeeds
                // the auth state listener will be notified and logic to handle the
                // signed in user can be handled in the listener.
                if (!task.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "Signed up Failed", Toast.LENGTH_SHORT).show();
                } else {
                    FirebaseUser user = mAuth.getCurrentUser();
                    UserID = user.getEmail().replace("@", "").replace(".", "");
                    DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();

                    DatabaseReference ref1 = mRootRef.child("users").child(UserID);


                    ref1.child("Name").setValue(name.getText().toString().trim());
                    ref1.child("Image_Url").setValue("Null");
                    ref1.child("Email").setValue(user.getEmail());

                                                //or
                    {/*
                                        HashMap<String,String> hashMap = new HashMap<>();
                                        hashMap.put("id",UserID);
                                        hashMap.put("Email",user.getEmail());
                                        hashMap.put("Image_url","Null");

                                        ref1.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if(task.isSuccessful())
                                                {
                                                   Intent intent = new Intent(MainActivity.class,xyz.class);
                                                   intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                                   startActivity(intent);
                                                   finish();
                                                }
                                            }
                                        });*/
                    }
                    Toast.makeText(MainActivity.this, "Account Created ", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    //Now start Sign In Process
    //SignIn Process
    private void callsignin(String email,String password) {

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Log.w("TESTING", "signInWithEmail:failed", task.getException());
                            Toast.makeText(MainActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Intent i = new Intent(MainActivity.this, Home_page.class);
                            finish();
                            startActivity(i);
                        }
                    }
                });

    }

}