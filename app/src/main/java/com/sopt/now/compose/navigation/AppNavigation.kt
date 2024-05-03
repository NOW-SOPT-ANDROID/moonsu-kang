package com.sopt.now.compose.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sopt.now.compose.screen.auth.login.LoginScreen
import com.sopt.now.compose.screen.auth.signup.SignUpScreen

@Composable
fun AppNavigation() {
    // NavController 인스턴스 생성
    val navController = rememberNavController()

    //Navigation Graph
    NavHost(navController = navController, startDestination = NavRoutes.LOGIN) {
        // Go SignUpScreen
        composable(NavRoutes.SIGN_UP) {
            SignUpScreen(navController = navController)
        }

        composable(NavRoutes.LOGIN) {
            LoginScreen(navController = navController)
        }

        composable(NavRoutes.HOME) {
            BottomNavigation()
        }
    }
}