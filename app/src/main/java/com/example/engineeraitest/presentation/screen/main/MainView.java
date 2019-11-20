package com.example.engineeraitest.presentation.screen.main;

import com.example.engineeraitest.domain.model.User;
import com.example.engineeraitest.presentation.base.BaseView;

import java.util.List;

public interface MainView extends BaseView {
    void usersLoadSuccess(List<User> userList);

    void usersLoadError(String message);
}
