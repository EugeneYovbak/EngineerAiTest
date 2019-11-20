package com.example.engineeraitest.presentation.screen.main;

import androidx.lifecycle.LiveData;

import com.example.engineeraitest.domain.UserRepository;
import com.example.engineeraitest.domain.model.User;
import com.example.engineeraitest.presentation.base.BaseViewModel;
import com.example.engineeraitest.presentation.base.DataWrapper;
import com.example.engineeraitest.presentation.tools.SingleLiveEvent;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainViewModel extends BaseViewModel {

    private static final int USERS_LIMIT = 10;

    private UserRepository mUserRepository;

    private SingleLiveEvent<DataWrapper<List<User>>> usersData = new SingleLiveEvent<>();

    private int offset = 0;

    public MainViewModel(UserRepository mUserRepository) {
        this.mUserRepository = mUserRepository;
    }

    public LiveData<DataWrapper<List<User>>> getUsersData() {
        return usersData;
    }

    public void getUsers() {
        usersData.setValue(new DataWrapper(DataWrapper.Status.LOADING));
        Disposable usersDisposable = mUserRepository.getUsers(offset, USERS_LIMIT)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(userList -> {
                            offset += userList.size();
                            usersData.setValue(new DataWrapper(DataWrapper.Status.SUCCESS, userList));
                        },
                        throwable -> usersData.setValue(new DataWrapper(DataWrapper.Status.SUCCESS, throwable))
                );
        compositeDisposable.add(usersDisposable);
    }
}
