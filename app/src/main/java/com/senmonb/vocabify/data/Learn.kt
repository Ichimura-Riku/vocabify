package com.senmonb.vocabify.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "learn")
data class Learn(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "word")
    val word: String,
    @ColumnInfo(name = "translation")
    val translation: String,
    @ColumnInfo(name = "isLook")
    val isLook: Int,

    )