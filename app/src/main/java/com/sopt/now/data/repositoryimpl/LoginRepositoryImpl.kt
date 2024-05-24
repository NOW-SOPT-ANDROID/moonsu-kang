package com.sopt.now.data.repositoryimpl

import android.content.SharedPreferences
import com.sopt.now.data.datasource.LoginDataSource
import com.sopt.now.data.model.RequestLoginDto
import com.sopt.now.domain.model.LoginResult
import com.sopt.now.domain.model.LoginInfo
import com.sopt.now.domain.repository.LoginRepository
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import javax.inject.Inject


class LoginRepositoryImpl @Inject constructor(
    private val loginDataSource: LoginDataSource,
    private val sharedPreferences: SharedPreferences
): LoginRepository {

    override suspend fun login(loginInfo: LoginInfo): LoginResult {
        val requestDto = RequestLoginDto(loginInfo.authenticationId, loginInfo.password)
        return try {
            val response = loginDataSource.login(requestDto)
            if (response.isSuccessful) {
                val memberId = response.headers()["Location"]
                if (memberId != null) {
                    sharedPreferences.edit().putString("memberId", memberId).apply()
                    LoginResult(true, response.body()?.message ?: "Login success")
                } else {
                    LoginResult(false, "응답에서 회원 ID를 찾을 수 없슴당...")
                }
            } else {
                val errorMessage = response.errorBody()?.string()?.let { parseErrorResponse(it) }
                    ?: "오류 메시지 없이 로그인 실패함"
                LoginResult(false, errorMessage)
            }
        } catch (e: Exception) {
            LoginResult(false, e.message ?: "exception error")
        }
    }

    private fun parseErrorResponse(jsonString: String): String? {
        return try {
            val json = Json.parseToJsonElement(jsonString).jsonObject
            json["message"]?.jsonPrimitive?.content
        } catch (e: Exception) {
            "Error parsing response: ${e.message}"
        }
    }
}
