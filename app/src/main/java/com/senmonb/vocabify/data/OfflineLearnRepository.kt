package com.senmonb.vocabify.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext

class OfflineLearnRepository constructor(private val learnDao: LearnDao) : LearnRepository {
    override suspend fun insertLearn(learn: Learn) = withContext(Dispatchers.IO) { learnDao.insert(learn = learn) }

    override suspend fun updateLearn(learn: Learn) = withContext(Dispatchers.IO) { learnDao.update(learn = learn) }

    override suspend fun deleteLearn(learn: Learn) = withContext(Dispatchers.IO) { learnDao.delete(learn = learn) }

    override fun getAllLearn(): Flow<List<Learn>> = learnDao.getAllLearn().flowOn(Dispatchers.IO)

    override fun getLearnByCategory(category: String): Flow<List<Learn>> = learnDao.getLearnByCategory(category).flowOn(Dispatchers.IO)

    override fun getAllUnlearned(): Flow<List<Learn>> = learnDao.getAllUnlearned().flowOn(Dispatchers.IO)

    override fun getLearned(): Flow<List<Learn>> = learnDao.getLearned().flowOn(Dispatchers.IO)
}
