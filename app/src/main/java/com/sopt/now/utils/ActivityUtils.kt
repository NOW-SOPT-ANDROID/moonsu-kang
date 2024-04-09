package com.sopt.now.utils

import android.app.Activity
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar

// 토스트 메시지
fun Activity.toast(@StringRes messageResId: Int, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, getString(messageResId), duration).show()
}

// 스낵바 메시지
fun Activity.snackBar(
    message: CharSequence,
    duration: Int = Snackbar.LENGTH_SHORT,
    view: View = this.findViewById(android.R.id.content)
) {
    val snackbar = Snackbar.make(view, message, duration)
    val snackbarView = snackbar.view
    val textView = snackbarView.findViewById(com.google.android.material.R.id.snackbar_text) as? TextView // Snackbar 텍스트 색상을 변경하려면, TextView 찾아야됨.
    textView?.setTextColor(ContextCompat.getColor(this, android.R.color.black)) // 텍스트 색상 변경.

    snackbar.show()
}
