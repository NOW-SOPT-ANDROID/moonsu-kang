package com.sopt.now.compose.screen

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.sopt.now.compose.R
import com.sopt.now.compose.component.ActionButton
import com.sopt.now.compose.component.InputField
import com.sopt.now.compose.utils.ToastUtil

@Composable
fun LoginScreen(
    navController: NavHostController,
    userIdFromSignUp: String = "",
    userPwdFromSignUp: String = "",
    nicknameFromSignUp: String = "",
    mbtiFromSignUp: String = ""

) {
    var userId by rememberSaveable { mutableStateOf(userIdFromSignUp) }
    var userPwd by rememberSaveable { mutableStateOf(userPwdFromSignUp) }
    val context = LocalContext.current // to use ToastMessage
    val loginSuccessMessage = stringResource(id = R.string.login_success)
    val loginFailMessage = stringResource(id = R.string.login_failed)


    Column(modifier = Modifier.padding(16.dp)) {
        LoginTitle()
        InputField(
            label = stringResource(id = R.string.login_id),
            value = userId,
            onValueChange = { userId = it },
            hint = stringResource(id = R.string.login_id_hint)
        )
        InputField(
            label = stringResource(id = R.string.login_pwd),
            value = userPwd,
            onValueChange = { userPwd = it },
            hint = stringResource(id = R.string.login_pwd_hint),
            isPassword = true
        )
        ActionButton(
            text = stringResource(id = R.string.login_button_sign),
            onClick = {
                if (userId.isNotEmpty() && userPwd.isNotEmpty() && userId == userIdFromSignUp && userPwd == userPwdFromSignUp) {
                    ToastUtil.show(context, loginSuccessMessage, Toast.LENGTH_LONG)
                    navController.navigate("main/$userId/$userPwd/$nicknameFromSignUp/$mbtiFromSignUp") {
                        // 네비게이션 스택에서 현재까지의 화면을 제거
                        popUpTo("login") { inclusive = true }
                    }
                } else {
                    ToastUtil.show(context, loginFailMessage, Toast.LENGTH_LONG)
                }
            }
        )
        ActionButton(
            text = stringResource(id = R.string.login_button_signup),
            onClick = { navController.navigate("signup") }
        )
    }
}

@Composable
fun LoginTitle() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 30.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.login_title),
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(30.dp))
    }
}