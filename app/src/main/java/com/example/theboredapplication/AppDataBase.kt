package com.example.theboredapplication

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.theboredapplication.model.QuoteEntity

@Database(entities = [QuoteEntity::class], version = 1)
abstract class AppDataBase : RoomDatabase() {

    abstract fun quoteDao(): QuoteDao


    companion object {
        @Volatile
        private var instance: AppDataBase? = null

        fun getDatabase(context: Context): AppDataBase {
            if (instance == null) {
                synchronized(this) {
                    instance =
                        Room.databaseBuilder(context, AppDataBase::class.java, "quotes")
                            .allowMainThreadQueries()
                            .build()
                }
            }
            return instance!!
        }
    }
}