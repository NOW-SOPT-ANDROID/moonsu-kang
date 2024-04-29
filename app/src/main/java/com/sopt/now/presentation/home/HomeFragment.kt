package com.sopt.now.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.sopt.now.MainActivity
import com.sopt.now.adapter.FriendAdapter
import com.sopt.now.adapter.UserAdapter
import com.sopt.now.databinding.FragmentHomeBinding
import com.sopt.now.model.User

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding
        get() = requireNotNull(_binding) { "바인딩 객체 좀 생성해주세요 제발!!" }
    // HomeViewModel을 Activity 범위에서 사용하여 공유 데이터 관리
    private val viewModel: HomeViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeUserInfo()
        initAdapter()
    }

    // 사용자 정보를 가져오고 ViewModel에 설정하기, 단방향 처리 흐름 지키기
    private fun getUserInfo() {
        arguments?.getParcelable<User>(MainActivity.USER_DATA)?.let { user ->
            viewModel.setUserInfo(user)
        }
    }

    // ViewModel의 LiveData를 관찰하여 UI 업데이트
    // as?를 사용하는 것을 권장
    private fun observeUserInfo() {
        getUserInfo()
        // UserAdapter 초기화, 사용자 정보 설정
        viewModel.userInfo.observe(viewLifecycleOwner) { user ->
            (binding.rvHome.adapter as? ConcatAdapter)?.adapters?.filterIsInstance<UserAdapter>()?.forEach {
                it.setUser(user)
            }
        }
    }

    /**
     * filterIsInstance<UserAdapter>() ~ 가 뭘까요?
     * 처리 로직은 ConcatAdapter 내의 어댑터 중에서 각 해당하는 Adapter 인스턴스만 찾아내고,
     * 알맞은 메서드를 호출해서 데이터 설정한다고 생각하면 good
     * */

    // 어댑터 초기화하고 RecyclerView에 설정
    private fun initAdapter() {
        val userAdapter = UserAdapter()
        val friendAdapter = FriendAdapter()
        friendAdapter.setFriendList(viewModel.mockFriendsInfo) // 정적 데이터 이므로 바로 설정
        val concatAdapter = ConcatAdapter(userAdapter, friendAdapter)
        binding.rvHome.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = concatAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}