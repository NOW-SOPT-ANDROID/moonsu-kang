package com.sopt.now.compose.screen.auth.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.now.compose.repository.SignUpRepository
import com.sopt.now.data.model.RequestSignUpDto
import kotlinx.coroutines.launch

class SignUpViewModel(
    private val signUpRepository: SignUpRepository
) : ViewModel() {
    private val _signUpState = MutableLiveData<SignUpState>()
    val signUpState: LiveData<SignUpState> = _signUpState

    fun signUp(signUpDto: RequestSignUpDto) {
        viewModelScope.launch {
            _signUpState.value = SignUpState.Loading
            val result = signUpRepository.signUp(signUpDto)
            _signUpState.value = if (result.isSuccess) {
                SignUpState.Success("회원가입 성공")
            } else {
                SignUpState.Error(result.exceptionOrNull()?.message ?: "회원가입 실패")
            }
        }
    }
}