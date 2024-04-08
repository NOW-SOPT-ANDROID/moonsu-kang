package com.sopt.now.presentation.login

import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.sopt.now.R
import com.sopt.now.databinding.ActivityLoginBinding
import com.sopt.now.model.User
import com.sopt.now.presentation.main.MainActivity
import com.sopt.now.presentation.signup.SignUpActivity
import com.sopt.now.utils.snackBar
import com.sopt.now.utils.toast

class LoginActivity : AppCompatActivity() {

    companion object {
        const val USER_DATA = "user_data"
    }

    private val binding by lazy { ActivityLoginBinding.inflate(layoutInflater) }
    /**
     * binding은 Activity의 생명주기 동안에 한 번만 초기화하면 된다.
     * lazy를 사용하면 한 번 초기화된 이후에는 그 값이 변하지 않아서 lateinit 사용 안했다!
     * */
    private val viewModel: LoginViewModel by viewModels()
    /**
     * 직접 SignUpViewModel() 인스턴스를 생성하는 것은 권장되지 않음
     * by viewModels() 델리게이트를 사용하면 구성 변경 등의 관리가 자동으로 처리된다고 구글링하면 나와있는걸 확인했습니다.
     * */


    private val signupResultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val user: User? = result.data?.getParcelableExtra(USER_DATA)
                user?.let { viewModel.setUserInfo(it) }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupClickListeners()
        observeUserInfo()
    }

    private fun observeUserInfo() {
        viewModel.userInfo.observe(this) { user ->
            // 로그인 화면에 사용자 ID를 자동으로 작성
            binding.edtLoginId.setText(user.id)
        }
    }

    private fun setupClickListeners() {
        binding.btnLoginSign.setOnClickListener { handleLoginClick() }
        binding.btnLoginSignup.setOnClickListener { handleSignupClick() }
    }

    private fun handleLoginClick() {
        val inputId = binding.edtLoginId.text.toString()
        val inputPwd = binding.edtLoginPwd.text.toString()

        if (viewModel.loginValid(inputId, inputPwd)) {
            toast(R.string.login_success)
            navigateToMainActivity()
        } else {
            snackBar("아이디 혹은 비밀번호를 다시 확인해주세요.")
        }
    }

    private fun handleSignupClick() {
        signupResultLauncher.launch(Intent(this@LoginActivity, SignUpActivity::class.java))
    }

    private fun navigateToMainActivity() {
        val intent = Intent(this@LoginActivity, MainActivity::class.java).apply {
            putExtra(USER_DATA, viewModel.userInfo.value)
        }
        startActivity(intent)
        finish()
    }

}