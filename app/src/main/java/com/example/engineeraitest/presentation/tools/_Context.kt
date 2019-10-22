package com.example.engineeraitest.presentation.tools

import android.content.Context
import android.net.ConnectivityManager
import android.view.LayoutInflater
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.engineeraitest.R

val Context.layoutInflater: LayoutInflater
    get() = LayoutInflater.from(this)

fun Context.color(res: Int): Int = ContextCompat.getColor(this, res)

fun Context.isOnline(): Boolean {
    val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val netInfo = connectivityManager.activeNetworkInfo
    return netInfo != null && netInfo.isConnected
}

fun Context?.showToast(message: String?) {
    if (this != null) Toast.makeText(this, message ?: getString(R.string.error_conversion_default), Toast.LENGTH_SHORT).show()
}