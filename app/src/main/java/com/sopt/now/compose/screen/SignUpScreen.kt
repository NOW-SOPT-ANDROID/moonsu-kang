package com.sopt.now.compose.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.sopt.now.compose.R
import com.sopt.now.compose.component.UserInfoInputField
import com.sopt.now.compose.utils.rememberUserInfoState


@Composable
fun SignUpScreen(navController: NavHostController) {
    val userInfoState = rememberUserInfoState()

    Column(modifier = Modifier.padding(16.dp)) {
        TitleSection(title = stringResource(id = R.string.signup_title))

        UserInfoInputField(
            label = "ID",
            userInfoState.userId,
            userInfoState.userIdError,
            hint = stringResource(id = R.string.signup_id_hint)
        )
        UserInfoInputField(
            "비밀번호",
            userInfoState.userPwd,
            userInfoState.userPwdError,
            hint = stringResource(id = R.string.signup_pwd_hint),
            true
        )
        UserInfoInputField(
            "닉네임",
            userInfoState.nickname,
            userInfoState.nicknameError,
            hint = stringResource(id = R.string.signup_nickname_hint)
        )
        UserInfoInputField(
            "MBTI",
            userInfoState.mbti,
            userInfoState.mbtiError,
            hint = stringResource(id = R.string.signup_mbti_hint)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (userInfoState.validate()) {
                    navController.navigate("login/${userInfoState.userId.value}/${userInfoState.userPwd.value}/${userInfoState.nickname.value}/${userInfoState.mbti.value}") {
                        popUpTo("signup") { inclusive = true }
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(stringResource(id = R.string.login_button_signup))
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