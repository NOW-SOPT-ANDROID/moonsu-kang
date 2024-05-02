package com.sopt.now.domain.usecase

import com.sopt.now.domain.model.SignUpInfo
import com.sopt.now.domain.model.SignUpResult
import com.sopt.now.domain.repository.SignUpRepository
import javax.inject.Inject

class SignUpUseCase @Inject constructor(
    private val signUpRepository: SignUpRepository
) {
    suspend fun execute(signUpInfo: SignUpInfo): SignUpResult {
        return try {
            signUpRepository.signUp(signUpInfo)
        } catch (e: Exception) {
            SignUpResult(false, e.message ?: "occur error")
        }
    }
}