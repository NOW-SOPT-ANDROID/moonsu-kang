package com.sopt.now.compose.screen.auth.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sopt.now.ServicePool
import com.sopt.now.data.model.RequestSignUpDto
import com.sopt.now.data.model.ResponseSignUpDto
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpViewModel : ViewModel() {
    private val _signUpState = MutableLiveData<SignUpState>()
    val signUpState: LiveData<SignUpState> = _signUpState

    fun signUp(dto: RequestSignUpDto) {
        _signUpState.value = SignUpState.Loading
        val call = ServicePool.authService.signUp(dto)
        call.enqueue(object : Callback<ResponseSignUpDto> {
            override fun onResponse(call: Call<ResponseSignUpDto>, response: Response<ResponseSignUpDto>) {
                if (response.isSuccessful) {
                    _signUpState.value = SignUpState.Success(response.body()?.message ?: "회원가입 성공")
                } else {
                    _signUpState.value = SignUpState.Error(response.body()?.message ?: "회원가입 실패")
                }
            }

            override fun onFailure(call: Call<ResponseSignUpDto>, t: Throwable) {
                _signUpState.value = SignUpState.Error("네트워크 오류: ${t.message}")
            }
        })
    }
}
