package com.example.searching.server;


import com.squareup.okhttp.OkHttpClient;

import retrofit.RestAdapter;
import retrofit.client.OkClient;

public class ServerConnection
{
    public static setDataToDatabase doConnect()
    {
//        OkHttpClient client = new OkHttpClient();
//        client.setProtocols(Arrays.asList(Protocol.HTTP_1_1));

        RestAdapter.Builder builder=new RestAdapter.Builder();
        builder.setEndpoint("http://buildbest.000webhostapp.com/");
        builder.setLogLevel(RestAdapter.LogLevel.FULL);
        builder.setClient(new OkClient(new OkHttpClient()));


        RestAdapter adapter=builder.build();
        setDataToDatabase apiMethods= adapter.create(setDataToDatabase.class);
        return(apiMethods);
    }
}

