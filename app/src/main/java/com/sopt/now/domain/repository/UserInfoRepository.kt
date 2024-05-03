package com.sopt.now.domain.repository

import com.sopt.now.domain.model.UserInfo

interface UserInfoRepository {
    fun getMemberId(): String?
    suspend fun fetchUserInfo(memberId: String): UserInfo
}