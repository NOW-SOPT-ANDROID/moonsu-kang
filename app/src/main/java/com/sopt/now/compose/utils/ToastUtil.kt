package com.sopt.now.compose.utils

import android.content.Context
import android.widget.Toast

object ToastUtil {
    fun toast(context: Context, message: String, duration: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(context, message, duration).show()
    }
}