package com.example.theboredapplication.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Quotes")
data class QuoteEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "act")
    val act:String
)