package com.forensic.mart.server;

import com.forensic.mart.BeanFiles.Comments_bean;
import com.forensic.mart.BeanFiles.Courses_bean;
import com.forensic.mart.BeanFiles.Education_bean;
import com.forensic.mart.BeanFiles.Employments_bean;
import com.forensic.mart.BeanFiles.Posts_beans;
import com.forensic.mart.BeanFiles.ProfileDataPostBean;
import com.forensic.mart.BeanFiles.Profile_data_bean;
import com.forensic.mart.BeanFiles.TestQuestion_bean;
import com.forensic.mart.BeanFiles.TestQuestion_bean_result;
import com.forensic.mart.BeanFiles.VideoBean;
import com.forensic.mart.BeanFiles.WebPost_bean;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Multipart;
import retrofit.http.POST;
import retrofit.http.Part;
import retrofit.http.Query;
import retrofit.mime.TypedFile;

public interface SetDataToDatabase {

    @GET("/Signup.php")
    void signup(@Query("rand") int x, @Query("name") String name, @Query("username") String username, @Query("email") String email, @Query("phone") String phone, @Query("password") String password
            , @Query("country_id") int country_id, @Query("dial_code") String country, Callback<ServerResponse> serverResponseCallback);

    @GET("/send_otp.php")
    void send_otp(@Query("email") String email, Callback<ServerResponse> serverResponseCallback);

    @GET("/updateValidity.php")
    void updateValidity(@Query("otp") String otp, @Query("email") String email, Callback<ServerResponse> callback);

    @GET("/Login.php")
    void login(@Query("username") String username, @Query("password") String password, Callback<ServerResponse> callback);

    @GET("/Fetch_profile_data.php")
    void fetchData(@Query("username") String username, @Query("ty") String type, Callback<Profile_data_bean> serverResponseCallback);

    @GET("/Fetch_profile_data.php")
    void fetchEduData(@Query("username") String username, @Query("ty") String type, Callback<Education_bean> serverResponseCallback);

    @GET("/Fetch_profile_data.php")
    void fetchEmployData(@Query("username") String username, @Query("ty") String type, Callback<Employments_bean> serverResponseCallback);

    @GET("/checkUser.php")
    void checkUser(@Query("email") String email, Callback<ServerResponse> callback);

    @GET("/updatePassword.php")
    void updatePassword(@Query("email") String email, @Query("newpassword") String newpassword, Callback<ServerResponse> callback);

    @GET("/fetchStaticPosts.php")
    void fetchPosts(@Query("website") String website, Callback<Posts_beans> posts_beansCallback);

    @POST("/fetchCourses.php")
    void fetchCourses(Callback<Courses_bean> courses_beanCallback);

    @GET("/fetchWebPosts.php")
    void fetchWEbPosts(@Query("type") String type, Callback<WebPost_bean> webPost_beanCallback);

    @GET("/fetchVideo.php")
    void fetchVideo(Callback<VideoBean> videoBeanCallback);

    @GET("/sendSMS.php")
    void sendSMS(@Query("phone") String phone, @Query("country_id") String country_id, Callback<ServerResponse> callback);

    @GET("/matchOtp.php")
    void updateSmsValidity(@Query("otp") String toString, @Query("phone") String phone, Callback<ServerResponse> callback);


    @GET("/fTestQuestions.php")
    void fTestQuestions(@Query("quizid") int quizid, Callback<TestQuestion_bean> testQuestion_beanCallback);

    @GET("/fTestQuestions_result.php")
    void fTestQuestions_result(@Query("id") String quizId, Callback<TestQuestion_bean_result> testQuestion_bean_resultCallback);

    @GET("/maxQuizid.php")
    void maxQuizId(Callback<ServerResponse> callback);

    @GET("/Edit_profile.php")
    void EditProfile_desi(@Query("id") String id,@Query("designation") String Designation,Callback<ServerResponse> serverResponseCallback);

    @GET("/Edit_profile.php")
    void EditProfile_add_edu(@Query("act") String act,@Query("uid") String user_id,@Query("school") String toString,@Query("from") String toString1,@Query("to") String toString2,
                             @Query("degree") String toString3,@Query("details") String toString4, Callback<ServerResponse> serverResponseCallback);
    @GET("/Edit_profile.php")
    void EditProfile_add_employ(@Query("act") String act,@Query("uid") String user_id,@Query("title") String toString,@Query("from") String toString1,@Query("to") String toString2,
                             @Query("details") String toString3, Callback<ServerResponse> serverResponseCallback);

    @GET("/Edit_profile.php")
    void EditProfile_update_edu(@Query("act") String act,@Query("id") String user_id,@Query("school") String toString,@Query("from") String toString1,@Query("to") String toString2,
                                @Query("degree") String toString3,@Query("details") String toString4, Callback<ServerResponse> serverResponseCallback);
  @GET("/Edit_profile.php")
    void EditProfile_update_employ(@Query("act") String act,@Query("id") String user_id,@Query("title") String toString,@Query("from") String toString1,@Query("to") String toString2,
                                @Query("details") String toString3, Callback<ServerResponse> serverResponseCallback);

    @GET("/Edit_profile.php")
    void EditProfile_remove_edu(@Query("act") String act,@Query("id") String user_id, Callback<ServerResponse> serverResponseCallback);

    @Multipart
    @POST("/Update_profile_pic.php")
    void Update_profile_pic(@Part("image") TypedFile image, @Part("id") String id, Callback<ServerResponse> callback);

    @GET("/LoadPosts_foren.php")
    void loadWEbPosts(@Query("act") String start_home, Callback<WebPost_bean> webPost_beanCallback);

    @GET("/LoadPosts_foren.php")
    void loadWEbPosts_more(@Query("act") String s1,@Query("id") int id, Callback<WebPost_bean> webPost_more_beanCallback);

    @GET("/Fetch_profile_data.php")
    void profile_data_posts(@Query("ty") String type,@Query("id") String id,Callback<ProfileDataPostBean>profileDataPostBeanCallback);

    @GET("/comments.php")
    void fetchComments(@Query("act") String act, @Query("postId") String postId, Callback<Comments_bean>comments_beanCallback);

    @GET("/comments.php")
    void addComment(@Query("act") String act,@Query("userId") String USERID, @Query("postId") int postId,@Query("comment") String com, Callback<ServerResponse> responseCallback);

    @GET("/LoadPosts_foren.php")
    void loadWEbPosts_specific(@Query("act") String start,@Query("ty") String type, Callback<WebPost_bean> webPost_beanCallback);

    @GET("/LoadPosts_foren.php")
    void loadWEbPosts_specific_more(@Query("act") String start,@Query("ty") String type,@Query("id") int id, Callback<WebPost_bean> webPost_beanCallback);

    @GET("/comments.php")
    void likes(@Query("act") String act, @Query("postid") int postid, @Query("uid") String uid, Callback<ServerResponse> callback);

    @Multipart
    @POST("/createPost_mart.php")
    void createPostWithPic(@Part("act") String act,@Part("title") String title,@Part("link") String link,@Part("detail") String detail,
                           @Part("type") String type,@Part("userid") String id, @Part("image") TypedFile image, Callback<ServerResponse> callback);

    @GET("/createPost_mart.php")
    void createPostWithoutPic(@Query("act") String act,@Query("title") String title,@Query("link") String link,@Query("detail") String detail,
                              @Query("type") String type,@Query("userid") String id, Callback<ServerResponse> callback);

}


