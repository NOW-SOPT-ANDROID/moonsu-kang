package com.sopt.now.compose.repositoryimpl

import com.sopt.now.AuthService
import com.sopt.now.compose.repository.LoginRepository
import com.sopt.now.data.model.RequestLoginDto
import com.sopt.now.data.model.ResponseLoginDto


class LoginRepositoryImpl(
    private val authService: AuthService
) : LoginRepository {
    override suspend fun login(loginDto: RequestLoginDto): Result<ResponseLoginDto> {
        return runCatching {
            val response = authService.login(loginDto)
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
