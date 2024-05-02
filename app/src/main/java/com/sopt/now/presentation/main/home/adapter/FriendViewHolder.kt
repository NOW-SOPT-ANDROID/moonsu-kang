package com.sopt.now.presentation.main.home.adapter

import androidx.recyclerview.widget.RecyclerView
import com.sopt.now.databinding.ItemFriendBinding
import com.sopt.now.presentation.main.home.model.Friend

class FriendViewHolder(private val binding: ItemFriendBinding): RecyclerView.ViewHolder(binding.root) {
    companion object {
        private const val MAX_DESCRIPTION_LENGTH = 25
        private const val MAX_MUSIC_LENGTH = 8
    }

    fun onBind(friendData: Friend) {
        with(binding) {
            ivFriendProfile.setImageResource(friendData.profileImage)
            tvFriendName.text = friendData.name
            tvFriendDescription.apply {
                text = friendData.selfDescription.abbreviateText(MAX_DESCRIPTION_LENGTH)
            }
            tvFriendMusic.apply {
                text = friendData.music.abbreviateText(MAX_MUSIC_LENGTH)
            }
        }
    }

    /**
     * String Class에 메서드 정의 하기
     * 텍스트 축약, maxLength보다 크면 그이상 잘라내고 ...
     * */
    private fun String.abbreviateText(maxLength: Int): String =
        if (this.length > maxLength) this.substring(0, maxLength) + "..." else this
}
