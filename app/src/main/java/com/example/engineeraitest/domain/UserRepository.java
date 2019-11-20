package com.example.engineeraitest.domain;

import com.example.engineeraitest.domain.model.User;

import java.util.List;

import io.reactivex.Single;

public interface UserRepository {
    Single<List<User>> getUsers(int offset, int limit);
}
