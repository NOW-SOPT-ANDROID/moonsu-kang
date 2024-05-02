package com.sopt.now.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sopt.now.databinding.ItemFriendBinding
import com.sopt.now.model.Friend

class FriendAdapter : RecyclerView.Adapter<FriendViewHolder>() {
    private var friendList: List<Friend> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriendViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemFriendBinding.inflate(inflater, parent, false)
        return FriendViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FriendViewHolder, position: Int) {
        holder.onBind(friendList[position])
    }

    override fun getItemCount() = friendList.size

    fun setFriendList(newFriendList: List<Friend>) {
        if (friendList != newFriendList) {
            friendList = newFriendList
            notifyDataSetChanged() // 변경이 있을 때만 notifyDataSetChanged 호출.
        }
    }
}