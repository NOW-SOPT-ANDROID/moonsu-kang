package com.sopt.now.presentation.signup

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.sopt.now.databinding.ActivitySignUpBinding
import com.sopt.now.model.User
import com.sopt.now.utils.snackBar
import com.sopt.now.utils.toast


class SignUpActivity : AppCompatActivity() {
    private val binding by lazy { ActivitySignUpBinding.inflate(layoutInflater) }
    /**
     * binding은 Activity의 생명주기 동안에 한 번만 초기화하면 된다.
     * lazy를 사용하면 한 번 초기화된 이후에는 그 값이 변하지 않아서 lateinit 사용 안했다!
     * */

    private val viewModel: SignUpViewModel by viewModels()
    /**
     * 직접 SignUpViewModel() 인스턴스를 생성하는 것은 권장되지 않음
     * by viewModels() 델리게이트를 사용하면 구성 변경 등의 관리가 자동으로 처리된다고 구글링하면 나와있는걸 확인했습니다.
     * */


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupClickListeners()
        observeSignUpResult()
    }

    private fun setupClickListeners() {
        binding.btnSignupComplete.setOnClickListener {
            val user = createUserFromInput()
            viewModel.signUp(user)
        }
    }

    private fun observeSignUpResult() {
        viewModel.signUpResult.observe(this) { validationResult ->
            if (validationResult == null) {
                viewModel.user.value?.let { user ->
                    toast("회원가입을 완료했습니다!")
                    val resultIntent = Intent().apply {
                        putExtra("user_data", user) // User 객체를 Intent에 포함
                    }
                    setResult(RESULT_OK, resultIntent)
                    finish()
                }
            } else {
                snackBar(validationResult) // 유효성 검사 실패 메시지 표시
            }
        }
    }


    private fun createUserFromInput(): User = User(
        id = binding.edtSignupId.text.toString(),
        pwd = binding.edtSignupPwd.text.toString(),
        nickname = binding.edtSignupNickname.text.toString(),
        mbti = binding.edtSignupMbtl.text.toString()
    )
}
