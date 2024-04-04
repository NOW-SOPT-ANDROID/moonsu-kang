package com.sopt.now.compose.utils

// 회원가입 유효성 검사 클래스
class SignUpValidator {
    companion object {
        private const val NICKNAME_PATTERN = "^[a-zA-Z0-9]*$"
        private const val MBTI_PATTERN = "^(E|I)(S|N)(T|F)(J|P)$"
        private val nicknameRegex = Regex(NICKNAME_PATTERN)
        private val mbtiRegex = Regex(MBTI_PATTERN)

        fun isValidId(id: String): Boolean = id.length in 6..10
        fun isValidPassword(pwd: String): Boolean = pwd.length in 8..12
        fun isValidNickname(nickname: String): Boolean = nickname.isNotBlank() && nickname.matches(
            nicknameRegex
        )
        fun isValidMbti(mbti: String): Boolean = mbti.isNotBlank() && mbti.matches(mbtiRegex)
    }
}
