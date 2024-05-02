package com.sopt.now.domain.usecase

import com.sopt.now.domain.model.UserInfo
import com.sopt.now.domain.repository.UserInfoRepository
import javax.inject.Inject

class FetchUserInfoUseCase @Inject constructor(
    private val userInfoRepository: UserInfoRepository
) {
    suspend fun execute(memberId: String): UserInfo {
        return userInfoRepository.fetchUserInfo(memberId)
    }
}