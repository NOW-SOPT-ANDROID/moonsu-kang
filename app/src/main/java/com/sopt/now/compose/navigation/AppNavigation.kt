package com.sopt.now.compose.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sopt.now.compose.screen.LoginScreen
import com.sopt.now.compose.screen.MainScreen
import com.sopt.now.compose.screen.SignUpScreen

@Composable
fun AppNavigation() {
    // NavController 인스턴스 생성
    val navController = rememberNavController()

    //Navigation Graph
    NavHost(navController = navController, startDestination = NavRoutes.LOGIN) {
        // Go SignUpScreen
        composable(NavRoutes.SIGN_UP) {
            SignUpScreen(navController)
        }

        // 회원가입 후 넘어온 사용자 정보를 받기 위한 인자값 없는 로그인 화면
        composable(NavRoutes.LOGIN) {
            LoginScreen(navController)
        }

        // LoginScreen으로 넘어온 사용자 정보를 기반으로 로그인을 시도하는 경로
        composable(
            route = "${NavRoutes.LOGIN}/{${NavArgs.USER_ID}}/{${NavArgs.USER_PWD}}/{${NavArgs.NICKNAME}}/{${NavArgs.MBTI}}",
        ) { backStackEntry ->
            val userId = backStackEntry.arguments?.getString(NavArgs.USER_ID) ?: ""
            val userPwd = backStackEntry.arguments?.getString(NavArgs.USER_PWD) ?: ""
            val nickname = backStackEntry.arguments?.getString(NavArgs.NICKNAME) ?: ""
            val mbti = backStackEntry.arguments?.getString(NavArgs.MBTI) ?: ""

            // 추출한 사용자 정보를 LoginScreen에 전달
            LoginScreen(navController, userId, userPwd, nickname, mbti)
        }

        // MainScreen으로 이동하는 경로
        composable(
            route = "${NavRoutes.MAIN}/{${NavArgs.USER_ID}}/{${NavArgs.USER_PWD}}/{${NavArgs.NICKNAME}}/{${NavArgs.MBTI}}",

        ) { backStackEntry ->
            val userId = backStackEntry.arguments?.getString(NavArgs.USER_ID) ?: ""
            val userPwd = backStackEntry.arguments?.getString(NavArgs.USER_PWD) ?: ""
            val nickname = backStackEntry.arguments?.getString(NavArgs.NICKNAME) ?: ""
            val mbti = backStackEntry.arguments?.getString(NavArgs.MBTI) ?: ""

            // 추출한 사용자 정보를 MainScreen에 전달
            MainScreen(userId, userPwd, nickname, mbti)
        }
        /**
         * 명시적으로 인수의 타입을 지정하는 것을 추가해도 된다.
         * 이걸 추가하면 +타입 안정성, +Type Miss Match 오류 방지
        arguments = listOf(
        navArgument("userId") { type = NavType.StringType },
        navArgument("userPwd") { type = NavType.StringType },
        navArgument("nickname") { type = NavType.StringType },
        navArgument("mbti") { type = NavType.StringType }
        )
         */
    }
}