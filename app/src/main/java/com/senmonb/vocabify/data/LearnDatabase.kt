package com.senmonb.vocabify.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Learn::class], version = 1, exportSchema = false)
abstract class LearnDatabase : RoomDatabase() {

    abstract fun learnDao(): LearnDao

    companion object {
        @Volatile
        private var Instance: LearnDatabase? = null

        fun getDatabase(context: Context): LearnDatabase {
            // if the Instance is not null, return it, otherwise create a new database instance.
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, LearnDatabase::class.java, "learn_database")
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
        }
    }
}
