package com.sopt.now.compose.screen

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sopt.now.compose.R
import com.sopt.now.compose.component.UserInfoSection


@Composable
fun MainScreen(userId: String, userPwd: String, nickname: String, mbti: String) {
    Column(modifier = Modifier.padding(16.dp)) {
        //
        ProfileHeader()
        Spacer(modifier = Modifier.height(16.dp))

        // 사용자 정보 섹션
        UserInfoSection(stringResource(id = R.string.signup_id), userId)
        UserInfoSection(stringResource(id = R.string.signup_pwd), userPwd)
        UserInfoSection(stringResource(id = R.string.signup_nickname), nickname)
        UserInfoSection(stringResource(id = R.string.signup_mbti), mbti)
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
