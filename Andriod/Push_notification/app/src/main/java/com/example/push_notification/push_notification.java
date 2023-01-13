package com.example.push_notification;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.StrictMode;

import androidx.annotation.Nullable;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class push_notification {

    Activity activity;
    public push_notification(@Nullable Activity activity) {

        this.activity = activity;
    }

    public void sendNotification(final String email, final String header)
    {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                int SDK_INT = android.os.Build.VERSION.SDK_INT;
                if (SDK_INT > 8) {
                    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                    StrictMode.setThreadPolicy(policy);

                    String send_email;

                    // not working error says "All included players are not subscribed"
                    //String[] send_email=new String[]{"pb1@gmail.com","pb2@gmail.com"};


                    //This is a Simple Logic to Send Notification different Device Programmatically....
                    if (email.equals("pb1@gmail.com")) {
                        send_email = "pb2@gmail.com";
                    } else {
                        send_email = "pb1@gmail.com";
                    }


                    try {
                        String jsonResponse;

                        URL url = new URL("https://onesignal.com/api/v1/notifications");
                        HttpURLConnection con = (HttpURLConnection) url.openConnection();
                        con.setUseCaches(false);
                        con.setDoOutput(true);
                        con.setDoInput(true);

                        con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
                        con.setRequestProperty("Authorization", "Basic ZTE3NmE0N2QtN2RkMC00Mzc0LThhMDktYzI2OGM1YTExM2Zm");
                        con.setRequestMethod("POST");

                        String strJsonBody = "{"
                                + "\"app_id\": \"33bcd165-eb1b-4fc9-9fd2-c747e8c7b080\","

                                //please replace email with your tag name
                                + "\"filters\": [{\"field\": \"tag\", \"key\": \"email\", \"relation\": \"=\", \"value\": \"" +send_email + "\"}],"

                                + "\"data\": {\"foo\": \"bar\"},"
                                + "\"headings\":{\"en\": \"" + header +" \"},"
                                + "\"contents\": {\"en\": \"English Message\"},"
                                + "\"small_icon\":{\"image\" :\"@android:res/drawable/ic_stat_onesignal_default/ic_stat_onesignal_default.png\"}"
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
