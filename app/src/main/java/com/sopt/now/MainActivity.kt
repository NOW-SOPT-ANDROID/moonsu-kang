package com.sopt.now

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.sopt.now.databinding.ActivityHomeBinding
import com.sopt.now.model.User
import com.sopt.now.presentation.home.HomeFragment
import com.sopt.now.presentation.mypage.MyPageFragment
import com.sopt.now.presentation.search.SearchFragment

// Fragment의 부모 Activity
class MainActivity : AppCompatActivity() {
    companion object {
        const val USER_DATA = "user_data"
    }
    private val binding by lazy { ActivityHomeBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // Activity가 최초 생성될 때만 HomeFragment 초기화하기
        if (savedInstanceState == null) {
            initializeHomeFragment()
        }

        setupBottomNavigation()
    }

    // HomeFragment 초기화하고 UserData 설정
    private fun initializeHomeFragment() {
        val user = intent.getParcelableExtra<User>(USER_DATA)
        replaceFragment(HomeFragment().applyBundle(user))
    }

    // Bottom Navigation 클릭 이벤트
    private fun setupBottomNavigation() {
        binding.bnvHome.setOnItemSelectedListener { menuItem ->
            val fragment = when (menuItem.itemId) {
                R.id.menu_home -> HomeFragment().applyBundle(intent.getParcelableExtra(USER_DATA))
                R.id.menu_search -> SearchFragment()
                R.id.menu_mypage -> MyPageFragment().applyBundle(intent.getParcelableExtra(USER_DATA))
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

    /**
     *  applyBundle 확장함수
     *  Bundle을 생성하고, 거기에 데이터를 담은 다음, 이걸 fragment의 arguments에 설정
     * */
    private fun Fragment.applyBundle(user: User?): Fragment {
        arguments = Bundle().apply {
            putParcelable(USER_DATA, user)
        }
        return this
    }
}
