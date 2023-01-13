package com.example.searching.server;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

public interface setDataToDatabase {

    @GET("/fetchall_request.php")
    void fetchAllRequests(@Query("status") String status, Callback<bean_fetch_Requests> callBack);


}
