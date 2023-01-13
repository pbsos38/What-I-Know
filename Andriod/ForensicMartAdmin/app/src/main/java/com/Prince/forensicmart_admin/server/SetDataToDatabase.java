package com.Prince.forensicmart_admin.server;

import com.Prince.forensicmart_admin.Bean.Competition_bean;
import com.Prince.forensicmart_admin.Bean.Courses_bean;
import com.Prince.forensicmart_admin.Bean.Posts_beans;
import com.Prince.forensicmart_admin.Bean.VideoBean;

import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.Multipart;
import retrofit.http.POST;
import retrofit.http.Part;
import retrofit.http.Query;
import retrofit.mime.TypedFile;

public interface SetDataToDatabase {

    @GET("/adminLogin.php")
    void adminlogin(@Query("username") String extracteduid, @Query("password") String extractedpwd, Callback<ServerResponse> serverResponseCallback);

    @Multipart
    @POST("/createStaticPost.php")
    void createPost(@Part("image") TypedFile image, @Part("postname") String postname, @Part("postlink") String postlink,
                    @Part("website") String website, Callback<ServerResponse> callback);

    @GET("/fetchStaticPosts.php")
    void fetchPosts(@Query("website") String website, Callback<Posts_beans> posts_beansCallback);

    @POST("/fetchCourses.php")
    void fetchCourses(Callback<Courses_bean> courses_beanCallback);

    @GET("/fetchVideo.php")
    void fetchVideo(Callback<VideoBean> videoBeanCallback);

    @GET("/addVideo.php")
    void addVideo(@Query("link") String linkk, @Query("name") String namee, Callback<ServerResponse> serverResponseCallback);

    @GET("/addQuizQuestion.php")
    void addQuizQuestion(@Query("q") String ques, @Query("a") String optA, @Query("b") String optB, @Query("c") String optC,
                         @Query("d") String optD, @Query("ans") String optAnswer, @Query("sub") String choosedSubject,
                         Callback<ServerResponse> callback);

    @POST("/certificateManager.php")
    void updateCertificate(@Part("image") TypedFile image, Callback<ServerResponse> callback);
    @FormUrlEncoded
    @POST("/competitionManager.php")
    void Competition(@Field("act") String act, @Field("name") String name, @Field("details") String details, @Field("cid") String cid, @Field("quesno") String quesno,
                     @Field("ques") String ques, @Field("a") String a, @Field("b") String b, @Field("c") String c, @Field("d") String d, @Field("ans") String ans,
                     @Field("status") String status, Callback<ServerResponse> callback);
    @FormUrlEncoded
    @POST("/competitionManager.php")
    void Competition_list(@Field("act") String act, @Field("cid") String cid, Callback<Competition_bean> callback);


}
