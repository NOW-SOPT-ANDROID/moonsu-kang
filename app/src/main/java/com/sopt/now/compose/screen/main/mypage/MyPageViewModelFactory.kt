package com.sopt.now.compose.screen.main.mypage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sopt.now.compose.repository.UserInfoRepository

class MyPageViewModelFactory(private val userInfoRepository: UserInfoRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MyPageViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MyPageViewModel(userInfoRepository) as T
        }
        throw IllegalArgumentException("알 수 없는 ViewModel 클래스")
    }
}