package com.example.engineeraitest.presentation.screen.main.di;

import com.example.engineeraitest.presentation.screen.main.MainActivity;

import dagger.Subcomponent;

@Subcomponent(modules = MainModule.class)
@MainScope
public interface MainComponent {
    void inject(MainActivity mainActivity);
}
