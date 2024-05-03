package com.sopt.now.compose.screen.auth.signup

sealed class SignUpState {
    data object Loading : SignUpState()
    data class Success(val message: String) : SignUpState()
    data class Error(val message: String) : SignUpState()
}