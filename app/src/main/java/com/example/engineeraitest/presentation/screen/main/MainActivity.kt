package com.example.engineeraitest.presentation.screen.main

import android.os.Bundle
import androidx.lifecycle.Observer
import com.example.engineeraitest.R
import com.example.engineeraitest.domain.model.User
import com.example.engineeraitest.presentation.base.BaseActivity
import com.example.engineeraitest.presentation.base.DataWrapper
import com.example.engineeraitest.presentation.tools.showToast
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity() {

    private val mViewModel by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        mViewModel.getUsersData().observe(this, Observer<DataWrapper<List<User>>> {
            handleUsers(it)
        })
        mViewModel.getUsers()
    }

    private fun handleUsers(dataWrapper: DataWrapper<List<User>>) {
        when (dataWrapper.status) {
            DataWrapper.Status.LOADING -> {
                showProgress()
            }
            DataWrapper.Status.SUCCESS -> {
                hideProgress()
//                setUserList(dataWrapper.data!!)
            }
            DataWrapper.Status.ERROR -> {
                hideProgress()
                showToast(dataWrapper.throwable?.message)
            }
        }
    }
}
