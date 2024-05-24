package com.sopt.now.presentation.auth.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.sopt.now.presentation.main.MainActivity
import com.sopt.now.databinding.ActivityLoginBinding
import com.sopt.now.presentation.auth.AuthStatus
import com.sopt.now.presentation.auth.signup.SignUpActivity
import com.sopt.now.utils.toast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    private val binding by lazy { ActivityLoginBinding.inflate(layoutInflater) }
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupClickListeners()
        observeLoginState()
    }

    private fun setupClickListeners() {
        binding.btnLoginSign.setOnClickListener {
            val inputId = binding.edtLoginId.text.toString()
            val inputPwd = binding.edtLoginPwd.text.toString()
            viewModel.login(inputId, inputPwd)
        }
        binding.btnLoginSignup.setOnClickListener {
            handleSignupClick()
        }
    }

    private fun observeLoginState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.loginState.collect { state ->
                    handleLoading(state.isLoading)
                    when (state.status) {
                        AuthStatus.SUCCESS -> {
                            navigateToHome()
                            showMemberIdToast()
                        }
                        AuthStatus.FAILED -> toast(state.message)
                        else -> {}
                    }
                }
            }
        }
    }

    private fun getMemberIdFromPreferences(): String? {
        val sharedPreferences = getSharedPreferences("memberId", Context.MODE_PRIVATE)
        return sharedPreferences.getString("memberId", "Not Found")
    }

    private fun showMemberIdToast() {
        val memberId = getMemberIdFromPreferences()
        toast("Member ID: $memberId", Toast.LENGTH_LONG) // 유틸리티 함수 사용
    }

    private fun handleLoading(show: Boolean) {
        binding.pgbLoginLoading.visibility = if (show) View.VISIBLE else View.GONE
    }

    private fun handleSignupClick() {
        val intent = Intent(this, SignUpActivity::class.java)
        startActivity(intent)
    }

    private fun navigateToHome() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun toast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}