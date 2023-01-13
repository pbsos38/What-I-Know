package com.forensic.mart.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import retrofit.Callback;

import com.forensic.mart.BeanFiles.Profile_data_bean;
import com.forensic.mart.LocalDatabase.DataBaseHelper;
import com.forensic.mart.R;
import com.forensic.mart.server.Conn;
import com.forensic.mart.server.ServerResponse;
import com.forensic.mart.server.SetDataToDatabase;

import retrofit.RetrofitError;
import retrofit.client.Response;

public class Index extends AppCompatActivity {

    DataBaseHelper mydb;
    String x = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);

        //references
        {
            mydb = new DataBaseHelper(this);
        }
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                autoLogin();
            }
        }, 1000);

    }

    public void autoLogin() {

        String extracteduid = null, extractedpwd = null;
        Cursor res = mydb.distictvalues();
        Cursor res2 = mydb.fetchData();

        while (res.moveToNext()) {
            x = String.valueOf(res.getString(0));
        }
        while (res2.moveToNext()) {
            extracteduid = res2.getString(0);
            extractedpwd = res2.getString(1);
        }

        if (res.getCount() == 0) {
            Intent intent = new Intent(Index.this, Login.class);
            startActivity(intent);
            return;
        } else if (res.getCount() == 1) {
            SetDataToDatabase con = Conn.doConnect();
            String finalExtracteduid = extracteduid;
            con.login(extracteduid, extractedpwd, new Callback<ServerResponse>() {
                @Override
                public void success(ServerResponse serverResponse, Response response) {
                    if (serverResponse.msg.equals("Welcome")) {
                        fetchdata(serverResponse.msg, finalExtracteduid);
                    } else if (serverResponse.msg.equals("unverified")) {
                        fetchdata(serverResponse.msg, finalExtracteduid);
                    } else if (serverResponse.msg.equals("No User found")) {
                        startActivity(new Intent(Index.this,Login.class));
                    } else
                        Toast.makeText(Index.this, "" + serverResponse.msg, Toast.LENGTH_SHORT).show();
                }

                @Override
                public void failure(RetrofitError error) {
                    if (error.getKind().equals(RetrofitError.Kind.NETWORK)) {
                        Toast.makeText(Index.this, "No Internet connection found.", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(Index.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void fetchdata(String string, String string2) {
        SetDataToDatabase con = Conn.doConnect();
        con.fetchData(string2,"main", new Callback<Profile_data_bean>() {
            @Override
            public void success(Profile_data_bean profile_data_bean, Response response) {
                if (string.equals("Welcome")) {
                    if (profile_data_bean.data.get(0).getPhone_verify().equals("unverified")) {
                        Intent intent = new Intent(Index.this, unverified_mobile.class);
                        intent.putExtra("phone", profile_data_bean.data.get(0).getPhone());
                        intent.putExtra("username", string2 );
                        startActivity(intent);

                    } else {
                        Intent intent = new Intent(Index.this, DashBoard.class);
                        intent.putExtra("email", profile_data_bean.data.get(0).getEmail());
                        intent.putExtra("phoneVerificationStatus", profile_data_bean.getPhone_verify());
                        intent.putExtra("username", string2 );
                        intent.putExtra("userID", profile_data_bean.data.get(0).getId());
                        intent.putExtra("userName", profile_data_bean.data.get(0).getName());
                        if(mydb.updateUserId(profile_data_bean.data.get(0).getId(),string2)){
                            Log.d("------","Updated");
                            //Toast.makeText(Index.this, ""+profile_data_bean.data.get(0).getId()+string2, Toast.LENGTH_SHORT).show();
                        }
                        else
                            Log.d("------","Update failed");
                        startActivity(intent);
                    }
                } else if (string.equals("unverified")) {
                    Intent intent = new Intent(Index.this, otpInput.class);
                    intent.putExtra("email", profile_data_bean.data.get(0).getEmail());
                    startActivity(intent);
                }
            }

            @Override
            public void failure(RetrofitError error) {
                if (error.getKind().equals(RetrofitError.Kind.NETWORK)) {
                    Toast.makeText(Index.this, "No Internet connection found.", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(Index.this, error.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}