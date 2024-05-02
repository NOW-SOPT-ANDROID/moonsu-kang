package com.sopt.now.compose.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sopt.now.compose.screen.HomeScreen
import com.sopt.now.compose.screen.MyPageScreen
import com.sopt.now.compose.screen.SearchScreen


@Composable
fun BottomNavigation(userId: String, nickname: String, mbti: String) {
    val navController = rememberNavController()
    // 기본적인 구조 구성
    Scaffold(
        bottomBar = { BottomNavigationBar(navController) }, // 바텀 네비바 설정
        containerColor = MaterialTheme.colorScheme.background
    ) { innerPadding ->
        // Navigation 구조 정의
        NavHost(navController, startDestination = BottomNavItem.Home.route) {
            composable(BottomNavItem.Home.route) {
                HomeScreen(nickname, mbti, Modifier.padding(innerPadding))
            }
            composable(BottomNavItem.Mypage.route) {
                MyPageScreen(userId, nickname, mbti, Modifier.padding(innerPadding))
            }
            composable(BottomNavItem.Search.route) {
                SearchScreen(Modifier.padding(innerPadding))
            }
        }
    }
}
