package com.sopt.now.compose.repository

import com.sopt.now.data.model.RequestSignUpDto
import com.sopt.now.data.model.ResponseSignUpDto

interface SignUpRepository {
    suspend fun signUp(signUpDto: RequestSignUpDto): Result<ResponseSignUpDto>
}