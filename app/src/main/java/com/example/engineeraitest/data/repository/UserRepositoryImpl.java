package com.example.engineeraitest.data.repository;

import com.example.engineeraitest.data.api.UserApiService;
import com.example.engineeraitest.data.api.mapper.UserMapper;
import com.example.engineeraitest.domain.UserRepository;
import com.example.engineeraitest.domain.model.User;
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;

public class UserRepositoryImpl implements UserRepository {

    private UserApiService mUserApiService;

    public UserRepositoryImpl(UserApiService mUserApiService) {
        this.mUserApiService = mUserApiService;
    }

    @Override
    public Single<List<User>> getUsers(int offset, int limit) {
        return mUserApiService.getUsers(offset, limit)
                .map(userResponse -> {
                    if (!userResponse.isSuccessful() || !userResponse.body().getStatus()) {
                        throw new HttpException(userResponse);
                    }
                    return userResponse.body().getData().getUsers();
                })
                .toFlowable()
                .flatMap(Flowable::fromIterable)
                .map(new UserMapper())
                .toList();
    }
}
