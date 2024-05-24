package com.sopt.now.di

import com.sopt.now.data.repositoryimpl.LoginRepositoryImpl
import com.sopt.now.data.repositoryimpl.SignUpRepositoryImpl
import com.sopt.now.data.repositoryimpl.UserInfoRepositoryImpl
import com.sopt.now.domain.repository.LoginRepository
import com.sopt.now.domain.repository.SignUpRepository
import com.sopt.now.domain.repository.UserInfoRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindLoginRepository(
        loginRepositoryImpl: LoginRepositoryImpl
    ): LoginRepository

    @Binds
    @Singleton
    abstract fun bindSignUpRepository(
        signUpRepositoryImpl: SignUpRepositoryImpl
    ): SignUpRepository

    @Binds
    @Singleton
    abstract fun bindUserInfoRepository(
        userInfoRepositoryImpl: UserInfoRepositoryImpl
    ): UserInfoRepository
}

