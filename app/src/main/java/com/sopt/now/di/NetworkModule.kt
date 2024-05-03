package com.sopt.now.di

import com.sopt.now.data.RetrofitProvider
import com.sopt.now.data.datasource.LoginDataSource
import com.sopt.now.data.datasource.SignUpDataSource
import com.sopt.now.data.datasource.UserInfoDataSource


import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideLoginDataSource(): LoginDataSource {
        return RetrofitProvider.retrofit.create(LoginDataSource::class.java)
    }

    @Provides
    @Singleton
    fun provideUserInfoDataSource(): UserInfoDataSource {
        return RetrofitProvider.retrofit.create(UserInfoDataSource::class.java)
    }
    @Provides
    @Singleton
    fun provideSignUpDataSource(): SignUpDataSource {
        return RetrofitProvider.retrofit.create(SignUpDataSource::class.java)
    }

}
