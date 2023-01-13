package com.Prince.forensicmart_admin.server;

import com.squareup.okhttp.OkHttpClient;

import retrofit.RestAdapter;
import retrofit.client.OkClient;

public class Connection {
        public static SetDataToDatabase doConnect()
        {
            RestAdapter.Builder builder=new RestAdapter.Builder();
            builder.setEndpoint("http://buildbest.000webhostapp.com/forensicMart");
            builder.setLogLevel(RestAdapter.LogLevel.FULL);
            builder.setClient(new OkClient(new OkHttpClient()));


            RestAdapter adapter=builder.build();
            return(adapter.create(SetDataToDatabase.class));
        }
}
