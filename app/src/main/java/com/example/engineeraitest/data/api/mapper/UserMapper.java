package com.example.engineeraitest.data.api.mapper;

import com.example.engineeraitest.data.api.model.UserResponse;
import com.example.engineeraitest.domain.model.User;

import java.util.ArrayList;

import io.reactivex.functions.Function;

public class UserMapper implements Function<UserResponse.UserDataResponse, User> {

    @Override
    public User apply(UserResponse.UserDataResponse userDataResponse) throws Exception {
        return new User(
                userDataResponse.getName() != null ? userDataResponse.getName() : "",
                userDataResponse.getImage() != null ? userDataResponse.getImage() : "",
                userDataResponse.getItems() != null ? userDataResponse.getItems() : new ArrayList<>()
        );
    }
}
