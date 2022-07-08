package com.jonathan.disneyapp.di

import com.jonathan.disneyapp.data.repository.LoginRepository
import com.jonathan.disneyapp.data.repository.LoginRepositoryImpl
import com.jonathan.disneyapp.data.repository.RegisterRepository
import com.jonathan.disneyapp.data.repository.RegisterRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UserModule {

    @Provides
    @Singleton
    fun provideRegisterRepository(registerRepositoryImpl: RegisterRepositoryImpl): RegisterRepository = registerRepositoryImpl


    @Provides
    @Singleton
    fun provideLoginRepository(loginRepositoryImpl: LoginRepositoryImpl): LoginRepository = loginRepositoryImpl
}