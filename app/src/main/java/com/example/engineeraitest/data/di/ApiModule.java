package com.example.engineeraitest.data.di;

import com.example.engineeraitest.BuildConfig;
import com.example.engineeraitest.app.EngineerAiTestApp;
import com.example.engineeraitest.data.api.UserApiService;
import com.example.engineeraitest.data.api.tools.ConnectivityInterceptor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApiModule {

    @Provides
    @Singleton
    Interceptor provideInterceptor() {
        return new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
    }

    @Provides
    @Singleton
    ConnectivityInterceptor provideConnectivityInterceptor(EngineerAiTestApp engineerAiTestApp) {
        return new ConnectivityInterceptor(engineerAiTestApp);
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(Interceptor interceptor, ConnectivityInterceptor connectivityInterceptor) {
        return new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .addInterceptor(connectivityInterceptor)
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(10, TimeUnit.SECONDS)
                .build();
    }

    @Provides
    @Singleton
    Gson provideGson() {
        return new GsonBuilder().create();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(OkHttpClient okHttpClient, Gson gson) {
        return new Retrofit.Builder()
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(BuildConfig.API_URL)
                .build();
    }

    @Provides
    @Singleton
    UserApiService provideUserApiService(Retrofit retrofit) {
        return retrofit.create(UserApiService.class);
    }
}
