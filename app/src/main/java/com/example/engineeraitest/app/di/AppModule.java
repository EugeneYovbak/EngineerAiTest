package com.example.engineeraitest.app.di;

import com.example.engineeraitest.app.EngineerAiTestApp;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private EngineerAiTestApp mEngineerAiTestApp;

    public AppModule(EngineerAiTestApp engineerAiTestApp) {
        mEngineerAiTestApp = engineerAiTestApp;
    }

    @Provides
    @Singleton
    EngineerAiTestApp provideEngineerAiTestApp() {
        return mEngineerAiTestApp;
    }
}
