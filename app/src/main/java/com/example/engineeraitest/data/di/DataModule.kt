package com.example.engineeraitest.data.di

import com.example.engineeraitest.data.api.UserApiService
import com.example.engineeraitest.data.repository.UserRepositoryImpl
import com.example.engineeraitest.domain.exception.UserRepository
import org.koin.dsl.module

val dataModule = module {
    single { getUserRepository(get()) }
}

fun getUserRepository(userApiService: UserApiService): UserRepository {
    return UserRepositoryImpl(userApiService)
}