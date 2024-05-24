package com.sopt.now.domain.usecase

import com.sopt.now.domain.repository.LoginRepository
import com.sopt.now.domain.model.LoginResult
import com.sopt.now.domain.model.LoginInfo
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val loginRepository: LoginRepository
) {
    suspend fun execute(loginInfo: LoginInfo): LoginResult {
        return try {
            loginRepository.login(loginInfo)
        } catch (e: Exception) {
            LoginResult(false, e.message ?: "Unknown error")
        }
    }

}
