package com.jonathan.disneyapp.di

import android.content.Context
import androidx.room.Room
import com.jonathan.disneyapp.data.database.DisneyDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    private const val DISNEY_DATABASE_NAME = "disney_database"

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, DisneyDatabase::class.java, DISNEY_DATABASE_NAME).build()

    @Singleton
    @Provides
    fun provideRegisterRepository(disneyDatabase: DisneyDatabase) = disneyDatabase.registerDao()
}