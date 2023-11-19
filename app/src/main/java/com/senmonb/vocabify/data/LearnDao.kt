package com.senmonb.vocabify.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface LearnDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(learn: Learn)

    @Update
    suspend fun update(learn: Learn)

    @Delete
    suspend fun delete(learn: Learn)

    @Query("SELECT * from learn")
    fun getAllLearn(): Flow<List<Learn>>

    @Query("SELECT * FROM learn WHERE category_id = :categoryId")
    fun getLearnByCategory(categoryId: Int): Flow<List<Learn>>

    @Query("SELECT * FROM learn WHERE is_look = 0")
    fun getAllUnlearned(): Flow<List<Learn>>

    @Query("SELECT * FROM learn WHERE is_look = 1")
    fun getLearned(): Flow<List<Learn>>
}
