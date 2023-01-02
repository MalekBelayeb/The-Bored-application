package com.example.theboredapplication

import androidx.room.*
import com.example.theboredapplication.model.QuoteEntity

@Dao
interface QuoteDao {
    @Insert
    fun insert(quote: QuoteEntity)

    @Query("SELECT * FROM Quotes")
    fun getAllSavedQuotes(): MutableList<QuoteEntity>
}