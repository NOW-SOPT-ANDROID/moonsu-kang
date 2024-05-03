package com.sopt.now.presentation.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.sopt.now.R
import com.sopt.now.databinding.ActivityHomeBinding
import com.sopt.now.presentation.main.home.HomeFragment
import com.sopt.now.presentation.main.mypage.MyPageFragment
import com.sopt.now.presentation.main.search.SearchFragment
import dagger.hilt.android.AndroidEntryPoint

// Fragment의 부모 Activity
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityHomeBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // Activity가 최초 생성될 때만 HomeFragment 초기화하기
        if (savedInstanceState == null) {
            replaceFragment(HomeFragment())
        }
        setupBottomNavigation()
    }

    // Bottom Navigation 클릭 이벤트
    private fun setupBottomNavigation() {
        binding.bnvHome.setOnItemSelectedListener { menuItem ->
            val fragment = when (menuItem.itemId) {
                R.id.menu_home -> HomeFragment()
                R.id.menu_search -> SearchFragment()
                R.id.menu_mypage -> MyPageFragment()
                else -> null
            }
            fragment?.let {
                replaceFragment(it)
                true // 명시적 true return
            } ?: false // Fragment가 null일 경우 false
        }
    }

    // replaceFragment
    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fcv_home, fragment)
            .commit()
    }

}
