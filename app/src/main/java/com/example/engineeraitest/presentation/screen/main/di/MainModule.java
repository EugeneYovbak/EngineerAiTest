package com.example.engineeraitest.presentation.screen.main.di;

import com.example.engineeraitest.domain.UserRepository;
import com.example.engineeraitest.presentation.screen.main.MainViewModel;

import dagger.Module;
import dagger.Provides;

@Module
public class MainModule {

    @Provides
    @MainScope
    MainViewModel provideMainViewModel(UserRepository userRepository) {
        return new MainViewModel(userRepository);
    }
}
