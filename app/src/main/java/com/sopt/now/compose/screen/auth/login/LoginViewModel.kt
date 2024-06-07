package com.sopt.now.compose.screen.auth.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.now.compose.repository.LoginRepository
import com.sopt.now.data.model.RequestLoginDto
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginRepository: LoginRepository
) : ViewModel() {
    private val _loginState = MutableLiveData<LoginState>()
    val loginState: LiveData<LoginState> = _loginState

    fun login(loginDto: RequestLoginDto) {
        viewModelScope.launch {
            _loginState.value = LoginState.Loading
            val result = loginRepository.login(loginDto)
            _loginState.value = if (result.isSuccess) {
                LoginState.Success("로그인 성공")
            } else {
                LoginState.Error(result.exceptionOrNull()?.message ?: "로그인 실패")
            }
        }
    }
}
