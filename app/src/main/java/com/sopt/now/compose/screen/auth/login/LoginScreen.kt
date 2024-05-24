package com.sopt.now.compose.screen.auth.login

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.sopt.now.compose.R
import com.sopt.now.compose.component.ActionButton
import com.sopt.now.compose.component.InputField
import com.sopt.now.compose.navigation.NavRoutes
import com.sopt.now.data.model.RequestLoginDto

@Composable
fun LoginScreen(
    navController: NavHostController
) {
    val viewModel: LoginViewModel = viewModel()
    var userId by rememberSaveable { mutableStateOf("") }
    var userPwd by rememberSaveable { mutableStateOf("") }
    val loginState = viewModel.loginState.observeAsState()
    val context = LocalContext.current // to use ToastMessage

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

        when (val state = loginState.value) {
            is LoginState.Loading -> CircularProgressIndicator()
            is LoginState.Success -> {
                LaunchedEffect(state.message) {
                    Toast.makeText(context, state.message, Toast.LENGTH_LONG).show()
                    navController.navigate(NavRoutes.HOME) {
                        popUpTo(NavRoutes.LOGIN) { inclusive = true }
                    }
                }
            }
            is LoginState.Error -> {
                LaunchedEffect(state.message) {
                    Toast.makeText(context, state.message, Toast.LENGTH_LONG).show()
                }
                ActionButton(
                    text = stringResource(id = R.string.login_button_signin),
                    onClick = {
                        if (userId.isNotEmpty() && userPwd.isNotEmpty()) {
                            viewModel.login(RequestLoginDto(userId, userPwd))
                        }
                    }
                )
            }
            null -> ActionButton(
                text = stringResource(id = R.string.login_button_signin),
                onClick = {
                    if (userId.isNotEmpty() && userPwd.isNotEmpty()) {
                        viewModel.login(RequestLoginDto(userId, userPwd))
                    }
                }
            )
        }
        ActionButton(
            text = stringResource(id = R.string.login_button_signup),
            onClick = { navController.navigate(NavRoutes.SIGN_UP) }
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
