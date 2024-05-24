package com.sopt.now.domain.usecase

import android.content.SharedPreferences
import javax.inject.Inject

class SaveMemberIdUseCase @Inject constructor(
    private val sharedPreferences: SharedPreferences
) {
    fun execute(memberId: String) {
        sharedPreferences.edit().putString("memberId", memberId).apply()
    }
}


