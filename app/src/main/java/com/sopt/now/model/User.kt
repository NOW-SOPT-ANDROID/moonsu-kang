package com.sopt.now.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val id: String,
    val pwd: String,
    val nickname: String,
    val mbti: String
) : Parcelable

