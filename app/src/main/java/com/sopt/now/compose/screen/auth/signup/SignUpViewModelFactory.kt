package com.sopt.now.compose.screen.auth.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sopt.now.compose.repository.SignUpRepository

class SignUpViewModelFactory(private val signUpRepository: SignUpRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SignUpViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SignUpViewModel(signUpRepository) as T
        }
        throw IllegalArgumentException("알 수 없는 ViewModel 클래스")
    }
}