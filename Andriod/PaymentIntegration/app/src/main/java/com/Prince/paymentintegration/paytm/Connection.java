package com.Prince.paymentintegration.paytm;

import com.squareup.okhttp.OkHttpClient;

import retrofit.RestAdapter;
import retrofit.client.OkClient;

public class Connection {
        public static SetDataToDatabase doConnect()
        {
            RestAdapter.Builder builder=new RestAdapter.Builder();
            builder.setEndpoint("http://get-blood.000webhostapp.com/payments/paytm/");
            builder.setLogLevel(RestAdapter.LogLevel.FULL);
            builder.setClient(new OkClient(new OkHttpClient()));


            RestAdapter adapter=builder.build();
            return(adapter.create(SetDataToDatabase.class));
        }
}
