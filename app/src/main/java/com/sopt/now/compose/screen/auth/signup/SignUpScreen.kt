package com.sopt.now.compose.screen.auth.signup

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.sopt.now.ServicePool
import com.sopt.now.compose.R
import com.sopt.now.compose.component.UserInfoInputField
import com.sopt.now.compose.navigation.NavRoutes
import com.sopt.now.compose.repositoryimpl.SignUpRepositoryImpl
import com.sopt.now.compose.utils.ToastUtil.toast
import com.sopt.now.compose.utils.rememberUserInfoState
import com.sopt.now.data.model.RequestSignUpDto

@Composable
fun SignUpScreen(
    navController: NavHostController
) {
    val context = LocalContext.current // to use ToastMessage
    val authService = ServicePool.authService
    val signUpRepository = SignUpRepositoryImpl(authService)
    val viewModel: SignUpViewModel = viewModel(factory = SignUpViewModelFactory(signUpRepository))

    val userInfoState = rememberUserInfoState()
    val signUpState by viewModel.signUpState.observeAsState()


    Column(modifier = Modifier.padding(16.dp)) {
        TitleSection(title = stringResource(id = R.string.signup_title))

        UserInfoInputField(
            label = "ID",
            valueState = userInfoState.userId,
            errorState = userInfoState.userIdError,
            hint = stringResource(id = R.string.signup_id_hint)
        )
        UserInfoInputField(
            label = "비밀번호",
            valueState = userInfoState.userPwd,
            errorState = userInfoState.userPwdError,
            hint = stringResource(id = R.string.signup_pwd_hint),
            isPassword = true
        )
        UserInfoInputField(
            label = "닉네임",
            valueState = userInfoState.nickname,
            errorState = userInfoState.nicknameError,
            hint = stringResource(id = R.string.signup_nickname_hint)
        )
        UserInfoInputField(
            label = "전화번호",
            valueState = userInfoState.phone,
            errorState = userInfoState.phoneError,
            hint = stringResource(id = R.string.signup_phone_hint)
        )

        Spacer(modifier = Modifier.height(16.dp))

        when (signUpState) {
            is SignUpState.Loading -> CircularProgressIndicator()
            is SignUpState.Success -> {
                LaunchedEffect(true) {
                    toast(context, (signUpState as SignUpState.Success).message)
                    navController.navigate(NavRoutes.LOGIN) {
                        popUpTo(NavRoutes.SIGN_UP) { inclusive = true }
                    }
                }
            }
            is SignUpState.Error -> {
                LaunchedEffect(true) {
                    toast(context, (signUpState as SignUpState.Error).message)
                }
            }
            null -> {
                Button(
                    onClick = {
                        if (userInfoState.validate()) {
                            val requestSignUpDto = RequestSignUpDto(
                                authenticationId = userInfoState.userId.value,
                                password = userInfoState.userPwd.value,
                                nickname = userInfoState.nickname.value,
                                phone = userInfoState.phone.value
                            )
                            viewModel.signUp(requestSignUpDto)
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(stringResource(id = R.string.login_button_signup))
                }
            }
        }
    }
}


@Composable
fun TitleSection(title: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 32.dp, bottom = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
    }
}