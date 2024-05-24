package com.sopt.now.domain.model

import kotlinx.serialization.SerialName

data class SignUpInfo(
    val authenticationId: String,
    val password: String,
    val nickname: String,
    val phone: String
)
