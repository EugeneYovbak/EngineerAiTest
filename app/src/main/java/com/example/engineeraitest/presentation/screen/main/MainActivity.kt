package com.example.engineeraitest.presentation.screen.main

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.engineeraitest.R
import com.example.engineeraitest.domain.model.User
import com.example.engineeraitest.presentation.base.BaseActivity
import com.example.engineeraitest.presentation.base.DataWrapper
import com.example.engineeraitest.presentation.screen.main.adapter.UserAdapter
import com.example.engineeraitest.presentation.tools.addPagingScrollListener
import com.example.engineeraitest.presentation.tools.showToast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity() {

    private val mViewModel by viewModel<MainViewModel>()

    private lateinit var mAdapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        initUserList()

        mViewModel.getUsersData().observe(this, Observer<DataWrapper<List<User>>> {
            handleUsers(it)
        })
        mViewModel.getUsers()
    }

    private fun initUserList() {
        mAdapter = UserAdapter()
        rv_users.layoutManager = LinearLayoutManager(this)
        rv_users.addPagingScrollListener {
            if (!isInProgress) mViewModel.getUsers()
        }
        rv_users.adapter = mAdapter
    }

    private fun handleUsers(dataWrapper: DataWrapper<List<User>>) {
        when (dataWrapper.status) {
            DataWrapper.Status.LOADING -> {
                showProgress()
            }
            DataWrapper.Status.SUCCESS -> {
                hideProgress()
                mAdapter.addUserList(dataWrapper.data!!)
            }
            DataWrapper.Status.ERROR -> {
                hideProgress()
                showToast(dataWrapper.throwable?.message)
            }
        }
    }
}
