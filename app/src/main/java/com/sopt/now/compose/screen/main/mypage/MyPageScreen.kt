package com.sopt.now.compose.screen.main.mypage

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sopt.now.ServicePool
import com.sopt.now.compose.R
import com.sopt.now.compose.component.UserInfoSection
import com.sopt.now.compose.repositoryimpl.UserInfoRepositoryImpl
import com.sopt.now.compose.utils.ToastUtil.toast

@Composable
fun MyPageScreen(modifier: Modifier = Modifier) {
    val authService = ServicePool.authService
    val userInfoRepository = UserInfoRepositoryImpl(authService)
    val viewModel: MyPageViewModel = viewModel(factory = MyPageViewModelFactory(userInfoRepository))

    val userInfoState = viewModel.userInfoState
    val isUserInfoLoaded by remember { derivedStateOf { userInfoState.isLoaded } }
    val statusMessage by viewModel.statusMessage.observeAsState()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.getUserInfo()
    }

    LaunchedEffect(statusMessage) {
        statusMessage?.let {
            toast(context, it)
        }
    }

    Column(modifier = modifier.padding(16.dp)) {
        ProfileHeader()
        Spacer(modifier = Modifier.height(16.dp))

        if (isUserInfoLoaded) {
            UserInfoSection(title = "ID", info = userInfoState.authenticationId ?: "")
            UserInfoSection(title = "Nickname", info = userInfoState.nickname ?: "")
            UserInfoSection(title = "Phone", info = userInfoState.phone ?: "")
        } else {
            Text("Loading user info...")
        }
    }
}


@Composable
fun ProfileHeader() {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Image(
            painter = painterResource(id = R.drawable.ic_profile),
            contentDescription = "Profile",
            modifier = Modifier.size(60.dp)
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            text = stringResource(id = R.string.main_name),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
    }
    Column {
        Spacer(modifier = Modifier.width(20.dp))
        Text(
            text = stringResource(id = R.string.main_explain),
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

