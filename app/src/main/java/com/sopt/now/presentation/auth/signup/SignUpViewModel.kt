package com.sopt.now.presentation.auth.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.now.domain.model.SignUpInfo
import com.sopt.now.domain.usecase.SignUpUseCase
import com.sopt.now.presentation.auth.AuthState
import com.sopt.now.presentation.auth.AuthStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUpUseCase: SignUpUseCase
) : ViewModel() {
    private val _signUpState = MutableStateFlow(AuthState(AuthStatus.UNINITIALIZED,"",false))
    val signUpState: StateFlow<AuthState> = _signUpState.asStateFlow()

    fun signUp(authenticationId: String, password: String, nickname: String, phone: String) {
        _signUpState.value = AuthState(AuthStatus.PROCESSING, "", true)
        viewModelScope.launch {
            val signUpInfo = SignUpInfo(authenticationId, password, nickname, phone)
            val result = signUpUseCase.execute(signUpInfo)
            _signUpState.value = AuthState(if (result.success) AuthStatus.SUCCESS else AuthStatus.FAILED, result.message, false)
        }
    }
}
