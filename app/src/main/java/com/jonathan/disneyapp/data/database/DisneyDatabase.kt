package com.jonathan.disneyapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jonathan.disneyapp.data.database.entity.RegisterEntity
import com.jonathan.disneyapp.data.database.dao.RegisterDao

@Database(entities = [RegisterEntity::class], version = 1)
abstract class DisneyDatabase: RoomDatabase() {

    abstract fun registerDao(): RegisterDao
}