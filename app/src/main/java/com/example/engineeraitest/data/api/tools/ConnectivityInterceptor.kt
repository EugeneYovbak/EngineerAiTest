package com.example.engineeraitest.data.api.tools

import android.content.Context
import com.example.engineeraitest.R
import com.example.engineeraitest.domain.exception.NoConnectivityException
import com.example.engineeraitest.presentation.tools.isOnline
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class ConnectivityInterceptor(private val mContext: Context) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        if (!mContext.isOnline()) {
            throw NoConnectivityException(
                mContext.getString(
                    R.string.error_connection
                )
            )
        }

        val builder = chain.request().newBuilder()
        return chain.proceed(builder.build())
    }
}