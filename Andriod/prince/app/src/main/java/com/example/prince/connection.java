package com.example.prince;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.OkHttpClient;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import org.jetbrains.annotations.NotNull;

import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/*public class connection {
    public static setData doConnect(){

        RestAdapter.Builder builder = new RestAdapter.Builder();
        builder.setEndpoint("http://marketplaceapi.softelsystems.net/");
        builder.setLogLevel(RestAdapter.LogLevel.FULL);
        builder.setClient(new OkClient(new OkHttpClient()));

        RestAdapter adapter = builder.build();
        setData api = adapter.create(setData.class);
        return (api);
    }
}*/
public class connection {
    private static Retrofit retrofit;

    public static Retrofit getRetrofitInstance(){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl("http://marketplaceapi.softelsystems.net/")
                    //.client(okHttpClient())
                    .addConverterFactory(GsonConverterFactory.create(gson))

                    .build();
        }
        return retrofit;
    }
/*
    private static okhttp3.OkHttpClient okHttpClient(){
        return new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @NotNull
                    @Override
                    public Response intercept(@NotNull Chain chain) throws IOException {
                        Request request=chain.request().newBuilder().addHeader("Authorization","Bearer "+ Token).build();
                        return chain.proceed(request);
                    }
                })
                .build();
    }
*/
}
