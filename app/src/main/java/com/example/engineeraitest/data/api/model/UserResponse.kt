package com.example.engineeraitest.data.api.model

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("data") var data: UserDataInnerResponse?
)

data class UserDataInnerResponse(
    @SerializedName("users") var users: List<UserDataResponse>
)

data class UserDataResponse(
    @SerializedName("name") var name: String?,
    @SerializedName("image") var image: String?,
    @SerializedName("items") var items: List<String>?
)