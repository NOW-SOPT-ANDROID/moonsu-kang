package com.sopt.now.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sopt.now.databinding.ItemUserBinding
import com.sopt.now.model.User

class UserAdapter : RecyclerView.Adapter<UserViewHolder>() {
    private var userInfo: User? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemUserBinding.inflate(inflater, parent, false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        // userInfo가 null이 아니면 onBind 메서드 호출해서 바인딩
        userInfo?.let { holder.onBind(it) }
    }

    override fun getItemCount(): Int {
        // userInfo가 null이 아니면 == 1 else 0
        return if (userInfo != null) 1 else 0
    }

    fun setUser(newUserInfo: User?) {
        if (this.userInfo != newUserInfo) {
            this.userInfo = newUserInfo
            notifyDataSetChanged() // 변경 있을때만
        }
    }
}