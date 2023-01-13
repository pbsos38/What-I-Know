package com.example.upload.server;


import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Multipart;
import retrofit.http.POST;
import retrofit.http.Part;
import retrofit.http.Query;
import retrofit.mime.TypedFile;

public interface SetDataToDatabase {

    @Multipart
    @POST("/manualCreatePost.php")
    void createPost(@Part("pic") String pic, @Part("postname") String name, @Part("postlink") String postlink, @Part("website") String website, Callback<ServerResponse> callback);

    /*@POST("/fetchCourses.php")
    void fetchPosts(Callback<courses_bean> callback);*/

    @GET("/addCourse.php")
    void addCourse(@Query("name") String name, Callback<ServerResponse> serverResponseCallback);

    @GET("/addVideo.php")
    void addVideo(@Query("link") String link,@Query("name") String name, Callback<ServerResponse> serverResponseCallback);

    @GET("/addQuizQuestion.php")
    void addQuizQuestion(@Query("q") String ques,@Query("a") String optA,@Query("b") String optB,@Query("c") String optC,
                         @Query("d") String optD,@Query("ans") String optAnswer,@Query("sub") String choosedSubject,
                         Callback<ServerResponse> callback);

}

