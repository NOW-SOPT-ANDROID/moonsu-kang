package com.sopt.now.presentation.main.mypage

import com.sopt.now.domain.model.UserInfo

data class UserInfoState(
    val userInfo: UserInfo? = null,
    val error: String? = null,
)