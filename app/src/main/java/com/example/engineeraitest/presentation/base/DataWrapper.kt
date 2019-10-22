package com.example.engineeraitest.presentation.base

class DataWrapper<T>(val status: Status, val data: T? = null, val throwable: Throwable? = null) {

    enum class Status {
        LOADING, SUCCESS, ERROR
    }
}