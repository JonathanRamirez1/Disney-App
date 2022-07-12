package com.jonathan.disneyapp.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.jonathan.disneyapp.data.database.entity.RegisterEntity

@Dao
interface RegisterDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUser(registerEntity: List<RegisterEntity>)
}