package com.example.engineeraitest.data.di

import android.content.Context
import com.example.engineeraitest.BuildConfig
import com.example.engineeraitest.data.api.UserApiService
import com.example.engineeraitest.data.api.tools.ConnectivityInterceptor
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit

val apiModule = module {
    single { getLoggingInterceptor() }
    single { getConnectivityInterceptor(get()) }

    single { getOkHttpClient(get(), get()) }
    single { getGson() }
    single { getRetrofit(get(), get()) }

    single { (get() as Retrofit).create(UserApiService::class.java) }
}

fun getLoggingInterceptor(): Interceptor {
    return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
}

fun getConnectivityInterceptor(context: Context): ConnectivityInterceptor {
    return ConnectivityInterceptor(context)
}

fun getOkHttpClient(loggingInterceptor: Interceptor, connectivityInterceptor: ConnectivityInterceptor): OkHttpClient {
    return OkHttpClient.Builder()
        .addInterceptor(connectivityInterceptor)
        .addInterceptor(loggingInterceptor)
        .readTimeout(60, TimeUnit.SECONDS)
        .connectTimeout(10, TimeUnit.SECONDS)
        .build()
}

fun getGson(): Gson {
    return GsonBuilder().create()
}

fun getRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .client(okHttpClient)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(GsonConverterFactory.create(gson))
        .baseUrl(BuildConfig.API_URL)
        .build()
}