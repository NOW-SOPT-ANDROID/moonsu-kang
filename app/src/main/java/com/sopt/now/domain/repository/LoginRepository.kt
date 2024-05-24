package com.sopt.now.domain.repository

import com.sopt.now.domain.model.LoginResult
import com.sopt.now.domain.model.LoginInfo

interface LoginRepository {
    suspend fun login(loginInfo: LoginInfo): LoginResult
}