package com.sopt.now.presentation.main.mypage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.now.domain.model.UserInfo
import com.sopt.now.domain.usecase.FetchUserInfoUseCase
import com.sopt.now.domain.usecase.GetMemberIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyPageViewModel @Inject constructor(
    private val getMemberIdUseCase: GetMemberIdUseCase,
    private val fetchUserInfoUseCase: FetchUserInfoUseCase
) : ViewModel() {
    private val _userInfoState = MutableStateFlow(UserInfoState())
    val userInfoState: StateFlow<UserInfoState> = _userInfoState.asStateFlow()

    fun fetchUserInfo() {
        viewModelScope.launch {
            val memberId = getMemberIdUseCase.execute()
            if (memberId != null) {
                try {
                    val fetchedUserInfo = fetchUserInfoUseCase.execute(memberId)
                    _userInfoState.value = UserInfoState(userInfo = fetchedUserInfo)
                } catch (e: Exception) {
                    _userInfoState.value = UserInfoState(error = e.message ?: "오류가 발생했습니다.")
                }
            } else {
                _userInfoState.value = UserInfoState(error = "Member ID를 찾을 수 없습니다.")
            }
        }
    }
}
