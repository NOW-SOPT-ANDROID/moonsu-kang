package com.sopt.now.domain.usecase

import com.sopt.now.domain.repository.UserInfoRepository
import javax.inject.Inject

class GetMemberIdUseCase @Inject constructor(
    private val userInfoRepository: UserInfoRepository
) {
    fun execute(): String? = userInfoRepository.getMemberId()
}