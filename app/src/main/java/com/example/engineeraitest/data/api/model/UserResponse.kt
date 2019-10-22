package com.example.engineeraitest.data.api.model

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("status") var status: Boolean?,
    @SerializedName("message") var message: String?,
    @SerializedName("data") var data: UserDataInnerResponse?
)

//It was too late when I found out has_more field, so I've decided to leave it as it is
data class UserDataInnerResponse(
    @SerializedName("users") var users: List<UserDataResponse>
)

data class UserDataResponse(
    @SerializedName("name") var name: String?,
    @SerializedName("image") var image: String?,
    @SerializedName("items") var items: List<String>?
)