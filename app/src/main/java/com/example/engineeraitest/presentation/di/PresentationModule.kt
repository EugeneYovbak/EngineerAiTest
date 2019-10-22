package com.example.engineeraitest.presentation.di

import com.example.engineeraitest.presentation.screen.main.MainViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel { MainViewModel(get()) }
}