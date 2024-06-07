package com.sopt.now.compose.repositoryimpl

import com.sopt.now.AuthService
import com.sopt.now.compose.repository.UserInfoRepository
import com.sopt.now.data.model.ResponseUserInfoDto

class UserInfoRepositoryImpl(
    private val authService: AuthService
) : UserInfoRepository {
    override suspend fun getUserInfo(): Result<ResponseUserInfoDto> {
        return runCatching {
            val response = authService.getUserInfo()
            if (response.isSuccessful) {
                response.body()?.let {
                    Result.success(it)
                } ?: Result.failure(Exception("Body is null"))
            } else {
                Result.failure(Exception(response.errorBody()?.string()))
            }
        }.getOrElse {
            Result.failure(it)
        }
    }

}