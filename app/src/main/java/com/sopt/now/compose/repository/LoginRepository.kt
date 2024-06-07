package com.sopt.now.compose.repository

import com.sopt.now.data.model.RequestLoginDto
import com.sopt.now.data.model.ResponseLoginDto

interface LoginRepository {
    suspend fun login(loginDto: RequestLoginDto): Result<ResponseLoginDto>
}