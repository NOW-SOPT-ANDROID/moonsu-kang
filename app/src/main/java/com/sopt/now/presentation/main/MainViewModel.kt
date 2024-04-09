package com.sopt.now.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sopt.now.model.User

class MainViewModel : ViewModel() {
    // 로그인 정보를 담은 User를 LiveData로 관리
    private val _userInfo = MutableLiveData<User>()
    val userInfo: LiveData<User> get() = _userInfo

    fun setUserInfo(user: User) {
        _userInfo.value = user
    }
}