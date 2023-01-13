package com.Prince.forensicexams.server;
import com.Prince.forensicexams.BeanFiles.CompPosts_bean;
import com.Prince.forensicexams.BeanFiles.TestQuestion_bean;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Multipart;
import retrofit.http.POST;
import retrofit.http.Part;
import retrofit.http.Query;
import retrofit.mime.TypedFile;

public interface SetDataToDatabase {


    @GET("/fTestQuestionsRand.php")
    void fTestQuestions(@Query("sub") String subject,@Query("securityKey") int securityKey, Callback<TestQuestion_bean> testQuestion_beanCallback);

    @GET("/fCompPosts.php")
    void fPosts(Callback<CompPosts_bean> compPosts_beanCallback);

    @Multipart
    @POST("/createCompetitionPost.php")
    void createPost(@Part("image") TypedFile image, @Part("postlink") String postlink, Callback<ServerResponse> callback);

}
