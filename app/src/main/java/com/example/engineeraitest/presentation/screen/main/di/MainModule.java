package com.example.engineeraitest.presentation.screen.main.di;

import com.example.engineeraitest.domain.UserRepository;
import com.example.engineeraitest.presentation.screen.main.presenter.MainPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class MainModule {

    @Provides
    @MainScope
    MainPresenter provideMainViewModel(UserRepository userRepository) {
        return new MainPresenter(userRepository);
    }
}
