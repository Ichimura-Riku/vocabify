package com.senmonb.vocabify.di

import android.content.Context
import com.senmonb.vocabify.data.LearnDatabase
import com.senmonb.vocabify.data.LearnRepository
import com.senmonb.vocabify.data.OfflineLearnRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object LearnRepositoryModules {
    @Provides
    @Singleton
    fun bindLearnRepository(
        @ApplicationContext context: Context,
    ): LearnRepository {
        return OfflineLearnRepository(LearnDatabase.getDatabase(context).learnDao())
    }
}
