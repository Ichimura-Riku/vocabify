package com.senmonb.vocabify.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "learn")
data class Learn(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "learn_id")
    val id: Int = 0,
    @ColumnInfo(name = "category_id")
    val category: Int,
    @ColumnInfo(name = "word")
    val word: String,
    @ColumnInfo(name = "translation")
    val translation: String,
    @ColumnInfo(name = "is_look")
    val isLook: Int,
)
