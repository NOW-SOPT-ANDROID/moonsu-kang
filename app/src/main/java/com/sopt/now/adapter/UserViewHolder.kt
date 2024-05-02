package com.sopt.now.adapter

import androidx.recyclerview.widget.RecyclerView
import com.sopt.now.databinding.ItemUserBinding
import com.sopt.now.model.User

class UserViewHolder(private val binding: ItemUserBinding): RecyclerView.ViewHolder(binding.root) {
    fun onBind(userInfo: User) {
        with(binding) {
            tvUserName.text = userInfo.nickname
            tvUserMbti.text = userInfo.mbti
        }
    }
}