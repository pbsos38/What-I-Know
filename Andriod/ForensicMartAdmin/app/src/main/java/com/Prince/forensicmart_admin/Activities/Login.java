package com.Prince.forensicmart_admin.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Toast;

import com.Prince.forensicmart_admin.LocalDatabase.DataBaseHelper;
import com.Prince.forensicmart_admin.R;
import com.Prince.forensicmart_admin.SmallFeatures.ViewDialog;
import com.Prince.forensicmart_admin.server.Conn;
import com.Prince.forensicmart_admin.server.ServerResponse;
import com.Prince.forensicmart_admin.server.SetDataToDatabase;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class Login extends AppCompatActivity {
    private TextInputLayout uid_lay, pass_lay;
    private TextInputEditText uid, pass;

    private CheckBox remember;
    private ViewDialog viewDialog;
    private DataBaseHelper mydb;
    private ImageView showPass;
    private MaterialToolbar materialToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //reference
        {
            uid = findViewById(R.id.username_login2_editabble);
            uid_lay = findViewById(R.id.username_login2_layout);
            pass_lay = findViewById(R.id.password_login2_layout);
            pass = findViewById(R.id.Password_login2_editabble);
            remember = findViewById(R.id.chkRemember_dashboard);
            showPass = findViewById(R.id.loginShowPass);
            viewDialog = new ViewDialog(this);
            mydb = new DataBaseHelper(this);
            materialToolbar = findViewById(R.id.topAppBar);
        }

        showPass.setOnClickListener(v->{
            pass.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
        });

        materialToolbar.setNavigationOnClickListener(v->{
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        });
    }

    public void login_login2(View view) {
        if (check_EmptyNess()) {

            viewDialog.showDialog();
            SetDataToDatabase con = Conn.doConnect();
            con.adminlogin(uid.getText().toString(), pass.getText().toString(), new Callback<ServerResponse>() {
                @Override
                public void success(ServerResponse serverResponse, Response response) {
                    viewDialog.hideDialog();
                    if (serverResponse.msg.equals("Welcome")) {
                        if (remember.isChecked()) {
                            saveinfo(uid.getText().toString(), pass.getText().toString());
                        }
                        startActivity(new Intent(Login.this,Dashboard.class));
                        //fetchdata(serverResponse.msg);
                    }
                    else
                        Toast.makeText(Login.this, "" + serverResponse.msg, Toast.LENGTH_SHORT).show();
                }

                @Override
                public void failure(RetrofitError error) {
                    viewDialog.hideDialog();
                    if (error.getKind().equals(RetrofitError.Kind.NETWORK)) {
                        Toast.makeText(Login.this, "No Internet connection found.", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(Login.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }


    private boolean check_EmptyNess() {
        if (uid.getText().toString().trim().isEmpty()) {
            uid_lay.setHelperTextEnabled(false);
            uid_lay.setErrorEnabled(true);
            uid_lay.setError("Please fill your Username");
            uid.requestFocus();
            return false;
        }
        if (pass.getText().toString().trim().isEmpty()) {
            pass_lay.setHelperTextEnabled(false);
            pass_lay.setErrorEnabled(true);
            pass_lay.setError("Please fill your Password");
            pass.requestFocus();
            return false;
        }
        return true;
    }


    public void saveinfo(@NonNull String suid, @NonNull String spwd) {

        int no_ofEntries = mydb.getProfilesCount();
        if (no_ofEntries == 0) {
            //Toast.makeText(this, String.valueOf(no_ofEntries), Toast.LENGTH_SHORT).show();
            boolean isInserted = mydb.insertData(suid, spwd);
            if (isInserted) {
                //Toast.makeText(login_dashboard.this, "Remembered", Toast.LENGTH_SHORT).show();
            } else
                Toast.makeText(Login.this, "Some error occurred while saving password", Toast.LENGTH_SHORT).show();
        } else if (no_ofEntries >= 1) {
            boolean deletedRows = mydb.deleteData();
            if (deletedRows) {
                boolean isInserted = mydb.insertData(suid, spwd);
                if (isInserted) {
                    //Toast.makeText(login_dashboard.this, "Remembered", Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(Login.this, "Some error occurred while saving password", Toast.LENGTH_SHORT).show();
            } else
                Toast.makeText(Login.this, "Some error occurred while saving password", Toast.LENGTH_SHORT).show();
        }
    }
    public void show_repeat_password(View view) {

    }
}