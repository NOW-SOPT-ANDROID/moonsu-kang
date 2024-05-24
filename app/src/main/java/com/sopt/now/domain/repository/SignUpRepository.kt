package com.sopt.now.domain.repository

import com.sopt.now.domain.model.SignUpInfo
import com.sopt.now.domain.model.SignUpResult

interface SignUpRepository {
    suspend fun signUp(signUpInfo: SignUpInfo): SignUpResult
}