package com.example.engineeraitest.data.repository

import com.example.engineeraitest.data.api.UserApiService
import com.example.engineeraitest.data.api.mapper.UserMapper
import com.example.engineeraitest.domain.exception.UserRepository
import com.example.engineeraitest.domain.model.User
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException
import io.reactivex.Flowable
import io.reactivex.Single

class UserRepositoryImpl(private val mUserApiService: UserApiService) : UserRepository {

    override fun getUsers(offset: Int?, limit: Int?): Single<List<User>> {
        return mUserApiService.getUsers(offset, limit)
            .map {
                if (!it.isSuccessful) throw HttpException(it)
                it.body()?.data?.users!!
            }
            .toFlowable()
            .flatMap { Flowable.fromIterable(it) }
            .map(UserMapper())
            .toList()
    }
}