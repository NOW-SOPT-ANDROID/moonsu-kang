package com.sopt.now.compose.screen.main.mypage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.now.ServicePool
import com.sopt.now.compose.MemberIdInterceptor
import com.sopt.now.compose.repository.UserInfoRepository
import com.sopt.now.data.model.ResponseUserInfoDto
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyPageViewModel(
    private val userInfoRepository: UserInfoRepository
) : ViewModel() {
    val userInfoState = UserInfoState()
    private val _statusMessage = MutableLiveData<String>()
    val statusMessage: LiveData<String> = _statusMessage

    fun getUserInfo() {
        viewModelScope.launch {
            val result = userInfoRepository.getUserInfo()
            if (result.isSuccess) {
                result.getOrNull()?.data?.let { data ->
                    userInfoState.authenticationId = data.authenticationId
                    userInfoState.nickname = data.nickname
                    userInfoState.phone = data.phone
                }
                _statusMessage.postValue("사용자 정보 조회 성공")
            } else {
                _statusMessage.postValue("로드 실패: ${result.exceptionOrNull()?.message}")
            }
        }
    }
}