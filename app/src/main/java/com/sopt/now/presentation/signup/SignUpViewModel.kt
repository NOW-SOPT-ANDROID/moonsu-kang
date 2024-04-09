package com.sopt.now.presentation.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sopt.now.model.User
import com.sopt.now.utils.SignUpValidator

class SignUpViewModel : ViewModel() {
    private val _signUpResult = MutableLiveData<String?>()
    val signUpResult: LiveData<String?> = _signUpResult

    private val _user = MutableLiveData<User?>()
    val user: LiveData<User?> = _user

    fun signUp(user: User) {
        val validationResult = validateSignUp(user)
        if (validationResult == null) {
            _user.value = user // 유효성 검사를 통과했다면, User 객체 저장
        }
        _signUpResult.value = validationResult
    }

    private fun validateSignUp(user: User): String? {
        return when {
            !SignUpValidator.isValidId(user.id) -> "아이디는 6~10 글자여야 합니다."
            !SignUpValidator.isValidPassword(user.pwd) -> "비밀번호는 8~12 글자여야 합니다."
            !SignUpValidator.isValidNickname(user.nickname) -> "닉네임은 한 글자 이상이어야 하며, 공백으로만 이루어질 수 없습니다."
            !SignUpValidator.isValidMbti(user.mbti) -> "MBTI를 올바르게 입력해주세요."
            else -> null // 유효성 검사 통과
        }
    }
}
