package com.sopt.now.compose.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector
import com.sopt.now.compose.R


enum class BottomNavItem(val route: String, @StringRes val labelId: Int, val icon: ImageVector) {
    Home(route = NavRoutes.HOME, labelId = R.string.menu_home, icon = Icons.Default.Home),
    Mypage(route = NavRoutes.MYPAGE, labelId = R.string.menu_mypage, icon = Icons.Default.AccountCircle),
    Search(route = NavRoutes.SEARCH, labelId = R.string.menu_search, icon = Icons.Default.Search)
}

