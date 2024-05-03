package com.sopt.now.compose.screen.main.mypage

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class UserInfoState {
    var authenticationId by mutableStateOf<String?>(null)
    var nickname by mutableStateOf<String?>(null)
    var phone by mutableStateOf<String?>(null)

    val isLoaded: Boolean
        get() = authenticationId != null && nickname != null && phone != null
}