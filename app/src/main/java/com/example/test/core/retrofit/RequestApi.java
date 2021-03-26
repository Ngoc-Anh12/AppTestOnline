package com.example.test.core.retrofit;

import com.example.test.model.Chapter;
import com.example.test.model.ListCourse;
import com.example.test.model.ResponseDTO;
import com.example.test.model.User;
import com.example.test.model.UserInfo;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RequestApi {

    @POST("/api/v2/authorization")
    Call<ResponseDTO<UserInfo>> LoginImpl (@Body User acc);
    @POST("/api/v2/sign")
    Call<ResponseDTO<Boolean>> RegisterImp(@Body User acc);
    @GET("/api/v2/courseOfsuject")
    Call<ResponseDTO<ListCourse>> getListCourse(int subjectId, int userId );
    @GET("/api/v2/mucluc")
    Call<ResponseDTO<Chapter>> getChapter(int userId, int courseId);
    

}
