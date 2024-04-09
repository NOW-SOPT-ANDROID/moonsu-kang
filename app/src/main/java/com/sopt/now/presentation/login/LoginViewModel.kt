package com.sopt.now.presentation.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sopt.now.model.User

class LoginViewModel : ViewModel() {
    // 로그인 정보를 담은 User를 LiveData로 관리
    private val _userInfo = MutableLiveData<User>()
    val userInfo: LiveData<User> = _userInfo

    /**
     * 로그인한 사용자의 정보를 담고 있는 User 객체
     * 이 함수를 통해 _userInfo의 값을 업데이트할 수 있고,
     * 관찰자에게 자동으로 알려지게 됨.
     */
    fun setUserInfo(user: User) {
        _userInfo.value = user
    }

    /**
     * 로그인 유효성 검사
     * 입력받은 아이디와 비밀번호가 현재 로그인한 사용자의 정보와 일치하는지 확인
     * 일치하면 true, 그렇지 않으면 false
     */
    fun loginValid(id: String, pwd: String): Boolean {
        return userInfo.value?.let {
            it.id == id && it.pwd == pwd
        } ?: false
    }
}
