package com.example.engineeraitest.presentation.tools

import android.content.Context
import android.view.LayoutInflater
import android.widget.Toast
import com.example.engineeraitest.R

val Context.layoutInflater: LayoutInflater
    get() = LayoutInflater.from(this)

fun Context?.showToast(message: String?) {
    if (this != null) Toast.makeText(this, message ?: getString(R.string.error_conversion_default), Toast.LENGTH_SHORT).show()
}