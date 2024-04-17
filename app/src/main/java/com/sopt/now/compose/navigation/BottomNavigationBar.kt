package com.sopt.now.compose.navigation


import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomNavigationBar(navController: NavController) {
    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.Mypage,
        BottomNavItem.Search
    )
    //바텀네비바 구성
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.primaryContainer,
        contentColor = MaterialTheme.colorScheme.onPrimaryContainer
    ) {
        val navBackStackEntry = navController.currentBackStackEntryAsState() // 현재 NavStack의 상태를 관찰
        val currentRoute = navBackStackEntry.value?.destination?.route // 현재 활성화된 라우트를 추출

        items.forEach { item ->
            NavigationBarItem(
                icon = { Icon(item.icon, contentDescription = stringResource(id = item.labelId)) },  // 아이콘
                label = { Text(stringResource(id = item.labelId)) },  // 라벨
                selected = currentRoute == item.route,  // 현재 라우트와 아이템 라우트가 같으면 선택 상태
                onClick = {
                    // 다른 라우트를 클릭했을 경우, 해당 라우트로 가기
                    if (currentRoute != item.route) {
                        navController.navigate(item.route) {
                            // NavStack을 최상위까지 클리어 하고 새로운 루트로 가기
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true  // 상태 저장
                            }
                            launchSingleTop = true  // 기존 인스턴스 재사용
                            restoreState = true  // 상태 복원
                        }
                    }
                }
            )
        }
    }
}
