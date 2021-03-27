package com.example.test.core.retrofit;

import com.example.test.model.Chapter;
import com.example.test.model.Document;
import com.example.test.model.ListCourse;
import com.example.test.model.ResponseDTO;
import com.example.test.model.User;
import com.example.test.model.UserInfo;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RequestApi {

    @POST("/api/v2/authorization")
    Call<ResponseDTO<UserInfo>> LoginImpl (@Body User acc);
    @POST("/api/v2/sign")
    Call<ResponseDTO<Boolean>> RegisterImp(@Body User acc);
    @GET("/api/v2/courseOfsuject")
    Call<ResponseDTO<List<ListCourse>>> getListCourse(@Query("userId") int userId,@Query("subjectId") int subjectId, @Header("Authorization") String token );
    @GET("/api/v2/mucluc")
    Call<ResponseDTO<List<Chapter>>> getChapter(@Query("userId")int userId, @Query("courseId") int courseId);
    @POST("/api/v2/loginToken")
    Call<ResponseDTO<UserInfo>> getToken(@Header("Authorization") String token);
    @GET("/api/v2/documentAll")
    Call<ResponseDTO<List<Document>>> getListDown();

}
