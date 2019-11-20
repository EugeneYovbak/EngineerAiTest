package com.example.engineeraitest.data.api;

import com.example.engineeraitest.data.api.model.UserResponse;

import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface UserApiService {

    @GET("/api/users")
    Single<Response<UserResponse>> getUsers(@Query("offset") int offset, @Query("limit") int limit);
}
