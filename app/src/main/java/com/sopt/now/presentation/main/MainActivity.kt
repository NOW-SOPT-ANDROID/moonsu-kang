package com.sopt.now.presentation.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.sopt.now.databinding.ActivityMainBinding
import com.sopt.now.model.User

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        getUserInfo()
        initUI()
    }

    private fun getUserInfo() {
        val user: User? = intent.getParcelableExtra("user_data") // User 객체 받기
        if (user != null) {
            viewModel.setUserInfo(user) // ViewModel에 User 객체 적용
        }
    }

    private fun initUI() {
        with(binding) {
            tvMainNickname.text = viewModel.userInfo.value?.nickname
            tvMainIdValue.text = viewModel.userInfo.value?.id
            tvMainPwdValue.text = viewModel.userInfo.value?.pwd
            tvMainMbtiValue.text = viewModel.userInfo.value?.mbti
        }
    }
}