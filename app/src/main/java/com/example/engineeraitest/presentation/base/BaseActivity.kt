package com.example.engineeraitest.presentation.base

import android.view.View
import android.view.WindowManager
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import com.example.engineeraitest.R

abstract class BaseActivity : AppCompatActivity() {

    fun showProgress() {
        findViewById<ProgressBar>(R.id.progress_bar).visibility = View.VISIBLE
        window.setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
    }

    fun hideProgress() {
        window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
        findViewById<ProgressBar>(R.id.progress_bar).visibility = View.GONE
    }

    fun isInProgress(): Boolean {
        return findViewById<ProgressBar>(R.id.progress_bar).visibility == View.VISIBLE
    }
}