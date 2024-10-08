package com.example.app_aprendi_v2.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.app_aprendi_v2.data.User


@Database(entities = [User::class], version = 1)
 abstract class AppDatabase : RoomDatabase() {
     abstract fun userDao(): UserDao

     companion object {
         @Volatile
         private var INSTANCE: AppDatabase? = null

         fun getDatabase(context: Context): AppDatabase {
             return INSTANCE ?: synchronized(this) {
                 val instance = Room.databaseBuilder(
                     context.applicationContext,
                     AppDatabase::class.java,
                     "app_database"
                 ).build()
                 INSTANCE = instance
                 instance
             }
         }
     }
 }