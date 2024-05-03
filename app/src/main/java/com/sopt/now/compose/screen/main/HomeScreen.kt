package com.sopt.now.compose.screen.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sopt.now.compose.R
import com.sopt.now.compose.component.FriendItem
import com.sopt.now.compose.utils.FriendRepository

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    val friends = FriendRepository.friends
    LazyColumn(modifier = modifier.padding(10.dp)) {
        item {
//            UserProfileInfo(nickname, mbti)
        }
        items(friends) { friend ->
            FriendItem(friend)
        }
    }
}

@Composable
fun UserProfileInfo(nickname: String, mbti: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween, // 좌우 간격 최대로 설정
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
    ) {
        Column {
            Text(
                text = nickname,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(4.dp))  // 닉네임과 MBTI 사이 간격
            Text(
                text = mbti,
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Gray
            )
        }
        Image(
            painter = painterResource(id = R.drawable.ic_user_profile),
            contentDescription = "UserProfile",
            modifier = Modifier.size(60.dp)
        )
    }
}
