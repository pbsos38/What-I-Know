package com.example.videochats;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.hbb20.CountryCodePicker;

import java.util.concurrent.TimeUnit;

public class RegisterActivity extends AppCompatActivity {

    CountryCodePicker ccp;
    EditText codeText, phoneText;
    Button countinueAndNextBtn;
    String checker = "", phoneNumber = "";
    RelativeLayout relativeLayout;

    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    FirebaseAuth mAuth;
    String mVerificationId;
    PhoneAuthProvider.ForceResendingToken mResendToken;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_a_cticcity);
        phoneText = findViewById(R.id.phoneText);
        codeText  = findViewById(R.id.codeText);
        countinueAndNextBtn = findViewById(R.id.continueNextButton);
        relativeLayout = findViewById(R.id.phoneAuth);
        ccp = findViewById(R.id.ccp);
        ccp.registerCarrierNumberEditText(phoneText);

        mAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);
        countinueAndNextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (countinueAndNextBtn.getText().equals("Submit") || checker.equals("Code Sent")){
                    String VerificationCode = codeText.getText().toString();
                    if (VerificationCode.equals("")){
                        Toast.makeText(RegisterActivity.this, "First enter verification code", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        progressDialog.setTitle("Code verification");
                        progressDialog.setMessage("Please Wait");
                        progressDialog.setCanceledOnTouchOutside(false);
                        progressDialog.show();

                        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId,VerificationCode);
                        signInWithPhoneAuthCredential(credential);
                    }
                }
                else
                {
                    phoneNumber = ccp.getFullNumberWithPlus();
                    if (!phoneNumber.equals("")){

                        progressDialog.setTitle("Phone verification");
                        progressDialog.setMessage("Please Wait");
                        progressDialog.setCanceledOnTouchOutside(false);
                        progressDialog.show();

                        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                                phoneNumber,        // Phone number to verify
                                1,                 // Timeout duration
                                TimeUnit.SECONDS,   // Unit of timeout
                                RegisterActivity.this,               // Activity (for callback binding)
                                mCallbacks);        // OnVerificationStateChangedCallbacks
                    }
                    else
                        Toast.makeText(RegisterActivity.this, "enter phone no", Toast.LENGTH_SHORT).show();
                }
            }
        });


        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
            signInWithPhoneAuthCredential(phoneAuthCredential);
            }

            @Override
            public void onVerificationFailed(@NonNull FirebaseException e) {

                Toast.makeText(RegisterActivity.this, "Inalid PHone No", Toast.LENGTH_SHORT).show();
                relativeLayout.setVisibility(View.VISIBLE);
                progressDialog.dismiss();
                countinueAndNextBtn.setText("Continue");
                codeText.setVisibility(View.GONE);
            }

            @Override
            public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                super.onCodeSent(s, forceResendingToken);
                relativeLayout.setVisibility(View.GONE);

                mVerificationId = s;
                mResendToken = forceResendingToken;

                checker = "Code Sent";
                countinueAndNextBtn.setText("Submit");
                codeText.setVisibility(View.VISIBLE);
                progressDialog.dismiss();
                Toast.makeText(RegisterActivity.this, "Code has been resent", Toast.LENGTH_SHORT).show();
            }
        };
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            progressDialog.dismiss();
                            Toast.makeText(RegisterActivity.this, "regiterRation done", Toast.LENGTH_SHORT).show();
                            SendUserToMainACtivity();

                        } else {
                            // Sign in failed, display a message and update the UI
                            progressDialog.dismiss();
                            Log.d("Phone Error",""+task.getException().toString());
                        }
                    }
                });
    }

    private void SendUserToMainACtivity(){
        startActivity(new Intent(RegisterActivity.this,MainActivity.class));
        finish();
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if (firebaseUser!=null){
            startActivity(new Intent(RegisterActivity.this,MainActivity.class));
        }
    }
}