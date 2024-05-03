package com.sopt.now.presentation.auth.signup


import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.sopt.now.databinding.ActivitySignUpBinding
import com.sopt.now.presentation.auth.AuthStatus
import com.sopt.now.utils.toast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SignUpActivity : AppCompatActivity() {

    private val binding by lazy { ActivitySignUpBinding.inflate(layoutInflater) }
    private val viewModel: SignUpViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupClickListeners()
        observeSignUpState()
    }

    private fun setupClickListeners() {
        binding.btnSignupComplete.setOnClickListener {
            val authenticationId = binding.edtSignupId.text.toString()
            val password = binding.edtSignupPwd.text.toString()
            val nickname = binding.edtSignupNickname.text.toString()
            val phone = binding.edtSignupPhone.text.toString()
            viewModel.signUp(authenticationId, password, nickname, phone)
        }
    }

    private fun observeSignUpState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.signUpState.collect { state ->
                    handleLoading(state.isLoading)
                    when (state.status) {
                        AuthStatus.SUCCESS -> handleSuccess(state.message)
                        AuthStatus.FAILED -> handleError(state.message)
                        else -> {}
                    }
                }
            }
        }
    }

    private fun handleSuccess(message: String) {
        toast(message)
        finish()
    }

    private fun handleError(message: String) {
        toast(message)
    }

    private fun handleLoading(show: Boolean) {
        binding.pgbSignupLoading.visibility = if (show) View.VISIBLE else View.GONE
    }


}
