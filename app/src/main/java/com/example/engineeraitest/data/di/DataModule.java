package com.example.engineeraitest.data.di;

import com.example.engineeraitest.data.api.UserApiService;
import com.example.engineeraitest.data.repository.UserRepositoryImpl;
import com.example.engineeraitest.domain.UserRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DataModule {

    @Provides
    @Singleton
    UserRepository provideUserRepository(UserApiService userApiService) {
        return new UserRepositoryImpl(userApiService);
    }
}
