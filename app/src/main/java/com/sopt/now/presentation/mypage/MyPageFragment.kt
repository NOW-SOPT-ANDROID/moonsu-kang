package com.sopt.now.presentation.mypage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.sopt.now.MainActivity
import com.sopt.now.R
import com.sopt.now.databinding.FragmentMypageBinding
import com.sopt.now.model.User
import com.sopt.now.utils.toast

class MyPageFragment : Fragment() {
    private var _binding: FragmentMypageBinding? = null
    private val binding: FragmentMypageBinding
        get() = requireNotNull(_binding) { "바인딩 객체 좀 생성해주세요 제발!!" }
    // MyPageViewModel을 Activity 범위에서 사용하여 공유 데이터 관리
    private val viewModel: MyPageViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentMypageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getUserInfo()
        observeUserInfo()
    }

    private fun getUserInfo() {
        arguments?.getParcelable<User>(MainActivity.USER_DATA)?.let { user ->
            viewModel.setUserInfo(user)
        }
    }

    private fun observeUserInfo() {
        viewModel.userInfo.observe(viewLifecycleOwner) { user ->
            updateUI(user)
        }
    }

    private fun updateUI(user: User?) {
        if (user != null) {
            with(binding) {
                tvMypageNickname.text = user.nickname
                tvMypageIdValue.text = user.id
                tvMypagePwdValue.text = user.pwd
                tvMypageMbtiValue.text = user.mbti
            }
        } else {
            toast(R.string.mypage_failed_get_user)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}