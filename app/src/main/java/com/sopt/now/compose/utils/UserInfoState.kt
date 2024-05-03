package com.sopt.now.compose.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

class UserInfoState {
    val userId = mutableStateOf("")
    val userPwd = mutableStateOf("")
    val nickname = mutableStateOf("")
    val phone = mutableStateOf("")

    val userIdError = mutableStateOf<String?>(null)
    val userPwdError = mutableStateOf<String?>(null)
    val nicknameError = mutableStateOf<String?>(null)
    val phoneError = mutableStateOf<String?>(null)

    fun validate(): Boolean {
        userIdError.value = if (SignUpValidator.isValidId(userId.value)) null else "아이디는 6~10 글자여야 합니다."
        userPwdError.value = if (SignUpValidator.isValidPassword(userPwd.value)) null else "비밀번호는 8~12 글자여야 합니다."
        nicknameError.value = if (SignUpValidator.isValidNickname(nickname.value)) null else "닉네임은 한 글자 이상이어야 하며, 공백으로만 이루어질 수 없습니다."
        phoneError.value = if (SignUpValidator.isValidPhone(phone.value)) null else "전화번호 형식이 올바르지 않습니다."

        return listOf(userIdError, userPwdError, nicknameError, phoneError).all { it.value == null }
    }
}

@Composable
fun rememberUserInfoState() = remember { UserInfoState() }

