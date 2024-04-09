package com.sopt.now.compose.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

class UserInfoState {
    val userId = mutableStateOf("")
    val userPwd = mutableStateOf("")
    val nickname = mutableStateOf("")
    val mbti = mutableStateOf("")

    val userIdError = mutableStateOf<String?>(null)
    val userPwdError = mutableStateOf<String?>(null)
    val nicknameError = mutableStateOf<String?>(null)
    val mbtiError = mutableStateOf<String?>(null)

    fun validate(): Boolean {
        userIdError.value = if (SignUpValidator.isValidId(userId.value)) null else "아이디는 6~10 글자여야 합니다."
        userPwdError.value = if (SignUpValidator.isValidPassword(userPwd.value)) null else "비밀번호는 8~12 글자여야 합니다."
        nicknameError.value = if (SignUpValidator.isValidNickname(nickname.value)) null else "닉네임은 한 글자 이상이어야 하며, 공백으로만 이루어질 수 없습니다."
        mbtiError.value = if (SignUpValidator.isValidMbti(mbti.value)) null else "MBTI를 올바르게 입력해주세요."

        return listOf(userIdError, userPwdError, nicknameError, mbtiError).all { it.value == null }
    }
}

@Composable
fun rememberUserInfoState() = remember { UserInfoState() }
