package com.sopt.now.compose.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun UserInfoInputField(
    label: String,
    valueState: MutableState<String>,
    errorState: MutableState<String?>,
    hint: String,
    isPassword: Boolean = false
) {
    Column(modifier = Modifier.padding(vertical = 10.dp)) {
        Text(text = (label))
        OutlinedTextField(
            value = valueState.value,
            onValueChange = { valueState.value = it },
            label = { Text(hint) },
            modifier = Modifier.fillMaxWidth(),
            isError = errorState.value != null,
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = if (isPassword) KeyboardType.Password else KeyboardType.Text),
            visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None
        )
        errorState.value?.let {
            Text(it, color = Color.Red)
        }
    }
}

