package com.sopt.now.presentation.auth.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.now.domain.model.LoginInfo
import com.sopt.now.domain.usecase.LoginUseCase
import com.sopt.now.presentation.auth.AuthStatus
import com.sopt.now.presentation.auth.AuthState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel() {
    private val _loginState = MutableStateFlow(AuthState(AuthStatus.UNINITIALIZED,"",false))
    val loginState: StateFlow<AuthState> = _loginState.asStateFlow()

    fun login(authenticationId: String, password: String) {
        _loginState.value = AuthState(AuthStatus.PROCESSING, "", true)
        viewModelScope.launch {
            val result = loginUseCase.execute(LoginInfo(authenticationId, password))
            _loginState.value = AuthState(if (result.success) AuthStatus.SUCCESS else AuthStatus.FAILED, result.message, false)
        }
    }
}
