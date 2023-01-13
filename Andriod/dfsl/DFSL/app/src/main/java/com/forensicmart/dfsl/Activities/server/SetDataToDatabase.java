package com.forensicmart.dfsl.Activities.server;


import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

public interface SetDataToDatabase {

    @GET("/contactus.php")
    void ContactUS(@Query("subject") String subject, @Query("email") String email, @Query("msg") String msg, Callback<ServerRes> serverResponseCallback);

}


