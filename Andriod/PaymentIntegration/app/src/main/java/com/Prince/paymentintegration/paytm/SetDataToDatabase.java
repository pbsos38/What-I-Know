package com.Prince.paymentintegration.paytm;


import retrofit.Callback;
import retrofit.http.Multipart;
import retrofit.http.POST;
import retrofit.http.Part;
import retrofit.mime.TypedFile;

public interface SetDataToDatabase {

    @Multipart
    @POST("/index.php")
    void make_payment(@Part("oid") String amount, Callback<ServerResponse> callback);

}
