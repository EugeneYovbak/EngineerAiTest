package com.example.engineeraitest.data.api.tools;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.engineeraitest.R;
import com.example.engineeraitest.domain.exception.NoConnectivityException;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

public class ConnectivityInterceptor implements Interceptor {

    private Context mContext;

    public ConnectivityInterceptor(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {
        ConnectivityManager connectivityManager = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = connectivityManager.getActiveNetworkInfo();
        if (netInfo == null || !netInfo.isConnected()) {
            throw new NoConnectivityException(mContext.getString(R.string.error_connection));
        }
        return chain.proceed(chain.request().newBuilder().build());
    }
}
