package com.example.app_aprendi_v2.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.app_aprendi_v2.data.User


@Dao
interface UserDao {
    @Insert
    suspend fun insert(user: User)

    @Query("SELECT * FROM users WHERE id = :id")
    suspend fun getUserById(id: Int): User?
}