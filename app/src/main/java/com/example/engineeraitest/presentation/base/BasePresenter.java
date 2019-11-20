package com.example.engineeraitest.presentation.base;

import io.reactivex.disposables.CompositeDisposable;

public abstract class BasePresenter<T extends BaseView> {

    protected T mView;
    protected CompositeDisposable mCompositeDisposable;

    public void onAttach(T view) {
        mView = view;
        mCompositeDisposable = new CompositeDisposable();
    }

    public void onDetach() {
        mCompositeDisposable.dispose();
        mView = null;
    }
}
