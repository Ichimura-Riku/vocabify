package com.senmonb.vocabify.data

import kotlinx.coroutines.flow.Flow

interface LearnRepository {

    // 新たにtodoを入れるためのメソッド。おそらく、gpt系のAPIで使用する
    suspend fun insertLearn(learn: Learn)

    // カラムをアップデートする。特定のカラムに対して操作するので、idの引数はいるのでは？と思ってしまうが、同じidなら更新されると信じる
    suspend fun updateLearn(learn: Learn)

    // 削除用のメソッド。もし関心のない単語であれば削除機能によって削除する
    suspend fun deleteLearn(learn: Learn)

    // 全ての学習用データを取得する。英和はここから出題する
    fun getAllLearn(): Flow<List<Learn>>

    // すでに学習したデータを取得する。和英はここから出題する
    fun getAllUnlearned(): Flow<List<Learn>>

    // 学習済みのデータを取得する。基本は使用しないと思う。振り返り機能をつけるとしたら使うかな？
    fun getLearned(): Flow<List<Learn>>
}
