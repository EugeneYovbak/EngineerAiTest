package com.example.engineeraitest.app.di;

import com.example.engineeraitest.app.EngineerAiTestApp;
import com.example.engineeraitest.presentation.screen.main.di.MainComponent;

public class DependencyGraph {

    private AppComponent mAppComponent;

    private MainComponent mMainComponent;

    public DependencyGraph(EngineerAiTestApp mEngineerAiTestApp) {
        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(mEngineerAiTestApp))
                .build();
    }

    public MainComponent initMainComponent() {
        mMainComponent = mAppComponent.plusMainComponent();
        return mMainComponent;
    }

    public void releaseMainComponent() {
        mMainComponent = null;
    }
}
