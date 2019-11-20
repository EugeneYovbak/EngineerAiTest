package com.example.engineeraitest.presentation.base;

import android.view.View;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.example.engineeraitest.R;

public abstract class BaseActivity extends AppCompatActivity {

    protected void showProgress() {
        findViewById(R.id.progress_bar).setVisibility(View.VISIBLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }

    protected void hideProgress() {
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        findViewById(R.id.progress_bar).setVisibility(View.GONE);
    }

    protected boolean isInProgress() {
        return findViewById(R.id.progress_bar).getVisibility() == View.VISIBLE;
    }
}
