package com.sopt.now.compose.screen.auth.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sopt.now.ServicePool
import com.sopt.now.compose.MemberIdInterceptor
import com.sopt.now.data.model.RequestLoginDto
import com.sopt.now.data.model.ResponseLoginDto
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel : ViewModel() {
    private val _loginState = MutableLiveData<LoginState>()
    val loginState: LiveData<LoginState> = _loginState

    fun login(dto: RequestLoginDto) {
        _loginState.value = LoginState.Loading
        val call = ServicePool.authService.login(dto)
        call.enqueue(object : Callback<ResponseLoginDto> {
            override fun onResponse(call: Call<ResponseLoginDto>, response: Response<ResponseLoginDto>) {
                if (response.isSuccessful && response.body()?.code == 200) {
                    val successMessage = "로그인 성공: Member ID = ${MemberIdInterceptor.memberId}"
                    _loginState.value = LoginState.Success(successMessage)
                } else {
                    _loginState.value = LoginState.Error(response.body()?.message ?: "로그인 실패")
                }
            }

            override fun onFailure(call: Call<ResponseLoginDto>, t: Throwable) {
                _loginState.value = LoginState.Error("네트워크 오류: ${t.message}")
            }
        })
    }
}