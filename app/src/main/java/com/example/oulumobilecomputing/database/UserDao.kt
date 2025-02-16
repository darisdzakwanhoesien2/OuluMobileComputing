package com.example.oulumobilecomputing.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserData(user: UserEntity)

    @Query("SELECT * FROM user_data ORDER BY id DESC LIMIT 1")
    suspend fun getUserData(): UserEntity?
}
