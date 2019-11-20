package com.example.engineeraitest.app.di;

import com.example.engineeraitest.data.di.ApiModule;
import com.example.engineeraitest.data.di.DataModule;
import com.example.engineeraitest.presentation.screen.main.di.MainComponent;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = {AppModule.class, ApiModule.class, DataModule.class})
@Singleton
public interface AppComponent {

    MainComponent plusMainComponent();
}
