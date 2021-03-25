package com.example.test.core.retrofit;

import com.example.test.model.ResponseDTO;
import com.example.test.model.User;
import com.example.test.model.UserInfo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RequestApi {

    @POST("/api/v2/authorization")
    Call<ResponseDTO<UserInfo>> LoginImpl (@Body User acc);
    @POST("/api/v2/sign")
    Call<ResponseDTO<Boolean>> RegisterImp(@Body User acc);

}
