package com.sopt.now.compose.repositoryimpl

import com.sopt.now.AuthService
import com.sopt.now.compose.repository.SignUpRepository
import com.sopt.now.data.model.RequestSignUpDto
import com.sopt.now.data.model.ResponseSignUpDto

class SignUpRepositoryImpl(
    private val authService: AuthService
) : SignUpRepository {
    override suspend fun signUp(signUpDto: RequestSignUpDto): Result<ResponseSignUpDto> {
        return runCatching {
            val response = authService.signUp(signUpDto)
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