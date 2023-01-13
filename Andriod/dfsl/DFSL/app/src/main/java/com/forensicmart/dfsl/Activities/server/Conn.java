package com.forensicmart.dfsl.Activities.server;


import com.squareup.okhttp.OkHttpClient;

import retrofit.RestAdapter;
import retrofit.client.OkClient;

public class Conn {
    public static SetDataToDatabase doConnect()
    {
        RestAdapter.Builder builder=new RestAdapter.Builder();
        builder.setEndpoint("https://www.forensicmart.com/Android/");
        builder.setLogLevel(RestAdapter.LogLevel.FULL);
        builder.setClient(new OkClient(new OkHttpClient()));


        RestAdapter adapter=builder.build();
        return(adapter.create(SetDataToDatabase.class));
    }
}
