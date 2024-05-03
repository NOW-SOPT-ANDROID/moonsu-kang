package com.sopt.now.compose.utils

// 회원가입 유효성 검사 클래스
class SignUpValidator {
    companion object {
        private const val NICKNAME_PATTERN = "^[a-zA-Z0-9\\s]*$"  // 닉네임은 영문자와 숫자, 공백 허용
        private const val PHONE_PATTERN = "^01(?:0|1|[6-9])-(?:\\d{3}|\\d{4})-\\d{4}$"
        private val nicknameRegex = Regex(NICKNAME_PATTERN)
        private val phoneRegex = Regex(PHONE_PATTERN)

        fun isValidId(id: String): Boolean = id.length in 6..10
        fun isValidPassword(pwd: String): Boolean = pwd.length in 8..12
        fun isValidNickname(nickname: String): Boolean = nickname.isNotBlank() && nickname.matches(nicknameRegex)
        fun isValidPhone(phone: String): Boolean = phone.matches(phoneRegex)
    }
}
