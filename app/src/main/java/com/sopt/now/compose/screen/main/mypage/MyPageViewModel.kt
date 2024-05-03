package com.sopt.now.compose.screen.main.mypage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sopt.now.ServicePool
import com.sopt.now.compose.MemberIdInterceptor
import com.sopt.now.data.model.ResponseUserInfoDto
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyPageViewModel : ViewModel() {
    val userInfoState = UserInfoState()
    private val _statusMessage = MutableLiveData<String>()
    val statusMessage: LiveData<String> = _statusMessage

    fun fetchUserInfo() {
        val memberId = MemberIdInterceptor.memberId
        if (memberId == null) {
            _statusMessage.postValue("Member ID not found")
            return
        }

        ServicePool.authService.getUserInfo(memberId).enqueue(object : Callback<ResponseUserInfoDto> {
            override fun onResponse(call: Call<ResponseUserInfoDto>, response: Response<ResponseUserInfoDto>) {
                if (response.isSuccessful && response.body()?.code == 200) {
                    response.body()?.data?.let { data ->
                        userInfoState.authenticationId = data.authenticationId
                        userInfoState.nickname = data.nickname
                        userInfoState.phone = data.phone
                    }
                } else {
                    _statusMessage.postValue("회원정보 조회 실패: ${response.errorBody()?.string() ?: "Unknown error"}")
                }
            }

            override fun onFailure(call: Call<ResponseUserInfoDto>, t: Throwable) {
                _statusMessage.postValue("네트워크 오류: ${t.message}")
            }
        })
    }
}