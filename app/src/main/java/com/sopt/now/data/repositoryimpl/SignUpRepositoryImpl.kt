package com.sopt.now.data.repositoryimpl

import com.sopt.now.data.datasource.SignUpDataSource
import com.sopt.now.data.model.RequestSignUpDto
import com.sopt.now.domain.repository.SignUpRepository
import com.sopt.now.domain.model.SignUpInfo
import com.sopt.now.domain.model.SignUpResult
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import javax.inject.Inject


class SignUpRepositoryImpl @Inject constructor(
    private val signUpDataSource: SignUpDataSource
): SignUpRepository {
    override suspend fun signUp(signUpInfo: SignUpInfo): SignUpResult {
        val requestDto = RequestSignUpDto(
            signUpInfo.authenticationId,
            signUpInfo.password,
            signUpInfo.nickname,
            signUpInfo.phone
        )
        val response = signUpDataSource.signUp(requestDto)
        return if (response.isSuccessful) {
            val body = response.body()
            SignUpResult(true, body?.message ?: "Success without a message.")
        } else {
            val errorResponse = response.errorBody()?.string()?.let { parseErrorResponse(it) }
                ?: "fail status: ${response.code()}"
            SignUpResult(false, errorResponse)
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


