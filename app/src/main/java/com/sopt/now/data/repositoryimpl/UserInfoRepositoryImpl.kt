package com.sopt.now.data.repositoryimpl

import android.content.SharedPreferences
import com.sopt.now.data.datasource.UserInfoDataSource
import com.sopt.now.domain.model.UserInfo
import com.sopt.now.domain.repository.UserInfoRepository
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import javax.inject.Inject


class UserInfoRepositoryImpl @Inject constructor(
    private val userInfoDataSource: UserInfoDataSource,
    private val sharedPreferences: SharedPreferences
) : UserInfoRepository {

    // 멤버 ID 불러오기
    override fun getMemberId(): String? {
        return sharedPreferences.getString("memberId", null)
    }

    // 사용자 정보 가져오기
    override suspend fun fetchUserInfo(memberId: String): UserInfo {
        val response = userInfoDataSource.fetchUserInfo(memberId)
        if (!response.isSuccessful) {
            val errorMessage = response.errorBody()?.string()?.let { parseErrorResponse(it) }
                ?: "에러 메시지 없음"
            throw Exception("사용 정보 get 실패: $errorMessage")
        }
        val userInfoData = response.body()?.data ?: throw Exception("User data is null")
        return UserInfo(userInfoData.authenticationId, userInfoData.nickname, userInfoData.phone)
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
