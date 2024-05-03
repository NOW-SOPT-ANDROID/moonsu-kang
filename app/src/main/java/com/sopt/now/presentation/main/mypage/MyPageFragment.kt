package com.sopt.now.presentation.main.mypage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.sopt.now.databinding.FragmentMypageBinding
import com.sopt.now.utils.toast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MyPageFragment : Fragment() {
    private var _binding: FragmentMypageBinding? = null
    private val binding get() = requireNotNull(_binding)
    private val viewModel: MyPageViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentMypageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeUserInfoState()
        viewModel.fetchUserInfo()
    }

    private fun observeUserInfoState() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.userInfoState.collect { state ->
                state.userInfo?.let { user ->
                    binding.tvMypageIdValue.text = user.authenticationId
                    binding.tvMypageNickname.text = user.nickname
                    binding.tvMypagePhoneValue.text = user.phone
                } ?: run {
                    if (state.error != null) {
                        toast(state.error)
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
