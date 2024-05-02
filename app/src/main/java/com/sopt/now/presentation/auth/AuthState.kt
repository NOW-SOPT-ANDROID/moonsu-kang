package com.sopt.now.presentation.auth

data class AuthState(
    val status: AuthStatus,
    val message: String = "",
    val isLoading: Boolean
)