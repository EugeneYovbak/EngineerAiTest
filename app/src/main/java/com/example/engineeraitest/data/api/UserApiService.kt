package com.example.engineeraitest.data.api

import com.example.engineeraitest.data.api.model.UserResponse
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface UserApiService {

    @GET("/api/users")
    fun getUsers(@Query("offset") offset: Int?, @Query("limit") limit: Int?): Single<Response<UserResponse>>
}