package com.example.engineeraitest.app.di

import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.AppGlideModule
import com.example.engineeraitest.data.di.apiModule
import com.example.engineeraitest.data.di.dataModule
import com.example.engineeraitest.presentation.di.presentationModule

val appModules = listOf(
    apiModule,
    dataModule,

    presentationModule
)

@GlideModule
class GlideModule : AppGlideModule()