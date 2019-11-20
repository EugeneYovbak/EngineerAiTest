package com.example.engineeraitest.presentation.screen.main

import androidx.lifecycle.LiveData
import com.example.engineeraitest.domain.UserRepository
import com.example.engineeraitest.domain.model.User
import com.example.engineeraitest.presentation.base.BaseViewModel
import com.example.engineeraitest.presentation.base.DataWrapper
import com.example.engineeraitest.presentation.tools.SingleLiveEvent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainViewModel(private var mUserRepository: UserRepository) : BaseViewModel() {

    companion object {
        private const val USERS_LIMIT = 10
    }

    private var offset: Int = 0

    private val usersData = SingleLiveEvent<DataWrapper<List<User>>>()
    fun getUsersData(): LiveData<DataWrapper<List<User>>> = usersData

    fun getUsers() {
        usersData.value = DataWrapper(DataWrapper.Status.LOADING)
        val usersDisposable = mUserRepository.getUsers(offset, USERS_LIMIT)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    offset += it.size
                    usersData.value = DataWrapper(DataWrapper.Status.SUCCESS, data = it)
                },
                { usersData.value = DataWrapper(DataWrapper.Status.ERROR, throwable = it) }
            )
        compositeDisposable.add(usersDisposable)
    }
}