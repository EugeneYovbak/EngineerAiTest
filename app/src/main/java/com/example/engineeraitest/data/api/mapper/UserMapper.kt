package com.example.engineeraitest.data.api.mapper

import com.example.engineeraitest.data.api.model.UserDataResponse
import com.example.engineeraitest.domain.model.User
import io.reactivex.functions.Function

class UserMapper : Function<UserDataResponse, User> {

    override fun apply(userDataResponse: UserDataResponse): User {
        with(userDataResponse) {
            return User(
                name ?: "",
                image ?: "",
                items ?: ArrayList()
            )
        }
    }
}