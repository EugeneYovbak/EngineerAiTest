package com.example.engineeraitest.domain.exception

import com.example.engineeraitest.domain.model.User
import io.reactivex.Single

interface UserRepository {
    fun getUsers(offset: Int?, limit: Int?): Single<List<User>>
}