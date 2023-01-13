package com.forensic.mart.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.forensic.mart.R;
import com.forensic.mart.SmallFeatures.ViewDialog;
import com.forensic.mart.server.Connection;
import com.forensic.mart.server.ServerResponse;
import com.forensic.mart.server.SetDataToDatabase;
import com.chaos.view.PinView;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class otpInput extends AppCompatActivity {

    String email;
    ViewDialog viewDialog;
    PinView otp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_input);
        email = getIntent().getStringExtra("email");
        viewDialog = new ViewDialog(this);
        otp = findViewById(R.id.pin_view);
    }

    public void callNextScreenFromOTP(View view) {
        viewDialog.showDialog();
        SetDataToDatabase con = Connection.doConnect();
        if (email != null) {
            con.updateValidity(otp.getText().toString(), email, new Callback<ServerResponse>() {
                @Override
                public void success(ServerResponse serverResponse, Response response) {
                    viewDialog.hideDialog();
                    if (serverResponse.msg.equals("otp matched")) {
                        startActivity(new Intent(otpInput.this, Login.class));
                    } else if (serverResponse.msg.equals("wrong otp")) {
                        Toast.makeText(otpInput.this, "Entered OTP is wrong.\nPlease Recheck", Toast.LENGTH_SHORT).show();
                    } else
                        Toast.makeText(otpInput.this, "" + serverResponse.msg, Toast.LENGTH_SHORT).show();
                }

                @Override
                public void failure(RetrofitError error) {
                    viewDialog.hideDialog();
                    if (error.getKind().equals(RetrofitError.Kind.NETWORK)) {
                        Toast.makeText(otpInput.this, "No Internet connection found.", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(otpInput.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } else {
            viewDialog.hideDialog();
            Toast.makeText(this, "Please Restart Application", Toast.LENGTH_SHORT).show();
        }
    }

    public void resendOtp(View view) {
        viewDialog.showDialog();
        SetDataToDatabase con = Connection.doConnect();
        con.send_otp(email, new Callback<ServerResponse>() {
            @Override
            public void success(ServerResponse serverResponse, Response response) {
                viewDialog.hideDialog();
                if (serverResponse.msg.equals("sent")) {
                    Toast.makeText(otpInput.this, "Done", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void failure(RetrofitError error) {
                viewDialog.hideDialog();
                if (error.getKind().equals(RetrofitError.Kind.NETWORK)) {
                    Toast.makeText(otpInput.this, "No Internet connection found.", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(otpInput.this, error.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
