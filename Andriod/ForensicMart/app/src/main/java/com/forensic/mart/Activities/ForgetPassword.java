package com.forensic.mart.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.forensic.mart.R;
import com.forensic.mart.SmallFeatures.ViewDialog;
import com.forensic.mart.server.Connection;
import com.forensic.mart.server.ServerResponse;
import com.chaos.view.PinView;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.forensic.mart.server.SetDataToDatabase;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class ForgetPassword extends BottomSheetDialogFragment {
    Activity activity;
    Button sendOtp, update, verify;
    TextInputLayout email_lay, pass_lay, rePass_lay;
    TextInputEditText email, pass, rePass;
    ViewDialog viewDialog;
    TextView hintText, verifyText, resendText;
    PinView pin;


    public ForgetPassword(Login login) {
        this.activity = login;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.forget_password_bottom_sheet, container, false);
        //reference
        {
            sendOtp = v.findViewById(R.id.send_otp_forget_password);
            update = v.findViewById(R.id.update_password_forget_password);
            email_lay = v.findViewById(R.id.email_forget_password_layout);
            email = v.findViewById(R.id.email_forget_password_editabble);
            pass = v.findViewById(R.id.password_forget_password_editabble);
            pass_lay = v.findViewById(R.id.password_forget_password_layout);
            rePass = v.findViewById(R.id.repeat_password_forget_password_editable);
            rePass_lay = v.findViewById(R.id.repeat_password_forget_password_layout);
            verify = v.findViewById(R.id.verify_forgetPassword);
            hintText = v.findViewById(R.id.text_suggest_forgetPassword);
            verifyText = v.findViewById(R.id.text_verify_forgetPassword);
            resendText = v.findViewById(R.id.resend_forgetPassword);
            pin = v.findViewById(R.id.pin_view_forgetPassword);
            viewDialog = new ViewDialog(activity);
        }
        {
            verifyText.setVisibility(View.INVISIBLE);
            verifyText.setVisibility(View.GONE);
            pin.setVisibility(View.INVISIBLE);
            pin.setVisibility(View.GONE);
            resendText.setVisibility(View.INVISIBLE);
            resendText.setVisibility(View.GONE);
            verify.setVisibility(View.INVISIBLE);
            verify.setVisibility(View.GONE);
            hintText.setVisibility(View.INVISIBLE);
            hintText.setVisibility(View.GONE);

            pass_lay.setVisibility(View.INVISIBLE);
            pass_lay.setVisibility(View.GONE);
            rePass_lay.setVisibility(View.INVISIBLE);
            rePass_lay.setVisibility(View.GONE);
            update.setVisibility(View.INVISIBLE);
            update.setVisibility(View.GONE);
        }
        sendOtp.setOnClickListener(v13 -> {
            if (email.getText().toString().trim().isEmpty()) {
                email_lay.setHelperTextEnabled(false);
                email_lay.setErrorEnabled(true);
                email_lay.setError("Please enter your email");
            } else {
                checkUserExistence(email.getText().toString());
            }
        });

        verify.setOnClickListener(v14 -> verifyotp());
        resendText.setOnClickListener(v1 -> resendOtp());
        update.setOnClickListener(v12 -> updatepasswordfn());


        return v;
    }

    private void checkUserExistence(String s1) {
        viewDialog.showDialog();
        SetDataToDatabase con = Connection.doConnect();
        con.checkUser(s1, new Callback<ServerResponse>() {
            @Override
            public void success(ServerResponse serverResponse, Response response) {
                viewDialog.hideDialog();
                if (serverResponse.msg.equals("goAhead")) {
                    sendOtpfn(s1);
                } else if (serverResponse.msg.equals("No User found")) {
                    email_lay.setHelperTextEnabled(false);
                    email_lay.setErrorEnabled(true);
                    email_lay.setError(serverResponse.msg);
                } else
                    Toast.makeText(activity, "" + serverResponse.msg, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void failure(RetrofitError error) {
                viewDialog.hideDialog();
                if (error.getKind().equals(RetrofitError.Kind.NETWORK)) {
                    Toast.makeText(activity, "No Internet connection found.", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(activity, error.toString(), Toast.LENGTH_SHORT).show();
                }
            }

        });
    }

    private void sendOtpfn(String ss) {
        viewDialog.showDialog();
        SetDataToDatabase con = Connection.doConnect();
        con.send_otp(ss, new Callback<ServerResponse>() {
            @Override
            public void success(ServerResponse serverResponse, Response response) {
                if (serverResponse.msg.equals("sent")) {
                    email_lay.setVisibility(View.INVISIBLE);
                    email_lay.setVisibility(View.GONE);
                    sendOtp.setVisibility(View.INVISIBLE);
                    sendOtp.setVisibility(View.GONE);
                    verifyText.setVisibility(View.VISIBLE);
                    pin.setVisibility(View.VISIBLE);
                    resendText.setVisibility(View.VISIBLE);
                    verify.setVisibility(View.VISIBLE);
                    hintText.setVisibility(View.VISIBLE);
                } else
                    Toast.makeText(activity, "Please try again later", Toast.LENGTH_LONG).show();
                viewDialog.hideDialog();
            }

            @Override
            public void failure(RetrofitError error) {
                viewDialog.hideDialog();
                if (error.getKind().equals(RetrofitError.Kind.NETWORK)) {
                    Toast.makeText(activity, "No Internet connection found.", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(activity, error.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void resendOtp() {
        viewDialog.showDialog();
        SetDataToDatabase con = Connection.doConnect();
        con.send_otp(email.getText().toString(), new Callback<ServerResponse>() {
            @Override
            public void success(ServerResponse serverResponse, Response response) {
                viewDialog.hideDialog();
                if (serverResponse.msg.equals("sent")) {
                    Toast.makeText(activity, "Done", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void failure(RetrofitError error) {
                viewDialog.hideDialog();
                if (error.getKind().equals(RetrofitError.Kind.NETWORK)) {
                    Toast.makeText(activity, "No Internet connection found.", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(activity, error.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void verifyotp() {
        viewDialog.showDialog();
        SetDataToDatabase con = Connection.doConnect();
        con.updateValidity(pin.getText().toString(), email.getText().toString(), new Callback<ServerResponse>() {
            @Override
            public void success(ServerResponse serverResponse, Response response) {
                viewDialog.hideDialog();
                if (serverResponse.msg.equals("otp matched")) {
                    verifyText.setVisibility(View.INVISIBLE);
                    verifyText.setVisibility(View.GONE);
                    pin.setVisibility(View.INVISIBLE);
                    pin.setVisibility(View.GONE);
                    resendText.setVisibility(View.INVISIBLE);
                    resendText.setVisibility(View.GONE);
                    hintText.setVisibility(View.INVISIBLE);
                    hintText.setVisibility(View.GONE);
                    verify.setVisibility(View.INVISIBLE);
                    verify.setVisibility(View.GONE);
                    pass_lay.setVisibility(View.VISIBLE);
                    rePass_lay.setVisibility(View.VISIBLE);
                    update.setVisibility(View.VISIBLE);

                } else if (serverResponse.msg.equals("wrong otp")) {
                    Toast.makeText(activity, "Entered OTP is wrong.\nPlease Recheck", Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(activity, "" + serverResponse.msg, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void failure(RetrofitError error) {
                viewDialog.hideDialog();
                if (error.getKind().equals(RetrofitError.Kind.NETWORK)) {
                    Toast.makeText(activity, "No Internet connection found.", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(activity, error.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void updatepasswordfn() {
        if (checkEmptyness()) {
            viewDialog.showDialog();
            SetDataToDatabase con = Connection.doConnect();
            con.updatePassword(email.getText().toString(), pass.getText().toString(), new Callback<ServerResponse>() {
                @Override
                public void success(ServerResponse serverResponse, Response response) {
                    viewDialog.hideDialog();
                    if (serverResponse.msg.equals("Pasword updated")) {
                        Toast.makeText(activity, "Password updated", Toast.LENGTH_SHORT).show();
                        dismiss();
                    } else if (serverResponse.msg.equals("No user found")) {
                        Toast.makeText(activity, "Something Went Wrong.\n Please Try Again Later", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void failure(RetrofitError error) {
                    viewDialog.hideDialog();
                    if (error.getKind().equals(RetrofitError.Kind.NETWORK)) {
                        Toast.makeText(activity, "No Internet connection found.", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(activity, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private boolean checkEmptyness() {
        if (pass.getText().toString().trim().isEmpty()) {
            pass_lay.setHelperTextEnabled(false);
            pass_lay.setErrorEnabled(true);
            pass_lay.setError("Please Enter your Password");
            return false;
        }
        if (rePass.getText().toString().trim().isEmpty()) {
            rePass_lay.setHelperTextEnabled(false);
            rePass_lay.setErrorEnabled(true);
            rePass_lay.setError("Please Enter your Password");
            return false;
        }
        if (!pass.getText().toString().equals(rePass.getText().toString())) {
            pass_lay.setHelperTextEnabled(false);
            pass_lay.setErrorEnabled(true);
            pass_lay.setError("Your Passwords Don't Match");
            rePass_lay.setHelperTextEnabled(false);
            rePass_lay.setErrorEnabled(true);
            rePass_lay.setError("Your Passwords Don't Match");
            return false;
        }
        return true;
    }


}
