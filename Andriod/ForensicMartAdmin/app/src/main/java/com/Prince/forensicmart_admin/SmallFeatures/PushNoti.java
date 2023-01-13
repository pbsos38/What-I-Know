package com.Prince.forensicmart_admin.SmallFeatures;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.StrictMode;

import androidx.annotation.Nullable;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class PushNoti {
    Activity activity;
    public PushNoti(@Nullable Activity activity) {

        this.activity = activity;
    }

    public void sendNotification_segment(final String header ,final String msg)
    {
        // This method is only used for sending push notifications to forensic Exams paneldash
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                int SDK_INT = android.os.Build.VERSION.SDK_INT;
                if (SDK_INT > 8) {
                    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                    StrictMode.setThreadPolicy(policy);
                    try {
                        String jsonResponse;

                        URL url = new URL("https://onesignal.com/api/v1/notifications");
                        HttpURLConnection con = (HttpURLConnection) url.openConnection();
                        con.setUseCaches(false);
                        con.setDoOutput(true);
                        con.setDoInput(true);

                        con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
                        con.setRequestProperty("Authorization", "Basic MGJhMTgyMWYtMTJjZi00NDBlLTllNTEtMTc2YjAwNjcwM2M4");
                        con.setRequestMethod("POST");

                        String strJsonBody = "{"
                                + "\"app_id\": \"ec86445a-d014-4bae-9c00-c6b95637ccb4\","
                                +   "\"included_segments\": [\"All\"],"
                                + "\"data\": {\"foo\": \"bar\"},"
                                + "\"headings\":{\"en\": \"" + header + "\"},"
                                + "\"contents\": {\"en\": \"" + msg + "\"}"
                                //+ "\"small_icon\":{\"image\" :\"@android:res/drawable/ic_stat_onesignal_default/ic_stat_onesignal_default.png\"}"
                                + "}";


                        System.out.println("strJsonBody:\n" + strJsonBody);

                        byte[] sendBytes = strJsonBody.getBytes("UTF-8");
                        con.setFixedLengthStreamingMode(sendBytes.length);

                        OutputStream outputStream = con.getOutputStream();
                        outputStream.write(sendBytes);

                        int httpResponse = con.getResponseCode();
                        System.out.println("httpResponse: " + httpResponse);

                        if (httpResponse >= HttpURLConnection.HTTP_OK
                                && httpResponse < HttpURLConnection.HTTP_BAD_REQUEST) {
                            Scanner scanner = new Scanner(con.getInputStream(), "UTF-8");
                            jsonResponse = scanner.useDelimiter("\\A").hasNext() ? scanner.next() : "";
                            scanner.close();
                        } else {
                            Scanner scanner = new Scanner(con.getErrorStream(), "UTF-8");
                            jsonResponse = scanner.useDelimiter("\\A").hasNext() ? scanner.next() : "";
                            scanner.close();
                        }
                        System.out.println("jsonResponse:\n" + jsonResponse);

                    } catch (Throwable t) {
                        t.printStackTrace();
                    }
                }
            }
        });

    }
}
