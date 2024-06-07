package com.sopt.now.compose.repository

import com.sopt.now.data.model.ResponseUserInfoDto

interface UserInfoRepository {
    suspend fun getUserInfo(): Result<ResponseUserInfoDto>
}