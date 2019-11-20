package com.example.engineeraitest.presentation.base;

public class DataWrapper<T> {

    private Status status;
    private T data;
    private Throwable throwable;

    public DataWrapper(Status status) {
        this.status = status;
    }

    public DataWrapper(Status status, T data) {
        this.status = status;
        this.data = data;
    }

    public DataWrapper(Status status, Throwable throwable) {
        this.status = status;
        this.throwable = throwable;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public void setThrowable(Throwable throwable) {
        this.throwable = throwable;
    }

    public enum Status {
        LOADING, SUCCESS, ERROR
    }
}
