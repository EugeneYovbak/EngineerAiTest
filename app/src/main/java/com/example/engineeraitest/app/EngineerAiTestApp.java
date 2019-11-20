package com.example.engineeraitest.app;

import android.app.Application;

import com.example.engineeraitest.app.di.DependencyGraph;

public class EngineerAiTestApp extends Application {

    private static DependencyGraph mDependencyGraph;

    @Override
    public void onCreate() {
        super.onCreate();
        mDependencyGraph = new DependencyGraph(this);
    }

    public static DependencyGraph getDependencyGraph() {
        return mDependencyGraph;
    }
}
