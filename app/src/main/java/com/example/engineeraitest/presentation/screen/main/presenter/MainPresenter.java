package com.example.engineeraitest.presentation.screen.main.presenter;

import com.example.engineeraitest.domain.UserRepository;
import com.example.engineeraitest.presentation.base.BasePresenter;
import com.example.engineeraitest.presentation.screen.main.MainView;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainPresenter extends BasePresenter<MainView> {

    private static final int USERS_LIMIT = 10;

    private UserRepository mUserRepository;

    private int offset = 0;

    @Inject
    public MainPresenter(UserRepository mUserRepository) {
        this.mUserRepository = mUserRepository;
    }

    public void getUsers() {
        mView.showLoadingIndicator();
        Disposable usersDisposable = mUserRepository.getUsers(offset, USERS_LIMIT)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doAfterTerminate(mView::hideLoadingIndicator)
                .subscribe(userList -> {
                            offset += userList.size();
                            mView.usersLoadSuccess(userList);
                        },
                        throwable -> mView.usersLoadError(throwable.getMessage())
                );
        mCompositeDisposable.add(usersDisposable);
    }
}
