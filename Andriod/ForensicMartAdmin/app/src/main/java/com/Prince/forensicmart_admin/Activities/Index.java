package com.Prince.forensicmart_admin.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.Prince.forensicmart_admin.LocalDatabase.DataBaseHelper;
import com.Prince.forensicmart_admin.R;
import com.Prince.forensicmart_admin.server.Conn;
import com.Prince.forensicmart_admin.server.ServerResponse;
import com.Prince.forensicmart_admin.server.SetDataToDatabase;
import com.google.firebase.analytics.FirebaseAnalytics;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class Index extends AppCompatActivity {

    DataBaseHelper mydb;
    String x = null;
    private FirebaseAnalytics mFirebaseAnalytics;

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
// Obtain the FirebaseAnalytics instance.
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
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
            con.adminlogin(extracteduid, extractedpwd, new Callback<ServerResponse>() {
                @Override
                public void success(ServerResponse serverResponse, Response response) {
                    if (serverResponse.msg.equals("Welcome")) {
                        //fetchdata(serverResponse.msg, finalExtracteduid);
                        startActivity(new Intent(Index.this, Dashboard.class));
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

/*
    private void fetchdata(String string,String string2) {
        setDataToDatabase con = Connection.doConnect();
        con.fetchData(string2, new Callback<Profile_data_bean>() {
            @Override
            public void success(Profile_data_bean profile_data_bean, Response response) {
                if (string.equals("Welcome")) {
                    Intent intent = new Intent(Index.this, DashBoard.class);
                    intent.putExtra("email", profile_data_bean.data.get(0).getEmail());
                    intent.putExtra("phoneVerificationStatus", profile_data_bean.getPhone_verify());
                    startActivity(intent);
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
*/
}